package com.piksel.cellies.bookstore.model.data

data class NewReleases(
    val books: List<Book>,
    val error: String,
    val total: String
)