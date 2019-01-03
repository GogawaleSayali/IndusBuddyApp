package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Model_CategoriesList {


    @SerializedName("categories")
    private ArrayList<ModelItemCategories> categories ;

    public ArrayList<ModelItemCategories> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<ModelItemCategories> categories) {
        this.categories = categories;
    }

}
