package com.piksel.cellies.bookstore.model.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    val authors: String?,
    val desc: String?,
    val error: String?,
    val image: String,
    val isbn10: String?,
    @PrimaryKey
    val isbn13: String,
    val language: String?,
    val pages: String?,
    val price: String,
    val publisher: String?,
    val rating: String?,
    val subtitle: String,
    val title: String,
    val url: String,
    val year: String?
)