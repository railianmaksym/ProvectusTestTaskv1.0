package com.railian.maksym.provectustesttask;

import android.app.Application;
import android.net.Uri;

import com.railian.maksym.provectustesttask.network.RandomUserApi;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by fluffy on 30.06.17.
 */

public class MyApplication extends Application {

    // Retrofit and Api Declaration
    private Retrofit retrofit;
    private static RandomUserApi randomUserApi;

    @Override
    public void onCreate() {
        super.onCreate();

        // Database Manager initialisation
        FlowManager.init(new FlowConfig.Builder(this).build());

        // Create HTTP client
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS);



        // Retrofit intialisation
        retrofit=new Retrofit.Builder()
                .baseUrl(Uri.parse("https://randomuser.me/").buildUpon().build().toString())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        randomUserApi=retrofit.create(RandomUserApi.class);
   }

    public static RandomUserApi getApi(){

        return randomUserApi;
    }
}
