package com.indushealthplus.android.indusbuddy.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.baseactivities.BaseActivity;
import com.indushealthplus.android.indusbuddy.retrofit.ApiUrl;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

public class TipActivity extends BaseActivity {
    private TextView tvDescription;
    private ImageView ivImage1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);
        toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Tip");

        ivImage1 = findViewById(R.id.ivImage1);
        tvDescription = findViewById(R.id.tvDescription);
        try {
            String desc = getIntent().getExtras().getString("desc", "");
            String files = getIntent().getExtras().getString("files", "");
            if (!files.equalsIgnoreCase("")) {
                ArrayList<String> myList = new ArrayList<String>(Arrays.asList(files));
                String image = ApiUrl.Base_URL_INDUS + "viewReport/content/" + myList.get(0);
                Picasso.get().load(image).into(ivImage1);
            }
            if (desc != null) {
                if (!desc.equalsIgnoreCase("")) {
                    tvDescription.setText(desc);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
