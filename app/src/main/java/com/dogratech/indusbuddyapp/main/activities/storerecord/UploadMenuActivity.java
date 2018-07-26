package com.dogratech.indusbuddyapp.main.activities.storerecord;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.baseactivities.BaseActivity;
import com.dogratech.indusbuddyapp.main.helper.FilePath;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class UploadMenuActivity extends BaseActivity implements View.OnClickListener {
    private final String LOG = this.getClass().getName();
    private static int CAMERA_REQUEST = 0;
    private static int GALLERY_REQUEST = 1;
    private static int PICKFILE_REQUEST = 2;
    private static int UPLOAD_FINISH = 3;
    private int selectedImage = -1;
    private TextView tvBackBtn, tvNextBtn,tvFileName,tvClose;
    private ImageView ivImage;
    private LinearLayout llCamera,llGallery,llFile;
    private String filePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Store Record");
        initialize();
    }

    /**
     * Initialize all the ui widgets.
     */
    private void initialize() {
        tvBackBtn = findViewById(R.id.tvBackBtn);
        tvNextBtn = findViewById(R.id.tvNextBtn);
        tvFileName= findViewById(R.id.tvFileName);
        tvClose   = findViewById(R.id.tvClose);
        llCamera  = findViewById(R.id.llCamera);
        llGallery = findViewById(R.id.llGallery);
        llFile    = findViewById(R.id.llFile);
        ivImage   = findViewById(R.id.ivImage);
        setListeners();
    }

    /**
     * Set listeners to the widgets like "onClick"
     */
    private void setListeners() {
        tvBackBtn .setOnClickListener(this);
        tvNextBtn .setOnClickListener(this);
        llCamera  .setOnClickListener(this);
        llGallery .setOnClickListener(this);
        llFile    .setOnClickListener(this);
        tvClose   .setOnClickListener(this);
        ivImage   .setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvClose:
                filePath  = "";
                ivImage   .setVisibility(View.GONE);
                tvClose   .setVisibility(View.GONE);
                tvFileName.setVisibility(View.GONE);
                break;
            case R.id.llFile:
                selectFile();
                break;
            case R.id.llCamera:
                cameraIntent(CAMERA_REQUEST);
                break;
            case R.id.llGallery:
                galleryIntent(GALLERY_REQUEST);
                break;
            case R.id.tvBackBtn:
                finish();
                break;

            case R.id.tvNextBtn:
                if (!filePath.equalsIgnoreCase("")) {
                    Intent intent = new Intent(UploadMenuActivity.this, DocTypeActivity.class);
                    intent.putExtra("filePath", filePath);
                    startActivityForResult(intent,UPLOAD_FINISH);
                }else {
                    Toast.makeText(this, "Please select the file/Image", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /*********------------------------------------------*
     * Start the camera to capture the documents photo.*
     * @param request : camera request code.            *
     *------------------------------------------*********/
    private void cameraIntent(int request) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, request);
    }

    /********__________________________________________
     * Open photo gallery to select the document photo*
     * @param request : gallery request code          *
     *_________________________________________********/
    private void galleryIntent(int request) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent , request);//one can be replaced with any action code
    }

    /**************************
     * Select file from folder *
     **************************/
    private void selectFile(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        //intent.setType("*/*");
        startActivityForResult(intent,PICKFILE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == UPLOAD_FINISH) {
                finish();
            }else if (requestCode == GALLERY_REQUEST) {
                ivImage.setVisibility(View.VISIBLE);
                tvFileName.setVisibility(View.VISIBLE);
                tvClose.setVisibility(View.VISIBLE);
                Uri imgUri = data.getData();
                filePath = getRealPathFromURI(imgUri);
                File file = new File(filePath);
                if (file.getName().contains("png") || file.getName().contains("jpg") ||
                        file.getName().contains("jpeg")){
                    if(file.exists()){
                        Bitmap myBitmap = BitmapFactory.decodeFile(filePath);
                        ivImage.setImageBitmap(myBitmap);
                    }
                }
                tvFileName.setText(new File(filePath).getName());
            } else if (requestCode == CAMERA_REQUEST) {
                ivImage.setVisibility(View.VISIBLE);
                tvFileName.setVisibility(View.VISIBLE);
                tvClose.setVisibility(View.VISIBLE);
                onCaptureImageResult(data, selectedImage);
            } else if(requestCode == PICKFILE_REQUEST) {
                ivImage.setVisibility(View.VISIBLE);
                tvFileName.setVisibility(View.VISIBLE);
                tvClose.setVisibility(View.VISIBLE);
                Uri selectedFileUri = data.getData();
                String selectedFilePath = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    selectedFilePath = FilePath.getPath(UploadMenuActivity.this,selectedFileUri);
                }else{
                   //TODO - select file from folder if device op less than Kitkat.
                }
                Log.e(LOG,"Selected File Path:" + selectedFilePath);
                filePath = selectedFilePath;
                File file = new File(filePath);
                if (file.getName().contains("pdf")) {
                    ivImage.setImageResource(R.drawable.pdf);
                    tvFileName.setText(file.getName());
                }
            }
        }
    }

    /**=================================
     * Get the real path form the file.=
     * @param uri : file uri           =
     * @return : file path             =
     *================================**/
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

    /*******************************************
     * After capturing image fit the image file. *
     * @param data : intent data return by camera.*
     * @param request : camera request code.     *
     *******************************************/
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
        Uri path = getImageUri(UploadMenuActivity.this,thumbnail);
        filePath = getRealPathFromURI(path);
        File file = new File(filePath);
        if (file.getName().contains("png") || file.getName().contains("jpg") ||file.getName().contains("jpeg")){
            if(file.exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(filePath);
                ivImage.setImageBitmap(myBitmap);
            }
        }
    }

    /***********************************
    * Get Image uri from bitmap.         *
   * @param inContext : Activity context. *
    * @param inImage : bitmap.           *
     * @return                          *
       *********************************/
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(),
                inImage, "Title", null);
        return Uri.parse(path);
    }
}
