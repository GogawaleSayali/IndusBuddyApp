package com.dogratech.indusbuddyapp.main.activities.healthcheckup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.adapters.ReasonsAdapter;
import com.dogratech.indusbuddyapp.main.helper.Constatnts;
import com.dogratech.indusbuddyapp.main.helper.UpdateMyAppointment;
import com.dogratech.indusbuddyapp.main.models.ModelPostPoneReasonRes;
import com.dogratech.indusbuddyapp.main.models.Model_Response_AppReschedule;
import com.dogratech.indusbuddyapp.main.uitility.NetworkUtility;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amolr on 23/3/18.
 */

public class GetAppointmentActivity extends UpdateMyAppointment implements  View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_appointment);
        initializeToolBar();
        initialize();
        initialiseClass();
        setListeners();
    }

    /*********************************
     * Initialize all the ui widgets.*
     *********************************/
    private void setListeners() {
        tvCentreIcon   .setOnClickListener(this);
        tvCalendarIcon .setOnClickListener(this);
        llSchedule     .setOnClickListener(this);
        tvClose        .setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvCalendarIcon:
                dpd.show(getFragmentManager(), "Datepickerdialog");
                Calendar calendar = Calendar.getInstance();
                dpd.setMinDate(calendar);
                break;
            case R.id.tvCentreIcon:
                Intent intent = new Intent(this,CentreSelectionActivity.class);
                intent.putExtra("cityId",cityId);
                startActivityForResult(intent,100);
                break;
            case R.id.llSchedule :
                if (!centreCode.equalsIgnoreCase("")) {
                    getReasons();
                   // requestRescheduleApp();
                }
                break;
            case R.id.tvClose :
                rlReasons.setVisibility(View.GONE);
                break;

            default:
                break;
        }
    }

    /************************************
     * Get Appointment Reschedule reason*
     ************************************/
    private void getReasons() {
    if(NetworkUtility.isNetworkAvailable(getApplicationContext())){
        interface_get.getPostPoneReason()
            .enqueue(new Callback<ModelPostPoneReasonRes>() {
                @Override
                public void onResponse(Call<ModelPostPoneReasonRes> call,
                                       Response<ModelPostPoneReasonRes> response) {
                try{
                    if(response.isSuccessful()){
                        if(response.body()!=null){
                            ModelPostPoneReasonRes reasonRes = response.body();
                            if(reasonRes.getStatusCode()== Constatnts.S_CODE_0){
                            if (reasonRes.getModelReason().size()>0) {
                                rlReasons.setVisibility(View.VISIBLE);
                                reasonsAdapter = new ReasonsAdapter(
                               GetAppointmentActivity.this, reasonRes.getModelReason());
                                rvReasons.setLayoutManager(
                                        new LinearLayoutManager(GetAppointmentActivity.this));
                                rvReasons.setAdapter(reasonsAdapter);
                            }
                            }else{
                                mToast("Error:"+reasonRes.getErrorCode());
                            }
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                }
                @Override
                public void onFailure(Call<ModelPostPoneReasonRes> call, Throwable t) {
                    Log.d(TAG,t.toString());
                }
            });
    }else {
        Toast.makeText(this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
    }
    }

    public void selectedReason(String reasonId){
        requestRescheduleApp(reasonId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100){
            if (resultCode == Activity.RESULT_OK){
                centreCode = data.getExtras().getString("centreCode");
            }
        }
    }
}
