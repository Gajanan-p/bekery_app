package com.ssinfomate.bakeryapp.webservices.saleheaddetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestDeleteGrSaleHeadDetail {

    @SerializedName("TRSNO")
    @Expose
    private Integer trsno;

    /**
     * No args constructor for use in serialization
     *
     */
    public RequestDeleteGrSaleHeadDetail() {
    }

    /**
     *
     * @param trsno
     */
    public RequestDeleteGrSaleHeadDetail(Integer trsno) {
        super();
        this.trsno = trsno;
    }

    public Integer getTrsno() {
        return trsno;
    }

    public void setTrsno(Integer trsno) {
        this.trsno = trsno;
    }


}
