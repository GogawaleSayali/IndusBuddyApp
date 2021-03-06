package com.dogratech.indusbuddyapp.main.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.helper.AnimateFirstDisplayListener;
import com.dogratech.indusbuddyapp.main.models.Model_Test;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

/**
 * Created by akshaya on 19/4/17.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> /*implements Filterable*/ {
    private LayoutInflater layoutInflater;
    public Context mContext;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private DisplayImageOptions options;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private ArrayList<Model_Test> model_tests_list ;
    private Model_Test model_test ;
   // private CustomFilter mFilter;


    public TestAdapter(Context mContext, ArrayList<Model_Test> model_tests_list) {
        this.model_tests_list = model_tests_list;
        this.mContext    = mContext;
        layoutInflater   =  (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_test, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TestAdapter.MyViewHolder holder, int position) {
        try {
            model_test = new Model_Test();
            model_test = model_tests_list.get(position);
            try {
                if (!model_test.getTest().equalsIgnoreCase(null)) {
                    String pName = model_test.getTest();
                    holder.tvTest.setText(pName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return model_tests_list.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

   /* @Override
    public Filter getFilter() {
        return mFilter;
    }*/

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTest;
        public ImageView ivTest;

        public MyViewHolder(View view) {
            super(view);
            tvTest       =  (TextView) view.findViewById(R.id.tvTest);
            ivTest       =  view.findViewById(R.id.ivTest);
            changeImageColor("primary",ivTest);
        }
        /************************************
         * Fill image with color.           *
         * @param color : color tb be filled*
         * @param imageView : ImageView     *
         ************************************/
        private void changeImageColor(String color,ImageView imageView){
            if (color.equalsIgnoreCase("primary")) {
                imageView.setColorFilter(mContext.getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);
            }else if (color.equalsIgnoreCase("white")) {
                imageView.setColorFilter(mContext.getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
