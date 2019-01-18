package com.indushealthplus.android.indusbuddy.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.models.ModelItemByVisit;

import java.util.ArrayList;

/**
 * Created by amolr on 8/3/18.
 */

public class ParameterEmojiAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<ModelItemByVisit> data = new ArrayList<>();

    public ParameterEmojiAdapter(Context context,ArrayList<ModelItemByVisit> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position){return data.get(position).getParameterId();
    }

    public String getParameterName(int pos){
        return data.get(pos).getParameterName();
    }

    public String getNormal(int pos){
        return data.get(pos).getNormalValue();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.byvisit_item, parent, false);
        }
        ImageView ivEmoji = convertView.findViewById(R.id.ivEmoji);
        TextView tvNA = convertView.findViewById(R.id.tvNA);
        TextView tv_parametername   = convertView.findViewById(R.id.tv_parametername);
        ModelItemByVisit byVisit = data.get(position);
        tv_parametername .setText(byVisit.getParameterName());
        if (!byVisit.getParameterValue().equalsIgnoreCase("NA")) {
            if (byVisit.getNormalValue().equalsIgnoreCase("1")) {
                ivEmoji.setVisibility(View.VISIBLE);
                tvNA.setVisibility(View.GONE);
                String resultStatus = data.get(position).getTestResultStatus();
                if (resultStatus.equalsIgnoreCase("normal")) {
                    ivEmoji.setImageResource(R.drawable.emoji_happy);
                    ivEmoji.setColorFilter(context.getResources().getColor(R.color.colorGreen), PorterDuff.Mode.SRC_IN);
                } else if (resultStatus.equalsIgnoreCase("abnormal low")) {
                    ivEmoji.setImageResource(R.drawable.emoji_sad);
                    ivEmoji.setColorFilter(context.getResources().getColor(R.color.colorOrange), PorterDuff.Mode.SRC_IN);
                } else if (resultStatus.equalsIgnoreCase("abnormal high")){
                    ivEmoji.setImageResource(R.drawable.emoji_sad);
                    ivEmoji.setColorFilter(context.getResources().getColor(R.color.colorRed), PorterDuff.Mode.SRC_IN);
                }
            }else{
                ivEmoji.setVisibility(View.GONE);
                tvNA.setVisibility(View.VISIBLE);
            }
        }else{
            ivEmoji.setVisibility(View.GONE);
            tvNA.setVisibility(View.VISIBLE);
        }
          return convertView;
    }

    public void updateData(ArrayList<ModelItemByVisit> byVisits) {
        this.data = byVisits;
        notifyDataSetChanged();
    }
}
