package com.example.volio_test_app.api

import com.example.volio_test_app.model.Item
import com.example.volio_test_app.model.detail.Detail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("newsfeed.json")
    suspend fun getAllNewsFeed() : Response<Item>

    @GET("detail.json")
    suspend fun getDetail() : Detail
}