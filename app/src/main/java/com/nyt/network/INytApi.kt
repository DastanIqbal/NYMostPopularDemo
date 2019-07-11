package com.nyt.network

import com.nyt.network.model.MostViewedB
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface INytApi {

    @GET("viewed/{id}.json")
    fun mostViewed(
            @Path("id") period: Int
    ): Deferred<MostViewedB>
}