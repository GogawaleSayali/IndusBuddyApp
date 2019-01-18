package com.indushealthplus.android.indusbuddy.helper;

/**
 * Created by akshaya on 13/3/18.
 */

public class ErrorCodesAndMessagesManager {
    public static ErrorCodesAndMessagesManager errorCodesAndMessages;
    public static ErrorCodesAndMessagesManager getInstance(){
        if (errorCodesAndMessages == null){
            errorCodesAndMessages = new ErrorCodesAndMessagesManager();
        }
        return errorCodesAndMessages;
    }

    public String S_CODE_0       = "0";
    public  String S_CODE_1       = "1";
    public  String E_CODE_200     = "200";
    public  String E_CODE_200_Msg = "OK";
    public  int    E_CODE_400     = 400;
    public  String E_CODE_400_Msg = "Bad Request";
    public  int E_CODE_404        = 404;
    public  String E_CODE_404_Msg = "Not Found";
    public  int E_CODE_500        = 500;
    public  String E_CODE_500_Msg = "Internal Server Error";
    public  int E_CODE_700        = 700;
    public  String E_CODE_700_Msg = "Please send all required parameters";
    public  int E_CODE_701        = 701;
    public  String E_CODE_701_Msg = "No Data Found In Appointments";
    public  int E_CODE_702        = 702;
    public  String E_CODE_702_Msg = "You Can Not Prepond Your Appointment";
    public  int E_CODE_703        = 703;
    public  String E_CODE_703_Msg = "You Can Not Take CT Pending Appointment";

    public String getErrorMessage(int error_code){
        switch (error_code){
            case 400:
                return getE_CODE_400_Msg();
            case 404:
                return getE_CODE_404_Msg();
            case 500:
                return getE_CODE_500_Msg();
            case 700:
                return getE_CODE_700_Msg();
            case 701:
                return getE_CODE_701_Msg();
            case 702:
                return getE_CODE_702_Msg();
            case 703:
                return getE_CODE_703_Msg();

        }
        return "";
    }

    public String getS_CODE_0() {
        return S_CODE_0;
    }

    public String getS_CODE_1() {
        return S_CODE_1;
    }

    public String getE_CODE_200() {
        return E_CODE_200;
    }

    public String getE_CODE_200_Msg() {
        return E_CODE_200_Msg;
    }

    public int getE_CODE_400() {
        return E_CODE_400;
    }

    public String getE_CODE_400_Msg() {
        return E_CODE_400_Msg;
    }

    public int getE_CODE_404() {
        return E_CODE_404;
    }

    public String getE_CODE_404_Msg() {
        return E_CODE_404_Msg;
    }

    public int getE_CODE_500() {
        return E_CODE_500;
    }

    public String getE_CODE_500_Msg() {
        return E_CODE_500_Msg;
    }

    public int getE_CODE_700() {
        return E_CODE_700;
    }

    public String getE_CODE_700_Msg() {
        return E_CODE_700_Msg;
    }

    public int getE_CODE_701() {
        return E_CODE_701;
    }

    public String getE_CODE_701_Msg() {
        return E_CODE_701_Msg;
    }

    public int getE_CODE_702() {
        return E_CODE_702;
    }

    public String getE_CODE_702_Msg() {
        return E_CODE_702_Msg;
    }

    public int getE_CODE_703() {
        return E_CODE_703;
    }

    public String getE_CODE_703_Msg() {
        return E_CODE_703_Msg;
    }
}
