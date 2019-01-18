package com.indushealthplus.android.indusbuddy.activities.fitness.fitbitintegration;

import android.app.Activity;
import android.os.Handler;

/**
 * Created by amolr on 24/4/18.
 */

public class ResourceLoaderFactory<T> {
    private String urlFormat;
    private Class<T> classType;

    public ResourceLoaderFactory(String urlFormat, Class<T> classType) {
        this.urlFormat = urlFormat;
        this.classType = classType;
    }

    public ResourceLoader<T> newResourceLoader(Activity contextActivity, Scope[] requiredScopes,String method, String... pathParams) {

        String url = urlFormat;
        if (pathParams != null && pathParams.length > 0) {
            url = String.format(urlFormat, pathParams);
        }

        return new ResourceLoader<T>(contextActivity, url, requiredScopes, new Handler(), classType,method);
    }
}
