package com.indushealthplus.android.indusbuddy.models;

/**
 * Created by akshaya on 28/2/18.
 */

public class Model_Availed_Package {
    private String pkg_name ;
    private String pkg_type ;
    private String pkg_test ;
    private String pkg_amount ;
    private String pkg_paymode ;
    private int image ;

    public Model_Availed_Package() {
    }

    public Model_Availed_Package(String pkg_name, String pkg_type, String pkg_test,
                                 String pkg_amount, String pkg_paymode ,int image) {
        this.pkg_name = pkg_name;
        this.pkg_type = pkg_type;
        this.pkg_test = pkg_test;
        this.pkg_amount = pkg_amount;
        this.pkg_paymode = pkg_paymode;
        this.image = image;
    }

    public String getPkg_name() {
        return pkg_name;
    }

    public void setPkg_name(String pkg_name) {
        this.pkg_name = pkg_name;
    }

    public String getPkg_type() {
        return pkg_type;
    }

    public void setPkg_type(String pkg_type) {
        this.pkg_type = pkg_type;
    }

    public String getPkg_test() {
        return pkg_test;
    }

    public void setPkg_test(String pkg_test) {
        this.pkg_test = pkg_test;
    }

    public String getPkg_amount() {
        return pkg_amount;
    }

    public void setPkg_amount(String pkg_amount) {
        this.pkg_amount = pkg_amount;
    }

    public String getPkg_paymode() {
        return pkg_paymode;
    }

    public void setPkg_paymode(String pkg_paymode) {
        this.pkg_paymode = pkg_paymode;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
