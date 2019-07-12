package com.nyt.mostpopular.mostviewed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyt.mostpopular.R
import com.nyt.network.ApiRepository
import com.nyt.network.NetworkStates
import com.nyt.network.model.ResultB
import kotlinx.coroutines.launch

class MostViewedViewModel : ViewModel() {
    private val repository = ApiRepository.INSTANCE
    var mostViewedList = MutableLiveData<List<ResultB>>()
    var networkStatusLiveData = MutableLiveData<NetworkStates>()

    private var period = 1

    init {
        loadMostViewedList()
    }

    private fun loadMostViewedList() {
        viewModelScope.launch {
            val result = repository.mostViewed(period) {
                networkStatusLiveData.postValue(it)
            }
            mostViewedList.postValue(result)
        }
    }

    fun reload() {
        loadMostViewedList()
    }

    fun onOptionsItemSelected(itemId: Int) {
        when (itemId) {
            R.id.mnu_1day -> {
                period = 1
                reload()
            }
            R.id.mnu_7days -> {
                period = 7
                reload()
            }
            R.id.mnu_30days -> {
                period = 30
                reload()
            }
        }
    }
}
