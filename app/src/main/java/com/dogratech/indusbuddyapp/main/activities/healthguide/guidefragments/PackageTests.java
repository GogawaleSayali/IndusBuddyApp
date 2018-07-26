package com.dogratech.indusbuddyapp.main.activities.healthguide.guidefragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.adapters.PackagesAvailableAdapter;
import com.dogratech.indusbuddyapp.main.listeners.RecyclerItemClickListener;
import com.dogratech.indusbuddyapp.main.models.Model_Test;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PackageTests.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PackageTests#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PackageTests extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView ;
    private RecyclerView.LayoutManager mLayoutManager ;
    private TextView tvDataNotFound ;
    private PackagesAvailableAdapter adapter ;
    private ArrayList<Model_Test> testList ;
    private OnFragmentInteractionListener mListener;
    private View PackageTests ;

    public PackageTests() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PackageTests.
     */
    // TODO: Rename and change types and number of parameters
    public static PackageTests newInstance(String param1, String param2) {
        PackageTests fragment = new PackageTests();
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
        PackageTests =  inflater.inflate(R.layout.fragment_package_tests, container, false);
        initialise();
        setRecyclerDecorator();
        setListeners();
        prepareTestList();
        return PackageTests ;
    }

    private void initialise() {
        recyclerView   = PackageTests.findViewById(R.id.recyclerView);
        tvDataNotFound = PackageTests.findViewById(R.id.tvDataNotFound);
    }

    private void setListeners() {
        recyclerView           . addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        }));
    }

    private void prepareTestList() {
        String[]data = mParam1.split(",");
        for (int i = 0 ;i<data.length;i++){
            if (!data[i].isEmpty()) {
                Model_Test a = new Model_Test(data[i]);
                testList.add(a);
            }
        }
        if (testList.size()>0){
            tvDataNotFound.setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();
    }


    public void setRecyclerDecorator(){
        testList               = new ArrayList<>();
        adapter                = new PackagesAvailableAdapter(getActivity(),testList, new PackagesAvailableAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Model_Test item) {
              //  startActivity(new Intent(getActivity(), UnavailedPackageDetailsActivity.class));
            }
        });
        mLayoutManager         = new LinearLayoutManager(getActivity());
        recyclerView           . setLayoutManager(mLayoutManager);

        recyclerView           . setAdapter(adapter);
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
