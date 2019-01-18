package com.indushealthplus.android.indusbuddy.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.healthcheckup.CentreDetailsActivity;
import com.indushealthplus.android.indusbuddy.activities.healthcheckup.CentreSelectionActivity;
import com.indushealthplus.android.indusbuddy.models.ModelCentre;
import com.jackandphantom.circularimageview.CircleImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akshaya on 23/2/18.
 */

public class CentreAdapter extends RecyclerView.Adapter<CentreAdapter.MyViewHolder> implements Filterable{
    private Context mContext;
    private List<ModelCentre> packages;
    private List<ModelCentre> packagesListFiltered;

    public void updateList(ArrayList<ModelCentre> modelCentre) {
        packages = modelCentre;
        packagesListFiltered =modelCentre;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, tvSelect,tvDetailsCenter;
        public ImageView thumbnail;
        public CircleImage ivCircle;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            tvSelect = (TextView) view.findViewById(R.id.tvSelect);
            tvDetailsCenter = (TextView) view.findViewById(R.id.tvDetailsCenter);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            ivCircle =  view.findViewById(R.id.ivCircle);
        }
    }


    public CentreAdapter(Context mContext, List<ModelCentre> packages) {
        this.mContext  = mContext;
        this.packages  = packages;
        this.packagesListFiltered = packages;
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    packagesListFiltered = packages;
                } else {
                    List<ModelCentre> filteredList = new ArrayList<>();
                    for (ModelCentre row : packages) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getCentreName().toLowerCase().contains(charString.toLowerCase()) || row.getCentreName().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    packagesListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = packagesListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                packagesListFiltered = (ArrayList<ModelCentre>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_centre_locator, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ModelCentre aPackage = packagesListFiltered.get(position);
        holder.title.setText(aPackage.getCentreName());
        // loading album cover using Glide library
        try {
            Glide.with(mContext).load(aPackage.getHospital_image()).into(holder.thumbnail);
        }catch (Exception e){e.printStackTrace();}
        try {
            Glide.with(mContext).load(aPackage.getLogoUrl()).into(holder.ivCircle);
        }catch (Exception e){e.printStackTrace();}
        holder.tvDetailsCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CentreDetailsActivity.class);
                intent.putExtra("cityName",aPackage.getCityName());
                intent.putExtra("stateName",aPackage.getStateName());
                intent.putExtra("centreName",aPackage.getCentreName());
                intent.putExtra("address",aPackage.getAddress());
                intent.putExtra("centerContactNumber",aPackage.getCenterContactNumber());
                intent.putExtra("centerContactName",aPackage.getCenterContactName());
                intent.putExtra("latitude",""+aPackage.getLatitude());
                intent.putExtra("longitude",""+aPackage.getLongitude());
                intent.putExtra("logo",aPackage.getLogoUrl());
                intent.putExtra("hospital_image",aPackage.getHospital_image());
                intent.putExtra("health_friend",aPackage.getHealth_friend());
                intent.putExtra("PHP",aPackage.getPHP());
                intent.putExtra("ESS",aPackage.getESS());
                intent.putExtra("SUP",aPackage.getSUP());
                intent.putExtra("OPTIMA",aPackage.getOPTIMA());
                intent.putExtra("URL",aPackage.getURL());
                intent.putExtra("HOSPITAL_DESCRIPTION",aPackage.getHOSPITAL_DESCRIPTION());
                intent.putExtra("SERVICES",aPackage.getSERVICES());
                intent.putExtra("PRIMA",aPackage.getPRIMA());
                mContext.startActivity(intent);
            }
        });
        holder.tvSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("centreCode",""+aPackage.getCentreCode());
                ((CentreSelectionActivity) mContext).setResult(Activity.RESULT_OK,intent);
                ((CentreSelectionActivity) mContext).finish();
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_package, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return packagesListFiltered.size();
    }

}
