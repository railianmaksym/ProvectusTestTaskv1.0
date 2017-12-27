package com.railian.maksym.provectustesttask.network;

import com.railian.maksym.provectustesttask.models.UserItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fluffy on 28.06.17.
 */

public interface RandomUserApi {
    @GET("api/")
    Call<UserItem> getData(@Query("results") Integer results, @Query("nat") String nats);
}

