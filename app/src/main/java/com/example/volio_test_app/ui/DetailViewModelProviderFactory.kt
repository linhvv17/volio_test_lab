package com.example.volio_test_app.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.volio_test_app.NewsApplication
import com.example.volio_test_app.repository.DetailRepository
import com.example.volio_test_app.repository.NewsRepository

class DetailViewModelProviderFactory(
    val app: Application,
    val detailRepository: DetailRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(app, detailRepository) as T
    }
}