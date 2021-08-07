package com.example.volio_test_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.volio_test_app.NewsApplication
import com.example.volio_test_app.R
import com.example.volio_test_app.repository.DetailRepository
import com.example.volio_test_app.repository.NewsRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: NewsViewModel
    lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val newsRepository = NewsRepository()
        val viewModelProviderFactory = NewsViewModelProviderFactory(application, newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

        val detailRepository = DetailRepository()
        val detailViewModelProviderFactory = DetailViewModelProviderFactory(application, detailRepository)
        detailViewModel = ViewModelProvider(this, detailViewModelProviderFactory).get(DetailViewModel::class.java)


        setContentView(R.layout.activity_main)
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
    }
}