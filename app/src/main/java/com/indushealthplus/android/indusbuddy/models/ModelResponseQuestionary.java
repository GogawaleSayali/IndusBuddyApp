package com.indushealthplus.android.indusbuddy.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshaya on 14/3/18.
 */

public class ModelResponseQuestionary {
    /*
    *{
        "status_code":0,
        "data":{
        "hraTypes":[],
        "answerArray":[
        ]
        },
        "error_code":0,
        "msg":"success"
        }
    * */
    @SerializedName("status_code")
    private int status_code ;

    @SerializedName("data")
    private Model_Item_hraTypes_answerArray typesAnswerArray ;

    @SerializedName("error_code")
    private int error_code ;

    @SerializedName("msg")
    private String msg ;

    public ModelResponseQuestionary() {
    }

    public ModelResponseQuestionary(int status_code, Model_Item_hraTypes_answerArray typesAnswerArray,
                                    int error_code, String msg) {
        this.status_code = status_code;
        this.typesAnswerArray = typesAnswerArray;
        this.error_code = error_code;
        this.msg = msg;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public Model_Item_hraTypes_answerArray getTypesAnswerArray() {
        return typesAnswerArray;
    }

    public void setTypesAnswerArray(Model_Item_hraTypes_answerArray typesAnswerArray) {
        this.typesAnswerArray = typesAnswerArray;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
