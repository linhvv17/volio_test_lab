package com.example.volio_test_app.ui

import android.app.Application
import androidx.lifecycle.*
import com.example.volio_test_app.model.detail.Detail
import com.example.volio_test_app.repository.DetailRepository
import com.example.volio_test_app.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class DetailViewModel(
    app: Application,
    val detailRepository: DetailRepository
) : ViewModel() {

    val detailLiveData = MutableLiveData<Detail>()

    fun fetchDetail() {
        viewModelScope.launch {
            val detail = withContext(Dispatchers.IO) {
                detailRepository.getDetail()
            }
            detailLiveData.value = detailRepository.getDetail()
        }
    }

}
