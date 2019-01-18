package com.indushealthplus.android.indusbuddy.activities.healthcheckup;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.baseactivities.BaseActivity;
import com.indushealthplus.android.indusbuddy.adapters.ViewPagerAdapter;
import com.indushealthplus.android.indusbuddy.activities.healthcheckup.checkupfragments.AddOnPkg;
import com.indushealthplus.android.indusbuddy.activities.healthcheckup.checkupfragments.AddOnTests;
import com.indushealthplus.android.indusbuddy.activities.healthcheckup.checkupfragments.DiscountCouponPkg;
import com.indushealthplus.android.indusbuddy.activities.healthguide.guidefragments.PackageTests;

public class TestDetailsActivity extends BaseActivity implements
        PackageTests.OnFragmentInteractionListener,AddOnTests.OnFragmentInteractionListener,
        AddOnPkg.OnFragmentInteractionListener,DiscountCouponPkg.OnFragmentInteractionListener,
        TabLayout.OnTabSelectedListener {
    private ViewPager viewPager;
    private Toolbar toolbar ;
    private TabLayout tabLayout;
    private ViewPagerAdapter adapter ;
    private Intent intent;
    private String tabTitles[] = new String[] { "PACKAGE TEST", "ADD ON TESTS","ADD ON PACKAGES",
                                                "DISCOUNT COUPON PACKAGE"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_details);
        initToolBar();
        initialise();
        setListener();
    }

    private void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        String type  = getIntent().getStringExtra("detailsType");
        if (type.equalsIgnoreCase("other")) {
            initializeToolBar(toolbar, "Package Details");
        }else{
            initializeToolBar(toolbar, "Test Details");
        }
    }

    private void initialise() {
        this      .getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        this      .getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        tabLayout =findViewById(R.id.tabs);
        viewPager =findViewById(R.id.viewpager);
                   setupViewPager(viewPager);
        tabLayout .setupWithViewPager(viewPager);
    }

    private void setListener() {
        tabLayout.addOnTabSelectedListener(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        String tests = getIntent().getExtras().getString("pkgTests");
        String addonTests = getIntent().getExtras().getString("AddonTests");
        String addonPackages = getIntent().getExtras().getString("AddonPackages");
        String addonDiscountPkgs = getIntent().getExtras().getString("AddonDiscountPkgs");

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter .addFragment(PackageTests.newInstance(tests,""), tabTitles[0].toUpperCase());
        adapter .addFragment(AddOnTests.newInstance(addonTests,""), tabTitles[1].toUpperCase());
        adapter .addFragment(AddOnPkg.newInstance(addonPackages,""), tabTitles[2].toUpperCase());
        adapter .addFragment(DiscountCouponPkg.newInstance(addonDiscountPkgs,""), tabTitles[3].toUpperCase());
        viewPager.setAdapter(adapter);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void JumpToPageFood(View view) {
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        hideKeyBoard();
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    /**
     * Hide Soft key board.
     */
    private void hideKeyBoard() {
        try {
            View view = getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
