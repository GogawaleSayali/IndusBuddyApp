package com.indushealthplus.android.indusbuddy.activities.healthguide.guidefragments;

import android.content.Context;
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
import android.widget.FrameLayout;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.adapters.DoctorCommentAdapter;
import com.indushealthplus.android.indusbuddy.helper.Constatnts;
import com.indushealthplus.android.indusbuddy.managers.SharedPrefsManager;
import com.indushealthplus.android.indusbuddy.models.Model_Item_AnalysisObject;
import com.indushealthplus.android.indusbuddy.models.Model_Item_doctor_comment;
import com.indushealthplus.android.indusbuddy.models.Model_Response_DoctorComment;
import com.indushealthplus.android.indusbuddy.retrofit.ApiClient;
import com.indushealthplus.android.indusbuddy.retrofit.ApiInterfaceGet;
import com.indushealthplus.android.indusbuddy.retrofit.ApiUrl;
import com.indushealthplus.android.indusbuddy.uitility.NetworkUtility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DoctorCommentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DoctorCommentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoctorCommentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final String TAG = this.getClass().getName();
    private RecyclerView rv_doctorView;
    private DoctorCommentAdapter adapter;
    private TextView tvNoCommentMsg;
    private ApiInterfaceGet interface_get;
    private ArrayList<Model_Item_doctor_comment> doctorCommentList;
    private View DoctorComment;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SharedPrefsManager prefsManager;
    private String userId;
    private FrameLayout frame;

    private OnFragmentInteractionListener mListener;

    public DoctorCommentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DoctorCommentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DoctorCommentFragment newInstance(String param1, String param2) {
        DoctorCommentFragment fragment = new DoctorCommentFragment();
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




    private Boolean isStarted = false;
    private Boolean isVisible = false;



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if (isStarted && isVisible) {
            loadComments();
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        DoctorComment = inflater.inflate(R.layout.fragment_doctor_comment, container, false);
        return DoctorComment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initiliseClass();
        initialise();
        // loadComments();
    }

    private void initialise() {
        rv_doctorView = DoctorComment.findViewById(R.id.rv_doctorView);
        tvNoCommentMsg = DoctorComment.findViewById(R.id.tvNoCommentMsg);
        frame = DoctorComment.findViewById(R.id.frame);
        isStarted = true;
        if (isVisible && isStarted){

            loadComments();
        }


    }

    private void initiliseClass() {
        interface_get = ApiClient.getClient(ApiClient.BASE_URL_TYEP_INDUS).create(ApiInterfaceGet.class);
        prefsManager = SharedPrefsManager.getSharedInstance(getActivity());
        userId = prefsManager.getData(getString(R.string.shars_userid));
        doctorCommentList = new ArrayList<>();
        // loadComments();
    }


    private void loadComments() {
        String url = ApiUrl.Base_URL_INDUS + ApiUrl.getAnalysisCommentByEHRId + userId;
        if (NetworkUtility.isNetworkAvailable(getActivity())) {

            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custom_loader, null);
            dialogBuilder.setView(dialogView);
            final AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.setCancelable(false);
            try {
                alertDialog.show();
            } catch (Exception e) {
            }

            interface_get.getDoctorComments(url).enqueue(new Callback<Model_Response_DoctorComment>() {
                @Override
                public void onResponse(Call<Model_Response_DoctorComment> call, Response<Model_Response_DoctorComment> response) {
                    try {
                        if (alertDialog.isShowing()) {
                            alertDialog.hide();
                        }
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Model_Response_DoctorComment doctorComment = response.body();
                                if (doctorComment.getStatus_code() == Constatnts.S_CODE_0) {
                                    Model_Item_AnalysisObject analysisObject = doctorComment.getAnalysisObject();
                                    ArrayList<Model_Item_doctor_comment> comments = analysisObject.getAnalysisComments();
                                   /* String dataDummy = "{     \"msg\": \"success\",     \"status_code\": 0,     \"data\": {         \"analysisComments\": [             {                 \"visitId\": \"1951216\",                 \"commentTypeId\": \"1\",                 \"analysisCommentId\": \"27775\",                 \"addedBy\": \"amol\",                 \"analysisComment\": \"neee\",                 \"comment\": \"Previous\",                 \"classification\": \"Urgent\",                 \"addedOn\": \"04-Apr-2018 10:57:36\"             },             {                 \"visitId\": \"1951216\",                 \"commentTypeId\": \"2\",                 \"analysisCommentId\": \"27776\",                 \"addedBy\": \"amol\",                 \"analysisComment\": \"grrdrfvgf\",                 \"comment\": \"New Com1\",                 \"classification\": \"Urgent\",                 \"addedOn\": \"04-Apr-2018 10:57:36\"             }         ]     },     \"error_code\": 0 }";
                                    JSONObject jsonObject = new JSONObject(dataDummy);
                                    JSONObject jsondata = jsonObject.getJSONObject("data");
                                    JSONArray jsonArray = jsondata.getJSONArray("analysisComments");
                                    ArrayList<Model_Item_doctor_comment> comments2 = new ArrayList<>();
                                    for (int i = 0; i < jsonArray.length();i++){
                                        Model_Item_doctor_comment comment = new Model_Item_doctor_comment();
                                        JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                                        comment.setAddedBy(jsonObject1.getString("addedBy"));
                                        comment.setAddedOn(jsonObject1.getString("addedOn"));
                                        comment.setAnalysisComment(jsonObject1.getString("analysisComment"));
                                        comment.setAnalysisCommentId(jsonObject1.getString("analysisCommentId"));
                                        comment.setClassification(jsonObject1.getString("classification"));
                                        comment.setComment(jsonObject1.getString("comment"));
                                        comment.setCommentTypeId(jsonObject1.getString("commentTypeId"));
                                        comment.setVisitId(jsonObject1.getString("visitId"));
                                        comments2.add(comment);
                                    }
                                    comments =comments2;*/
                                    if (comments != null) {
                                        if (comments.size() > 0) {
                                            tvNoCommentMsg.setVisibility(View.INVISIBLE);
                                            doctorCommentList = comments;
                                          /*  if (adapter != null) {
                                                adapter.update(doctorCommentList);
                                            } else {*/
                                                setCommentData(doctorCommentList);
                                            //}
                                        } else {
                                            tvNoCommentMsg.setVisibility(View.VISIBLE);
                                        }
                                    }

                                  /* else
                                        {
                                        tvNoCommentMsg.setVisibility(View.VISIBLE);
                                        }*/
                                }/*else{
                                    tvNoCommentMsg.setVisibility(View.VISIBLE);
                                }*/
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<Model_Response_DoctorComment> call, Throwable t) {
                    Log.d(TAG, t.toString());
                    if (alertDialog.isShowing()) {
                        alertDialog.hide();
                    }
                    tvNoCommentMsg.setVisibility(View.VISIBLE);
                }
            });
        } else {
            snackInternet();
        }
    }

    /*
     * Internet checking code
     * */
    public void snackInternet() {
        Snackbar snackbar = Snackbar
                .make(frame, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
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

    private void setCommentData(List<Model_Item_doctor_comment> doctorCommentList) {
        adapter = new DoctorCommentAdapter(getActivity(), doctorCommentList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv_doctorView.setLayoutManager(mLayoutManager);
        rv_doctorView.setItemAnimator(new DefaultItemAnimator());
        rv_doctorView.setAdapter(adapter);
    }


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
