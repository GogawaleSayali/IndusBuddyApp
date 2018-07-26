package com.dogratech.indusbuddyapp.main.models;


import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by amolr on 22/5/18.
 */

public class EventItemModel {
    @SerializedName("corporate")
    private String corporate ;
    @SerializedName("corporateId")
    private String corporateId ;
    @SerializedName("ehrId")
    private String ehrId ;
    @SerializedName("eventId")
    private String eventId ;
    @SerializedName("eventTypename")
    private String eventTypename ;
    @SerializedName("eventTitle")
    private String eventTitle ;
    @SerializedName("paymentType")
    private String paymentType ;
    @SerializedName("percentage")
    private String percentage ;
    @SerializedName("price")
    private String price ;
    @SerializedName("percentageAmount")
    private String percentageAmount ;
    @SerializedName("serviceName")
    private String serviceName ;
    @SerializedName("vFrom")
    private String vFrom ;
    @SerializedName("vTo")
    private String vTo ;
    @SerializedName("status")
    private String status ;
    @SerializedName("center")
    private String center ;
    @SerializedName("slot")
    private ArrayList<SlotsModel> slot ;
    @SerializedName("days")
    private String days;

    public EventItemModel() {
    }

    public String getCorporate() {
        return corporate;
    }

    public void setCorporate(String corporate) {
        this.corporate = corporate;
    }

    public String getCorporateId() {
        return corporateId;
    }

    public void setCorporateId(String corporateId) {
        this.corporateId = corporateId;
    }

    public String getEhrId() {
        return ehrId;
    }

    public void setEhrId(String ehrId) {
        this.ehrId = ehrId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventTypename() {
        return eventTypename;
    }

    public void setEventTypename(String eventTypename) {
        this.eventTypename = eventTypename;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPercentageAmount() {
        return percentageAmount;
    }

    public void setPercentageAmount(String percentageAmount) {
        this.percentageAmount = percentageAmount;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getvFrom() {

       return vFrom;
    }

    public void setvFrom(String vFrom) {
        this.vFrom = vFrom;
    }

    public String getvTo() {
       return vTo;
    }

    public void setvTo(String vTo) {
        this.vTo = vTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<SlotsModel> getSlot() {
        return slot;
    }

    public void setSlot(ArrayList<SlotsModel> slot) {
        this.slot = slot;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getFormatedFrom(){
        DateFormat inFormat = new SimpleDateFormat( "MM/dd/yyyy hh:mm:ss aa");
        DateFormat outFormat = new SimpleDateFormat( "dd MMM yyyy");
        String dddd = "";
        try {
            Date dateStr = inFormat.parse(getvFrom());
            dddd = outFormat.format(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (!dddd.equalsIgnoreCase("")){
            return dddd;
        }
        return getvFrom();
    }

    public String getFormatedTo(){
        DateFormat inFormat = new SimpleDateFormat( "MM/dd/yyyy hh:mm:ss aa");
        DateFormat outFormat = new SimpleDateFormat( "dd MMM yyyy");
        String dddd = "";
        try {
            Date dateStr = inFormat.parse(getvTo());
            dddd = outFormat.format(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (!dddd.equalsIgnoreCase("")){
            return dddd;
        }
        return getvTo();
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
