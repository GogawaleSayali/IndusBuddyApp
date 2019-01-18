package com.dogratech.indusbuddyapp.main.activities.healthguide;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.adapters.HRAQuesnAdapter;
import com.dogratech.indusbuddyapp.main.activities.healthguide.guidefragments.HRAFragment;
import com.dogratech.indusbuddyapp.main.managers.SharedPrefsManager;
import com.dogratech.indusbuddyapp.main.models.HRAAnswerMainModel;
import com.dogratech.indusbuddyapp.main.models.HraTypeModel;
import com.dogratech.indusbuddyapp.main.models.Model_Item_Question;
import com.dogratech.indusbuddyapp.main.uitility.DeviceUtility;
import com.google.gson.Gson;

import java.util.ArrayList;

public class HRAActivity extends AppCompatActivity {
    private ActionBar   actionBar;
    private TextView    tvHraType,tvPrevious,tvNext;
    private ImageView   ivPrevious,ivNext;
    private RelativeLayout                 rlPrevious,rlNext;
    private ArrayList<Model_Item_Question> question;
    private LoopingViewPager viewpager;
    private SharedPrefsManager prefsManager;
    private int userId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hra);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar . setHomeButtonEnabled(true); // disable the button
        actionBar . setDisplayHomeAsUpEnabled(true); // remove the left caret
        actionBar . setDisplayShowHomeEnabled(true);
        initialize();
        setListeners();
    }

    private void setListeners() {
        tvPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(viewpager.getCurrentItem()-1);
            }
        });

        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(viewpager.getCurrentItem()+1);
            }
        });

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
               try {
                   DeviceUtility.hideKeyBord( HRAActivity.this);
               }catch (Exception e){
                   e.printStackTrace();
               }
            }

            @Override
            public void onPageSelected(int position) {
                try {
                    if (position == question.size() - 1) {
                        rlNext.setVisibility(View.GONE);
                    } else if (position == 0) {
                        rlPrevious.setVisibility(View.GONE);
                    } else if (position == question.size() - 1 && position == 0) {
                        rlNext.setVisibility(View.GONE);
                        rlPrevious.setVisibility(View.GONE);
                    } else {
                        rlNext.setVisibility(View.VISIBLE);
                        rlPrevious.setVisibility(View.VISIBLE);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item!=null) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    finish();
                    break;
                default:
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    private void initialize() {
        prefsManager = SharedPrefsManager.getSharedInstance(this);
        userId       = Integer.parseInt(prefsManager.getData(getString(R.string.shars_userid)));
        viewpager    = findViewById(R.id.viewpager);
        tvHraType    = findViewById(R.id.tvHraType);
        ivPrevious   = findViewById(R.id.ivPrevious);
        ivNext       = findViewById(R.id.ivNext);
        tvPrevious   = findViewById(R.id.tvPrevious);
        tvNext       = findViewById(R.id.tvNext);
        rlPrevious   = findViewById(R.id.rlPrevious);
        rlNext       = findViewById(R.id.rlNext);

        rlPrevious.setVisibility(View.GONE);
        rlPrevious.setVisibility(View.GONE);

        changeImageColor("white",ivNext);
        changeImageColor("white",ivPrevious);

        String jsonStr = getIntent().getStringExtra("hra");
        Gson gson = new Gson();
        HraTypeModel typeModel = gson.fromJson(jsonStr,HraTypeModel.class);
        tvHraType.setText(typeModel.getHraType());
        tvHraType.setVisibility(View.GONE);
        actionBar.setTitle(typeModel.getHraType());
        question = typeModel.getQuestionsOfHraType();
        for (Model_Item_Question question1:question){
            HRAAnswerMainModel model = new HRAAnswerMainModel();
            model.setQuestionId(question1.getQuestionId());
            model.setClientId(userId);
            model.setAnswer("");
            HRAFragment.myAnswerArray.add(model);
        }
        HRAQuesnAdapter quesnAdapter = new HRAQuesnAdapter(HRAActivity.this,question,false);
        viewpager.setAdapter(quesnAdapter);
        if (question.size() == 1){
            rlPrevious.setVisibility(View.GONE);
            rlNext.setVisibility(View.GONE);
        }

    }

    private void changeImageColor(String color,ImageView imageView){
        try {
            if (color.equalsIgnoreCase("primary")) {
                imageView.setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);
            } else if (color.equalsIgnoreCase("white")) {
                imageView.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
