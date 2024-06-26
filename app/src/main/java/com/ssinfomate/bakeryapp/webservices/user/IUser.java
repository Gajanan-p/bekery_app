package com.ssinfomate.bakeryapp.webservices.user;

import com.ssinfomate.bakeryapp.ui.changepass.ChangePasswordModel;
import com.ssinfomate.bakeryapp.ui.changepass.ChangePasswordRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IUser {
    @Headers("Content-Type: application/json")
    @POST("api/Values/AcMasterList")
    Call<ArrayList<UserModel>> getUserModel(@Body RequestUser requestUser);

    @Headers("Content-Type: application/json")
    @POST("api/Values/changepassword")
    Call<ArrayList<ChangePasswordModel>> getChangePassword(@Body ChangePasswordRequest changePasswordRequest);


}
