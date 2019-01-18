package com.dogratech.indusbuddyapp.main.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.reminder.ReminderActivity;
import com.dogratech.indusbuddyapp.main.models.ModelItemReminder;

import java.util.List;

/**
 * Created by akshaya on 12/3/18.
 */

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.MyViewHolder> {
    private ReminderActivity mContext;
    private List<ModelItemReminder> reminderList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  tv_reminder,tvDate,tvRemForMe,tvCategory;
        public LinearLayout llRow;

        public MyViewHolder(View view) {
            super(view);
            tv_reminder = view.findViewById(R.id.tv_reminder);
            tvDate = view.findViewById(R.id.tvDate);
            tvRemForMe = view.findViewById(R.id.tvRemForMe);
            tvCategory = view.findViewById(R.id.tvCategory);
            llRow       = view.findViewById(R.id.llRow);
        }
    }

    public ReminderAdapter(ReminderActivity mContext, List<ModelItemReminder> reminderList) {
        this.mContext  = mContext;
        this.reminderList  = reminderList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_reminder, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReminderAdapter.MyViewHolder holder, final int position) {
        ModelItemReminder itemReminder = reminderList.get(position);
        holder.tv_reminder.setText(itemReminder.getEventTitle());
        holder.tvDate.setText(itemReminder.getReminderDateTime());
        holder.tvRemForMe.setText(itemReminder.getRemindMeFor());
        holder.tvCategory.setText(itemReminder.getCategory());
        holder.llRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.showReminder(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }
}
