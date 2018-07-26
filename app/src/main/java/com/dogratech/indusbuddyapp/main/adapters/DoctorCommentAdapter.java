package com.dogratech.indusbuddyapp.main.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.models.Model_Item_doctor_comment;

import java.util.List;

/**
 * Created by akshaya on 12/3/18.
 */

public class DoctorCommentAdapter extends RecyclerView.Adapter<DoctorCommentAdapter.MyViewHolder> {
    private Context mContext;
    private List<Model_Item_doctor_comment> dr_comment_list;

    public void update(List<Model_Item_doctor_comment> doctorCommentList) {
        dr_comment_list = doctorCommentList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  tv_comment,tvClassification,tvAddedOn,tvAddedBy;
        public MyViewHolder(View view) {
            super(view);
            tv_comment = view.findViewById(R.id.tv_comment);
            tvClassification = view.findViewById(R.id.tvClassification);
            tvAddedOn = view.findViewById(R.id.tvAddedOn);
            tvAddedBy = view.findViewById(R.id.tvAddedBy);
        }
    }

    public DoctorCommentAdapter(Context mContext, List<Model_Item_doctor_comment> dr_comment_list) {
        this.mContext  = mContext;
        this.dr_comment_list  = dr_comment_list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_doctor_comment, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DoctorCommentAdapter.MyViewHolder holder, int position) {
        try {
            Model_Item_doctor_comment doctor_comment = dr_comment_list.get(position);
            String comment = doctor_comment.getAnalysisComment();
            if (comment != null) {
                comment = comment.replace("\n", "\n\n");
                holder.tv_comment.setText(comment);
            }
            holder.tvClassification.setText(doctor_comment.getClassification());
            holder.tvAddedBy.setText(doctor_comment.getAddedBy());
            holder.tvAddedOn.setText(doctor_comment.getAddedOn());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dr_comment_list.size();
    }
}
