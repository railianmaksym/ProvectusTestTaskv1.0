package com.railian.maksym.provectustesttask

import android.app.Application
import android.arch.persistence.room.Room
import android.net.Uri
import com.railian.maksym.provectustesttask.network.RandomUserApi


import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by fluffy on 30.06.17.
 */

class MyApplication : Application() {

    // Retrofit and Api Declaration
    private var retrofit: Retrofit? = null


    override fun onCreate() {
        super.onCreate()


        // Create HTTP client
        val httpClient = OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)


        // Retrofit intialisation
        retrofit = Retrofit.Builder()
                .baseUrl(Uri.parse("https://randomuser.me/").buildUpon().build().toString())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()

        api = retrofit!!.create<RandomUserApi>(RandomUserApi::class.java!!)

         database = Room.databaseBuilder(applicationContext, com.railian.maksym.provectustesttask.repository.Database::class.java, "my-todo-db").allowMainThreadQueries().build()

    }

    companion object {
        var api: RandomUserApi? = null
            private set
        var database : com.railian.maksym.provectustesttask.repository.Database? = null
            private set
    }
}
