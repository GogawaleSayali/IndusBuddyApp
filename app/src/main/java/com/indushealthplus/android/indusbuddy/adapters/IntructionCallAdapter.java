package com.indushealthplus.android.indusbuddy.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.healthcheckup.InstructionCallActivity;
import com.indushealthplus.android.indusbuddy.helper.AnimateFirstDisplayListener;
import com.indushealthplus.android.indusbuddy.models.PckgwiseInstrctionCallModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

/**
 * Created by sayali.gogawale on 1/11/2019.
 */

public class IntructionCallAdapter extends RecyclerView.Adapter<IntructionCallAdapter.MyViewHolder> {
    private LayoutInflater layoutInflater;
    public Context mContext;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private DisplayImageOptions options;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private ArrayList<PckgwiseInstrctionCallModel> instruction_pckg ;
    private PckgwiseInstrctionCallModel pck_name;

    public IntructionCallAdapter(Context mContext, ArrayList<PckgwiseInstrctionCallModel> instruction_pckg) {
        this.instruction_pckg = instruction_pckg;
        if(mContext != null){
            this.mContext    = mContext;
            layoutInflater   =  (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
        }

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
    public IntructionCallAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_instruction_call_list_item, parent, false);
        return new IntructionCallAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IntructionCallAdapter.MyViewHolder holder, int position) {
        try {
            pck_name = new PckgwiseInstrctionCallModel();
            pck_name = instruction_pckg.get(position);
            try {
                if (!pck_name.getName().equalsIgnoreCase(null)) {
                    String pName = pck_name.getName();
                    holder.tv_package_name.setText(pName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            holder.package_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //FeedbackActivity :
                    Intent intent  = new Intent(mContext,InstructionCallActivity.class);
                    //data here is injsonform :
                    String json = "[{\"Language\": \"First\",\"audioLink\":\"hindi.mp3\"},{\"Language\": \"Second\",\"audioLink\":\"marathi.mp3\"}]";
                    intent.putExtra("audioDetails",json);
                    mContext.startActivity(intent);
                   /* Intent intent  = new Intent(mContext,TestDetailsActivity.class);
                    intent.putExtra("detailsType","my");
                    intent.putExtra("pkgTests",model_test.getPackageTests());
                    intent.putExtra("AddonTests",model_test.getAvaildedAddOnTest());
                    intent.putExtra("AddonPackages",model_test.getAvaildedAddOnPackage());
                    intent.putExtra("AddonDiscountPkgs",model_test.getAvaildedDiscountCouponPackage());
                    mContext.startActivity(intent);*/
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return instruction_pckg.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_package_name ;
        public TextView package_image ;

        public MyViewHolder(View view) {
            super(view);
            tv_package_name       = view.findViewById(R.id.tv_instruction_pck_name);
            package_image         = view.findViewById(R.id.img_audio);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
