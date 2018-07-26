package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by akshaya on 13/3/18.
 */

public class Model_Response_Appointment_Details {
    /*
    * {
        "StatusCode": 0,
        "AppointmentDetails": {
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
        },
        "ErrorCode": 200
}
    * */

    @SerializedName("StatusCode")
    private String StatusCode ;

    @SerializedName("AppointmentDetails")
    private ArrayList<Model_Item_Appointment> appointment ;
    @SerializedName("ErrorCode")
    private String ErrorCode ;

    public Model_Response_Appointment_Details() {
    }

    public Model_Response_Appointment_Details(String statusCode, ArrayList<Model_Item_Appointment>  appointment, String errorCode) {
        StatusCode = statusCode;
        this.appointment = appointment;
        ErrorCode = errorCode;
    }

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String statusCode) {
        StatusCode = statusCode;
    }

    public ArrayList<Model_Item_Appointment>  getAppointment() {
        return appointment;
    }

    public void setAppointment(ArrayList<Model_Item_Appointment>  appointment) {
        this.appointment = appointment;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }
}
