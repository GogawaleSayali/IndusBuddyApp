package com.indushealthplus.android.indusbuddy.componentclasses;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.Button;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.apphomeactivity.AppHomeActivity;
import com.indushealthplus.android.indusbuddy.activities.baseactivities.BaseActivity;

/*****************************
 * Fitness Tracker components*
 * @author amolr             *
 *****************************/
public class FitnessTrackerComponents extends BaseActivity {
    protected Button login_button;

    @Override
    public void onBackPressed() {
        finishActivity();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item!=null) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    finishActivity();
                    break;
                case R.id.action_home:
                    finishActivity();
                    break;
                default:
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /*********************************************
     * Finish the activity on back arrow clicked.*
     *********************************************/
    private void finishActivity(){
        Intent i = new Intent(this, AppHomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }
}
