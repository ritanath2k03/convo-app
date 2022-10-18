package com.example.chatsapp.Adaptes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatsapp.Chat_Detail_Activity;
import com.example.chatsapp.Models.Users;
import com.example.chatsapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{
    ArrayList<Users> list;
   Context context;

    public UserAdapter(ArrayList<Users> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).
                inflate(R.layout.each_chat,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     Users users=list.get(position);
        Picasso.get().load(users.getProfilepic()).
                placeholder(R.drawable.ic_person).into(holder.image);
        holder.username.setText(users.getUsername());
        holder.lastmMassage.setText(users.getLastmassage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Chat_Detail_Activity.class);
                intent.putExtra("userId",users.getUserId());
                intent.putExtra("userProfilepic",users.getProfilepic());
                intent.putExtra("userName",users.getUsername());
                intent.putExtra("value",2);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
ImageView image;
TextView username,lastmMassage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.UserProfileImage);
            username=itemView.findViewById(R.id.UserName);
            lastmMassage=itemView.findViewById(R.id.UserLastMassage);
        }
    }
}
