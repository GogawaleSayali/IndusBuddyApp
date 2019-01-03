package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

public class ModelItemSubCategories
{
     /*"subCategoryId": 4,
             "subCategory": "Surprising Benefits of Food",
             "addedBy": null,
             "addedOn": null,
             "modifyBy": null,
             "modifyOn": null,
             "isActive": null,
             "categoryMaster":
             {
            "categoryId": 1,
            "category": "Diet & Nutrition",
            "addedBy": null,
            "addedOn": null,
            "modifyBy": null,
            "modifyOn": null,
            "isActive": null,
            "subCategories": []}*/
     @SerializedName("subCategoryId")
     private String subCategoryId ;

    @SerializedName("subCategory")
    private String subCategory;

    @SerializedName("addedBy")
    private String addedBy ;

    @SerializedName("addedOn")
    private String addedOn ;

    @SerializedName("modifyBy")
    private String modifyBy ;

    @SerializedName("modifyOn")
    private String modifyOn ;

    @SerializedName("isActive")
    private String isActive ;

    @SerializedName("categoryMaster")
    private Model_CategoryMaster model_categoryMasterClass;


    public Model_CategoryMaster getModel_categoryMasterClass() {
        return model_categoryMasterClass;
    }

    public void setModel_categoryMasterClass(Model_CategoryMaster model_categoryMasterClass) {
        this.model_categoryMasterClass = model_categoryMasterClass;
    }

    public String getAddedOn() {

        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

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





}
