package com.piksel.cellies.bookstore.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.piksel.cellies.bookstore.model.repository.BookRepository

class BooksListViewModel(val bookRepository: BookRepository) : BaseViewModel() {

    val booksLiveData = bookRepository.getBookList()

    /* If the name and description are present, create new Book and add it to the datasource */
    fun insertBook(bookName: String?, bookDescription: String?) {
        TODO("Not yet requested")
    }
}

class BooksListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BooksListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BooksListViewModel(
                bookRepository = BookRepository.getInstance(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
