package com.dogratech.indusbuddyapp.main.activities.storerecord;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.baseactivities.BaseActivity;
import com.dogratech.indusbuddyapp.main.adapters.RecordViewShareAdapter;
import com.dogratech.indusbuddyapp.main.models.ModelRedordDetails;

import java.util.ArrayList;

public class ViewAndShareActivity extends BaseActivity {
    private RecyclerView rvViewRecords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_and_share);

        inititialize();
      }

    private void inititialize() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        rvViewRecords = findViewById(R.id.rvViewRecords);
        Bundle bundle = getIntent().getExtras();
        String type = bundle.getString("type");
        String addedOn = bundle.getString("addedOn");
        initializeToolBar(toolbar,type);
        ArrayList<ModelRedordDetails> data = new ArrayList<>();
        ModelRedordDetails redordDetails = new ModelRedordDetails();
        redordDetails.setFileName(bundle.getString("data"));
        redordDetails.setDate(bundle.getString("addedOn"));
        redordDetails.setFilePath(bundle.getString("data"));
        redordDetails.setFileType(type);
        data.add(redordDetails);
        RecordViewShareAdapter recordsAdapter = new RecordViewShareAdapter(ViewAndShareActivity.this,data);
        RecyclerView      . LayoutManager mLayoutManager = new LinearLayoutManager(ViewAndShareActivity.this);
        rvViewRecords.setLayoutManager(mLayoutManager);
        rvViewRecords.setAdapter(recordsAdapter);
        setListeners();
    }

    private void setListeners() {

    }


}
