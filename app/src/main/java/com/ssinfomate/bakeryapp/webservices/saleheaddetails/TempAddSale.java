package com.ssinfomate.bakeryapp.webservices.saleheaddetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TempAddSale {
    @SerializedName("ITEMID")
    @Expose
    private String itemid;
    @SerializedName("ITEMIDName")
    @Expose
    private String itemname;
    @SerializedName("QUANTITY")
    @Expose
    private Integer quantity;
    @SerializedName("DATE")
    @Expose
    private String date;
    @SerializedName("ITEMCODE")
    @Expose
    private String itemcode;
    public TempAddSale() {
        this.itemname = itemname;
        this.itemid = itemid;
        this.quantity = quantity;
        this.date = date;
        this.itemcode = itemcode;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }
}
