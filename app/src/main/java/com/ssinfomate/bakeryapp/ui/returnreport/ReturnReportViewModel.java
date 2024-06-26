package com.ssinfomate.bakeryapp.ui.returnreport;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReturnReportViewModel {

    @SerializedName("GRSALEDTID")
    @Expose
    private String grsaledtid;
    @SerializedName("TRSNO")
    @Expose
    private String trsno;
    @SerializedName("TRDATE")
    @Expose
    private String trdate;
    @SerializedName("SRNO")
    @Expose
    private String srno;
    @SerializedName("ITEMID")
    @Expose
    private String itemid;
    @SerializedName("QUANTITY")
    @Expose
    private String quantity;
    @SerializedName("CLOUD_DOWNLOAD")
    @Expose
    private String cloudDownload;
    @SerializedName("MSG")
    @Expose
    private Object msg;

    /**
     * No args constructor for use in serialization
     *
     */
    public ReturnReportViewModel() {
    }

    /**
     *
     * @param msg
     * @param itemid
     * @param quantity
     * @param cloudDownload
     * @param srno
     * @param trdate
     * @param grsaledtid
     * @param trsno
     */
    public ReturnReportViewModel(String grsaledtid, String trsno, String trdate, String srno, String itemid, String quantity, String cloudDownload, Object msg) {
        super();
        this.grsaledtid = grsaledtid;
        this.trsno = trsno;
        this.trdate = trdate;
        this.srno = srno;
        this.itemid = itemid;
        this.quantity = quantity;
        this.cloudDownload = cloudDownload;
        this.msg = msg;
    }

    public String getGrsaledtid() {
        return grsaledtid;
    }

    public void setGrsaledtid(String grsaledtid) {
        this.grsaledtid = grsaledtid;
    }

    public String getTrsno() {
        return trsno;
    }

    public void setTrsno(String trsno) {
        this.trsno = trsno;
    }

    public String getTrdate() {
        return trdate;
    }

    public void setTrdate(String trdate) {
        this.trdate = trdate;
    }

    public String getSrno() {
        return srno;
    }

    public void setSrno(String srno) {
        this.srno = srno;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCloudDownload() {
        return cloudDownload;
    }

    public void setCloudDownload(String cloudDownload) {
        this.cloudDownload = cloudDownload;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

}