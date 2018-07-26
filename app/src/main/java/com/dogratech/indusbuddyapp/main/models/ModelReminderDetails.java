package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

public class ModelReminderDetails
{
   /* { "emailId":"a@b.c",
            "mobileNo":"7875545421",
            "category":"consultation",
            "eventTitle":"title",
            "reminderDateTime":"03/14/2018 12:17 PM",
            "eventStartDate":"03/01/2018 12:00 AM",
            "eventEndDate":"03/01/2018 1:00 AM",
            "doctorName":"AmolSa",
            "doctorDateTime":"03/14/2018 12:17 PM",
            "medicineName":"",
            "medicineDose":"",
            "medicineDateTime":"",
            "typeOfExercise":"",
            "durationInMinutes":"",
            "testName":"",
            "centreName":"",
            "remindMeFor":"",
            "location":"",
            "duration":"",
            "clientName":"reminder for",
            "clientId":15000,
            "reminders":["03/14/2018 1:17 PM",
            "03/13/2018 12:17 PM"],
        "ehrReminderMasterId":"1",
            "dailyRepeat":1,
            "weeklyRepeat":1,
            "monthlyRepeat":1,
            "repeatBy":"1",
            "yearlyRepeat":1,
            "ends":"1",
            "afterText":4,
            "onText":"",
            "weeklyDays":[],
        "recurrencePattern":"1" }*/

    @SerializedName("emailId")
    private String emailId;

    @SerializedName("mobileNo")
    private String mobileNo;

    @SerializedName("eventTitle")
    private String eventTitle;

    @SerializedName("reminderDateTime")
    private String reminderDateTime;

    @SerializedName("eventStartDate")
    private String eventStartDate;

    @SerializedName("eventEndDate")
    private String eventEndDate;

    @SerializedName("doctorDateTime")
    private String doctorDateTime;

    @SerializedName("medicineName")
    private String medicineName;

    @SerializedName("medicineDose")
    private String medicineDose;

    @SerializedName("medicineDateTime")
    private String medicineDateTime;

    @SerializedName("typeOfExercise")
    private String typeOfExercise;

    @SerializedName("durationInMinutes")
    private String durationInMinutes;

    @SerializedName("testName")
    private String testName;

    @SerializedName("centreName")
    private String centreName;

    @SerializedName("remindMeFor")
    private String remindMeFor;

    @SerializedName("location")
    private String location;

    @SerializedName("duration")
    private String duration;

    @SerializedName("clientName")
    private String clientName;

    @SerializedName("clientId")
    private String clientId;

    @SerializedName("reminders")
    private String reminders;

    @SerializedName("ehrReminderMasterId")
    private String ehrReminderMasterId;

    @SerializedName("dailyRepeat")
    private String dailyRepeat;

    @SerializedName("weeklyRepeat")
    private String weeklyRepeat;

    @SerializedName("monthlyRepeat")
    private String monthlyRepeat;

    @SerializedName("repeatBy")
    private String repeatBy;

    @SerializedName("yearlyRepeat")
    private String yearlyRepeat;

    @SerializedName("ends")
    private String ends;

    @SerializedName("afterText")
    private String afterText;

    @SerializedName("onText")
    private String onText;

    @SerializedName("weeklyDays")
    private String weeklyDays;

    @SerializedName("recurrencePattern")
    private String recurrencePattern;

    public ModelReminderDetails(String emailId, String mobileNo, String eventTitle,
                                String reminderDateTime, String eventStartDate,
                                String eventEndDate, String doctorDateTime,
                                String medicineName, String medicineDose,
                                String medicineDateTime, String typeOfExercise,
                                String durationInMinutes, String testName,
                                String centreName, String remindMeFor, String location,
                                String duration, String clientName, String clientId,
                                String reminders, String ehrReminderMasterId, String dailyRepeat,
                                String weeklyRepeat, String monthlyRepeat, String repeatBy,
                                String yearlyRepeat, String ends, String afterText,
                                String onText, String weeklyDays, String recurrencePattern)
    {
        this.emailId = emailId;
        this.mobileNo = mobileNo;
        this.eventTitle = eventTitle;
        this.reminderDateTime = reminderDateTime;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.doctorDateTime = doctorDateTime;
        this.medicineName = medicineName;
        this.medicineDose = medicineDose;
        this.medicineDateTime = medicineDateTime;
        this.typeOfExercise = typeOfExercise;
        this.durationInMinutes = durationInMinutes;
        this.testName = testName;
        this.centreName = centreName;
        this.remindMeFor = remindMeFor;
        this.location = location;
        this.duration = duration;
        this.clientName = clientName;
        this.clientId = clientId;
        this.reminders = reminders;
        this.ehrReminderMasterId = ehrReminderMasterId;
        this.dailyRepeat = dailyRepeat;
        this.weeklyRepeat = weeklyRepeat;
        this.monthlyRepeat = monthlyRepeat;
        this.repeatBy = repeatBy;
        this.yearlyRepeat = yearlyRepeat;
        this.ends = ends;
        this.afterText = afterText;
        this.onText = onText;
        this.weeklyDays = weeklyDays;
        this.recurrencePattern = recurrencePattern;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getReminderDateTime() {
        return reminderDateTime;
    }

    public void setReminderDateTime(String reminderDateTime) {
        this.reminderDateTime = reminderDateTime;
    }

    public String getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(String eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public String getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(String eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public String getDoctorDateTime() {
        return doctorDateTime;
    }

    public void setDoctorDateTime(String doctorDateTime) {
        this.doctorDateTime = doctorDateTime;
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

    public String getMedicineDateTime() {
        return medicineDateTime;
    }

    public void setMedicineDateTime(String medicineDateTime) {
        this.medicineDateTime = medicineDateTime;
    }

    public String getTypeOfExercise() {
        return typeOfExercise;
    }

    public void setTypeOfExercise(String typeOfExercise) {
        this.typeOfExercise = typeOfExercise;
    }

    public String getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(String durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getCentreName() {
        return centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName;
    }

    public String getRemindMeFor() {
        return remindMeFor;
    }

    public void setRemindMeFor(String remindMeFor) {
        this.remindMeFor = remindMeFor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getReminders() {
        return reminders;
    }

    public void setReminders(String reminders) {
        this.reminders = reminders;
    }

    public String getEhrReminderMasterId() {
        return ehrReminderMasterId;
    }

    public void setEhrReminderMasterId(String ehrReminderMasterId) {
        this.ehrReminderMasterId = ehrReminderMasterId;
    }

    public String getDailyRepeat() {
        return dailyRepeat;
    }

    public void setDailyRepeat(String dailyRepeat) {
        this.dailyRepeat = dailyRepeat;
    }

    public String getWeeklyRepeat() {
        return weeklyRepeat;
    }

    public void setWeeklyRepeat(String weeklyRepeat) {
        this.weeklyRepeat = weeklyRepeat;
    }

    public String getMonthlyRepeat() {
        return monthlyRepeat;
    }

    public void setMonthlyRepeat(String monthlyRepeat) {
        this.monthlyRepeat = monthlyRepeat;
    }

    public String getRepeatBy() {
        return repeatBy;
    }

    public void setRepeatBy(String repeatBy) {
        this.repeatBy = repeatBy;
    }

    public String getYearlyRepeat() {
        return yearlyRepeat;
    }

    public void setYearlyRepeat(String yearlyRepeat) {
        this.yearlyRepeat = yearlyRepeat;
    }

    public String getEnds() {
        return ends;
    }

    public void setEnds(String ends) {
        this.ends = ends;
    }

    public String getAfterText() {
        return afterText;
    }

    public void setAfterText(String afterText) {
        this.afterText = afterText;
    }

    public String getOnText() {
        return onText;
    }

    public void setOnText(String onText) {
        this.onText = onText;
    }

    public String getWeeklyDays() {
        return weeklyDays;
    }

    public void setWeeklyDays(String weeklyDays) {
        this.weeklyDays = weeklyDays;
    }

    public String getRecurrencePattern() {
        return recurrencePattern;
    }

    public void setRecurrencePattern(String recurrencePattern) {
        this.recurrencePattern = recurrencePattern;
    }
}
