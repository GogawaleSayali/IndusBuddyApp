package com.dogratech.indusbuddyapp.main.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.healthcheckup.ApptDetailsActivity;
import com.dogratech.indusbuddyapp.main.models.Model_Item_Appointment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by akshaya on 12/3/18.
 */

public class MyAppointmentsAdapter extends RecyclerView.Adapter<MyAppointmentsAdapter.MyViewHolder> {
    private Context mContext;
    private List<Model_Item_Appointment> tipsList;

    public void updateList(ArrayList<Model_Item_Appointment> contentsPreview) {
        tipsList = contentsPreview;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  tvAppt,tvApptOn,tvApptStatus,tvDetails;
        public MyViewHolder(View view) {
            super(view);
            tvAppt = view.findViewById(R.id.tvAppt);
            tvApptOn = view.findViewById(R.id.tvApptOn);
            tvApptStatus = view.findViewById(R.id.tvApptStatus);
            tvDetails = view.findViewById(R.id.tvDetails);
        }
    }

    public MyAppointmentsAdapter(Context mContext, List<Model_Item_Appointment> tipsList) {
        this.mContext  = mContext;
        this.tipsList  = tipsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_appts, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyAppointmentsAdapter.MyViewHolder holder, int position) {
        final Model_Item_Appointment health_tips = tipsList.get(position);
        holder.tvAppt.setText(health_tips.getSchemeName());
        holder.tvApptOn.setText(health_tips.getAppointmentDate());
        holder.tvApptStatus.setText(health_tips.getAppointmentStatus());
        holder.tvDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ApptDetailsActivity.class);
                intent.putExtra("date",health_tips.getAppointmentDate());
                intent.putExtra("number",health_tips.getAppointmentNo());
                intent.putExtra("schema",health_tips.getSchemeName());
                intent.putExtra("benificiaryName",health_tips.getBeneficiaryName());
                intent.putExtra("benbificiaryRelation",health_tips.getBeneficiaryRelation());
                intent.putExtra("status",health_tips.getAppointmentStatus());
                intent.putExtra("centre",health_tips.getCCName());
                intent.putExtra("cityCode",health_tips.getCityCode());
                intent.putExtra("financial",health_tips.getAppointmentYear());
                mContext.startActivity(intent);
            }
        });
    }

    public static String getDate(long milliSeconds, String dateFormat)
    {
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
