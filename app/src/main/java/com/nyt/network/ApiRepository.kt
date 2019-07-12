package com.nyt.network

import com.nyt.network.model.ResultB
import java.net.UnknownHostException

class ApiRepository {
    private val TAG = this::class.java.simpleName

    companion object {
        @JvmStatic
        val INSTANCE = ApiRepository()
    }

    private fun mostViewedAsync(period: Int) = ApiWrapper.getAPI().mostViewed(period)

    suspend fun mostViewed(
        period: Int = 1,
        networkStateCallback: (networkState: NetworkStates) -> Unit
    ): List<ResultB> {
        return try {
            networkStateCallback.invoke(NetworkStates.LOADING)

            val mostViewedB = mostViewedAsync(period).await()
            val status = mostViewedB.status?.toLowerCase().equals("ok")

            if (status && mostViewedB.results.isNotEmpty()) {
                networkStateCallback.invoke(NetworkStates.SUCCESS)
                mostViewedB.results
            } else {
                networkStateCallback.invoke(NetworkStates.FAILED)
                emptyList()
            }
        } catch (e: Exception) {
            if (e is UnknownHostException) {
                networkStateCallback.invoke(NetworkStates.NOINTERNET)
            } else {
                networkStateCallback.invoke(NetworkStates.FAILED)
            }
            e.printStackTrace()
            emptyList()
        }
    }
}