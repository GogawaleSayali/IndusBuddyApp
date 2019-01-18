package com.dogratech.indusbuddyapp.main.adapters;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.models.ModelRedordDetails;
import com.dogratech.indusbuddyapp.main.models.Model_Item_Report;
import com.dogratech.indusbuddyapp.main.retrofit.ApiUrl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amolr on 15/6/18.
 */

public class RecordViewShareAdapter extends RecyclerView.Adapter<RecordViewShareAdapter.MyViewHolder>{
    private Context mContext;
    private List<Model_Item_Report> packagesListFiltered;

    public void updateList(ArrayList<Model_Item_Report> modelCentre) {
        packagesListFiltered = modelCentre;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvFileName,tvRecordDate;
        public ImageView ivViewIcon,ivShareIcon;
        public CardView card_view;
        public ImageView ivFileIcon;

        public MyViewHolder(View view) {
            super(view);
            ivFileIcon =  view.findViewById(R.id.ivFileIcon);
            tvFileName =  view.findViewById(R.id.tvFileName);
            tvRecordDate  =  view.findViewById(R.id.tvRecordDate);
            card_view  =  view.findViewById(R.id.card_view);
            ivShareIcon  =  view.findViewById(R.id.ivShareIcon);
            ivViewIcon  =  view.findViewById(R.id.ivViewIcon);

        }
    }


    public RecordViewShareAdapter(Context mContext, List<Model_Item_Report> packages) {
        this.mContext  = mContext;
        this.packagesListFiltered = packages;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_records_share, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Model_Item_Report records = packagesListFiltered.get(position);
        holder.tvFileName.setText(records.getFilePath());
        holder.tvRecordDate.setText(records.getAddedOn());
        final String image = ApiUrl.Base_URL_INDUS+"viewReport/report/"+records.getFilePath();
        holder.ivShareIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String fileName = records.getFilePath();
                    if (fileName.contains("pdf")){
                        new DownloadFile().execute(image,records.getComment());
                    }else if (fileName.contains("jpg") ||fileName.contains("jpeg") ||fileName.contains("png")){
                        new BitmapLoader().execute(image,records.getFilePath(),records.getComment());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        holder.ivViewIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFile(image);
            }
        });
        changeImageColor("white",holder.ivViewIcon);
        changeImageColor("white",holder.ivShareIcon);
    }

    private void changeImageColor(String color,ImageView imageView){
        if (color.equalsIgnoreCase("white")) {
            imageView.setColorFilter(mContext.getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);
        }
    }

    public class BitmapLoader extends AsyncTask<String, Void ,Bitmap>{
        String fileName = "",comment = "";
        AlertDialog alertDialog =null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
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
                // tO SHARE TEXT AND IMAGE BY INTENT :
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
                    String path = MediaStore.Images.Media.insertImage(mContext.getContentResolver(), bitmap, "", null);
                    Uri screenshotUri = Uri.parse(path);
                    intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                    intent.setType("image/*");
                    mContext.startActivity(Intent.createChooser(intent, "Share image via..."));
            }
        }
    }



    private class DownloadFile extends AsyncTask<String, Void, File>{
        AlertDialog alertDialog =null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
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
           return FileDownloader.downloadFile(fileUrl, pdfFile);
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
                    mContext.startActivity(Intent.createChooser(intent, "Share File via..."));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }



    public static class FileDownloader {
        private static final int  MEGABYTE = 1024 * 1024;

        public static File downloadFile(String fileUrl, File directory){
            try {

                URL url = new URL(fileUrl);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream(directory);
                int totalSize = urlConnection.getContentLength();

                byte[] buffer = new byte[MEGABYTE];
                int bufferLength = 0;
                while((bufferLength = inputStream.read(buffer))>0 ){
                    fileOutputStream.write(buffer, 0, bufferLength);
                }
                fileOutputStream.close();

            return directory;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private void openFile(String url) {

        try {

            //oPEN IMAGE OR DOCUMENT THROUGH INTENT :
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            if (url.toString().contains(".doc") || url.toString().contains(".docx")) {
                // Word document
                intent.setDataAndType(uri, "application/msword");
            } else if (url.toString().contains(".pdf")) {
                // PDF file
                intent.setDataAndType(uri, "application/pdf");
            } else if (url.toString().contains(".ppt") || url.toString().contains(".pptx")) {
                // Powerpoint file
                intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
            } else if (url.toString().contains(".xls") || url.toString().contains(".xlsx")) {
                // Excel file
                intent.setDataAndType(uri, "application/vnd.ms-excel");
            } else if (url.toString().contains(".zip") || url.toString().contains(".rar")) {
                // WAV audio file
                intent.setDataAndType(uri, "application/x-wav");
            } else if (url.toString().contains(".rtf")) {
                // RTF file
                intent.setDataAndType(uri, "application/rtf");
            } else if (url.toString().contains(".wav") || url.toString().contains(".mp3")) {
                // WAV audio file
                intent.setDataAndType(uri, "audio/x-wav");
            } else if (url.toString().contains(".gif")) {
                // GIF file
                intent.setDataAndType(uri, "image/gif");
            } else if (url.toString().contains(".jpg") || url.toString().contains(".jpeg") || url.toString().contains(".png")) {
                // JPG file
                intent.setDataAndType(uri, "image/jpeg");
            } else if (url.toString().contains(".txt")) {
                // Text file
                intent.setDataAndType(uri, "text/plain");
            } else if (url.toString().contains(".3gp") || url.toString().contains(".mpg") ||
                    url.toString().contains(".mpeg") || url.toString().contains(".mpe") || url.toString().contains(".mp4") || url.toString().contains(".avi")) {
                // Video files
                intent.setDataAndType(uri, "video/*");
            } else {
                intent.setDataAndType(uri, "*/*");
            }

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(mContext,"No application found which can open the file", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return packagesListFiltered.size();
    }

}
