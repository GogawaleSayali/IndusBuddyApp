package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by amolr on 19/4/18.
 */

public class SubMenuList {
    @SerializedName("Submenu_Name")
    private String subMenuName;

    public SubMenuList(String subMenuName) {
        this.subMenuName = subMenuName;
    }

    public String getSubMenuName() {
        return subMenuName;
    }

    public void setSubMenuName(String subMenuName) {
        this.subMenuName = subMenuName;
    }
}
