package com.indushealthplus.android.indusbuddy.activities.healthcheckup;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.baseactivities.BaseActivity;

public class CentreDetailsActivity extends BaseActivity implements View.OnClickListener {
    private FrameLayout fltShowMap;
    private Intent intent ;
    private  String latitude,longitude;
    private TextView tvAvailableTest,title,tvAddress,tvDescription;
    private ImageView package_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centre_details);
        initialise();
        setListeners();
        showDetails();
    }

    private void showDetails() {
        Bundle bundle = getIntent().getExtras();
       String cityName =  bundle.getString("cityName");
        String stateName = bundle.getString("stateName");
        String centreName =bundle.getString("centreName");
        String address =bundle.getString("address");
        String centerContactNumber =bundle.getString("centerContactNumber" );
        String centerContactName =bundle.getString("centerContactName");
        latitude   =bundle.getString("latitude");
        longitude = bundle.getString("longitude");
        String logo =bundle.getString("logo");
        String hospital_image =bundle.getString("hospital_image");
        String health_friend =bundle.getString("health_friend");
        String PHP =bundle.getString("PHP");
        String ESS =bundle.getString("ESS");
        String SUP =bundle.getString("SUP");
        String OPTIMA =bundle.getString("OPTIMA");
        String URL =bundle.getString("URL");
        String HOSPITAL_DESCRIPTION =bundle.getString("HOSPITAL_DESCRIPTION");
        String SERVICES =bundle.getString("SERVICES");
        String PRIMA = bundle.getString("PRIMA");

        title.setText(centreName);
        tvAddress.setText(address);
        tvDescription.setText(HOSPITAL_DESCRIPTION);
        Glide.with(CentreDetailsActivity.this).load(hospital_image).into(package_image);
    }

    /**
     * Initialize Ui widgets
     */
    private void initialise() {
        toolbar    = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,getString(R.string.title_activity_centre_details));
        tvAvailableTest     = findViewById(R.id.tvAvailableTest);
        fltShowMap = findViewById(R.id.fltShowMap);
        package_image = findViewById(R.id.package_image);
        title         = findViewById(R.id.title);
        tvAddress     = findViewById(R.id.tvAddress);
        tvDescription = findViewById(R.id.tvDescription);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    /**
     * Set listeners
     */
    private void setListeners() {
        fltShowMap       . setOnClickListener(this);
        tvAvailableTest  . setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fltShowMap :
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+latitude+","+longitude+"");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
               /*  intent = new Intent(CentreDetailsActivity.this,ShowOnMapsActivity.class);
                 startActivity(intent);*/
                 break;
                 case R.id.tvAvailableTest :
                     Toast.makeText(this, "No Data Available!!", Toast.LENGTH_SHORT).show();   
                // intent = new Intent(CentreDetailsActivity.this,TestDetailsActivity.class);
                // startActivity(intent);
                 break;

                 default:
                     break;
        }
    }
}
