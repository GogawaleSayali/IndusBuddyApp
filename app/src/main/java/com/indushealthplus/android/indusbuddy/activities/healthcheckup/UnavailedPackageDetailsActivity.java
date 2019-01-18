package com.indushealthplus.android.indusbuddy.activities.healthcheckup;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.baseactivities.BaseActivity;
import com.indushealthplus.android.indusbuddy.componentclasses.MyAppointmentComponents;

public class UnavailedPackageDetailsActivity extends BaseActivity implements View.OnClickListener {
    private TextView txt_testdetails ,txt_getAppointment ;
    private Intent intent ;
    private String from = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_availed_package);
        initialise();
        initialiseClass();
        setListeners();
    }

    private void initialise() {
        toolbar               = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Package Details");
        txt_testdetails       = findViewById(R.id.txt_testdetails);
        txt_getAppointment    = findViewById(R.id.txt_getAppointment);

        /*from = getIntent().getStringExtra("from");
        if (from.equalsIgnoreCase("history")){
            txt_getAppointment.setText("My Appointment");
        }else {
            txt_getAppointment.setText("Get Appointment");
        }*/
    }

    private void setListeners() {
        txt_testdetails       . setOnClickListener(this);
        txt_getAppointment    . setOnClickListener(this);
    }

    private void initialiseClass() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the HomeComponents/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case android.R.id.home:
                /*intent = new Intent(UnavailedPackageDetailsActivity.this, UnAvailedPackagesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                startActivity(intent);*/
                finish();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_testdetails :
                 intent = new Intent(UnavailedPackageDetailsActivity.this,TestDetailsActivity.class);
                 startActivity(intent);
                 break;
            case R.id.txt_getAppointment :
                    intent = new Intent(UnavailedPackageDetailsActivity.this, MyAppointmentComponents.class);
                    startActivity(intent);
                 break;

                 default:
                     break;
        }
    }
}
