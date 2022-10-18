package com.example.chatsapp.Models;

public class MassagesModel {
    String Id,Massage;
    Long MsgTime;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getMassage() {
        return Massage;
    }

    public void setMassage(String massage) {
        Massage = massage;
    }

    public Long getMsgTime() {
        return MsgTime;
    }

    public void setMsgTime(Long msgTime) {
        MsgTime = msgTime;
    }

    public MassagesModel(String id, String massage) {
        Id = id;
        Massage = massage;
    }

    public MassagesModel(String id, String massage, Long msgTime) {
        Id = id;
        Massage = massage;
        MsgTime = msgTime;
    }
    public MassagesModel(){

    }
}
