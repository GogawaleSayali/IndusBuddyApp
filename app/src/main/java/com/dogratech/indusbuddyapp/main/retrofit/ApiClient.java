package com.dogratech.indusbuddyapp.main.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by akshaya on 25/5/17.
 */

public class ApiClient {
    public static final String BASE_URL_TYEP_ICONNECT = "iconnect";
    public static final String BASE_URL_TYEP_INDUS = "indus";
    public static final String BASE_URL_TYEP_MOBILE = "mobile";
    public static final String BASE_URL_TYEP_WELLNESS = "wellness";
    private static Retrofit retrofit = null;
    private static String baseUrl = ApiUrl.Base_URL_INDUS;
    public static OkHttpClient getClientOkHttp() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
        return client;

    }

    public static Gson gson = new GsonBuilder()
                                  .setLenient()
                                  .create();

    public static Retrofit getClient(String urlType) {
        try {
            if (urlType.equalsIgnoreCase(BASE_URL_TYEP_MOBILE)){
                baseUrl = ApiUrl.Base_URL_MOBILE;
            }else if (urlType.equalsIgnoreCase(BASE_URL_TYEP_INDUS)){
                baseUrl = ApiUrl.Base_URL_INDUS;
            }else if (urlType.equalsIgnoreCase(BASE_URL_TYEP_WELLNESS)){
                baseUrl = ApiUrl.Base_URL_WELLNESS;
            }else if (urlType.equalsIgnoreCase(BASE_URL_TYEP_ICONNECT)){
                baseUrl = ApiUrl.Base_URL_ICONNECT;
            }

           // if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .client(getClientOkHttp())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
           // }
        }catch (Exception e){
            e.printStackTrace();
        }
        return retrofit;
    }
}
