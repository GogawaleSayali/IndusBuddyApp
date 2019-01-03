package com.dogratech.indusbuddyapp.main.retrofit;

/**
 * Created by akshaya on 25/5/17.
 */

public class ApiUrl {
    /*Buy package urls*/
    public static final String CD_USER_URL
            = "http://www.indusites.com/member-login-indusite.html";
    public static final String PHARMA_AND_TELESALES_USER_URL
            = "https://www.indushealthplus.com/health-checkup-packages.html";

    /*Base urls*/
    public static final String Base_URL_INDUS = "http://115.113.153.202/IndusHealth/api/";
    public static final String Base_URL_MOBILE = "http://115.113.153.199/MobileAppAPI/MobileApp/";
    public static final String Base_URL_ICONNECT = "http://iconnect.indushealthplus.com/";

   // public static final String Base_URL_WELLNESS = "http://103.68.8.196:8080/api/";
    public static final String Base_URL_WELLNESS = "http://61.8.141.2:8090/api/";

    /*Url end points*/
    public static final String verifyOTP = "verifyOTP/";
    public static final String getClientByEHRId = "getClientByEHRId/";
    public static final String resendOTP = "resendOTP/";
    public static final String verifyDetails = "verifyDetails/";
    public static final String getJoiningKITDLstatus = "GetJoiningKITDLstatus/";
    public static final String getRenewalstatus = "GetRenewalstatus/";
    public static final String getAppointmentDetails = "GetAppointmentDetails/";
    public static final String trackParameterByEHRId = "trackParameterByEHRId/";
    public static final String getSelfUploadReportByEHRId = "getSelfUploadReportByEHRId/";
    public static final String getAnalysisCommentByEHRId = "getAnalysisCommentByEHRId/";
    public static final String getReminderByEHRId = "getReminderByEHRId/";
    public static final String getHRAByEHRId = "getHRAByEHRId/";
    public static final String postAppointment = "PostAppointment/";
    public static final String saveHRADetails = "saveHRADetails/";
    public static final String getPaymentstatus = "GetPaymentstatus/";
    public static final String uploadSelfReport = "uploadSelfReport/";
    public static final String uploadProfilePicture = "uploadProfilePicture/";
    public static final String GetPackageDetails = "GetAvailedPackages/";
    public static final String GetPendingPackageDetails = "GetPendingPackages/";
    public static final String GetMemberDetails = "GetMemberDetails/";
    public static final String getMenu = "menu/";
    public static final String event = "event/";
    public static final String eventHistory = "eventHistory/";
    public static final String contentPreview = "contentsPreview";
    public static final String getCentreList = "IMAAPIS/CentresListDetail.html?";
    public static final String getHomePageNews = "SUtils/GetHomePageNews";
    public static final String getArchivesSectionNews = "SUtils/GetArchiveSectionNews?DOMAIN=";
    public static final String isEvent = "IsEvent/";
    public static final String DOMAIN_IHP = "IHP";
    public static final String GET_POSTPONE_REASON = "GetListPostpondedReason";
    public static final String saveReminder = "saveReminder/";
    public static final String GET_CATEGORIES = "getCategories";
    public static final String GET_SUBCATEGORIES = "getSubCategories";


    //public static final String dailyActivities     = "/activities/date/%s.json";
    //public static final String GetPackageDetails   = "GetPackageDetails/";
}
