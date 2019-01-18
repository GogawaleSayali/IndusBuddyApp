package com.indushealthplus.android.indusbuddy.activities.fitness;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.componentclasses.FitnessTrackerComponents;
import com.indushealthplus.android.indusbuddy.activities.fitness.fitbitintegration.AuthenticationManager;
import com.indushealthplus.android.indusbuddy.activities.fitness.fitbitintegration.AuthenticationResult;
import com.indushealthplus.android.indusbuddy.activities.fitness.fitbitintegration.Scope;
import com.indushealthplus.android.indusbuddy.interfaces.AuthenticationHandler;

import java.util.Set;

/*******************************
 * Created by amolr on 23/3/18.*
 *******************************/

public class MyFitnessActivity extends FitnessTrackerComponents implements AuthenticationHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitnsee_tracker);
            initToolBar();
    }

    /************************
     * Initialize toolbar   *
     ************************/
    private void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Fitness Tracker");
        initialize();
        setListeners();
    }

    /************************************
     * Initialize all the widgets of UI.*
     ************************************/
    private void initialize() {
        login_button = findViewById(R.id.login_button);
    }

    /**
     * Set Listeners to widgets like Onclick listeners,..etc
     */
    private void setListeners() {
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthenticationManager.login(MyFitnessActivity.this);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (AuthenticationManager.isLoggedIn()) {
            startActivity(new Intent(MyFitnessActivity.this,MyFitnessHomeActivity.class));
            finish();
        }else {
            //AuthenticationManager.login(MyFitnessActivity.this);
        }
    }

    @Override
    public void onAuthFinished(AuthenticationResult authenticationResult) {
        if (authenticationResult.isSuccessful()) {
             //  Toast.makeText(this, "Logged in already", Toast.LENGTH_SHORT).show();
        } else {
            displayAuthError(authenticationResult);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!AuthenticationManager.onActivityResult(requestCode, resultCode, data, this)) {
            // Handle other activity results, if needed
        }
    }

    /**************************************************************************
     * Show the dialog with proper message.                                   *
     * @param authenticationResult : Result of the authentication with Fitbit.*
     **************************************************************************/
    private void displayAuthError(AuthenticationResult authenticationResult) {
        String message = "";

        switch (authenticationResult.getStatus()) {
            case dismissed:
                message = getString(R.string.login_dismissed);
                break;
            case error:
                message = authenticationResult.getErrorMessage();
                break;
            case missing_required_scopes:
                Set<Scope> missingScopes = authenticationResult.getMissingScopes();
                String missingScopesText = TextUtils.join(", ", missingScopes);
                message = getString(R.string.missing_scopes_error) + missingScopesText;
                break;
        }

        new AlertDialog.Builder(this)
                .setTitle(R.string.login_title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .create()
                .show();
    }
}
