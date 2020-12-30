package com.piksel.cellies.bookstore

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.piksel.cellies.bookstore.model.Book
import com.piksel.cellies.bookstore.view.adapter.BookListAdapter
import com.piksel.cellies.bookstore.viewmodel.BooksListViewModel
import com.piksel.cellies.bookstore.viewmodel.BooksListViewModelFactory

const val BOOK_ID = "book id"

class BooksListActivity : AppCompatActivity() {
    private val newBookActivityRequestCode = 1
    private val booksListViewModel by viewModels<BooksListViewModel>{
        BooksListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        //SETUP ADAPTER
        val booksAdapter = BookListAdapter(::adapterOnClick)

        //ATTACH ADAPTER to RECYCLERVIEW
        val recyclerView: RecyclerView = findViewById(R.id.booklist_recycler_view)
        recyclerView.adapter = booksAdapter

        //OBSERVER
        booksListViewModel.booksLiveData.observe(this, {
            it?.let {
            booksAdapter.submitList(it as MutableList<Book>)
        }
    })

    }

    private fun adapterOnClick(book: Book) {
        TODO() //HERE TO IMPLEMENT THE SECOND SCREEN: book details activity
//        val intent = Intent(this, BookDetailActivity()::class.java)
//        intent.putExtra(BOOK_ID, book.title)
//        startActivity(intent)

    }
}