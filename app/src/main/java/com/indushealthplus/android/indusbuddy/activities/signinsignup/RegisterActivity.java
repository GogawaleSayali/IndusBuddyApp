package com.indushealthplus.android.indusbuddy.activities.signinsignup;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.adapters.GenderAdapter;
import com.indushealthplus.android.indusbuddy.models.Model_Response;
import com.indushealthplus.android.indusbuddy.models.ModelUserDetails;
import com.indushealthplus.android.indusbuddy.retrofit.ApiClient;
import com.indushealthplus.android.indusbuddy.retrofit.ApiInterfacePost;
import com.indushealthplus.android.indusbuddy.uitility.DeviceUtility;
import com.indushealthplus.android.indusbuddy.uitility.NetworkUtility;
import com.indushealthplus.android.indusbuddy.uitility.StringUtitlity;
import com.shagi.materialdatepicker.date.DatePickerFragmentDialog;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
    * Registration page for user
    *
    * */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,
        DatePickerFragmentDialog.OnDateSetListener {
    private String LOG = RegisterActivity.class.getName();
    private EditText et_firstname ,et_lastname ,et_dob ,et_mobile,et_email;
    private Button btnRegistration;
    private String fName, lName, mobile , email , dob;
    private RelativeLayout lout_main;
    private ApiInterfacePost interface_post;
    private Spinner spinnerGender ;
    private TextView tvGenderSelect ;
    private int Day,Year,Month;
    private DatePickerFragmentDialog dialogDatePicker;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setToolBar();
        DeviceUtility.hideKeyBord(RegisterActivity.this);
        initialise();
        setListener();
    }

    private void setToolBar() {
        getSupportActionBar().setHomeButtonEnabled(true); // disable the button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // remove the left caret
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Verification");
    }

    /****************************************
     * find view from xml and initialise it *
      ****************************************/
    private void initialise(){
        interface_post = ApiClient.getClient(ApiClient.BASE_URL_TYEP_INDUS).create(ApiInterfacePost.class);
        et_firstname   = findViewById(R.id.et_firstname);
        et_lastname    = findViewById(R.id.et_lastname);
        et_dob         = findViewById(R.id.et_dob);
        et_mobile      = findViewById(R.id.et_mobile);
        et_email       = findViewById(R.id.et_email);
        btnRegistration= findViewById(R.id.btn_registration);
        lout_main      = findViewById(R.id.Lout_Main);
        tvGenderSelect = findViewById(R.id.tvGenderSelect);
        spinnerGender  = findViewById(R.id.spinnerGender);

        /*Gender spinner data feed*/
        ArrayList<String> items = new ArrayList<>();
        items.add("Select Gender");
        items.add("Male");
        items.add("Female");
        items.add("Other");
        GenderAdapter genderAdapter = new GenderAdapter(RegisterActivity.this,R.layout.gender_item,items);
        spinnerGender.setAdapter(genderAdapter);

        /*Date picker dialog*/
        Calendar calendar = Calendar.getInstance();
        Year  = calendar.get(Calendar.YEAR) ;
        Month = calendar.get(Calendar.MONTH);
        Day   = calendar.get(Calendar.DAY_OF_MONTH);
        dialogDatePicker = DatePickerFragmentDialog.newInstance(this,Year-15,Month,Day);
        dialogDatePicker.setMaxDate(System.currentTimeMillis());
        dialogDatePicker.showYearPickerFirst(true);
        fragmentManager = getSupportFragmentManager();
    }

   /*****************************************
    **provide event of clicking , selecting**
    *****************************************/
    private void setListener() {
        btnRegistration.   setOnClickListener(this);
        et_dob             .   setOnClickListener(this);
        tvGenderSelect     .   setOnClickListener(this);
        spinnerGender      .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ite = parent.getItemAtPosition(position).toString();
                tvGenderSelect.setText(ite);
                spinnerGender.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerGender.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_registration :
                 isValid();
                 break;
            case R.id.et_dob :
                 dateFromDatePicker(1);
                 break;
            case R.id.tvGenderSelect :
                    spinnerGender.setVisibility(View.VISIBLE);
                    spinnerGender.performClick();
                 break;
            default:
                 break;
        }
    }

      /*********************************************
      **redirection provide on click using intent**
     *********************************************/
    private void redirection(int pos){
        switch (pos){
            case 1: Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    break;
            default:
                    break;
        }
    }

    /*******************************************
      **select date for DOB in dd-mm-yyyy format**
       ********************************************/
    private void dateFromDatePicker(final  int request){
        dialogDatePicker.show(fragmentManager, "tag");
    }

    private void setDate(int year , String mon, String dy){
        String month = isMinute(mon);
        String day = isMinute(dy);
        et_dob.setText(year+ "-"+month+ "-"+day);
        et_dob.setError(null);
    }

   /**************************************
    * checking number <2 digit to add '0'*
    **************************************/
    private String isMinute(String minutes){
        if(minutes.length()<2){
            minutes = "0"+minutes;
        }else{
            minutes = minutes;
        }
        return minutes;
    }

   /************************************
    * get text from text view in string *
    ************************************/
    private void getText(){
        fName = et_firstname. getText().toString().trim();
        lName = et_lastname . getText().toString().trim();
        dob         = et_dob      . getText().toString().trim();
        mobile      = et_mobile   . getText().toString().trim();
        email       = et_email    . getText().toString().trim();
    }

   /**********************************************
    * validation for fName,lName,dob,gender,email*
    **********************************************/
    private void isValid(){
        boolean bfName  = false;
        boolean blName  = false;
        boolean bDob    = false;
        boolean bMobile = false;
        boolean bEmail  = false;
        boolean bGender = false;
        getText();
        if(fName.equalsIgnoreCase("") || fName.length()==0){
            et_firstname.setError(getString(R.string.register_name_empty));
            et_firstname.requestFocus();
        }else{
            bfName = true;
        }

        if(lName.equalsIgnoreCase("") || lName.length()==0){
            et_lastname.setError(getString(R.string.register_name_empty));
            et_lastname.requestFocus();
        }else{
            blName = true;
        }

        if(dob.equalsIgnoreCase("") || dob.length()==0){
            et_dob.setError(getString(R.string.register_empty_date));
            et_dob.requestFocus();
        }else{
            bDob = true;
        }

        if(mobile.equalsIgnoreCase("") || mobile.length()==0){
            et_mobile.setError(getString(R.string.register_empty_mobile));
            et_mobile.requestFocus();
        }else if(mobile.length()<10 || mobile.length()!=10){
            et_mobile.setError(getString(R.string.register_invalid_mobile));
            et_mobile.requestFocus();
        }else{
            bMobile = true;
        }
        if(email.equalsIgnoreCase("") || email.length()==0){
            et_email.setError(getString(R.string.register_empty_email));
            et_email.requestFocus();
        }else if(!StringUtitlity.isEmailValid(email)){
            et_email.setError(getString(R.string.register_invalid_email));
            et_email.requestFocus();
        }else{
            bEmail = true;
        }
        if(bMobile || bEmail){
            et_email.setError(null);
            et_mobile.setError(null);
        }

       /* TextView errorText = (TextView)spinnerGender.getSelectedView();
        if(spinnerGender.getSelectedItem().equals("Select Gender"))
        {
            errorText.setError("anything here, just to add the icon");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Pick one of them");//changes the selected item text to this
            errorText.setError(null);
        }*/

        String gender;
        if(spinnerGender.getSelectedItem().toString().equalsIgnoreCase("Male")){
            bGender = true;
            gender = "Male";
        }else  if(spinnerGender.getSelectedItem().toString().equalsIgnoreCase("Female")){
            bGender = true;
            gender = "Female";
        }else if(spinnerGender.getSelectedItem().toString().equalsIgnoreCase("Other")){
            gender = "Other";
            bGender = true;
        }else{
            gender= "";
            bGender = false;
        }

        if(bfName && blName && bDob && bGender){
            if(bMobile)
            {
                registerUser(fName, lName,dob,mobile,gender,true);
            }else if(bEmail)
            {
                registerUser(fName, lName,dob,email,gender,false);
            }
        }
    }



    /*
    * rgex for number validation
    * */
    public boolean isValidPhoneNumber(String number){
        boolean contact = false ;
        if (number.matches("[0-9]+") && number.length() > 2) {
            contact  = true ;
        }
        else{
            contact = false;
        }
        return contact ;
    }

    /*
    * request for server to register user
    * */

    private void registerUser(String fName, String lName, String dob, String eMobile, String gender,
                              boolean isMobile){
        if(NetworkUtility.isNetworkAvailable(getApplicationContext())){
            char s = gender.toString().trim().charAt(0);
            ModelUserDetails details = new ModelUserDetails();
           /* if(et_firstname.equals(fName))
            {
                details.setFirstName(fName);
            }*/
            details.setFirstName(fName);
            details.setLastName(lName);
           /*if(et_lastname.equals(lName)) {
                details.setLastName(lName);
            }*/
            details.setGender(""+s);
            details.setDob(dob);
            if(isMobile){
                details.setMobileNumber("91"+eMobile);
                details.setEmailId("");
            }else {
                details.setEmailId(eMobile);
                details.setMobileNumber("");
            }
            try {
                Call<Model_Response> userCall = interface_post.verifyDetails(details);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                LayoutInflater inflater = this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_loader, null);
                dialogBuilder.setView(dialogView);
                final AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.setCancelable(false);
                alertDialog.show();
                userCall.enqueue(new Callback<Model_Response>() {
                    @Override
                    public void onResponse(Call<Model_Response> call, Response<Model_Response> response) {
                        if (alertDialog.isShowing()){
                            alertDialog.hide();
                        }
                        try {
                            if (response.isSuccessful()) {
                                try {
                                    if (response.body() != null) {
                                        Model_Response modelResponse = response.body();
                                        if (modelResponse.getStatus_code().equalsIgnoreCase(getString(R.string.statuse_code_0))) {
                                            Toast(modelResponse.getMsg());
                                            redirection(1);
                                        } else {
                                            Toast(modelResponse.getMsg());
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
                    public void onFailure(Call<Model_Response> call, Throwable t) {
                        if (alertDialog.isShowing()){
                            alertDialog.hide();
                        }
                        Log.d(LOG, t.toString());
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            snackInternet();
        }
    }

   /**************************
    * Internet checking code *
    **************************/
    public void snackInternet(){
        Snackbar snackbar = Snackbar
                .make(lout_main, getString(R.string.no_internet), Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.RED);
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }

    /*********************
     * Show Toast Message*
     *********************/
    private void Toast(String msg){
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDateSet(DatePickerFragmentDialog view, int year, int monthOfYear, int dayOfMonth) {
        Year  = year;
        Month = monthOfYear;
        Day   = dayOfMonth;
        setDate(year,String.valueOf(monthOfYear+1),String.valueOf(dayOfMonth));
    }
}
