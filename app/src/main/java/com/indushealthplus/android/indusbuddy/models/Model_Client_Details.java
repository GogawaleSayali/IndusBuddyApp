package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;
/**
 * Created by akshaya on 19/2/18.
 */

public class Model_Client_Details{
    /*
    * clientDetails": {
                "lastName": "Pawar",
                "otpSendAt": "2018-02-19 13:29:42",
                "emailId": "",
                "otp": "199741",
                "firstName": "Mr. Sakharam",
                "mobileNumber": "9403345344",
                "clientId": "15000"
                "userType": "C"
            }
    * */

    @SerializedName("lastName")
    private String lastName ;
    @SerializedName("otpSendAt")
    private String otpSendAt ;
    @SerializedName("emailId")
    private String emailId ;
    @SerializedName("otp")
    private String otp ;
    @SerializedName("dob")
    private String dob ;
    @SerializedName("gender")
    private String gender ;
    @SerializedName("firstName")
    private String firstName ;
    @SerializedName("mobileNumber")
    private String mobileNumber ;
    @SerializedName("clientId")
    private String clientId ;
    @SerializedName("userType")
    private String userType ;
    @SerializedName("profilePicture")
    private String profilePicture ;

    public Model_Client_Details() {
    }

    public Model_Client_Details(String lastName, String otpSendAt, String emailId,
                                String otp, String firstName,
                                String mobileNumber, String clientId,String userType) {
        this.lastName = lastName;
        this.otpSendAt = otpSendAt;
        this.emailId = emailId;
        this.otp = otp;
        this.firstName = firstName;
        this.mobileNumber = mobileNumber;
        this.clientId = clientId;
        this.userType = userType;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOtpSendAt() {
        return otpSendAt;
    }

    public void setOtpSendAt(String otpSendAt) {
        this.otpSendAt = otpSendAt;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
