package com.dogratech.indusbuddyapp.main.activities.wellness.wellnessfragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.adapters.MyEventAdapter;
import com.dogratech.indusbuddyapp.main.models.EventItemModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyEventFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyEventFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View rootview ;

    private RecyclerView recyclerView;
    private MyEventAdapter adapter;
    private TextView tvNoEventsMsg;
    private List<EventItemModel> events;
    private RecyclerView.LayoutManager mLayoutManager ;
    private OnFragmentInteractionListener mListener;

    public MyEventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyEventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyEventFragment newInstance(String param1, String param2) {
        MyEventFragment fragment = new MyEventFragment();
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
        rootview=  inflater.inflate(R.layout.fragment_my_event, container, false);
        return rootview ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialise();
        prepareData();
    }

    private void initialise() {
        recyclerView          = rootview .findViewById(R.id.recycler_view);
        tvNoEventsMsg         = rootview .findViewById(R.id.tvNoEventsMsg);
        events                = new ArrayList<>();
        mLayoutManager        = new LinearLayoutManager(getActivity());
        recyclerView          . setLayoutManager(mLayoutManager);
        // recyclerView          . addItemDecoration(new GridSpacingItemDecoration(2,
        // DeviceUtility.convertDpToPx(getActivity(),2), true));
        recyclerView          . setItemAnimator(new DefaultItemAnimator());
        //recyclerView          . setAdapter(adapter);
    }

    /**
     * Adding few albums for testing
     */
    private void prepareData() {
        if (mParam1.equalsIgnoreCase("")){
            //show error
        }else{
            Gson gson = new Gson();
            Type type = new TypeToken<List<EventItemModel>>() {}.getType();
            events = gson.fromJson(mParam1, type);
            if (events.size()>0) {
                tvNoEventsMsg.setVisibility(View.GONE);
                adapter = new MyEventAdapter(getActivity(), events);
                recyclerView.setAdapter(adapter);
            }else{
                tvNoEventsMsg.setVisibility(View.VISIBLE);
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void updateDate(ArrayList<EventItemModel> events) {
        this.events = events;
        if (events.size()>0) {
            tvNoEventsMsg.setVisibility(View.GONE);
            if (adapter == null) {
                adapter = new MyEventAdapter(getActivity(), events);
                recyclerView.setAdapter(adapter);
            }else {
                adapter.updateData(events);
            }
        }else{
            tvNoEventsMsg.setVisibility(View.VISIBLE);
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
