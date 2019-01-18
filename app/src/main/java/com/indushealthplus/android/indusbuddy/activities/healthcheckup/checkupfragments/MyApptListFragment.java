package com.indushealthplus.android.indusbuddy.activities.healthcheckup.checkupfragments;

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
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.helper.ErrorCodesAndMessagesManager;
import com.indushealthplus.android.indusbuddy.managers.SharedPrefsManager;
import com.indushealthplus.android.indusbuddy.models.Model_Item_Appointment;
import com.indushealthplus.android.indusbuddy.models.Model_Response_Appointment_Details;
import com.indushealthplus.android.indusbuddy.retrofit.ApiClient;
import com.indushealthplus.android.indusbuddy.retrofit.ApiInterfaceGet;
import com.indushealthplus.android.indusbuddy.retrofit.ApiUrl;
import com.indushealthplus.android.indusbuddy.uitility.NetworkUtility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyApptListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyApptListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyApptListFragment extends Fragment {
    private View rootView;
    private RecyclerView rvMyAppt;
    protected String userId ;
    TextView tvDataNotFound1;
    protected SharedPrefsManager prefsManager ;
    protected ApiInterfaceGet interface_get ;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RelativeLayout rlPrpgress;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MyApptListFragment() {
        // Required empty public constructor
    }

    public static MyApptListFragment newInstance(String param1, String param2) {
        MyApptListFragment fragment = new MyApptListFragment();
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
        rootView = inflater.inflate(R.layout.fragment_my_appt_list, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
    }

    private void initialize() {
        prefsManager         = SharedPrefsManager.getSharedInstance(getActivity());
        interface_get        = ApiClient.getClient(ApiClient.BASE_URL_TYEP_MOBILE).create(ApiInterfaceGet.class);
        userId               = prefsManager.getData(getString(R.string.shars_userid));
        rvMyAppt =rootView.findViewById(R.id.rvMyAppt);
        requestGetAppointmentDetails();
    }

    protected void requestGetAppointmentDetails() {

        String url = ApiUrl.Base_URL_MOBILE +ApiUrl.getAppointmentDetails+userId;
        if(NetworkUtility.isNetworkAvailable(getActivity())){
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custom_loader, null);
            dialogBuilder.setView(dialogView);
            final AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.setCancelable(false);
            alertDialog.show();
            interface_get.getAppointment(url).enqueue(new Callback<Model_Response_Appointment_Details>() {
                @Override
                public void onResponse(Call<Model_Response_Appointment_Details> call, Response<Model_Response_Appointment_Details> response) {
                    try{
                        if (alertDialog.isShowing()){
                            alertDialog.hide();
                        }
                        if(response.isSuccessful()){
                            if(response.body()!=null){
                                Model_Response_Appointment_Details details = response.body();
                                if(details.getStatusCode().equalsIgnoreCase(getString(R.string.statuse_code_0)))
                                {
                                    ArrayList<Model_Item_Appointment> appointment = details.getAppointment();
                                   // setAppointmentData(appointment);
                                }else{
                                    String error_code = details.getErrorCode();
                                    ErrorCodesAndMessagesManager errCodeMsg = ErrorCodesAndMessagesManager.getInstance();
                                    showToast(errCodeMsg.getErrorMessage(Integer.parseInt(error_code)));
                                    }

                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<Model_Response_Appointment_Details> call, Throwable t) {
                    Log.d("TAG",t.toString());
                    if (alertDialog.isShowing()){
                        alertDialog.hide();
                    }
                }
            });
        }else{
            snackInternet();
        }

    }

    /*
     * Internet checking code
     * */
    public void snackInternet(){
        Snackbar snackbar = Snackbar
                .make(rvMyAppt, getString(R.string.netConnection), Snackbar.LENGTH_LONG)
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

    protected void showToast(String message) {
        Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
