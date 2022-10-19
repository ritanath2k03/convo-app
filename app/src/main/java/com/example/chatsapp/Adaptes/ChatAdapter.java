package com.example.chatsapp.Adaptes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatsapp.Models.MassagesModel;
import com.example.chatsapp.R;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{

    ArrayList<MassagesModel> massagesModels;
    Context context;
int SENDER_VIEW_TYPE=1;
    int RECEINER_VIEW_TYPE=2;

    public ChatAdapter(ArrayList<MassagesModel> massagesModels, Context context) {
        this.massagesModels = massagesModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if(viewType==SENDER_VIEW_TYPE){
           View view = LayoutInflater.from(context).inflate(R.layout.sender_layout,parent,false);
           return new SenderViewHolder(view);
       }
       else {
           View view = LayoutInflater.from(context).inflate(R.layout.receiver_layout,parent,false);
           return new ReceiverViewHolder(view);
       }

    }

    @Override
    public int getItemViewType(int position) {
        if(massagesModels.get(position).getId().equals(FirebaseAuth.getInstance().getUid()))
        {
            return SENDER_VIEW_TYPE;
        }
        else
        {
            return RECEINER_VIEW_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MassagesModel massagesModel=massagesModels.get(position);
        if(holder.getClass()==SenderViewHolder.class){
            ((SenderViewHolder)holder).senderMsg.setText(massagesModel.getMassage());
            ((SenderViewHolder)holder).senderTime.setText(newtime(massagesModel.getMsgTime()));
        }
        else {
            ((ReceiverViewHolder)holder).receiverMsg.setText(massagesModel.getMassage());
            ((ReceiverViewHolder)holder).receiverTime.setText(newtime(massagesModel.getMsgTime()));
        }


    }

    private String newtime(Long msgTime) {
        SimpleDateFormat sfd = new SimpleDateFormat("HH:mm:ss");
       String newtime = sfd.format(msgTime);
        return newtime;
    }


    @Override
    public int getItemCount() {
        return massagesModels.size();
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder{
TextView receiverMsg,receiverTime;
        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);

            receiverMsg=itemView.findViewById(R.id.Receiver_text);
            receiverTime=itemView.findViewById(R.id.Receiver_time);

        }
    }
    public class SenderViewHolder extends RecyclerView.ViewHolder{
        TextView senderMsg,senderTime;
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            senderMsg=itemView.findViewById(R.id.Sender_text);
            senderTime=itemView.findViewById(R.id.Sender_time);
        }
    }
}
