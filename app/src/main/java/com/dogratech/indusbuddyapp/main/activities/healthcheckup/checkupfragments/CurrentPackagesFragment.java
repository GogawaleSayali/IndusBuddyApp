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
import com.dogratech.indusbuddyapp.main.adapters.MyAvailedAdapter;
import com.dogratech.indusbuddyapp.main.helper.Constatnts;
import com.dogratech.indusbuddyapp.main.managers.SharedPrefsManager;
import com.dogratech.indusbuddyapp.main.models.AvailedPackagesModel;
import com.dogratech.indusbuddyapp.main.models.PackageDetailsModel;
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
 * {@link CurrentPackagesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CurrentPackagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentPackagesFragment extends Fragment implements View.OnClickListener{
    private View rootView;
    private RecyclerView recyclerView;
    private MyAvailedAdapter adapter;
    private ArrayList<PackageDetailsModel> packages;
    private RecyclerView.LayoutManager mLayoutManager ;
    private TextView tvTestAV,tvDataNotFound;
    private RelativeLayout rlWithPkg,rlWthoutPkg,rlProgress;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FloatingActionButton fabInstruction;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private boolean isPkg = false;
    private TextView tvGetPkg,tvCenterLocator;
    private OnFragmentInteractionListener mListener;

    public CurrentPackagesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CurrentPackagesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentPackagesFragment newInstance(String param1, String param2) {
        CurrentPackagesFragment fragment = new CurrentPackagesFragment();
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
        rootView = inflater.inflate(R.layout.fragment_current_packages, container, false);
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
        rlWithPkg       = rootView .findViewById(R.id.rlWithPkg);
        rlWthoutPkg     = rootView .findViewById(R.id.rlWthoutPkg);
        rlProgress     = rootView .findViewById(R.id.rlProgress);
        tvGetPkg        = rootView .findViewById(R.id.tvGetPkg);
        tvCenterLocator = rootView .findViewById(R.id.tvCenterLocator);
        recyclerView    = rootView .findViewById(R.id.recycler_view);
        tvTestAV        = rootView .findViewById(R.id.tvTestAV);
        tvDataNotFound  = rootView .findViewById(R.id.tvDataNotFound);
        fabInstruction  = rootView .findViewById(R.id.fabInstruction);
        packages        = new ArrayList<>();
        mLayoutManager  = new GridLayoutManager(getActivity(), 1);
        recyclerView    . setLayoutManager(mLayoutManager);
       // recyclerView  . addItemDecoration(new GridSpacingItemDecoration(2,
        // DeviceUtility.convertDpToPx(getActivity(),2), true));
        recyclerView    . setItemAnimator(new DefaultItemAnimator());


    }
    private void setListener() {
        tvTestAV       . setOnClickListener(this);
        tvGetPkg       .setOnClickListener(this);
        tvCenterLocator.setOnClickListener(this);
        fabInstruction .setOnClickListener(this);
        /*recyclerView          . addOnItemTouchListener(new RecyclerItemClickListener(
                getActivity(), this));*/
    }

    private void prepareAvailedPackagesData() {
        SharedPrefsManager prefsManager = SharedPrefsManager.getSharedInstance(getActivity());
        if (NetworkUtility.isNetworkAvailable(getActivity())) {
            rlProgress.setVisibility(View.VISIBLE);
            String userId = prefsManager.getData(getString(R.string.shars_userid));
            //userId = "210263"; // <--- Dummy user
            String url = ApiUrl.Base_URL_MOBILE + ApiUrl.GetPendingPackageDetails + userId;
            ApiInterfaceGet interfaceGet = ApiClient.getClient(ApiUrl.Base_URL_MOBILE).create(ApiInterfaceGet.class);
            interfaceGet.getAvailedPackes(url).enqueue(new Callback<AvailedPackagesModel>() {
                @Override
                public void onResponse(Call<AvailedPackagesModel> call, Response<AvailedPackagesModel> response) {
                    if (rlProgress.getVisibility() == View.VISIBLE){ rlProgress.setVisibility(View.GONE);}
                    if (response.body() != null) {
                        AvailedPackagesModel packagesModel = response.body();
                        if (packagesModel.getStatusCode() == Constatnts.S_CODE_0) {
                            packages = packagesModel.getPackageDetailsModels();
                            if(packages!=null) {
                                if (packages.size() > 0) {
                                    rlWithPkg.setVisibility(View.VISIBLE);
                                    rlWthoutPkg.setVisibility(View.GONE);
                                    tvDataNotFound.setVisibility(View.GONE);
                                    adapter = new MyAvailedAdapter(getActivity(), packages);
                                    recyclerView . setAdapter(adapter);
                                }else {
                                    if (AppHomeActivity.ROLE.equalsIgnoreCase("C")){
                                        tvDataNotFound.setVisibility(View.VISIBLE);
                                        rlWthoutPkg.setVisibility(View.GONE);
                                        rlWithPkg.setVisibility(View.GONE);
                                    }else {
                                        tvDataNotFound.setVisibility(View.GONE);
                                        rlWthoutPkg.setVisibility(View.VISIBLE);
                                        rlWithPkg.setVisibility(View.GONE);
                                    }
                                }
                            }else {
                                if (AppHomeActivity.ROLE.equalsIgnoreCase("C")){
                                    tvDataNotFound.setVisibility(View.VISIBLE);
                                    rlWthoutPkg.setVisibility(View.GONE);
                                    rlWithPkg.setVisibility(View.GONE);
                                }else {
                                    tvDataNotFound.setVisibility(View.GONE);
                                    rlWthoutPkg.setVisibility(View.VISIBLE);
                                    rlWithPkg.setVisibility(View.GONE);
                                }
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<AvailedPackagesModel> call, Throwable t) {
                    if (rlProgress.getVisibility() == View.VISIBLE){ rlProgress.setVisibility(View.GONE);}
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

 /*   @Override
    public void onItemClick(View view, int position) {
        Intent  intent =new Intent(getActivity(),UnavailedPackageDetailsActivity.class);
        intent.putExtra("from","current");
        startActivity(intent);
    }
*/
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

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column
            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)
                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.tvTestAV :
                startActivity(new Intent(getActivity(),TestAVActivity.class));
                break;
            case R.id.tvGetPkg:
                openURLinBrowser();
                //startActivity(new Intent(getActivity(), IndusSitesURLActivity.class));
                break;
            case R.id.tvCenterLocator:
                startActivity(new Intent(getActivity(), CenterLocatorActivity.class));
                break;
            case R.id.fabInstruction:
                startActivity(new Intent(getActivity(), InstructionCallActivity.class));
                break;
            /*case R.id.tvCentreLocator:
                        startActivity(new Intent(MyHealthChekUpActivity.this,CentreSelectionActivity.class));
                break;
            case R.id.llUnAvailedPkg:
                        startActivity(new Intent(MyHealthChekUpActivity.this,UnAvailedPackagesActivity.class));
                break;
            case R.id.llAvailedPkg:
<<<<<<< HEAD:app/src/main/java/com/dogratech/indusbuddyapp/main/activity/MyHealthChekUpActivity.java
                       // startActivity(new Intent(MyHealthChekUpActivity.this,UnAvailedPackagesActivity.class));
                break;*/
            //    startActivity(new Intent(MyHealthChekUpActivity.this,MyAvailedPackagesActivity.class));
            //    break;
        }
    }
    public void openURLinBrowser(){
        String url = "http://www.indusites.com/member-login-indusite.html";
        if (AppHomeActivity.ROLE.equalsIgnoreCase("M")){
            url = ApiUrl.CD_USER_URL;
        }else if (AppHomeActivity.ROLE .equalsIgnoreCase("P") ||
                AppHomeActivity.ROLE.equalsIgnoreCase("T")){
            url = ApiUrl.PHARMA_AND_TELESALES_USER_URL;
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

}
