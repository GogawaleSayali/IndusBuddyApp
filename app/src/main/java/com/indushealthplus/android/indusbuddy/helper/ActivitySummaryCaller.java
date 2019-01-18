package com.indushealthplus.android.indusbuddy.helper;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;

import com.indushealthplus.android.indusbuddy.models.DailyActivitySummary;

/**
 * Created by amolr on 30/4/18.
 */

public class ActivitySummaryCaller implements LoaderManager.LoaderCallbacks<DailyActivitySummary> {
    @Override
    public Loader<DailyActivitySummary> onCreateLoader(int id, Bundle args) {

        return null;
    }

    @Override
    public void onLoadFinished(Loader<DailyActivitySummary> loader, DailyActivitySummary data) {

    }

    @Override
    public void onLoaderReset(Loader<DailyActivitySummary> loader) {

    }
}
