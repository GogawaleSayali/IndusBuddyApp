package com.dogratech.indusbuddyapp.main.interfaces;

import com.dogratech.indusbuddyapp.main.activities.fitness.fitbitintegration.AuthenticationResult;

/**
 * Created by amolr on 17/4/18.
 */

public interface AuthenticationHandler {
    void onAuthFinished(AuthenticationResult result);
}
