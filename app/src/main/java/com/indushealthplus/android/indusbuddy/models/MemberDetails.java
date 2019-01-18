package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

public class MemberDetails {
    @SerializedName("MemberId")
    private int memberId;
    @SerializedName("MemberName")
    private String MemberName;
    @SerializedName("Gender")
    private String Gender;
    @SerializedName("Email")
    private String email;
    @SerializedName("Phone")
    private String phone;
    @SerializedName("IVP")
    private String ivp;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return MemberName;
    }

    public void setMemberName(String memberName) {
        MemberName = memberName;
    }

    public String getGender() {
        if (Gender.equalsIgnoreCase("M")){
            return  "Male";
        }else if (Gender.equalsIgnoreCase("F")){
            return  "Female";
        }else{
            return Gender;
        }
    }

    public void setGender(String gender) {
       Gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIvp() {
        return ivp;
    }

    public void setIvp(String ivp) {
        this.ivp = ivp;
    }
}
