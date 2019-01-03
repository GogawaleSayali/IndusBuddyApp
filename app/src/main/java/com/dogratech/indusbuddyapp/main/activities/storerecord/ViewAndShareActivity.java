package com.dogratech.indusbuddyapp.main.activities.storerecord;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.baseactivities.BaseActivity;
import com.dogratech.indusbuddyapp.main.adapters.RecordViewShareAdapter;
import com.dogratech.indusbuddyapp.main.models.ModelRedordDetails;
import com.dogratech.indusbuddyapp.main.models.Model_Item_Report;
import com.dogratech.indusbuddyapp.main.models.ViewRecordSupportModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ViewAndShareActivity extends BaseActivity {
    private RecyclerView rvViewRecords;
    private ViewRecordSupportModel viewRecordSupportModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_and_share);

        inititialize();
      }

    private void inititialize() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        rvViewRecords = findViewById(R.id.rvViewRecords);
        Gson gson = new Gson();
         viewRecordSupportModel = gson.fromJson(getIntent().getStringExtra("myjson"), ViewRecordSupportModel.class);
        initializeToolBar(toolbar,viewRecordSupportModel.getName());
        ArrayList<Model_Item_Report> data = viewRecordSupportModel.getRecordsList();

        RecordViewShareAdapter recordsAdapter = new RecordViewShareAdapter(ViewAndShareActivity.this,data);
        RecyclerView      . LayoutManager mLayoutManager = new LinearLayoutManager(ViewAndShareActivity.this);
        rvViewRecords.setLayoutManager(mLayoutManager);
        rvViewRecords.setAdapter(recordsAdapter);
        setListeners();
    }

    private void setListeners() {

    }


}
