package com.homeautogroup.meru.api;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.homeautogroup.meru.activities.SettingsActivity;

public class APIUrl {

    //private static final String BASE_URL = "http://79b6fa3d.ngrok.io/";
    //public static final String BASE_IMAGE = "http://ff657371.ngrok.io/";

    public static String getServerUrl(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        return sharedPreferences.getString(SettingsActivity.SERVER_URL,null);
    }
}
