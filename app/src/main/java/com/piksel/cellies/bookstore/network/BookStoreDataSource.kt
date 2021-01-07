package com.piksel.cellies.bookstore.network

import javax.inject.Inject

class BookStoreDataSource @Inject constructor(
    private val bookStoreService: BookStoreService
): BaseDataSource() {

    suspend fun getNewRealeses() = getResult { bookStoreService.getNewReleases() }
    suspend fun getBook(isbn: String) = getResult { bookStoreService.getBook(isbn) }

}