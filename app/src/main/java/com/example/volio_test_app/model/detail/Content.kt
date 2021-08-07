package com.example.volio_test_app.model.detail

data class Content(
    val caption: String,
    val duration: Int,
    val href: String,
    val main_color: String,
    val markups: List<Markup>,
    val original_height: Int,
    val original_width: Int,
    val preview_image: PreviewImage,
    val text: String
)