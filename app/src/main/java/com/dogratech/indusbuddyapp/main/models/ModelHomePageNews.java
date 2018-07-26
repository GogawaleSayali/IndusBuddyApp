package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

public class ModelHomePageNews {
    @SerializedName("PDF_URL")
    private String pdfUrls;
    @SerializedName("DTAE")
    private String date;
    @SerializedName("PRIORITY")
    private String priority;
    @SerializedName("PAGE_IMAGE")
    private String pageImages;
    @SerializedName("YEAR")
    private String year;
    @SerializedName("CATEGORY_TYPE")
    private String categoryType;
    @SerializedName("SLIDER_THUMB")
    private String sliderThumb;
    @SerializedName("SLIDER_IMAGE")
    private String sliderInage;
    @SerializedName("SMALL_DESCRIPTION")
    private String smallDescription;
    @SerializedName("DETAIL_DESCRIPTION")
    private String detailDescription;
    @SerializedName("MONTH")
    private String month;
    @SerializedName("URL")
    private String url;
    @SerializedName("DOMAIN")
    private String domain;
    @SerializedName("TITLE")
    private String title;

    public String getPdfUrls() {
        return pdfUrls;
    }

    public void setPdfUrls(String pdfUrls) {
        this.pdfUrls = pdfUrls;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPageImages() {
        return pageImages;
    }

    public void setPageImages(String pageImages) {
        this.pageImages = pageImages;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getSliderThumb() {
        return sliderThumb;
    }

    public void setSliderThumb(String sliderThumb) {
        this.sliderThumb = sliderThumb;
    }

    public String getSliderInage() {
        return sliderInage;
    }

    public void setSliderInage(String sliderInage) {
        this.sliderInage = sliderInage;
    }

    public String getSmallDescription() {
        return smallDescription;
    }

    public void setSmallDescription(String smallDescription) {
        this.smallDescription = smallDescription;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getPdfUrlArray() {
        String[] pdfUrlArr = pdfUrls.split("|");
        return pdfUrlArr;
    }

    public String[] getPageImageArray() {
        String[] pageImageArr = pageImages.split("|");
        return pageImageArr;
    }
}
