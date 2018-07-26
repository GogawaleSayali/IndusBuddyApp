package com.dogratech.indusbuddyapp.main.activities.fitness.fitbitintegration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.webkit.WebView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.baseactivities.BaseActivity;
import com.dogratech.indusbuddyapp.main.interfaces.AuthenticationHandler;

import java.util.HashSet;
import java.util.Set;

public class FitbitLoginActivity extends BaseActivity implements AuthenticationHandler{
    public static final String CONFIGURATION_VERSION = "CONFIGURATION_VERSION";
    public static final String AUTHENTICATION_RESULT_KEY = "AUTHENTICATION_RESULT_KEY";
    public static final String CLIENT_CREDENTIALS_KEY = "CLIENT_CREDENTIALS_KEY";
    public static final String EXPIRES_IN_KEY = "EXPIRES_IN_KEY";
    public static final String SCOPES_KEY = "SCOPES_KEY";
    private WebView login_webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitbit_login);
        toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Fitness Tracker");
        initialize();
    }

    private void initialize() {
        login_webview = findViewById(R.id.login_webview);
        ClientCredentials clientCredentials = getIntent().getParcelableExtra(CLIENT_CREDENTIALS_KEY);
        Long expiresIn = getIntent().getLongExtra(EXPIRES_IN_KEY, 604800);
        Parcelable[] scopes = getIntent().getParcelableArrayExtra(SCOPES_KEY);
        Set<Scope> scopesSet = new HashSet<>();
        for (Parcelable scope : scopes) {
            scopesSet.add((Scope) scope);
        }

        AuthorizationController authorizationController = new AuthorizationController(
                login_webview,
                clientCredentials,
                this);

        authorizationController.authenticate(expiresIn, scopesSet);
    }

    @Override
    public void onAuthFinished(AuthenticationResult result) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(AUTHENTICATION_RESULT_KEY, result);
        resultIntent.putExtra(CONFIGURATION_VERSION, getIntent().getIntExtra(CONFIGURATION_VERSION, 0));
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
