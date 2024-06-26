package com.ssinfomate.bakeryapp.webservices.acmaster;

import com.ssinfomate.bakeryapp.ui.home.HomeViewModel;
import com.ssinfomate.bakeryapp.ui.home.PartyWiseTotalQtyRequest;
import com.ssinfomate.bakeryapp.ui.returnreport.GetPartyReturnDetailsRequest;
import com.ssinfomate.bakeryapp.ui.returnreport.ReturnReportViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IAcMaster {
    @Headers("Content-Type: application/json")
    @POST("api/Values/PartyWiseTotalQty")
    Call<HomeViewModel> getValue(@Body PartyWiseTotalQtyRequest request);

    @Headers("Content-Type: application/json")
    @POST("api/Values/GetPartyReturnDetails")
    Call<ArrayList<ReturnReportViewModel>> getReportList(@Body GetPartyReturnDetailsRequest request);

}
