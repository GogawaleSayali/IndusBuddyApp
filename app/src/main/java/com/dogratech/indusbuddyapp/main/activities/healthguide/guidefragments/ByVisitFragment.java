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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.healthguide.GraphByVisitActivity;
import com.dogratech.indusbuddyapp.main.adapters.ParameterEmojiAdapter;
import com.dogratech.indusbuddyapp.main.managers.SharedPrefsManager;
import com.dogratech.indusbuddyapp.main.models.ModelItemByVisit;
import com.dogratech.indusbuddyapp.main.models.Model_Item_VisitList;
import com.dogratech.indusbuddyapp.main.models.Model_Item_Visit_ParameterList;
import com.dogratech.indusbuddyapp.main.models.Model_Response_VisitList_Parameter;
import com.dogratech.indusbuddyapp.main.models.ParamGraphModel;
import com.dogratech.indusbuddyapp.main.retrofit.ApiClient;
import com.dogratech.indusbuddyapp.main.retrofit.ApiInterfaceGet;
import com.dogratech.indusbuddyapp.main.retrofit.ApiUrl;
import com.dogratech.indusbuddyapp.main.uitility.NetworkUtility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ByVisitFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ByVisitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ByVisitFragment extends Fragment {
    private View rootView;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ParameterEmojiAdapter parameterEmojiAdapter;
    private ListView lvParameters;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ;
    private ApiInterfaceGet interface_get ;
    private SharedPrefsManager prefsManager ;
    private String userId ;
    private Spinner spinnerVisit ;
    private String TAG = this.getClass().getName();
    private OnFragmentInteractionListener mListener;
    Map<String, ArrayList<ModelItemByVisit>> map = new HashMap<>();
    private ArrayList<ParamGraphModel> paramWiseList = new ArrayList<>();
    private TextView tvDataNotFound,tvDataNotFound_visit;

    public ByVisitFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ByVisitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ByVisitFragment newInstance(String param1, String param2) {
        ByVisitFragment fragment = new ByVisitFragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_by_visit, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
        initialiseClass();
        requestGetByVisitList();
        setListeners();
    }

    private void setListeners() {
        spinnerVisit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String visit = (String) parent.getAdapter().getItem(position);
              //  if (!visit.equalsIgnoreCase("Select Visit")) {
                    lvParameters.setVisibility(View.VISIBLE);
                    tvDataNotFound.setVisibility(View.GONE);
                    tvDataNotFound_visit.setVisibility(View.GONE);
                    String[] visitParams = visit.split("-");
                    if (visitParams.length == 5)
                    {
                        String visitId = visitParams[1];
                        ArrayList<ModelItemByVisit> list = map.get(visitId.trim());
                        if (map.get(visitId.trim()).size()>0)
                        {
                            setDataToVisitList(map.get(visitId.trim()));
                        }else
                            {
                            lvParameters.setVisibility(View.GONE);
                            tvDataNotFound.setVisibility(View.VISIBLE);
                        }
                    }

               /* }else{
                    lvParameters.setVisibility(View.GONE);
                    tvDataNotFound.setVisibility(View.VISIBLE);
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lvParameters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                paramWiseList.clear();
                ParamGraphModel model = new ParamGraphModel();
                model.setTestStatus("no"); model.setVisitDate("0"); model.setParamValue("0.0");
                model.setUpperVal("0");    model.setLowerVal("0");  model.setVisitDate(" ");
                paramWiseList.add(model);

                Set<String> KeySet = map.keySet();
                int i = 0;
                String paramId = String.valueOf(parent.getAdapter().getItem(position)).trim();
                for (String key:KeySet){
                    ArrayList<ModelItemByVisit> itemByVisit = map.get(key);
                    for (ModelItemByVisit byVisit: itemByVisit) {
                        if (byVisit.getParameterId().trim().equalsIgnoreCase(paramId)){
                            if (!byVisit.getNormalValue().equalsIgnoreCase("NA")) {
                                ParamGraphModel graphModel = new ParamGraphModel();
                                graphModel.setLowerVal(byVisit.getLowerValue());
                                graphModel.setUpperVal(byVisit.getUpperValue());
                                graphModel.setParamValue(byVisit.getParameterValue());
                                graphModel.setTestStatus(byVisit.getTestResultStatus());
                                graphModel.setVisitDate(itemByVisit.get(i).getVisitDate());
                                paramWiseList.add(graphModel);
                                i++;
                            }
                        }
                    }
                }
               /* ParamGraphModel graphModel = new ParamGraphModel();
                graphModel.setLowerVal("70");  graphModel.setUpperVal("100");
                graphModel.setParamValue("65");graphModel.setTestStatus("abnormal low");
                graphModel.setVisitDate("18 Jan 2018"); paramWiseList.add(graphModel);

                ParamGraphModel graphModel1 = new ParamGraphModel();
                graphModel1.setLowerVal("70");   graphModel1.setUpperVal("100");
                graphModel1.setParamValue("125");graphModel1.setTestStatus("abnormal high");
                graphModel1.setVisitDate("25 Apr 2018");paramWiseList.add(graphModel1);
*/
                ParamGraphModel modelLast = new ParamGraphModel();
                modelLast.setTestStatus("no");  modelLast.setVisitDate("0");
                modelLast.setParamValue("0.0"); modelLast.setUpperVal("0");
                modelLast.setLowerVal("0");     modelLast.setVisitDate(" ");
                paramWiseList.add(modelLast);

                /*********************************************************
                 * If normal value is 1 then it has upper and lower value*
                 * other wise it doesn't have upper and lower value.     *
                 *********************************************************/
                if (parameterEmojiAdapter.getNormal(position).equalsIgnoreCase("1")) {
                    Gson gson = new Gson();
                    String jsonStr = gson.toJson(paramWiseList);
                    Intent intent = new Intent(getActivity(), GraphByVisitActivity.class);
                    intent.putExtra("paramData", jsonStr);
                    intent.putExtra("paramName", parameterEmojiAdapter.getParameterName(position));
                    startActivity(intent);
                }else {

                    /**normal Value is other than 1.**/
                }
            }
        });
    }

    private void requestGetByVisitList() {
       // String url = ApiUrl.Base_URL_INDUS + ApiUrl.trackParameterByEHRId +userId ;
        if(NetworkUtility.isNetworkAvailable(getActivity())){
        SharedPrefsManager prefsManager = SharedPrefsManager.getSharedInstance(getActivity());
        String userId = prefsManager.getData(getString(R.string.shars_userid));
       // userId = "179998"; //Dummy user id test puropose
        String url = ApiUrl.Base_URL_INDUS + ApiUrl.trackParameterByEHRId +userId;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_loader, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        alertDialog.show();
            interface_get.getVisitparamterList(url).enqueue(new Callback<Model_Response_VisitList_Parameter>() {
                @Override
                public void onResponse(Call<Model_Response_VisitList_Parameter> call, Response<Model_Response_VisitList_Parameter> response) {
                    try{
                        if (alertDialog.isShowing()){
                            alertDialog.hide();
                        }
                        if(response.isSuccessful()){
                            if(response.body()!=null){
                                Model_Response_VisitList_Parameter listParameter = response.body();
                                Model_Item_VisitList visitList = listParameter.getItemVisitList();
                                ArrayList<Model_Item_Visit_ParameterList> itemByVisit = visitList.getParameterLists();


                                ArrayList<String> visitDates = new ArrayList<>();
                                ArrayList<ModelItemByVisit> byVisits = new ArrayList<>();
                                ArrayList<ModelItemByVisit> byVisitsSorted = new ArrayList<>();
                               // visitDates.add("Select Visit");


                                for (int i = 0; i<itemByVisit.size(); i++) {
                                    byVisitsSorted.clear();
                                    byVisits = itemByVisit.get(i).getParameterList();
                                    ArrayList<ModelItemByVisit> itemByVisitNoData = new ArrayList<>();
                                    ArrayList<ModelItemByVisit> itemByVisitGreen = new ArrayList<>();
                                    ArrayList<ModelItemByVisit> itemByVisitRed = new ArrayList<>();
                                    ArrayList<ModelItemByVisit> itemByVisitOrange = new ArrayList<>();


                                    for (ModelItemByVisit visit :byVisits) {
                                        if (visit.getNormalValue().equalsIgnoreCase("1")){
                                            if (visit.getTestResultStatus().equalsIgnoreCase("normal")){
                                                itemByVisitGreen.add(visit);
                                            }else if (visit.getTestResultStatus().equalsIgnoreCase("abnormal low")){
                                                itemByVisitOrange.add(visit);
                                            }else if (visit.getTestResultStatus().equalsIgnoreCase("abnormal high")){
                                                itemByVisitRed.add(visit);
                                            }
                                        }else {
                                            itemByVisitNoData.add(visit);
                                        }
                                    }
                                    byVisitsSorted.addAll(itemByVisitGreen);
                                    byVisitsSorted.addAll(itemByVisitOrange);
                                    byVisitsSorted.addAll(itemByVisitRed);
                                    byVisitsSorted.addAll(itemByVisitNoData);

                                    String visitId = itemByVisit.get(i).getVisitId();
                                    String visitDate = itemByVisit.get(i).getVisitDate();
                                    visitDates.add("Visit Id - "+visitId+" - ( "+visitDate+" )");
                                    for (int j = 0;j<byVisitsSorted.size();j++){
                                        byVisitsSorted.get(j).setVisitDate(itemByVisit.get(i).getVisitDate());
                                    }
                                    map.put(visitId.trim(), byVisitsSorted);

                                }



                                setVisitDateToSpinner(visitDates);
                                TrackParameter.global_paramlist = byVisitsSorted;
                                TrackParameter.global_visitlist = listParameter.getItemVisitList().getParameterLists();
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<Model_Response_VisitList_Parameter> call, Throwable t) {
                    Log.d(TAG,t.toString());
                    if (alertDialog.isShowing()){
                        alertDialog.hide();
                    }
                }
            });
        }else{
            snackInternet();
        }

    }

    private void setDataToVisitList(ArrayList<ModelItemByVisit> byVisits) {
        if (byVisits.size()>0) {
            if (parameterEmojiAdapter == null) {
                parameterEmojiAdapter = new ParameterEmojiAdapter(getActivity(), byVisits);
                lvParameters.setAdapter(parameterEmojiAdapter);
            } else {
                parameterEmojiAdapter.updateData(byVisits);
            }
        }else{
            lvParameters.setVisibility(View.GONE);
            tvDataNotFound.setVisibility(View.VISIBLE);
        }
    }

    private void setVisitDateToSpinner(ArrayList<String> visitDates) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                        android.R.layout.simple_spinner_dropdown_item,visitDates);
        spinnerVisit.setAdapter(adapter); // this will set list of values to spinner
    }

    private void initialiseClass() {
        interface_get   = ApiClient.getClient(ApiClient.BASE_URL_TYEP_INDUS).create(ApiInterfaceGet.class);
        prefsManager    = SharedPrefsManager.getSharedInstance(getActivity());
        userId          = prefsManager.getData(getActivity().getString(R.string.shars_userid));


    }

    private void initialize() {
        lvParameters = rootView.findViewById(R.id.lvParameters);
        tvDataNotFound = rootView.findViewById(R.id.tvDataNotFound);
        tvDataNotFound_visit=rootView.findViewById(R.id.tvDataNotFound_visit);
        spinnerVisit = rootView.findViewById(R.id.spinnerVisit);

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    /*
   * Internet checking code
   * */
    public void snackInternet(){
        Snackbar snackbar = Snackbar
                .make(rootView, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });

        snackbar.setActionTextColor(Color.RED);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
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
