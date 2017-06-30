package com.railian.maksym.provectustesttask;

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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



public class UsersActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private static final String TAG="UsersActivity";

    private RecyclerView usersRecyclerView;
    private List<UsersItem> mitems=new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorScheme(R.color.blue,  R.color.yellow);
        DatabaseHelper databaseHelper=new DatabaseHelper();

        usersRecyclerView = (RecyclerView)findViewById(R.id.users_recycler_view);
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mitems=databaseHelper.select();

        if(mitems.isEmpty()) {
            new RandomUsersItemTask().execute();
        }

        setAdapter();

    }
    private void setAdapter(){
        usersRecyclerView.setAdapter(new UsersAdapter(mitems));
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);

        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                new RandomUsersItemTask().execute();
                mSwipeRefreshLayout.setRefreshing(false);
                setAdapter();
            }
        }, 2000);
    }


    private class UsersHolder extends RecyclerView.ViewHolder  {
        private ImageView userImageView;
        private TextView userTextView;

        public UsersHolder(View itemView) {
            super(itemView);
            userImageView=itemView.findViewById(R.id.user_item_image);
            userTextView=itemView.findViewById(R.id.user_item_fio);
        }


    }

    private class UsersAdapter extends RecyclerView.Adapter<UsersHolder>{
        private List<UsersItem> mUsersItems;

        public UsersAdapter(List<UsersItem> items){

            mUsersItems=items;
        }


        @Override
        public UsersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(UsersActivity.this);
            View v =inflater.from(parent.getContext()).inflate(R.layout.users_item,parent,false);
            v.setOnClickListener(new MyOnClickListener());

            return new UsersHolder(v);
        }

        @Override
        public void onBindViewHolder(UsersHolder holder, int position) {
            UsersItem usersItem=mUsersItems.get(position);

            Picasso.with(UsersActivity.this).load(usersItem.getUrl()).into(holder.userImageView);

            holder.userTextView.setText(usersItem.getName());


        }


        @Override
        public int getItemCount() {

            return mUsersItems.size();
        }


    }

    private  class RandomUsersItemTask extends AsyncTask<Void,Void,List<UsersItem>>{

        @Override
        protected List<UsersItem> doInBackground(Void... voids) {
           RandomUserApi randomUserApi=  new RandomUserApi();
          //  Log.e("LEST_ASYNC ",randomUserApi.getItems(20).toString());
            return randomUserApi.getItems(20);

        }

        @Override
        protected void onPostExecute(List<UsersItem> items) {
            DatabaseHelper databaseHelper=new DatabaseHelper();
            databaseHelper.insert(items);


            mitems=databaseHelper.select();
            setAdapter();
        }
    }

    public class MyOnClickListener implements View.OnClickListener {
        DatabaseHelper databaseHelper=new DatabaseHelper();
        @Override
        public void onClick(View v) {
            int itemPosition = usersRecyclerView.indexOfChild(v);
            UsersItem userClicked =databaseHelper.selectByID(itemPosition);
            Log.e("Clicked on ",userClicked.getName());
            Log.e("Clicked on ",String.valueOf(itemPosition));
        }
    }
}
