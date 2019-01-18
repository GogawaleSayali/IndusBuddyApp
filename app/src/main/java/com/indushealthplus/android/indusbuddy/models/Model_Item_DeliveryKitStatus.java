package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshaya on 13/3/18.
 */

public class Model_Item_DeliveryKitStatus {
    /*
    * "JoiningKitDLStatus": {
                "DeliveryKitStatus": "Delivered",
                "DeliveryKitDate": "29-SEP-2016",
                "Remark": "470814"
            }
    * */
    @SerializedName("DeliveryKitStatus")
    private String DeliveryKitStatus ;

    @SerializedName("DeliveryKitDate")
    private String DeliveryKitDate ;

    @SerializedName("Remark")
    private String Remark ;

    public Model_Item_DeliveryKitStatus() {
    }

    public Model_Item_DeliveryKitStatus(String deliveryKitStatus, String deliveryKitDate, String remark) {
        DeliveryKitStatus = deliveryKitStatus;
        DeliveryKitDate = deliveryKitDate;
        Remark = remark;
    }

    public String getDeliveryKitStatus() {
        return DeliveryKitStatus;
    }

    public void setDeliveryKitStatus(String deliveryKitStatus) {
        DeliveryKitStatus = deliveryKitStatus;
    }

    public String getDeliveryKitDate() {
        return DeliveryKitDate;
    }

    public void setDeliveryKitDate(String deliveryKitDate) {
        DeliveryKitDate = deliveryKitDate;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
