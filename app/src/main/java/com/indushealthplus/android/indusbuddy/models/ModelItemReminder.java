package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshaya on 14/3/18.
 */

public class ModelItemReminder {
    /*
    * {
        "ehrReminderMasterId": "null",
        "remindMeFor": "",
        "reminderId": "19",
        "recurrencePattern": "03/06/2018 12:57 PM",
        "clientName": "for",
        "groupId": "20",
        "emailId": "a@b.c",
        "isActive": "Y",
        "duration": "",
        "eventTitle": "Reminder",
        "doctorName": "Doctor Name",
        "endEvent": "03/02/2018 1:00 AM",
        "startEvent": "03/02/2018 12:00 AM",
        "typeExercise": "",
        "doctorDateTime": "03/06/2018 12:57 PM",
        "testName": "",
        "clientId": "15000",
        "medicineDateTime": "",
        "reminderDateTime": "03/06/2018 1:57PM",
        "durationInMinutes": "",
        "mobileNo": "7875545421",
        "medicineName": "",
        "medicineDose": "",
        "centreName": "",
        "location": "",
        "category": "consultation"
      },
    *
    * */

    @SerializedName("ehrReminderMasterId")
    private String ehrReminderMasterId ;

    @SerializedName("remindMeFor")
    private String remindMeFor ;

    @SerializedName("reminderId")
    private String reminderId ;

    @SerializedName("recurrencePattern")
    private String recurrencePattern ;

    @SerializedName("clientName")
    private String clientName ;

    @SerializedName("groupId")
    private String groupId ;

    @SerializedName("emailId")
    private String emailId ;

    @SerializedName("isActive")
    private String isActive ;

    @SerializedName("duration")
    private String duration ;

    @SerializedName("eventTitle")
    private String eventTitle ;

    @SerializedName("doctorName")
    private String doctorName ;

    @SerializedName("endEvent")
    private String endEvent ;

    @SerializedName("startEvent")
    private String startEvent ;

    @SerializedName("typeExercise")
    private String typeExercise ;

    @SerializedName("doctorDateTime")
    private String doctorDateTime ;

    @SerializedName("testName")
    private String testName ;

    @SerializedName("clientId")
    private String clientId ;

    @SerializedName("medicineDateTime")
    private String medicineDateTime ;

    @SerializedName("reminderDateTime")
    private String reminderDateTime ;

    @SerializedName("durationInMinutes")
    private String durationInMinutes ;

    @SerializedName("mobileNo")
    private String mobileNo ;

    @SerializedName("medicineName")
    private String medicineName ;

    @SerializedName("medicineDose")
    private String medicineDose ;

    @SerializedName("centreName")
    private String centreName ;

    @SerializedName("location")
    private String location ;

    @SerializedName("category")
    private String category ;

    public ModelItemReminder(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public ModelItemReminder() {
    }

    public String getEhrReminderMasterId() {
        return ehrReminderMasterId;
    }

    public void setEhrReminderMasterId(String ehrReminderMasterId) {
        this.ehrReminderMasterId = ehrReminderMasterId;
    }

    public String getRemindMeFor() {
        return remindMeFor;
    }

    public void setRemindMeFor(String remindMeFor) {
        this.remindMeFor = remindMeFor;
    }

    public String getReminderId() {
        return reminderId;
    }

    public void setReminderId(String reminderId) {
        this.reminderId = reminderId;
    }

    public String getRecurrencePattern() {
        return recurrencePattern;
    }

    public void setRecurrencePattern(String recurrencePattern) {
        this.recurrencePattern = recurrencePattern;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getEndEvent() {
        return endEvent;
    }

    public void setEndEvent(String endEvent) {
        this.endEvent = endEvent;
    }

    public String getStartEvent() {
        return startEvent;
    }

    public void setStartEvent(String startEvent) {
        this.startEvent = startEvent;
    }

    public String getTypeExercise() {
        return typeExercise;
    }

    public void setTypeExercise(String typeExercise) {
        this.typeExercise = typeExercise;
    }

    public String getDoctorDateTime() {
        return doctorDateTime;
    }

    public void setDoctorDateTime(String doctorDateTime) {
        this.doctorDateTime = doctorDateTime;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getMedicineDateTime() {
        return medicineDateTime;
    }

    public void setMedicineDateTime(String medicineDateTime) {
        this.medicineDateTime = medicineDateTime;
    }

    public String getReminderDateTime() {
        return reminderDateTime;
    }

    public void setReminderDateTime(String reminderDateTime) {
        this.reminderDateTime = reminderDateTime;
    }

    public String getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(String durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineDose() {
        return medicineDose;
    }

    public void setMedicineDose(String medicineDose) {
        this.medicineDose = medicineDose;
    }

    public String getCentreName() {
        return centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
