package com.dogratech.indusbuddyapp.main.activities.signinsignup;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.apphomeactivity.AppHomeActivity;
import com.dogratech.indusbuddyapp.main.uitility.NetworkUtility;
import com.dogratech.indusbuddyapp.main.managers.SharedPrefsManager;
import com.dogratech.indusbuddyapp.main.models.Model_Response;
import com.dogratech.indusbuddyapp.main.retrofit.ApiClient;
import com.dogratech.indusbuddyapp.main.retrofit.ApiInterfaceGet;
import com.dogratech.indusbuddyapp.main.retrofit.ApiUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOtpActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    private Button btn_SubmitOTP  ,btn_ResendOtp;
    private EditText etOTP;//etOtpOne , etOtpTwo ,etOtpThree , etOtpFour ,etOtpFive ,etOtpSix;
    private Intent intent ;
    private TextView tvTitmerOtpNotReceive;
    private LinearLayout Lout_Main ;
    private String LOG = VerifyOtpActivity.class.getName();
    private CountDownTimer mCountDownTimer;
    private ApiInterfaceGet interface_get;
    private SharedPrefsManager preferences;
    private static int countDownTime = 300000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        getSupportActionBar().setHomeButtonEnabled(true); // disable the button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // remove the left caret
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initialise();
        initialiseClass();
        setListener();
        if(checkAndRequestPermissions()){

        }

        setTimer();
    }
    /*
    * find view from xml and initialise it
    * */
    private void initialise(){
        btn_SubmitOTP       = findViewById(R.id.btn_SubmitOTP);
        btn_ResendOtp       = findViewById(R.id.btn_ResendOtp);
        etOTP               = findViewById(R.id.etOTP);
        Lout_Main           = findViewById(R.id.Lout_Main);
        tvTitmerOtpNotReceive= findViewById(R.id.tvTitmerOtpNotReceive);
    }

    private  boolean checkAndRequestPermissions() {
        int permissionSendMessage = ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);

        int receiveSMS = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS);

        int readSMS = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_SMS);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (receiveSMS != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.RECEIVE_MMS);
        }
        if (readSMS != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_SMS);
        }
        if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.SEND_SMS);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    /*
    * Class initialisation
    * */
    private void initialiseClass(){
        interface_get      = ApiClient.getClient(ApiClient.BASE_URL_TYEP_INDUS).create(ApiInterfaceGet.class);
        preferences        = new SharedPrefsManager();
    }

    /*
     *provide event of clicking , selecting
     * */
    private void setListener() {
        btn_SubmitOTP       .   setOnClickListener(this);
        btn_ResendOtp       .   setOnClickListener(this);

    }

    /**
     * Hide Soft key board.
     */
    private void hideKeyBoard(){
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(etOTP.getWindowToken(), 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_SubmitOTP :
                if(isValidOTP()) {
                    //txt_OTPError.setVisibility(View.INVISIBLE);
                    if (mCountDownTimer!=null){
                        mCountDownTimer.cancel();
                        mCountDownTimer = null;
                    }
                }else{
                    invalidateFields();
                }
                 break;

            case R.id.btn_ResendOtp :
                    if(NetworkUtility.isNetworkAvailable(VerifyOtpActivity.this)) {
                        ResendOTPRequest();
                        setTimer();
                    }else{
                        snackInternet();
                    }
                 break;
            default:
                 break;
        }
    }

    private boolean isValidOTP() {
        String OTPStr = etOTP  . getText().toString().trim();
        if (OTPStr.toString().trim().length()==6){
            if(NetworkUtility.isNetworkAvailable(VerifyOtpActivity.this)) {
                VerifyOTPRequest(OTPStr);
            }else {
                snackInternet();
            }
            return true;
        }
        return false;
    }

    private void invalidateFields() {
        if (mCountDownTimer!=null){
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
        //txt_OTPTimer.setText("");
        //txt_OTPTimer.setVisibility(View.INVISIBLE);
       // btn_ResendOtp.setEnabled(true);
        //txt_OTPError.setVisibility(View.VISIBLE);
        clearOTP();
    }


    private void clearOTP() {
        etOTP.setText("");
    }


    /*
    * Verify OTP request
    * */
    private void VerifyOTPRequest(String OTPStr){
        Bundle bundle       = getIntent().getExtras();
        if(bundle!=null) {
            if (NetworkUtility.isNetworkAvailable(VerifyOtpActivity.this)) {
                String userid = preferences.getData(getString(R.string.shars_userid));
                String url = ApiUrl.Base_URL_INDUS + ApiUrl.verifyOTP + userid + "/" + OTPStr;
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                LayoutInflater inflater = this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_loader, null);
                dialogBuilder.setView(dialogView);
                final AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.setCancelable(false);
                alertDialog.show();
                interface_get.verifyOTP(url).enqueue(new Callback<Model_Response>() {
                    @Override
                    public void onResponse(Call<Model_Response> call, Response<Model_Response> response) {
                        if (alertDialog.isShowing()) {
                            alertDialog.hide();
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Model_Response model_response = response.body();
                                if (model_response.getStatus_code().equalsIgnoreCase(getString(R.string.statuse_code_0))) {
                                    redirection(1);
                                } else {
                                    Toast(model_response.getMsg());
                                    etOTP.setText("");
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Model_Response> call, Throwable t) {
                        if (alertDialog.isShowing()) {
                            alertDialog.hide();
                        }
                        Log.d(LOG, t.toString());
                    }
                });
            }else {
                snackInternet();
            }
        }
    }

    /*
    * Resend OTP
    * */
    public void ResendOTPRequest(){
        if (NetworkUtility.isNetworkAvailable(VerifyOtpActivity.this)) {
            Bundle extras = getIntent().getExtras();
            String userid = extras.getString(getString(R.string.shars_userid));
            String url = ApiUrl.Base_URL_INDUS + ApiUrl.resendOTP + "/" + userid;
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custom_loader, null);
            dialogBuilder.setView(dialogView);
            final AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.setCancelable(false);
            alertDialog.show();
            interface_get.resendOTP(url).enqueue(new Callback<Model_Response>() {
                @Override
                public void onResponse(Call<Model_Response> call, Response<Model_Response> response) {
                    if (alertDialog.isShowing()) {
                        alertDialog.hide();
                    }
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            Model_Response modelResponse = response.body();
                            if (modelResponse.getStatus_code().equalsIgnoreCase(getString(R.string.statuse_code_0))) {
                                Toast(modelResponse.getMsg());
                                Log.e(LOG, "Response id =====" + modelResponse.
                                        getClientDetails().getClientId() + "\n" +
                                        modelResponse.getClientDetails().getOtp());
                            } else {
                                Toast(response.body().getMsg());
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<Model_Response> call, Throwable t) {
                    if (alertDialog.isShowing()) {
                        alertDialog.hide();
                    }
                    Log.d(LOG, t.toString());
                }
            });
        }else{
            snackInternet();
        }
    }

    /*
    * redirection provide on click using intent
    * */
    private void redirection(int pos){
        switch (pos){
            case 1 : gotoHome();
                     break;
            default: break;
        }
    }

    private void gotoHome() {
        SharedPrefsManager sharedPrefsManager = SharedPrefsManager
                .getSharedInstance(VerifyOtpActivity.this);
        sharedPrefsManager.setData(getString(R.string.isLoggedin),"yes");
        intent = new Intent(VerifyOtpActivity.this ,AppHomeActivity.class);
        intent .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onResume() {
        LocalBroadcastManager.getInstance(this).
                registerReceiver(receiver, new IntentFilter("otp"));
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }
/*
    *//**
     * Sms auto read OTP functionality.
     *//*
    private void autoReadOtp() {
        SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {

            }
        });
    }*/

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase("otp")) {
                final String messageText = intent.getStringExtra("message");
                Log.d(LOG,"On sms received, Message : "+messageText);
                String smsCode = parseCode(messageText);
                setOtp(smsCode);
            }
        }
    };

    /**
     * Parse verification code
     *
     * @param message sms message
     * @return only four numbers from massage string
     */
    public static String parseCode(String message) {
        Pattern p = Pattern.compile("\\b\\d{6}\\b");
        Matcher m = p.matcher(message);
        String code = "";
        while (m.find()) {
            code = m.group(0);
        }
        return code;
    }

    /**
     * Set the OTP code to the edittext.
     * @param smsCode
     */
    private void setOtp(String smsCode) {
        if (!smsCode.equalsIgnoreCase("")) {
            if (etOTP !=null){
                etOTP.setText("" + smsCode);
                if(isValidOTP()) {
                    //txt_OTPError.setVisibility(View.INVISIBLE);
                    if (mCountDownTimer!=null){
                        mCountDownTimer.cancel();
                        mCountDownTimer = null;
                    }
                }else{
                    invalidateFields();
                }
            }
            removeFocuse();
        }
    }

    /**
     * Focus remove from the edittext.
     */
    private void removeFocuse() {
        etOTP.setFocusable(false);
        if (mCountDownTimer!=null){
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
        //txt_OTPTimer.setVisibility(View.INVISIBLE);
        //txt_OTPError.setVisibility(View.INVISIBLE);
        //btn_ResendOtp.setEnabled(false);
    }

    private void setTimer() {
        tvTitmerOtpNotReceive.setVisibility(View.VISIBLE);
        btn_ResendOtp.setVisibility(View.GONE);
        clearOTP();
        if (mCountDownTimer!=null){
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
        mCountDownTimer = new CountDownTimer(countDownTime, 1000) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000) % 60 ;
                int minutes = (int) ((millisUntilFinished / (1000*60)) % 60);
                 //tvTitmerOtpNotReceive.setText(""+minutes+" : "+seconds );
                if(seconds<10) {
                    tvTitmerOtpNotReceive.setText("0" + minutes + " : "+"0"+ seconds);
                }else{
                    tvTitmerOtpNotReceive.setText("0" + minutes + " : " + seconds);
                }
            }
            public void onFinish() {
                //txt_OTPTimer.setText("done!");
                tvTitmerOtpNotReceive.setText("Didn't receive OTP ?");
                btn_ResendOtp.setVisibility(View.VISIBLE);

            }
        }.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    /*
    * Internet checking code
    * */
    public void snackInternet(){
        Snackbar snackbar = Snackbar
                .make(Lout_Main, getString(R.string.netConnection), Snackbar.LENGTH_LONG)
                .setAction("RETRY", new View.OnClickListener() {
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

    private void Toast(String msg){
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
    }
}
