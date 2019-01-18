package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelItemCategories
{
    /*   "categoryId": 1,
               "category": "Diet & Nutrition",
               "addedBy": null,
               "addedOn": null,
               "modifyBy": null,
               "modifyOn": null,
               "isActive": null,
               "subCategories": []*/

    @SerializedName("categoryId")
    private String categoryId ;

    @SerializedName("category")
    private String category;

    @SerializedName("addedBy")
    private String addedBy ;

    @SerializedName("modifyBy")
    private String modifyBy ;

    @SerializedName("modifyOn")
    private String modifyOn ;

    @SerializedName("isActive")
    private String isActive ;

    @SerializedName("subCategories")
    private ArrayList<ModelsubCategories> modelItemsubCategories ;


    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(String modifyOn) {
        this.modifyOn = modifyOn;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }





    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }




}
