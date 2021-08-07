package com.example.volio_test_app.repository

import com.example.volio_test_app.api.RetrofitInstance


class DetailRepository(
) {
    suspend fun getDetail() =
        RetrofitInstance.api.getDetail()

}