package com.dogratech.indusbuddyapp.main.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.storerecord.ViewAndShareActivity;
import com.dogratech.indusbuddyapp.main.models.Model_Item_Report;
import com.dogratech.indusbuddyapp.main.models.ViewRecordSupportModel;
import com.google.gson.Gson;
import com.jackandphantom.circularimageview.CircleImage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amolr on 15/6/18.
 */

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.MyViewHolder> {
    private Context mContext;
    private List<ViewRecordSupportModel> packagesListFiltered;

    public void updateList(ArrayList<ViewRecordSupportModel> modelCentre) {
        packagesListFiltered = modelCentre;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDocumentType, tvRecordCount;
        public CardView card_view;
        public ImageView thumbnail;
        public CircleImage ivCircle;

        public MyViewHolder(View view) {
            super(view);
            card_view = view.findViewById(R.id.card_view);
            tvDocumentType = view.findViewById(R.id.tvDocumentType);
            tvRecordCount = view.findViewById(R.id.tvRecordCount);
        }
    }


    public RecordsAdapter(Context mContext, List<ViewRecordSupportModel> packages) {
        this.mContext = mContext;
        this.packagesListFiltered = packages;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_records, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ViewRecordSupportModel records = packagesListFiltered.get(position);
        holder.tvDocumentType.setText(records.getName());
        holder.tvRecordCount.setText(records.getCount());
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, ViewAndShareActivity.class);
                Gson gson = new Gson();
                String myJson = gson.toJson(records);
                intent.putExtra("myjson", myJson);

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return packagesListFiltered.size();
    }

}
