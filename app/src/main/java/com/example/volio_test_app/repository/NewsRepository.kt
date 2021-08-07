package com.example.volio_test_app.repository

import com.example.volio_test_app.api.RetrofitInstance


class NewsRepository(
) {
    suspend fun getAllNewsFeed() =
        RetrofitInstance.api.getAllNewsFeed()

}