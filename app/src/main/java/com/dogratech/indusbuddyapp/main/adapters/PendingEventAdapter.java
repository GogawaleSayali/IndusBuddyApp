package com.dogratech.indusbuddyapp.main.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.wellness.EventDetailsActivity;
import com.dogratech.indusbuddyapp.main.models.EventItemModel;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by akshaya on 3/5/18.
 */

public class PendingEventAdapter extends RecyclerView.Adapter<PendingEventAdapter.MyViewHolder> {
    private Context mContext;
    private List<EventItemModel> events;
    public PendingEventAdapter(Context mContext, List<EventItemModel> events) {
        this.mContext  = mContext;
        this.events  = events;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_my_event, parent, false);
        return new MyViewHolder(itemView);
    }

    public void updateData(List<EventItemModel> events){
        this.events = events;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final EventItemModel aEvent = events.get(position);
        holder.tvEventName.setText(aEvent.getEventTitle());
        holder.tv_evcorporate.setText("Corporate : "+aEvent.getCorporate());
        holder.tv_evservice.setText("Service : "+aEvent.getServiceName());
        holder.tvType.setText(aEvent.getEventTypename());
        String per = aEvent.getPercentage();
        if (per.equalsIgnoreCase("0")){
            per = "100";
        }
        holder.tvPayPercentage.setText(per+" %");
        holder.tvValidity.setText(aEvent.getFormatedFrom());
        holder.tvValidityTo.setText(aEvent.getFormatedTo());
        if (aEvent.getStatus().equalsIgnoreCase("Pending")){
            holder.tvStatus.setTextColor(Color.RED);
        }else {
            holder.tvStatus.setTextColor(Color.parseColor("#006400"));
        }
        holder.tvStatus.setText(aEvent.getStatus());
        holder.tvViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String jsonData = gson.toJson(aEvent);
                Intent intent = new Intent(mContext,EventDetailsActivity.class);
                intent.putExtra("eventDetails",jsonData);
                //mContext.startActivity(intent);
                ((Activity)mContext).startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvEventName, tv_evcorporate, tv_evservice, tvStatus,
                tvType, tvViewDetails, tvPayPercentage, tvValidity, tvValidityTo;

        public MyViewHolder(View view) {
            super(view);
            tvEventName = view.findViewById(R.id.tvEventName);
            tv_evcorporate = view.findViewById(R.id.tv_evcorporate);
            tv_evservice = view.findViewById(R.id.tv_evservice);
            tvStatus = view.findViewById(R.id.tvStatus);
            tvType = view.findViewById(R.id.tvType);
            tvViewDetails = view.findViewById(R.id.tvViewDetails);
            tvValidity = view.findViewById(R.id.tvValidity);
            tvValidityTo = view.findViewById(R.id.tvValidityTo);
            tvPayPercentage = view.findViewById(R.id.tvPayPercentage);
        }
    }

}
