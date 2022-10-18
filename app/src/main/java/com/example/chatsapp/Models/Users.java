package com.example.chatsapp.Models;

public class Users {
    String profilepic,username,mail,password,userId,lastmassage;

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastmassage() {
        return lastmassage;
    }

    public void setLastmassage(String lastmassage) {
        this.lastmassage = lastmassage;
    }

    public Users() {
    }



    public Users(String profilepic, String username, String mail, String password, String userId, String lastmassage) {
        this.profilepic = profilepic;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.userId = userId;
        this.lastmassage = lastmassage;
    }

    public Users(String username, String mail, String password) {
        this.username = username;
        this.mail = mail;
        this.password = password;
    }


}
