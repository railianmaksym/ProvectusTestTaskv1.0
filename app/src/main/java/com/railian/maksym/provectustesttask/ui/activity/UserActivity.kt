package com.railian.maksym.provectustesttask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView

import android.widget.TextView
import com.railian.maksym.provectustesttask.MyApplication

import com.railian.maksym.provectustesttask.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user.*


class UserActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val itemPosition = intent.getSerializableExtra("USER_ID") as Int
        val user = MyApplication.database!!.databaseDao().findTaskById(itemPosition)
        user_name.text = user!!.title + "." + " " + user.first + " " + user.last
        user_location.text = user.city + ", " + user.nat
        user_email.text = user.email
        user_phone.text = user.phone
        user_cell.text = user.cell
        user_nickname.text = user.username
        user_dob.text = user.dob.subSequence(0, 10)
        user_dor.text = user.registered.subSequence(0, 10)
        user_adress.text = user.street + ", " + user.city + ", " + user.state + ", " + user.nat + ", " + user.postcode
        Picasso.with(this@UserActivity).load(user.large).into(user_image)

    }

}
