package com.indushealthplus.android.indusbuddy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.models.ModelItemByVisit;

import java.util.ArrayList;

/**
 * Created by amolr on 8/3/18.
 */

public class ParameterAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<ModelItemByVisit> data = new ArrayList<>();

    public ParameterAdapter(Context context ,ArrayList<ModelItemByVisit> data) {
        this.context = context;
        this.data    = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.byparameter_item, parent, false);
        }
        TextView tvParameterName = convertView.findViewById(R.id.tvParameterName);
        tvParameterName          . setText(data.get(position).getParameterName());
        return convertView;
    }

    public void updateData(ArrayList<ModelItemByVisit> global_paramlist) {
        this.data = global_paramlist;
        notifyDataSetChanged();
    }
}
