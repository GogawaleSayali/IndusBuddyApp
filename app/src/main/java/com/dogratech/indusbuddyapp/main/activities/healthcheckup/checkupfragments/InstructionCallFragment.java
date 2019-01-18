package com.dogratech.indusbuddyapp.main.activities.healthcheckup.checkupfragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.apphomeactivity.AppHomeActivity;
import com.dogratech.indusbuddyapp.main.activities.healthcheckup.CenterLocatorActivity;
import com.dogratech.indusbuddyapp.main.activities.healthcheckup.InstructionCallActivity;
import com.dogratech.indusbuddyapp.main.activities.healthcheckup.TestAVActivity;
import com.dogratech.indusbuddyapp.main.adapters.IntructionCallAdapter;
import com.dogratech.indusbuddyapp.main.adapters.MyAvailedAdapter;
import com.dogratech.indusbuddyapp.main.adapters.MyPackages_Adapter;
import com.dogratech.indusbuddyapp.main.helper.Constatnts;
import com.dogratech.indusbuddyapp.main.managers.SharedPrefsManager;
import com.dogratech.indusbuddyapp.main.models.AvailedPackagesModel;
import com.dogratech.indusbuddyapp.main.models.InstructionCallModel;
import com.dogratech.indusbuddyapp.main.models.PackageDetailsModel;
import com.dogratech.indusbuddyapp.main.models.PckgwiseInstrctionCallModel;
import com.dogratech.indusbuddyapp.main.retrofit.ApiClient;
import com.dogratech.indusbuddyapp.main.retrofit.ApiInterfaceGet;
import com.dogratech.indusbuddyapp.main.retrofit.ApiUrl;
import com.dogratech.indusbuddyapp.main.uitility.NetworkUtility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InstructionCallFragment extends Fragment {
    private View rootView;
    private RecyclerView recyclerView;
    private IntructionCallAdapter adapter;
    private ArrayList<PckgwiseInstrctionCallModel> instructions;
    private RecyclerView.LayoutManager mLayoutManager ;
    private SharedPrefsManager sharedPrefsManager;
    private InstructionCallFragment.OnFragmentInteractionListener mListener;
    private TextView tvDataNotFound;
    private RelativeLayout rlPrpgress;
    public InstructionCallFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_instruction_call, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialise();
        setListener();
        pckgwiseInstructionCallList();
    }

    private void initialise() {
        recyclerView       = rootView .findViewById(R.id.recycler_view);
        tvDataNotFound     =  rootView.findViewById(R.id.tvDataNotFound);
        rlPrpgress     =  rootView.findViewById(R.id.rlPrpgress);
        sharedPrefsManager = SharedPrefsManager.getSharedInstance(getActivity());
        mLayoutManager     = new GridLayoutManager(getActivity(), 1);
        recyclerView       . setLayoutManager(mLayoutManager);
        //recyclerView     . addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(2), true));
        recyclerView       . setItemAnimator(new DefaultItemAnimator());
    }
    private void setListener() {

    }

    /*********************************
     * Get the avaialed packages list*
     *********************************/
    private void pckgwiseInstructionCallList() {
        SharedPrefsManager prefsManager = SharedPrefsManager.getSharedInstance(getActivity());
        if (NetworkUtility.isNetworkAvailable(getActivity())) {
            rlPrpgress.setVisibility(View.VISIBLE);
            String userId = sharedPrefsManager.getData(getString(R.string.shars_userid));
            //userId = "210263"; // <--- Dummy user
            String packageList = prefsManager.getData(getString(R.string.shares_pckList));
            Log.d("Instruction TAG :","Instruction Package List" + packageList);

            String url = ApiUrl.Base_URL_MOBILE + ApiUrl.GetPendingPackageDetails + userId;
            ApiInterfaceGet interfaceGet = ApiClient.getClient(ApiUrl.Base_URL_MOBILE).create(ApiInterfaceGet.class);

//            interfaceGet.getPckwiseInstructionCallList(packageList).enqueue(new Callback<InstructionCallModel>() {
            interfaceGet.getPckwiseInstructionCallList(url).enqueue(new Callback<InstructionCallModel>() {
                @Override
                public void onResponse(Call<InstructionCallModel> call, Response<InstructionCallModel> response) {
                    if (rlPrpgress.getVisibility() == View.VISIBLE){rlPrpgress.setVisibility(View.GONE); }
                    if (response.body() != null) {
                        InstructionCallModel instructionsModel = response.body();
                        if (instructionsModel.getStatusCode() == Constatnts.S_CODE_0) {
                            instructions = instructionsModel.getPckWise();
                            if(instructions!=null) {
                                if (instructions.size() > 0) {
                                    tvDataNotFound.setVisibility(View.GONE);
                                    adapter      = new IntructionCallAdapter(getActivity(), instructions);
                                    recyclerView . setAdapter(adapter);
                                }else {
                                    tvDataNotFound.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<InstructionCallModel> call, Throwable t) {
                    if (rlPrpgress.getVisibility() == View.VISIBLE){rlPrpgress.setVisibility(View.GONE); }
                }
            });
        }else {
            snackInternet();
        }
    }

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

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InstructionCallFragment.OnFragmentInteractionListener) {
            mListener = (InstructionCallFragment.OnFragmentInteractionListener) context;
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
        void onFragmentInteraction(Uri uri);
    }
}
