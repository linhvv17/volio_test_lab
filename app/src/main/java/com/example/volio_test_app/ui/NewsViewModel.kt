package com.example.volio_test_app.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.volio_test_app.NewsApplication
import com.example.volio_test_app.model.Item
import com.example.volio_test_app.repository.NewsRepository
import com.example.volio_test_app.util.Resource
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response

class NewsViewModel(
    app: Application,
    val newsRepository: NewsRepository
) : AndroidViewModel(app) {

    val items: MutableLiveData<Resource<Item>> = MutableLiveData()
    var item: Item? = null


    init {
        getItems()
    }

    fun getItems() = viewModelScope.launch {
        safeItems()
    }


    private fun handleItem(response: Response<Item>) : Resource<Item> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if(item == null) {
                    item = resultResponse
                } else {
                    val oldArticles = item?.items
                    val newArticles = resultResponse.items
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(item ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private suspend fun safeItems() {
        items.postValue(Resource.Loading())
        try {
            if(hasInternetConnection()) {
                val response = newsRepository.getAllNewsFeed()
                items.postValue(handleItem(response))
            } else {
                items.postValue(Resource.Error("No internet connection"))
            }
        } catch(t: Throwable) {
            when(t) {
                is IOException -> items.postValue(Resource.Error("Network Failure"))
                else -> items.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}












