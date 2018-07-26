package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by akshaya on 14/3/18.
 */

public class Model_ReminderList {
    @SerializedName("reminders")
    private ArrayList<ModelItemReminder> itemReminders ;

    public Model_ReminderList() {
    }

    public ArrayList<ModelItemReminder> getItemReminders() {
        return itemReminders;
    }

    public void setItemReminders(ArrayList<ModelItemReminder> itemReminders) {
        this.itemReminders = itemReminders;
    }
}
