package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PackageDetailsModel {
    @SerializedName("Name")
    private String name;
    @SerializedName("Amount")
    private String amount;
    @SerializedName("PaymentStatus")
    private String paymentStatus;
    @SerializedName("PackageTests")
    private String packageTests = "";
    @SerializedName("AvaildedAddOnTest")
    private String availdedAddOnTest = "";
    @SerializedName("AvaildedAddOnPackage")
    private String availdedAddOnPackage = "";
    @SerializedName("AvaildedDiscountCouponPackage")
    private String availdedDiscountCouponPackage = "";
    @SerializedName("NotAvaildedAddOnTest")
    private String notAvaildedAddOnTest;
    @SerializedName("NotAvaildedAddOnPackage")
    private String notAvaildedAddOnPackage;
    @SerializedName("NotAvaildedDiscountCouponPackage")
    private String notAvaildedDiscountCouponPackage;
    @SerializedName("Type")
    private String type;
    @SerializedName("ReceiptNo")
    private String receiptNo;
    @SerializedName("ReceiptDate")
    private String receiptDate;
    @SerializedName("Paymode")
    private String paymode;
    @SerializedName("AvailableAddOnTest")
    private String availableAddOnTest;

    public String getAvailableDiscountCouponPackage() {
        return availableDiscountCouponPackage;
    }

    public void setAvailableDiscountCouponPackage(String availableDiscountCouponPackage) {
        this.availableDiscountCouponPackage = availableDiscountCouponPackage;
    }

    @SerializedName("AvailableDiscountCouponPackage")
    private String availableDiscountCouponPackage;


    public String getAvailableAddOnPackage() {
        return availableAddOnPackage;
    }

    public void setAvailableAddOnPackage(String availableAddOnPackage) {
        this.availableAddOnPackage = availableAddOnPackage;
    }

    @SerializedName("AvailableAddOnPackage")
    private String availableAddOnPackage;

    public String getAvailableAddOnTest() {
        return availableAddOnTest;
    }

    public void setAvailableAddOnTest(String availableAddOnTest) {
        this.availableAddOnTest = availableAddOnTest;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPackageTests() {
        return packageTests;
    }

    public void setPackageTests(String packageTests) {
        this.packageTests = packageTests;
    }

    public String getAvaildedAddOnTest() {
        return availdedAddOnTest;
    }

    public void setAvaildedAddOnTest(String availdedAddOnTest) {
        this.availdedAddOnTest = availdedAddOnTest;
    }

    public String getAvaildedAddOnPackage() {
        return availdedAddOnPackage;
    }

    public void setAvaildedAddOnPackage(String availdedAddOnPackage) {
        this.availdedAddOnPackage = availdedAddOnPackage;
    }

    public String getAvaildedDiscountCouponPackage() {
        return availdedDiscountCouponPackage;
    }

    public void setAvaildedDiscountCouponPackage(String availdedDiscountCouponPackage) {
        this.availdedDiscountCouponPackage = availdedDiscountCouponPackage;
    }

    public String getNotAvaildedAddOnTest() {
        return notAvaildedAddOnTest;
    }

    public void setNotAvaildedAddOnTest(String notAvaildedAddOnTest) {
        this.notAvaildedAddOnTest = notAvaildedAddOnTest;
    }

    public String getNotAvaildedAddOnPackage() {
        return notAvaildedAddOnPackage;
    }

    public void setNotAvaildedAddOnPackage(String notAvaildedAddOnPackage) {
        this.notAvaildedAddOnPackage = notAvaildedAddOnPackage;
    }

    public String getNotAvaildedDiscountCouponPackage() {
        return notAvaildedDiscountCouponPackage;
    }

    public void setNotAvaildedDiscountCouponPackage(String notAvaildedDiscountCouponPackage) {
        this.notAvaildedDiscountCouponPackage = notAvaildedDiscountCouponPackage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getPaymode() {
        return paymode;
    }

    public void setPaymode(String paymode) {
        this.paymode = paymode;
    }

    public ArrayList<String> getPkgTestArray(){
        ArrayList<String> tests = new ArrayList<>();
        String[] testsArr = null;
        if (packageTests.contains(",")){
            testsArr = packageTests.split(",");
        }
        if (testsArr!=null) {
            for (int i = 0; i < testsArr.length; i++) {
                tests.add(testsArr[i]);
            }
        }
        return tests;
    }
}
