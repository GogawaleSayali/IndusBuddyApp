package com.indushealthplus.android.indusbuddy.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.navmenuactivities.ArchivesActivity;
import com.indushealthplus.android.indusbuddy.models.ModelHomePageNews;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akshaya on 23/2/18.
 */

public class HomePageNewsAdapter extends RecyclerView.Adapter<HomePageNewsAdapter.MyViewHolder>{
    private Context mContext;
    private List<ModelHomePageNews> packagesListFiltered;
    private String indusHomeNewsUrl = "https://iconnect.indushealthplus.com/images/news/";

    public void updateList(ArrayList<ModelHomePageNews> modelCentre) {
        packagesListFiltered =modelCentre;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle, tvDomain,tvMonth,tvDescription,tvReadMore,tvSources, tvDate;
        public ImageView ivPhoto;

        public MyViewHolder(View view) {
            super(view);
            tvTitle =  view.findViewById(R.id.tvTitle);
            tvDomain = view.findViewById(R.id.tvDomain);
            tvMonth =  view.findViewById(R.id.tvMonth);
            tvDescription =  view.findViewById(R.id.tvDescription);
            tvReadMore =  view.findViewById(R.id.tvReadMore);
            ivPhoto = view.findViewById(R.id.ivPhoto);
            tvSources =  view.findViewById(R.id.tvSources);
            tvDate =  view.findViewById(R.id.tvDate);
        }
    }


    public HomePageNewsAdapter(Context mContext, List<ModelHomePageNews> packages) {
        this.mContext  = mContext;
        this.packagesListFiltered = packages;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_homepage_news, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ModelHomePageNews homePageNews = packagesListFiltered.get(position);
        holder.tvTitle.setText(homePageNews.getTitle());
        holder.tvDomain.setText(homePageNews.getDomain());
        holder.tvMonth.setText(homePageNews.getMonth()+" - "+homePageNews.getYear());
        holder.tvDescription.setText(homePageNews.getSmallDescription());
        holder.tvDate.setText(homePageNews.getDate());
        Picasso.get().load(indusHomeNewsUrl+homePageNews.getSliderInage()).into(holder.ivPhoto);
        // loading album cover using Glide library
        holder.tvReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (holder.tvReadMore.getText().toString().equalsIgnoreCase("Read More")){
                   holder.tvReadMore.setText("Read Less");
                   holder.tvDescription.setText(homePageNews.getSmallDescription()+". "+homePageNews.getDetailDescription());
               }else {
                   holder.tvReadMore.setText("Read More");
                   holder.tvDescription.setText(homePageNews.getSmallDescription());
               }
            }
        });
        holder.tvSources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ArchivesActivity.class);
                intent.putExtra("archives",homePageNews.getPdfUrls());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return packagesListFiltered.size();
    }

}
