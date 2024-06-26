package com.ssinfomate.bakeryapp.ui.returnreport;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPartyReturnDetailsRequest {

    @SerializedName("PARTYID")
    @Expose
    private String partyid;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetPartyReturnDetailsRequest() {
    }

    /**
     *
     * @param partyid
     */
    public GetPartyReturnDetailsRequest(String partyid) {
        super();
        this.partyid = partyid;
    }

    public String getPartyid() {
        return partyid;
    }

    public void setPartyid(String partyid) {
        this.partyid = partyid;
    }

}