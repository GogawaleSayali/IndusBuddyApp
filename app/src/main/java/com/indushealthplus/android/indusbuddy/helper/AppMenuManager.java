package com.indushealthplus.android.indusbuddy.helper;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.indushealthplus.android.indusbuddy.models.MainMenuModel;
import com.indushealthplus.android.indusbuddy.models.MainMenuResponceModel;
import com.indushealthplus.android.indusbuddy.retrofit.ApiClient;
import com.indushealthplus.android.indusbuddy.retrofit.ApiInterfaceGet;
import com.indushealthplus.android.indusbuddy.retrofit.ApiUrl;
import com.indushealthplus.android.indusbuddy.uitility.DeviceUtility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*******************************
 * Created by amolr on 20/4/18.*
 *******************************/

public class AppMenuManager extends GoogleClientHelper{
    private static final String CHECKUP  = "Health Checkup";
    private static final String GUIDE    = "Health Guide";
    private static final String WELLNESS = "My Wellness";
    private static final String FITNESS  = "Fitness Tracker";

    /*********************************************************
     * Hit Get menu webservice according to role of the user.*
     * Roles can be like : O,P,M,C...etc.                    *
     * @param role : Role to pass through the webservice.    *
     *********************************************************/
    protected void getMenus(String role) {
       String url = ApiUrl.Base_URL_WELLNESS+ApiUrl.getMenu+role;
        ApiInterfaceGet interface_get = ApiClient.getClient(ApiClient.BASE_URL_TYEP_WELLNESS).create(ApiInterfaceGet.class);
        interface_get.getRoleMenus(url).enqueue(new Callback<MainMenuResponceModel>() {
            @Override
            public void onResponse(Call<MainMenuResponceModel> call, Response<MainMenuResponceModel> response) {
                rlProgressMain.setVisibility(View.GONE);
                try {
                    if (response.body() != null) {
                        MainMenuResponceModel responceModel = response.body();
                        if (responceModel != null) {
                            if (responceModel.getStatusCode() == 1) {
                                ArrayList<MainMenuModel> mainMenus = responceModel.getMainMenuList();
                                showMenus(mainMenus);
                            }else {
                                tvError.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<MainMenuResponceModel> call, Throwable t) {
                rlProgressMain.setVisibility(View.GONE);
                tvError.setVisibility(View.VISIBLE);
                tvError.setText(t.getMessage());
            }

        });
    }

    /*****************************************************
     * Manage the Main Menu UI.                          *
     * @param mainMenus : Menu list came from the server.*
     *****************************************************/
    private void showMenus(ArrayList<MainMenuModel> mainMenus) {
        for (int i = 0 ;i <mainMenus.size();i++){
            switch (mainMenus.get(i).getMenuName()) {
                case CHECKUP:
                    CHECKUP_MENU      = CHECKUP;
                    subMenuCheckup    = mainMenus.get(i).getSubMenuLists();
                    cardHealthCheckUp  .setVisibility(View.VISIBLE);
                    rlCheckupSmall.setVisibility(View.VISIBLE);
                    break;
                case GUIDE:
                    GUIDE_MENU      = GUIDE;
                    subMenuGuide    = mainMenus.get(i).getSubMenuLists();
                    cardHealthGuide  .setVisibility(View.VISIBLE);
                    rlGuideSmall.setVisibility(View.VISIBLE);
                    break;
                case WELLNESS:
                    WELLNESS_MENU   = WELLNESS;
                    subMenuWellness = mainMenus.get(i).getSubMenuLists();
                    cardWellness     .setVisibility(View.VISIBLE);
                    rlWellnessSmall.setVisibility(View.VISIBLE);
                    break;
                case FITNESS:
                    FITNESS_MENU   = FITNESS;
                    subMenuFitness = mainMenus.get(i).getSubMenuLists();
                    cardFitness     .setVisibility(View.VISIBLE);
                    rlFitnessSmall.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }

        int marginValue = DeviceUtility.convertDpToPx(this, 8);
        if (!CHECKUP_MENU.isEmpty() && !GUIDE_MENU.isEmpty()){
            llTop.setVisibility(View.VISIBLE);
            resizeImages(ivHealthCheckup,false,false,true,true);
        }
        if (!WELLNESS_MENU.isEmpty() && !FITNESS_MENU.isEmpty()){
            llBottom.setVisibility(View.VISIBLE);
            resizeImages(ivWelness,true,true,false ,false);
        }
        if (CHECKUP_MENU.isEmpty() && !GUIDE_MENU.isEmpty()){
            llTop.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams paramsGuide    = (LinearLayout.LayoutParams) cardHealthGuide.getLayoutParams();
            paramsGuide.bottomMargin = marginValue;
            paramsGuide.leftMargin = marginValue;
            cardHealthGuide.setLayoutParams(paramsGuide);
            resizeImages(ivHealthGuide,false,false,true ,false);
        }else if (!CHECKUP_MENU.isEmpty() && GUIDE_MENU.isEmpty()){
            llTop.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams paramsCheckup  = (LinearLayout.LayoutParams) cardHealthCheckUp.getLayoutParams();
            paramsCheckup.rightMargin = marginValue;
            cardHealthCheckUp.setLayoutParams(paramsCheckup);
            resizeImages(ivHealthCheckup,false,false,false ,true);
        }
        if (WELLNESS_MENU.isEmpty() && !FITNESS_MENU.isEmpty()){
            llBottom.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams paramsFitness  = (LinearLayout.LayoutParams) cardFitness.getLayoutParams();
            paramsFitness.leftMargin = marginValue;
            cardFitness.setLayoutParams(paramsFitness);
            resizeImages(ivFitness,true,false,false ,false);
        }else if (!WELLNESS_MENU.isEmpty() && FITNESS_MENU.isEmpty()){
            llBottom.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams paramsWellness = (LinearLayout.LayoutParams) cardWellness.getLayoutParams();
            paramsWellness.rightMargin = marginValue;
            cardWellness.setLayoutParams(paramsWellness);
            resizeImages(ivWelness,false,true,false ,false);
        }


    }

    /**********************************************************************************************
     * This method resize the ImageView ie. width and height same.so the background look circular*
      * @param view : ImageView                                                                   *
     *  @param isFitness : is fitness menu available ?                                           *
      * @param isWellness : is wellness menu available?                                           *
     *  @param isHealthGuide : is Health guide menu available?                                   *
      * @param isHealthCheckUp : is Health Checkup menu available?                                *
     *********************************************************************************************/
    private void resizeImages(final ImageView view, final boolean isFitness, final boolean isWellness,
                              final boolean isHealthGuide, final boolean isHealthCheckUp) {
        view.post(new Runnable() {
            @Override
            public void run() {

                int width = view.getWidth();
                int height = view.getHeight();
                int value = width ;
                if (isFitness){
                    final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)
                            ivFitness.getLayoutParams();
                    value = height < width ? height : width;
                    params.height = value;
                    params.width = value;
                    ivFitness.setLayoutParams(params);
                }
                if (isWellness){
                    final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)
                            ivWelness.getLayoutParams();
                    value = height < width ? height : width;
                    params.height = value;
                    params.width = value;
                    ivWelness.setLayoutParams(params);
                }
                if (isHealthGuide){
                    final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)
                            ivHealthGuide.getLayoutParams();
                    value = height < width ? height : width;
                    params.height = value;
                    params.width = value;
                    ivHealthGuide.setLayoutParams(params);
                }
                if (isHealthCheckUp){
                    final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)
                            ivHealthCheckup.getLayoutParams();
                    value = height < width ? height : width;
                    params.height = value;
                    params.width = value;
                    ivHealthCheckup.setLayoutParams(params);
                }
            }
        });

    }
}
