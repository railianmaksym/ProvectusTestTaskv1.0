package com.railian.maksym.provectustesttask.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.railian.maksym.provectustesttask.R;
import com.railian.maksym.provectustesttask.models.Result;
import com.railian.maksym.provectustesttask.ui.activity.UserActivity;
import com.railian.maksym.provectustesttask.utilites.JsonMapper;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersHolder> {
    private List<Result> mUsersItems;
    private Context context;
    private JsonMapper jsonMapper = new JsonMapper();

    public UsersAdapter(List<Result> items, Context context) {
        this.context = context;
        mUsersItems = items;
    }


    @Override
    public UsersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_item, parent, false);
        return new UsersHolder(v);
    }

    @Override
    public void onBindViewHolder(UsersHolder holder, final int position) {
        Result usersItem = mUsersItems.get(position);
        Picasso.with(context).load(usersItem.getPicture().getMedium()).into(holder.userImageView);
        holder.userTextView.setText(jsonMapper.formateCorrectNames(usersItem.getName().getTitle()) + "."
                + " " + jsonMapper.formateCorrectNames(usersItem.getName().getFirst())
                + " " + jsonMapper.formateCorrectNames(usersItem.getName().getLast()));
        holder.userLocationView.setText(jsonMapper.formateCorrectNames(usersItem.getLocation().getCity() + ", " +
                usersItem.getNat()));
        holder.user_item.setOnClickListener(view -> {
            Intent intent = new Intent(context, UserActivity.class);
            intent.putExtra("USER_ID", position);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {

        return mUsersItems.size();
    }
}

class UsersHolder extends RecyclerView.ViewHolder {
    ImageView userImageView;
    TextView userTextView;
    TextView userLocationView;
    RelativeLayout user_item;

    UsersHolder(View itemView) {
        super(itemView);

        userImageView = itemView.findViewById(R.id.user_item_image);
        userTextView = itemView.findViewById(R.id.user_item_fio);
        userLocationView = itemView.findViewById(R.id.user_item_location);
        user_item = itemView.findViewById(R.id.user_item);
    }


}

