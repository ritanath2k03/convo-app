package com.example.chatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.chatsapp.Adaptes.ChatAdapter;
import com.example.chatsapp.Adaptes.UserAdapter;
import com.example.chatsapp.Models.MassagesModel;
import com.example.chatsapp.databinding.ActivityChatDetailBinding;
import com.example.chatsapp.databinding.FragmentChatBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class Chat_Detail_Activity extends AppCompatActivity {

ActivityChatDetailBinding binding;
FirebaseDatabase database;
FirebaseAuth auth;
FragmentChatBinding fragmentChatBinding;
    int value=0;
    int previousvalue=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChatDetailBinding.inflate(getLayoutInflater());
        fragmentChatBinding=FragmentChatBinding.inflate(getLayoutInflater());
        setContentView(fragmentChatBinding.getRoot());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
database=FirebaseDatabase.getInstance();
auth=FirebaseAuth.getInstance();
final String senderId=auth.getUid();

String receiveId=getIntent().getStringExtra("userId");
String userName=getIntent().getStringExtra("userName");
String userProfilepic=getIntent().getStringExtra("userProfilepic");

binding.ChatDetailedUserName.setText(userName);
        Picasso.get().load(userProfilepic).placeholder(R.drawable.ic_person).into(binding.UserProfileImage);
   binding.ChatDetailedBackButton.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           Intent intent=new Intent(Chat_Detail_Activity.this,MainActivity.class);
           startActivity(intent);

       }
   });
   final ArrayList<MassagesModel> massagesModels=new ArrayList<>();
   final ChatAdapter chatAdapter=new ChatAdapter(massagesModels,this);
   binding.ChatDetailedRecyclerView.setAdapter(chatAdapter);

        LinearLayoutManager LayoutManager=new LinearLayoutManager(this);
        binding.ChatDetailedRecyclerView.setLayoutManager(LayoutManager);
        final String senderRoom=senderId+receiveId;
        final String receiverRoom=receiveId+senderId;





        binding.Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              String Massage= binding.ChatDetailedText.getText().toString();
              final MassagesModel model=new MassagesModel(senderId,Massage);
              model.setMsgTime(new Date().getTime());
              binding.ChatDetailedText.setText("");
              database.getReference().child("Chats")
                      .child(senderRoom).push()
                      .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                          @Override
                          public void onSuccess(Void unused) {
                              database.getReference().child("Chats")
                                      .child(receiverRoom)
                                      .push()
                                      .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                          @Override
                                          public void onSuccess(Void unused) {

                                          }
                                      });
                          }
                      });
            }
        });
    }

    final int newgetvalue(){

        return  value;
    }

    @Override
    protected void onResume() {
        super.onResume();
        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        final String senderId=auth.getUid();
        String receiveId=getIntent().getStringExtra("userId");
        String userName=getIntent().getStringExtra("userName");
        String userProfilepic=getIntent().getStringExtra("userProfilepic");
        final ArrayList<MassagesModel> massagesModels=new ArrayList<>();
        final ChatAdapter chatAdapter=new ChatAdapter(massagesModels,this);
        binding.ChatDetailedRecyclerView.setAdapter(chatAdapter);

        LinearLayoutManager LayoutManager=new LinearLayoutManager(this);
        binding.ChatDetailedRecyclerView.setLayoutManager(LayoutManager);
        final String senderRoom=senderId+receiveId;
        final String receiverRoom=receiveId+senderId;
        database.getReference().child("Chats").child(senderRoom)
                .addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        massagesModels.clear();
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                            MassagesModel model=dataSnapshot.getValue(MassagesModel.class);
                            massagesModels.add(model);
                        }

                        value=chatAdapter.getItemCount();

                        Log.d("number", String.valueOf(value));
                        Log.d("prenumber", String.valueOf(previousvalue));
//                              if(value>previousvalue){
//                                  Toast.makeText(Chat_Detail_Activity.this, "Massage from "+userName, Toast.LENGTH_SHORT).show();
//                                  previousvalue= value;
//                              }
                        chatAdapter.notifyDataSetChanged();
                        binding.ChatDetailedRecyclerView.smoothScrollToPosition(binding.ChatDetailedRecyclerView.getAdapter().getItemCount());

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}