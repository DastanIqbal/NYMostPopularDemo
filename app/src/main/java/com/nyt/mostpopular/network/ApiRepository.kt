package com.nyt.mostpopular.network

import com.nyt.mostpopular.network.model.ResultB
import retrofit2.HttpException

/**
 * Created by dastaniqbal on 26/11/2018.
 * 26/11/2018 10:08
 */
class ApiRepository {
    private val TAG = this::class.java.simpleName

    companion object {
        val INSTANCE = ApiRepository()
    }

    private fun mostViewedAsync(period: Int) = ApiWrapper.getAPI().mostViewed(period)

    suspend fun mostViewed(period: Int = 1): List<ResultB> {
        return try {
            val mostViewedB = mostViewedAsync(period).await()
            val status = mostViewedB.status?.toLowerCase().equals("ok")

            if (status && mostViewedB.results.isNotEmpty())
                mostViewedB.results
            else emptyList()
        } catch (e: Exception) {
            if (e is HttpException) {
                //TODO: Handle Error e.code() e.message e.response()
            }
            e.printStackTrace()
            emptyList()
        }
    }
}