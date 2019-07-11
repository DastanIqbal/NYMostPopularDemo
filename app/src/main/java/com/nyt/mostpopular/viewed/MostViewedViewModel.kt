package com.nyt.mostpopular.viewed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nyt.mostpopular.network.ApiRepository
import kotlinx.coroutines.Dispatchers

class MostViewedViewModel : ViewModel() {
    private val repository = ApiRepository()

    val mostViewedList = liveData(Dispatchers.IO) {
        val retrivedTodo = repository.mostViewed(1)
        emit(retrivedTodo)
    }
}
