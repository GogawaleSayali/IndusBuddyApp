package com.indushealthplus.android.indusbuddy.uitility;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.util.Locale;

/**
 * Created by amolr on 5/3/18.
 */

public class DeviceUtility {
    public static int convertDpToPx(Context context , int dp){
        return Math.round(dp*(context.getResources().getDisplayMetrics().xdpi/ DisplayMetrics.DENSITY_DEFAULT));
    }

    public static String getDefualtLanguage(){
        return Locale.getDefault().getDisplayLanguage();
    }

    public static void hideKeyBord(Activity context){
    /*    try {
            if (context != null) {
                InputMethodManager inputMethodManager =
                        (InputMethodManager) context.getSystemService(
                                Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(
                        context.getCurrentFocus().getWindowToken(), 0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }*/
        context . getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        context . getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }
}