package com.dogratech.indusbuddyapp.main.activities.healthcheckup;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.baseactivities.BaseActivityNoMenu;

import java.util.ArrayList;
import java.util.List;

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

    private void showRadioButtonDialog() {


        String[] singleChoiceItems = getResources().getStringArray(R.array.dialog_single_choice_array);
        int itemSelected = 0;
        final AlertDialog dialog= new AlertDialog.Builder(this)
                .setSingleChoiceItems(singleChoiceItems, itemSelected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int selectedIndex) {

                    }
                })

                .setPositiveButton("Ok", null)
                .setNegativeButton("Cancel", null)
                .show();
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.parseColor("#CC000000")));

      /* final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_instructioncall);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.parseColor("#CC000000")));
        dialog.show();
        RadioGroup rg = (RadioGroup) dialog.findViewById(R.id.radiogroup_instructioncall);*/


        //dialog.show();



    }


    private void initialize()
    {
        showRadioButtonDialog();
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
