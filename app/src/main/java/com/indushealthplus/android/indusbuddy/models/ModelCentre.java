package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by amolr on 23/2/18.
 */

public class ModelCentre {
    @SerializedName("cityCode")
    private int cityCode;
    @SerializedName("cityName")
    private String cityName;
    @SerializedName("stateCode")
    private int stateCode;
    @SerializedName("stateName")
    private String stateName;
    @SerializedName("centreCode")
    private int centreCode;
    @SerializedName("centreName")
    private String centreName;
    @SerializedName("address")
    private String address;
    @SerializedName("centerContactNumber")
    private String centerContactNumber;
    @SerializedName("centerContactName")
    private String centerContactName;
    @SerializedName("latitude")
    private Double latitude;
    @SerializedName("longitude")
    private Double longitude;
    @SerializedName("logo")
    private String logoUrl;
    @SerializedName("hospital_image")
    private String hospital_image;
    @SerializedName("health_friend")
    private String health_friend;
    @SerializedName("PHP")
    private String PHP;
    @SerializedName("ESS")
    private String ESS;
    @SerializedName("SUP")
    private String SUP;
    @SerializedName("OPTIMA")
    private String OPTIMA;
    @SerializedName("URL")
    private String URL;
    @SerializedName("HOSPITAL_DESCRIPTION")
    private String HOSPITAL_DESCRIPTION;
    @SerializedName("SERVICES")
    private String SERVICES;
    @SerializedName("PRIMA")
    private String PRIMA;

    public ModelCentre() {
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getCentreCode() {
        return centreCode;
    }

    public void setCentreCode(int centreCode) {
        this.centreCode = centreCode;
    }

    public String getCentreName() {
        return centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCenterContactNumber() {
        return centerContactNumber;
    }

    public void setCenterContactNumber(String centerContactNumber) {
        this.centerContactNumber = centerContactNumber;
    }

    public String getCenterContactName() {
        return centerContactName;
    }

    public void setCenterContactName(String centerContactName) {
        this.centerContactName = centerContactName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getHospital_image() {
        return hospital_image;
    }

    public void setHospital_image(String hospital_image) {
        this.hospital_image = hospital_image;
    }

    public String getHealth_friend() {
        return health_friend;
    }

    public void setHealth_friend(String health_friend) {
        this.health_friend = health_friend;
    }

    public String getPHP() {
        return PHP;
    }

    public void setPHP(String PHP) {
        this.PHP = PHP;
    }

    public String getESS() {
        return ESS;
    }

    public void setESS(String ESS) {
        this.ESS = ESS;
    }

    public String getSUP() {
        return SUP;
    }

    public void setSUP(String SUP) {
        this.SUP = SUP;
    }

    public String getOPTIMA() {
        return OPTIMA;
    }

    public void setOPTIMA(String OPTIMA) {
        this.OPTIMA = OPTIMA;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getHOSPITAL_DESCRIPTION() {
        return HOSPITAL_DESCRIPTION;
    }

    public void setHOSPITAL_DESCRIPTION(String HOSPITAL_DESCRIPTION) {
        this.HOSPITAL_DESCRIPTION = HOSPITAL_DESCRIPTION;
    }

    public String getSERVICES() {
        return SERVICES;
    }

    public void setSERVICES(String SERVICES) {
        this.SERVICES = SERVICES;
    }

    public String getPRIMA() {
        return PRIMA;
    }

    public void setPRIMA(String PRIMA) {
        this.PRIMA = PRIMA;
    }
}
