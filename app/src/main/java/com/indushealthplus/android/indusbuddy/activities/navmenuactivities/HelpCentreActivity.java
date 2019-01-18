package com.indushealthplus.android.indusbuddy.activities.navmenuactivities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.baseactivities.BaseActivity;

public class HelpCentreActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_phone,tv_phone_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_centre);
        toolbar   = findViewById(R.id.toolbar);
        actionBar = getSupportActionBar();
        initializeToolBar(toolbar,"Help Centre");
        initializeWidgets();
    }

    private void initializeWidgets() {
        tv_phone=findViewById(R.id.tv_phone);
        tv_phone_one=findViewById(R.id.tv_phone_one);
        tv_phone.setOnClickListener(this);
        tv_phone_one.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_phone:
                Intent i = new Intent(Intent.ACTION_DIAL);
                String p = "tel:" + tv_phone.getText().toString().trim();
                i.setData(Uri.parse(p));
                startActivity(i);
                break;

            case R.id.tv_phone_one:
                Intent ii = new Intent(Intent.ACTION_DIAL);
                String pp = "tel:" + tv_phone_one.getText().toString().trim();
                ii.setData(Uri.parse(pp));
                startActivity(ii);
                break;
        }

    }
}
