package com.ssinfomate.bakeryapp.webservices;


import com.ssinfomate.bakeryapp.webservices.acmaster.IAcMaster;
import com.ssinfomate.bakeryapp.webservices.itemmaster.IItemMaster;
import com.ssinfomate.bakeryapp.webservices.saleheaddetails.IGrSaleHeadDetail;
import com.ssinfomate.bakeryapp.webservices.user.IUser;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Webservice {
    static Retrofit retrofit;
    public static String BASE_URL="http://bekary.testapp.co.in/";
//    http://bekary.testapp.co.in/

    static IUser iUser;
    static IItemMaster iItemMaster;
    static IGrSaleHeadDetail iGrSaleHeadDetail;
    static IAcMaster iAcMaster;
//    static ITotalDueInMAPl iTotalDueInMAPl;

    public static Retrofit getRetrofitClient() {

        if(retrofit==null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static IUser getUserData()
    {
        if (iUser==null)
        {
            iUser = getRetrofitClient().create(IUser.class);
        }
        return iUser;
    }
    public static IItemMaster getItemMasterData()
    {
        if (iItemMaster==null)
        {
            iItemMaster = getRetrofitClient().create(IItemMaster.class);
        }
        return iItemMaster;
    }
    public static IGrSaleHeadDetail setGrSaleHeadDetailData()
    {
        if (iGrSaleHeadDetail==null)
        {
            iGrSaleHeadDetail = getRetrofitClient().create(IGrSaleHeadDetail.class);
        }
        return iGrSaleHeadDetail;
    }
    public static IAcMaster getReport()
    {
        if (iAcMaster==null)
        {
            iAcMaster = getRetrofitClient().create(IAcMaster.class);
        }
        return iAcMaster;
    }
}
