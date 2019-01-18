package com.indushealthplus.android.indusbuddy.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akshaya on 23/2/18.
 */

public class OneTimeSumamryAdapter extends RecyclerView.Adapter<OneTimeSumamryAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> oneTimeSummaryLst;

    public void updateList(ArrayList<String> modelCentre) {
        oneTimeSummaryLst =modelCentre;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNumber,tvTime,tvDelete;
        public LinearLayout llRow;
        public MyViewHolder(View view) {
            super(view);
            tvNumber = view.findViewById(R.id.tvNumber);
            tvTime = view.findViewById(R.id.tvTime);
            tvDelete = view.findViewById(R.id.tvDelete);
            llRow = view.findViewById(R.id.llRow);
        }
    }


    public OneTimeSumamryAdapter(Context mContext, List<String> packages) {
        this.mContext  = mContext;
        this.oneTimeSummaryLst = packages;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_ontime_summary, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (position % 2 == 0){
            holder.llRow.setBackgroundColor(Color.parseColor("#07000000"));
        }else {
            holder.llRow.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        final String time = oneTimeSummaryLst.get(position);
        holder.tvTime.setText(time);
        int number = position +1;
        holder.tvNumber.setText(""+number);
        // loading album cover using Glide library
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((HomePageNewsActivity)mContext).SelectYear(year);
                oneTimeSummaryLst.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return oneTimeSummaryLst.size();
    }

}
