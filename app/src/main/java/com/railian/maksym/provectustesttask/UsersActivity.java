package com.railian.maksym.provectustesttask;

import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Observable;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.skyfishjy.library.RippleBackground;
import com.squareup.picasso.Picasso;

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

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorScheme(R.color.blue, R.color.yellow);
        DatabaseHelper databaseHelper = new DatabaseHelper();
        jsonMapper = new JsonMapper();
        usersRecyclerView = (RecyclerView) findViewById(R.id.users_recycler_view);
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
       // usersRecyclerView.setClipToOutline(true);

        // If database is empty - we are load data from server
        mitems = jsonMapper.convertToNetworkModel(databaseHelper.select());

        if (mitems.isEmpty()) {

            loadData();
        }

        setAdapter();

    }

    private void setAdapter() {
        usersRecyclerView.setAdapter(new UsersAdapter(mitems));
    }

    @Override
    public void onRefresh() {

        // Clear database before data updating
        final DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.deleteAll();

        mSwipeRefreshLayout.setRefreshing(true);

        // Load data from server
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
                mSwipeRefreshLayout.setRefreshing(false);
                setAdapter();
            }
        }, 2000);
    }

    public void loadData() {

        // Retrofit request execute in async mode
        MyApplication.getApi().getData(20, "au,br,ca,ch,de,dk,es,fi,fr,gb,ie,nz,tr,us").enqueue(new Callback<UserItem>() {
            @Override
            public void onResponse(Call<UserItem> call, Response<UserItem> response) {
                DatabaseHelper databaseHelper = new DatabaseHelper();
                if (response.isSuccessful()) {

                    // Insert loaded data in database and add to current list for insert in recyclerview
                    try {

                        databaseHelper.insert(jsonMapper.convertFromNetworkModel(response.body().getResults()));
                        mitems = response.body().getResults();
                        setAdapter();

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

    // Delcarate holder for rercyclerview item
    private class UsersHolder extends RecyclerView.ViewHolder {
        private ImageView userImageView;
        private TextView userTextView;
        private TextView userLocationView;

        public UsersHolder(View itemView) {
            super(itemView);

            // RecyclerView child views init
            userImageView = itemView.findViewById(R.id.user_item_image);
            userTextView = itemView.findViewById(R.id.user_item_fio);
            userLocationView = itemView.findViewById(R.id.user_item_location);
        }


    }

    private class UsersAdapter extends RecyclerView.Adapter<UsersHolder> {
        private List<Result> mUsersItems;

        public UsersAdapter(List<Result> items) {

            mUsersItems = items;
        }


        @Override
        public UsersHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            // Inflate RecyclerView child from users_item layout and set onclick listener for RV childs
            LayoutInflater inflater = LayoutInflater.from(UsersActivity.this);
            View v = inflater.from(parent.getContext()).inflate(R.layout.users_item, parent, false);
            v.setOnClickListener(new MyOnClickListener());

            return new UsersHolder(v);
        }

        @Override
        public void onBindViewHolder(UsersHolder holder, int position) {

            // Apply data for RecyclerView child views
            Result usersItem = mUsersItems.get(position);

            Picasso.with(UsersActivity.this).load(usersItem.getPicture().getMedium()).into(holder.userImageView);

            holder.userTextView.setText(jsonMapper.formateCorrectNames(usersItem.getName().getTitle()) + "."
                    + " " + jsonMapper.formateCorrectNames(usersItem.getName().getFirst())
                    + " " + jsonMapper.formateCorrectNames(usersItem.getName().getLast()));

            holder.userLocationView.setText(jsonMapper.formateCorrectNames(usersItem.getLocation().getCity() + ", " +
                    usersItem.getNat()));


        }


        @Override
        public int getItemCount() {

            return mUsersItems.size();
        }

    }

    public class MyOnClickListener implements View.OnClickListener {
        DatabaseHelper databaseHelper = new DatabaseHelper();


        // If RecyclerView child is clicked
        @Override
        public void onClick(View v) {

            int itemPosition = usersRecyclerView.getChildAdapterPosition(v);

            Intent intent = new Intent(UsersActivity.this, UserActivity.class);
            intent.putExtra("USER_ID", itemPosition);
            startActivity(intent);

            Log.e("Clicked on ", String.valueOf(itemPosition));
        }
    }
}
