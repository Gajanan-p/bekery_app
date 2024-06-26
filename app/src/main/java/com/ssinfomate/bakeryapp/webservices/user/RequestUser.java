package com.ssinfomate.bakeryapp.webservices.user;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestUser {

    @SerializedName("ACNTCODE")
    @Expose
    private String acntcode;
    @SerializedName("ACNTPASS")
    @Expose
    private String acntpass;

    /**
     * No args constructor for use in serialization
     *
     */
    public RequestUser() {
    }

    /**
     *
     * @param acntcode
     * @param acntpass
     */
    public RequestUser(String acntcode, String acntpass) {
        super();
        this.acntcode = acntcode;
        this.acntpass = acntpass;
    }

    public String getAcntcode() {
        return acntcode;
    }

    public void setAcntcode(String acntcode) {
        this.acntcode = acntcode;
    }

    public String getAcntpass() {
        return acntpass;
    }

    public void setAcntpass(String acntpass) {
        this.acntpass = acntpass;
    }

}