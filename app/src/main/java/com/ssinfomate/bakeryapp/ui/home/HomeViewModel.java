package com.ssinfomate.bakeryapp.ui.home;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeViewModel {

    @SerializedName("Qty")
    @Expose
    private String qty;
    @SerializedName("MSG")
    @Expose
    private Object msg;

    /**
     * No args constructor for use in serialization
     *
     */
    public HomeViewModel() {
    }

    /**
     *
     * @param msg
     * @param qty
     */
    public HomeViewModel(String qty, Object msg) {
        super();
        this.qty = qty;
        this.msg = msg;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

}