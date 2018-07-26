package com.dogratech.indusbuddyapp.main.adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.navmenuactivities.ArchivesActivity;
import com.dogratech.indusbuddyapp.main.models.ArchivesModel;
import com.dogratech.indusbuddyapp.main.models.ModelHomePageNews;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akshaya on 23/2/18.
 */

public class ArchivesAdapter extends RecyclerView.Adapter<ArchivesAdapter.MyViewHolder>{
    private Context mContext;
    private ArrayList<ArchivesModel> archives;
    private String indusHomeArchiveUrl = "https://iconnect.indushealthplus.com/images/news/publisher/";


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle, tvDomain,tvMonth,tvDescription,tvReadMore,tvSources, tvDate;
        public ImageView ivPhoto;

        public MyViewHolder(View view) {
            super(view);
            ivPhoto = (ImageView) view.findViewById(R.id.ivPhoto);
        }
    }


    public ArchivesAdapter(Context mContext, ArrayList<ArchivesModel> archives) {
        this.mContext  = mContext;
        this.archives  = archives;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_archives, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ArchivesModel archive = archives.get(position);
        Picasso.get().load(indusHomeArchiveUrl+archive.getImage()).into(holder.ivPhoto);
        // loading album cover using Glide library
        holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {/*
                String fileName = "";
                if (archive.get.contains(".jpg") ){
                    fileName = archive.replace(".jpg",".pdf");
                } else if(archive.contains(".jpeg")){
                    fileName = archive.replace(".jpeg",".pdf");
                } else if (archive.contains(".png")) {
                    fileName = archive.replace(".png",".pdf");
                }*/
                openFile("https://iconnect.indushealthplus.com/images/media/" + archive.getPdf());
            }
        });


    }
    private void openFile(String url) {

        try {
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
        return archives.size();
    }

}
