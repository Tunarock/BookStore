package com.piksel.cellies.bookstore.model.repository

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.piksel.cellies.bookstore.model.Book
import com.piksel.cellies.bookstore.model.data.bookList

class BookRepository(resources: Resources) {

    //loading temporary static data set
    private val initialBookList = bookList(resources)
    private val booksLiveData = MutableLiveData(initialBookList)

    fun getBookList(): LiveData<List<Book>> {
        return booksLiveData
    }

    fun addBook(newBook: Book) {
        TODO("Not yet implemented")
    }

    companion object {
        private var INSTANCE: BookRepository? = null
        fun getInstance(resources: Resources): BookRepository {
            return synchronized(BookRepository::class){
                val newInstance = INSTANCE ?: BookRepository(resources)
                INSTANCE = newInstance
                newInstance
            }

        }

    }

}
