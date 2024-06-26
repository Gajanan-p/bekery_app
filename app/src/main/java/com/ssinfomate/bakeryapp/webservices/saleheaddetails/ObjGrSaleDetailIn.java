package com.ssinfomate.bakeryapp.webservices.saleheaddetails;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ObjGrSaleDetailIn {

    @SerializedName("ITEMID")
    @Expose
    private String itemid;
    @SerializedName("QUANTITY")
    @Expose
    private Integer quantity;

    /**
     * No args constructor for use in serialization
     *
     */
    public ObjGrSaleDetailIn() {
    }

    /**
     *
     * @param itemid
     * @param quantity
     */
    public ObjGrSaleDetailIn(String itemid, Integer quantity) {
        super();
        this.itemid = itemid;
        this.quantity = quantity;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}