package com.ssinfomate.bakeryapp.webservices.saleheaddetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertGrSaleHeadDetail {
    @SerializedName("MSG")
    @Expose
    private String msg;

    /**
     * No args constructor for use in serialization
     *
     */
    public InsertGrSaleHeadDetail() {
    }

    /**
     *
     * @param msg
     */
    public InsertGrSaleHeadDetail(String msg) {
        super();
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}