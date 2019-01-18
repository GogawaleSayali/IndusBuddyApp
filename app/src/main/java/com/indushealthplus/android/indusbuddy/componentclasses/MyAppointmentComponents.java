package com.indushealthplus.android.indusbuddy.componentclasses;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.helper.MyAppointmentMain;

public class MyAppointmentComponents extends MyAppointmentMain {

    protected void initialise() {
        getActivity()         . getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getActivity()         . getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        rvMyAppt              = rootView.findViewById(R.id.rvMyAppt);
    }

    /*
   * Internet checking code
   * */
    public void snackInternet(){
        Snackbar snackbar = Snackbar
                .make(rvMyAppt, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });

        snackbar.setActionTextColor(Color.RED);
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }

    protected void showToast(String message) {
        Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
    }
}
