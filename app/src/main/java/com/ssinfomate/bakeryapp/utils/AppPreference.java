package com.ssinfomate.bakeryapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.ssinfomate.bakeryapp.webservices.itemmaster.ItemMasterListModel;
import com.ssinfomate.bakeryapp.webservices.user.TempChangePass;
import com.ssinfomate.bakeryapp.webservices.user.UserModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppPreference {

    private static String TAG="appDataPreferences";
    public static String appPreferences="appPreferences";
    public static String appLoginPreferences="appLoginPreferences";
    public static String appLoginPreferencesKey="appLoginPreferencesKey";
    public static String appTempDetailPreferencesKey="appTempDetailPreferencesKey";
    public static String appItemPreferencesKey="appItemPreferencesKey";
    public static SharedPreferences sharedLoginPreferences;
    public static SharedPreferences sharedAppPreferences;
    public static SharedPreferences getAppSharedPreferences(Context context){
        if(sharedAppPreferences==null){
            sharedAppPreferences=context.getSharedPreferences(AppPreference.appPreferences
                    , Context.MODE_PRIVATE);
        }
        return sharedAppPreferences;
    }

    public static SharedPreferences getLoginSharedPreferences(Context context){
        if(sharedLoginPreferences==null){
            sharedLoginPreferences=context.getSharedPreferences(AppPreference.appLoginPreferences, Context.MODE_PRIVATE);
        }
        return sharedLoginPreferences;
    }

    public static void setLoginDataPreferences(Context context, UserModel userModel){
        Gson gson = new Gson();
        String data=gson.toJson(userModel);
        Log.i(TAG,"Set user model data");
        Log.i(TAG,data);
        SharedPreferences.Editor editor=getLoginSharedPreferences(context).edit();
        editor.putString(appLoginPreferencesKey,data);
        editor.commit();
    }

    public static UserModel getLoginDataPreferences(Context context){
        String data=getLoginSharedPreferences(context).getString(appLoginPreferencesKey,"");
        Gson gson = new Gson();
        UserModel userModel= gson.fromJson(data,UserModel.class);
        Log.i(TAG,"get user model data");
        Log.i(TAG,data);
        return userModel;
    }

    public static boolean clearLoginDataPreferences(Context context){
        SharedPreferences.Editor editor=getLoginSharedPreferences(context).edit();
        editor.clear();
        editor.apply();
        editor.commit();
        Log.i(TAG,"Clear login Preferences");
        return true;
    }
    public static void setItemDataPreferences(Context context, ItemMasterListModel itemMasterListModel){
        Gson gson = new Gson();
        String data=gson.toJson(itemMasterListModel);
        Log.i(TAG,"Set Item data");
        Log.i(TAG,data);
        SharedPreferences.Editor editor=getAppSharedPreferences(context).edit();
        editor.putString(appItemPreferencesKey,data);
        editor.commit();
    }

    public static ItemMasterListModel getItemDataPreferences(Context context){
        String data=getAppSharedPreferences(context).getString(appItemPreferencesKey,"");
        Gson gson = new Gson();
        ItemMasterListModel itemMasterListModel= gson.fromJson(data,ItemMasterListModel.class);
        Log.i(TAG,"get Item data "+ data);
        Log.i(TAG,data);
        return itemMasterListModel;
    }
    public static List<TempChangePass> getTempDataPreferences(Context context){
        String data=getAppSharedPreferences(context).getString(appTempDetailPreferencesKey,"");
        Gson gson = new Gson();
        List newReducedStockModels=  Arrays.asList( new Gson().fromJson(data, TempChangePass[].class));
        Log.i(TAG,"get Insert Sale Data "+ data);

        return  newReducedStockModels;
    }

    public static void setTempDataPreferences(Context context,
                                                ArrayList<TempChangePass> tempChangePasses){
        Gson gson = new Gson();
        String data=gson.toJson(tempChangePasses);
        Log.i(TAG,"Set Insert Sale Data ");
        Log.i(TAG,data);
        SharedPreferences.Editor editor=getAppSharedPreferences(context).edit();
        editor.putString(appTempDetailPreferencesKey,data);
        editor.commit();
    }
}
