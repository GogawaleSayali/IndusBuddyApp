package com.indushealthplus.android.indusbuddy.activities.healthguide.guidefragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.models.ModelItemByVisit;
import com.indushealthplus.android.indusbuddy.models.Model_Item_Visit_ParameterList;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TrackParameter.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TrackParameter#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrackParameter extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //private BarChart mBarChart ;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View rootView;
    private TextView tvVisitWiseTab,tvParameterWise;
    private OnFragmentInteractionListener mListener;
    public static ArrayList<ModelItemByVisit> global_paramlist ;
    public static ArrayList<Model_Item_Visit_ParameterList> global_visitlist;

    public TrackParameter() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment rootView.
     */
    // TODO: Rename and change types and number of parameters
    public static TrackParameter newInstance(String param1, String param2) {
        TrackParameter fragment = new TrackParameter();
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
        rootView =  inflater.inflate(R.layout.fragment_track_parameter, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialise();
        initialiseClass();
        setListeners();
    }

    private void initialiseClass() {
        global_paramlist    = new ArrayList<>();
        global_visitlist    = new ArrayList<>();
    }

    private void setListeners() {
        tvParameterWise.setOnClickListener(this);
        tvVisitWiseTab.setOnClickListener(this);
    }

    private void initialise() {
       // mBarChart           = (BarChart) rootView.findViewById(R.id.barchart);
        tvVisitWiseTab      =  rootView.findViewById(R.id.tvVisitWiseTab);
        tvParameterWise     =  rootView.findViewById(R.id.tvParameterWiseTab);
        displayView(1);
      //  SetBarElements(30,30,40,100);
    }



    public void displayView(int position) {
        String title = getString(R.string.app_name);
        Fragment fragment = null;
        switch (position) {
            case 1:
                fragment = new ByVisitFragment();
                break;
            case 2:
                fragment = new ByParameterFragment();
                break;

            default:
                break;
        }

        if (fragment != null) {
            try {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                    fragmentManager.beginTransaction()
                            .replace(R.id.trackParamFragContainer, fragment).commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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

/*
    public void SetBarElements(int confirm, int completed, int cancel, int totalcount){
        float tconfirm = 0;
        float tcompleted = 0;
        float tcancel = 0;
        try {
            tconfirm = (confirm * 100) / totalcount;
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            tcompleted = (completed * 100) / totalcount;
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            tcancel = (cancel * 100) / totalcount;
        }catch (Exception e){
            e.printStackTrace();
        }
        mBarChart.clearChart();
        mBarChart.invalidate();
        try {
            mBarChart.addBar(new BarModel("2016", tcompleted, 0xFF3F7F00));
            mBarChart.addBar(new BarModel("2017", tconfirm, 0xFF006CC6));
            mBarChart.addBar(new BarModel("2018", tcancel, 0xFFFF0101));
            mBarChart.startAnimation();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvVisitWiseTab:
                            tvVisitWiseTab.setBackground(getResources().getDrawable(R.drawable.square_half_round_tab_left_colored));
                            tvParameterWise.setBackground(getResources().getDrawable(R.drawable.square_half_round_tab_right_white));
                            tvVisitWiseTab.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorWhite, null));
                            tvParameterWise.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, null));
                            displayView(1);
                break;
            case R.id.tvParameterWiseTab:
                            tvVisitWiseTab.setBackground(getResources().getDrawable(R.drawable.square_half_round_tab_left_white));
                            tvParameterWise.setBackground(getResources().getDrawable(R.drawable.square_half_round_tab_right_colored));
                            tvVisitWiseTab.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, null));
                            tvParameterWise.setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorWhite, null));
                            displayView(2);
                break;
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
