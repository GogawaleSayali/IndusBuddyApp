package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by amolr on 19/4/18.
 */

public class MainMenuModel {
    @SerializedName("Menu_ID")
    private String menuId;
    @SerializedName("Menu_Name")
    private String menuName;
    @SerializedName("SubMenu")
    private ArrayList<SubMenuList> subMenuLists;

    public MainMenuModel(String menuId, String menuName, ArrayList<SubMenuList> subMenuLists) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.subMenuLists = subMenuLists;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public ArrayList<SubMenuList> getSubMenuLists() {
        return subMenuLists;
    }

    public void setSubMenuLists(ArrayList<SubMenuList> subMenuLists) {
        this.subMenuLists = subMenuLists;
    }
}
