package com.indushealthplus.android.indusbuddy.activities.healthcheckup;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.apphomeactivity.AppHomeActivity;
import com.indushealthplus.android.indusbuddy.adapters.CentreAdapter;
import com.indushealthplus.android.indusbuddy.models.ModelCentre;
import com.indushealthplus.android.indusbuddy.retrofit.ApiClient;
import com.indushealthplus.android.indusbuddy.retrofit.ApiInterfaceGet;
import com.indushealthplus.android.indusbuddy.uitility.NetworkUtility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CentreSelectionActivity extends AppCompatActivity/* implements
    RecyclerItemClickListener.OnItemClickListener*/{
    private RecyclerView recyclerView;
    private CentreAdapter adapter;
    private List<ModelCentre> centres;
    private  Toolbar toolbar ;
    private RecyclerView.LayoutManager mLayoutManager ;
    private SearchView searchView;
    private String cityId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centre_locator);
        initialise();
        initialiseClass();
        setListener();
        cityId = getIntent().getExtras().getString("cityId");
        getCenters();
        /*try {
            Glide.with(this).load(R.drawable.nutri).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }


    private void initialiseClass() {
        centres               = new ArrayList<>();
        adapter               = new CentreAdapter(this, centres);
        mLayoutManager        = new GridLayoutManager(this, 1);
        recyclerView          . setLayoutManager(mLayoutManager);
        //recyclerView          . addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(5), true));
        recyclerView          . setItemAnimator(new DefaultItemAnimator());
        recyclerView          . setAdapter(adapter);

    }

    private void initialise() {
        toolbar               = findViewById(R.id.toolbar);
        recyclerView          = findViewById(R.id.recycler_view);
                                setSupportActionBar(toolbar);
        getSupportActionBar() . setHomeButtonEnabled(true); // disable the button
        getSupportActionBar() . setDisplayHomeAsUpEnabled(true); // remove the left caret
        getSupportActionBar() . setDisplayShowHomeEnabled(true);
        getSupportActionBar() . setTitle("Select Centre");
    }

    /**
     * Set listeners
     */
    private void setListener() {
       /* recyclerView          . addOnItemTouchListener(new RecyclerItemClickListener(
                this, this));*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.centre_selection, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                adapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
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
           case R.id.action_home:
               Intent i = new Intent(this, AppHomeActivity.class);
               i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT|Intent.FLAG_ACTIVITY_CLEAR_TASK);
               //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               startActivity(i);
               finish();
               break;
           case R.id.action_search:
                   return true;
               default:
                   break;
       }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Adding few albums for testing
     */
    private void getCenters() {
            if (NetworkUtility.isNetworkAvailable(CentreSelectionActivity.this)) {
                ApiInterfaceGet interfaceGet = ApiClient.getClient(ApiClient.BASE_URL_TYEP_ICONNECT)
                        .create(ApiInterfaceGet.class);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(CentreSelectionActivity.this);
                LayoutInflater inflater = this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_loader, null);
                dialogBuilder.setView(dialogView);
                final AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.setCancelable(false);
                try {
                    alertDialog.show();
                }catch (Exception e){
                    e.printStackTrace();
                }
                cityId = "1";
                if (!cityId.equalsIgnoreCase("")) {
                    try {
                        interfaceGet.getCenters(cityId).enqueue(new Callback<ArrayList<ModelCentre>>() {
                            @Override
                            public void onResponse(Call<ArrayList<ModelCentre>> call, Response<ArrayList<ModelCentre>> response) {
                                if (alertDialog.isShowing()) {
                                    alertDialog.hide();
                                }
                                if (response.isSuccessful()) {
                                    if (response.body() != null) {
                                        ArrayList<ModelCentre> ModelCentre = response.body();
                                        if (ModelCentre != null) {
                                            if (ModelCentre.size() > 0) {
                                                if (adapter == null) {
                                                    adapter = new CentreAdapter(CentreSelectionActivity.this, centres);
                                                    recyclerView.setAdapter(adapter);
                                                } else {
                                                    adapter.updateList(ModelCentre);
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<ArrayList<ModelCentre>> call, Throwable t) {
                                if (alertDialog.isShowing()) {
                                    alertDialog.hide();
                                }
                            }
                        });
                    }catch (Exception e){
                        if (alertDialog.isShowing()) {
                            alertDialog.hide();
                        }
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(CentreSelectionActivity.this, "Network not available", Toast.LENGTH_SHORT).show();
                }
            }else {
                snackInternet();
            }
/*        for(int i = 0 ;i <15 ;i++){
            Model_health_tips health_tips = new Model_health_tips(getString(R.string.tips)+" "+i);
            tipsList.add(health_tips);
        }
      */

    }
    public void snackInternet(){
        Snackbar snackbar = Snackbar
                .make(recyclerView, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });

        snackbar.setActionTextColor(Color.RED);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
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
