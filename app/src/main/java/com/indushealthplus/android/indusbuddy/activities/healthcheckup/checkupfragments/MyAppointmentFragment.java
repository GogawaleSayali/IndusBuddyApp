package com.indushealthplus.android.indusbuddy.activities.healthcheckup.checkupfragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.indushealthplus.android.indusbuddy.helper.MyAppointmentHelperWebServices;

/**
 * Created by amolr on 4/5/18.
 */

public class MyAppointmentFragment extends MyAppointmentHelperWebServices {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialise();
        initialiseClass();
        requestGetAppointmentDetails();
    }

}
