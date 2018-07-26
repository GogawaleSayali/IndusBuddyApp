package com.dogratech.indusbuddyapp.main.activities.navmenuactivities;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.baseactivities.BaseActivity;
import com.dogratech.indusbuddyapp.main.adapters.ArticleSettingsAdapter;
import com.dogratech.indusbuddyapp.main.models.ModelArticleSettings;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends BaseActivity implements View.OnClickListener {
    private FrameLayout frameArticlePrefs;
    private RecyclerView rvArticleSettings;
    private TextView tvSave;
    private RelativeLayout rlArticlePrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initToolBar();
        initialize();
        setListeners();
    }


    private void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Settings");
    }

    private void initialize() {
        rvArticleSettings = findViewById(R.id.rvArticleSettings);
        frameArticlePrefs = findViewById(R.id.frameArticlePrefs);
        tvSave            = findViewById(R.id.tvSave);
        rlArticlePrefs    = findViewById(R.id.rlArticlePrefs);

        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        rvArticleSettings   .setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(rvArticleSettings.getContext(),
                        recyclerLayoutManager.getOrientation());
        rvArticleSettings.addItemDecoration(dividerItemDecoration);
        ArticleSettingsAdapter articleSettingsAdapter = new
        ArticleSettingsAdapter(getArticlePrefs(),this);
        rvArticleSettings.setAdapter(articleSettingsAdapter);

    }

    private void setListeners() {
        rlArticlePrefs.setOnClickListener(this);
        tvSave        .setOnClickListener(this);
    }

    private List<ModelArticleSettings> getArticlePrefs(){
        List<ModelArticleSettings> modelList = new ArrayList<ModelArticleSettings>();
        modelList.add(new ModelArticleSettings("Heart Day", false));
        modelList.add(new ModelArticleSettings("Yoga", false));
        modelList.add(new ModelArticleSettings("Diet", false));
        return modelList;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlArticlePrefs:
                                frameArticlePrefs.setVisibility(View.VISIBLE);
                break;
            case R.id.tvSave:
                                frameArticlePrefs.setVisibility(View.GONE);
                break;
        }
    }
}
