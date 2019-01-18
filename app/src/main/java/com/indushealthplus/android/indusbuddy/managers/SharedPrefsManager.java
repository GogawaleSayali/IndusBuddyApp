package com.indushealthplus.android.indusbuddy.managers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import com.indushealthplus.android.indusbuddy.helper.Constatnts;
import com.indushealthplus.android.indusbuddy.R;

/**
 * Created by amolr on 12/3/18.
 */

public class SharedPrefsManager {
    private static SharedPrefsManager sharedPrefsManager;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor sharedPrefsEditor;

    public static SharedPrefsManager getSharedInstance(Context context){
        if (sharedPrefsManager == null){
            sharedPrefsManager = new SharedPrefsManager();
            sharedPreferences = context.getSharedPreferences(context.getResources().
                    getString(R.string.IndusBuddySharedPrefs),Activity.MODE_PRIVATE);
        }
        return sharedPrefsManager;
    }

    public String getData(String key) {
        return  sharedPreferences.getString(key,Constatnts.NODATA);
    }

    public void setData(String key,String data) {
        sharedPrefsEditor = getEditor();
        sharedPrefsEditor.putString(key,data);
        sharedPrefsEditor.commit();
    }

    public void clearData() {
        sharedPrefsEditor = getEditor();
        sharedPrefsEditor.clear();
        sharedPrefsEditor.commit();
    }

    private SharedPreferences.Editor getEditor(){
        return sharedPreferences.edit();
    }

}
