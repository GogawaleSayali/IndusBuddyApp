package com.dogratech.indusbuddyapp.main.activities.navmenuactivities;

import java.util.ArrayList;
import java.util.HashMap;

public class ConstantManager {
    public static final String CHECK_BOX_CHECKED_TRUE = "YES";
    public static final String CHECK_BOX_CHECKED_FALSE = "NO";
    public static final String SUBCATEGORY = "subcategorylist";
    public static final String CATEGORY = "categorylist";
    public static final String SETTINGACTIVITY_CALL = "settingactivitycall";
    public static final String SETTING = "Setting";
    public static final String SETTING1 = "Setting1";

    public static ArrayList<ArrayList<HashMap<String, String>>> childItems = new ArrayList<>();
    public static ArrayList<HashMap<String, String>> parentItems = new ArrayList<>();


    public class Parameter {
        public static final String IS_CHECKED = "is_checked";
        public static final String SUB_CATEGORY_NAME = "sub_category_name";
        public static final String CATEGORY_NAME = "category_name";
        public static final String CATEGORY_ID = "category_id";
        public static final String SUB_ID = "sub_id";
    }
}
