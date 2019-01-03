package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Model_SubCategoriesList
{

    @SerializedName("subCategories")
    private ArrayList<ModelItemSubCategories> subcategoriesdetails ;

    public ArrayList<ModelItemSubCategories> getSubcategoriesdetails() {
        return subcategoriesdetails;
    }

    public void setSubcategoriesdetails(ArrayList<ModelItemSubCategories> subcategoriesdetails) {
        this.subcategoriesdetails = subcategoriesdetails;
    }
}
