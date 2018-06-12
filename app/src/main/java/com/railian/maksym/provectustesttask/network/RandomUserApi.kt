package com.railian.maksym.provectustesttask.network

import com.railian.maksym.provectustesttask.models.UserItem

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by fluffy on 28.06.17.
 */

interface RandomUserApi {
    @GET("api/")
    fun getData(@Query("results") results: Int?, @Query("nat") nats: String): Call<UserItem>
}

