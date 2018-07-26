package com.dogratech.indusbuddyapp.main.componentclasses;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.baseactivities.BaseActivity;
import com.dogratech.indusbuddyapp.main.adapters.ReasonsAdapter;
import com.dogratech.indusbuddyapp.main.managers.FontManager;
import com.dogratech.indusbuddyapp.main.managers.SharedPrefsManager;
import com.dogratech.indusbuddyapp.main.retrofit.ApiClient;
import com.dogratech.indusbuddyapp.main.retrofit.ApiInterfaceGet;
import com.dogratech.indusbuddyapp.main.retrofit.ApiInterfacePost;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GetAppointmentComponents extends BaseActivity implements DatePickerDialog
        .OnDateSetListener ,TimePickerDialog.OnTimeSetListener {
    protected static final String TAG = GetAppointmentComponents.class.getName();
    private Calendar mCalendar;
    protected DatePickerDialog dpd;
    protected TimePickerDialog tpd;
    protected String centreCode = "";
    protected TextView tvDateAndTime,tvPackageName,tvAppointmentNumber,tvCentreName,tvClose ;
    private String dateTimeStr= "";
    protected TextView tvCalendarIcon,tvBeneficiaryIcon,tvCentreIcon,tvBeneficiaryName,tvBeneficiaryRelation;
    protected LinearLayout loutCheckCentre ,llSchedule;
    private SharedPrefsManager prefsManager ;
    protected ApiInterfacePost interface_post ;
    protected ApiInterfaceGet interface_get ;
    protected String cityId,apptNumber;
    protected String financial = "";
    protected RelativeLayout rlReasons;
    protected RecyclerView rvReasons;
    protected ReasonsAdapter reasonsAdapter;

    protected void initialiseClass() {
        interface_post     = ApiClient.getClient(ApiClient.BASE_URL_TYEP_MOBILE).create(ApiInterfacePost.class);
        interface_get     = ApiClient.getClient(ApiClient.BASE_URL_TYEP_MOBILE).create(ApiInterfaceGet.class);
        prefsManager       = SharedPrefsManager.getSharedInstance(getApplicationContext());
    }

    protected void initializeToolBar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar() . setHomeButtonEnabled(true); // disable the button
        getSupportActionBar() . setDisplayHomeAsUpEnabled(true); // remove the left caret
        getSupportActionBar() . setDisplayShowHomeEnabled(true);
        getSupportActionBar() . setTitle("Schedule Appointment");
    }

    protected void initialize() {
        tvPackageName     = findViewById(R.id.tvPackageName);
        tvDateAndTime     = findViewById(R.id.tvDateAndTime);
        tvCalendarIcon    = findViewById(R.id.tvCalendarIcon);
        tvBeneficiaryIcon = findViewById(R.id.tvBeneficiaryIcon);
        tvCentreIcon      = findViewById(R.id.tvCentreIcon);
        loutCheckCentre   = findViewById(R.id.loutCheckCentre);
        llSchedule        = findViewById(R.id.llSchedule);
        tvBeneficiaryName     = findViewById(R.id.tvBeneficiaryName);
        tvBeneficiaryRelation = findViewById(R.id.tvBeneficiaryRelation);
        tvAppointmentNumber   = findViewById(R.id.tvAppointmentNumber);
        tvCentreName          = findViewById(R.id.tvCentreName);
        rlReasons             = findViewById(R.id.rlReasons);
        rvReasons             = findViewById(R.id.rvReasons);
        tvClose               = findViewById(R.id.tvClose);

        tvCalendarIcon    .setTypeface(FontManager.getTypeface(this,FontManager.FONTAWESOME));
        tvBeneficiaryIcon .setTypeface(FontManager.getTypeface(this,FontManager.FONTAWESOME));
        tvCentreIcon      .setTypeface(FontManager.getTypeface(this,FontManager.FONTAWESOME));


        mCalendar = Calendar.getInstance();
        dpd = DatePickerDialog.newInstance(
                GetAppointmentComponents.this,
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)
        );
        tpd = TimePickerDialog.newInstance(
                GetAppointmentComponents.this,
                mCalendar.get(Calendar.HOUR_OF_DAY),
                mCalendar.get(Calendar.MINUTE),
                false);

                setExistingData();

    }

    private void setExistingData() {
        Bundle bundle = getIntent().getExtras();
        tvPackageName.setText(bundle.getString("package").replace(":",""));
        tvDateAndTime.setText(bundle.getString("date"));
        cityId = bundle.getString("cityId");
        cityId = bundle.getString("financial");
        apptNumber = bundle.getString("number").replace(":","");
        tvAppointmentNumber.setText("Appt No : "+apptNumber);
        String bName = bundle.getString("beneficiary");
        if (!bName.isEmpty()){
            bName = bName.replace(":","");
            bName = bName.replace("-","");
        }
        tvBeneficiaryName.setText(bName);
        String bRelation = bundle.getString("relation");
        if (!bRelation.isEmpty()){
            bRelation = bRelation.replace(":","");
        }
        tvBeneficiaryRelation.setText(bRelation);
        String centre = bundle.getString("centre");
        if (!centre.isEmpty()){
            centre = centre.replace(":","");
        }
        tvCentreName.setText(centre);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
      //  tpd.show(getFragmentManager(), "Datepickerdialog");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,monthOfYear);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String monthName = new SimpleDateFormat("MMM").format(c.getTime());
        dateTimeStr= "Updated Date : "+dayOfMonth+"-"+monthName+"-"+year;
        tvDateAndTime.setText(dateTimeStr);
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
       // dateTimeStr+=" "+hourOfDay+":"+minute;
    }



    protected void mToast(String msg){
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
    }
}
