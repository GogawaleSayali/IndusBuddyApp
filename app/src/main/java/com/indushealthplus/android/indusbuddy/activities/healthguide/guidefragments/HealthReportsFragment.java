package com.indushealthplus.android.indusbuddy.activities.healthguide.guidefragments;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.indushealthplus.android.indusbuddy.R;
import com.indushealthplus.android.indusbuddy.adapters.ReportAdapter;
import com.indushealthplus.android.indusbuddy.helper.Constatnts;
import com.indushealthplus.android.indusbuddy.helper.ErrorCodesAndMessagesManager;
import com.indushealthplus.android.indusbuddy.managers.SharedPrefsManager;
import com.indushealthplus.android.indusbuddy.models.Model_Item_Report;
import com.indushealthplus.android.indusbuddy.models.ModelSelfUploadReports;
import com.indushealthplus.android.indusbuddy.models.ModelResGetAllReport;
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
 * {@link HealthReportsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HealthReportsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HealthReportsFragment extends Fragment implements View.OnTouchListener, AdapterView.OnItemClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final String LOG = this.getClass().getName();
    private View StoreRecords;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView lvReport ;
    private float yScroll1 = 0, yScroll2 = 0,previousY = 0;
    private TextView tvNoReportsMsg;
    private OnFragmentInteractionListener mListener;
    private ArrayList<Model_Item_Report> model_reports_list ;
    private String filePath ;
    private ReportAdapter reportAdapter ;
    private String TAG = this.getClass().getName();
    private ApiInterfaceGet interface_get ;
    private SharedPrefsManager prefsManager;
    private String userId ;
    private FrameLayout frame;
    public HealthReportsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HealthReportsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HealthReportsFragment newInstance(String param1, String param2) {
        HealthReportsFragment fragment = new HealthReportsFragment();
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
        StoreRecords =  inflater.inflate(R.layout.fragment_store_records, container, false);
        initialise();
        initialiseClass();
        //setData();
        setListeners();
        return StoreRecords ;
    }

    private void initialiseClass() {
        interface_get   = ApiClient.getClient(ApiClient.BASE_URL_TYEP_INDUS).create(ApiInterfaceGet.class);
        prefsManager    = SharedPrefsManager.getSharedInstance(getActivity());
        userId          = prefsManager.getData(getActivity().getString(R.string.shars_userid));
    }

    private void setListeners() {
        lvReport        . setOnTouchListener(this);
        lvReport        . setOnItemClickListener(this);
    }

    private void setData() {
        if (NetworkUtility.isNetworkAvailable(getActivity())){
        ModelSelfUploadReports model_report ;
        model_reports_list = new ArrayList<>();
        String url = ApiUrl.Base_URL_INDUS + ApiUrl.getSelfUploadReportByEHRId + userId;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_loader, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        try {
            alertDialog.show();
        }catch (Exception e){
        }
            interface_get.getAllReport(url).enqueue(new Callback<ModelResGetAllReport>() {
                @Override
                public void onResponse(Call<ModelResGetAllReport> call, Response<ModelResGetAllReport> response) {
                    try{
                        if (alertDialog.isShowing()){
                            alertDialog.hide();
                        }
                        if(response.isSuccessful()){
                            if(response.body()!=null){
                                ModelResGetAllReport allReport = response.body();
                                if(allReport.getStatus_code()== Constatnts.S_CODE_0){
                                    ModelSelfUploadReports selfUploadReports = allReport.getUploadReports();
                                    model_reports_list = selfUploadReports.getSelfUploadReports();
                                    if (model_reports_list.size()>0) {
                                        tvNoReportsMsg.setVisibility(View.GONE);
                                        filePath = selfUploadReports.getFilePath();
                                        reportAdapter = new ReportAdapter(getActivity(),
                                                HealthReportsFragment.this,
                                                model_reports_list, filePath);
                                        lvReport.setAdapter(reportAdapter);
                                        reportAdapter.notifyDataSetChanged();
                                    }else{
                                        tvNoReportsMsg.setVisibility(View.VISIBLE);
                                    }
                                }else{
                                    tvNoReportsMsg.setVisibility(View.VISIBLE);
                                    int error_code= allReport.getError_code();
                                    ErrorCodesAndMessagesManager errCodeMsg = ErrorCodesAndMessagesManager.getInstance();
                                    showToast(errCodeMsg.getErrorMessage(error_code));
                                }
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ModelResGetAllReport> call, Throwable t) {
                    Log.d(TAG,t.toString());
                    if (alertDialog.isShowing()){
                        alertDialog.hide();
                    }
                    tvNoReportsMsg.setVisibility(View.VISIBLE);
                }
            });
        }else{
            snackInternet();
        }
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
    }

    private void initialise() {
        lvReport       = StoreRecords.findViewById(R.id.lvReport);
        tvNoReportsMsg = StoreRecords.findViewById(R.id.tvNoReportsMsg);
        frame          = StoreRecords.findViewById(R.id.frame);
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
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) //up
        {
            yScroll1 =  event.getY();
        }
        if (event.getAction() == MotionEvent.ACTION_UP)   //down
        {
            yScroll2 =  event.getY();
            if (yScroll2>yScroll1){
                Log.v("ScrollListview  "," up ");
            }else{
                Log.v("ScrollListview  "," Down");
            }
        }
        return false;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Model_Item_Report item_report = (Model_Item_Report) parent.getAdapter().getItem(position);
        String imageName = item_report.getFilePath();
        String image = ApiUrl.Base_URL_INDUS+"viewReport/report/"+imageName;
        openFile(image);

    }

    private void openFile(String url) {

        try {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            if (url.toString().contains(".doc") || url.toString().contains(".docx")) {
                // Word document
                intent.setDataAndType(uri, "application/msword");
            } else if (url.toString().contains(".pdf")) {
                // PDF file
                intent.setDataAndType(uri, "application/pdf");
            } else if (url.toString().contains(".ppt") || url.toString().contains(".pptx")) {
                // Powerpoint file
                intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
            } else if (url.toString().contains(".xls") || url.toString().contains(".xlsx")) {
                // Excel file
                intent.setDataAndType(uri, "application/vnd.ms-excel");
            } else if (url.toString().contains(".zip") || url.toString().contains(".rar")) {
                // WAV audio file
                intent.setDataAndType(uri, "application/x-wav");
            } else if (url.toString().contains(".rtf")) {
                // RTF file
                intent.setDataAndType(uri, "application/rtf");
            } else if (url.toString().contains(".wav") || url.toString().contains(".mp3")) {
                // WAV audio file
                intent.setDataAndType(uri, "audio/x-wav");
            } else if (url.toString().contains(".gif")) {
                // GIF file
                intent.setDataAndType(uri, "image/gif");
            } else if (url.toString().contains(".jpg") || url.toString().contains(".jpeg") || url.toString().contains(".png")) {
                // JPG file
                intent.setDataAndType(uri, "image/jpeg");
            } else if (url.toString().contains(".txt")) {
                // Text file
                intent.setDataAndType(uri, "text/plain");
            } else if (url.toString().contains(".3gp") || url.toString().contains(".mpg") ||
                    url.toString().contains(".mpeg") || url.toString().contains(".mpe") || url.toString().contains(".mp4") || url.toString().contains(".avi")) {
                // Video files
                intent.setDataAndType(uri, "video/*");
            } else {
                intent.setDataAndType(uri, "*/*");
            }

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getActivity(),"No application found which can open the file", Toast.LENGTH_SHORT).show();
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

    /*
  * Internet checking code
  * */
    public void snackInternet(){
        Snackbar snackbar = Snackbar
                .make(frame, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
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
}
