package com.dogratech.indusbuddyapp.main.activities.navmenuactivities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.apphomeactivity.AppHomeActivity;
import com.dogratech.indusbuddyapp.main.activities.baseactivities.BaseActivityNoMenu;
import com.dogratech.indusbuddyapp.main.helper.Constatnts;
import com.dogratech.indusbuddyapp.main.managers.FontManager;
import com.dogratech.indusbuddyapp.main.managers.SharedPrefsManager;
import com.dogratech.indusbuddyapp.main.models.MemberDetails;
import com.dogratech.indusbuddyapp.main.models.MemberDetrailsModel;
import com.dogratech.indusbuddyapp.main.models.Model_Response;
import com.dogratech.indusbuddyapp.main.models.Model_Response_Report;
import com.dogratech.indusbuddyapp.main.retrofit.ApiClient;
import com.dogratech.indusbuddyapp.main.retrofit.ApiInterfaceGet;
import com.dogratech.indusbuddyapp.main.retrofit.ApiInterfacePost;
import com.dogratech.indusbuddyapp.main.retrofit.ApiUrl;
import com.dogratech.indusbuddyapp.main.uitility.DeviceUtility;
import com.dogratech.indusbuddyapp.main.uitility.NetworkUtility;
import com.dogratech.indusbuddyapp.main.uitility.PermissionUtility;
import com.jackandphantom.circularimageview.CircleImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import jp.wasabeef.blurry.Blurry;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dogratech.indusbuddyapp.main.uitility.Commman.changeDateFormat;

public class UserProfileActivity extends BaseActivityNoMenu {
    private static int CAMERA_REQUEST = 0;
    private static int GALLERY_REQUEST = 1;
    private CardView cardIVP;
    private CircleImage circleImage;
    private LinearLayout llCover;
    private View viewLine;
    private TextView tvGenderIcon, tvDobIcon, tvMobileIcon;
    private SharedPrefsManager prefsManager;
    private TextView tvUserName, tvIVPs, tvGender, tvDOB, tvMobile, tvEmail;
    private ImageView ivBlurImage, ivChangeImage, ivnormal;
    final CharSequence[] items = {"Take Photo", "Choose from Gallery", "Cancel"};
    public String userChoosenTask = "";
    private SharedPrefsManager preferences;
    private int selectedImage = -1;
    private ApiInterfaceGet interface_get;
    private LinearLayout llParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        initToolBar();
        initialize();
        setListeners();
        // getMememberDetails();
    }

    private void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar, "Profile");
    }

    private void initialize() {
        interface_get = ApiClient.getClient(ApiClient.BASE_URL_TYEP_INDUS).create(ApiInterfaceGet.class);
        preferences = SharedPrefsManager.getSharedInstance(UserProfileActivity.this);
        cardIVP = findViewById(R.id.cardIVP);
        viewLine = findViewById(R.id.viewLine);
        tvGenderIcon = findViewById(R.id.tvGenderIcon);
        tvDobIcon = findViewById(R.id.tvDobIcon);
        tvMobileIcon = findViewById(R.id.tvMobileIcon);

        tvUserName = findViewById(R.id.tvProfUserName);
        tvMobile = findViewById(R.id.tvMobile);
        tvEmail = findViewById(R.id.tvEmail);
        tvGender = findViewById(R.id.tvGender);
        tvIVPs = findViewById(R.id.tvIVPs);
        tvDOB = findViewById(R.id.tvDOB);
        ivBlurImage = findViewById(R.id.ivBlurImage);
        ivChangeImage = findViewById(R.id.ivChangeImage);
        ivnormal = findViewById(R.id.ivnormal);
        circleImage = findViewById(R.id.circleImage);
        llCover = findViewById(R.id.llCover);
        llParent = findViewById(R.id.llParent);

        prefsManager = SharedPrefsManager.getSharedInstance(UserProfileActivity.this);

        tvGenderIcon.setTypeface(FontManager.getTypeface(UserProfileActivity.this,
                FontManager.FONTAWESOME));
        tvDobIcon.setTypeface(FontManager.getTypeface(UserProfileActivity.this,
                FontManager.FONTAWESOME));
        tvMobileIcon.setTypeface(FontManager.getTypeface(UserProfileActivity.this,
                FontManager.FONTAWESOME));

        tvUserName.setText(prefsManager.getData(getString(R.string.username)) + " " +
                prefsManager.getData(getString(R.string.userLName)));
        tvMobile.setText(prefsManager.getData(getString(R.string.userMobile)));


        cardIVP.post(new Runnable() {
            @Override
            public void run() {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) cardIVP.getLayoutParams();
                if (!AppHomeActivity.ROLE.equalsIgnoreCase("c")) {
                    RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) cardIVP.getLayoutParams();
                    params1.height = 0;
                    DeviceUtility.convertDpToPx(UserProfileActivity.this, 0);
                    cardIVP.setLayoutParams(params1);
                    cardIVP.setVisibility(View.INVISIBLE);
                } else {
                    params.height = DeviceUtility.convertDpToPx(UserProfileActivity.this, 65);
                    cardIVP.setLayoutParams(params);
                    cardIVP.setVisibility(View.VISIBLE);
                }
            }
        });

        setUserDetails();
    }

    private void setUserDetails() {
        String fName = preferences.getData(getString(R.string.username));
        String lName = preferences.getData(getString(R.string.userLName));
        tvUserName.setText(fName + " " + lName);
        tvMobile.setText(preferences.getData(getString(R.string.userMobile)));
        tvGender.setText(preferences.getData(getString(R.string.userGender)));
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
        Calendar calendar = Calendar.getInstance();
        String dob = preferences.getData(getString(R.string.userDOB));


        tvDOB.setText(changeDateFormat(dob));
        tvEmail.setText(preferences.getData(getString(R.string.userEmail)));
        String url = ApiUrl.Base_URL_INDUS + "viewReport/profile/" + preferences.getData(getString(R.string.userProfile));
        Glide.with(UserProfileActivity.this).load(url).into(circleImage);
        Glide.with(UserProfileActivity.this).load(url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                ivnormal.post(new Runnable() {
                    @Override
                    public void run() {
                        Blurry.with(UserProfileActivity.this)
                                .radius(25)
                                .sampling(1)
                                .color(Color.argb(80, 0, 0, 0))
                                .async()
                                .capture(findViewById(R.id.ivnormal))
                                .into((ImageView) findViewById(R.id.ivBlurImage));
                    }
                });
                return false;
            }
        }).into(ivnormal);
    }

    private void setListeners() {
        ivChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        tvMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                String p = "tel:" + tvMobile.getText().toString().trim();
                i.setData(Uri.parse(p));
                startActivity(i);
            }
        });
    }

    private void getMememberDetails() {
        String userId = SharedPrefsManager.getSharedInstance(UserProfileActivity.this).getData(getString(R.string.shars_userid));
        //userId = "226305"; // demo userid
        String url = ApiUrl.Base_URL_MOBILE + ApiUrl.GetMemberDetails + userId;
        ApiInterfaceGet interfaceGet = ApiClient.getClient(ApiClient.BASE_URL_TYEP_MOBILE).create(ApiInterfaceGet.class);
        if (NetworkUtility.isNetworkAvailable(UserProfileActivity.this)) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(UserProfileActivity.this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custom_loader, null);
            dialogBuilder.setView(dialogView);
            final AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.setCancelable(false);
            alertDialog.show();
            interfaceGet.getMemberDetails(url).enqueue(new Callback<MemberDetrailsModel>() {
                @Override
                public void onResponse(Call<MemberDetrailsModel> call, Response<MemberDetrailsModel> response) {
                    if (alertDialog.isShowing()) {
                        alertDialog.hide();
                    }
                    if (response.body() != null) {
                        MemberDetrailsModel detrailsModel = response.body();
                        if (detrailsModel.getStatusCode() == Constatnts.S_CODE_0) {
                            ArrayList<MemberDetails> memberDetailsLst = detrailsModel.getMemberDetails();
                            if (memberDetailsLst != null) {
                                if (memberDetailsLst.size() > 0) {
                                    MemberDetails memberDetails = memberDetailsLst.get(0);
                                    tvIVPs.setText(memberDetails.getIvp());
                                    if (!memberDetails.getGender().equalsIgnoreCase(""))
                                        tvGender.setText(memberDetails.getGender());
                                    if (memberDetails.getPhone().length() == 10) {
                                        tvMobile.setText("+91 " + memberDetails.getPhone());
                                    } else {
                                        if (!memberDetails.getPhone().equalsIgnoreCase(""))
                                            tvMobile.setText(memberDetails.getPhone());
                                    }
                                    if (!memberDetails.getEmail().equalsIgnoreCase(""))
                                        tvEmail.setText(memberDetails.getEmail());
                                }
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<MemberDetrailsModel> call, Throwable t) {
                    if (alertDialog.isShowing()) {
                        alertDialog.hide();
                    }
                    Log.e("UserProfile", "" + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            snackInternet();
        }
    }

    public void snackInternet() {
        Snackbar snackbar = Snackbar
                .make(llParent, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
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


    public void getUserDetails() {
        String userId = preferences.getData(getString(R.string.shars_userid));
        if (NetworkUtility.isNetworkAvailable(getApplicationContext())) {
            String url = ApiUrl.Base_URL_INDUS + ApiUrl.getClientByEHRId + userId + "/N";
            interface_get.getClientByEHRId(url).enqueue(new Callback<Model_Response>() {
                @Override
                public void onResponse(Call<Model_Response> call, Response<Model_Response> response) {
                    try {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Model_Response model_response = response.body();
                                if (model_response.getStatus_code().equalsIgnoreCase(getString(R.string.statuse_code_0))) {
                                    Log.e("LOG", "Response id =====" + model_response.
                                            getClientDetails().getClientId() + "\n" +
                                            model_response.getClientDetails().getOtp());
                                    // if (Constatnts.MLM_WITH_PKG.equalsIgnoreCase(Constatnts.USERROLE)) {
                                    preferences.setData(getString(R.string.shars_userid),
                                            model_response.getClientDetails().getClientId());
                                    preferences.setData(getString(R.string.username), model_response.getClientDetails().getFirstName());
                                    preferences.setData(getString(R.string.userLName), model_response.getClientDetails().getLastName());
                                    preferences.setData(getString(R.string.userMobile), model_response.getClientDetails().getMobileNumber());
                                    preferences.setData(getString(R.string.userType), model_response.getClientDetails().getUserType());
                                    preferences.setData(getString(R.string.userGender), model_response.getClientDetails().getGender());
                                    preferences.setData(getString(R.string.userDOB), model_response.getClientDetails().getDob());
                                    preferences.setData(getString(R.string.userEmail), model_response.getClientDetails().getEmailId());
                                    preferences.setData(getString(R.string.userProfile), model_response.getClientDetails().getProfilePicture());
                                    String url = ApiUrl.Base_URL_INDUS + "viewReport/profile/" + preferences.getData(getString(R.string.userProfile));
                                    Glide.with(UserProfileActivity.this).load(url).into(circleImage);
                                    Glide.with(UserProfileActivity.this).load(url).listener(new RequestListener<Drawable>() {
                                        @Override
                                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                            ivnormal.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Blurry.with(UserProfileActivity.this)
                                                            .radius(25)
                                                            .sampling(1)
                                                            .color(Color.argb(80, 0, 0, 0))
                                                            .async()
                                                            .capture(findViewById(R.id.ivnormal))
                                                            .into((ImageView) findViewById(R.id.ivBlurImage));
                                                }
                                            });
                                            return false;
                                        }
                                    }).into(ivnormal);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<Model_Response> call, Throwable t) {
                    Log.d("LOG", t.toString());
                }
            });
        } else {
            snackInternet();
        }
    }


    private void selectImage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UserProfileActivity.this, R.style.DialogTheme);
        builder.setTitle(getString(R.string.Camera_Add_Photo));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = PermissionUtility.checkPermission(UserProfileActivity.this);
                if (items[item].equals(getString(R.string.Camera_Take_Photo))) {
                    if (result) {
                        userChoosenTask = getString(R.string.Camera_Take_Photo);
                        cameraIntent(CAMERA_REQUEST);
                    }
                } else if (items[item].equals(getString(R.string.Choose_from_Gallery))) {
                    userChoosenTask = getString(R.string.Choose_from_Gallery);
                    if (result)
                        galleryIntent(GALLERY_REQUEST);
                } else if (items[item].equals(getString(R.string.Camera_Cancel))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void cameraIntent(int request) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, request);
    }

    private void galleryIntent(int request) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, request);//one can be replaced with any action code
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY_REQUEST) {
                onSelectFromGalleryResult(data, selectedImage);
                Uri imgUri = data.getData();
                File rFile = new File(getRealPathFromURI(imgUri));
                File fPath = new File(rFile.getPath());
                setFilePathToServer(fPath);
            } else if (requestCode == CAMERA_REQUEST) {
                onCaptureImageResult(data, selectedImage);
            }
        }
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data, int request) {
        Bitmap bm = null;
        if (data != null) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().
                        openInputStream(imageUri);
                bm = BitmapFactory.decodeStream(imageStream);

                //setImageToImageview(request, bm,imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void onCaptureImageResult(Intent data, int request) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        // thumbnail                   . compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri path = getImageUri(UserProfileActivity.this, thumbnail);
        File rFile = new File(getRealPathFromURI(path));
        File fPath = new File(rFile.getPath());
        setFilePathToServer(fPath);
    }

    private void setFilePathToServer(final File realPath) {
        if (NetworkUtility.isNetworkAvailable(UserProfileActivity.this)) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(UserProfileActivity.this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custom_loader, null);
            dialogBuilder.setView(dialogView);
            final AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.setCancelable(false);
            alertDialog.show();
            SharedPrefsManager prefsManager = SharedPrefsManager
                    .getSharedInstance(UserProfileActivity.this);
            String userId = prefsManager.getData(getString(R.string.shars_userid));
            ApiInterfacePost interfacePost = ApiClient.getClient(ApiClient.BASE_URL_TYEP_INDUS).create(ApiInterfacePost.class);
            RequestBody UserID = RequestBody.create(MultipartBody.FORM, userId);
            RequestBody requeFile = RequestBody.create(MediaType.parse("multipart/form-data"), realPath);
            MultipartBody.Part garden_fileToUpload = MultipartBody.Part.createFormData("file", realPath.getName(), requeFile);

            try {
                interfacePost.uploadProfilePic(UserID, garden_fileToUpload).enqueue(new Callback<Model_Response_Report>() {

                    @Override
                    public void onResponse(Call<Model_Response_Report> call, Response<Model_Response_Report> response) {
                        Log.d("LOG", response.message());
                        try {
                            if (alertDialog.isShowing()) {
                                alertDialog.hide();
                            }
                            if (response.isSuccessful()) {
                                if (!(response.body() == null)) {
                                    preferences.setData(getString(R.string.userProfile), realPath.getName());
                                    getUserDetails();
                                    Model_Response_Report report = response.body();
                                    if (report.getStatus_code() == Constatnts.S_CODE_0) {
                                        Log.d("LOG", report.getMsg());
                                    }
                                    Toast.makeText(UserProfileActivity.this, "" + report.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(UserProfileActivity.this, "" + response.message(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(UserProfileActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            snackInternet();
        }
    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(),
                inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        int idx = 0;
        try {
            if (cursor != null) {
                cursor.moveToFirst();
            }
            if (cursor != null) {
                idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursor.getString(idx);
    }


}
