package com.piksel.cellies.bookstore.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.piksel.cellies.bookstore.model.data.Book
import com.piksel.cellies.bookstore.model.repository.BookRepository
import com.piksel.cellies.bookstore.utils.Resource

class BookDetailViewModel @ViewModelInject constructor(
    private val repository: BookRepository
) : ViewModel() {

    private val _isbn = MutableLiveData<String>()

    private val _book = _isbn.switchMap { isbn13 ->
        repository.getBook(isbn13)
    }
    val book: LiveData<Resource<Book>> = _book

    fun start(isbn13: String) {
        _isbn.value = isbn13
    }

}
