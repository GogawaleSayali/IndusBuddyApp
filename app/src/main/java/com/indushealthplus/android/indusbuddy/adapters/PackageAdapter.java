package com.indushealthplus.android.indusbuddy.adapters;

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
import com.indushealthplus.android.indusbuddy.activities.healthcheckup.TestDetailsActivity;
import com.indushealthplus.android.indusbuddy.models.Model_Package;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akshaya on 23/2/18.
 */

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.MyViewHolder> implements Filterable{
    private Context mContext;
    private List<Model_Package> packages;
    private List<Model_Package> packagesListFiltered;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, tvYouMay,tvTestDetails;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            tvYouMay = (TextView) view.findViewById(R.id.tvYouMay);
            tvTestDetails = (TextView) view.findViewById(R.id.tvTestDetails);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }


    public PackageAdapter(Context mContext, List<Model_Package> packages) {
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
                    List<Model_Package> filteredList = new ArrayList<>();
                    for (Model_Package row : packages) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getName().contains(charSequence)) {
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
                packagesListFiltered = (ArrayList<Model_Package>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_cur_package_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Model_Package aPackage = packagesListFiltered.get(position);
        holder.title.setText(aPackage.getName());
        final Intent intent = new Intent(mContext, TestDetailsActivity.class);
        intent.putExtra("pkgTests","");
        intent.putExtra("AddonTests","");
        intent.putExtra("AddonPackages","");
        intent.putExtra("AddonDiscountPkgs","");
        holder.tvYouMay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("detailsType","other");
                mContext.startActivity(intent);
            }
        });
        holder.tvTestDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("detailsType","my");
                mContext.startActivity(intent);
            }
        });
        // loading album cover using Glide library
        Glide.with(mContext).load(aPackage.getThumbnail()).into(holder.thumbnail);
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
