package com.dogratech.indusbuddyapp.main.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.healthcheckup.FeedbackActivity;
import com.dogratech.indusbuddyapp.main.activities.healthcheckup.MyPackageActivity;
import com.dogratech.indusbuddyapp.main.activities.healthcheckup.TestDetailsActivity;
import com.dogratech.indusbuddyapp.main.helper.AnimateFirstDisplayListener;
import com.dogratech.indusbuddyapp.main.models.PackageDetailsModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

public class MyPackages_Adapter extends RecyclerView.Adapter<MyPackages_Adapter.MyViewHolder> {
    private LayoutInflater layoutInflater;
    public Context mContext;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private DisplayImageOptions options;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private ArrayList<PackageDetailsModel> model_availed_packages ;
    private PackageDetailsModel model_test ;

    public MyPackages_Adapter(Context mContext, ArrayList<PackageDetailsModel> model_availed_packages) {
        this.model_availed_packages = model_availed_packages;
        this.mContext    = mContext;
        if(mContext != null){
            layoutInflater   =  (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
        }
            // for above 2 lines null pointer handled :

        // mFilter = new CustomFilter(TestAdapter.this);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.logo_indus)
                .showImageForEmptyUri(R.mipmap.logo_indus)
                .showImageOnFail(R.mipmap.logo_indus)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mypackages, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyPackages_Adapter.MyViewHolder holder, int position) {
        try {
            model_test = new PackageDetailsModel();
            model_test = model_availed_packages.get(position);
            try {
                if (!model_test.getName().equalsIgnoreCase(null)) {
                    String pName = model_test.getName();
                    holder.tv_package_name.setText(pName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            /*try {
                if (!model_test.getPkg_type().equalsIgnoreCase(null)) {
                    String pName = model_test.getPkg_type();
                    holder.tv_packageType.setText(pName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (!model_test.getPkg_test().equalsIgnoreCase(null)) {
                    String pName = model_test.getPkg_test();
                    holder.tv_addontest.setText(pName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            try {
                if (!model_test.getAmount().equalsIgnoreCase(null)) {
                    String pName = model_test.getAmount();
                    holder.tv_totalamount.setText(pName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (!model_test.getPaymentStatus().equalsIgnoreCase(null)) {
                    String pName = model_test.getPaymentStatus();
                    holder.tv_paymode.setText(pName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            holder.tv_feedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Intent intent  = new Intent(mContext,FeedbackActivity.class);
                    intent.putExtra("from","history");
                    mContext.startActivity(intent);*/

                    Intent intent  = new Intent(mContext,TestDetailsActivity.class);
                    intent.putExtra("detailsType","my");
                    intent.putExtra("pkgTests",model_test.getPackageTests());
                    intent.putExtra("AddonTests",model_test.getAvailableAddOnTest());
                    intent.putExtra("AddonPackages",model_test.getAvailableAddOnPackage());
                    intent.putExtra("AddonDiscountPkgs",model_test.getAvailableDiscountCouponPackage());
                    mContext.startActivity(intent);
                }
            });

            holder.tv_testdetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent  = new Intent(mContext,TestDetailsActivity.class);
                    intent.putExtra("detailsType","my");
                    intent.putExtra("pkgTests",model_test.getPackageTests());
                    intent.putExtra("AddonTests",model_test.getAvaildedAddOnTest());
                    intent.putExtra("AddonPackages",model_test.getAvaildedAddOnPackage());
                    intent.putExtra("AddonDiscountPkgs",model_test.getAvailableDiscountCouponPackage());
                    mContext.startActivity(intent);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return model_availed_packages.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_package_name ,tv_totalamount,tv_paymode,tv_feedback,tv_testdetails;
        public ImageView package_image ;

        public MyViewHolder(View view) {
            super(view);
            tv_package_name       = view.findViewById(R.id.tv_package_name);
            tv_feedback           = view.findViewById(R.id.tv_feedback);
            tv_testdetails        = view.findViewById(R.id.tv_testdetails);
            tv_totalamount        = view.findViewById(R.id.tv_totalamount);
            tv_paymode            = view.findViewById(R.id.tv_paymode);
            package_image         = view.findViewById(R.id.package_image);

        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
