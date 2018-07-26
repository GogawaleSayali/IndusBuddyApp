package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshaya on 19/2/18.
 */

public class ModelUserDetails {
    /*
            * {
        "firstName":"Mr. Sakharam",
        "lastName":"Pawar",
        "gender":"M",
        "dob":"2010-02-16",
        "mobileNumber":"919403345344",
        "emailId":""
        }
    * */

    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("gender")
    private String gender;
    @SerializedName("dob")
    private String dob;
    @SerializedName("mobileNumber")
    private String mobileNumber;
    @SerializedName("emailId")
    private String emailId;

    public ModelUserDetails() {
    }

    public ModelUserDetails(String firstName, String lastName, String gender,
                            String dob, String mobileNumber, String emailId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
