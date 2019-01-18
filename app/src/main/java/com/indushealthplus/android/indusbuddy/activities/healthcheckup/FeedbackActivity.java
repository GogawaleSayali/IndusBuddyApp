package com.indushealthplus.android.indusbuddy.activities.healthcheckup;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.baseactivities.BaseActivity;
import com.indushealthplus.android.indusbuddy.managers.SharedPrefsManager;
import com.indushealthplus.android.indusbuddy.retrofit.ApiClient;
import com.indushealthplus.android.indusbuddy.retrofit.ApiInterfacePost;
import com.indushealthplus.android.indusbuddy.uitility.DeviceUtility;
import com.indushealthplus.android.indusbuddy.uitility.NetworkUtility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class FeedbackActivity extends BaseActivity implements View.OnClickListener {
    private MaterialRatingBar ratingbar;
    private LinearLayout llRating;
    private TextView tvVideoUpload,fbClose;
    private EditText evFeedbackcomment;
    private TextView submitFeedback;
    private Float mRatingCount = 0.0f;
    private ImageView attachmentImgView;

    private static int CAMERA_REQUEST = 0;
    private int selectedVideo = -1;

    protected RelativeLayout rlGeneralInfo, rlServiceRelated, rlAdditionalInfo;
    protected LinearLayout llGeneralInfo, llServiceRelatedInfo, llAdditionalInfo;
    private WebView webView;
    private String terms = "https://www.indushealthplus.com/";

    private String filePath = "";
    private Uri uriVideo;
    String Videofilepath="";

   // private String terms = "https://paytm.com/fastag-dealer-tnc";
    private RelativeLayout rlProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initToolBar();
        initialize();
        setListeners();
    }

    private void setListeners() {
        rlGeneralInfo.setOnClickListener(this);
        rlServiceRelated.setOnClickListener(this);
        rlAdditionalInfo.setOnClickListener(this);
        llGeneralInfo.setOnClickListener(this);
        llServiceRelatedInfo.setOnClickListener(this);
        llAdditionalInfo.setOnClickListener(this);
        submitFeedback.setOnClickListener(this);
        tvVideoUpload.setOnClickListener(this);
        fbClose.setOnClickListener(this);

        ratingbar.setOnRatingChangeListener(new MaterialRatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
                if(rating == 0.0f){
                    submitFeedback.setEnabled(false);
                }else{
                    if (rating < 5.0f) {
                        llRating.setVisibility(View.VISIBLE);
                        tvVideoUpload.setVisibility(View.GONE);
                        attachmentImgView.setVisibility(View.GONE);
                    } else {
                        llRating.setVisibility(View.GONE);
                        tvVideoUpload.setVisibility(View.VISIBLE);
                        attachmentImgView.setVisibility(View.GONE);
                    }
                    Log.d("Feedback Rating :", "Rating nos outside :: " + rating);
                    mRatingCount = rating;
                    submitFeedback.setEnabled(true);
                }
            }
        });
    }

    private void initialize() {
        ratingbar = findViewById(R.id.ratingbar);
        llRating = findViewById(R.id.llRating);
        tvVideoUpload = findViewById(R.id.tvVideoUpload);
        rlGeneralInfo = findViewById(R.id.rlGeneralInfo);
        rlServiceRelated = findViewById(R.id.rlServiceRelated);
        rlAdditionalInfo = findViewById(R.id.rlAdditionalInfo);
        llGeneralInfo = findViewById(R.id.llGeneralInfo);
        llServiceRelatedInfo = findViewById(R.id.llServiceRelatedInfo);
        llAdditionalInfo = findViewById(R.id.llAdditionalInfo);
        rlProgress     = findViewById(R.id.rlProgress);
        submitFeedback = findViewById(R.id.btn_SubmitFeedback);
        submitFeedback.setEnabled(false);
        evFeedbackcomment = findViewById(R.id.et_goodservice);
        attachmentImgView   = findViewById(R.id.ivImage);
        fbClose   = findViewById(R.id.fbClose);
        DeviceUtility.hideKeyBord(FeedbackActivity.this);

        webView = findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                rlProgress.setVisibility(View.VISIBLE);
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
                if (rlProgress.getVisibility() == View.VISIBLE){ rlProgress.setVisibility(View.GONE);}
            }
        });


        webView.loadUrl(terms);


    }

    private void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar, "Feedback");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fbClose:
                Videofilepath  = "";
                attachmentImgView.setVisibility(View.GONE);
                fbClose.setVisibility(View.GONE);
                tvVideoUpload.setVisibility(View.VISIBLE);
                tvVideoUpload.setText(R.string.feedback_video_testimonial_text);
                break;
            case R.id.btn_SubmitFeedback:
                submitPackageFeedback();
                break;

            case R.id.tvVideoUpload:
                cameraIntent(CAMERA_REQUEST);

                break;

            case R.id.rlGeneralInfo:
                llGeneralInfo.setVisibility(View.VISIBLE);
                llAdditionalInfo.setVisibility(View.GONE);
                llServiceRelatedInfo.setVisibility(View.GONE);
                rlGeneralInfo.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                rlAdditionalInfo.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                rlServiceRelated.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case R.id.rlServiceRelated:
                llGeneralInfo.setVisibility(View.GONE);
                llAdditionalInfo.setVisibility(View.GONE);
                llServiceRelatedInfo.setVisibility(View.VISIBLE);
                rlGeneralInfo.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                rlAdditionalInfo.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                rlServiceRelated.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            case R.id.rlAdditionalInfo:
                llGeneralInfo.setVisibility(View.GONE);
                llAdditionalInfo.setVisibility(View.VISIBLE);
                llServiceRelatedInfo.setVisibility(View.GONE);
                rlGeneralInfo.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                rlAdditionalInfo.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                rlServiceRelated.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
        }
    }

    private void submitPackageFeedback(){
        String mComment = "";
        if(submitFeedback.isEnabled()){
            if(mRatingCount < 5.0f){

                 mComment = evFeedbackcomment. getText().toString().trim();
            }else{
                 mComment = "";
                 // Upload video testimonial :
                Log.d("FeedbackTag :", "Feedback VideoPath:: " + Videofilepath);
                if(Videofilepath != null){
                    File file = new File(Videofilepath);
                    uploadVideoToServer(file);
                }


            }
            Log.d("Feedback Rating :", "Rating nos :: submit button click" + mRatingCount + " and " + mComment);
        }
    }

    /*********------------------------------------------*
     * Start the camera to capture the documents photo.*
     * @param request : camera request code.            *
     *------------------------------------------*********/
    private void cameraIntent(int request) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        tvVideoUpload.setVisibility(View.GONE);
        startActivityForResult(intent, request);
    }

    public static Intent createTakeVideoIntent() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        Uri uri = getOutputVideoUri();  // create a file to save the video in specific folder
        if (uri != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); // set the video image quality to high

        return intent;
    }

    private static Uri getOutputVideoUri() {
        if (Environment.getExternalStorageState() == null) {
            return null;
        }

        File mediaStorage = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "YOUR_APP_VIDEO");
        if (!mediaStorage.exists() &&
                !mediaStorage.mkdirs()) {
            Log.e("FeedbackCtivity :: ", "failed to create directory: " + mediaStorage);
            return null;
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        File mediaFile = new File(mediaStorage, "VID_" + timeStamp + ".mp4");
        return Uri.fromFile(mediaFile);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            tvVideoUpload.setText(R.string.feedback_button_uploaded);
            if (requestCode == CAMERA_REQUEST) {
               samsungCaptureVideo(data,selectedVideo);
                // onCaptureVideoResult(data,selectedVideo);
            }
            Log.d("Feedback Rating :","Rating nos :: onActivityResult " + resultCode);
        }
    }

    private void samsungCaptureVideo(Intent data, int request) {
        uriVideo = data.getData();
      //  Constant.IS_FILE_ATTACH = true;
       // Toast.makeText(FeedbackActivity.this, uriVideo.getPath(), Toast.LENGTH_LONG).show();
        String[] filePathColumn = { MediaStore.Video.Media.DATA };

        Cursor cursor = getContentResolver().query(uriVideo, filePathColumn,null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        Videofilepath = cursor.getString(columnIndex);
        Log.d("TAG","Videofilepath filepath from camera : " + Videofilepath);
        cursor.close();
        File f = new File(Videofilepath);
        Log.d("TAG","file created ? : " + f.exists());

        Bitmap bMap = null;
        do {
            try {
                // Simulate network access.
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!f.exists());
        bMap = ThumbnailUtils.createVideoThumbnail(Videofilepath, MediaStore.Video.Thumbnails.MICRO_KIND);
        do {
            try {
                // Simulate network access.
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (bMap == null);
//        imageOrVideo = "video";
        attachmentImgView.setVisibility(View.VISIBLE);
        attachmentImgView.setImageBitmap(bMap);
        fbClose.setVisibility(View.VISIBLE);
    }

    private void uploadVideoToServer(File filePathforVideo) {
        if (NetworkUtility.isNetworkAvailable(FeedbackActivity.this)){
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(FeedbackActivity.this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custom_loader, null);
            dialogBuilder.setView(dialogView);
            final AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.setCancelable(false);
            alertDialog.show();

            SharedPrefsManager prefsManager = SharedPrefsManager
                    .getSharedInstance(FeedbackActivity.this);
            String userId = prefsManager.getData(getString(R.string.shars_userid));

            ApiInterfacePost interfacePost = ApiClient.getClient(ApiClient.BASE_URL_TYEP_INDUS).create(ApiInterfacePost.class);
            RequestBody UserID = RequestBody.create(MultipartBody.FORM, userId);
            RequestBody requeFile = RequestBody.create(MediaType.parse("multipart/form-data"), filePathforVideo);
            MultipartBody.Part garden_fileToUpload = MultipartBody.Part.createFormData("file", filePathforVideo.getName(), requeFile);


        }else {
            snackInternet();
        }
    }

    public void snackInternet(){
     /*   Snackbar snackbar = Snackbar
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
        snackbar.show();*/
    }
}
