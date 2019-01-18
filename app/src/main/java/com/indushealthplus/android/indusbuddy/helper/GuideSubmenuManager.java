package com.indushealthplus.android.indusbuddy.helper;

import android.graphics.Color;
import android.net.Uri;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.baseactivities.BaseActivity;
import com.indushealthplus.android.indusbuddy.activities.healthguide.guidefragments.ByParameterFragment;
import com.indushealthplus.android.indusbuddy.activities.healthguide.guidefragments.ByVisitFragment;
import com.indushealthplus.android.indusbuddy.activities.healthguide.guidefragments.DoctorCommentFragment;
import com.indushealthplus.android.indusbuddy.activities.healthguide.guidefragments.HealthReportsFragment;
import com.indushealthplus.android.indusbuddy.activities.healthguide.guidefragments.HRAFragment;
import com.indushealthplus.android.indusbuddy.activities.healthguide.guidefragments.TrackParameter;
import com.indushealthplus.android.indusbuddy.models.HRAAnswerMainModel;
import com.indushealthplus.android.indusbuddy.models.SubMenuList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import goldzweigapps.tabs.Builder.EasyTabsBuilder;
import goldzweigapps.tabs.Colors.EasyTabsColors;
import goldzweigapps.tabs.Interface.TabsListener;
import goldzweigapps.tabs.Items.TabItem;
import goldzweigapps.tabs.View.EasyTabs;

/**
 * Created by amolr on 23/4/18.
 */

public class GuideSubmenuManager extends BaseActivity implements
             TrackParameter.OnFragmentInteractionListener,HRAFragment.OnFragmentInteractionListener,
             HealthReportsFragment.OnFragmentInteractionListener, DoctorCommentFragment.OnFragmentInteractionListener,
             ByVisitFragment.OnFragmentInteractionListener,ByParameterFragment.OnFragmentInteractionListener{

    protected static final String SUBMENU_TRACK_PARAMS = "Track Parameter";
    protected static final String SUBMENU_HRA          = "HRA";
    protected static final String SUBMENU_HEALTH_REP   = "Health Report";
    protected static final String SUBMENU_STORE_RECS   = "Store Records";
    protected static final String SUBMENU_DR_COMMENTS  = "Doctor Comments";
    protected static final String SUBMENU_HEALTH_TIPS  = "Health Tips";

    protected TextView tvError;
    protected EasyTabs eTabHealthGuide;
    protected ArrayList<HRAAnswerMainModel> answerArray = new ArrayList<>();
    /*******************************************************************
     * This method create and att tabs in UI according to submenu list.*
     *******************************************************************/
    protected void setSubMenuTabs() {
        EasyTabsBuilder easyTabsBuilder = new EasyTabsBuilder();
        Gson gson = new Gson();
        String jsonStr = getIntent().getExtras().getString("jsonStrSubmenu","nodata");
        if (!jsonStr.equalsIgnoreCase("nodata")) {
            ArrayList<SubMenuList> subMenuList = gson.fromJson(jsonStr, new TypeToken<List<SubMenuList>>(){}.getType());
            TabItem[] tabsParams = new TabItem[subMenuList.size()];
            for (int i  = 0 ; i < subMenuList.size();i++){
                switch (subMenuList.get(i).getSubMenuName()){
                    case SUBMENU_TRACK_PARAMS:
                        tabsParams[i] = new TabItem(new TrackParameter(), SUBMENU_TRACK_PARAMS);
                        break;
                    case SUBMENU_HRA:
                        String data = gson.toJson(answerArray);
                        tabsParams[i] = new TabItem(new HRAFragment().newInstance(data,""), SUBMENU_HRA);
                        break;
                    case SUBMENU_HEALTH_REP:
                        tabsParams[i] = new TabItem(new HealthReportsFragment(), SUBMENU_HEALTH_REP);
                        break;
                    case SUBMENU_STORE_RECS:
                       //TODO - Remove
                       // tabsParams[i] = new TabItem(new HealthReportsFragment(), SUBMENU_STORE_RECS);
                        break;
                    case SUBMENU_DR_COMMENTS:
                        tabsParams[i] = new TabItem(new DoctorCommentFragment(), SUBMENU_DR_COMMENTS);
                        break;
                    case SUBMENU_HEALTH_TIPS:
                       // TODO -Remove
                       // tabsParams[i] = new TabItem(new HealthTips(), SUBMENU_HEALTH_TIPS);
                        break;
                }
            }
            try {
                easyTabsBuilder.with(eTabHealthGuide).addTabs(tabsParams)
                        .setTabsBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null))
                        .setIndicatorColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, null))
                        .setTextColors(EasyTabsColors.White, Color.parseColor("#70FFFFFF"))
                        .setTabLayoutScrollable(true)
                        .withListener(new TabsListener() {
                            @Override
                            public void onScreenPosition(int position) {
                                //Log.d("tag", String.valueOf(position));
                            }
                        }).Build();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            tvError.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
