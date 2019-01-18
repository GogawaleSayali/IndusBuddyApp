package com.indushealthplus.android.indusbuddy.interfaces;


import com.indushealthplus.android.indusbuddy.activities.fitness.fitbitintegration.BasicHttpRequestBuilder;

/**
 * Created by jboggess on 9/26/16.
 */

public interface RequestSigner {

    void signRequest(BasicHttpRequestBuilder builder);

}
