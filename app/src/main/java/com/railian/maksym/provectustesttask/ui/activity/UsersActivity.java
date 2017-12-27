package com.railian.maksym.provectustesttask.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.railian.maksym.provectustesttask.ui.adapters.UsersAdapter;
import com.railian.maksym.provectustesttask.utilites.JsonMapper;
import com.railian.maksym.provectustesttask.MyApplication;
import com.railian.maksym.provectustesttask.R;
import com.railian.maksym.provectustesttask.models.Result;
import com.railian.maksym.provectustesttask.models.UserItem;
import com.railian.maksym.provectustesttask.repository.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UsersActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    // Debug tag
    private static final String TAG = "UsersActivity";

    private RecyclerView usersRecyclerView;
    private List<Result> mitems = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private JsonMapper jsonMapper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        mSwipeRefreshLayout = findViewById(R.id.refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorScheme(R.color.blue, R.color.yellow);
        DatabaseHelper databaseHelper = new DatabaseHelper();
        jsonMapper = new JsonMapper();
        usersRecyclerView = findViewById(R.id.users_recycler_view);
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mitems = jsonMapper.convertToNetworkModel(databaseHelper.select());

        if (mitems.isEmpty()) {
            loadData();
        }
        setAdapter();
    }

    private void setAdapter() {
        usersRecyclerView.setAdapter(new UsersAdapter(mitems,this));
    }

    @Override
    public void onRefresh() {

        // Clear database before data updating
        final DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.deleteAll();
        mSwipeRefreshLayout.setRefreshing(true);
        loadData();
        // Load data from server
        mSwipeRefreshLayout.postDelayed(() -> {
            setAdapter();
            mSwipeRefreshLayout.setRefreshing(false);
        }, 2000);
    }

    public void loadData() {


        MyApplication.getApi().getData(20, "au,br,ca,ch,de,dk,es,fi,fr,gb,ie,nz,tr,us").enqueue(new Callback<UserItem>() {
            @Override
            public void onResponse(Call<UserItem> call, Response<UserItem> response) {
                DatabaseHelper databaseHelper = new DatabaseHelper();
                if (response.isSuccessful()) {
                    try {

                        databaseHelper.insert(jsonMapper.convertFromNetworkModel(response.body().getResults()));
                        mitems = response.body().getResults();

                    } catch (Exception e) {
                        Log.e(TAG, " INSERT ", e);
                    }

                } else Log.e("No Connection ", response.errorBody().toString());
            }

            @Override
            public void onFailure(Call<UserItem> call, Throwable t) {
                Toast.makeText(UsersActivity.this, "Check your internet connection ", Toast.LENGTH_SHORT).show();
                Log.e("INTERNET ERROR ", " RESPONSE NON EXECUTE ", t);
            }
        });

    }

}
