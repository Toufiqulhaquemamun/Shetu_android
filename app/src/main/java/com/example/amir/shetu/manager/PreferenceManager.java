package com.example.amir.shetu.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.amir.shetu.other.ApplicationContext;

public class PreferenceManager {

    private static String PREFERENCE_NAME="shetu";

    private static PreferenceManager instance=null;

    private static SharedPreferences preferences=null;

    public static String USER_ID ="id";

    public static String PRODUCT_ID ="productId";

    public static String COMMODITY_ID ="commodityId";

    public static String PREORDER_PRODUCT_ID ="preorderproductId";

    public static String PREORDER_REMAIN_QUANTITY ="preorderremainquantity";

    public static String COMODITY_POST_ID ="comoditypostid";

    public static String BID_ID ="bidId";

    public static String PREORDER_BID_ID ="preorderbidId";

    public static String TYPE ="type";

    public static String USER_NAME ="userName";

    public static String REMEMBER="remember";

    public static String PASSWORD ="password";

    public static String TOKEN ="token";

    public static String USER_IMAGE="user_image";

    public static String FLAG ="flag";

    public static String PREORDERFLAG ="preorderflag";

    public static String NMBROFBIDS="total_bids";

    public static String ACC_INFO="acc_info";

    public static String TOTAL_Agent_Share="agent_share";

    private PreferenceManager(){

        Context context=ApplicationContext.getContext();

        preferences=context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
    }


    public static PreferenceManager getInstance(){

       if(PreferenceManager.instance==null){

           return  new PreferenceManager();

       }else {
           return instance;
       }

    }

    public void setString(String name,String value){

        preferences.edit().putString(name,value).commit();
    }

    public void setBoolean(String name,boolean value){

        preferences.edit().putBoolean(name,value).commit();
    }

    public void setInt(String name,int value){
        preferences.edit().putInt(name,value).commit();
    }

    public String getString(String name){
        return preferences.getString(name,null);
    }

    public boolean getBoolean(String name){
        return preferences.getBoolean(name,false);
    }

    public int getInt(String name){
        return preferences.getInt(name,0);
    }


}
