package com.piksel.cellies.bookstore.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.piksel.cellies.bookstore.databinding.ViewBookListItemBinding
import com.piksel.cellies.bookstore.model.Book
import com.squareup.picasso.Picasso

class BookListAdapter(private val onClick: (Book) -> Unit) :
    ListAdapter<Book, BookListAdapter.BookListViewHolder>(BookDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewBinding = ViewBookListItemBinding.inflate(inflater, parent, false)
        return BookListViewHolder(viewBinding, onClick)
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    //useless property removed: , private val bookListViewModel: BookListViewModel
    class BookListViewHolder(private val itemBinding: ViewBookListItemBinding, val onClick: (Book) -> Unit)
        : RecyclerView.ViewHolder(itemBinding.root){
        private val bookTextView = itemBinding.bookText
        private val bookImageView = itemBinding.bookImage
        private var currentBook: Book? = null

        init {
            itemBinding.root.setOnClickListener{
                currentBook?.let{
                    onClick(it)
                }

            }
        }

        fun bind(book: Book) {
            currentBook = book
            bookTextView.text = book.title
            //check if this is correct!!!!
            Log.d("PICASSO","LOADING URL IMAGE from "+book.imgURI)
            Picasso
                .get()
                .load(book.imgURI)
                .resize(50, 50)
                .centerCrop()
                .into(bookImageView)
        }
    }
}

object BookDiffCallback : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.title == newItem.title
    }
}

