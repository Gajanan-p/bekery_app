package com.ssinfomate.bakeryapp.webservices.saleheaddetails;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GrSaleDetailRequest {

    @SerializedName("PARTYID")
    @Expose
    private String partyid;
    @SerializedName("OperationType")
    @Expose
    private String operationType;
    @SerializedName("ObjGrSaleDetailIn")
    @Expose
    private List<ObjGrSaleDetailIn> objGrSaleDetailIn = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public GrSaleDetailRequest() {
    }

    /**
     *
     * @param objGrSaleDetailIn
     * @param operationType
     * @param partyid
     */
    public GrSaleDetailRequest(String partyid, String operationType, List<ObjGrSaleDetailIn> objGrSaleDetailIn) {
        super();
        this.partyid = partyid;
        this.operationType = operationType;
        this.objGrSaleDetailIn = objGrSaleDetailIn;
    }

    public String getPartyid() {
        return partyid;
    }

    public void setPartyid(String partyid) {
        this.partyid = partyid;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public List<ObjGrSaleDetailIn> getObjGrSaleDetailIn() {
        return objGrSaleDetailIn;
    }

    public void setObjGrSaleDetailIn(List<ObjGrSaleDetailIn> objGrSaleDetailIn) {
        this.objGrSaleDetailIn = objGrSaleDetailIn;
    }

}