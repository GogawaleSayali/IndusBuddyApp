package com.dogratech.indusbuddyapp.main.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.healthguide.guidefragments.HealthReportsFragment;
import com.dogratech.indusbuddyapp.main.managers.FontManager;
import com.dogratech.indusbuddyapp.main.models.Model_Item_Report;
import com.dogratech.indusbuddyapp.main.retrofit.ApiUrl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by akshaya on 12/3/18.
 */

public class ReportAdapter extends BaseAdapter {
    private Context context;
    private HealthReportsFragment storeRecords;
    private ArrayList<Model_Item_Report> data = new ArrayList<>();
    public static Typeface normal_font;
    private String filepath ;
    public ReportAdapter(Context context, HealthReportsFragment storeRecords,
                         ArrayList<Model_Item_Report> data,String filepath) {
        this.context = context;
        this.storeRecords = storeRecords;
        this.data = data;
        filepath  = filepath ;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_report_file, parent, false);
        }
        TextView tv_fileIcon = convertView.findViewById(R.id.tv_fileIcon);
        TextView tv_fileName = convertView.findViewById(R.id.tv_fileName);
        TextView tv_share    = convertView.findViewById(R.id.tv_share);
        TextView tv_uploadedBy = convertView.findViewById(R.id.tv_uploadedBy);
        TextView tv_uploadedDate = convertView.findViewById(R.id.tv_uploadedDate);
        tv_fileIcon          . setTypeface(FontManager.getTypeface(context,FontManager.FONTAWESOME));
        tv_share             . setTypeface(FontManager.getTypeface(context,FontManager.FONTAWESOME));

        tv_fileName          . setText(data.get(position).getFilePath());
        tv_uploadedBy        . setText(data.get(position).getAddedBy());
        tv_uploadedDate      . setText(data.get(position).getAddedOn());

        tv_share .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //storeRecords.shareReport();
                String fileName = data.get(position).getFilePath();
                final String image = ApiUrl.Base_URL_INDUS+"viewReport/report/"+fileName;
                try {
                    if (fileName.contains("pdf")){
                        new DownloadFile().execute(image,fileName);
                    }else if (fileName.contains("jpg") ||fileName.contains("jpeg") ||fileName.contains("png")){
                        new BitmapLoader().execute(image,fileName,fileName);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return convertView;
    }
    public class BitmapLoader extends AsyncTask<String, Void ,Bitmap>{
        String fileName = "",comment = "";
        AlertDialog alertDialog =null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custom_loader, null);
            dialogBuilder.setView(dialogView);
            alertDialog = dialogBuilder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.setCancelable(false);
            alertDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String[] urls) {
            URL url = null;
            Bitmap bitmap = null;
            fileName = urls[1];
            comment =  urls[2];
            try {
                url = new URL(urls[0]);
                bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (alertDialog!=null){
                if (alertDialog.isShowing()){
                    alertDialog.dismiss();
                }
            }
            if (bitmap!=null){
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, comment);
                String path = MediaStore.Images.Media.insertImage
                        (context.getContentResolver(), bitmap, "", null);
                Uri screenshotUri = Uri.parse(path);
                intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                intent.setType("image/*");
                context.startActivity(Intent.createChooser(intent, "Share image via..."));
            }
        }
    }



    private class DownloadFile extends AsyncTask<String, Void, File> {
        AlertDialog alertDialog =null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custom_loader, null);
            dialogBuilder.setView(dialogView);
            alertDialog = dialogBuilder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.setCancelable(false);
            alertDialog.show();
        }

        @Override
        protected File doInBackground(String... strings) {
            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
            String fileName = strings[1];  // -> maven.pdf
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "testthreepdf");
            folder.mkdir();

            File pdfFile = new File(folder, fileName+".pdf");

            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            return RecordViewShareAdapter.FileDownloader.downloadFile(fileUrl, pdfFile);
        }

        @Override
        protected void onPostExecute(File aVoid) {
            super.onPostExecute(aVoid);
            if (alertDialog!=null){
                if (alertDialog.isShowing()){
                    alertDialog.dismiss();
                }
            }
            if (aVoid!=null){
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("application/pdf");
                    intent.putExtra(Intent.EXTRA_TEXT, aVoid.getName());
                    intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+aVoid.getAbsolutePath()));
                    context.startActivity(Intent.createChooser(intent, "Share File via..."));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}
