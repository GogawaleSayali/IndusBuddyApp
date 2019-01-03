package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

public class Model_MainSubCategories
{
    @SerializedName("data")
    private Model_SubCategoriesList subcategoriesList;
    @SerializedName("status_code")
    private int status_code ;
    @SerializedName("error_code")
    private int error_code ;
    @SerializedName("msg")
    private String msg ;

    public Model_SubCategoriesList getSubcategoriesList() {
        return subcategoriesList;
    }

    public void setSubcategoriesList(Model_SubCategoriesList subcategoriesList) {
        this.subcategoriesList = subcategoriesList;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }



}
