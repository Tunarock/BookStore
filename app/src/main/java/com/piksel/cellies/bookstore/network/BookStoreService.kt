package com.piksel.cellies.bookstore.network

import com.piksel.cellies.bookstore.model.data.Book
import com.piksel.cellies.bookstore.model.data.NewReleases
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookStoreService {

    @GET("new")
    suspend fun getNewReleases(): Response<NewReleases>

    @GET("books/{isbn}")
    suspend fun getBook(@Path("isbn") isbn: String) : Response<Book>

}