package com.ssinfomate.bakeryapp.ui.changepass;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePasswordRequest {

    @SerializedName("ACNTCODE")
    @Expose
    private String acntcode;
    @SerializedName("ACNTPASS")
    @Expose
    private String acntpass;
    @SerializedName("newACNTPASS")
    @Expose
    private String newACNTPASS;
    @SerializedName("MSG")
    @Expose
    private String msg;

    /**
     * No args constructor for use in serialization
     *
     */
    public ChangePasswordRequest() {
    }

    /**
     *
     * @param msg
     * @param newACNTPASS
     * @param acntcode
     * @param acntpass
     */
    public ChangePasswordRequest(String acntcode, String acntpass, String newACNTPASS, String msg) {
        super();
        this.acntcode = acntcode;
        this.acntpass = acntpass;
        this.newACNTPASS = newACNTPASS;
        this.msg = msg;
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

    public String getNewACNTPASS() {
        return newACNTPASS;
    }

    public void setNewACNTPASS(String newACNTPASS) {
        this.newACNTPASS = newACNTPASS;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}