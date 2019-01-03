package com.dogratech.indusbuddyapp.main.models;

import java.util.ArrayList;

public class ViewRecordSupportModel {
    private String count;
    private String name;
    private ArrayList<Model_Item_Report> RecordsList;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Model_Item_Report> getRecordsList() {
        return RecordsList;
    }

    public void setRecordsList(ArrayList<Model_Item_Report> recordsList) {
        RecordsList = recordsList;
    }
}
