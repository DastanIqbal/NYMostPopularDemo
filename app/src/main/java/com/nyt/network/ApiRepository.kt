package com.nyt.network

import com.nyt.network.model.ResultB
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * Created by dastaniqbal on 26/11/2018.
 * 26/11/2018 10:08
 */
enum class NetworkStates {
    LOADING,
    SUCCESS,
    FAILED,
    NOINTERNET
}

class ApiRepository {
    private val TAG = this::class.java.simpleName

    companion object {
        @JvmStatic
        val INSTANCE = ApiRepository()
    }

    private fun mostViewedAsync(period: Int) = ApiWrapper.getAPI().mostViewed(period)

    suspend fun mostViewed(period: Int = 1, networkStateCallback: (networkState: NetworkStates) -> Unit): List<ResultB> {
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
            if (e is HttpException) {
                //TODO: Handle Error e.code() e.message e.response()
                networkStateCallback.invoke(NetworkStates.FAILED)
            } else if (e is UnknownHostException) {
                networkStateCallback.invoke(NetworkStates.NOINTERNET)
            }
            e.printStackTrace()
            emptyList()
        }
    }
}