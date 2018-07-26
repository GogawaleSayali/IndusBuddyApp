package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshaya on 13/3/18.
 */

public class Model_Item_RenewalStatus {
    /*
    *  "RenewalStatus": {
        "MemberId": 499573,
        "MemberName": "JADHAV AKSHAY PUNDLIK",
        "ExpiryDate": "02-DEC-2018"
    }
    * */

    @SerializedName("MemberId")
    private String MemberId ;

    @SerializedName("MemberName")
    private String MemberName ;

    @SerializedName("ExpiryDate")
    private String ExpiryDate ;

    public Model_Item_RenewalStatus() {
    }

    public Model_Item_RenewalStatus(String memberId, String memberName, String expiryDate) {
        MemberId = memberId;
        MemberName = memberName;
        ExpiryDate = expiryDate;
    }

    public String getMemberId() {
        return MemberId;
    }

    public void setMemberId(String memberId) {
        MemberId = memberId;
    }

    public String getMemberName() {
        return MemberName;
    }

    public void setMemberName(String memberName) {
        MemberName = memberName;
    }

    public String getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        ExpiryDate = expiryDate;
    }
}
