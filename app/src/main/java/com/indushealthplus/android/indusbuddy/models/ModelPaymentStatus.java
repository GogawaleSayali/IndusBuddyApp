package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by amolr on 20/3/18.
 */

public class ModelPaymentStatus {
    @SerializedName("Type")
    private String type;
    @SerializedName("ReceiptNo")
    private int receiptNo;
    @SerializedName("ReceiptDate")
    private String receiptDate;
    @SerializedName("Scheme")
    private String scheme;
    @SerializedName("AddOnPackage")
    private String addOnPackage;

    @SerializedName("AddOnTest")
    private String addOnTest;

    @SerializedName("Amount")
    private int amount;
    @SerializedName("Paymode")
    private String payMode;
    @SerializedName("Status")
    private String Status;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(int receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getAddOnPackage() {
        return addOnPackage;
    }

    public void setAddOnPackage(String addOnPackage) {
        this.addOnPackage = addOnPackage;
    }

    public String getAddOnTest() {
        return addOnTest;
    }

    public void setAddOnTest(String addOnTest) {
        this.addOnTest = addOnTest;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
