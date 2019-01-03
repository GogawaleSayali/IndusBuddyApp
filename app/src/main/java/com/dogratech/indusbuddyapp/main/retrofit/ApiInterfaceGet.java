package com.dogratech.indusbuddyapp.main.retrofit;


import com.dogratech.indusbuddyapp.main.models.AvailedPackagesModel;
import com.dogratech.indusbuddyapp.main.models.ContentPreviewMainModel;
import com.dogratech.indusbuddyapp.main.models.EventsMainModel;
import com.dogratech.indusbuddyapp.main.models.HistoryEventResponse;
import com.dogratech.indusbuddyapp.main.models.MainMenuResponceModel;
import com.dogratech.indusbuddyapp.main.models.MemberDetrailsModel;
import com.dogratech.indusbuddyapp.main.models.ModelCentre;
import com.dogratech.indusbuddyapp.main.models.ModelHomePageNews;
import com.dogratech.indusbuddyapp.main.models.ModelIsWellnessEvent;
import com.dogratech.indusbuddyapp.main.models.ModelPaymentStatusResponse;
import com.dogratech.indusbuddyapp.main.models.ModelPostPoneReasonRes;
import com.dogratech.indusbuddyapp.main.models.Model_Categories;
import com.dogratech.indusbuddyapp.main.models.Model_MainSubCategories;
import com.dogratech.indusbuddyapp.main.models.Model_item_setReminderDetails;
import com.dogratech.indusbuddyapp.main.models.Model_Response;
import com.dogratech.indusbuddyapp.main.models.Model_Response_Appointment_Details;
import com.dogratech.indusbuddyapp.main.models.Model_Response_DeliveryKit;
import com.dogratech.indusbuddyapp.main.models.Model_Response_DoctorComment;
import com.dogratech.indusbuddyapp.main.models.ModelResponseQuestionary;
import com.dogratech.indusbuddyapp.main.models.Model_Response_ReminderList;
import com.dogratech.indusbuddyapp.main.models.Model_Response_RenewalStatus;
import com.dogratech.indusbuddyapp.main.models.Model_Response_VisitList_Parameter;
import com.dogratech.indusbuddyapp.main.models.ModelResGetAllReport;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by akshaya on 25/5/17.
 */

public interface ApiInterfaceGet {

    @GET
    public Call<Model_Response> getClientByEHRId (@Url String url);

    @GET
    public Call<Model_Response> resendOTP (@Url String url);

    @GET
    public Call<Model_Response> verifyOTP (@Url String url);

    @GET
    public Call<Model_Response_DeliveryKit> deliveryKitStatus (@Url String url);

    @GET
    public Call<Model_Response_RenewalStatus>  renewalStatus (@Url String url);

    @GET
    public Call<ModelPaymentStatusResponse>  paymentStatus (@Url String url);

    @GET
    public Call<Model_Response_Appointment_Details>  getAppointment (@Url String url);

    @GET
    public Call<Model_Response_VisitList_Parameter>  getVisitparamterList (@Url String url);

    @GET
    public Call<ModelResGetAllReport>  getAllReport (@Url String url);

    @GET
    public Call<Model_Response_ReminderList>  getAllReminders (@Url String url);

    @GET
    public Call<Model_Categories> getArticleCategories (@Url String url);

   @GET
    public Call<Model_MainSubCategories> getSubCategories (@Url String url);

    @GET
    public Call<ModelResponseQuestionary>  getHRAByEHRId (@Url String url);

    @GET
    public Call<Model_Response_DoctorComment>  getDoctorComments (@Url String url);

    @GET()
    public Call<MainMenuResponceModel>  getRoleMenus (@Url String url);

    @GET
    public Call<AvailedPackagesModel>  getAvailedPackes (@Url String url);


    @GET
    public Call<MemberDetrailsModel>  getMemberDetails (@Url String url);


    @GET
    public Call<EventsMainModel>  getEvents(@Url String url);

    @GET
    public Call<HistoryEventResponse>  getHistory(@Url String url);

    @GET
    public Call<ContentPreviewMainModel>  getContentPreview(@Url String url);

    @GET(ApiUrl.getCentreList)
    Call<ArrayList<ModelCentre>> getCenters(@Query("city") String city);

    @GET(ApiUrl.getHomePageNews)
    Call<ArrayList<ModelHomePageNews>> getHomePageNews();

    @GET(ApiUrl.getArchivesSectionNews+ApiUrl.DOMAIN_IHP)
    Call<ArrayList<ModelHomePageNews>> getHomePageNews(@Query("year") String year);

    @GET
    Call<ModelIsWellnessEvent> isEventAvail(@Url String url);

    @GET(ApiUrl.GET_POSTPONE_REASON)
    Call<ModelPostPoneReasonRes> getPostPoneReason ();





}
