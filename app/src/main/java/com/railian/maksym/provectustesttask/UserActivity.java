package com.railian.maksym.provectustesttask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.style.TtsSpan;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;

import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Date;

/**
 * Created by fluffy on 30.06.17.
 */

public class UserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // Initializing views
        ImageView imageView = (ImageView) findViewById(R.id.user_image);
        TextView textView = (TextView) findViewById(R.id.user_name);
        TextView textViewLocation = (TextView) findViewById(R.id.user_location);
        TextView textViewEmail = (TextView) findViewById(R.id.user_email);
        TextView textViewPhone = (TextView) findViewById(R.id.user_phone);
        TextView textViewCell = (TextView) findViewById(R.id.user_cell);
        TextView textViewAdress = (TextView) findViewById(R.id.user_adress);
        TextView textViewNickname=(TextView)findViewById(R.id.user_nickname);
        TextView textViewDob=(TextView)findViewById(R.id.user_dob);
        TextView textViewDor=(TextView)findViewById(R.id.user_dor);

        DatabaseHelper databaseHelper = new DatabaseHelper();

        // Get data from UsersActivity
        int itemPosition = (int) getIntent().getSerializableExtra("USER_ID");
        DatabaseModel user = databaseHelper.selectByID(itemPosition);

        // Applying data for views
        textView.setText(user.getTitle()+"."+" "+user.getFirst()+" "+user.getLast());
        textViewLocation.setText(user.getCity() + ", " + user.getNat());
        textViewEmail.setText(user.getEmail());
        textViewPhone.setText(user.getPhone());
        textViewCell.setText(user.getCell());
        textViewNickname.setText(user.getUsername());
        textViewDob.setText(user.getDob().substring(0,10));
        textViewDor.setText(user.getRegistered().substring(0,10));
        textViewAdress.setText(user.getStreet() + ", " + user.getCity() + ", " + user.getState() + ", " +user.getNat()+", "+ user.getPostcode());
        Picasso.with(UserActivity.this).load(user.getLarge()).into(imageView);

    }

}
