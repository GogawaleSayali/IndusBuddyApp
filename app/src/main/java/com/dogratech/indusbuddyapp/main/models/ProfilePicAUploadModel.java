package com.dogratech.indusbuddyapp.main.models;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProfilePicAUploadModel {
    private String ehrId;
    private MultipartBody.Part requestBodyFile;

    public String getEhrId() {
        return ehrId;
    }

    public void setEhrId(String ehrId) {
        this.ehrId = ehrId;
    }

    public MultipartBody.Part getRequestBodyFile() {
        return requestBodyFile;
    }

    public void setRequestBodyFile(MultipartBody.Part requestBodyFile) {
        this.requestBodyFile = requestBodyFile;
    }
}
