package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by amolr on 19/4/18.
 */

public class MainMenuResponceModel {
    @SerializedName("status_code")
    private int statusCode;
    @SerializedName("msg")
    private String msg;
    @SerializedName("menu")
    private ArrayList<MainMenuModel> mainMenuList;

    public MainMenuResponceModel(int statusCode, String msg, ArrayList<MainMenuModel> mainMenuList) {
        this.statusCode = statusCode;
        this.msg = msg;
        this.mainMenuList = mainMenuList;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<MainMenuModel> getMainMenuList() {
        return mainMenuList;
    }

    public void setMainMenuList(ArrayList<MainMenuModel> mainMenuList) {
        this.mainMenuList = mainMenuList;
    }
}
