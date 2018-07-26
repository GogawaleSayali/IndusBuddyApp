package com.dogratech.indusbuddyapp.main.activities.healthcheckup.checkupfragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.helper.ErrorCodesAndMessagesManager;
import com.dogratech.indusbuddyapp.main.managers.SharedPrefsManager;
import com.dogratech.indusbuddyapp.main.models.ModelPaymentStatus;
import com.dogratech.indusbuddyapp.main.models.ModelPaymentStatusResponse;
import com.dogratech.indusbuddyapp.main.models.Model_Item_DeliveryKitStatus;
import com.dogratech.indusbuddyapp.main.models.Model_Item_RenewalStatus;
import com.dogratech.indusbuddyapp.main.models.Model_Response_DeliveryKit;
import com.dogratech.indusbuddyapp.main.models.Model_Response_RenewalStatus;
import com.dogratech.indusbuddyapp.main.retrofit.ApiClient;
import com.dogratech.indusbuddyapp.main.retrofit.ApiInterfaceGet;
import com.dogratech.indusbuddyapp.main.retrofit.ApiUrl;
import com.dogratech.indusbuddyapp.main.uitility.NetworkUtility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DashboardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String TAG = this.getClass().getName();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView tv_status ,tv_date ,tv_name ,tv_expiryDate ,tv_joining_status ,tv_joining_date,
            tv_renewal_status,tv_renewal_date,tv_checkupUpgrade_status,tv_checkupUpgrade_date;
    private ApiInterfaceGet interface_get ;
    private View DashboardFragment ;
    private OnFragmentInteractionListener mListener;
    private LinearLayout llPayJoining,llRenewal,llCheckUpUpgrade;
    private ImageView ivDownline,ivIncentive,ivKit,ivMemberShip,ivPaymentStatus;
    private SharedPrefsManager prefsManager;
    private String userId ;
    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
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
        DashboardFragment =  inflater.inflate(R.layout.fragment_dashboard, container, false);
        initialise();
        initialiseClass();
        requestDeliveryStatus();
        requestRenewalStatus();
        requestPaymentStatus();
        return DashboardFragment ;
    }

    private void initialiseClass() {
        interface_get     = ApiClient.getClient(ApiClient.BASE_URL_TYEP_MOBILE).create(ApiInterfaceGet.class);
        prefsManager      = SharedPrefsManager.getSharedInstance(getActivity());;
        userId            = prefsManager.getData(getString(R.string.shars_userid));
    }

    private void initialise() {
        tv_status         = DashboardFragment.findViewById(R.id.tv_status);
        tv_date           = DashboardFragment.findViewById(R.id.tv_date);
        tv_name           = DashboardFragment.findViewById(R.id.tv_name);
        tv_expiryDate     = DashboardFragment.findViewById(R.id.tv_expiryDate);
        tv_joining_status = DashboardFragment.findViewById(R.id.tv_joining_status);
        tv_joining_date   = DashboardFragment.findViewById(R.id.tv_joining_date);
        tv_renewal_status = DashboardFragment.findViewById(R.id.tv_renewal_status);
        tv_renewal_date   = DashboardFragment.findViewById(R.id.tv_renewal_date);
        tv_checkupUpgrade_status = DashboardFragment.findViewById(R.id.tv_checkupUpgrade_status);
        tv_checkupUpgrade_date   = DashboardFragment.findViewById(R.id.tv_checkupUpgrade_date);
        llPayJoining      = DashboardFragment.findViewById(R.id.llPayJoining);
        llRenewal         = DashboardFragment.findViewById(R.id.llRenewal);
        llCheckUpUpgrade  = DashboardFragment.findViewById(R.id.llCheckUpUpgrade);
        ivDownline        = DashboardFragment.findViewById(R.id.ivDownline);
        ivIncentive       = DashboardFragment.findViewById(R.id.ivIncentive);
        ivKit             = DashboardFragment.findViewById(R.id.ivKit);
        ivMemberShip      = DashboardFragment.findViewById(R.id.ivMemberShip);
        ivPaymentStatus   = DashboardFragment.findViewById(R.id.ivPaymentStatus);

        ivDownline.setColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
        ivIncentive.setColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
        ivKit.setColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
        ivMemberShip.setColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
        ivPaymentStatus.setColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
    }

    private void requestDeliveryStatus(){
        String url = ApiUrl.Base_URL_MOBILE +ApiUrl.getJoiningKITDLstatus+userId;
        if(NetworkUtility.isNetworkAvailable(getActivity())){
            interface_get.deliveryKitStatus(url).enqueue(new Callback<Model_Response_DeliveryKit>() {
                @Override
                public void onResponse(Call<Model_Response_DeliveryKit> call, Response<Model_Response_DeliveryKit> response) {
                    try {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Model_Response_DeliveryKit deliveryKit = response.body();
                                if (deliveryKit.getStatusCode().equalsIgnoreCase(getString(R.string.statuse_code_0))) {
                                    Model_Item_DeliveryKitStatus kit = deliveryKit.getItemDeliveryKit();
                                    setDeliveryStatusData(kit);
                                } else {
                                    String error_code = deliveryKit.getErrorCode();
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
                public void onFailure(Call<Model_Response_DeliveryKit> call, Throwable t) {
                    Log.d(TAG,t.toString());
                }
            });
        }else{
            snackInternet();
        }
    }
    private void requestRenewalStatus() {
        String url = ApiUrl.Base_URL_MOBILE +ApiUrl.getRenewalstatus+userId;
        if(NetworkUtility.isNetworkAvailable(getActivity())){
            interface_get.renewalStatus(url).enqueue(new Callback<Model_Response_RenewalStatus>() {
                @Override
                public void onResponse(Call<Model_Response_RenewalStatus> call, Response<Model_Response_RenewalStatus> response) {
                    try {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Model_Response_RenewalStatus renewalStatus = response.body();
                                if (renewalStatus.getStatusCode().equalsIgnoreCase(getString(R.string.statuse_code_0))) {
                                    Model_Item_RenewalStatus kit = renewalStatus.getItemRenewalStatus();
                                    setRenewalStatusData(kit);
                                } else {
                                    String error_code = renewalStatus.getErrorCode();
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
                public void onFailure(Call<Model_Response_RenewalStatus> call, Throwable t) {
                    Log.d(TAG,t.toString());
                }
            });
        }else{
            snackInternet();
        }
    }
    private void requestPaymentStatus() {
        String url = ApiUrl.Base_URL_MOBILE +ApiUrl.getPaymentstatus+userId;
        if(NetworkUtility.isNetworkAvailable(getActivity())){
            interface_get.paymentStatus(url).enqueue(new Callback<ModelPaymentStatusResponse>() {
                @Override
                public void onResponse(Call<ModelPaymentStatusResponse> call, Response<ModelPaymentStatusResponse> response) {
                    try {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                ModelPaymentStatusResponse paymentStatusResponse = response.body();
                                if (String.valueOf(paymentStatusResponse.getStatusCode()).equalsIgnoreCase(getString(R.string.statuse_code_0))) {
                                    ArrayList<ModelPaymentStatus> modelPaymentStatuses = paymentStatusResponse.getPaymentStatus();
                                    setPaymentStatuses(modelPaymentStatuses);
                                } else {
                                    String error_code = String.valueOf(paymentStatusResponse.getErrorCode());
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
                public void onFailure(Call<ModelPaymentStatusResponse> call, Throwable t) {
                    Log.d(TAG,t.toString());
                }
            });
        }else{
            snackInternet();
        }
    }

    private void setPaymentStatuses(ArrayList<ModelPaymentStatus> modelPaymentStatuses) {
        for (int i = 0 ; i<modelPaymentStatuses.size();i++){
            if (modelPaymentStatuses.get(i).getType().equalsIgnoreCase("Newsale")){
                tv_joining_date.setText("Date : "+modelPaymentStatuses.get(i).getReceiptDate().toUpperCase());
                tv_joining_status.setText("Status : "+modelPaymentStatuses.get(i).getStatus());

                llPayJoining.setVisibility(View.VISIBLE);
            }else  if (modelPaymentStatuses.get(i).getType().equalsIgnoreCase("Renewal")){
                llRenewal.setVisibility(View.VISIBLE);
                tv_renewal_date.setText("Date : "+modelPaymentStatuses.get(i).getReceiptDate().toUpperCase());
                tv_renewal_status.setText("Status : "+modelPaymentStatuses.get(i).getStatus());
            }else{
                tv_checkupUpgrade_date.setText("Date : "+modelPaymentStatuses.get(i).getReceiptDate().toUpperCase());
                tv_checkupUpgrade_status.setText("Status : "+modelPaymentStatuses.get(i).getStatus());
                llCheckUpUpgrade.setVisibility(View.VISIBLE);
            }
        }
    }

    private void setRenewalStatusData(Model_Item_RenewalStatus kit) {
        try {
            if (kit.getMemberName() != null) {
                tv_name.setText(getActivity().getString(R.string.dashboard_name)+" "+kit.getMemberName());
            }else{

            }

            if (kit.getExpiryDate() != null) {
                tv_expiryDate.setText(getActivity().getString(R.string.dashboard_expirydate)+" "+kit.getExpiryDate());
            }else{

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void showToast(String message) {
        Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    private void setDeliveryStatusData(Model_Item_DeliveryKitStatus kit) {
        try {
            if (kit.getDeliveryKitStatus() != null) {
                tv_status.setText(getActivity().getString(R.string.dashboard_Status)+" "+kit.getDeliveryKitStatus());
            }else{

            }

            if (kit.getDeliveryKitDate() != null) {
                tv_date.setText(getActivity().getString(R.string.dashboard_date)+" "+kit.getDeliveryKitDate());
            }else{

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
   * Internet checking code
   * */
    public void snackInternet(){
        Snackbar snackbar = Snackbar
                .make(DashboardFragment, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
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
