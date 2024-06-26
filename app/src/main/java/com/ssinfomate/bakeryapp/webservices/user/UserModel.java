package com.ssinfomate.bakeryapp.webservices.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("ACNTCODE")
    @Expose
    private String acntcode;
    @SerializedName("ACNTDESC")
    @Expose
    private String acntdesc;
    @SerializedName("GSTNO")
    @Expose
    private String gstno;
    @SerializedName("ACNTPASS")
    @Expose
    private String acntpass;
    @SerializedName("ACMASTERID")
    @Expose
    private String acmasterid;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("MSG")
    @Expose
    private Object msg;

    /**
     * No args constructor for use in serialization
     *
     */
    public UserModel() {
    }

    /**
     *
     * @param msg
     * @param acntcode
     * @param acntdesc
     * @param gstno
     * @param acmasterid
     * @param acntpass
     */
    public UserModel(String acntcode, String acntdesc, String gstno, String acntpass,
                     String acmasterid,String status, Object msg) {
        super();
        this.acntcode = acntcode;
        this.acntdesc = acntdesc;
        this.gstno = gstno;
        this.acntpass = acntpass;
        this.acmasterid = acmasterid;
        this.status = status;
        this.msg = msg;
    }

    public String getAcntcode() {
        return acntcode;
    }

    public void setAcntcode(String acntcode) {
        this.acntcode = acntcode;
    }

    public String getAcntdesc() {
        return acntdesc;
    }

    public void setAcntdesc(String acntdesc) {
        this.acntdesc = acntdesc;
    }

    public String getGstno() {
        return gstno;
    }

    public void setGstno(String gstno) {
        this.gstno = gstno;
    }

    public String getAcntpass() {
        return acntpass;
    }

    public void setAcntpass(String acntpass) {
        this.acntpass = acntpass;
    }

    public String getAcmasterid() {
        return acmasterid;
    }

    public void setAcmasterid(String acmasterid) {
        this.acmasterid = acmasterid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }
}
