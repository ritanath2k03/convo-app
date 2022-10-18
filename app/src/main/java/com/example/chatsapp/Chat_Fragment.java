package com.example.chatsapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.chatsapp.Adaptes.UserAdapter;
import com.example.chatsapp.Models.Users;
import com.example.chatsapp.databinding.FragmentChatBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Chat_Fragment extends Fragment {

FragmentChatBinding binding;
FirebaseDatabase database=FirebaseDatabase.getInstance();
DatabaseReference reference=database.getReference();
UserAdapter adapter;
ArrayList<Users> list;

    public Chat_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentChatBinding.inflate(inflater, container, false);

        binding.ChatRecyclerView.setHasFixedSize(true);

        list=new ArrayList<>();
        adapter=new UserAdapter(list,getContext());
        binding.ChatRecyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        binding.ChatRecyclerView.setLayoutManager(layoutManager);

        reference.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              list.clear();

                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Users model=dataSnapshot.getValue(Users.class);
                    model.setUserId(dataSnapshot.getKey());
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
       });

        return binding.getRoot();
    }
}