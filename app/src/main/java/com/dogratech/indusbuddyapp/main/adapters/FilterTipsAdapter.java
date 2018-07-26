package com.dogratech.indusbuddyapp.main.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.healthguide.guidefragments.HealthReportsFragment;
import com.dogratech.indusbuddyapp.main.models.Model_filter_tips;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akshaya on 12/3/18.
 */

public class FilterTipsAdapter extends BaseAdapter {
    private Context context;
    private HealthReportsFragment storeRecords;
    private List<Model_filter_tips> data = new ArrayList<>();
    public static Typeface normal_font;

    public FilterTipsAdapter(Context context, List<Model_filter_tips> data) {
        this.context = context;
        this.data = data;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.row_tips_filter, parent, false);
        }
        CheckBox checkbox = convertView.findViewById(R.id.checkbox);
        checkbox          . setText(data.get(position).getFlt_name());
        return convertView;
    }
}
