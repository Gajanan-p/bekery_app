package com.ssinfomate.bakeryapp.webservices.saleheaddetails;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IGrSaleHeadDetail {
    @Headers("Content-Type: application/json")
    @POST("api/Values/DeleteGrSaleHeadDetail")
    Call<DeleteGrSaleHeadDetailModel> setDeleteGrSaleHeadDetail(@Body RequestDeleteGrSaleHeadDetail requestDeleteGrSaleHeadDetail);

    @Headers("Content-Type: application/json")
    @POST("api/Values/InsertGrSaleHeadDetail")
    Call<InsertGrSaleHeadDetail> setInsertSaleHeadDetail(@Body JsonObject insertGrSaleHeadDetail);
}
