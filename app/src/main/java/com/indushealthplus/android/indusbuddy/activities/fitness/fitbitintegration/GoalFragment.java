package com.indushealthplus.android.indusbuddy.activities.fitness.fitbitintegration;

import android.content.Loader;
import android.os.Bundle;
import android.widget.Toast;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.fitness.SetGoalActivity;
import com.indushealthplus.android.indusbuddy.activities.fitness.fitbitintegration.fragments.InfoGoalUpdateFragment;
import com.indushealthplus.android.indusbuddy.helper.UpdateGoalHelper;
import com.indushealthplus.android.indusbuddy.models.Goals;

/**
 * Created by amolr on 26/4/18.
 */

public class GoalFragment extends InfoGoalUpdateFragment<Goals> {
    public GoalFragment(){
    }

    @Override
    public Loader<ResourceLoaderResult<Goals>> onCreateLoader(int id, Bundle args) {
        return UpdateGoalHelper.updateStepsGoal(getActivity(), SetGoalActivity.stepesNumber);
    }

    @Override
    public int getTitleResourceId() {
        return R.string.activity_info;
    }

    @Override
    protected int getLoaderId() {
        return 50;
    }

    @Override
    public void onLoadFinished(Loader<ResourceLoaderResult<Goals>> loader, ResourceLoaderResult<Goals> data) {
        super.onLoadFinished(loader, data);
        if (data.isSuccessful()) {
            Toast.makeText(getActivity(), "Goal is updated successfully!!", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }else{
            try {
                Toast.makeText(getActivity(), "" + data.getErrorMessage(), Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<ResourceLoaderResult<Goals>> loader) {
    }
}