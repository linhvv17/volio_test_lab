package com.example.volio_test_app.model

import java.io.Serializable

data class Content(
    val avatar: Any,
    val content: Any,
    val content_type: String,
    val description: String,
    val document_id: String,
    val images: MutableList<Image>,
    val origin_url: String,
    val published_date: String,
    val publisher: Publisher,
    val title: String
) : Serializable