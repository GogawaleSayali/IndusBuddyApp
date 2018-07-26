package com.dogratech.indusbuddyapp.main.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.healthcheckup.CentreDetailsActivity;
import com.dogratech.indusbuddyapp.main.activities.healthcheckup.CentreSelectionActivity;
import com.dogratech.indusbuddyapp.main.activities.navmenuactivities.HomePageNewsActivity;
import com.dogratech.indusbuddyapp.main.models.ModelCentre;
import com.jackandphantom.circularimageview.CircleImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akshaya on 23/2/18.
 */

public class ArchiveYearAdapter extends RecyclerView.Adapter<ArchiveYearAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> packagesListFiltered;

    public void updateList(ArrayList<String> modelCentre) {
        packagesListFiltered =modelCentre;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvYear;

        public MyViewHolder(View view) {
            super(view);
            tvYear = view.findViewById(R.id.tvYear);
        }
    }


    public ArchiveYearAdapter(Context mContext, List<String> packages) {
        this.mContext  = mContext;
        this.packagesListFiltered = packages;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_archives_year, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final String year = packagesListFiltered.get(position);
        holder.tvYear.setText(year);
        // loading album cover using Glide library
        holder.tvYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomePageNewsActivity)mContext).SelectYear(year);
            }
        });
    }

    @Override
    public int getItemCount() {
        return packagesListFiltered.size();
    }

}
