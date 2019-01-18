package com.indushealthplus.android.indusbuddy.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amolr on 22/2/18.
 */

public class GenderAdapter extends ArrayAdapter<String> {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private List<String> items;
    private final int mResource;

    public GenderAdapter(@NonNull Context context, @LayoutRes int resource,ArrayList<String> items) {
        super(context, resource, 0,items);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        this.items = items;

    }

    @Nullable
    @Override
    public String getItem(int position) {
        return super.getItem(position).toString();
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = mInflater.inflate(mResource, parent, false);

        TextView offTypeTv = (TextView) view.findViewById(R.id.tvGender);


        offTypeTv.setText(items.get(position));

        return view;
    }
}
