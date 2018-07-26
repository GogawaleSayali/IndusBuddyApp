package com.dogratech.indusbuddyapp.main.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.models.ModelArticleSettings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amolr on 21/3/18.
 */

public class ArticleSettingsAdapter extends
        RecyclerView.Adapter<ArticleSettingsAdapter.ViewHolder> {
    private List<ModelArticleSettings> filterList;
    private Context context;

    public ArticleSettingsAdapter(List<ModelArticleSettings> filterModelList
            , Context ctx) {
        filterList = filterModelList;
        context = ctx;
    }

    @Override
    public ArticleSettingsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                          int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_article_settings, parent, false);
        ViewHolder viewHolder = new ViewHolder(view,viewType);
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ModelArticleSettings filterM = filterList.get(position);
        holder.tvArticlePrefName.setText(filterM.getName());
        //if (filterM.isSelected()) {
            holder.checkBoxArticlePrefs.setChecked(filterM.isSelected());
      /*  } else {
            holder.checkBoxArticlePrefs.setChecked(false);
        }*/
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvArticlePrefName;
        public CheckBox checkBoxArticlePrefs;

        public ViewHolder(View view, final int pos) {
            super(view);
            tvArticlePrefName = (TextView) view.findViewById(R.id.tvArticlePrefName);
            checkBoxArticlePrefs = (CheckBox) view.findViewById(R.id.checkBoxArticlePrefs);

            //item click event listener
            view.setOnClickListener(this);

            //checkbox click event handling
            checkBoxArticlePrefs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {
                    filterList.get(pos).setSelected(isChecked);
                }
            });

        }

        @Override
        public void onClick(View v) {
          //  TextView brndName = (TextView) v.findViewById(R.id.brand_name);
            //show more information about brand
            int count = getSelectedItems().size();
            Toast.makeText(context, "selected items size : "+count, Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<ModelArticleSettings> getSelectedItems(){
        ArrayList<ModelArticleSettings> list = new ArrayList<>();
        for (ModelArticleSettings settings: filterList ) {
            if (settings.isSelected()){
                list.add(settings);
            }
        }
        return list;
    }
}
