package com.indushealthplus.android.indusbuddy.models;

/**
 * Created by akshaya on 12/3/18.
 */

public class Model_filter_tips {
    private String flt_name ;
    private int flt_id ;
    private boolean flt_status ;

    public Model_filter_tips() {
    }

    public Model_filter_tips(String flt_name, int flt_id, boolean flt_status) {
        this.flt_name = flt_name;
        this.flt_id = flt_id;
        this.flt_status = flt_status;
    }

    public String getFlt_name() {
        return flt_name;
    }

    public void setFlt_name(String flt_name) {
        this.flt_name = flt_name;
    }

    public int getFlt_id() {
        return flt_id;
    }

    public void setFlt_id(int flt_id) {
        this.flt_id = flt_id;
    }

    public boolean isFlt_status() {
        return flt_status;
    }

    public void setFlt_status(boolean flt_status) {
        this.flt_status = flt_status;
    }
}
