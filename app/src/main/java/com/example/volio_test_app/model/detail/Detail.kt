package com.example.volio_test_app.model.detail

data class Detail(
    val description: String,
    val document_id: String,
    val origin_url: String,
    val published_date: String,
    val publisher: Publisher,
    val sections: List<Section>,
    val template_type: String,
    val title: String
)