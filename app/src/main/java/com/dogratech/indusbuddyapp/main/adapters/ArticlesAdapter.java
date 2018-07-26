package com.dogratech.indusbuddyapp.main.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dogratech.indusbuddyapp.R;

import java.util.ArrayList;

/**
 * Created by amolr on 8/3/18.
 */

public class ArticlesAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<String> data = new ArrayList<>();

    public ArticlesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 10;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.article_item, parent, false);
        }
        return convertView;
    }
}
