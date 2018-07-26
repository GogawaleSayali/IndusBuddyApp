package com.dogratech.indusbuddyapp.main.interfaces;


import com.dogratech.indusbuddyapp.main.activities.fitness.fitbitintegration.BasicHttpRequestBuilder;

/**
 * Created by jboggess on 9/26/16.
 */

public interface RequestSigner {

    void signRequest(BasicHttpRequestBuilder builder);

}
