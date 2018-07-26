package com.dogratech.indusbuddyapp.main.activities.healthcheckup;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.baseactivities.BaseActivity;
import com.dogratech.indusbuddyapp.main.models.Model_Item_Appointment;

public class ApptDetailsActivity extends BaseActivity implements View.OnClickListener{
    private LinearLayout loutCheckCentre ;
    private TextView txt_testdetails;
    private TextView tv_app_date ,tv_app_no ,tv_app_package ,tv_app_beneficiaryName,
            tv_app_beneRelation, tv_app_status ,tv_app_centre ;
    private String cityCode;
    private String financial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appt_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar,"Appointment Details");
        initialize();
        setListeners();
    }

    private void initialize() {
        loutCheckCentre       = findViewById(R.id.loutCheckCentre);
        txt_testdetails       = findViewById(R.id.txt_testdetails);
        tv_app_date           = findViewById(R.id.tv_app_date);
        tv_app_no             = findViewById(R.id.tv_app_no);
        tv_app_package        = findViewById(R.id.tv_app_package);
        tv_app_beneficiaryName= findViewById(R.id.tv_app_beneficiaryName);
        tv_app_beneRelation   = findViewById(R.id.tv_app_beneficiaryRelation);
        tv_app_status         = findViewById(R.id.tv_app_status);
        tv_app_centre         = findViewById(R.id.tv_app_centre);
        Bundle bundle   = getIntent().getExtras();
        String date     = bundle.getString("date");
        String number   = bundle.getString("number");
        String schema   = bundle.getString("schema");
        String benName  = bundle.getString("benificiaryName");
        String benRelation = bundle.getString("benbificiaryRelation");
        String status   = bundle.getString("status");
        String center   = bundle.getString("centre");
        financial   = bundle.getString("financial");
        cityCode = bundle.getString("cityCode");
        Model_Item_Appointment appointment = new Model_Item_Appointment();
        appointment     .setAppointmentDate(date);
        appointment     .setAppointmentNo(number);
        appointment     .setSchemeName(schema);
        appointment     .setBeneficiaryName(benName);
        appointment     .setBeneficiaryRelation(benRelation);
        appointment     .setAppointmentStatus(status);
        appointment     .setCCName(center);
        appointment     .setCityCode(cityCode);
        setAppointmentData(appointment);
        if(status.equalsIgnoreCase("Checkup Done")){
            txt_testdetails.setVisibility(View.GONE);
        }
    }
    protected void setListeners() {
        loutCheckCentre      . setOnClickListener(this);
        txt_testdetails      . setOnClickListener(this);
    }

    /*
     * Internet checking code
     * */
    public void snackInternet(){
        Snackbar snackbar = Snackbar
                .make(loutCheckCentre, getString(R.string.netConnection), Snackbar.LENGTH_LONG)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });

        snackbar.setActionTextColor(Color.RED);
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loutCheckCentre :
                Intent intent =new Intent(ApptDetailsActivity.this,CentreDetailsActivity.class);
                intent.putExtra("from","current");
                startActivity(intent);
                break;
            case R.id.txt_testdetails :
                Intent intentGetAppi =new Intent(ApptDetailsActivity.this,GetAppointmentActivity.class);
                intentGetAppi.putExtra("package",tv_app_package.getText());
                intentGetAppi.putExtra("date",tv_app_date.getText());
                intentGetAppi.putExtra("number",tv_app_no.getText());
                intentGetAppi.putExtra("beneficiary",tv_app_beneficiaryName.getText());
                intentGetAppi.putExtra("relation", tv_app_beneRelation.getText());
                intentGetAppi.putExtra("centre",tv_app_centre.getText());
                intentGetAppi.putExtra("cityId",cityCode);
                intentGetAppi.putExtra("financial",financial);
                startActivity(intentGetAppi);
                break;

            default:
                break;
        }
    }

    private void setAppointmentData(Model_Item_Appointment appointment) {
        try{
            if(appointment.getAppointmentDate()!=null){
                tv_app_date . setText(appointment.getAppointmentDate());
            }else{
                tv_app_date.setText("N/A");
            }

            if(appointment.getAppointmentNo()!=null){
                String apptNo = appointment.getAppointmentNo();
                tv_app_no . setText(apptNo);
            }else{
                tv_app_no.setText("N/A");
            }

            if(appointment.getSchemeName()!=null){
                String scheme = appointment.getSchemeName();
                tv_app_package . setText(scheme);
            }else{
                tv_app_package.setText("N/A");
            }

            if(appointment.getBeneficiaryName()!=null){
                String beneficiaryName = appointment.getBeneficiaryName().replace("-","").trim();
                tv_app_beneficiaryName . setText(beneficiaryName);
            }else{
                tv_app_beneficiaryName.setText("N/A");
            }

            if(appointment.getBeneficiaryRelation()!=null){
                String benRelation = appointment.getBeneficiaryRelation();
                tv_app_beneRelation. setText(benRelation);
            }else{
                tv_app_beneRelation.setText("N/A");
            }

            if(appointment.getAppointmentStatus()!=null){
                String apptStatus = appointment.getAppointmentStatus();
                tv_app_status . setText(apptStatus);
            }else{
                tv_app_status.setText("N/A");
            }

            if(appointment.getCCName()!=null){
                String apptCentre = appointment.getCCName();
                tv_app_centre . setText(apptCentre);
            }else{
                tv_app_centre.setText("N/A");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
