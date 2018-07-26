package com.dogratech.indusbuddyapp.main.activities.storerecord;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.baseactivities.BaseActivity;
import com.dogratech.indusbuddyapp.main.helper.Constatnts;
import com.dogratech.indusbuddyapp.main.managers.SharedPrefsManager;
import com.dogratech.indusbuddyapp.main.models.Model_Response_Report;
import com.dogratech.indusbuddyapp.main.retrofit.ApiClient;
import com.dogratech.indusbuddyapp.main.retrofit.ApiInterfacePost;
import com.dogratech.indusbuddyapp.main.uitility.NetworkUtility;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocTypeActivity extends BaseActivity implements View.OnClickListener {
    private ImageView ivTestOne,ivTestTwo,ivSuitcase,ivPrescription;
    private TextView tvBackBtn,tvNextBtn;
    private RadioGroup rgDocType;
    private RadioButton radioPrescription,radioPathology;
    private ApiInterfacePost interface_post ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_type);
        Toolbar toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Fill in few details");
        initialize();
    }

    private void initialize() {
        ivTestOne  = findViewById(R.id.ivTestOne);
        ivTestTwo  = findViewById(R.id.ivTestTwo);
        ivSuitcase = findViewById(R.id.ivSuitcase);
        ivPrescription = findViewById(R.id.ivPrescription);
        tvBackBtn  = findViewById(R.id.tvBackBtn);
        tvNextBtn  = findViewById(R.id.tvNextBtn);
        rgDocType  = findViewById(R.id.rgDocType);

        changeImageColor("white",ivPrescription);
        changeImageColor("white",ivTestOne);
        changeImageColor("white",ivTestTwo);
        changeImageColor("white",ivSuitcase);

        setListeners();
    }

    private void setListeners() {
        tvBackBtn.setOnClickListener(this);
        tvNextBtn.setOnClickListener(this);
    }

    /************************************
     * Fill image with color.           *
     * @param color : color tb be filled*
     * @param imageView : ImageView     *
     ************************************/
    private void changeImageColor(String color,ImageView imageView){
        if (color.equalsIgnoreCase("orange")) {
            imageView.setColorFilter(getResources().getColor(R.color.colorOrange), PorterDuff.Mode.SRC_IN);
        }else if (color.equalsIgnoreCase("white")) {
            imageView.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);
        }else if (color.equalsIgnoreCase("brown")) {
            imageView.setColorFilter(getResources().getColor(R.color.colorBrownDark), PorterDuff.Mode.SRC_IN);
        }else if (color.equalsIgnoreCase("blue")) {
            imageView.setColorFilter(getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_IN);
        }else if (color.equalsIgnoreCase("green")) {
            imageView.setColorFilter(getResources().getColor(R.color.colorGreenDark), PorterDuff.Mode.SRC_IN);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvBackBtn:
                onBackPressed();
                break;
            case R.id.tvNextBtn:
                try {
                    String filePath = getIntent().getStringExtra("filePath");
                    File file = new File(filePath);
                    setFilePathToServer(file);
                }catch (Exception e){
                    e.printStackTrace();
                }
              //  startActivity(new Intent(DocTypeActivity.this,NamingReportActivity.class));
                break;
        }
    }

       private void setFilePathToServer(File realPath) {
        if (NetworkUtility.isNetworkAvailable(DocTypeActivity.this)){
           if (rgDocType.getCheckedRadioButtonId() != -1) {
               RadioButton radioButton = findViewById(rgDocType.getCheckedRadioButtonId());
               AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(DocTypeActivity.this);
               LayoutInflater inflater = this.getLayoutInflater();
               View dialogView = inflater.inflate(R.layout.custom_loader, null);
               dialogBuilder.setView(dialogView);
               final AlertDialog alertDialog = dialogBuilder.create();
               alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
               alertDialog.setCancelable(false);
               alertDialog.show();
               String userId = SharedPrefsManager.getSharedInstance(DocTypeActivity.this).getData(getString(R.string.shars_userid));
               long file_length = realPath.length();
               // if(file_length<FILE_SIZE) {
               interface_post = ApiClient.getClient(ApiClient.BASE_URL_TYEP_INDUS).create(ApiInterfacePost.class);
               RequestBody UserID = RequestBody.create(MultipartBody.FORM, userId);
               RequestBody comment = RequestBody.create(MultipartBody.FORM, ""+radioButton.getText().toString());
                   try {
                       RequestBody requeFile = RequestBody.create(MediaType.parse("multipart/form-data"), realPath);
                       MultipartBody.Part garden_fileToUpload = MultipartBody.Part.createFormData("file", realPath.getName(), requeFile);
                       interface_post.uploadSelfReport(UserID, comment, garden_fileToUpload).enqueue(new Callback<Model_Response_Report>() {
                           @Override
                           public void onResponse(Call<Model_Response_Report> call, Response<Model_Response_Report> response) {
                               Log.d("LOG", response.message());
                               try {
                                   if (alertDialog.isShowing()) {
                                       alertDialog.hide();
                                   }
                                   if (response.isSuccessful()) {
                                       if (!(response.body() == null)) {
                                           Model_Response_Report report = response.body();
                                           if (report.getStatus_code() == Constatnts.S_CODE_0) {
                                               Log.d("LOG", report.getMsg());
                                               Intent intent = new Intent();
                                               setResult(RESULT_OK,intent);
                                               finish();
                                           }
                                           Toast.makeText(DocTypeActivity.this, "" + report.getMsg(), Toast.LENGTH_SHORT).show();
                                       }
                                   } else {
                                       Toast.makeText(DocTypeActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                                   }
                               } catch (Exception e) {
                                   e.printStackTrace();
                               }
                           }

                           @Override
                           public void onFailure(Call<Model_Response_Report> call, Throwable t) {
                               Log.d("TAG", t.toString());
                               if (alertDialog.isShowing()) {
                                   alertDialog.hide();
                               }
                           }
                       });
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }
           }else {
            snackInternet();
        }

       }

    public void snackInternet(){
        Snackbar snackbar = Snackbar
                .make(tvNextBtn, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
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


    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED,intent);
        finish();
    }
}
