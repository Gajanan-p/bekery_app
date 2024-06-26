package com.ssinfomate.bakeryapp.ui.changepass;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePasswordModel {

    @SerializedName("ACNTCODE")
    @Expose
    private Object acntcode;
    @SerializedName("ACNTPASS")
    @Expose
    private Object acntpass;
    @SerializedName("newACNTPASS")
    @Expose
    private Object newACNTPASS;
    @SerializedName("MSG")
    @Expose
    private String msg;

    /**
     * No args constructor for use in serialization
     *
     */
    public ChangePasswordModel() {
    }

    /**
     *
     * @param msg
     * @param newACNTPASS
     * @param acntcode
     * @param acntpass
     */
    public ChangePasswordModel(Object acntcode, Object acntpass, Object newACNTPASS, String msg) {
        super();
        this.acntcode = acntcode;
        this.acntpass = acntpass;
        this.newACNTPASS = newACNTPASS;
        this.msg = msg;
    }

    public Object getAcntcode() {
        return acntcode;
    }

    public void setAcntcode(Object acntcode) {
        this.acntcode = acntcode;
    }

    public Object getAcntpass() {
        return acntpass;
    }

    public void setAcntpass(Object acntpass) {
        this.acntpass = acntpass;
    }

    public Object getNewACNTPASS() {
        return newACNTPASS;
    }

    public void setNewACNTPASS(Object newACNTPASS) {
        this.newACNTPASS = newACNTPASS;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}