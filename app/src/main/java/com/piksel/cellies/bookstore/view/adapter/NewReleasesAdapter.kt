package com.piksel.cellies.bookstore.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.piksel.cellies.bookstore.databinding.ItemBookBinding
import com.piksel.cellies.bookstore.model.data.Book
import com.squareup.picasso.Picasso

class NewReleasesAdapter(private val listener: NewReleaseItemListener) : RecyclerView.Adapter<BookViewHolder>() {

    private val items = ArrayList<Book>()

    interface NewReleaseItemListener {
        fun onClickedBook(bookIsbn: String)
    }

    fun setItems(items: ArrayList<Book>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding: ItemBookBinding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) = holder.bind(items[position])
}

class BookViewHolder(private val itemBinding: ItemBookBinding, private val listener: NewReleasesAdapter.NewReleaseItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var book: Book

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: Book) {
        this.book = item
        itemBinding.title.text = item.title
        itemBinding.subtitle.text = item.subtitle

        Picasso
            .get()
            .load(item.image)
            .resize(75, 75)
            .centerCrop()
            .into(itemBinding.image)
    }

    override fun onClick(v: View?) {
        listener.onClickedBook(book.isbn13)
    }
}


