package com.indushealthplus.android.indusbuddy.helper;

import android.graphics.Color;
import android.net.Uri;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.baseactivities.BaseActivity;
import com.indushealthplus.android.indusbuddy.activities.healthcheckup.checkupfragments.AvailedPackagesFragment;
import com.indushealthplus.android.indusbuddy.activities.healthcheckup.checkupfragments.CurrentPackagesFragment;
import com.indushealthplus.android.indusbuddy.activities.healthcheckup.checkupfragments.DashboardFragment;
import com.indushealthplus.android.indusbuddy.activities.healthcheckup.checkupfragments.InstructionCallFragment;
import com.indushealthplus.android.indusbuddy.activities.healthcheckup.checkupfragments.MlmWithoutPkgFragment;
import com.indushealthplus.android.indusbuddy.activities.healthcheckup.checkupfragments.MyAppointmentFragment;
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

public class CheckUpSubMenuManager extends BaseActivity implements
        DashboardFragment.OnFragmentInteractionListener,
        CurrentPackagesFragment.OnFragmentInteractionListener,
        AvailedPackagesFragment.OnFragmentInteractionListener,
        MyAppointmentHelperWebServices.OnFragmentInteractionListener,
        InstructionCallFragment.OnFragmentInteractionListener,
        MlmWithoutPkgFragment.OnFragmentInteractionListener{
    private TextView tvCentreLocator;
    protected EasyTabs tabs;
    protected TextView tvError;
    protected static final String SUBMENU_MY_PKGS = "My Packages";
    protected static final String SUBMENU_AVAILED_PKGS = "Availed Packages";
    protected static final String SUBMENU_MY_APPTMNT = "My Appointments";
    protected static final String SUBMENU_STATUS = "Status";
    protected static final String SUBMENU_INSTRUCTION = "Instruction call";
    /**Toolbar and Action bar common for all activities which extends this class**/
    protected Toolbar toolbar;
    protected ActionBar actionBar;

    protected void showTabNemu() {
        EasyTabsBuilder easyTabsBuilder = new EasyTabsBuilder();
        Gson gson = new Gson();
        String jsonStr = getIntent().getExtras().getString("jsonStrSubmenu","nodata");
        if (!jsonStr.equalsIgnoreCase("nodata")) {
            ArrayList<SubMenuList> subMenuList = gson.fromJson(jsonStr, new TypeToken<List<SubMenuList>>(){}.getType());
            TabItem[] tabsParams = new TabItem[subMenuList.size()];
            for (int i  = 0 ; i < subMenuList.size();i++){
                switch (subMenuList.get(i).getSubMenuName()){
                    case SUBMENU_MY_PKGS:
                        //if (AppHomeActivity.ROLE.equalsIgnoreCase("MW")) {
                        //    tabsParams[i] = new TabItem(new MlmWithoutPkgFragment(), SUBMENU_MY_PKGS);
                       // }else {
                            tabsParams[i] = new TabItem(new CurrentPackagesFragment(), SUBMENU_MY_PKGS);
                      //  }
                        break;
                    case SUBMENU_AVAILED_PKGS:
                        tabsParams[i] = new TabItem(new AvailedPackagesFragment(), SUBMENU_AVAILED_PKGS);
                        break;
                    case SUBMENU_MY_APPTMNT:
                            tabsParams[i] = new TabItem(new MyAppointmentFragment(), SUBMENU_MY_APPTMNT);
                        break;
                    case SUBMENU_STATUS:
                        tabsParams[i] = new TabItem(new DashboardFragment(), SUBMENU_STATUS);
                        break;

                    case SUBMENU_INSTRUCTION:
                        tabsParams[i] = new TabItem(new InstructionCallFragment(), SUBMENU_INSTRUCTION);
                        break;

                        default:
                        tabsParams[i] = new TabItem(new MlmWithoutPkgFragment(), SUBMENU_STATUS);
                        break;
                }
            }
            try {
                easyTabsBuilder.with(tabs).addTabs(tabsParams)
                        .setTabsBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null))
                        .setIndicatorColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, null))
                        .setTextColors(EasyTabsColors.White, Color.parseColor("#70FFFFFF"))
                        .setTabLayoutScrollable(true)
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
        }else {
            tvError.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
