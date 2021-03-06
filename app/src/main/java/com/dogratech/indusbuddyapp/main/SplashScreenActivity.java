package com.dogratech.indusbuddyapp.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.signinsignup.LoginActivity;
import com.dogratech.indusbuddyapp.main.helper.Constatnts;
import com.dogratech.indusbuddyapp.main.activities.apphomeactivity.AppHomeActivity;
import com.dogratech.indusbuddyapp.main.managers.SharedPrefsManager;

/**
 * Launching activity first time having timer 3 seconds 
 */
public class SplashScreenActivity extends AppCompatActivity {
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    private TextView tvWelcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
       /*  LanguageManager languageManager = LanguageManager.getLanguageManager();
         String lang = languageManager.getSelectedLanguage();

        tvWelcome = findViewById(R.id.tvWelcome);

        tvWelcome.setText(getString());
*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPrefsManager prefsManager = SharedPrefsManager.getSharedInstance(SplashScreenActivity.this);
                if (prefsManager.getData(getString(R.string.isLoggedin)).equalsIgnoreCase(Constatnts.NODATA)) {
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(SplashScreenActivity.this, AppHomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },AUTO_HIDE_DELAY_MILLIS);
    }

  }
