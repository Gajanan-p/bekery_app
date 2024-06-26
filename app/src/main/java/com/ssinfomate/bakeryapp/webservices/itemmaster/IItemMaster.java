package com.ssinfomate.bakeryapp.webservices.itemmaster;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface IItemMaster {

    @Headers("Content-Type: application/json")
    @GET("api/Values/ItemMasterList")
    Call<ArrayList<ItemMasterListModel>> getItemMasterList();
}
