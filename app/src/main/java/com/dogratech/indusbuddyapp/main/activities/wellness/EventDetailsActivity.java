package com.dogratech.indusbuddyapp.main.activities.wellness;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.baseactivities.BaseActivity;
import com.dogratech.indusbuddyapp.main.helper.Constatnts;
import com.dogratech.indusbuddyapp.main.managers.SharedPrefsManager;
import com.dogratech.indusbuddyapp.main.models.EventItemModel;
import com.dogratech.indusbuddyapp.main.models.SlotsModel;
import com.dogratech.indusbuddyapp.main.retrofit.ApiClient;
import com.dogratech.indusbuddyapp.main.retrofit.ApiInterfacePost;
import com.dogratech.indusbuddyapp.main.uitility.NetworkUtility;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetailsActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_pay ,tv_feedback,tv_confirm, tvEventName,tvCorporate,eventType, tvService,
            tvCenter, tvValidityFrom, tvValidityTo, tvAmount, tvPayment, tvStatus, tvDiscount, tvDays;
    private Spinner spinnerSlots;
    private String slotId = "";
    private ArrayList<String> slotsList = new ArrayList<>();
    private  Map<String,String> map = new HashMap<String, String>();
    private String eventId = "",corporateId = "",userId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Event Details");
        initialize();
        setListeners();
        setData();
    }

    /*****************************
     * Initialize All UI widgets.*
     *****************************/
    public void initialize() {
        tv_pay        = findViewById(R.id.tv_pay);
        tv_feedback   = findViewById(R.id.tv_feedback);
        tv_confirm    = findViewById(R.id.tv_confirm);
        tvEventName   = findViewById(R.id.tvEventName);
        tvCorporate   = findViewById(R.id.tvCorporate);
        eventType     = findViewById(R.id.eventType);
        tvService     = findViewById(R.id.tvService);
        tvCenter      = findViewById(R.id.tvCenter);
        tvValidityFrom= findViewById(R.id.tvValidityFrom);
        tvValidityTo  = findViewById(R.id.tvValidityTo);
        tvAmount      = findViewById(R.id.tvAmount);
        tvPayment     = findViewById(R.id.tvPayment);
        tvStatus      = findViewById(R.id.tvStatus);
        tvDays        = findViewById(R.id.tvDays);
        tvDiscount    = findViewById(R.id.tvDiscount);
        spinnerSlots  = findViewById(R.id.spinnerSlots);
    }

    /*******   ********************
     * Set Listeners to widgets*
     ***************************/
    private void setListeners() {
        tv_pay                .   setOnClickListener(this);
        tv_feedback           .   setOnClickListener(this);
        tv_confirm            .   setOnClickListener(this);
        spinnerSlots.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = slotsList.get(position);
                if (!str.equalsIgnoreCase("Select your slot")) {
                     slotId = map.get(str);
                 }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**********************************************************************************************
     * Show Event details, if event is pending and having some discount then user pay rest of the *
     * amount, Where as if discount is 100% then user have to pay nothing. just confirm the event.*
     **********************************************************************************************/
    private void setData() {
        userId = SharedPrefsManager.getSharedInstance(this).getData(getString(R.string.shars_userid));
        String jsonData = getIntent().getStringExtra("eventDetails");
        Gson gson = new Gson();
        EventItemModel eventItemModel = gson.fromJson(jsonData,EventItemModel.class);
        if (eventItemModel!=null){
            corporateId = eventItemModel.getCorporateId();
            eventId = eventItemModel.getEventId();
            if (eventItemModel.getStatus().equalsIgnoreCase("Pending")){
                if (eventItemModel.getPaymentType().equalsIgnoreCase("Partial")){
                    tv_pay.setVisibility(View.VISIBLE);
                    tv_confirm.setVisibility(View.GONE);
                    tv_feedback.setVisibility(View.GONE);
                }else{
                    tv_pay.setVisibility(View.GONE);
                    tv_confirm.setVisibility(View.VISIBLE);
                    tv_feedback.setVisibility(View.GONE);
                }
            }else {
                tv_feedback.setVisibility(View.GONE);
                tv_pay.setVisibility(View.GONE);
                tv_confirm.setVisibility(View.GONE);
                spinnerSlots.setEnabled(false);
            }

            DateFormat inFormat = new SimpleDateFormat( "MM/dd/yyyy hh:mm:ss aa");
            try {
                java.util.Date date = inFormat.parse(eventItemModel.getvTo());
                Date date1 = new Date(System.currentTimeMillis());
                if (date.before(date1)){
                    if(eventItemModel.getStatus().equalsIgnoreCase("Pending")) {
                        tv_feedback.setVisibility(View.GONE);
                        tv_pay.setVisibility(View.GONE);
                        tv_confirm.setVisibility(View.GONE);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tvEventName.setText(eventItemModel.getEventTitle());
            tvCorporate.setText(eventItemModel.getCorporate());
            eventType.setText(eventItemModel.getEventTypename());
            tvService.setText(eventItemModel.getServiceName());
            tvCenter.setText(eventItemModel.getCenter());
            tvValidityFrom.setText(eventItemModel.getFormatedFrom());
            tvValidityTo.setText(eventItemModel.getFormatedTo());
            tvAmount.setText(eventItemModel.getPrice()+"/-");
            tvStatus.setText(eventItemModel.getStatus());
            tvDays.setText(eventItemModel.getDays());
            String str = eventItemModel.getPercentage();
            if (str.equalsIgnoreCase("0")){
                str = "100 %";
            }else{
                str +=" %";
            }
            tvDiscount.setText(str);
            tvPayment.setText(eventItemModel.getPercentageAmount()+"/-");

            map.clear();
            slotsList.clear();
            slotsList.add("Select your slot");
            for (SlotsModel slotsModel:eventItemModel.getSlot()) {
                String slotStr = slotsModel.getStartTime()+"  -  "+slotsModel.getEndTime();
                map.put(slotStr,slotsModel.getSlotId());
                slotsList.add(slotStr);
            }
            if (slotsList.size()>0) {
                spinnerSlots.setAdapter(new ArrayAdapter<String>(EventDetailsActivity.this, R.layout.event_slot_row, slotsList));
                if (slotsList.size() == 2) {
                    spinnerSlots.setSelection(1);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_confirm :
                                if (!slotId.isEmpty()){
                                    makeConfirmEvent();
                                }else {
                                    Toast.makeText(this, R.string.slot_select_error, Toast.LENGTH_SHORT).show();
                                }
                 break;
            case R.id.tv_pay :
                                if (!slotId.isEmpty()){

                                }else {
                                    Toast.makeText(this, R.string.slot_select_error, Toast.LENGTH_SHORT).show();
                                }
                 break;
            case R.id.tv_feedback :
                startActivity(new Intent(EventDetailsActivity.this,EventFeedbackActivity.class));
                break;
            default:
                break;
        }
    }

    private void makeConfirmEvent() {
        ApiInterfacePost interfacePost = ApiClient.getClient(ApiClient.BASE_URL_TYEP_WELLNESS)
                .create(ApiInterfacePost.class);
        ConfirmEvenModel evenModel = new ConfirmEvenModel();
        evenModel.setCorporate_ID(corporateId);
        evenModel.setEHR_ID(userId);
        evenModel.setEvent_ID(eventId);
        evenModel.setSlot_ID(slotId);
        if (NetworkUtility.isNetworkAvailable(EventDetailsActivity.this)) {
            interfacePost.confirmEvent(evenModel).enqueue(new Callback<ConfirmResponse>() {
                @Override
                public void onResponse(Call<ConfirmResponse> call, Response<ConfirmResponse> response) {
                    if (response.body() != null) {
                        ConfirmResponse confirmResponse = response.body();
                        if (confirmResponse.statusCode == Constatnts.S_CODE_1) {
                            Toast.makeText(EventDetailsActivity.this,
                                    R.string.event_confirm_success, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            setResult(Activity.RESULT_OK, intent);
                            finish();
                        } else {
                            Toast.makeText(EventDetailsActivity.this,
                                    R.string.something_wrong_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ConfirmResponse> call, Throwable t) {

                }
            });
        }else {
            snackInternet();
        }
    }

    public void snackInternet(){
        Snackbar snackbar = Snackbar
                .make(tv_confirm, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
                .setAction("Retry", new View.OnClickListener() {
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

    public class ConfirmEvenModel{
        private String EHR_ID;
        private String Corporate_ID;
        private String Event_ID;
        private String Slot_ID;

        public String getEHR_ID() {
            return EHR_ID;
        }

        public void setEHR_ID(String EHR_ID) {
            this.EHR_ID = EHR_ID;
        }

        public String getCorporate_ID() {
            return Corporate_ID;
        }

        public void setCorporate_ID(String corporate_ID) {
            Corporate_ID = corporate_ID;
        }

        public String getEvent_ID() {
            return Event_ID;
        }

        public void setEvent_ID(String event_ID) {
            Event_ID = event_ID;
        }

        public String getSlot_ID() {
            return Slot_ID;
        }

        public void setSlot_ID(String slot_ID) {
            Slot_ID = slot_ID;
        }
    }

   public class ConfirmResponse{
        @SerializedName("statusCode")
        private int statusCode;
        @SerializedName("msg")
        private String msg;

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

}
