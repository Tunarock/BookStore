package com.piksel.cellies.bookstore.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piksel.cellies.bookstore.model.data.NewReleases
import com.piksel.cellies.bookstore.model.repository.BookRepository
import com.piksel.cellies.bookstore.utils.Resource
import kotlinx.coroutines.launch

class NewReleasesViewModel @ViewModelInject constructor(private val repo: BookRepository): ViewModel() {
    val newReleases = repo.getNewReleases()
}