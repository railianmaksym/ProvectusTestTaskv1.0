package com.railian.maksym.provectustesttask.ui.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.railian.maksym.provectustesttask.R
import com.railian.maksym.provectustesttask.models.Result
import com.railian.maksym.provectustesttask.ui.activity.UserActivity
import com.railian.maksym.provectustesttask.utilites.JsonMapper
import com.squareup.picasso.Picasso

class UsersAdapter(private val mUsersItems: List<Result>, private val context: Context) : RecyclerView.Adapter<UsersHolder>() {
    private val jsonMapper = JsonMapper()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.users_item, parent, false)
        return UsersHolder(v)
    }

    override fun onBindViewHolder(holder: UsersHolder, position: Int) {
        val usersItem = mUsersItems[position]
        Picasso.with(context).load(usersItem.picture?.medium).into(holder.userImageView)
        holder.userTextView.text = (jsonMapper.formateCorrectNames(usersItem.name?.title) + "."
                + " " + jsonMapper.formateCorrectNames(usersItem.name?.first)
                + " " + jsonMapper.formateCorrectNames(usersItem.name?.last))
        holder.userLocationView.text = jsonMapper.formateCorrectNames(usersItem.location?.city + ", " +
                usersItem.nat)
        holder.userItem.setOnClickListener { view ->
            val intent = Intent(context, UserActivity::class.java)
            intent.putExtra("USER_ID", position)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {

        return mUsersItems.size
    }
}

class UsersHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var userImageView: ImageView = itemView.findViewById(R.id.user_item_image)
    var userTextView: TextView = itemView.findViewById(R.id.user_item_fio)
    var userLocationView: TextView = itemView.findViewById(R.id.user_item_location)
    var userItem: RelativeLayout = itemView.findViewById(R.id.user_item)
}

