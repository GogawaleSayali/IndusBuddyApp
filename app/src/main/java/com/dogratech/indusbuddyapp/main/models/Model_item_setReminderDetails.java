package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Model_item_setReminderDetails
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @SerializedName("category")
    private String category;

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



    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @SerializedName("doctorName")
    private String doctorName;

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
    private int clientId;

    public ArrayList<String> getReminderlist() {
        return reminderlist;
    }

    public void setReminderlist(ArrayList<String> reminderlist) {
        this.reminderlist = reminderlist;
    }

    @SerializedName("reminders")
    private ArrayList<String> reminderlist;

    @SerializedName("ehrReminderMasterId")
    private String ehrReminderMasterId;

    @SerializedName("dailyRepeat")
    private int dailyRepeat;

    @SerializedName("weeklyRepeat")
    private int weeklyRepeat;

    @SerializedName("monthlyRepeat")
    private int monthlyRepeat;

    @SerializedName("repeatBy")
    private String repeatBy;

    @SerializedName("yearlyRepeat")
    private int yearlyRepeat;

    @SerializedName("ends")
    private String ends;

    @SerializedName("afterText")
    private int afterText;

    @SerializedName("onText")
    private String onText;



    public ArrayList<WeeklyDaysItem> getWeeklyDaysItems() {
        return weeklyDaysItems;
    }

    public void setWeeklyDaysItems(ArrayList<WeeklyDaysItem> weeklyDaysItems) {
        this.weeklyDaysItems = weeklyDaysItems;
    }

    @SerializedName("weeklyDays")
    private ArrayList<WeeklyDaysItem> weeklyDaysItems;

    @SerializedName("recurrencePattern")
    private String recurrencePattern;




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

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }



    public String getEhrReminderMasterId() {
        return ehrReminderMasterId;
    }

    public void setEhrReminderMasterId(String ehrReminderMasterId) {
        this.ehrReminderMasterId = ehrReminderMasterId;
    }

    public int getDailyRepeat() {
        return dailyRepeat;
    }

    public void setDailyRepeat(int dailyRepeat) {
        this.dailyRepeat = dailyRepeat;
    }

    public int getWeeklyRepeat() {
        return weeklyRepeat;
    }

    public void setWeeklyRepeat(int weeklyRepeat) {
        this.weeklyRepeat = weeklyRepeat;
    }

    public int getMonthlyRepeat() {
        return monthlyRepeat;
    }

    public void setMonthlyRepeat(int monthlyRepeat) {
        this.monthlyRepeat = monthlyRepeat;
    }

    public String getRepeatBy() {
        return repeatBy;
    }

    public void setRepeatBy(String repeatBy) {
        this.repeatBy = repeatBy;
    }

    public int getYearlyRepeat() {
        return yearlyRepeat;
    }

    public void setYearlyRepeat(int yearlyRepeat) {
        this.yearlyRepeat = yearlyRepeat;
    }

    public String getEnds() {
        return ends;
    }

    public void setEnds(String ends) {
        this.ends = ends;
    }

    public int getAfterText() {
        return afterText;
    }

    public void setAfterText(int afterText) {
        this.afterText = afterText;
    }

    public String getOnText() {
        return onText;
    }

    public void setOnText(String onText) {
        this.onText = onText;
    }


    public String getRecurrencePattern() {
        return recurrencePattern;
    }

    public void setRecurrencePattern(String recurrencePattern) {
        this.recurrencePattern = recurrencePattern;
    }
}
