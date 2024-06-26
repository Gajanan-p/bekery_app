package com.ssinfomate.bakeryapp.webservices.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TempChangePass {
    @SerializedName("USERNAME")
    @Expose
    private String username;
    @SerializedName("PASSWORD")
    @Expose
    private String password;

    public TempChangePass() {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
