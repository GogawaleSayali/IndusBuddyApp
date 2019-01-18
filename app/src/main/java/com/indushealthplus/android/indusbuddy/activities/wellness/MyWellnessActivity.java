package com.indushealthplus.android.indusbuddy.activities.wellness;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.wellness.wellnessfragments.MyEventFragment;
import com.indushealthplus.android.indusbuddy.activities.wellness.wellnessfragments.PendingEventFragment;
import com.indushealthplus.android.indusbuddy.helper.Constatnts;
import com.indushealthplus.android.indusbuddy.componentclasses.WellnessComponents;
import com.indushealthplus.android.indusbuddy.managers.SharedPrefsManager;
import com.indushealthplus.android.indusbuddy.models.EventItemModel;
import com.indushealthplus.android.indusbuddy.models.EventsMainModel;
import com.indushealthplus.android.indusbuddy.models.SubMenuList;
import com.indushealthplus.android.indusbuddy.retrofit.ApiClient;
import com.indushealthplus.android.indusbuddy.retrofit.ApiInterfaceGet;
import com.indushealthplus.android.indusbuddy.retrofit.ApiUrl;
import com.indushealthplus.android.indusbuddy.uitility.NetworkUtility;
import com.google.gson.Gson;

import java.util.ArrayList;

import goldzweigapps.tabs.Builder.EasyTabsBuilder;
import goldzweigapps.tabs.Colors.EasyTabsColors;
import goldzweigapps.tabs.Interface.TabsListener;
import goldzweigapps.tabs.Items.TabItem;
import goldzweigapps.tabs.View.EasyTabs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amolr on 23/3/18.
 */

public class MyWellnessActivity extends WellnessComponents implements PendingEventFragment.OnFragmentInteractionListener,
        MyEventFragment.OnFragmentInteractionListener {
    protected static final String WELLNESS_PENDING_EVENT = "Pending Event";
    protected static final String WELLNESS_MY_EVENT = "My Event";
    protected EasyTabs tabs;
    protected TextView tvError;
    private ProgressBar progressBar;
    private PendingEventFragment pendingEventFragment;
    private MyEventFragment myEventFragment;
    private ArrayList<EventItemModel> itemModelsPending,itemModelsConfirmed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellness);
        Toolbar toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"My Wellness");
        initialize();
    }

    /****************************
     * Initialize all ui widgets*
     ****************************/
    public void initialize() {
        tabs        = findViewById(R.id.EasyTabs);
        tvError     = findViewById(R.id.tvError);
        progressBar = findViewById(R.id.progressBar);
        prepareData();
    }

    /****************************
     * Show tab layout ans tabs.*
     ****************************/
    protected void showTabNemu() {
        EasyTabsBuilder easyTabsBuilder = new EasyTabsBuilder();
            ArrayList<SubMenuList> subMenuList = new ArrayList<>();
            SubMenuList menuList = new SubMenuList(WELLNESS_PENDING_EVENT);
            SubMenuList menuList1 = new SubMenuList(WELLNESS_MY_EVENT);
            subMenuList.add(menuList);
            subMenuList.add(menuList1);
            Gson gson = new Gson();
            TabItem[] tabsParams = new TabItem[subMenuList.size()];
            for (int i  = 0 ; i < subMenuList.size();i++){
                switch (subMenuList.get(i).getSubMenuName()){
                    case WELLNESS_PENDING_EVENT:
                        String pending = gson.toJson(itemModelsPending);
                        pendingEventFragment = PendingEventFragment.newInstance(pending,"");
                        tabsParams[i] = new TabItem(pendingEventFragment, WELLNESS_PENDING_EVENT);
                        break;
                    case WELLNESS_MY_EVENT:
                        String confirmed = gson.toJson(itemModelsConfirmed);
                        myEventFragment = MyEventFragment.newInstance(confirmed,"");
                        tabsParams[i] = new TabItem(myEventFragment, WELLNESS_MY_EVENT);
                        break;
                }
            }
            try {
                easyTabsBuilder.with(tabs).addTabs(tabsParams)
                        .setTabsBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null))
                        .setIndicatorColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, null))
                        .setTextColors(EasyTabsColors.White, Color.parseColor("#70FFFFFF"))
                        .setTabLayoutScrollable(true)
                        .setTabLayoutScrollable(false)
                        .withListener(new TabsListener() {
                            @Override
                            public void onScreenPosition(int position) {
                                Log.d("tag", String.valueOf(position));
                            }
                        })
                        .Build();
            }catch (Exception e){
                e.printStackTrace();
            }
    }

     /*********************************************************************
     * Hit wellness events webservice and show pending and my events lists.*
      *********************************************************************/
    private void prepareData() {
        if (NetworkUtility.isNetworkAvailable(MyWellnessActivity.this)) {
            progressBar.setVisibility(View.VISIBLE);
            SharedPrefsManager prefsManager = SharedPrefsManager
                    .getSharedInstance(MyWellnessActivity.this);
            String userId = prefsManager.getData(getString(R.string.shars_userid));
            //userId = "179997";
            String url = ApiUrl.Base_URL_WELLNESS + ApiUrl.event + userId;
            ApiInterfaceGet interfaceGet = ApiClient.getClient(ApiClient.BASE_URL_TYEP_WELLNESS)
                    .create(ApiInterfaceGet.class);
            interfaceGet.getEvents(url).enqueue(new Callback<EventsMainModel>() {
                @Override
                public void onResponse(Call<EventsMainModel> call, Response<EventsMainModel> response) {
                    progressBar.setVisibility(View.GONE);
                    if (response.body() != null) {
                        EventsMainModel mainModel = response.body();
                        if (mainModel.getStatusCode() == Constatnts.S_CODE_1) {
                            itemModelsPending = new ArrayList<>();
                            itemModelsConfirmed = new ArrayList<>();
                            for (EventItemModel itemModel : mainModel.getGrpEventItemModels()) {
                                if (itemModel.getStatus().equalsIgnoreCase("Pending")) {
                                    itemModelsPending.add(itemModel);
                                } else {
                                    itemModelsConfirmed.add(itemModel);
                                }
                            }
                            for (EventItemModel itemModel : mainModel.getIndividualEventItemModel()) {
                                if (itemModel.getStatus().equalsIgnoreCase("Pending")) {
                                    itemModelsPending.add(itemModel);
                                } else {
                                    itemModelsConfirmed.add(itemModel);
                                }
                            }
                            if (pendingEventFragment == null && myEventFragment == null) {
                                showTabNemu();
                            } else {
                                if (pendingEventFragment != null) {
                                    pendingEventFragment.updateDate(itemModelsPending);
                                }
                                if (myEventFragment != null) {
                                    myEventFragment.updateDate(itemModelsConfirmed);
                                }
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<EventsMainModel> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    t.printStackTrace();
                }
            });
        }else {
            snackInternet();
        }
    }

    public void snackInternet(){
        Snackbar snackbar = Snackbar
                .make(tabs, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                prepareData();
            }
            }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }


    private void showDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MyWellnessActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_event_sort, null);
        dialogBuilder.setView(dialogView);
        TextView tv_filter = dialogView.findViewById(R.id.tv_filter);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        tv_filter. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        alertDialog.show();

    }
}
