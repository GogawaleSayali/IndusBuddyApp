package com.indushealthplus.android.indusbuddy.activities.healthcheckup;

import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.baseactivities.BaseActivityNoMenu;
import com.indushealthplus.android.indusbuddy.models.AudioDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InstructionCallActivity extends BaseActivityNoMenu implements View.OnClickListener {
    private ImageView ivPlayPause;
    private MediaPlayer mediaPlayer;
    private int instructionCallLanguage, instructionCallValue;
    private String jsonStr ;
    private ArrayList<AudioDetails> audioDetailList;
    private AudioDetails audioDetailObj ;
    ArrayList<String> scripts ;
    String[] singleChoiceItems;
    String[] listToString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction_call);
        Toolbar toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Instruction call");
//**********
        Gson gson = new Gson();
        jsonStr = getIntent().getStringExtra("audioDetails");
        List<AudioDetails> list = gson.fromJson(getIntent().getStringExtra("audioDetails"), new TypeToken<List<AudioDetails>>(){}.getType());
        scripts = new ArrayList<>();
        for(int y=0; y < list.size() ; y++){
            scripts.add(list.get(y).getLanguage());
        }
        String[] myArray = new String[scripts.size()];
        listToString = scripts.toArray(myArray);
//**********
        initialize();
    }

    private void dynamiallyDisplayRadioButton() {
       // String[] singleChoiceItems = getResources().getStringArray(R.array.dialog_single_choice_array);
        singleChoiceItems = listToString;
        int itemSelected = 0;
        final AlertDialog dialog= new AlertDialog.Builder(this)
                .setSingleChoiceItems(singleChoiceItems, itemSelected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int selectedIndex) {
                        instructionCallLanguage = selectedIndex;
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    //*********
                        String url = "http://indusites.com/media/indus_anthem.mp3"; // your URL here
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        try {
                            mediaPlayer.setDataSource(url);
                            mediaPlayer.prepare(); // might take long! (for buffering, etc)
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mediaPlayer.start();
                    //*********
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showRadioButtonDialog() {
        String[] singleChoiceItems = getResources().getStringArray(R.array.dialog_single_choice_array);
        int itemSelected = 0;
        final AlertDialog dialog= new AlertDialog.Builder(this)
                .setSingleChoiceItems(singleChoiceItems, itemSelected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int selectedIndex) {
                            instructionCallLanguage = selectedIndex;
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(instructionCallLanguage == 0){
                            instructionCallValue = getApplicationContext().getResources().getIdentifier("sampleaudio","raw",getApplicationContext().getPackageName());
                        }else if(instructionCallLanguage == 1){
                            instructionCallValue = getApplicationContext().getResources().getIdentifier("sample_audio_two","raw",getApplicationContext().getPackageName());
                        }else{
                            instructionCallValue = getApplicationContext().getResources().getIdentifier("sampleaudio","raw",getApplicationContext().getPackageName());
                        }
                        mediaPlayer = MediaPlayer.create(getApplicationContext(),instructionCallValue);
                        mediaPlayer.setLooping(true);
                        mediaPlayer.start();
                    }
                })
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

    @Override
    protected void onPause() {
        super.onPause();
        if(mediaPlayer != null){
            mediaPlayer.pause();
        }
    }

    private void initialize() {
             dynamiallyDisplayRadioButton();
        // showRadioButtonDialog();
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
                    if(mediaPlayer != null){
                        ivPlayPause.setTag("pause");
                        ivPlayPause.setImageResource(R.drawable.play);
                        mediaPlayer.pause();
                    }
                }else{
                    if(mediaPlayer != null) {
                        ivPlayPause.setTag("play");
                        ivPlayPause.setImageResource(R.drawable.pause);
                        mediaPlayer.start();
                    }
                }
                break;
        }
    }
}
