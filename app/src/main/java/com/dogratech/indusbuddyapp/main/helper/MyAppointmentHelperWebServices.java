package com.dogratech.indusbuddyapp.main.helper;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.adapters.MyAppointmentsAdapter;
import com.dogratech.indusbuddyapp.main.componentclasses.MyAppointmentComponents;
import com.dogratech.indusbuddyapp.main.models.Model_Item_Appointment;
import com.dogratech.indusbuddyapp.main.models.Model_Response_Appointment_Details;
import com.dogratech.indusbuddyapp.main.retrofit.ApiUrl;
import com.dogratech.indusbuddyapp.main.uitility.NetworkUtility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amolr on 27/3/18.
 */

public class MyAppointmentHelperWebServices extends MyAppointmentComponents
{

    protected void requestGetAppointmentDetails() {
        //userId = "210263";
        String url = ApiUrl.Base_URL_MOBILE +ApiUrl.getAppointmentDetails+userId;
        if(NetworkUtility.isNetworkAvailable(getActivity())){
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custom_loader, null);
            dialogBuilder.setView(dialogView);
            final AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.setCancelable(false);
            alertDialog.show();
            interface_get.getAppointment(url).enqueue(new Callback<Model_Response_Appointment_Details>() {
                @Override
                public void onResponse(Call<Model_Response_Appointment_Details> call, Response<Model_Response_Appointment_Details> response) {
                    try{
                        if (alertDialog.isShowing()){
                            alertDialog.hide();
                        }
                        if(response.isSuccessful()){
                            if(response.body()!=null){
                                Model_Response_Appointment_Details details = response.body();
                                if(details.getStatusCode().equalsIgnoreCase(getString(R.string.statuse_code_0)))
                                {
                                    ArrayList<Model_Item_Appointment> appointment = details.getAppointment();
                                        Log.v("Suze : ", "" + appointment.size());
                                        if(appointment.size()>0)
                                        {
                                            tvDataNotFound.setVisibility(View.GONE);
                                        }
                                        else
                                        {
                                            tvDataNotFound.setVisibility(View.VISIBLE);
                                        }
                                        showApptList(appointment);

                                }else{
                                    String error_code = details.getErrorCode();
                                    ErrorCodesAndMessagesManager errCodeMsg = ErrorCodesAndMessagesManager.getInstance();
                                    showToast(errCodeMsg.getErrorMessage(Integer.parseInt(error_code)));
                                }
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<Model_Response_Appointment_Details> call, Throwable t) {
                    Log.d(TAG,t.toString());
                    if (alertDialog.isShowing()){
                        alertDialog.hide();
                    }
                }
            });
        }else{
            snackInternet();
        }

    }

    private void showApptList(ArrayList<Model_Item_Appointment> appointment) {
        MyAppointmentsAdapter adapter = new MyAppointmentsAdapter(getActivity(),appointment);
        RecyclerView. LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvMyAppt . setLayoutManager(mLayoutManager);
        rvMyAppt . setItemAnimator(new DefaultItemAnimator());
        rvMyAppt .setAdapter(adapter);
    }

}
