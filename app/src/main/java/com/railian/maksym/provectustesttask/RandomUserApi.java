package com.railian.maksym.provectustesttask;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by fluffy on 28.06.17.
 */

public class RandomUserApi {
    private static final String TAG="RandomUserApi";

    public String getJsonString(String Url) throws IOException{
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(Url)
                .build();
        Response response=client.newCall(request).execute();
        String result= response.body().string();
        return result;

    }


    public List<UsersItem> getItems(int count){

        List<UsersItem> usersItems=new ArrayList<>();

          String url=  Uri.parse("https://randomuser.me/api/")
                    .buildUpon()
                    .appendQueryParameter("results",String.valueOf(count)).build().toString();
            try {
                String JsonString = getJsonString(url);
                JSONObject jsonObject = new JSONObject(JsonString);
               usersItems= parseJson(jsonObject);
                Log.d("JSON: ",JsonString);
            }catch (IOException ioe){
                Log.e(TAG,"Ошибка данных ",ioe);
            }catch (JSONException jse){
                Log.e(TAG,"Ошибка JSON ",jse);
            }
       // usersItems = new Select().from(UsersItem.class).queryList();
        return usersItems;
    }

    private List<UsersItem> parseJson(JSONObject jsonObject)  throws IOException,JSONException {

        JSONArray jsonArray = jsonObject.getJSONArray("results");

        List<UsersItem> items = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            UsersItem item = new UsersItem();
            JSONObject user = jsonArray.getJSONObject(i);
            item.setId(i+1);
            item.setName(user.getJSONObject("name").getString("title")+" "+
            user.getJSONObject("name").getString("first")+" "+
            user.getJSONObject("name").getString("last"));
            item.setUrl(user.getJSONObject("picture").getString("medium"));
            items.add(item);

            /*item.setPictureUrlSmall(user.getJSONObject("picture").getString("medium"));
            item.setTitle(user.getJSONObject("name").getString("title"));
            item.setFirst(user.getJSONObject("name").getString("first"));
            item.setLast(user.getJSONObject("name").getString("last"));
            items.add(item);*/
        }



        Log.e(TAG,items.toString());
        return items;

    }

    }

