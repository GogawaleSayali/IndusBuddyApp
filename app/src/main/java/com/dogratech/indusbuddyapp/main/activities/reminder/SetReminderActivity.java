package com.dogratech.indusbuddyapp.main.activities.reminder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.baseactivities.BaseActivity;
import com.dogratech.indusbuddyapp.main.activities.healthcheckup.GetAppointmentActivity;
import com.dogratech.indusbuddyapp.main.activities.signinsignup.LoginActivity;
import com.dogratech.indusbuddyapp.main.adapters.OneTimeSumamryAdapter;
import com.dogratech.indusbuddyapp.main.adapters.ReasonsAdapter;
import com.dogratech.indusbuddyapp.main.adapters.ReminderAdapter;
import com.dogratech.indusbuddyapp.main.helper.Constatnts;
import com.dogratech.indusbuddyapp.main.helper.ErrorCodesAndMessagesManager;
import com.dogratech.indusbuddyapp.main.managers.SharedPrefsManager;
import com.dogratech.indusbuddyapp.main.models.ModelItemReminder;
import com.dogratech.indusbuddyapp.main.models.ModelPostPoneReasonRes;
import com.dogratech.indusbuddyapp.main.models.ModelUserDetails;
import com.dogratech.indusbuddyapp.main.models.Model_Item_Appointment;
import com.dogratech.indusbuddyapp.main.models.Model_Response;
import com.dogratech.indusbuddyapp.main.models.Model_Response_Appointment_Details;
import com.dogratech.indusbuddyapp.main.models.Model_Response_ReminderList;
import com.dogratech.indusbuddyapp.main.models.Model_Response_setReminderDetails;
import com.dogratech.indusbuddyapp.main.models.Model_item_setReminderDetails;
import com.dogratech.indusbuddyapp.main.models.ReminderListItem;
import com.dogratech.indusbuddyapp.main.models.WeeklyDaysItem;
import com.dogratech.indusbuddyapp.main.retrofit.ApiClient;
import com.dogratech.indusbuddyapp.main.retrofit.ApiInterfaceGet;
import com.dogratech.indusbuddyapp.main.retrofit.ApiInterfacePost;
import com.dogratech.indusbuddyapp.main.retrofit.ApiUrl;
import com.dogratech.indusbuddyapp.main.uitility.NetworkUtility;
import com.google.gson.Gson;
import com.shagi.materialdatepicker.date.DatePickerFragmentDialog;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetReminderActivity extends BaseActivity implements View.OnClickListener,
        OnDayClickListener, DatePickerFragmentDialog.OnDateSetListener {
    private EditText etRemTitle, etRemFor, etMobile, etEmail, etOneTimeNumber, etOccurancesNumber, etOnRepeat,
            etDrName, etTypeOfExercise, etExerciseDurationinMinutes, etTestName, etCentername, etMedicineName,
            etMedicineDose, etRemMeFor, etOtherLocation, etDurationdateTime, etDuration;
    private TextView tvCancel, tvCancelEndDate, tvOk, tvOkEndDate, tvCancelOneTime, tvCancelRepeat, tvDone, tvAddOnTimeSchedule, tvDoneRepeat,
            tvReminderDateTimeIOneTime, tvStartDateVal, tvEndDateVal, tvDrDateTime, tvmedicineDateTime,
            tvRepeatEvery, tvRepeatsOn, tvRepeatSumm, tvSubmitReminder, tv_DrDateTime, tvMedicineDateTime, tvSummary;
    private Spinner spinnerCategory, spinnerSchedule, spinnerRepeats, spinnerRepeatEvery;
    private LinearLayout llStartDate, llEndDate, llDrConsult, llExecise, llMedicine, llLabTest, llOther,
            llRepeatWeeklyOn, llMonthlyRepeatBy, llOneTime, llRepeat, llOnetimeSummary;
    private RelativeLayout rlCalendarView, rlRepeat, rlCalenderViewEndDate;
    private RelativeLayout rlProgress;
    private RecyclerView rv_reminder;
    private CalendarView calendarView, calenderViewEndDate;
    private EventDay eventDay = null;
    private String dateStr, dateStr1, date, dateStr2;
    private RadioGroup rgEnds, rgMonthlyRepeatBy, rgReminder, rgforme_other_Reminder;
    private RadioButton rBOneTime, rBRepeat, rb_for_me, rb_other;
    private RelativeLayout rlOneTime;
    private RecyclerView rvScheduleOneTime, rvOnetimeSummary;
    private DatePickerFragmentDialog dialogDatePicker;
    private FragmentManager fragmentManager;
    private Calendar calendarSchedule, calendarScheduleEnddate;
    private OneTimeSumamryAdapter adapter;
    private ArrayList<String> oneTimeSummary = new ArrayList<>();
    private int pos = -1;
    private String repeatSummuryStr = "";
    private String schedules;
    private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    private SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
    ArrayList<CheckBox> weekDayArr = new ArrayList<>();
    private List<ModelItemReminder> reminderList;
    private ApiInterfacePost interface_post;
    private SharedPrefsManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_reminder);
        Toolbar toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar, "Set Reminder");
        initialize();
        addfieldintoedittext();

    }


    @SuppressLint("ResourceType")
    public boolean validate() {


        String Reminder_title = etRemTitle.getText().toString();
        String start_date = tvStartDateVal.getText().toString();
        String end_date = tvEndDateVal.getText().toString();

        //Spinner validation
        TextView errorText = (TextView) spinnerCategory.getSelectedView();
        if (spinnerCategory.getSelectedItem().equals("Select category")) {
            errorText.setError("anything here, just to add the icon");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Pick one of them");//changes the selected item text to this
            errorText.setError(null);
            return true;
        }


        if (rBOneTime.isChecked() || rBRepeat.isChecked()) {
            //Toast.makeText(getApplicationContext(),"Selected",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Select Item", Toast.LENGTH_SHORT).show();
            rBOneTime.setError("Select item");
            return true;
        }
        rBOneTime.setError(null);

/*
      if (Reminder_title.isEmpty()) {
            etRemTitle.setError("Fill Empty field");
            requestFocus(etRemTitle);
            return true;
        }
       *//*if (start_date.isEmpty()) {
            tvStartDateVal.setError("Set time");
            return true;
        }*/

        return false;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    /*************************
     * Initialize ui widgets.*
     *************************/
    private void initialize() {

        interface_post = ApiClient.getClient(ApiClient.BASE_URL_TYEP_INDUS).create(ApiInterfacePost.class);
        preferences = SharedPrefsManager.getSharedInstance(SetReminderActivity.this);
        tvSubmitReminder = findViewById(R.id.btn_submitReminder);
        etRemTitle = findViewById(R.id.etRemTitle);
        //etRemFor = findViewById(R.id.etRemFor);
        etMobile = findViewById(R.id.etMobile);
        etEmail = findViewById(R.id.etEmail);
        etOneTimeNumber = findViewById(R.id.etOneTimeNumber);
        etOccurancesNumber = findViewById(R.id.etOccurancesNumber);
        etOnRepeat = findViewById(R.id.etOnRepeat);
        etDrName = findViewById(R.id.etDrName);
        etTypeOfExercise = findViewById(R.id.etTypeofExecise);
        etExerciseDurationinMinutes = findViewById(R.id.etTypeofExecise);
        etTestName = findViewById(R.id.etTestName);
        etCentername = findViewById(R.id.etCentreName);
        etMedicineName = findViewById(R.id.etMedicineName);
        etMedicineDose = findViewById(R.id.etMedicineDose);
        etRemMeFor = findViewById(R.id.etRemMeFor);
        etOtherLocation = findViewById(R.id.etOthersLocation);
        etDurationdateTime = findViewById(R.id.etDurationDateTime);
        etDuration = findViewById(R.id.etOthersLocation);


        tvOk = findViewById(R.id.tvOk);
        tvOkEndDate = findViewById(R.id.tvOkEndDate);
        tvAddOnTimeSchedule = findViewById(R.id.tvAddOnTimeSchedule);
        tvDrDateTime = findViewById(R.id.tvDrDateTime);
        tvmedicineDateTime = findViewById(R.id.tvmedicineDateTime);
        tvRepeatEvery = findViewById(R.id.tvRepeatEvery);
        tvStartDateVal = findViewById(R.id.tvStartDateVal);
        tvEndDateVal = findViewById(R.id.tvEndDateVal);
        tvCancelOneTime = findViewById(R.id.tvCancelOneTime);
        tvCancelRepeat = findViewById(R.id.tvCancelRepeat);
        tvDone = findViewById(R.id.tvDone);
        tvDoneRepeat = findViewById(R.id.tvDoneRepeat);
        tvRepeatSumm = findViewById(R.id.tvRepeatSumm);
        tvCancel = findViewById(R.id.tvCancel);
        tvCancelEndDate = findViewById(R.id.tvCancelEndDate);
        tvRepeatsOn = findViewById(R.id.tvRepeatsOn);
        tvReminderDateTimeIOneTime = findViewById(R.id.tvReminderDateTimeIOneTime);
        tv_DrDateTime = findViewById(R.id.tvDrDateTime);
        tvSummary = findViewById(R.id.summary);


        spinnerSchedule = findViewById(R.id.spinnerSchedule);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerRepeats = findViewById(R.id.spinnerRepeats);
        spinnerRepeatEvery = findViewById(R.id.spinnerRepeatEvery);

        llDrConsult = findViewById(R.id.llDrConsult);
        llExecise = findViewById(R.id.llExecise);
        llMedicine = findViewById(R.id.llMedicine);
        llLabTest = findViewById(R.id.llLabTest);
        llRepeatWeeklyOn = findViewById(R.id.llRepeatWeeklyOn);
        llMonthlyRepeatBy = findViewById(R.id.llMonthlyRepeatBy);
        llOther = findViewById(R.id.llOther);
        llStartDate = findViewById(R.id.llStartDate);
        llEndDate = findViewById(R.id.llEndDate);
        llOnetimeSummary = findViewById(R.id.one_time_summary_layout);

        rlCalendarView = findViewById(R.id.rlCalendarView);
        rlCalenderViewEndDate = findViewById(R.id.rlCalendarViewEndDate);
        rlRepeat = findViewById(R.id.rlRepeat);
        rlOneTime = findViewById(R.id.rlOneTime);


        llOneTime = findViewById(R.id.llOneTime);
        llRepeat = findViewById(R.id.llRepeat);
        rgMonthlyRepeatBy = findViewById(R.id.rgMonthlyRepeatBy);
        rgEnds = findViewById(R.id.rgEnds);


        rBOneTime = findViewById(R.id.rBOneTime);
        rBRepeat = findViewById(R.id.rBRepeat);

        rb_for_me = findViewById(R.id.rb_for_me);
        rb_other = findViewById(R.id.rb_other);

        rvScheduleOneTime = findViewById(R.id.rvScheduleOneTime);
        rvOnetimeSummary = findViewById(R.id.rvScheduleOneTime_summary);
        //Global Calendar instance
        calendarView = findViewById(R.id.calendarView);

        calendarSchedule = Calendar.getInstance();
        int mYear = calendarSchedule.get(Calendar.YEAR);
        int mMonth = calendarSchedule.get(Calendar.MONTH);
        int mDay = calendarSchedule.get(Calendar.DAY_OF_MONTH);
        calendarSchedule.set(mYear, mMonth, mDay);


        try {
            calendarView.setDate(calendarSchedule);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }


        //Set spinners(dropdown).
        setSpinnersData();
        setListeners();
    }

    private void setSpinnersData() {
        ArrayList<String> scheduleList = new ArrayList<>();
        ArrayList<String> categoryList = new ArrayList<>();
        ArrayList<String> repeartList = new ArrayList<>();
        ArrayList<String> everyData = new ArrayList<>();
        scheduleList.add("Hours");
        scheduleList.add("Days");

        categoryList.add("Select category");
        categoryList.add("Dr. Consultation");
        categoryList.add("Exercise");
        categoryList.add("Lab Test");
        categoryList.add("Medicine");
        categoryList.add("Others");

        repeartList.add("Daily");
        repeartList.add("Weekly");
        repeartList.add("Monthly");
        repeartList.add("Yearly");
        for (int i = 0; i < 30; i++) {
            int everyPlus = i + 1;
            everyData.add("" + everyPlus);
        }
        spinnerSchedule.setAdapter(new ArrayAdapter<>(SetReminderActivity.this,
                R.layout.row_spinner_schedule, scheduleList));
        spinnerCategory.setAdapter(new ArrayAdapter<>(SetReminderActivity.this,
                R.layout.row_spinner_schedule, categoryList));
        spinnerRepeats.setAdapter(new ArrayAdapter<>(SetReminderActivity.this,
                R.layout.row_spinner_schedule, repeartList));
        spinnerRepeatEvery.setAdapter(new ArrayAdapter<>(SetReminderActivity.this,
                R.layout.row_spinner_schedule, everyData));

        weekDayArr.clear();
        weekDayArr.add((CheckBox) findViewById(R.id.cbSunday));
        weekDayArr.add((CheckBox) findViewById(R.id.cbMonday));
        weekDayArr.add((CheckBox) findViewById(R.id.cbTuesday));
        weekDayArr.add((CheckBox) findViewById(R.id.cbWedensday));
        weekDayArr.add((CheckBox) findViewById(R.id.cbThirsday));
        weekDayArr.add((CheckBox) findViewById(R.id.cbFriday));
        weekDayArr.add((CheckBox) findViewById(R.id.cbSaturday));
    }

    /*******************************************
     * Set Listeners to ui widgets like onClick.*
     *******************************************/
    private void setListeners() {
        llStartDate.setOnClickListener(this);
        llEndDate.setOnClickListener(this);
        rlCalendarView.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        tvCancelEndDate.setOnClickListener(this);
        tvOk.setOnClickListener(this);
        tvOkEndDate.setOnClickListener(this);
        tvDrDateTime.setOnClickListener(this);
        tvCancelOneTime.setOnClickListener(this);
        tvCancelRepeat.setOnClickListener(this);
        tvDone.setOnClickListener(this);
        tvDoneRepeat.setOnClickListener(this);
        tvRepeatsOn.setOnClickListener(this);
        tvOk.setOnClickListener(this);
        llOneTime.setOnClickListener(this);
        llRepeat.setOnClickListener(this);
        calendarView.setOnDayClickListener(this);
        tvmedicineDateTime.setOnClickListener(this);
        tvAddOnTimeSchedule.setOnClickListener(this);
        tvReminderDateTimeIOneTime.setOnClickListener(this);
        tvSubmitReminder.setOnClickListener(this);
        rb_for_me.setOnClickListener(this);
        rb_other.setOnClickListener(this);
 /*       radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group.getCheckedRadioButtonId() == R.id.rBOneTime){
                    //On radio button One time schedule is checked.
                    rlOneTime.setVisibility(View.VISIBLE);
                    String currentDate = format.format(calendarSchedule.getTime());
                    tvReminderDateTimeIOneTime.setText(currentDate);
                    tvRepeatSumm.setText("");
                }else if (group.getCheckedRadioButtonId() == R.id.rBRepeat){
                    //On radio button repeat schedule is checked.
                    String startdate = tvStartDateVal.getText().toString();
                    if (!startdate.equalsIgnoreCase("")) {
                        rlRepeat.setVisibility(View.VISIBLE);
                        rlOneTime.setVisibility(View.GONE);
                        tvRepeatsOn.setText(startdate);
                        tvRepeatSumm.setText("");
                    }else {
                      RadioButton button = group.findViewById( R.id.rBRepeat);
                      button.setChecked(false);
                        tvRepeatSumm.setText("");
                      Toast.makeText(SetReminderActivity.this, "Please select start date first!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });*/


        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getAdapter().getItem(position).toString()
                        .equalsIgnoreCase("Dr. Consultation")) {
                    llDrConsult.setVisibility(View.VISIBLE);
                    llExecise.setVisibility(View.GONE);
                    llMedicine.setVisibility(View.GONE);
                    llLabTest.setVisibility(View.GONE);
                    llOther.setVisibility(View.GONE);
                } else if (parent.getAdapter().getItem(position).toString()
                        .equalsIgnoreCase("Exercise")) {
                    llExecise.setVisibility(View.VISIBLE);
                    llDrConsult.setVisibility(View.GONE);
                    llMedicine.setVisibility(View.GONE);
                    llLabTest.setVisibility(View.GONE);
                    llOther.setVisibility(View.GONE);
                } else if (parent.getAdapter().getItem(position).toString()
                        .equalsIgnoreCase("Lab Test")) {
                    llLabTest.setVisibility(View.VISIBLE);
                    llDrConsult.setVisibility(View.GONE);
                    llExecise.setVisibility(View.GONE);
                    llMedicine.setVisibility(View.GONE);
                    llOther.setVisibility(View.GONE);
                } else if (parent.getAdapter().getItem(position).toString()
                        .equalsIgnoreCase("Medicine")) {
                    llMedicine.setVisibility(View.VISIBLE);
                    llDrConsult.setVisibility(View.GONE);
                    llExecise.setVisibility(View.GONE);
                    llLabTest.setVisibility(View.GONE);
                    llOther.setVisibility(View.GONE);
                } else if (parent.getAdapter().getItem(position).toString()
                        .equalsIgnoreCase("Others")) {
                    llOther.setVisibility(View.VISIBLE);
                    llDrConsult.setVisibility(View.GONE);
                    llExecise.setVisibility(View.GONE);
                    llMedicine.setVisibility(View.GONE);
                    llLabTest.setVisibility(View.GONE);
                } else {
                    llDrConsult.setVisibility(View.GONE);
                    llExecise.setVisibility(View.GONE);
                    llMedicine.setVisibility(View.GONE);
                    llLabTest.setVisibility(View.GONE);
                    llOther.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinnerRepeats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getAdapter().getItem(position).toString()
                        .equalsIgnoreCase("Daily")) {
                    llRepeatWeeklyOn.setVisibility(View.GONE);
                    llMonthlyRepeatBy.setVisibility(View.GONE);
                    tvRepeatEvery.setText("days");
                } else if (parent.getAdapter().getItem(position).toString()
                        .equalsIgnoreCase("Weekly")) {
                    llRepeatWeeklyOn.setVisibility(View.VISIBLE);
                    llMonthlyRepeatBy.setVisibility(View.GONE);
                    tvRepeatEvery.setText("weeks");
                } else if (parent.getAdapter().getItem(position).toString()
                        .equalsIgnoreCase("Monthly")) {
                    llMonthlyRepeatBy.setVisibility(View.VISIBLE);
                    llRepeatWeeklyOn.setVisibility(View.GONE);
                    tvRepeatEvery.setText("months");
                } else if (parent.getAdapter().getItem(position).toString()
                        .equalsIgnoreCase("Yearly")) {
                    llRepeatWeeklyOn.setVisibility(View.GONE);
                    llMonthlyRepeatBy.setVisibility(View.GONE);
                    tvRepeatEvery.setText("years");
                } else {
                    llRepeatWeeklyOn.setVisibility(View.GONE);
                    llMonthlyRepeatBy.setVisibility(View.GONE);
                    tvRepeatEvery.setText("days");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerRepeatEvery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getAdapter().getItem(position).toString().equalsIgnoreCase("1")) {
                    repeatSummuryStr = "Daily";
                    tvRepeatEvery.setText("days");
                } else {
                    repeatSummuryStr = "Every " + parent.getAdapter().getItem(position).toString() + " days";
                    tvRepeatEvery.setText("days");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submitReminder:
                if (!validate()) {
                    Model_item_setReminderDetails details = new Model_item_setReminderDetails();
                    details.setCategory(spinnerCategory.getSelectedItem().toString()); //Category
                    details.setEmailId(etEmail.getText().toString()); //Email Id
                    details.setMobileNo(etMobile.getText().toString()); //Mobile No
                    //  details.setEventTitle(etRemTitle.getText().toString()); //eventTitle
                    details.setEventTitle(spinnerCategory.getSelectedItem().toString()); //eventTitle
                    details.setReminderDateTime(tvReminderDateTimeIOneTime.getText().toString()); //reminderDateTime
                    details.setEventStartDate(tvStartDateVal.getText().toString()); //eventStartDate
                    details.setEventEndDate(tvEndDateVal.getText().toString()); //eventEndDate
                    details.setDoctorName(etDrName.getText().toString()); //doctorName
                    details.setDoctorDateTime(tvDrDateTime.getText().toString()); //doctorDateTime
                    details.setMedicineName(etMedicineName.getText().toString()); //medicineName
                    details.setMedicineDose(etMedicineDose.getText().toString()); //medicineDose
                    details.setMedicineDateTime(tvmedicineDateTime.getText().toString()); //medicineDateTime
                    details.setTypeOfExercise(etTypeOfExercise.getText().toString()); //typeOfExercise
                    details.setDurationInMinutes(etExerciseDurationinMinutes.getText().toString()); //durationInMinutes
                    details.setTestName(etTestName.getText().toString()); //testName
                    details.setCentreName(etCentername.getText().toString()); //centreName
                    details.setRemindMeFor(etRemMeFor.getText().toString()); //remindMeFor
                    details.setLocation(etOtherLocation.getText().toString()); //location
                    details.setDuration(etDuration.getText().toString()); //duration
                    //details.setClientName(etRemFor.getText().toString()); //clientName
                    int clientid = Integer.parseInt(preferences.getData(getString(R.string.shars_userid))); //clientId
                    details.setClientId(clientid);
                    details.setReminderlist(oneTimeSummary);//reminders
                    details.setEhrReminderMasterId("1");//ehrReminderMasterId
                    details.setDailyRepeat(spinnerRepeatEvery.getSelectedItemPosition()); //dailyRepeat
                    details.setWeeklyRepeat(spinnerRepeatEvery.getSelectedItemPosition()); //weeklyRepeat
                    details.setMonthlyRepeat(spinnerRepeatEvery.getSelectedItemPosition()); //monthlyRepeat
                    details.setYearlyRepeat(spinnerRepeatEvery.getSelectedItemPosition()); //yearlyRepeat
                    details.setRepeatBy("1");//repeatBy
                    details.setEnds("1"); //ends
                    String value = etOccurancesNumber.getText().toString();
                    if (value.equals("")) {
                        details.setAfterText(0);
                    } else {
                        int finalValue = Integer.parseInt(value);
                        details.setAfterText(finalValue);//afterText
                    }


                    details.setOnText(etOnRepeat.getText().toString());//onText
                    ArrayList<WeeklyDaysItem> weeklydays = new ArrayList<>();
                    details.setWeeklyDaysItems(weeklydays); //weeklyDays
                    details.setRecurrencePattern("1"); //recurrencePattern

                    Gson gson = new Gson();
                    String StringResponse = gson.toJson(details);
                    saveReminderOnServer(details);
                }
                break;
            case R.id.tvAddOnTimeSchedule:
                showOnTimeSummary();
                break;
            case R.id.rb_for_me:
                addfieldintoedittext();
                break;
            case R.id.rb_other:
                clearEdittext();
                break;
            case R.id.llStartDate:
                Calendar startCalMinDate = Calendar.getInstance();
                /**TODO - now set min date to start calendar
                 * which you want
                 */
                int mYear = startCalMinDate.get(Calendar.YEAR);
                int mMonth = startCalMinDate.get(Calendar.MONTH);
                int mDay = startCalMinDate.get(Calendar.DAY_OF_MONTH);
                startCalMinDate.set(mYear, mMonth, mDay - 1);
                calendarView.setMinimumDate(startCalMinDate);
                rlCalendarView.clearFocus();
                rlCalendarView.setVisibility(View.VISIBLE);
                rlCalendarView.setTag("start");

                break;

            case R.id.llEndDate:
                //calendarView.invalidate();
                Calendar endCalMinDate = Calendar.getInstance();

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
                if (dateStr1 == null) {
                    Toast.makeText(this, "Please select start date First", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        endCalMinDate.setTime(sdf.parse(dateStr1));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    calendarView.setMinimumDate(endCalMinDate);
                    rlCalendarView.setVisibility(View.VISIBLE);
                    rlCalendarView.setTag("end");
                }
                /**
                 *TODO - now set minimun date for endcalendar
                 * */

                break;

            case R.id.tvOk:
                if (eventDay != null) {
                    setDates();
                }
                break;

            case R.id.tvCancel:
                rlCalendarView.setVisibility(View.GONE);
                rlCalendarView.setTag("");
                RadioButton button = findViewById(R.id.rBRepeat);
                button.setChecked(false);
                RadioButton buttonO = findViewById(R.id.rBOneTime);
                buttonO.setChecked(false);
                eventDay = null;
                break;

            case R.id.tvCancelOneTime:
                rlRepeat.setVisibility(View.GONE);
                rlOneTime.setVisibility(View.GONE);
                rBOneTime.setChecked(false);
                break;
            case R.id.tvCancelRepeat:
                rlRepeat.setVisibility(View.GONE);
                rlOneTime.setVisibility(View.GONE);
                rBRepeat.setChecked(false);
                break;
            case R.id.tvDone:
                llOnetimeSummary.setVisibility(View.VISIBLE);
                rlOneTime.setVisibility(View.GONE);
                rlRepeat.setVisibility(View.GONE);
                //rvScheduleOneTime.setAdapter(adapter);

                break;
            case R.id.tvDoneRepeat:
                rlOneTime.setVisibility(View.GONE);
                rlRepeat.setVisibility(View.GONE);
                tvRepeatSumm.setVisibility(View.VISIBLE);
                tvSummary.setVisibility(View.VISIBLE);
                llOnetimeSummary.setVisibility(View.GONE);
                setSymmaryText();
                tvRepeatSumm.setText("\n" + repeatSummuryStr);
                break;
            case R.id.tvDrDateTime:
                Calendar DrDateTime = Calendar.getInstance();
                calendarView.setMinimumDate(DrDateTime);
                rlCalendarView.setVisibility(View.VISIBLE);
                rlCalendarView.setTag("dr");
                break;
            case R.id.tvmedicineDateTime:
                rlCalendarView.setVisibility(View.VISIBLE);
                rlCalendarView.setTag("medicine");
                break;
            case R.id.tvReminderDateTimeIOneTime:
                if (dateStr1 == null) {
                    Toast.makeText(getApplicationContext(), "Please select End date first", Toast.LENGTH_LONG).show();
                } else {
                    showCalendarDialog();
                }
                break;
            case R.id.llOneTime:
                rBRepeat.setChecked(false);
                if (rBOneTime.isChecked()) {
                    rlRepeat.setVisibility(View.GONE);
                    rlOneTime.setVisibility(View.VISIBLE);
                } else {
                    rBOneTime.setChecked(true);
                    rlOneTime.setVisibility(View.VISIBLE);
                    Calendar calendarcurrentDate = Calendar.getInstance();
                    //dialogDatePicker.setMinDate(calendarcurrentDate);
                    String currentDate = format.format(calendarcurrentDate.getTime());
                    tvReminderDateTimeIOneTime.setText(currentDate);
                    tvRepeatSumm.setText("");
                }
                break;
            case R.id.llRepeat:
                rBOneTime.setChecked(false);
                String startdate = tvStartDateVal.getText().toString();
                if (!startdate.equalsIgnoreCase("")) {
                    if (rBRepeat.isChecked()) {
                        //rBRepeat.setChecked(false);
                        rlRepeat.setVisibility(View.VISIBLE);
                        rlOneTime.setVisibility(View.GONE);
                        tvRepeatsOn.setText(startdate);
                        tvRepeatSumm.setText("");
                    } else {
                        rBRepeat.setChecked(true);
                        rlRepeat.setVisibility(View.VISIBLE);
                        rlOneTime.setVisibility(View.GONE);
                        tvRepeatsOn.setText(startdate);
                        tvRepeatSumm.setText("");
                        rBRepeat.setChecked(true);
                    }
                } else {
                    rlRepeat.setVisibility(View.GONE);
                    rlOneTime.setVisibility(View.GONE);
                    rBRepeat.setChecked(false);
                    tvRepeatSumm.setText("");
                    Toast.makeText(SetReminderActivity.this, "Please select start date first!!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void clearEdittext() {
        etMobile.setText("");
        etEmail.setText("");


































































        
        etMobile.setFocusableInTouchMode(true);
        etEmail.setFocusableInTouchMode(true);
        rb_for_me.setChecked(false);
        rb_other.setChecked(true);
    }

    private void addfieldintoedittext() {
        etMobile.setText(preferences.getData(getString(R.string.userMobile)));
        etEmail.setText(preferences.getData(getString(R.string.userEmail)));
        etMobile.setFocusable(false);
        etEmail.setFocusable(false);
        rb_for_me.setChecked(true);
        rb_other.setChecked(false);
    }

    private void saveReminderOnServer(Model_item_setReminderDetails details) {
        //TODO - On success - >
        if (NetworkUtility.isNetworkAvailable(getApplicationContext())) {

            try {
                Call<Model_Response_setReminderDetails> userCall = interface_post.saveReminder(details);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                LayoutInflater inflater = this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_loader, null);
                dialogBuilder.setView(dialogView);
                final AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.setCancelable(false);
                alertDialog.show();
                userCall.enqueue(new Callback<Model_Response_setReminderDetails>() {
                    @Override
                    public void onResponse(Call<Model_Response_setReminderDetails> call, Response<Model_Response_setReminderDetails> response) {
                        if (alertDialog.isShowing()) {
                            alertDialog.hide();
                        }
                        try {
                            if (response.isSuccessful()) {
                                try {
                                    if (response.body() != null) {
                                        Model_Response_setReminderDetails modelResponse = response.body();
                                        if (modelResponse.getStatus_code().equalsIgnoreCase(getString(R.string.statuse_code_0))) {
                                            toast(modelResponse.getMsg());
                                            Intent intent = new Intent();
                                            setResult(Activity.RESULT_OK, intent);
                                            finish();
                                        } else {
                                            toast(modelResponse.getMsg());
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<Model_Response_setReminderDetails> call, Throwable t) {
                        if (alertDialog.isShowing()) {
                            alertDialog.hide();
                        }
                        Log.d("tag", t.toString());
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            snackInternet();
        }
    }

    private void snackInternet() {
        Snackbar snackbar = Snackbar
                .make(rv_reminder, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });

        snackbar.setActionTextColor(Color.RED);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }


    private void setSymmaryText() {
        if (rgEnds.getCheckedRadioButtonId() != -1) {
            if (rgEnds.getCheckedRadioButtonId() == R.id.radioRepeatNever) {
                if (spinnerRepeats.getSelectedItem().toString().equalsIgnoreCase("Daily")) {
                    if (Integer.parseInt(spinnerRepeatEvery.getSelectedItem().toString()) == 1) {
                        repeatSummuryStr = "Daily";
                    } else {
                        repeatSummuryStr = "Every " + spinnerRepeatEvery.getSelectedItem() + " days";
                    }
                } else if (spinnerRepeats.getSelectedItem().toString().equalsIgnoreCase("Weekly")) {
                    String weekdays = " ";
                    boolean flag = false;
                    for (int i = 0; i < weekDayArr.size(); i++) {
                        if (weekDayArr.get(i).isChecked()) {
                            if (flag == false) {
                                weekdays = " " + weekDayArr.get(i).getTag().toString();
                                flag = true;
                            } else {
                                weekdays += ", " + weekDayArr.get(i).getTag().toString();
                            }
                        }
                    }

                    if (weekdays.equalsIgnoreCase(" ")) {
                        weekdays = "WEDNESDAY";
                    }
                    weekdays.replace(" , ", "");
                    if (Integer.parseInt(spinnerRepeatEvery.getSelectedItem().toString()) == 1) {
                        repeatSummuryStr = "Weekly on " + weekdays;
                    } else {
                        repeatSummuryStr = "Every " + spinnerRepeatEvery.getSelectedItem() + " weeks on " + weekdays;
                    }
                } else if (spinnerRepeats.getSelectedItem().toString().equalsIgnoreCase("Monthly")) {
                    if (rgMonthlyRepeatBy.getCheckedRadioButtonId() == R.id.rbDayOfMonth) {
                        if (Integer.parseInt(spinnerRepeatEvery.getSelectedItem().toString()) == 1) {
                            repeatSummuryStr = "Monthly on day" + tvRepeatsOn.getText();//TODO- Show only day.
                        } else {
                            repeatSummuryStr = "Every " + spinnerRepeatEvery.getSelectedItem()
                                    + " months on day " + tvRepeatsOn.getText();
                        }
                    } else if (rgMonthlyRepeatBy.getCheckedRadioButtonId() == R.id.rbDayOfWeek) {
                        if (Integer.parseInt(spinnerRepeatEvery.getSelectedItem().toString()) == 1) {
                            repeatSummuryStr = "Monthly on the " + tvRepeatsOn.getText() + "WEDNESDAY";//TODO- Show only day.
                        } else {
                            repeatSummuryStr = "Every " + spinnerRepeatEvery.getSelectedItem()
                                    + "months on the " + tvRepeatsOn.getText() + "WEDNESDAY";
                        }
                    }
                } else if (spinnerRepeats.getSelectedItem().toString().equalsIgnoreCase("Yearly")) {
                    if (Integer.parseInt(spinnerRepeatEvery.getSelectedItem().toString()) == 1) {
                        repeatSummuryStr = "Annually on " + tvRepeatsOn.getText();//TODO- Show only day.
                    } else {
                        repeatSummuryStr = "Every " + spinnerRepeatEvery.getSelectedItem()
                                + "years on " + tvRepeatsOn.getText();
                    }
                }
            } else if (rgEnds.getCheckedRadioButtonId() == R.id.radioRepeatAfter) {
                if (spinnerRepeats.getSelectedItem().toString().equalsIgnoreCase("Daily")) {
                    if (Integer.parseInt(spinnerRepeatEvery.getSelectedItem().toString()) == 1) {
                        repeatSummuryStr = "Daily, " + etOccurancesNumber.getText() + " times";
                    } else {
                        repeatSummuryStr = "Every " + spinnerRepeatEvery.getSelectedItem() + " days, " +
                                etOccurancesNumber.getText() + " times";
                    }
                } else if (spinnerRepeats.getSelectedItem().toString().equalsIgnoreCase("Weekly")) {
                    String weekdays = " ";
                    boolean flag = false;
                    for (int i = 0; i < weekDayArr.size(); i++) {
                        if (weekDayArr.get(i).isChecked()) {
                            if (flag == false) {
                                weekdays = " " + weekDayArr.get(i).getTag().toString();
                                flag = true;
                            } else {
                                weekdays += ", " + weekDayArr.get(i).getTag().toString();
                            }
                        }
                    }
                    if (weekdays.equalsIgnoreCase(" ")) {
                        weekdays = "TUESDAY";
                    }
                    weekdays.replace(" ,", "");
                    if (Integer.parseInt(spinnerRepeatEvery.getSelectedItem().toString()) == 1) {
                        repeatSummuryStr = "Weekly on" + weekdays + "," + etOccurancesNumber.getText() + " times";
                    } else {
                        repeatSummuryStr = "Every " + spinnerRepeatEvery.getSelectedItem() +
                                " weeks on " + weekdays + ", " + etOccurancesNumber.getText() + " times";
                    }
                } else if (spinnerRepeats.getSelectedItem().toString().equalsIgnoreCase("Monthly")) {
                    if (rgMonthlyRepeatBy.getCheckedRadioButtonId() == R.id.rbDayOfMonth) {
                        if (Integer.parseInt(spinnerRepeatEvery.getSelectedItem().toString()) == 1) {
                            repeatSummuryStr = "Monthly on day" + tvRepeatsOn.getText() + ","
                                    + etOccurancesNumber.getText() + " times";//TODO- Show only day.
                        } else {
                            repeatSummuryStr = "Every " + spinnerRepeatEvery.getSelectedItem() + "months, on day"
                                    + tvRepeatsOn.getText() + "," + etOccurancesNumber.getText() + "" + " times";
                        }
                    } else if (rgMonthlyRepeatBy.getCheckedRadioButtonId() == R.id.rbDayOfWeek) {
                        if (Integer.parseInt(spinnerRepeatEvery.getSelectedItem().toString()) == 1) {
                            repeatSummuryStr = "Monthly on the day" + tvRepeatsOn.getText() + "WEDNESDAY, "
                                    + etOccurancesNumber.getText() + " times";//TODO- Show only day.
                        } else {
                            repeatSummuryStr = "Every " + spinnerRepeatEvery.getSelectedItem()
                                    + "months on the " + tvRepeatsOn.getText() + "WEDNESDAY, "
                                    + etOccurancesNumber.getText() + " times";
                        }
                    }
                } else if (spinnerRepeats.getSelectedItem().toString().equalsIgnoreCase("Yearly")) {
                    if (Integer.parseInt(spinnerRepeatEvery.getSelectedItem().toString()) == 1) {
                        repeatSummuryStr = "Annually on " + tvRepeatsOn.getText() + " , " + etOccurancesNumber.getText() + " times";//TODO- Show only day.
                    } else {
                        repeatSummuryStr = "Every " + spinnerRepeatEvery.getSelectedItem()
                                + "years on " + tvRepeatsOn.getText() + " , " + etOccurancesNumber.getText() + " times";
                    }
                }
            } else if (rgEnds.getCheckedRadioButtonId() == R.id.radioRepeatOn) {
                if (spinnerRepeats.getSelectedItem().toString().equalsIgnoreCase("Daily")) {
                    if (Integer.parseInt(spinnerRepeatEvery.getSelectedItem().toString()) == 1) {
                        repeatSummuryStr = "Daily," + " until " + etOnRepeat.getText();
                    } else {
                        repeatSummuryStr = "Every " + spinnerRepeatEvery.getSelectedItem() +
                                " days,until" + etOnRepeat.getText();
                    }
                } else if (spinnerRepeats.getSelectedItem().toString().equalsIgnoreCase("Weekly")) {
                    String weekdays = " ";
                    boolean flag = false;
                    for (int i = 0; i < weekDayArr.size(); i++) {
                        if (weekDayArr.get(i).isChecked()) {
                            if (flag == false) {
                                weekdays = " " + weekDayArr.get(i).getTag().toString();
                                flag = true;
                            } else {
                                weekdays += ", " + weekDayArr.get(i).getTag().toString();
                            }
                        }
                    }
                    weekdays.replace(" ,", "");
                    if (Integer.parseInt(spinnerRepeatEvery.getSelectedItem().toString()) == 1) {
                        repeatSummuryStr = "Weekly on" + weekdays + ", until" + etOnRepeat.getText();
                    } else {
                        repeatSummuryStr = "Every " + spinnerRepeatEvery.getSelectedItem() +
                                " weeks on " + weekdays + ", until" + etOnRepeat.getText();
                    }
                } else if (spinnerRepeats.getSelectedItem().toString().equalsIgnoreCase("Monthly")) {
                    if (rgMonthlyRepeatBy.getCheckedRadioButtonId() == R.id.rbDayOfMonth) {
                        if (Integer.parseInt(spinnerRepeatEvery.getSelectedItem().toString()) == 1) {
                            repeatSummuryStr = "Monthly on day" + tvRepeatsOn.getText() + ", until " + etOnRepeat.getText();//TODO- Show only day.
                        } else {
                            repeatSummuryStr = "Every " + spinnerRepeatEvery.getSelectedItem() + "months, on day"
                                    + tvRepeatsOn.getText() + ", until" + etOnRepeat.getText();
                        }
                    } else if (rgMonthlyRepeatBy.getCheckedRadioButtonId() == R.id.rbDayOfWeek) {
                        if (Integer.parseInt(spinnerRepeatEvery.getSelectedItem().toString()) == 1) {
                            repeatSummuryStr = "Monthly on day" + tvRepeatsOn.getText() + " WEDNESDAY, until " + etOnRepeat.getText();//TODO- Show only day.
                        } else {
                            repeatSummuryStr = "Every " + spinnerRepeatEvery.getSelectedItem()
                                    + "months on the " + tvRepeatsOn.getText() + " WEDNESDAY, until" + etOnRepeat.getText();
                        }
                    }
                } else if (spinnerRepeats.getSelectedItem().toString().equalsIgnoreCase("Yearly")) {
                    if (Integer.parseInt(spinnerRepeatEvery.getSelectedItem().toString()) == 1) {
                        repeatSummuryStr = "Annually on " + tvRepeatsOn.getText() + ", until " + etOnRepeat.getText();//TODO- Show only day.
                    } else {
                        repeatSummuryStr = "Every " + spinnerRepeatEvery.getSelectedItem()
                                + "years on " + tvRepeatsOn.getText() + ", until " + etOnRepeat.getText();
                    }
                }
            }
        }
    }

    /************************
     * Show calendar dialog.*
     ************************/
    private void showCalendarDialog() {
        Calendar calendar = Calendar.getInstance();
        dialogDatePicker = DatePickerFragmentDialog.newInstance(this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialogDatePicker.setMinDate(System.currentTimeMillis() - 1000);

        Calendar calendar1 = Calendar.getInstance();
        try {
            calendar1.setTime(format1.parse(dateStr2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dialogDatePicker.setMaxDate(calendar1);
        fragmentManager = getSupportFragmentManager();
        dialogDatePicker.show(fragmentManager, "tag");
    }

    /*******************************************
     * Set date values to particular text view.*
     *******************************************/
    private void setDates() {
        rlCalendarView.setVisibility(View.GONE);
        //Date c= (Date) Calendar.getInstance().getTime();
        dateStr = format.format(eventDay.getCalendar().getTime());
        dateStr1 = format1.format(eventDay.getCalendar().getTime());
        dateStr2 = format1.format(eventDay.getCalendar().getTime());

        if (rlCalendarView.getTag().toString().equalsIgnoreCase("start")) {
            tvStartDateVal.setText("" + dateStr1);
        } else if (rlCalendarView.getTag().toString().equalsIgnoreCase("end")) {
            tvEndDateVal.setText("" + dateStr2);
        } else if (rlCalendarView.getTag().toString().equalsIgnoreCase("dr")) {
            tvDrDateTime.setText("" + dateStr);
        } else if (rlCalendarView.getTag().toString().equalsIgnoreCase("medicine")) {
            tvmedicineDateTime.setText("" + dateStr);
        }
    }

    @Override
    public void onDayClick(EventDay eventDay) {
        this.eventDay = eventDay;

    }

    /*********************************
     * Show one time reminder dialog.*
     *********************************/
    private void showOnTimeSummary() {
        if (!etOneTimeNumber.getText().toString()
                .equalsIgnoreCase("")) {
            int oneSchedule = Integer.parseInt(etOneTimeNumber.getText().toString());
            Calendar calendar = Calendar.getInstance();
            try {
                if(date!=null) {
                    calendar.setTime(format1.parse(date));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
           /* calendar.set(Calendar.DAY_OF_MONTH, calendarSchedule.get(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.MONTH, calendarSchedule.get(Calendar.MONTH));
            calendar.set(Calendar.YEAR, calendarSchedule.get(Calendar.YEAR));*/
            if (spinnerSchedule.getSelectedItem().toString().equalsIgnoreCase("Days")) {
                calendar.add(Calendar.DAY_OF_MONTH, +oneSchedule);

            } else if (spinnerSchedule.getSelectedItem().toString()
                    .equalsIgnoreCase("Hours")) {
                calendar.add(Calendar.HOUR_OF_DAY, +oneSchedule);
            }
            schedules = format.format(calendar.getTime());
            oneTimeSummary.add(schedules);
            if (adapter == null) {
                adapter = new OneTimeSumamryAdapter(SetReminderActivity.this, oneTimeSummary);
                rvScheduleOneTime.setLayoutManager(new LinearLayoutManager(SetReminderActivity.this));
                rvOnetimeSummary.setLayoutManager(new LinearLayoutManager(SetReminderActivity.this));
                rvScheduleOneTime.setAdapter(adapter);
                rvOnetimeSummary.setAdapter(adapter);
            } else {
                adapter.updateList(oneTimeSummary);
            }
        }
    }

    public void showOneTimeRemDialog() {
        final Dialog dialog = new Dialog(SetReminderActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.onetime_rem_layout);

        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    @Override
    public void onDateSet(DatePickerFragmentDialog view, int year, int monthOfYear, int dayOfMonth) {

        Calendar calendarReminderDateandTime = Calendar.getInstance();
        calendarReminderDateandTime.set(Calendar.YEAR, year);
        calendarReminderDateandTime.set(Calendar.MONTH, (monthOfYear));
        calendarReminderDateandTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        /*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(calendar.getTime());*/
        date = format.format(calendarReminderDateandTime.getTime());
        tvReminderDateTimeIOneTime.setText("" + date);

        dialogDatePicker.dismiss();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(Activity.RESULT_CANCELED, intent);
        super.onBackPressed();
    }


    private void toast(String msg) {
        Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show();
    }

}