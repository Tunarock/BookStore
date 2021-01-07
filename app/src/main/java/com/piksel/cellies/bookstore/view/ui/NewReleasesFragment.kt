package com.piksel.cellies.bookstore.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.piksel.cellies.bookstore.R
import com.piksel.cellies.bookstore.databinding.NewReleasesFragmentBinding
import com.piksel.cellies.bookstore.utils.Resource
import com.piksel.cellies.bookstore.utils.autoCleared
import com.piksel.cellies.bookstore.view.adapter.NewReleasesAdapter
import com.piksel.cellies.bookstore.viewmodel.NewReleasesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewReleasesFragment: Fragment(), NewReleasesAdapter.NewReleaseItemListener {

    private var binding: NewReleasesFragmentBinding by autoCleared()
    private val viewModel: NewReleasesViewModel by viewModels()
    private lateinit var adapter: NewReleasesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewReleasesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = NewReleasesAdapter(this)
        binding.newReleasesRv.layoutManager = LinearLayoutManager(requireContext())
        binding.newReleasesRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.newReleases.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedBook(bookIsbn: String) {
        findNavController().navigate(
            R.id.action_newReleasesFragment_to_bookDetailFragment,
            bundleOf("isbn" to bookIsbn)
        )
    }
}