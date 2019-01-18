package com.indushealthplus.android.indusbuddy.retrofit;


import com.indushealthplus.android.indusbuddy.activities.wellness.EventDetailsActivity;
import com.indushealthplus.android.indusbuddy.models.HRAAnswerMainModel;
import com.indushealthplus.android.indusbuddy.models.Model_Response;
import com.indushealthplus.android.indusbuddy.models.Model_Response_AppReschedule;
import com.indushealthplus.android.indusbuddy.models.Model_Response_Report;
import com.indushealthplus.android.indusbuddy.models.ModelUserDetails;
import com.indushealthplus.android.indusbuddy.models.Model_Response_setReminderDetails;
import com.indushealthplus.android.indusbuddy.models.Model_item_setReminderDetails;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by akshaya on 25/5/17.
 */

public interface ApiInterfacePost {
    @POST(ApiUrl.verifyDetails)
    Call<Model_Response> verifyDetails(@Body ModelUserDetails body);

   @POST(ApiUrl.saveReminder)
    Call<Model_Response_setReminderDetails> saveReminder(@Body Model_item_setReminderDetails body);

    @POST(ApiUrl.postAppointment)
    Call<Model_Response_AppReschedule> RESCHEDULE_CALL  (@Field("AppNo") String AppNo,
                                                         @Field("AppYear") String AppYear ,
                                                         @Field("Center") String Center ,
                                                         @Field("AppDate") String AppDate ,
                                                         @Field("ActionCode") String ActionCode ,
                                                         @Field("ActionDetCode") String ActionDetCode ,
                                                         @Field("Explanation") String remark ,
                                                         @Field("Reason") String reason,
                                                         @Field("Appointment") String appoinmrnt);
    @Multipart
    @POST(ApiUrl.uploadSelfReport)
    Call<Model_Response_Report> uploadSelfReport (@Part("ehrId") RequestBody ehrId,
                                                          @Part("comment") RequestBody  comment,
                                                          @Part MultipartBody.Part file);
    @Multipart
    @POST(ApiUrl.uploadProfilePicture)
    Call<Model_Response_Report> uploadProfilePic (@Part("ehrId") RequestBody ehrId,@Part MultipartBody.Part file);

    @POST(ApiUrl.saveHRADetails)
    @Headers({"Accept: application/json",
            "Content-Type: application/json"})
    Call<Model_Response_Report> saveHRAAnswers (@Body ArrayList<HRAAnswerMainModel> data);

    @POST(ApiUrl.event)
    @Headers({"Accept: application/json",
            "Content-Type: application/json"})
    Call<EventDetailsActivity.ConfirmResponse> confirmEvent(@Body EventDetailsActivity.ConfirmEvenModel data);


}
