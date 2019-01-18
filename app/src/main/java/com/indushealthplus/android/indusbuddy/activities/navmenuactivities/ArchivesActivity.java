package com.indushealthplus.android.indusbuddy.activities.navmenuactivities;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.baseactivities.BaseActivityNoMenu;
import com.indushealthplus.android.indusbuddy.adapters.ArchivesAdapter;
import com.indushealthplus.android.indusbuddy.models.ArchivesModel;
import com.indushealthplus.android.indusbuddy.uitility.DeviceUtility;

import java.util.ArrayList;

public class ArchivesActivity extends BaseActivityNoMenu {
    private RecyclerView rvArchives;
    private ArchivesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indus_update);
        Toolbar toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Archives");
        initialize();
    }

    /**
     * Initialize Ui widgets.
     */
    private void initialize() {
        rvArchives = findViewById(R.id.rvArchives);
        setListeners();
        getArchives();
    }

    private void getArchives() {
        String archivePdfsStr = getIntent().getStringExtra("archives").toString();

        String[] archivesPfd = archivePdfsStr.split("\\|");
        ArrayList<ArchivesModel> data = new ArrayList<>();
        for (int j = 0; j< archivesPfd.length;j++){
            ArchivesModel archivesModel = new ArchivesModel();
            archivesModel.setPdf(archivesPfd[j]);
            String img = archivesPfd[j];
            archivesModel.setImage(img.replace(".pdf",".jpg"));
            data.add(archivesModel);
        }
        adapter = new ArchivesAdapter(ArchivesActivity.this, data);
        rvArchives.setLayoutManager(new GridLayoutManager(ArchivesActivity.this,2));
        rvArchives.addItemDecoration(new GridSpacingItemDecoration(2,
                DeviceUtility.convertDpToPx(ArchivesActivity.this,12),false));
        rvArchives.setAdapter(adapter);
    }

    /**
     * Set listeners to the ui widgets like "onClick".
     */
    private void setListeners() {
    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column
            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)
                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }


}
