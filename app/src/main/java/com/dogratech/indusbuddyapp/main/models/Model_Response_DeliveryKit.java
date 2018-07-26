package com.dogratech.indusbuddyapp.main.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshaya on 13/3/18.
 */

public class Model_Response_DeliveryKit {
    /*
    *{
            "StatusCode": 0,
            "JoiningKitDLStatus": {
                "DeliveryKitStatus": "Delivered",
                "DeliveryKitDate": "29-SEP-2016",
                "Remark": "470814"
            },
            "ErrorCode": 200
    }
    * */

    @SerializedName("StatusCode")
    private String StatusCode ;

    @SerializedName("JoiningKitDLStatus")
    private Model_Item_DeliveryKitStatus itemDeliveryKit;

    @SerializedName("ErrorCode")
    private String ErrorCode ;

    public Model_Response_DeliveryKit() {
    }

    public Model_Response_DeliveryKit(String statusCode, Model_Item_DeliveryKitStatus itemDeliveryKit,
                                      String errorCode) {
        StatusCode = statusCode;
        this.itemDeliveryKit = itemDeliveryKit;
        ErrorCode = errorCode;
    }

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String statusCode) {
        StatusCode = statusCode;
    }

    public Model_Item_DeliveryKitStatus getItemDeliveryKit() {
        return itemDeliveryKit;
    }

    public void setItemDeliveryKit(Model_Item_DeliveryKitStatus itemDeliveryKit) {
        this.itemDeliveryKit = itemDeliveryKit;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }
}
