package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshaya on 14/3/18.
 */

public class Model_Item_Beneficiary {

    /*
    * {
            "SrNo": 1,
            "Relation": "Self",
            "FullName": "MHASE VILAS MARUTI",
            "Age": 44,
            "DOB": "21-May-1973",
            "Gender": "M",
            "Email": "",
            "Address1": "SAI RESIDENCY,",
            "Address2": "OPP. PAISA FUND,",
            "Address3": "TALEGAON STATION, CHAKAN ROAD,",
            "ResidentialTel": "",
            "ContanctNo": "M",
            "SchemeNo": 2,
            "City": "PUNE",
            "FirstName": "VILAS",
            "MiddleName": "MARUTI",
            "LastName": "MHASE"
        }
    *
    * */

    @SerializedName("SrNo")
    private int SrNo ;
    @SerializedName("Relation")
    private String Relation ;
    @SerializedName("FullName")
    private String FullName ;
    @SerializedName("Age")
    private int Age ;
    @SerializedName("DOB")
    private String DOB ;
    @SerializedName("Gender")
    private String Gender ;
    @SerializedName("Email")
    private String Email ;
    @SerializedName("Address1")
    private String Address1 ;
    @SerializedName("Address2")
    private String Address2 ;
    @SerializedName("Address3")
    private String Address3 ;
    @SerializedName("ResidentialTel")
    private String ResidentialTel ;
    @SerializedName("ContanctNo")
    private String ContanctNo ;
    @SerializedName("SchemeNo")
    private int SchemeNo ;
    @SerializedName("City")
    private String City ;
    @SerializedName("FirstName")
    private String FirstName ;
    @SerializedName("MiddleName")
    private String MiddleName ;
    @SerializedName("LastName")
    private String LastName ;

    public Model_Item_Beneficiary() {
    }

    public int getSrNo() {
        return SrNo;
    }

    public void setSrNo(int srNo) {
        SrNo = srNo;
    }

    public String getRelation() {
        return Relation;
    }

    public void setRelation(String relation) {
        Relation = relation;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public void setAddress2(String address2) {
        Address2 = address2;
    }

    public String getAddress3() {
        return Address3;
    }

    public void setAddress3(String address3) {
        Address3 = address3;
    }

    public String getResidentialTel() {
        return ResidentialTel;
    }

    public void setResidentialTel(String residentialTel) {
        ResidentialTel = residentialTel;
    }

    public String getContanctNo() {
        return ContanctNo;
    }

    public void setContanctNo(String contanctNo) {
        ContanctNo = contanctNo;
    }

    public int getSchemeNo() {
        return SchemeNo;
    }

    public void setSchemeNo(int schemeNo) {
        SchemeNo = schemeNo;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
