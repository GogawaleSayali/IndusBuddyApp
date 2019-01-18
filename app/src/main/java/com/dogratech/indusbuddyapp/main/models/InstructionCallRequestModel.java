package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sayali.gogawale on 1/11/2019.
 */

public class InstructionCallRequestModel {

    @SerializedName("gender")
    private String gender;

    @SerializedName("packageName")
    private String packageName;


    public  InstructionCallRequestModel(String gender, String packageName) {
        this.gender = gender;
        this.packageName = packageName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String toString() {
        return "{" +
                "gender=" + gender  +
                ", packageName=" + packageName +
                "}";
    }
}

