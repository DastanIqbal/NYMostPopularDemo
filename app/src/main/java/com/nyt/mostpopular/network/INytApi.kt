package com.nyt.mostpopular.network

import com.nyt.mostpopular.network.model.MostViewedB
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by dastaniqbal on 25/11/2018.
 * 25/11/2018 11:17
 */
interface INytApi {

    @GET("viewed/{id}.json")
    fun mostViewed(
            @Path("id") period: Int
    ): Deferred<MostViewedB>
}