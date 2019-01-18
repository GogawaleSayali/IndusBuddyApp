package com.indushealthplus.android.indusbuddy.helper;

import android.util.Log;

import com.indushealthplus.android.indusbuddy.componentclasses.GetAppointmentComponents;
import com.indushealthplus.android.indusbuddy.models.Model_Response_AppReschedule;
import com.indushealthplus.android.indusbuddy.uitility.NetworkUtility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amolr on 27/3/18.
 */

public class UpdateMyAppointment extends GetAppointmentComponents {

    protected void requestRescheduleApp(String reasonid) {
        String appDate        = tvDateAndTime.getText().toString().trim();
        String[] items1       = appDate.split("-");
        String date           = items1[0];
        String month          = items1[1];
        String year           = items1[2];
        String appNo          = apptNumber;
        String appYear        = year;
        String financials        = financial;
        String appCentre      = centreCode;
        String apptActionCode = "MOBILE APP";
        String apptActionDetCode = "2";
        String reason     = reasonid;
        String remark     = "test";
        if(NetworkUtility.isNetworkAvailable(getApplicationContext())){
            interface_post.RESCHEDULE_CALL(appNo,financials,appCentre,appDate,apptActionCode,apptActionDetCode,remark,reason,"1")
                    .enqueue(new Callback<Model_Response_AppReschedule>() {
                @Override
                public void onResponse(Call<Model_Response_AppReschedule> call, Response<Model_Response_AppReschedule> response) {
                    try{
                        if(response.isSuccessful()){
                            if(response.body()!=null){
                                Model_Response_AppReschedule reschedule = response.body();
                                if(reschedule.getStatusCode()== Constatnts.S_CODE_0){
                                    mToast("Status:"+reschedule.getStatusCode());
                                }else{
                                    mToast("Error:"+reschedule.getErrorCode());
                                }
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<Model_Response_AppReschedule> call, Throwable t) {
                    Log.d(TAG,t.toString());
                }
            });
        }else {
            mToast("Please check your internet connection!!");
        }
    }

}
