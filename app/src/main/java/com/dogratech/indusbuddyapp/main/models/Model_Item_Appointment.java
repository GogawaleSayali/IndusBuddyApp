package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshaya on 13/3/18.
 */

public class Model_Item_Appointment {
    /*
    * "AppointmentDetails": {
            "AppointmentNo": 22576,
            "SaleType": "New Sale",
            "SchemeName": "Indus Essentia",
            "AddOnPackage": "",
            "AddOnTests": "",
            "BeneficiaryName": "- JADHAV VIMAL PUNDLIK -",
            "BeneficiaryRelation": "Self Mother",
            "AppointmentDate": "30-Mar-2017",
            "CCName": "Wockhardt Superspeciality Hospitals - Nagpur",
            "AppointmentStatus": "Confirmed"
        }
    * */

    @SerializedName("AppointmentNo")
    private String AppointmentNo ;
    @SerializedName("SaleType")
    private String SaleType ;
    @SerializedName("SchemeName")
    private String SchemeName ;
    @SerializedName("AddOnPackage")
    private String AddOnPackage ;
    @SerializedName("AddOnTests")
    private String AddOnTests ;
    @SerializedName("BeneficiaryName")
    private String BeneficiaryName ;
    @SerializedName("BeneficiaryRelation")
    private String BeneficiaryRelation ;
    @SerializedName("AppointmentDate")
    private String AppointmentDate ;
    @SerializedName("AppointmentYear")
    private String AppointmentYear ;
     @SerializedName("CCName")
    private String CCName ;
     @SerializedName("AppointmentStatus")
    private String AppointmentStatus ;
     @SerializedName("CityCode")
    private String cityCode ;

    public Model_Item_Appointment() {
    }

    public Model_Item_Appointment(String appointmentNo, String saleType, String schemeName,
                                  String addOnPackage, String addOnTests, String beneficiaryName,
                                  String beneficiaryRelation, String appointmentDate, String CCName,
                                  String appointmentStatus) {
        AppointmentNo = appointmentNo;
        SaleType = saleType;
        SchemeName = schemeName;
        AddOnPackage = addOnPackage;
        AddOnTests = addOnTests;
        BeneficiaryName = beneficiaryName;
        BeneficiaryRelation = beneficiaryRelation;
        AppointmentDate = appointmentDate;
        this.CCName = CCName;
        AppointmentStatus = appointmentStatus;
    }

    public String getAppointmentNo() {
        return AppointmentNo;
    }

    public void setAppointmentNo(String appointmentNo) {
        AppointmentNo = appointmentNo;
    }

    public String getSaleType() {
        return SaleType;
    }

    public void setSaleType(String saleType) {
        SaleType = saleType;
    }

    public String getSchemeName() {
        return SchemeName;
    }

    public void setSchemeName(String schemeName) {
        SchemeName = schemeName;
    }

    public String getAddOnPackage() {
        return AddOnPackage;
    }

    public void setAddOnPackage(String addOnPackage) {
        AddOnPackage = addOnPackage;
    }

    public String getAddOnTests() {
        return AddOnTests;
    }

    public void setAddOnTests(String addOnTests) {
        AddOnTests = addOnTests;
    }

    public String getBeneficiaryName() {
        return BeneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        BeneficiaryName = beneficiaryName;
    }

    public String getBeneficiaryRelation() {
        return BeneficiaryRelation;
    }

    public void setBeneficiaryRelation(String beneficiaryRelation) {
        BeneficiaryRelation = beneficiaryRelation;
    }

    public String getAppointmentDate() {
        return AppointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        AppointmentDate = appointmentDate;
    }

    public String getCCName() {
        return CCName;
    }

    public void setCCName(String CCName) {
        this.CCName = CCName;
    }

    public String getAppointmentStatus() {
        return AppointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        AppointmentStatus = appointmentStatus;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getAppointmentYear() {
        return AppointmentYear;
    }

    public void setAppointmentYear(String appointmentYear) {
        AppointmentYear = appointmentYear;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
