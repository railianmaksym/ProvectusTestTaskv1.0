package com.railian.maksym.provectustesttask.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import android.widget.TextView;

import com.railian.maksym.provectustesttask.R;
import com.railian.maksym.provectustesttask.repository.DatabaseHelper;
import com.railian.maksym.provectustesttask.repository.DatabaseModel;
import com.squareup.picasso.Picasso;


public class UserActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        DatabaseHelper databaseHelper = new DatabaseHelper();

        ImageView imageView = findViewById(R.id.user_image);
        TextView textView = findViewById(R.id.user_name);
        TextView textViewLocation = findViewById(R.id.user_location);
        TextView textViewEmail = findViewById(R.id.user_email);
        TextView textViewPhone = findViewById(R.id.user_phone);
        TextView textViewCell = findViewById(R.id.user_cell);
        TextView textViewAdress = findViewById(R.id.user_adress);
        TextView textViewNickname= findViewById(R.id.user_nickname);
        TextView textViewDob= findViewById(R.id.user_dob);
        TextView textViewDor= findViewById(R.id.user_dor);

        int itemPosition = (int) getIntent().getSerializableExtra("USER_ID");
        DatabaseModel user = databaseHelper.selectByID(itemPosition);
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
