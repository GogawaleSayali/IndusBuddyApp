package com.dogratech.indusbuddyapp.main.activities.healthcheckup;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.baseactivities.BaseActivityNoMenu;

public class InstructionCallActivity extends BaseActivityNoMenu implements View.OnClickListener {
    private ImageView ivPlayPause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction_call);
        Toolbar toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Instruction call");
        initialize();
    }

    private void initialize() {
        ivPlayPause = findViewById(R.id.ivPlayPause);
        setListeners();
    }

    private void setListeners() {
        ivPlayPause.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivPlayPause:
                if (ivPlayPause.getTag().toString().equalsIgnoreCase("play")){
                    ivPlayPause.setTag("pause");
                    ivPlayPause.setImageResource(R.drawable.play);
                }else{
                    ivPlayPause.setTag("play");
                    ivPlayPause.setImageResource(R.drawable.pause);
                }
                break;
        }
    }
}
