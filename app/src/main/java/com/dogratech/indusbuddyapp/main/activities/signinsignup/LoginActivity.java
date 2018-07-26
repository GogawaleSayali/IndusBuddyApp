package com.dogratech.indusbuddyapp.main.activities.signinsignup;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.baseactivities.BaseActivity;
import com.dogratech.indusbuddyapp.main.managers.SharedPrefsManager;
import com.dogratech.indusbuddyapp.main.models.Model_Response;
import com.dogratech.indusbuddyapp.main.retrofit.ApiClient;
import com.dogratech.indusbuddyapp.main.retrofit.ApiInterfaceGet;
import com.dogratech.indusbuddyapp.main.retrofit.ApiUrl;
import com.dogratech.indusbuddyapp.main.uitility.NetworkUtility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
    * Login activity provide login to user based on role
    *
    * */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_login_text ;
    private TextView txt_IconUser ;
    private Button btn_sendOtp ;
    private TextView txt_register ;
    private Intent intent ;
    private String[] PERMISSIONS = {
            Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.BODY_SENSORS};
    private int PERMISSION_ALL = 1;
    private ApiInterfaceGet interface_get;
    private static String LOG = LoginActivity.class.getName();
    private SharedPrefsManager preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkcomplatibilityMarshmallow();
        hideSoftKeyBoard();
        initialise();
        initialiseClass();
        setListener();
        if(!preferences.getData(getString(R.string.shars_userid)).equalsIgnoreCase("")){
           // startActivity(new Intent(LoginActivity.this,HomeComponents.class));
        }
       // startActivity(new Intent(LoginActivity.this,VerifyOtpActivity.class));
    }

    /*
    * find view from xml and initialise it
    * */
    private void initialise(){
        preferences   = SharedPrefsManager.getSharedInstance(LoginActivity.this);
        et_login_text = findViewById(R.id.et_login_text);
        txt_register  = findViewById(R.id.txt_register);
        btn_sendOtp   = findViewById(R.id.btn_sendOtp);
    }

    /*
    * provide event of clicking , selecting
    * */
    private void setListener() {
        btn_sendOtp .setOnClickListener(this);
        txt_register.setOnClickListener(this);
    }

    /*
    * hide the default keyboard once open the activity
    * */
    private void hideSoftKeyBoard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    /*
    * Class initialisation
    * */
    private void initialiseClass(){
        interface_get = ApiClient.getClient(ApiClient.BASE_URL_TYEP_INDUS).create(ApiInterfaceGet.class);
        preferences   = new SharedPrefsManager();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sendOtp :
                 isValid();
                 break;

            case R.id.txt_register :
                 redirection(2);
                 break;

                 default:
                    break;
        }
    }

    /*
    * redirection provide on click using intent
    * */
    private void redirection(int pos){
        switch (pos){
            case 2 : intent = new Intent(LoginActivity.this ,RegisterActivity.class);
                     startActivity(intent);
                     break;
            default:
                     break;
        }
    }

    /*
    * check validation for username as email or mobile
    * */
    private void isValid(){
        boolean emailid= false ;
        boolean mobile = false ;
        String userId  =  et_login_text.getText().toString().trim();

        if(userId.equalsIgnoreCase("") || userId.length()==0){
            et_login_text.setError(getString(R.string.login_empty_field));
            et_login_text.requestFocus();
        }else {
            /*if(isNumric(userId)){
                if(isEmailValid(userId)){
                    emailid = true;
                    mobile  = false;
                }else{
                    et_login_text.setError(getString(R.string.login_invalid_email));
                    et_login_text.requestFocus();
                }
            }else{
                if(isValidPhoneNumber(userId)) {
                    mobile  = true;
                    emailid = false;
                }else{
                    et_login_text.setError(getString(R.string.login_invalid_mobile));
                    et_login_text.requestFocus();
                }
            }*/
               doLogin(userId);
        }
    }

    /*
    * regex for check string having alphabets
    * */
    private boolean isNumric(String text){
        return  text.matches(".*[a-zA-Z]+.*");
    }

    /*
    * regex for email validation
    * */
    public boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())
            return true;
        else
            return false;
    }

    /*
    * regex for number validation
    * */
    public boolean isValidPhoneNumber(String number){
        boolean contact = false ;
        if (number.matches("[0-9]+") && number.length()==10) {
            contact  = true ;
        }
        else{
            contact = false;
        }
        return contact ;
    }

    /*
    * Internet checking code
    * */
    public void snackInternet(){
        Snackbar snackbar = Snackbar
                .make(txt_register, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
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


    /*Marshamallow permisions*/
        public void checkcomplatibilityMarshmallow() {
            if (Build.VERSION.SDK_INT >= 23) {
                // Marshmallow+
                if (!hasPermissions(this, PERMISSIONS)) {
                    ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
                }
                //  permission();
            } else {// Pre-Marshmallow
            }
        }

        public static boolean hasPermissions(Context context, String... permissions) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
                for (String permission : permissions) {
                    if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                        return false;
                    }
                }
            }
            return true;
        }

    private void Toast(String msg){
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
    }


       /***************************************
        * Server call for login and validation*
        ***************************************/

        public void doLogin(String userId){
            if(NetworkUtility.isNetworkAvailable(getApplicationContext())){
                String url = ApiUrl.Base_URL_INDUS + ApiUrl.getClientByEHRId+userId+"/Y";
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                LayoutInflater inflater = this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_loader, null);
                dialogBuilder.setView(dialogView);
                final AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.setCancelable(false);
                alertDialog.show();
                interface_get.getClientByEHRId(url).enqueue(new Callback<Model_Response>() {
                    @Override
                    public void onResponse(Call<Model_Response> call, Response<Model_Response> response) {
                        try {
                            if (response.isSuccessful()) {
                                if (alertDialog.isShowing()){
                                    alertDialog.hide();
                                }
                                if(response.body()!=null){
                                Model_Response model_response = response.body();
                                if(model_response.getStatus_code().equalsIgnoreCase(getString(R.string.statuse_code_0))) {
                                    Log.e(LOG,"Response id ====="+model_response.
                                            getClientDetails().getClientId()+"\n"+
                                            model_response.getClientDetails().getOtp());
                                   // if (Constatnts.MLM_WITH_PKG.equalsIgnoreCase(Constatnts.USERROLE)) {
                                        preferences.setData(getString(R.string.shars_userid),
                                                model_response.getClientDetails().getClientId());
                                        preferences.setData(getString(R.string.username),model_response.getClientDetails().getFirstName());
                                        preferences.setData(getString(R.string.userLName),model_response.getClientDetails().getLastName());
                                        preferences.setData(getString(R.string.userMobile),model_response.getClientDetails().getMobileNumber());
                                        preferences.setData(getString(R.string.userType),model_response.getClientDetails().getUserType());
                                        preferences.setData(getString(R.string.userGender),model_response.getClientDetails().getGender());
                                        preferences.setData(getString(R.string.userDOB),model_response.getClientDetails().getDob());
                                        preferences.setData(getString(R.string.userEmail),model_response.getClientDetails().getEmailId());
                                        preferences.setData(getString(R.string.userProfile),model_response.getClientDetails().getProfilePicture());
                                        intent = new Intent(LoginActivity.this, VerifyOtpActivity.class);
                                        intent.putExtra(getString(R.string.shars_user_otp),
                                                model_response.getClientDetails().getOtp());
                                        intent.putExtra(getString(R.string.shars_userid),
                                                model_response.getClientDetails().getClientId());
                                        startActivity(intent);
                                  //  }
                                }else{
                                    Toast(model_response.getMsg());
                                }
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<Model_Response> call, Throwable t) {
                        if (alertDialog.isShowing()){
                            alertDialog.hide();
                        }
                    Log.d(LOG,t.toString());
                        Toast.makeText(LoginActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                snackInternet();
            }
        }
}
