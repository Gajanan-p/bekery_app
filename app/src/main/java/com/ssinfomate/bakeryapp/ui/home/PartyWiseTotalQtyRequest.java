package com.ssinfomate.bakeryapp.ui.home;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PartyWiseTotalQtyRequest {

    @SerializedName("PARTYID")
    @Expose
    private String partyid;

    /**
     * No args constructor for use in serialization
     *
     */
    public PartyWiseTotalQtyRequest() {
    }

    /**
     *
     * @param partyid
     */
    public PartyWiseTotalQtyRequest(String partyid) {
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