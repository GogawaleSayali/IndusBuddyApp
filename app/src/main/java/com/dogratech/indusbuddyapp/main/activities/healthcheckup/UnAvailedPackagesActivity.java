package com.dogratech.indusbuddyapp.main.activities.healthcheckup;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.adapters.PackageAdapter;
import com.dogratech.indusbuddyapp.main.listeners.RecyclerItemClickListener;
import com.dogratech.indusbuddyapp.main.models.Model_Package;

import java.util.ArrayList;
import java.util.List;

public class UnAvailedPackagesActivity extends AppCompatActivity implements RecyclerItemClickListener.OnItemClickListener {
    private RecyclerView recyclerView;
    private PackageAdapter adapter;
    private List<Model_Package> packages;
    private Toolbar toolbar ;
    private RecyclerView.LayoutManager mLayoutManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_un_availed_package);
        initialise();
       // initCollapsingToolbar();
        initialiseClass();
        setListener();
        prepareAlbums();
/*
        try {
            Glide.with(this).load(R.drawable.health).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
    }

    private void initialiseClass() {
        packages              = new ArrayList<>();
        adapter               = new PackageAdapter(this, packages);
        mLayoutManager        = new GridLayoutManager(this, 1);
        recyclerView          . setLayoutManager(mLayoutManager);
        //recyclerView          . addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(2), true));
        recyclerView          . setItemAnimator(new DefaultItemAnimator());
        recyclerView          . setAdapter(adapter);
    }

    private void initialise() {
        toolbar               = (Toolbar) findViewById(R.id.toolbar);
        recyclerView          = (RecyclerView) findViewById(R.id.recycler_view);
        setSupportActionBar(toolbar);
        getSupportActionBar() . setHomeButtonEnabled(true); // disable the button
        getSupportActionBar() . setDisplayHomeAsUpEnabled(true); // remove the left caret
        getSupportActionBar() . setDisplayShowHomeEnabled(true);
        getSupportActionBar() . setTitle("My Packages");
    }
    private void setListener() {
        recyclerView          . addOnItemTouchListener(new RecyclerItemClickListener(
                                this, this));
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
               finish();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
/*
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);
        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.acitity_my_availed_packages));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(getString(R.string.acitity_my_availed_packages));
                    isShow = false;
                }
            }
        });
    }
*/

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.package_four,
                R.drawable.package_two,
                R.drawable.package_five,
                R.drawable.packahe_one,
                R.drawable.package_five,
                R.drawable.package_three,
                R.drawable.packahe_one,
                R.drawable.package_five,
                R.drawable.package_three,
                R.drawable.package_four,
                R.drawable.package_five};

        for (int i = 0;i<covers.length;i++){
            Model_Package a = new Model_Package("Package Name "+i,  covers[i]);
            packages.add(a);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {
        startActivity(new Intent(UnAvailedPackagesActivity.this,UnavailedPackageDetailsActivity.class));
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
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

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
