package com.example.letmebreathe.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Account implements Serializable {

    @SerializedName("username")
    private String userName;
    private String password;
    private boolean isAdmin;


    public Account() {
    }

    public Account(String userName, String password, boolean isAdmin) {
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.isAdmin = false;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordToConfirm() { return password; }

    public void setPasswordToConfirm(String password)
    {

    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


}
