package com.dogratech.indusbuddyapp.main.activities.healthguide.guidefragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.healthguide.HRAActivity;
import com.dogratech.indusbuddyapp.main.adapters.AdapterHraType;
import com.dogratech.indusbuddyapp.main.helper.Constatnts;
import com.dogratech.indusbuddyapp.main.managers.SharedPrefsManager;
import com.dogratech.indusbuddyapp.main.models.AnswersArrayModel;
import com.dogratech.indusbuddyapp.main.models.HRAAnswerMainModel;
import com.dogratech.indusbuddyapp.main.models.HraTypeModel;
import com.dogratech.indusbuddyapp.main.models.ModelHra;
import com.dogratech.indusbuddyapp.main.models.ModelResponseQuestionary;
import com.dogratech.indusbuddyapp.main.models.Model_Item_Question;
import com.dogratech.indusbuddyapp.main.models.Model_Item_hraTypes;
import com.dogratech.indusbuddyapp.main.models.Model_Item_hraTypes_answerArray;
import com.dogratech.indusbuddyapp.main.models.Model_Response_Report;
import com.dogratech.indusbuddyapp.main.retrofit.ApiClient;
import com.dogratech.indusbuddyapp.main.retrofit.ApiInterfaceGet;
import com.dogratech.indusbuddyapp.main.retrofit.ApiInterfacePost;
import com.dogratech.indusbuddyapp.main.retrofit.ApiUrl;
import com.dogratech.indusbuddyapp.main.uitility.NetworkUtility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HRAFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HRAFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HRAFragment extends Fragment implements View.OnClickListener{
    private View rootView;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "HRAFragment";
    private String userId ;
    private RecyclerView rvHra;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<HRAAnswerMainModel> answerArray;
    private ArrayList<ModelHra> data = new ArrayList<>();
    private ArrayList<Map<String,HraTypeModel>> mapList = new ArrayList<>();
    private OnFragmentInteractionListener mListener;
    private ApiInterfaceGet interface_get;
    private TextView tvSubmit;
    public static ArrayList<HRAAnswerMainModel> myAnswerArray = new ArrayList<>();
    private SharedPrefsManager prefsManager;
    ArrayList<HRAAnswerMainModel>  hraAnswerMainModels = new ArrayList<>();

    public HRAFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HRAFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HRAFragment newInstance(String param1, String param2) {
        HRAFragment fragment = new HRAFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<HRAAnswerMainModel>>() {}.getType();
            answerArray = gson.fromJson(mParam1,type);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_hra, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
        setListeners();
    }

    private void setListeners() {
        tvSubmit.setOnClickListener(this);
    }

    private void initialize() {
        data            = new ArrayList<>();
        interface_get = ApiClient.getClient(ApiClient.BASE_URL_TYEP_INDUS).create(ApiInterfaceGet.class);
        prefsManager    = SharedPrefsManager.getSharedInstance(getActivity());
        userId          = prefsManager.getData(getActivity().getString(R.string.shars_userid));
        rvHra = rootView .findViewById(R.id.rvHra);
        tvSubmit = rootView .findViewById(R.id.tvSubmit);
        requestHRAByEhrID();
    }

    private void requestHRAByEhrID() {
        if (NetworkUtility.isNetworkAvailable(getActivity())){
        String url = ApiUrl.Base_URL_INDUS + ApiUrl.getHRAByEHRId + userId ;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_loader, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        alertDialog.show();
            interface_get.getHRAByEHRId(url).enqueue(new Callback<ModelResponseQuestionary>() {
                @Override
                public void onResponse(Call<ModelResponseQuestionary> call, Response<ModelResponseQuestionary> response) {
                    try{
                        if (alertDialog.isShowing()){
                            alertDialog.hide();
                        }
                        if(response.isSuccessful()){
                            if(response.body()!=null){
                                data.clear();
                                ModelResponseQuestionary responseQuestionary = response.body();
                                if (responseQuestionary.getStatus_code() == Constatnts.S_CODE_0){
                                    Model_Item_hraTypes_answerArray typesAnswerArray = responseQuestionary
                                            .getTypesAnswerArray();
                                    ArrayList<Model_Item_hraTypes> hraTypes = typesAnswerArray.getHraTypes();
                                    final ArrayList<HraTypeModel> hraTypeModels = new ArrayList<>();
                                    ArrayList<AnswersArrayModel> answers = typesAnswerArray.getAnswerArray();
                                    ArrayList<Model_Item_Question> questions = new ArrayList<>();
                                    mapList.clear();
                                    for (int i = 0;i<hraTypes.size(); i++){
                                        HraTypeModel typeModel = new HraTypeModel();
                                        questions = hraTypes.get(i).getQuestions();

                                        for (int k = 0; k<questions.size();k++){
                                            for (int j = 0;j<answers.size();j++){
                                                if (questions.get(k).getQuestionId() == answers.get(j).getQuestionId()){
                                                    questions.get(k).setAnswString(answers.get(j).getTextAnswer());
                                                }
                                            }
                                        }

                                        typeModel.setHraType(hraTypes.get(i).getHraTypeName().replace(":",""));
                                        typeModel.setNumberOfQuestions(questions.size());
                                        typeModel.setSolvedQuestions(answers.size());
                                        typeModel.setQuestionsOfHraType(questions);
                                        hraTypeModels.add(typeModel);
                                        Map<String,HraTypeModel> map = new HashMap<>();
                                        map.put(hraTypes.get(i).getHraTypeName().replace(":",""),typeModel);
                                        mapList.add(map);
                                    }

                                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                                    rvHra.setLayoutManager(mLayoutManager);
                                    rvHra.setItemAnimator(new DefaultItemAnimator());
                                    AdapterHraType adapterHraType =new AdapterHraType(hraTypeModels, new AdapterHraType.MyClickListener() {
                                        @Override
                                        public void onItemClick(int position, View v) {
                                            Map<String,HraTypeModel> modelMap = mapList.get(position);
                                            Gson gson = new Gson();
                                            String jsonStr = gson.toJson(modelMap.get(hraTypeModels.get(position).getHraType()));
                                            Intent intent = new Intent(getActivity(), HRAActivity.class);
                                            intent.putExtra("hra",jsonStr);
                                            startActivity(intent);
                                        }
                                    });
                                    rvHra.setAdapter(adapterHraType);
                                    /*RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                                    rvHra.setLayoutManager(mLayoutManager);
                                    rvHra.setItemAnimator(new DefaultItemAnimator());
                                    rvHra.setAdapter(new AdapterHra(data));*/
                                }
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ModelResponseQuestionary> call, Throwable t) {
                    Log.d(TAG,t.toString());
                    if (alertDialog.isShowing()){
                        alertDialog.hide();
                    }
                }
            });
        }else{
            showSnackBar(getString(R.string.no_internet));
        }

    }


     /**************************
      * Internet checking code *
      **************************/
    public void showSnackBar(String msg){
        Snackbar snackbar = Snackbar
                .make(rootView, msg, Snackbar.LENGTH_LONG)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });

        snackbar.setActionTextColor(Color.RED);
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvSubmit:
                if (myAnswerArray!=null){
                    if (hraAnswerMainModels.size()==0){
                    for (int i = 0 ; i < myAnswerArray.size();i++) {
                        if (!myAnswerArray.get(i).getAnswer().equalsIgnoreCase("")) {
                            hraAnswerMainModels.add(myAnswerArray.get(i));
                        }
                    }
                    }
                    Gson gson = new Gson();
                    String string  = gson.toJson(hraAnswerMainModels);
                    Log.v("string json : ","data like  : "+string);
                    if (hraAnswerMainModels.size()>0){
                        saveHRA();
                    }
                }
                break;
        }
    }


    /**************************
     * Save HRA to the server *
     **************************/
    private void saveHRA() {
        if (NetworkUtility.isNetworkAvailable(getActivity())){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_loader, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        alertDialog.show();
            ApiInterfacePost interface_post = ApiClient.getClient(ApiClient.BASE_URL_TYEP_INDUS).create(ApiInterfacePost.class);
            interface_post.saveHRAAnswers(hraAnswerMainModels).enqueue(new Callback<Model_Response_Report>() {
                @Override
                public void onResponse(Call<Model_Response_Report> call, Response<Model_Response_Report> response) {
                    if (alertDialog.isShowing()){
                        alertDialog.hide();
                    }
                    if (response.body()!=null){
                        Model_Response_Report report = response.body();
                        if (report.getStatus_code() == Constatnts.S_CODE_0){
                            myAnswerArray.clear();
                            hraAnswerMainModels.clear();
                            Toast.makeText(getActivity(), "HRA saved, Thank you", Toast.LENGTH_SHORT).show();
                            requestHRAByEhrID();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Model_Response_Report> call, Throwable t) {
                    if (alertDialog.isShowing()){
                        alertDialog.hide();
                    }
                }
            });
        }else {
            showSnackBar(getString(R.string.no_internet));
        }

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
