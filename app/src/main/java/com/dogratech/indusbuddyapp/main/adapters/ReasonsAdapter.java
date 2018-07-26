package com.dogratech.indusbuddyapp.main.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.healthcheckup.GetAppointmentActivity;
import com.dogratech.indusbuddyapp.main.activities.navmenuactivities.HomePageNewsActivity;
import com.dogratech.indusbuddyapp.main.models.ModelReason;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amolr on 25/6/18.
 */

public class ReasonsAdapter extends RecyclerView.Adapter<ReasonsAdapter.MyViewHolder> {
    private Context mContext;
    private List<ModelReason> reasons;

    public void updateList(ArrayList<ModelReason> modelCentre) {
        reasons =modelCentre;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvReason;

        public MyViewHolder(View view) {
            super(view);
            tvReason = view.findViewById(R.id.tvReason);
        }
    }


    public ReasonsAdapter(Context mContext, List<ModelReason> packages) {
        this.mContext  = mContext;
        this.reasons = packages;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_reasons, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ModelReason modelReason = reasons.get(position);
        holder.tvReason.setText(modelReason.getReason());
        // loading album cover using Glide library
        holder.tvReason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GetAppointmentActivity)mContext).selectedReason(modelReason.getCode());
            }
        });
    }

    @Override
    public int getItemCount() {
        return reasons.size();
    }

}
