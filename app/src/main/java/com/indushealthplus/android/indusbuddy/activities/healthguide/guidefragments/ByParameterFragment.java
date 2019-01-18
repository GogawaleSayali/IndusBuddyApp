package com.indushealthplus.android.indusbuddy.activities.healthguide.guidefragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.healthguide.GraphByVisitActivity;
import com.indushealthplus.android.indusbuddy.adapters.ParameterAdapter;
import com.indushealthplus.android.indusbuddy.models.Model_Item_Visit_ParameterList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ByParameterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ByParameterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ByParameterFragment extends Fragment {
    private View rootView;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView lvByParameterList;
    private ParameterAdapter parameterEmojiAdapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private OnFragmentInteractionListener mListener;
    private ArrayList<String> vDate = new ArrayList<>();
    private ArrayList<String> pValue = new ArrayList<>();

    public ByParameterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ByParameterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ByParameterFragment newInstance(String param1, String param2) {
        ByParameterFragment fragment = new ByParameterFragment();
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
        rootView = inflater.inflate(R.layout.fragment_by_parameter, container, false);
        return  rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
        setListeners();
        initialiseClass();
    }

    private void initialiseClass() {
        vDate   . clear();
        pValue  . clear();
    }

    private void setListeners() {
        lvByParameterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    String pName = null;
                    for (int i = 0; i < TrackParameter.global_visitlist.size(); i++) {
                        Model_Item_Visit_ParameterList pList = TrackParameter.global_visitlist.get(i);
                        vDate.add(pList.getVisitDate());
                        pName = pList.getParameterList().get(position).getParameterName();
                        for (int j = 0; j < pList.getParameterList().size(); j++) {
                            if (pName.equalsIgnoreCase(pList.getParameterList().get(j).getParameterName())) {
                                pValue.add(pList.getParameterList().get(j).getParameterValue());
                            }
                        }
                    }

                    if (vDate.size() > 0 && pValue.size() > 0) {
                        String vsDate = gson.toJson(vDate);
                        String vsValue = gson.toJson(pValue);
                        Intent intent = new Intent(getActivity(), GraphByVisitActivity.class);
                        intent.putExtra(getString(R.string.visit_date), vsDate);
                        intent.putExtra(getString(R.string.visit_value), vsValue);
                        intent.putExtra(getString(R.string.visit_p_name), pName);
                        startActivity(intent);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void mToast(String msg) {
        Toast.makeText(getActivity(), ""+msg, Toast.LENGTH_SHORT).show();
    }

    private void initialize() {
        lvByParameterList = rootView .findViewById(R.id.lvByParameterList);
        if (parameterEmojiAdapter==null) {
            parameterEmojiAdapter = new ParameterAdapter(getActivity(), TrackParameter.global_paramlist);
            lvByParameterList.setAdapter(parameterEmojiAdapter);
        }else {
            parameterEmojiAdapter.updateData(TrackParameter.global_paramlist);
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
