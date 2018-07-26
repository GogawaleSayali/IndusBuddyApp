package com.dogratech.indusbuddyapp.main.models;

import java.util.ArrayList;

public class ModelRecords {
    private String recordType;
    ArrayList<ModelRedordDetails> records = new ArrayList<>();

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getCount() {
        return ""+records.size();
    }

    public ArrayList<ModelRedordDetails> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<ModelRedordDetails> records) {
        this.records = records;
    }
}
