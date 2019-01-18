package com.indushealthplus.android.indusbuddy.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.models.ModelHra;

import java.util.ArrayList;

/**
 * Created by amolr on 23/2/18.
 */

public class AdapterHra extends RecyclerView
        .Adapter<AdapterHra.HraViewHolder> {
    private static String LOG_TAG = "AdapterAllCenters";
    private ArrayList<ModelHra> mDataset;
    private static MyClickListener myClickListener;
    private final static int HEADER_VIEW = 0;
    private final static int OPTION_RADIO_VIEW = 1;
    private final static int OPTION_CHECKBOX_VIEW = 2;
    private final static int OPTION_TEXT_VIEW = 3;

    public static class HraViewHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        EditText etAnswer;
        CheckBox checkboxOptionOne,checkboxOptionTwo,checkboxOptionThree,checkboxOptionFour,
                checkboxOptionFive,checkboxOptionSix,checkboxOptionSeven,checkboxOptionEight,
                checkboxOptionNine,checkboxOptionTen;
        RadioButton radioOptionOne,radioOptionTwo,radioOptionThree,radioOptionFour,
                radioOptionFive,radioOptionSix,radioOptionSeven,radioOptionEight,radioOptionNine,radioOptionTen;
        TextView tvHeader,tvQuestion;
        RadioGroup radioGrpOption;
        ArrayList<RadioButton> radio = new ArrayList<>();
        ArrayList<CheckBox> checkBoxes = new ArrayList<>();
        public HraViewHolder(View itemView) {
            super(itemView);
            tvHeader           = itemView.findViewById(R.id.tvHeaderHra);
            tvQuestion         = itemView.findViewById(R.id.tvQuestion);
            etAnswer           = itemView.findViewById(R.id.etAnswer);

            checkboxOptionOne  = itemView.findViewById(R.id.checkboxOptionOne);
            checkboxOptionTwo  = itemView.findViewById(R.id.checkboxOptionTwo);
            checkboxOptionThree= itemView.findViewById(R.id.checkboxOptionThree);
            checkboxOptionFour = itemView.findViewById(R.id.checkboxOptionFour);
            checkboxOptionFive = itemView.findViewById(R.id.checkboxOptionFive);
            checkboxOptionSix  = itemView.findViewById(R.id.checkboxOptionSix);
            checkboxOptionSeven  = itemView.findViewById(R.id.checkboxOptionSeven);
            checkboxOptionEight  = itemView.findViewById(R.id.checkboxOptionEight);
            checkboxOptionNine  = itemView.findViewById(R.id.checkboxOptionNine);
            checkboxOptionTen  = itemView.findViewById(R.id.checkboxOptionTen);

            checkBoxes.add(checkboxOptionOne);
            checkBoxes.add(checkboxOptionTwo);
            checkBoxes.add(checkboxOptionThree);
            checkBoxes.add(checkboxOptionFour);
            checkBoxes.add(checkboxOptionFive);
            checkBoxes.add(checkboxOptionSix);
            checkBoxes.add(checkboxOptionSeven);
            checkBoxes.add(checkboxOptionEight);
            checkBoxes.add(checkboxOptionNine);
            checkBoxes.add(checkboxOptionTen);

            radioGrpOption     = itemView.findViewById(R.id.radioGrpOption);
            radioOptionOne     = itemView.findViewById(R.id.radioOptionOne);
            radioOptionTwo     = itemView.findViewById(R.id.radioOptionTwo);
            radioOptionThree   = itemView.findViewById(R.id.radioOptionThree);
            radioOptionFour    = itemView.findViewById(R.id.radioOptionFour);
            radioOptionFive    = itemView.findViewById(R.id.radioOptionFive);
            radioOptionSix     = itemView.findViewById(R.id.radioOptionSix);
            radioOptionSeven     = itemView.findViewById(R.id.radioOptionSeven);
            radioOptionEight     = itemView.findViewById(R.id.radioOptionEight);
            radioOptionNine     = itemView.findViewById(R.id.radioOptionNine);
            radioOptionTen     = itemView.findViewById(R.id.radioOptionTen);

            radio.add(radioOptionOne);
            radio.add(radioOptionTwo);
            radio.add(radioOptionThree);
            radio.add(radioOptionFour);
            radio.add(radioOptionFive);
            radio.add(radioOptionSix);
            radio.add(radioOptionSeven);
            radio.add(radioOptionEight);
            radio.add(radioOptionNine);
            radio.add(radioOptionTen);

            Log.i(LOG_TAG, "Adding Listener");
            //itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }


    @Override
    public int getItemViewType(int position) {
        String viewType = mDataset.get(position).getType();
        switch(viewType) {
            case "header":
                return HEADER_VIEW;
            case "radio":
                return OPTION_RADIO_VIEW;
            case "checkbox":
                return OPTION_CHECKBOX_VIEW;
            case "text":
                return OPTION_TEXT_VIEW;
            default:
                return HEADER_VIEW;
        }

    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public AdapterHra(ArrayList<ModelHra> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public HraViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        int layoutRes = 0;

        switch (viewType) {
            case HEADER_VIEW:
                layoutRes = R.layout.header_hra_item;
                break;
            case OPTION_RADIO_VIEW:
                layoutRes = R.layout.options_radio_item;
                break;
            case OPTION_CHECKBOX_VIEW:
                layoutRes = R.layout.options_checkbox_item;
                break;
            case OPTION_TEXT_VIEW:
                layoutRes = R.layout.options_text_item;
                break;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);
        return new HraViewHolder(view);

    }

    @Override
    public void onBindViewHolder(HraViewHolder holder, int position) {
      final ModelHra modelHra = mDataset.get(position);

          switch (getItemViewType(position)){
              case HEADER_VIEW:
                  holder.tvHeader.setText(modelHra.getHeader());
                  break;
              case OPTION_RADIO_VIEW:
                  holder.tvQuestion.setText(modelHra.getQuestion());
                  for (RadioButton radioButton :holder.radio) {
                      radioButton.setText("");
                      radioButton.setVisibility(View.GONE);
                  }
                  for (int i= 0;i<modelHra.getOptionsList().size();i++){
                          holder.radio.get(i).setVisibility(View.VISIBLE);
                          holder.radio.get(i).setText(modelHra.getOptionsList().get(i).getOption());
                  }
                  break;
              case OPTION_CHECKBOX_VIEW:
                  holder.tvQuestion.setText(modelHra.getQuestion());
                  for (CheckBox checkBox :holder.checkBoxes) {
                        checkBox.setText("");
                        checkBox.setVisibility(View.GONE);
                  }
                  for (int i= 0;i<modelHra.getOptionsList().size();i++){
                          holder.checkBoxes.get(i).setVisibility(View.VISIBLE);
                          holder.checkBoxes.get(i).setText(modelHra.getOptionsList().get(i).getOption());
                  }
                  break;
              case OPTION_TEXT_VIEW:
                  holder.tvQuestion.setText(modelHra.getQuestion());
                  break;
          }
    }

    public void addItem(ModelHra dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}