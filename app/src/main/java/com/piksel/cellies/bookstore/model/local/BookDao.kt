package com.example.rickandmorty.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.piksel.cellies.bookstore.model.data.Book

@Dao
interface BookDao {

    @Query("SELECT * FROM books")
    fun getAllBooks() : LiveData<List<Book>>

    @Query("SELECT * FROM books WHERE isbn13 = :isbn")
    fun getBook(isbn: String): LiveData<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books: List<Book>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)


}