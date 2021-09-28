package com.example.getdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;

    private List<User> users;
    private ClickListener myListener;

    public interface ClickListener{
        void onItemClicked(ViewHolder viewHolder);
    }


    public RecyclerViewAdapter(Context context, List<User> users, ClickListener listener) {
        this.context = context;
        this.users = users;
        this.myListener = listener;
    }


    @NonNull
    @Override
    //where we recycle views using LayoutInflater
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);
        return new ViewHolder(view);

    }


    @Override
    //Where we bind views with data
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String contact = users.get(position).name;
        User user = users.get(position);
        holder.imageUrl = user.imageUri;

        String userInfo = String.format("Name: %s  \nEmail: %s \nAddress: %s", user.name, user.email, user.address.street);
        holder.info = userInfo;
        holder.name.setText(contact);
        Picasso.with(context).load(holder.imageUrl).resize(250, 250)
                .centerCrop()
                .into(holder.image);
        holder.image.setVisibility(View.VISIBLE);


    }

    public void setUsers(List<User> users){
        this.users = users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public ImageView image;
        public String info;
        public String imageUrl = "";
        public int Position;
        public Fragment fragment;
        //public int position;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);

            name = itemView.findViewById(R.id.textView);
            image = itemView.findViewById(R.id.imageView);
            //fragment = new UserFragment();

        }

        @Override
        public void onClick(View view) {
            Position = getAdapterPosition();

            myListener.onItemClicked(this);
        }

        //@Override //best place to implement onclick is in viewholder
        //public void onClick(View view) {

//            Intent intent = new Intent(context, UserList.class);
//            intent.putExtra("UserInfo",info);
//
//            intent.putExtra("imageUrl", imageUrl);
//
//            intent.putExtra("source", "Recycler");
//            intent.putExtra("Position", Position);
//            context.startActivity(intent);
            //RecyclerViewActivity.Callbacks.getFragment(this);


        }
    }

