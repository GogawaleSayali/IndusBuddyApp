package com.dogratech.indusbuddyapp.main.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.asksira.loopingviewpager.LoopingPagerAdapter;
import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.healthguide.guidefragments.HRAFragment;
import com.dogratech.indusbuddyapp.main.models.HRAAnswerMainModel;
import com.dogratech.indusbuddyapp.main.models.Model_Item_Question;
import com.dogratech.indusbuddyapp.main.models.Model_Item_options;

import java.util.ArrayList;

/**
 * Created by amolr on 18/4/18.
 */

public class HRAQuesnAdapter extends LoopingPagerAdapter<Model_Item_Question> {
    private final static int OPTION_RADIO_VIEW = 0;
    private final static int OPTION_CHECKBOX_VIEW = 1;
    private final static int OPTION_TEXT_VIEW = 2;

    public HRAQuesnAdapter(Context context, ArrayList<Model_Item_Question> itemList, boolean isInfinite) {
        super(context, itemList, isInfinite);

    }

    //This method will be triggered if the item View has not been inflated before.
    @Override
    protected View inflateView(int viewType, int listPosition) {
        int layoutRes = 0;
        switch (viewType) {
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
        return LayoutInflater.from(context).inflate(layoutRes, null);
    }

    //Bind your data with your item View here.
    //Below is just an example in the demo app.
    //You can assume convertView will not be null here.
    //You may also consider using a ViewHolder pattern.
    @Override
    protected void bindView(View convertView, final int position, int viewType) {
            final String question = itemList.get(position).getQuestion();
            final int questionId = itemList.get(position).getQuestionId();
            ArrayList<Model_Item_options> options = itemList.get(position).getOptions();
        CheckBox checkboxOptionOne,checkboxOptionTwo,checkboxOptionThree,checkboxOptionFour,
                checkboxOptionFive,checkboxOptionSix,checkboxOptionSeven,checkboxOptionEight,
                checkboxOptionNine,checkboxOptionTen;
        RadioButton radioOptionOne,radioOptionTwo,radioOptionThree,radioOptionFour,
                radioOptionFive,radioOptionSix,radioOptionSeven,radioOptionEight,radioOptionNine,radioOptionTen;
        ArrayList<RadioButton> radio = new ArrayList<>();
        final ArrayList<CheckBox> checkBoxes = new ArrayList<>();
        final ArrayList<CheckBox> visibleCheck = new ArrayList<>();
        String strCheck = itemList.get(position).getAnswString();
        final ArrayList<String> ids = new ArrayList<>();

        final RadioGroup radioGrpOption;
        switch (getItemViewType(position)){
            case OPTION_RADIO_VIEW:
                TextView tvQuestion = convertView.findViewById(R.id.tvQuestion);
                tvQuestion.setText(question);
                radioGrpOption     = convertView.findViewById(R.id.radioGrpOption);
                radioOptionOne     = convertView.findViewById(R.id.radioOptionOne);
                radioOptionTwo     = convertView.findViewById(R.id.radioOptionTwo);
                radioOptionThree   = convertView.findViewById(R.id.radioOptionThree);
                radioOptionFour    = convertView.findViewById(R.id.radioOptionFour);
                radioOptionFive    = convertView.findViewById(R.id.radioOptionFive);
                radioOptionSix     = convertView.findViewById(R.id.radioOptionSix);
                radioOptionSeven   = convertView.findViewById(R.id.radioOptionSeven);
                radioOptionEight   = convertView.findViewById(R.id.radioOptionEight);
                radioOptionNine    = convertView.findViewById(R.id.radioOptionNine);
                radioOptionTen     = convertView.findViewById(R.id.radioOptionTen);

                radio.add(radioOptionOne);   radio.add(radioOptionTwo);
                radio.add(radioOptionThree); radio.add(radioOptionFour);
                radio.add(radioOptionFive);  radio.add(radioOptionSix);
                radio.add(radioOptionSeven); radio.add(radioOptionEight);
                radio.add(radioOptionNine);  radio.add(radioOptionTen);
                for (RadioButton radioButton :radio) {
                    radioButton.setText("");
                    radioButton.setSaveEnabled(false);
                    radioButton.setVisibility(View.GONE);
                    radioButton.setEnabled(true);
                    radioButton.setOnCheckedChangeListener(null);
                }
                for (int i= 0;i<options.size();i++){
                    radio.get(i).setVisibility(View.VISIBLE);
                    radio.get(i).setTag(options.get(i).getOptionId());
                    radio.get(i).setText(options.get(i).getOption());
                    radio.get(i).setChecked(false);
                }
                if (strCheck!=null){
                    for (int i = 0 ;i< radio.size();i++){
                        radio.get(i).setEnabled(false);
                        if (radio.get(i).getTag()!=null) {
                            if (radio.get(i).getTag().toString().equalsIgnoreCase(strCheck)) {
                                radio.get(i).setChecked(true);
                            }
                        }
                    }
                }
                radioGrpOption.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        HRAAnswerMainModel model = isAlreadyThere(questionId);
                        String optionId = String.valueOf(radioGrpOption.findViewById(group.getCheckedRadioButtonId()).getTag());
                        model.setAnswer(optionId);
                    }
                });
                break;
            case OPTION_CHECKBOX_VIEW:
                TextView tvQuestion1 = convertView.findViewById(R.id.tvQuestion);
                tvQuestion1.setText(question);
                checkboxOptionOne  = convertView.findViewById(R.id.checkboxOptionOne);
                checkboxOptionTwo  = convertView.findViewById(R.id.checkboxOptionTwo);
                checkboxOptionThree= convertView.findViewById(R.id.checkboxOptionThree);
                checkboxOptionFour = convertView.findViewById(R.id.checkboxOptionFour);
                checkboxOptionFive = convertView.findViewById(R.id.checkboxOptionFive);
                checkboxOptionSix  = convertView.findViewById(R.id.checkboxOptionSix);
                checkboxOptionSeven= convertView.findViewById(R.id.checkboxOptionSeven);
                checkboxOptionEight= convertView.findViewById(R.id.checkboxOptionEight);
                checkboxOptionNine = convertView.findViewById(R.id.checkboxOptionNine);
                checkboxOptionTen  = convertView.findViewById(R.id.checkboxOptionTen);

                checkBoxes.add(checkboxOptionOne);  checkBoxes.add(checkboxOptionTwo);
                checkBoxes.add(checkboxOptionThree);checkBoxes.add(checkboxOptionFour);
                checkBoxes.add(checkboxOptionFive); checkBoxes.add(checkboxOptionSix);
                checkBoxes.add(checkboxOptionSeven);checkBoxes.add(checkboxOptionEight);
                checkBoxes.add(checkboxOptionNine); checkBoxes.add(checkboxOptionTen);

                for (CheckBox checkBox :checkBoxes) {
                    checkBox.setText("");
                    checkBox.setVisibility(View.GONE);
                    checkBox.setSaveEnabled(false);
                    checkBox.setOnCheckedChangeListener(null);
                }
                for (int i= 0;i<options.size();i++){
                    checkBoxes.get(i).setVisibility(View.VISIBLE);
                    checkBoxes.get(i).setTag(options.get(i).getOptionId());
                    checkBoxes.get(i).setText(options.get(i).getOption());
                    checkBoxes.get(i).setChecked(options.get(i).isChecked());
                    checkBoxes.get(i).setEnabled(true);
                    visibleCheck.add(checkBoxes.get(i));
                }

                if (strCheck!=null){
                    if (!strCheck.isEmpty()){
                        String[] ansArray = strCheck.split(",");
                        for (int i = 0 ; i <ansArray.length;i++){
                           for (int j = 0;j < visibleCheck.size();j++) {
                               visibleCheck.get(j).setEnabled(false);
                               if (visibleCheck.get(j).getTag()!=null) {
                                   if (visibleCheck.get(j).getTag().toString().equalsIgnoreCase(ansArray[i])) {
                                       visibleCheck.get(j).setChecked(true);
                                   }
                               }
                           }
                        }
                    }
                }

                checkboxOptionOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        setAnswers(ids,visibleCheck,questionId);
                    }
                });
                checkboxOptionTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        setAnswers(ids,visibleCheck,questionId);
                    }
                });
                checkboxOptionThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        setAnswers(ids,visibleCheck,questionId);
                    }
                });
                checkboxOptionFour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        setAnswers(ids,visibleCheck,questionId);
                    }
                });
                checkboxOptionFive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        setAnswers(ids,visibleCheck,questionId);
                    }
                });
                checkboxOptionSix.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        setAnswers(ids,visibleCheck,questionId);
                    }
                });
                checkboxOptionSeven.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        setAnswers(ids,visibleCheck,questionId);
                    }
                });

                checkboxOptionEight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        setAnswers(ids,visibleCheck,questionId);
                    }
                });

                checkboxOptionNine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        setAnswers(ids,visibleCheck,questionId);
                    }
                });

                checkboxOptionTen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        setAnswers(ids,visibleCheck,questionId);
                    }
                });


                break;
            case OPTION_TEXT_VIEW:
                final TextView tvQuestion2 = convertView.findViewById(R.id.tvQuestion);
                tvQuestion2.setText(question);
                final HRAAnswerMainModel model = isAlreadyThere(questionId);
                final EditText etAnswer = convertView.findViewById(R.id.etAnswer);
                if (strCheck != null) {
                    if (!strCheck.isEmpty()) {
                        etAnswer.setEnabled(false);
                        etAnswer.setText(strCheck);
                    }
                }else{
                    etAnswer.setEnabled(true);
                }

                etAnswer.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String ans = etAnswer.getText().toString();

                        //model.setQuestionId(itemList.get(position).getQuestionId());
                        model.setAnswer(ans);
                    }
                });

                /*DeviceUtility.hideKeyBord((Activity) context);*/
                break;
        }
    }

    private void setAnswers(ArrayList<String> ids, ArrayList<CheckBox> checkBoxes, int questionId) {
        //HRAAnswerMainModel model = HRAActivity.myAnswerArray.get(position);
        for (int i = 0 ; i <checkBoxes.size();i++){
            if (checkBoxes.get(i).isChecked()){
                if (!ids.contains(String.valueOf(checkBoxes.get(i).getTag()))) {
                    ids.add(String.valueOf(checkBoxes.get(i).getTag()));
                }
            }else{
                if (ids.contains(String.valueOf(checkBoxes.get(i).getTag()))) {
                    ids.remove(String.valueOf(checkBoxes.get(i).getTag()));
                }
            }
        }
        String s = "";
        for (int i = 0 ; i <ids.size();i++){
            s +=","+ids.get(i);
        }

        if (s.startsWith(",")){
             s = s.replaceFirst(",","");
        }
        HRAAnswerMainModel model =  isAlreadyThere(questionId);
       /* String str = model.getAnswer();
        if (!str.equalsIgnoreCase("")){
            str+=","+s;
        }else {
            str = s;
        }*/
        model.setAnswer(s);
       // HRAFragment.myAnswerArray.get(position).setAnswer(s);
    }



    private HRAAnswerMainModel isAlreadyThere(int questionId) {

        for (HRAAnswerMainModel model1:HRAFragment.myAnswerArray){
            if (model1.getQuestionId() == questionId){
                return model1;
            }
        }
        return null;
    }

  /* private HRAAnswerMainModel isAlreadyThereO(int position) {
        for (HRAAnswerMainModel model1:answerArray){
            if (model1.getQuestionId() == itemList.get(position).getQuestionId()){
                return model1;
            }
        }
        return null;
    }
*/
    @Override
    protected int getItemViewType(int listPosition) {
        String viewType =  itemList.get(listPosition).getQuestionType();
        switch(viewType) {
            case "radio":
                return OPTION_RADIO_VIEW;
            case "checkbox":
                return OPTION_CHECKBOX_VIEW;
            case "text":
                return OPTION_TEXT_VIEW;
            default:
                return 0;
        }
    }
}
