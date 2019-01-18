package com.indushealthplus.android.indusbuddy.interfaces;

import com.indushealthplus.android.indusbuddy.activities.fitness.fitbitintegration.AuthenticationResult;

/**
 * Created by amolr on 17/4/18.
 */

public interface AuthenticationHandler {
    void onAuthFinished(AuthenticationResult result);
}
