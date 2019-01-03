package com.dogratech.indusbuddyapp.main.activities.navmenuactivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.activities.apphomeactivity.AppHomeActivity;
import com.dogratech.indusbuddyapp.main.activities.baseactivities.BaseActivity;
import com.dogratech.indusbuddyapp.main.adapters.MyCategoriesExpandableListAdapter;
import com.dogratech.indusbuddyapp.main.componentclasses.HomeComponents;
import com.dogratech.indusbuddyapp.main.helper.Constatnts;
import com.dogratech.indusbuddyapp.main.managers.SharedPrefsManager;
import com.dogratech.indusbuddyapp.main.models.Model_MainSubCategories;
import com.dogratech.indusbuddyapp.main.retrofit.ApiClient;
import com.dogratech.indusbuddyapp.main.retrofit.ApiInterfaceGet;
import com.dogratech.indusbuddyapp.main.retrofit.ApiUrl;
import com.dogratech.indusbuddyapp.main.uitility.Commman;
import com.dogratech.indusbuddyapp.main.uitility.NetworkUtility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dogratech.indusbuddyapp.main.activities.navmenuactivities.ConstantManager.CATEGORY;
import static com.dogratech.indusbuddyapp.main.activities.navmenuactivities.ConstantManager.SETTINGACTIVITY_CALL;
import static com.dogratech.indusbuddyapp.main.activities.navmenuactivities.ConstantManager.SUBCATEGORY;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = SettingsActivity.class.getName();
    private FrameLayout frameArticlePrefs;
    private TextView tvSave;
    //private RelativeLayout rlArticlePrefs;
    private ApiInterfaceGet interface_get;
    private ExpandableListView lvCategory;

    private ArrayList<HashMap<String, String>> parentItems;
    private ArrayList<ArrayList<HashMap<String, String>>> childItems;
    private MyCategoriesExpandableListAdapter myCategoriesExpandableListAdapter;
    private Model_MainSubCategories modelItemSubCategories;
    private SharedPrefsManager prefsManager;
    private Toolbar toolbar;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initToolBar();
        initialize();
        setListeners();


        String settingactivity = prefsManager.getData(SETTINGACTIVITY_CALL);
        if (settingactivity.equalsIgnoreCase(Constatnts.NODATA)) {
            requestGetCategories();
        } else {
            String subCate = prefsManager.getData(SUBCATEGORY);
            if (!subCate.equalsIgnoreCase(Constatnts.NODATA)) {
                Type type = new TypeToken<ArrayList<ArrayList<HashMap<String, String>>>>() {
                }.getType();
                Gson gson = new Gson();
                childItems = gson.fromJson(subCate, type);


                String Cate = prefsManager.getData(CATEGORY);
                if (!Cate.equalsIgnoreCase(Constatnts.NODATA)) {
                    Type typecat = new TypeToken<ArrayList<HashMap<String, String>>>() {
                    }.getType();

                    parentItems = gson.fromJson(Cate, typecat);


                    ConstantManager.parentItems = parentItems;
                    ConstantManager.childItems = childItems;

                    myCategoriesExpandableListAdapter = new MyCategoriesExpandableListAdapter(this, parentItems, childItems, false);
                    lvCategory.setAdapter(myCategoriesExpandableListAdapter);
                }
            }
            Log.d(TAG, "onCreate: ");

            if (parentItems.size() >= 1) {
                frameArticlePrefs.setVisibility(View.VISIBLE);
            } else {
                frameArticlePrefs.setVisibility(View.INVISIBLE);
            }


        }


    }


    private void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        initializeToolBar(toolbar, "Settings");
    }

    private void initialize() {
        interface_get = ApiClient.getClient(ApiClient.BASE_URL_TYEP_INDUS).create(ApiInterfaceGet.class);
        //setupReferences();
        // rvArticleSettings = findViewById(R.id.rvArticleSettings);
        frameArticlePrefs = findViewById(R.id.frameArticlePrefs);
        tvSave = findViewById(R.id.tvSave);
        // rlArticlePrefs = findViewById(R.id.rlArticlePrefs);

        lvCategory = findViewById(R.id.lvCategory);
        parentItems = new ArrayList<>();
        childItems = new ArrayList<>();
        prefsManager = SharedPrefsManager.getSharedInstance(SettingsActivity.this);


      /*  LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        rvArticleSettings   .setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(rvArticleSettings.getContext(),
                        recyclerLayoutManager.getOrientation());
        rvArticleSettings.addItemDecoration(dividerItemDecoration);
        ArticleSettingsAdapter articleSettingsAdapter = new
        ArticleSettingsAdapter(getArticlePrefs(),this);
        rvArticleSettings.setAdapter(articleSettingsAdapter);*/


    }

    private void setupReferences() {

        ArrayList<String> categoryIdList = new ArrayList<>();


        for (int i = 0; i < modelItemSubCategories.getSubcategoriesList().getSubcategoriesdetails().size(); i++) {
            categoryIdList.add(modelItemSubCategories.getSubcategoriesList().getSubcategoriesdetails().get(i).getModel_categoryMasterClass().getCategoryId());
        }

        categoryIdList = new ArrayList<String>(new LinkedHashSet<String>(categoryIdList));

        for (int i = 0; i < categoryIdList.size(); i++) {
            String categoryId = categoryIdList.get(i);
            ArrayList<HashMap<String, String>> childArrayList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> mapParent = new HashMap<String, String>();

            for (int j = 0; j < modelItemSubCategories.getSubcategoriesList().getSubcategoriesdetails().size(); j++) {


                if (categoryId.equals(modelItemSubCategories.getSubcategoriesList().getSubcategoriesdetails().get(j).getModel_categoryMasterClass().getCategoryId())) {

                    mapParent.put(ConstantManager.Parameter.CATEGORY_ID, modelItemSubCategories.getSubcategoriesList().getSubcategoriesdetails().get(j).getModel_categoryMasterClass().getCategoryId());
                    mapParent.put(ConstantManager.Parameter.CATEGORY_NAME, modelItemSubCategories.getSubcategoriesList().getSubcategoriesdetails().get(j).getModel_categoryMasterClass().getCategory());
                    mapParent.put(ConstantManager.Parameter.IS_CHECKED, ConstantManager.CHECK_BOX_CHECKED_TRUE);

                    HashMap<String, String> mapChild = new HashMap<String, String>();
                    mapChild.put(ConstantManager.Parameter.SUB_ID, modelItemSubCategories.getSubcategoriesList().getSubcategoriesdetails().get(j).getSubCategoryId());
                    mapChild.put(ConstantManager.Parameter.SUB_CATEGORY_NAME, modelItemSubCategories.getSubcategoriesList().getSubcategoriesdetails().get(j).getSubCategory());
                    mapChild.put(ConstantManager.Parameter.CATEGORY_ID, modelItemSubCategories.getSubcategoriesList().getSubcategoriesdetails().get(j).getSubCategoryId());
                    mapChild.put(ConstantManager.Parameter.IS_CHECKED, ConstantManager.CHECK_BOX_CHECKED_TRUE);
                    childArrayList.add(mapChild);
                }

            }

            childItems.add(childArrayList);
            parentItems.add(mapParent);

            ConstantManager.parentItems = parentItems;
            ConstantManager.childItems = childItems;

        }
        myCategoriesExpandableListAdapter = new MyCategoriesExpandableListAdapter(this, parentItems, childItems, false);
        lvCategory.setAdapter(myCategoriesExpandableListAdapter);

        if (parentItems.size() >= 1) {
            frameArticlePrefs.setVisibility(View.VISIBLE);
        } else {
            frameArticlePrefs.setVisibility(View.INVISIBLE);
        }


    }


    private void setListeners() {
        //rlArticlePrefs.setOnClickListener(this);
        tvSave.setOnClickListener(this);
    }

   /*private List<ModelArticleSettings> getArticlePrefs(){
        List<ModelArticleSettings> modelList = new ArrayList<ModelArticleSettings>();
        modelList.add(new ModelArticleSettings("Heart Day", false));
        modelList.add(new ModelArticleSettings("Yoga", false));
        modelList.add(new ModelArticleSettings("Diet", false));
        return modelList;
    }*/


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
           /* case R.id.rlArticlePrefs:
                requestGetCategories();
                //frameArticlePrefs.setVisibility(View.VISIBLE);
                break;*/
            case R.id.tvSave:
                Gson gson = new Gson();
                String subcate = gson.toJson(childItems);
                String cate = gson.toJson(parentItems);

                prefsManager.setData(SUBCATEGORY, subcate);
                prefsManager.setData(CATEGORY, cate);
                prefsManager.setData(SETTINGACTIVITY_CALL, "Setting");

                //frameArticlePrefs.setVisibility(View.GONE);
                finish();
                break;
        }
    }




    /*private void requestGetCategories()
       {
           String url      = ApiUrl.Base_URL_INDUS + ApiUrl.GET_CATEGORIES;
           if(NetworkUtility.isNetworkAvailable(getApplicationContext())){
               rlProgress.setVisibility(View.VISIBLE);
               interface_get.getArticleCategories(url).enqueue(new Callback<Model_Categories>() {
                   @Override
                   public void onResponse(Call<Model_Categories> call, Response<Model_Categories> response) {
                       if (rlProgress.getVisibility() == View.VISIBLE){
                           rlProgress.setVisibility(View.GONE);
                       }
                       try{
                           if(response.isSuccessful()){
                               if(response.body()!=null){
                                   modelItemCategories = response.body().getCategoriesList().getCategories();

                               }
                           }
                       }catch (Exception e){
                           e.printStackTrace();
                       }
                   }

                   @Override
                   public void onFailure(Call<Model_Categories> call, Throwable t) {
                       if (rlProgress.getVisibility() == View.VISIBLE){
                           rlProgress.setVisibility(View.GONE);
                       }
                       Log.d(TAG,t.toString());
                   }
               });
           }else{
               snackInternet();
           }
       }

       public void snackInternet(){
           Snackbar snackbar = Snackbar
                   .make(lvCategory, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
                   .setAction("Retry", new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                       }
                   });

           snackbar.setActionTextColor(Color.RED);
           View sbView = snackbar.getView();
           TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
           textView.setTextColor(Color.YELLOW);
           snackbar.show();
       }

   */


    private void requestGetCategories() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SettingsActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_loader, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        alertDialog.show();

        String url = ApiUrl.Base_URL_INDUS + ApiUrl.GET_SUBCATEGORIES;
        if (NetworkUtility.isNetworkAvailable(getApplicationContext())) {

            interface_get.getSubCategories(url).enqueue(new Callback<Model_MainSubCategories>() {
                @Override
                public void onResponse(Call<Model_MainSubCategories> call, Response<Model_MainSubCategories> response) {
                    if (alertDialog.isShowing()) {
                        alertDialog.hide();
                    }
                    try {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                // modelItemCategories = response.body().getCategoriesList().getCategories();
                                modelItemSubCategories = response.body();
                                setupReferences();

                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<Model_MainSubCategories> call, Throwable t) {
                    if (alertDialog.isShowing()) {
                        alertDialog.hide();
                    }
                    Log.d(TAG, t.toString());
                }
            });
        } else {
            snackInternet();
        }
    }

    public void snackInternet() {
        Snackbar snackbar = Snackbar
                .make(lvCategory, getString(R.string.no_internet), Snackbar.LENGTH_LONG)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });

        snackbar.setActionTextColor(Color.RED);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item != null) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    finish();
                    break;
                case R.id.action_home:
                    Gson gson = new Gson();
                    String subcate = gson.toJson(childItems);
                    String cate = gson.toJson(parentItems);

                    prefsManager.setData(SUBCATEGORY, subcate);
                    prefsManager.setData(CATEGORY, cate);
                    prefsManager.setData(SETTINGACTIVITY_CALL, "Setting");
                    Intent i = new Intent(this, AppHomeActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    finish();
                    break;
                default:
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /*****************************************************************
     * This method shows <- Arrow on top left corner of the activity.*
     *****************************************************************/
    protected void initializeToolBar(Toolbar toolbar, String title) {
        this.toolbar = toolbar;
        setSupportActionBar(this.toolbar);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true); // disable the button
        actionBar.setDisplayHomeAsUpEnabled(true); // remove the left caret
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(title);
    }


}
