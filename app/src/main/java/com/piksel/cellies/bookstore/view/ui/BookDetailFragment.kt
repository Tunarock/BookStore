package com.piksel.cellies.bookstore.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.piksel.cellies.bookstore.databinding.BookDetailFragmentBinding
import com.piksel.cellies.bookstore.model.data.Book
import com.piksel.cellies.bookstore.utils.Resource
import com.piksel.cellies.bookstore.viewmodel.BookDetailViewModel
import com.piksel.cellies.bookstore.utils.autoCleared
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailFragment : Fragment() {

    private var binding: BookDetailFragmentBinding by autoCleared()
    private val viewModel: BookDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BookDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("isbn")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.book.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindBook(it.data!!)
                    binding.progressBar.visibility = View.GONE
                    binding.bookCl.visibility = View.VISIBLE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.bookCl.visibility = View.GONE
                }
            }
        })
    }

    private fun bindBook(book: Book) {
        binding.title.text = book.title
        binding.subtitle.text = book.subtitle
        binding.description.text = book.desc
        binding.authors.text = book.authors
        Picasso
                .get()
                .load(book.image)
                .resize(50, 350)
                .centerCrop()
                .into(binding.image)

    }
}


