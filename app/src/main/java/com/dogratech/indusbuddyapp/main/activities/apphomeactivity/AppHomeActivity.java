package com.dogratech.indusbuddyapp.main.activities.apphomeactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.View;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.fitness.FitnessMenuActivity;
import com.dogratech.indusbuddyapp.main.activities.fitness.MyFitnessActivity;
import com.dogratech.indusbuddyapp.main.activities.healthcheckup.MyHealthChekUpActivity;
import com.dogratech.indusbuddyapp.main.activities.healthguide.MyHealthGuideActivity;
import com.dogratech.indusbuddyapp.main.activities.navmenuactivities.AboutUsActivity;
import com.dogratech.indusbuddyapp.main.activities.navmenuactivities.HelpCentreActivity;
import com.dogratech.indusbuddyapp.main.activities.navmenuactivities.HomePageNewsActivity;
import com.dogratech.indusbuddyapp.main.activities.navmenuactivities.ArchivesActivity;
import com.dogratech.indusbuddyapp.main.activities.navmenuactivities.SettingsActivity;
import com.dogratech.indusbuddyapp.main.activities.navmenuactivities.UserProfileActivity;
import com.dogratech.indusbuddyapp.main.activities.reminder.ReminderActivity;
import com.dogratech.indusbuddyapp.main.activities.signinsignup.LoginActivity;
import com.dogratech.indusbuddyapp.main.activities.storerecord.StoreRecordsActivity;
import com.dogratech.indusbuddyapp.main.activities.wellness.MyWellnessActivity;
import com.dogratech.indusbuddyapp.main.helper.AppMenuManager;
import com.dogratech.indusbuddyapp.main.helper.Constatnts;
import com.dogratech.indusbuddyapp.main.managers.SharedPrefsManager;
import com.google.gson.Gson;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

/*******************************
 * Created by amolr on 23/3/18.*
 *******************************/

public class AppHomeActivity extends AppMenuManager implements View.OnClickListener {
    public static String ROLE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        prefsManager = SharedPrefsManager.getSharedInstance(AppHomeActivity.this);
        String role = prefsManager.getData(getString(R.string.userType));
        if (!role.equalsIgnoreCase(Constatnts.NODATA)) {
            // role = "c";
            ROLE = role;
            getMenus(role);
        }
        initialize();
        setListeners();
        //googleApiClient();
    }

    /**************************************************
     * Set Listeners to ui Widgets like OnClick...etc *
     **************************************************/
    protected void setListeners() {
        rlProfile.setOnClickListener(this);
        rlRateOurApp.setOnClickListener(this);
        rlHelpCenter.setOnClickListener(this);
        rlAboutUs.setOnClickListener(this);
        rlLogout.setOnClickListener(this);
        rlSettings.setOnClickListener(this);

        cardHealthCheckUp.setOnClickListener(this);
        cardHealthGuide.setOnClickListener(this);
        cardWellness.setOnClickListener(this);
        cardFitness.setOnClickListener(this);

        ivEditProfile.setOnClickListener(this);
        ivViewArrow.setOnClickListener(this);
        ivViewArrowDown.setOnClickListener(this);
        ivHealthCheckupSmall.setOnClickListener(this);
        ivHealthGuideSmall.setOnClickListener(this);
        ivWelnessSmall.setOnClickListener(this);
        ivFitnessSmall.setOnClickListener(this);
        ivMenu.setOnClickListener(this);
        rlStoreRecords.setOnClickListener(this);
        rlReminders.setOnClickListener(this);
        rlIndusUpdate.setOnClickListener(this);
        img_settingactivity_arrow.setOnClickListener(this);
        sliding_layout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i("Tab", "onPanelSlide, offset " + slideOffset);
                if (slideOffset < 0.5) {
                    llMainMenuSmall.setVisibility(View.GONE);
                    ivViewArrow.setVisibility(View.VISIBLE);
                    ivViewArrowDown.setVisibility(View.GONE);
                } else if (slideOffset > 0.5) {
                    llMainMenuSmall.setVisibility(View.VISIBLE);
                    ivViewArrow.setVisibility(View.GONE);
                    ivViewArrowDown.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState
                    previousState, SlidingUpPanelLayout.PanelState newState) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        Gson gson = new Gson();
        switch (v.getId()) {
            case R.id.ivMenu:
                try {
                    drawerLayout.openDrawer(GravityCompat.END);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.rlProfile:
                startActivity(new Intent(AppHomeActivity.this, UserProfileActivity.class));
                break;
            case R.id.rlRateOurApp:

                break;
            case R.id.rlIndusUpdate:
                startActivity(new Intent(AppHomeActivity.this, HomePageNewsActivity.class));
                break;
            case R.id.rlStoreRecords:
                startActivity(new Intent(AppHomeActivity.this, StoreRecordsActivity.class));
                break;
            case R.id.rlReminders:
                startActivity(new Intent(AppHomeActivity.this, ReminderActivity.class));
                break;
            case R.id.rlHelpCenter:
                startActivity(new Intent(AppHomeActivity.this, HelpCentreActivity.class));
                break;

            case R.id.rlAboutUs:
                startActivity(new Intent(AppHomeActivity.this, AboutUsActivity.class));
                break;

            case R.id.ivHealthCheckupSmall:
            case R.id.rlHealthCheckUp:
                String jsonStr = gson.toJson(subMenuCheckup);
                Intent intentCheckup = new Intent(AppHomeActivity.this, MyHealthChekUpActivity.class);
                intentCheckup.putExtra("jsonStrSubmenu", jsonStr);
                startActivity(intentCheckup);
                break;

            case R.id.ivHealthGuideSmall:
            case R.id.rlHealthGuide:
                String jsonStrGuide = gson.toJson(subMenuGuide);
                Intent intentGuide = new Intent(AppHomeActivity.this, MyHealthGuideActivity.class);
                intentGuide.putExtra("jsonStrSubmenu", jsonStrGuide);
                startActivity(intentGuide);
                break;

            case R.id.ivWelnessSmall:
            case R.id.rlWellness:
                startActivity(new Intent(AppHomeActivity.this, MyWellnessActivity.class));
                break;
            case R.id.ivFitnessSamll:
            case R.id.rlFitness:
                startActivity(new Intent(AppHomeActivity.this, FitnessMenuActivity.class));
                break;

            case R.id.ivViewArrow:
                sliding_layout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                break;

            case R.id.ivViewArrowDown:
                sliding_layout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                break;

            case R.id.rlLogout:
                prefsManager.clearData();
                Intent intent = new Intent(AppHomeActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                break;

            case R.id.rlSettings:
                startActivity(new Intent(AppHomeActivity.this, SettingsActivity.class));
                break;

            case R.id.img_settingactivity_arrow:
                startActivity(new Intent(AppHomeActivity.this, SettingsActivity.class));
                break;
        }
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);
        }
    }
}
