package com.indushealthplus.android.indusbuddy.activities.healthcheckup.checkupfragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.adapters.MyAvailedAdapter;
import com.indushealthplus.android.indusbuddy.helper.Constatnts;
import com.indushealthplus.android.indusbuddy.managers.SharedPrefsManager;
import com.indushealthplus.android.indusbuddy.models.AvailedPackagesModel;
import com.indushealthplus.android.indusbuddy.models.PackageDetailsModel;
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
 * {@link AvailedPackagesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class AvailedPackagesFragment extends Fragment {
    private View rootView;
    private RecyclerView recyclerView;
    private MyAvailedAdapter adapter;
    private ArrayList<PackageDetailsModel> packages;
    private RecyclerView.LayoutManager mLayoutManager ;
    private SharedPrefsManager sharedPrefsManager;
    private OnFragmentInteractionListener mListener;
    private TextView tvDataNotFound;
    private RelativeLayout rlPrpgress;
    public AvailedPackagesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_history_packages, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialise();
        setListener();
        prepareAvailedPackagesData();
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
    private void prepareAvailedPackagesData() {
        if (NetworkUtility.isNetworkAvailable(getActivity())) {
            rlPrpgress.setVisibility(View.VISIBLE);
            String userId = sharedPrefsManager.getData(getString(R.string.shars_userid));
            //userId = "210263"; // <--- Dummy user
            String url = ApiUrl.Base_URL_MOBILE + ApiUrl.GetPackageDetails + userId;
            ApiInterfaceGet interfaceGet = ApiClient.getClient(ApiUrl.Base_URL_MOBILE).create(ApiInterfaceGet.class);
            interfaceGet.getAvailedPackes(url).enqueue(new Callback<AvailedPackagesModel>() {
                @Override
                public void onResponse(Call<AvailedPackagesModel> call, Response<AvailedPackagesModel> response) {
                    if (rlPrpgress.getVisibility() == View.VISIBLE){rlPrpgress.setVisibility(View.GONE); }
                    if (response.body() != null) {
                        AvailedPackagesModel packagesModel = response.body();
                        if (packagesModel.getStatusCode() == Constatnts.S_CODE_0) {
                            packages = packagesModel.getPackageDetailsModels();
                            if(packages!=null) {
                                if (packages.size() > 0) {
                                    tvDataNotFound.setVisibility(View.GONE);
                                    adapter      = new MyAvailedAdapter(getActivity(), packages);
                                    recyclerView . setAdapter(adapter);
                                }else {
                                    tvDataNotFound.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<AvailedPackagesModel> call, Throwable t) {
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
        void onFragmentInteraction(Uri uri);
    }
}
