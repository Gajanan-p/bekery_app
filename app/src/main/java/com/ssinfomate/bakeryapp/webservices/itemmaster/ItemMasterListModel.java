package com.ssinfomate.bakeryapp.webservices.itemmaster;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemMasterListModel {

    @SerializedName("ITEMCODE")
    @Expose
    private String itemcode;
    @SerializedName("ITEMDESC")
    @Expose
    private String itemdesc;
    @SerializedName("UNIT")
    @Expose
    private String unit;
    @SerializedName("RATE")
    @Expose
    private Integer rate;
    @SerializedName("ITEMMASTERID")
    @Expose
    private String itemmasterid;
    @SerializedName("ACTIVE")
    @Expose
    private String active;
    @SerializedName("MSG")
    @Expose
    private Object msg;

    /**
     * No args constructor for use in serialization
     *
     */
    public ItemMasterListModel() {
    }

    /**
     *
     * @param msg
     * @param itemdesc
     * @param unit
     * @param rate
     * @param itemcode
     * @param itemmasterid
     * @param active
     */
    public ItemMasterListModel(String itemcode, String itemdesc, String unit, Integer rate, String itemmasterid, String active, Object msg) {
        super();
        this.itemcode = itemcode;
        this.itemdesc = itemdesc;
        this.unit = unit;
        this.rate = rate;
        this.itemmasterid = itemmasterid;
        this.active = active;
        this.msg = msg;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getItemmasterid() {
        return itemmasterid;
    }

    public void setItemmasterid(String itemmasterid) {
        this.itemmasterid = itemmasterid;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

}