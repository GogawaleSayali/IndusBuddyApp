package com.indushealthplus.android.indusbuddy.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.activities.TipActivity;
import com.indushealthplus.android.indusbuddy.adapters.FilterTipsAdapter;
import com.indushealthplus.android.indusbuddy.adapters.HealthTipsAdapter;
import com.indushealthplus.android.indusbuddy.listeners.RecyclerItemClickListener;
import com.indushealthplus.android.indusbuddy.models.ContentPreviewMainModel;
import com.indushealthplus.android.indusbuddy.models.ContentsPreview;
import com.indushealthplus.android.indusbuddy.models.DataContent;
import com.indushealthplus.android.indusbuddy.models.FileObjectModel;
import com.indushealthplus.android.indusbuddy.models.Model_filter_tips;
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
 * {@link HealthTips.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HealthTips#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HealthTips extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private HealthTipsAdapter adapter;
    private FilterTipsAdapter filterTipsAdapter ;
    private ArrayList<ContentsPreview> contentsPreview;
    private List<Model_filter_tips> modelFilterTips;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView rv_healthTips ;
    private View HealthTips ;
    private FloatingActionButton btn_filter ;
    private OnFragmentInteractionListener mListener;


    public HealthTips() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HealthTips.
     */
    // TODO: Rename and change types and number of parameters
    public static HealthTips newInstance(String param1, String param2) {
        HealthTips fragment = new HealthTips();
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
        HealthTips = inflater.inflate(R.layout.fragment_health_tips, container, false);
        initialise();
        setCommentData();
        loadComments();
       // getchildArrayList("subcategorylist");
        setListener();
        return HealthTips ;
    }
/*
    public ArrayList<String> getchildArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }*/

    private void setListener() {
        btn_filter      . setOnClickListener(this);
        rv_healthTips   . addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String desc = contentsPreview.get(position).getContentDescription();
                ArrayList<String> files = new ArrayList<>();
                ArrayList<FileObjectModel> preview = contentsPreview.get(position).getContentFiles();
                for (FileObjectModel fileObjectModel :preview) {
                    if (fileObjectModel.getFileType().equalsIgnoreCase("image"))
                    files.add(fileObjectModel.getFileName());
                }
                String fileName = files.toString();
                //if (desc!=null) {
                   // if (!desc.equalsIgnoreCase("")) {
                        Intent intent = new Intent(getActivity(), TipActivity.class);
                        intent.putExtra("desc", desc);
                        intent.putExtra("files", fileName);
                        startActivity(intent);
                   /* }else {
                        Toast.makeText(getActivity(), R.string.errorNoDesc, Toast.LENGTH_SHORT).show();
                    }*/
               /* }else{
                    Toast.makeText(getActivity(), R.string.errorNoDesc, Toast.LENGTH_SHORT).show();
                }*/
            }
        }));
    }

    private void loadComments() {
        if (NetworkUtility.isNetworkAvailable(getActivity())){
            ApiInterfaceGet interfaceGet = ApiClient.getClient(ApiClient.BASE_URL_TYEP_INDUS)
                    .create(ApiInterfaceGet.class);
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custom_loader, null);
            dialogBuilder.setView(dialogView);
            final AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.setCancelable(false);
            alertDialog.show();
            String url = ApiUrl.Base_URL_INDUS + ApiUrl.contentPreview;
            interfaceGet.getContentPreview(url).enqueue(new Callback<ContentPreviewMainModel>() {
                @Override
                public void onResponse(Call<ContentPreviewMainModel> call, Response<ContentPreviewMainModel> response) {
                    if (alertDialog.isShowing()){
                        alertDialog.hide();
                    }
                    if (response.isSuccessful()){
                        if (response.body()!=null){
                            ContentPreviewMainModel previewMainModel = response.body();
                            DataContent dataContent = previewMainModel.getData();
                            contentsPreview = dataContent.getContents();
                            if (contentsPreview!=null){
                                if (contentsPreview.size()>0){
                                    if (adapter == null) {
                                        adapter = new HealthTipsAdapter(getActivity(), contentsPreview);
                                        rv_healthTips.setAdapter(adapter);
                                    }else{
                                        adapter.updateList(contentsPreview);
                                    }
                                }
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<ContentPreviewMainModel> call, Throwable t) {
                    if (alertDialog.isShowing()){
                        alertDialog.hide();
                    }
                }
            });

        }else {
            Toast.makeText(getActivity(), "Network not available", Toast.LENGTH_SHORT).show();
         }

/*        for(int i = 0 ;i <15 ;i++){
            Model_health_tips health_tips = new Model_health_tips(getString(R.string.tips)+" "+i);
            tipsList.add(health_tips);
        }
      */
    }

    private void setCommentData() {
        RecyclerView      . LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rv_healthTips     . setLayoutManager(mLayoutManager);
        rv_healthTips     . setItemAnimator(new DefaultItemAnimator());
    }

    private void initialise() {
        rv_healthTips     = HealthTips.findViewById(R.id.rv_healthTips);
        btn_filter        = (FloatingActionButton)HealthTips.findViewById(R.id.btn_filter);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_filter :
                 showDialog();
                 break;
            default:
                 break;
        }
    }

    private void showDialog(){
        final Dialog dialog = new Dialog(getActivity(),R.style.DialogTheme);
        dialog.setContentView(R.layout.tips_filter_dialog);
        dialog.setCancelable(true);
        dialog.setTitle("ListView");
        ListView lv       = (ListView) dialog.findViewById(R.id.lv);
        Button btn_submit = (Button) dialog.findViewById(R.id.btn_submit);
        btn_submit        . setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        setData(lv);
        dialog.show();
    }

    private void setData(ListView lv) {
        Model_filter_tips filter_tips ;
        modelFilterTips = new ArrayList<>();

        filter_tips  = new Model_filter_tips("Diet",1,true);
        modelFilterTips.add(filter_tips);

        filter_tips  = new Model_filter_tips("Weight Loss",2,true);
        modelFilterTips.add(filter_tips);

        filter_tips  = new Model_filter_tips("Fitness Workout",3,true);
        modelFilterTips.add(filter_tips);

        filterTipsAdapter = new FilterTipsAdapter(getActivity(),modelFilterTips);
        lv                . setAdapter(filterTipsAdapter);
        filterTipsAdapter . notifyDataSetChanged();
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
