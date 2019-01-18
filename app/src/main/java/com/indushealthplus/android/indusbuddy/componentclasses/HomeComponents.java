package com.indushealthplus.android.indusbuddy.componentclasses;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.apphomeactivity.AppHomeActivity;
import com.indushealthplus.android.indusbuddy.activities.baseactivities.BaseActivity;
import com.indushealthplus.android.indusbuddy.activities.navmenuactivities.SettingsActivity;
import com.indushealthplus.android.indusbuddy.adapters.HealthTipsAdapter;
import com.indushealthplus.android.indusbuddy.helper.Constatnts;
import com.indushealthplus.android.indusbuddy.managers.SharedPrefsManager;
import com.indushealthplus.android.indusbuddy.models.ContentPreviewMainModel;
import com.indushealthplus.android.indusbuddy.models.ContentsPreview;
import com.indushealthplus.android.indusbuddy.models.DataContent;
import com.indushealthplus.android.indusbuddy.models.ModelIsWellnessEvent;
import com.indushealthplus.android.indusbuddy.models.SubMenuList;
import com.indushealthplus.android.indusbuddy.retrofit.ApiClient;
import com.indushealthplus.android.indusbuddy.retrofit.ApiInterfaceGet;
import com.indushealthplus.android.indusbuddy.retrofit.ApiUrl;
import com.indushealthplus.android.indusbuddy.uitility.NetworkUtility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.indushealthplus.android.indusbuddy.activities.navmenuactivities.ConstantManager.SETTINGACTIVITY_CALL;
import static com.indushealthplus.android.indusbuddy.activities.navmenuactivities.ConstantManager.SUBCATEGORY;


public class HomeComponents extends BaseActivity {
    private static final String AUTH_PENDING = "auth_state_pending";
    protected DrawerLayout drawerLayout;
    protected ImageView ivMenu, ivHealthCheckup, ivHealthGuide, ivWelness, ivFitness, img_settingactivity_arrow, ivViewArrow,
            ivEditProfile, ivViewArrowDown, ivHealthCheckupSmall, ivHealthGuideSmall,
            ivWelnessSmall, ivFitnessSmall, ivRecords, ivReminders;
    protected RelativeLayout rlProfile, rlRateOurApp, rlHelpCenter, rlAboutUs, rlLogout, rlSettings,
            rlCheckupSmall, rlGuideSmall, rlWellnessSmall, rlFitnessSmall,
            rlStoreRecords, rlReminders, rlIndusUpdate, rlProgressMain;
    protected CardView cardHealthCheckUp, cardHealthGuide, cardWellness, cardFitness;
    protected TextView tvHealthCheckup, tvHealthGuide, tvWellness, tvFitness, tvArticleTitle,
            tvCheckUpSmall, tvGuideSmall, tvWellnessSmall, tvFitnessSmall, tvUserName, tvError;
    protected LinearLayout llMainMenuSmall, llRedeem, llMainMenuStrip;
    protected SlidingUpPanelLayout sliding_layout;
    protected SharedPrefsManager prefsManager;

    protected LinearLayout llBottom, llTop;
    protected static String CHECKUP_MENU, GUIDE_MENU, WELLNESS_MENU, FITNESS_MENU;
    protected ArrayList<SubMenuList> subMenuCheckup = new ArrayList<>();
    protected ArrayList<SubMenuList> subMenuGuide = new ArrayList<>();
    protected ArrayList<SubMenuList> subMenuWellness = new ArrayList<>();
    protected ArrayList<SubMenuList> subMenuFitness = new ArrayList<>();
    private RecyclerView rvArticles;
    private String selectedLanguage = "eng";
    private boolean authInProgress = false;
    private HealthTipsAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            authInProgress = savedInstanceState.getBoolean(AUTH_PENDING);
        }
    }

    /************************
     * Initialize Ui widgets*
     ************************/
    protected void initialize() {
        CHECKUP_MENU = GUIDE_MENU = WELLNESS_MENU = FITNESS_MENU = "";
        if (prefsManager == null) {
            prefsManager = SharedPrefsManager.getSharedInstance(HomeComponents.this);
        }
        drawerLayout = findViewById(R.id.drawer_layout);
        sliding_layout = findViewById(R.id.sliding_layout);

        /*RelativeLayout*/
        rlProfile = findViewById(R.id.rlProfile);
        rlRateOurApp = findViewById(R.id.rlRateOurApp);
        rlHelpCenter = findViewById(R.id.rlHelpCenter);
        rlAboutUs = findViewById(R.id.rlAboutUs);
        rlLogout = findViewById(R.id.rlLogout);
        rlSettings = findViewById(R.id.rlSettings);
        rlStoreRecords = findViewById(R.id.rlStoreRecords);
        rlIndusUpdate = findViewById(R.id.rlIndusUpdate);
        rlReminders = findViewById(R.id.rlReminders);

        cardHealthCheckUp = findViewById(R.id.rlHealthCheckUp);
        cardHealthGuide = findViewById(R.id.rlHealthGuide);
        cardWellness = findViewById(R.id.rlWellness);
        cardFitness = findViewById(R.id.rlFitness);
        rlCheckupSmall = findViewById(R.id.rlCheckupSmall);
        rlGuideSmall = findViewById(R.id.rlGuideSmall);
        rlWellnessSmall = findViewById(R.id.rlWellnessSmall);
        rlFitnessSmall = findViewById(R.id.rlFitnessSmall);
        rlProgressMain = findViewById(R.id.rlProgressMain);

        /*ImageView*/
        ivMenu = findViewById(R.id.ivMenu);
        ivHealthCheckup = findViewById(R.id.ivHealthCheckup);
        ivHealthGuide = findViewById(R.id.ivHealthGuide);
        ivWelness = findViewById(R.id.ivWelness);
        ivFitness = findViewById(R.id.ivFitness);
        ivHealthCheckupSmall = findViewById(R.id.ivHealthCheckupSmall);
        ivHealthGuideSmall = findViewById(R.id.ivHealthGuideSmall);
        ivWelnessSmall = findViewById(R.id.ivWelnessSmall);
        ivFitnessSmall = findViewById(R.id.ivFitnessSamll);

        ivViewArrow = findViewById(R.id.ivViewArrow);
        ivViewArrowDown = findViewById(R.id.ivViewArrowDown);
        ivEditProfile = findViewById(R.id.ivEditProfile);
        ivRecords = findViewById(R.id.ivRecords);
        ivReminders = findViewById(R.id.ivRemiders);

        /*LinearLayout*/
        llTop = findViewById(R.id.llTop);
        llBottom = findViewById(R.id.llBottom);
        llMainMenuStrip = findViewById(R.id.llMainMenuStrip);
        rvArticles = findViewById(R.id.rvArticles);
        llMainMenuSmall = findViewById(R.id.llMainMenuStrip);
        llRedeem = findViewById(R.id.llRedeem);

        /*TextView*/
        tvError = findViewById(R.id.tvError);
        tvUserName = findViewById(R.id.tvUserName);
        tvHealthCheckup = findViewById(R.id.tvHealthCheckup);
        tvHealthGuide = findViewById(R.id.tvHealthGuide);
        tvWellness = findViewById(R.id.tvWellness);
        tvFitness = findViewById(R.id.tvFitness);
        tvArticleTitle = findViewById(R.id.tvArticleTitle);
        tvCheckUpSmall = findViewById(R.id.tvCheckUpSmall);
        tvGuideSmall = findViewById(R.id.tvGuideSmall);
        tvWellnessSmall = findViewById(R.id.tvWellnessSmall);
        tvFitnessSmall = findViewById(R.id.tvFitnessSmall);
        img_settingactivity_arrow = findViewById(R.id.img_settingactivity_arrow);

        /*Change color of image bitmap */
        changeAllImagesColor();


        if (selectedLanguage.equalsIgnoreCase("eng")) {
            setLanguageEnglishText();
        }

        if (AppHomeActivity.ROLE.equalsIgnoreCase("C")) {
            checkWellnessEvent();
        } else {
            llRedeem.setVisibility(View.GONE);
        }

        String welcomeNote = "Welcome , " + prefsManager.getData(getString(R.string.username));
        //tvUserName.setText(welcomeNote);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(HomeComponents.this);
        rvArticles.setLayoutManager(mLayoutManager);
        rvArticles.setItemAnimator(new DefaultItemAnimator());

        loadComments();

        String settingactivity = prefsManager.getData(SETTINGACTIVITY_CALL);
        if (settingactivity.equalsIgnoreCase(Constatnts.NODATA)) {
            startActivity(new Intent(HomeComponents.this, SettingsActivity.class));
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        loadComments();
    }

    private void loadComments() {
        if (NetworkUtility.isNetworkAvailable(HomeComponents.this)) {
            ApiInterfaceGet interfaceGet = ApiClient.getClient(ApiClient.BASE_URL_TYEP_INDUS)
                    .create(ApiInterfaceGet.class);
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(HomeComponents.this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custom_loader, null);
            dialogBuilder.setView(dialogView);
            final AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.setCancelable(false);
            alertDialog.show();
            String url = ApiUrl.Base_URL_INDUS + ApiUrl.contentPreview;
            interfaceGet.getContentPreview(url).enqueue(new Callback<ContentPreviewMainModel>() {
                @Override
                public void onResponse(Call<ContentPreviewMainModel> call, Response<ContentPreviewMainModel> response) {
                    if (alertDialog.isShowing()) {
                        alertDialog.hide();
                    }
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            ContentPreviewMainModel previewMainModel = response.body();
                            DataContent dataContent = previewMainModel.getData();
                            ArrayList<ContentsPreview> contentsPreview = new ArrayList<>();
                            ArrayList<ContentsPreview> dataContentList = dataContent.getContents();
                            //Array list for checked items

                            String settingactivity = prefsManager.getData(SETTINGACTIVITY_CALL);
                            if (settingactivity.equalsIgnoreCase(Constatnts.NODATA)) {
                                contentsPreview.addAll(dataContentList);
                            } else {
                                String subCate = prefsManager.getData(SUBCATEGORY);
                                if (!subCate.equalsIgnoreCase(Constatnts.NODATA)) {
                                    Type type = new TypeToken<ArrayList<ArrayList<HashMap<String, String>>>>() {
                                    }.getType();
                                    Gson gson = new Gson();
                                    ArrayList<ArrayList<HashMap<String, String>>> arrayLists = gson.fromJson(subCate, type);

                                    if (arrayLists != null) {
                                        for (int i = 0; i < arrayLists.size(); i++) {
                                            ArrayList<HashMap<String, String>> mapArrayList = arrayLists.get(i);
                                            for (int j = 0; j < mapArrayList.size(); j++) {

                                                HashMap<String, String> hashMap = mapArrayList.get(j);
                                                if (hashMap.size() > 0) {
                                                    if (hashMap.get("is_checked").equalsIgnoreCase("YES")) {
                                                        for (int k = 0; k < dataContentList.size(); k++) {
                                                            if (hashMap.get("sub_category_name").equalsIgnoreCase(dataContentList.get(k).getSubCategories())) {
                                                                contentsPreview.add(dataContentList.get(k));
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            Log.d("TAG", "onResponse: ");
                                        }
                                    }
                                    // contentsPreview = dataContent.getContents();
                                }
                            }
                                if (contentsPreview != null) {

                                    if (adapter == null) {
                                        adapter = new HealthTipsAdapter(HomeComponents.this, contentsPreview);
                                        rvArticles.setAdapter(adapter);
                                    } else {
                                        adapter.updateList(contentsPreview);
                                    }
                                   if (contentsPreview.size() >= 1) {
                                        llMainMenuStrip.setVisibility(View.VISIBLE);
                                    } else {
                                        llMainMenuStrip.setVisibility(View.INVISIBLE);
                                    }
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<ContentPreviewMainModel> call, Throwable t) {
                    if (alertDialog.isShowing()) {
                        alertDialog.hide();
                    }
                }
            });

        } else {
            snackInternet();
        }
    }

    public void snackInternet() {
        Snackbar snackbar = Snackbar
                .make(rlProgressMain, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
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

    private void checkWellnessEvent() {
        ApiInterfaceGet interfaceGet = ApiClient.getClient(ApiClient.BASE_URL_TYEP_WELLNESS).create(ApiInterfaceGet.class);
        String userId = prefsManager.getData(getString(R.string.shars_userid));
        String url = ApiUrl.Base_URL_WELLNESS + ApiUrl.isEvent + userId;
        interfaceGet.isEventAvail(url).enqueue(new Callback<ModelIsWellnessEvent>() {
            @Override
            public void onResponse(Call<ModelIsWellnessEvent> call, Response<ModelIsWellnessEvent> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ModelIsWellnessEvent isWellnessEvent = response.body();
                        if (isWellnessEvent.getStatusCode() == 1) {
                            if (isWellnessEvent.getIsEvent().equalsIgnoreCase("Yes")) {
                                llRedeem.setVisibility(View.VISIBLE);
                            } else {
                                llRedeem.setVisibility(View.GONE);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelIsWellnessEvent> call, Throwable t) {

            }
        });
    }


    /***********************
     * Set image and color.*
     ***********************/
    private void changeAllImagesColor() {
        changeImageColor("primary", ivHealthCheckup);
        changeImageColor("primary", ivHealthGuide);
        changeImageColor("primary", ivWelness);
        changeImageColor("primary", ivFitness);
        changeImageColor("primary", ivRecords);
        changeImageColor("primary", ivReminders);

        changeImageColor("white", ivHealthCheckupSmall);
        changeImageColor("white", ivHealthGuideSmall);
        changeImageColor("white", ivWelnessSmall);
        changeImageColor("white", ivFitnessSmall);
        changeImageColor("white", ivViewArrow);
        changeImageColor("white", ivViewArrowDown);
    }


    /************************************
     * Fill image with color.           *
     * @param color : color tb be filled*
     * @param imageView : ImageView     *
     ************************************/
    private void changeImageColor(String color, ImageView imageView) {
        if (color.equalsIgnoreCase("primary")) {
            imageView.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);
        } else if (color.equalsIgnoreCase("white")) {
            imageView.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);
        }

    }

    /******************************
     * Set selected language text;*
     ******************************/
    private void setLanguageEnglishText() {
        tvArticleTitle.setText(R.string.articles);
        tvCheckUpSmall.setText(R.string.mini_checkup);
        tvGuideSmall.setText(R.string.mini_guide);
        tvWellnessSmall.setText(R.string.mini_wellness);
        tvFitnessSmall.setText(R.string.mini_fitness);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);
        } else if (sliding_layout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED) {
            sliding_layout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
/*
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(AUTH_PENDING, authInProgress);
    }*/
}
