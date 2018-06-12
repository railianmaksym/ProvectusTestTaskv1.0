package com.railian.maksym.provectustesttask.ui.activity

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast

import com.railian.maksym.provectustesttask.ui.adapters.UsersAdapter
import com.railian.maksym.provectustesttask.utilites.JsonMapper
import com.railian.maksym.provectustesttask.MyApplication
import com.railian.maksym.provectustesttask.R
import com.railian.maksym.provectustesttask.models.Result
import com.railian.maksym.provectustesttask.models.UserItem

import java.util.ArrayList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UsersActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private var usersRecyclerView: RecyclerView? = null
    private var mitems: List<Result>? = ArrayList()
    private var mSwipeRefreshLayout: SwipeRefreshLayout? = null
    private var jsonMapper: JsonMapper? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        mSwipeRefreshLayout = findViewById(R.id.refresh)
        mSwipeRefreshLayout!!.setOnRefreshListener(this)
        mSwipeRefreshLayout!!.setColorScheme(R.color.blue, R.color.yellow)
        val databaseHelper = MyApplication.database!!.databaseDao()
        jsonMapper = JsonMapper()
        usersRecyclerView = findViewById(R.id.users_recycler_view)
        usersRecyclerView!!.layoutManager = LinearLayoutManager(this)
        mitems = jsonMapper!!.convertToNetworkModel(databaseHelper.getAllTasks())

        if (mitems!!.isEmpty()) {
            loadData()
        }
        setAdapter()
    }

    private fun setAdapter() {
        usersRecyclerView!!.adapter = UsersAdapter(mitems!!, this)
    }

    override fun onRefresh() {

        // Clear database before data updating
        val databaseHelper = MyApplication.database!!.databaseDao()
        databaseHelper.dropTable()
        mSwipeRefreshLayout!!.isRefreshing = true
        loadData()
        // Load data from server
        mSwipeRefreshLayout!!.postDelayed({
            setAdapter()
            mSwipeRefreshLayout!!.isRefreshing = false
        }, 2000)
    }

    private fun loadData() {
        MyApplication.api!!.getData(20, "au,br,ca,ch,de,dk,es,fi,fr,gb,ie,nz,tr,us").enqueue(object : Callback<UserItem> {
            override fun onResponse(call: Call<UserItem>, response: Response<UserItem>) {
                val databaseHelper = MyApplication.database!!.databaseDao()
                if (response.isSuccessful) {
                    try {

                        databaseHelper.addAll(jsonMapper!!.convertFromNetworkModel(response.body()!!.results!!))
                        mitems = response.body()!!.results

                    } catch (e: Exception) {
                        Log.e(TAG, " INSERT ", e)
                    }

                } else
                    Log.e("No Connection ", response.errorBody()!!.toString())
            }

            override fun onFailure(call: Call<UserItem>, t: Throwable) {
                Toast.makeText(this@UsersActivity, "Check your internet connection ", Toast.LENGTH_SHORT).show()
                Log.e("INTERNET ERROR ", " RESPONSE NON EXECUTE ", t)
            }
        })

    }

    companion object {

        // Debug tag
        private val TAG = "UsersActivity"
    }

}
