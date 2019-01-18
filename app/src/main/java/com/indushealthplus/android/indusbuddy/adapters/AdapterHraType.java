package com.indushealthplus.android.indusbuddy.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.models.HraTypeModel;

import java.util.ArrayList;

/**
 * Created by amolr on 23/2/18.
 */

public class AdapterHraType extends RecyclerView
        .Adapter<AdapterHraType.HraViewHolder> {
    private static String LOG_TAG = "AdapterAllCenters";
    private ArrayList<HraTypeModel> mDataset = new ArrayList<>();
    private static MyClickListener myClickListener;

    public static class HraViewHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        private MyClickListener mListener;
        TextView tvHraType;
        public HraViewHolder(View itemView,MyClickListener myListener) {
            super(itemView);
            tvHraType        = itemView.findViewById(R.id.tvHraType);
            mListener = myListener;
            itemView.setOnClickListener(this);
            Log.i(LOG_TAG, "Adding Listener");
            //itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public AdapterHraType(ArrayList<HraTypeModel> myDataset,MyClickListener myClickListener) {
        mDataset = myDataset;
       this.myClickListener = myClickListener;
    }

    @Override
    public HraViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        int layoutRes = 0;

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_hra_type, parent, false);
        return new HraViewHolder(view,myClickListener);

    }

    @Override
    public void onBindViewHolder(HraViewHolder holder, int position) {
        holder.tvHraType.setText(String.valueOf(mDataset.get(position).getHraType()));
    }

    public void addItem(HraTypeModel dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    public int getPercentage(int numQ,int solvedQ){
        float perc = (solvedQ * 100)/numQ;
        Log.v("progress : ",""+perc);
        return (int)perc;
    }

}