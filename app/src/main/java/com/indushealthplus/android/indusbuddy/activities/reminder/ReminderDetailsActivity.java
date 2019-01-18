package com.indushealthplus.android.indusbuddy.activities.reminder;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.baseactivities.BaseActivity;
import com.indushealthplus.android.indusbuddy.models.ModelItemReminder;
import com.google.gson.Gson;

public class ReminderDetailsActivity extends BaseActivity {
    private TextView tvEventTitle,tvRemindertitle,tvReminderDateTime,tvEmail,tvMobile,tvLocation,tvMedicineName,
            tvMedicineDateTime,tvbMedicineDose,tvTestName,tvDoctorDateTime,tvDuration,tvDurationInMinutes,tvCentreName,
            tvClientid,tvreminders,tvTypeExercise,tvDoctorName,tvStartEvent,tvEndEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Reminder Details");
        initialize();
    }

    /**
     * Initialize ui widgets.
     */
    private void initialize()
    {
        tvEventTitle        = findViewById(R.id.tvEventTitle);
       // tvRemindertitle     =findViewById(R.id.tvreminderTitle);
        tvReminderDateTime  = findViewById(R.id.tvReminderDateTime);
        tvEmail             = findViewById(R.id.tvEmail);
        tvMobile            = findViewById(R.id.tvMobileNo);
        tvLocation          = findViewById(R.id.tvLocation);
        tvMedicineName      = findViewById(R.id.tvMedicineName);
        tvMedicineDateTime  = findViewById(R.id.tvMedicineDateTime);
        tvbMedicineDose     = findViewById(R.id.tvbMedicineDose);
        tvTestName          = findViewById(R.id.tvTestName);
        tvDoctorDateTime    = findViewById(R.id.tvDoctorDateTime);
        tvDuration          = findViewById(R.id.tvDuration);
        tvDurationInMinutes = findViewById(R.id.tvDurationInMinutes);
        tvCentreName        = findViewById(R.id.tvCentreName);
        tvClientid          =findViewById(R.id.tvClientid);
        tvTypeExercise      = findViewById(R.id.tvTypeExercise);
        tvDoctorName        = findViewById(R.id.tvDoctorName);
        tvStartEvent        = findViewById(R.id.tvStartEvent);
        tvEndEvent          = findViewById(R.id.tvEndEvent);
        //tvreminders         =findViewById(R.id.tvreminders);
        setReminderDetails();
    }

    /**
     * show reminder data in details
     */
    private void setReminderDetails() {
        String data        = getIntent().getStringExtra("data");
        Gson gson          = new Gson();
        ModelItemReminder  itemReminder = gson.fromJson(data,ModelItemReminder.class);
        tvEventTitle       .setText(itemReminder.getCategory());
        //tvRemindertitle    .setText(itemReminder.getEventTitle());
        tvReminderDateTime .setText(itemReminder.getReminderDateTime());
        tvEmail            .setText(itemReminder.getEmailId());
        //String mobile=itemReminder.getMobileNo();
        tvMobile           .setText(itemReminder.getMobileNo());
        tvLocation         .setText(itemReminder.getLocation());
        tvMedicineName     .setText(itemReminder.getMedicineName());
        tvMedicineDateTime .setText(itemReminder.getMedicineDateTime());
        tvbMedicineDose    .setText(itemReminder.getMedicineDose());
        tvTestName         .setText(itemReminder.getTestName());
        tvDoctorDateTime   .setText(itemReminder.getDoctorDateTime());
        tvDuration         .setText(itemReminder.getDuration());
        tvDurationInMinutes.setText(itemReminder.getDurationInMinutes());
        tvCentreName       .setText(itemReminder.getCentreName());
        tvClientid         .setText(itemReminder.getClientId());
        tvTypeExercise     .setText(itemReminder.getTypeExercise());
        tvDoctorName       .setText(itemReminder.getDoctorName());
        tvStartEvent       .setText(itemReminder.getStartEvent());
        tvEndEvent         .setText(itemReminder.getEndEvent());
    }

}
