package com.indushealthplus.android.indusbuddy.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.models.ContentsPreview;
import com.indushealthplus.android.indusbuddy.retrofit.ApiUrl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by akshaya on 12/3/18.
 */

public class HealthTipsAdapter extends RecyclerView.Adapter<HealthTipsAdapter.MyViewHolder> {
    private Context mContext;
    private List<ContentsPreview> tipsList;

    public void updateList(ArrayList<ContentsPreview> contentsPreview) {

        if (tipsList != null) {
            tipsList.clear();
            tipsList.addAll(contentsPreview);
        } else {
            tipsList = contentsPreview;
        }
        notifyDataSetChanged();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvArticleTitle, tvAddedBy, tvAddedOn, tvPublishDate, tvDescription;
        public ImageView ivArticleImage;

        public MyViewHolder(View view) {
            super(view);
            tvArticleTitle = view.findViewById(R.id.tvArticleTitle);
            tvDescription = view.findViewById(R.id.tvDescription);
            tvAddedBy = view.findViewById(R.id.tvAddedBy);
            tvAddedOn = view.findViewById(R.id.tvAddedOn);
            ivArticleImage = view.findViewById(R.id.ivArticleImage);
            tvPublishDate = view.findViewById(R.id.tvPublishDate);
        }
    }

    public HealthTipsAdapter(Context mContext, List<ContentsPreview> tipsList) {
        this.mContext = mContext;
        this.tipsList = tipsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HealthTipsAdapter.MyViewHolder holder, int position) {
        ContentsPreview health_tips = tipsList.get(position);
        holder.tvArticleTitle.setText(health_tips.getContentTitle());
        String descr = health_tips.getContentDescription().equalsIgnoreCase("")
                ? "-" : health_tips.getContentDescription();
        String addedBy = health_tips.getAddedBy() == null ? "-" : health_tips.getAddedBy();
        holder.tvDescription.setText(descr.trim());//mContext.getString(R.string.about_us_text)
        holder.tvAddedBy.setText("Added By - " + addedBy);
        long addedOn = health_tips.getAddedOn();
        String dateFormat = "dd-MM-yyyy hh:mm";
        holder.tvPublishDate.setText("Published on : " + getDate(addedOn, dateFormat));
        Log.d("Health Tips Crash :: ",  " champ : : " + health_tips.getContentFiles().isEmpty());
        if(!health_tips.getContentFiles().isEmpty()){
            if (health_tips.getContentFiles().get(0).getFileType().equalsIgnoreCase("image")) {
                final String image = ApiUrl.Base_URL_INDUS + "viewReport/content/"
                        + health_tips.getContentFiles().get(0).getFileName();
                Glide.with(mContext).load(image).into(holder.ivArticleImage);
            }
        }
    }

    public static String getDate(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    @Override
    public int getItemCount() {
        return tipsList.size();
    }
}
