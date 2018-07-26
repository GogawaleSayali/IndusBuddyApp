package com.dogratech.indusbuddyapp.main.activities.reminder;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.baseactivities.BaseActivity;
import com.dogratech.indusbuddyapp.main.activities.healthcheckup.GetAppointmentActivity;
import com.dogratech.indusbuddyapp.main.adapters.OneTimeSumamryAdapter;
import com.dogratech.indusbuddyapp.main.adapters.ReasonsAdapter;
import com.dogratech.indusbuddyapp.main.helper.Constatnts;
import com.dogratech.indusbuddyapp.main.models.ModelPostPoneReasonRes;
import com.dogratech.indusbuddyapp.main.uitility.NetworkUtility;
import com.shagi.materialdatepicker.date.DatePickerFragmentDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetReminderActivity extends BaseActivity implements View.OnClickListener,
        OnDayClickListener ,DatePickerFragmentDialog.OnDateSetListener {
    private EditText etRemTitle, etRemFor, etMobile, etEmail, etOneTimeNumber, etOccurancesNumber, etOnRepeat;
    private TextView tvCancel, tvOk, tvCancelOneTime, tvCancelRepeat, tvDone, tvAddOnTimeSchedule, tvDoneRepeat,
            tvReminderDateTimeIOneTime, tvStartDateVal, tvEndDateVal, tvDrDateTime, tvmedicineDateTime,
            tvRepeatEvery, tvRepeatsOn, tvRepeatSumm, tvSubmitReminder;
    private Spinner spinnerCategory, spinnerSchedule, spinnerRepeats, spinnerRepeatEvery;
    private LinearLayout llStartDate, llEndDate, llDrConsult, llExecise, llMedicine, llLabTest, llOther,
            llRepeatWeeklyOn, llMonthlyRepeatBy, llOneTime, llRepeat;
    private RelativeLayout rlCalendarView, rlRepeat;
    private CalendarView calendarView;
    private EventDay eventDay = null;
    private RadioGroup rgEnds, rgMonthlyRepeatBy;
    private RadioButton rBOneTime, rBRepeat;
    private RelativeLayout rlOneTime;
    private RecyclerView rvScheduleOneTime;
    private DatePickerFragmentDialog dialogDatePicker;
    private FragmentManager fragmentManager;
    private Calendar calendarSchedule;
    private OneTimeSumamryAdapter adapter;
    private ArrayList<String> oneTimeSummary = new ArrayList<>();
    private int pos = -1;
    private String repeatSummuryStr = "";
    private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    ArrayList<CheckBox> weekDayArr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_reminder);
        Toolbar toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar, "Set Reminder");
        initialize();
        //requestSetReminderData();

    }


    public boolean validate() {
        boolean valid = true;

        String email = etEmail.getText().toString();
        String mobile = etMobile.getText().toString();

        if (email.isEmpty() && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Enter a valid email address");
            requestFocus(etEmail);
            valid = false;
        } else {
            etEmail.setError(null);
        }

        if (mobile.isEmpty() && mobile.length() != 10) {
            etMobile.setError("Enter valid Mobile no");
            requestFocus(etMobile);
            valid = false;
        } else {

            etMobile.setError(null);
        }

        return valid;
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
        //interface_get= ApiClient.getClient(ApiClient.BASE_URL_TYEP_INDUS).create(ApiInterfaceGet.class);
        tvSubmitReminder = findViewById(R.id.btn_submitReminder);
        etRemTitle = findViewById(R.id.etRemTitle);
        etRemFor = findViewById(R.id.etRemFor);
        etMobile = findViewById(R.id.etMobile);
        etEmail = findViewById(R.id.etEmail);
        etOneTimeNumber = findViewById(R.id.etOneTimeNumber);
        etOccurancesNumber = findViewById(R.id.etOccurancesNumber);
        etOnRepeat = findViewById(R.id.etOnRepeat);

        tvOk = findViewById(R.id.tvOk);
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
        tvRepeatsOn = findViewById(R.id.tvRepeatsOn);
        tvReminderDateTimeIOneTime = findViewById(R.id.tvReminderDateTimeIOneTime);

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

        rlCalendarView = findViewById(R.id.rlCalendarView);
        rlRepeat = findViewById(R.id.rlRepeat);
        rlOneTime = findViewById(R.id.rlOneTime);

        llOneTime = findViewById(R.id.llOneTime);
        llRepeat = findViewById(R.id.llRepeat);
        rgMonthlyRepeatBy = findViewById(R.id.rgMonthlyRepeatBy);
        rgEnds = findViewById(R.id.rgEnds);

        rBOneTime = findViewById(R.id.rBOneTime);
        rBRepeat = findViewById(R.id.rBRepeat);

        rvScheduleOneTime = findViewById(R.id.rvScheduleOneTime);
        //Global Calendar instance
        calendarView = findViewById(R.id.calendarView);
        calendarSchedule = Calendar.getInstance();
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
        tvOk.setOnClickListener(this);
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
                if (validate()) {
                    saveReminderOnServer();
                }
                break;
            case R.id.tvAddOnTimeSchedule:
                showOnTimeSummary();
                break;
            case R.id.llStartDate:
                rlCalendarView.clearFocus();
                rlCalendarView.setVisibility(View.VISIBLE);
                rlCalendarView.setTag("start");
                break;
            case R.id.llEndDate:
                rlCalendarView.setVisibility(View.VISIBLE);
                rlCalendarView.setTag("end");
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
                rlOneTime.setVisibility(View.GONE);
                rlRepeat.setVisibility(View.GONE);
                break;
            case R.id.tvDoneRepeat:
                rlOneTime.setVisibility(View.GONE);
                rlRepeat.setVisibility(View.GONE);
                setSymmaryText();
                tvRepeatSumm.setText("Summary\n" + repeatSummuryStr);
                break;
            case R.id.tvDrDateTime:
                rlCalendarView.setVisibility(View.VISIBLE);
                rlCalendarView.setTag("dr");
                break;
            case R.id.tvmedicineDateTime:
                rlCalendarView.setVisibility(View.VISIBLE);
                rlCalendarView.setTag("medicine");
                break;
            case R.id.tvReminderDateTimeIOneTime:
                showCalendarDialog();
                break;
            case R.id.llOneTime:
                rBRepeat.setChecked(false);
                if (rBOneTime.isChecked()) {
                    rlRepeat.setVisibility(View.GONE);
                    rlOneTime.setVisibility(View.VISIBLE);
                } else {
                    rBOneTime.setChecked(true);
                    rlOneTime.setVisibility(View.VISIBLE);
                    String currentDate = format.format(calendarSchedule.getTime());
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

    private void saveReminderOnServer()
    {
        //TODO - On success - >

       /* if (NetworkUtility.isNetworkAvailable(getApplicationContext())) {
            interface_get.getPostPoneReason()
                    .enqueue(new Callback<ModelPostPoneReasonRes>() {
                        @Override
                        public void onResponse(Call<ModelPostPoneReasonRes> call,
                                               Response<ModelPostPoneReasonRes> response) {
                            try {
                                if (response.isSuccessful()) {
                                    if (response.body() != null) {
                                        ModelPostPoneReasonRes reasonRes = response.body();
                                        if (reasonRes.getStatusCode() == Constatnts.S_CODE_0) {
                                            if (reasonRes.getModelReason().size() > 0) {
                                                rlReasons.setVisibility(View.VISIBLE);
                                                reasonsAdapter = new ReasonsAdapter(
                                                        SetReminderActivity.this, reasonRes.getModelReason());
                                                rvReasons.setLayoutManager(
                                                        new LinearLayoutManager(SetReminderActivity.this));
                                                rvReasons.setAdapter(reasonsAdapter);
                                            }
                                        } else {
                                            mToast("Error:" + reasonRes.getErrorCode());
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ModelPostPoneReasonRes> call, Throwable t) {
                            Log.d(TAG, t.toString());
                        }
                    });
        } else {
            Toast.makeText(this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }


        /*Intent intent = new Intent();
        setResult(Activity.RESULT_OK,intent);
        finish();*/
    }

        /*Intent intent = new Intent();
        setResult(Activity.RESULT_OK,intent);
        finish();*/




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
        fragmentManager = getSupportFragmentManager();
        dialogDatePicker.show(fragmentManager, "tag");
    }

    /*******************************************
     * Set date values to particular text view.*
     *******************************************/
    private void setDates() {
        rlCalendarView.setVisibility(View.GONE);
        String dateStr = format.format(eventDay.getCalendar().getTime());
        if (rlCalendarView.getTag().toString().equalsIgnoreCase("start")) {
            tvStartDateVal.setText("" + dateStr);
        } else if (rlCalendarView.getTag().toString().equalsIgnoreCase("end")) {
            tvEndDateVal.setText("" + dateStr);
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
            calendar.set(Calendar.DAY_OF_MONTH, calendarSchedule.get(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.MONTH, calendarSchedule.get(Calendar.MONTH));
            calendar.set(Calendar.YEAR, calendarSchedule.get(Calendar.YEAR));
            if (spinnerSchedule.getSelectedItem().toString().equalsIgnoreCase("Days")) {
                calendar.add(Calendar.DAY_OF_MONTH, -oneSchedule);

            } else if (spinnerSchedule.getSelectedItem().toString()
                    .equalsIgnoreCase("Hours")) {
                calendar.add(Calendar.HOUR_OF_DAY, -oneSchedule);
            }
            String schedules = format.format(calendar.getTime());
            oneTimeSummary.add(schedules);
            if (adapter == null) {
                adapter = new OneTimeSumamryAdapter(SetReminderActivity.this, oneTimeSummary);
                rvScheduleOneTime.setLayoutManager(new LinearLayoutManager(SetReminderActivity.this));
                rvScheduleOneTime.setAdapter(adapter);
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
        calendarSchedule.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendarSchedule.set(Calendar.MONTH, monthOfYear);
        calendarSchedule.set(Calendar.YEAR, year);

        String date = format.format(calendarSchedule.getTime());
        tvReminderDateTimeIOneTime.setText("" + date);


        dialogDatePicker.dismiss();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(Activity.RESULT_CANCELED,intent);
        super.onBackPressed();
    }
}