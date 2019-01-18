package com.indushealthplus.android.indusbuddy.activities.baseactivities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.apphomeactivity.AppHomeActivity;

public class BaseActivity extends AppCompatActivity {
    /**Toolbar and Action bar common for all activities which extends this class**/
    protected Toolbar toolbar;
    protected ActionBar actionBar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item!=null) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    finish();
                    break;
                case R.id.action_home:
                    Intent i = new Intent(this, AppHomeActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    finish();
                    break;
                default:
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /*****************************************************************
     * This method shows <- Arrow on top left corner of the activity.*
     *****************************************************************/
    protected void initializeToolBar(Toolbar toolbar,String title){
        this.toolbar = toolbar;
        setSupportActionBar(this.toolbar);
        actionBar = getSupportActionBar();
        actionBar . setHomeButtonEnabled(true); // disable the button
        actionBar . setDisplayHomeAsUpEnabled(true); // remove the left caret
        actionBar . setDisplayShowHomeEnabled(true);
        actionBar.setTitle(title);
    }
}
