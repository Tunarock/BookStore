package com.piksel.cellies.bookstore.model.repository

import com.example.rickandmorty.data.local.BookDao
import com.piksel.cellies.bookstore.network.BookStoreDataSource
import com.piksel.cellies.bookstore.utils.performGetOperation
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val remoteDataSource: BookStoreDataSource,
    private val localDataSource: BookDao
) {

    fun getBook(isbn: String) =  performGetOperation(
        databaseQuery = { localDataSource.getBook(isbn) },
        networkCall = { remoteDataSource.getBook(isbn) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getNewReleases() = performGetOperation(
        databaseQuery = { localDataSource.getAllBooks() },
        networkCall = { remoteDataSource.getNewRealeses() },
        saveCallResult = { localDataSource.insertAll(it.books) }
    )

}
