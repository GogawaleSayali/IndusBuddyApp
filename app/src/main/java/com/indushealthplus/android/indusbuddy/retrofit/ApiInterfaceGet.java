package com.indushealthplus.android.indusbuddy.retrofit;


import com.indushealthplus.android.indusbuddy.models.AvailedPackagesModel;
import com.indushealthplus.android.indusbuddy.models.ContentPreviewMainModel;
import com.indushealthplus.android.indusbuddy.models.EventsMainModel;
import com.indushealthplus.android.indusbuddy.models.HistoryEventResponse;
import com.indushealthplus.android.indusbuddy.models.InstructionCallModel;
import com.indushealthplus.android.indusbuddy.models.MainMenuResponceModel;
import com.indushealthplus.android.indusbuddy.models.MemberDetrailsModel;
import com.indushealthplus.android.indusbuddy.models.ModelCentre;
import com.indushealthplus.android.indusbuddy.models.ModelHomePageNews;
import com.indushealthplus.android.indusbuddy.models.ModelIsWellnessEvent;
import com.indushealthplus.android.indusbuddy.models.ModelPaymentStatusResponse;
import com.indushealthplus.android.indusbuddy.models.ModelPostPoneReasonRes;
import com.indushealthplus.android.indusbuddy.models.Model_Categories;
import com.indushealthplus.android.indusbuddy.models.Model_MainSubCategories;
import com.indushealthplus.android.indusbuddy.models.Model_Response;
import com.indushealthplus.android.indusbuddy.models.Model_Response_Appointment_Details;
import com.indushealthplus.android.indusbuddy.models.Model_Response_DeliveryKit;
import com.indushealthplus.android.indusbuddy.models.Model_Response_DoctorComment;
import com.indushealthplus.android.indusbuddy.models.ModelResponseQuestionary;
import com.indushealthplus.android.indusbuddy.models.Model_Response_ReminderList;
import com.indushealthplus.android.indusbuddy.models.Model_Response_RenewalStatus;
import com.indushealthplus.android.indusbuddy.models.Model_Response_VisitList_Parameter;
import com.indushealthplus.android.indusbuddy.models.ModelResGetAllReport;

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

      @GET
      public Call<InstructionCallModel>  getPckwiseInstructionCallList (@Url String url);

//    @GET(ApiUrl.AvailedPackagesModel)
//    public Call<InstructionCallModel>  getPckwiseInstructionCallList (@Query("s")  String s);

//    @GET(ApiUrl.GET_POSTPONE_REASON)
//    public Call<InstructionCallModel>  getPckwiseInstructionCallList (@Query("s")  InstructionCallRequestModel s);

}
