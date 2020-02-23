package com.example.amir.shetu.model;

import com.google.gson.annotations.SerializedName;

public class UserLogin {
    @SerializedName("userName")
    String userName;

    @SerializedName("password")
    String password;


    String Token;

    public UserLogin(String userName, String password, String Token) {

        this.userName = userName;

        this.password = password;

        this.Token=Token;


    }

    @SerializedName("userName")
    public String getUsername() {
        return userName;
    }

    @SerializedName("password")
    public String getPassword() {
        return password;
    }


    public String getToken() {
        return Token;
    }
}
