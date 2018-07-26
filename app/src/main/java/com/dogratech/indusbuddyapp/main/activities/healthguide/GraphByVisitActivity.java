package com.dogratech.indusbuddyapp.main.activities.healthguide;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.TextView;

import com.dogratech.indusbuddyapp.R;
import com.dogratech.indusbuddyapp.main.models.ParamGraphModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GraphByVisitActivity extends AppCompatActivity implements
        OnChartGestureListener,
        OnChartValueSelectedListener {
    private static final String STATUS_NORMAL = "normal";
    private static final String STATUS_LOW = "abnormal low";
    private static final String STATUS_HIGN = "abnormal high";
    private static final String STATUS_NO = "no";
    private LineChart mChart ;
    private TextView tvParamName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_by_visit);
        initializeToolBar();
        initialise();
        setListeners();
        setData();
        //setLimitToData();
    }

    private void setListeners() {
        mChart      . setOnChartGestureListener(this);
        mChart      . setOnChartValueSelectedListener(this);
    }

    private void initialise() {
        getWindow() . setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mChart      = findViewById(R.id.linechart);
        tvParamName = findViewById(R.id.tvParamName);
    }

    private void initializeToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar() . setHomeButtonEnabled(true); // disable the button
        getSupportActionBar() . setDisplayHomeAsUpEnabled(true); // remove the left caret
        getSupportActionBar() . setDisplayShowHomeEnabled(true);
        getSupportActionBar() . setTitle("Track Parameter Graph");
    }

    private void setData() {
        mChart.setDescription("");
        mChart.setNoDataTextDescription("You need to provide data for the chart.");
        String paramName = getIntent().getStringExtra("paramName");
        String jsonStr = getIntent().getStringExtra("paramData");
        tvParamName.setText(paramName);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ParamGraphModel>>() {}.getType();
        ArrayList<ParamGraphModel> graphModels = gson.fromJson(jsonStr,type);
        ArrayList<String> xVals = setXAxisValues(graphModels);
        ArrayList<Entry> yVals = setYAxisValues(graphModels);

        LineDataSet set1;
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        // create a dataset and give it a type
        set1 = new LineDataSet(yVals, "");
        ArrayList<Integer> colors = new ArrayList<>();
        set1.setFillAlpha(110);
        //set1.setFillColor(Color.parseColor("#ff6633"));
        // set the line to be drawn like this "- - - - - -"
        //set1.enableDashedLine(10f, 5f, 0f);
        //set1.enableDashedHighlightLine(10f, 5f, 0f);

        set1.setColor(Color.parseColor("#000000"));
        set1.setLineWidth(0.5f);
        set1.setCircleRadius(5f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
        set1.setDrawFilled(true);
        set1.setDrawCubic(true);


        for (ParamGraphModel graphModel: graphModels) {
            if (graphModel.getTestStatus().equalsIgnoreCase(STATUS_HIGN)){
                colors.add(Color.RED);
            }else if (graphModel.getTestStatus().equalsIgnoreCase(STATUS_NORMAL)){
                colors.add(Color.parseColor("#0daa5a"));
            }else if (graphModel.getTestStatus().equalsIgnoreCase(STATUS_LOW)){
                colors.add(Color.parseColor("#ff6633"));
            }else if (graphModel.getTestStatus().equalsIgnoreCase(STATUS_NO)){
                colors.add(Color.BLACK);
            }
        }
        set1.setCircleColors(colors);
        dataSets.add(set1);
        LineData data = new LineData(xVals, dataSets);
        mChart.setData(data);
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mChart.invalidate();
        // dataSets.add(set1); // add the datasets
        // create a data object with the datasets
        // set data
    }



    private ArrayList<String> setXAxisValues(ArrayList<ParamGraphModel> graphModels){
        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0 ; i < graphModels.size();i++){
            xVals.add(graphModels.get(i).getVisitDate());
        }

        return xVals;
    }

    private ArrayList<Entry> setYAxisValues(ArrayList<ParamGraphModel> graphModels){
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0 ; i < graphModels.size();i++){
            Entry entry = new Entry(Float.valueOf(graphModels.get(i).getParamValue()),i);
            yVals.add(entry);
        }
        return yVals;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case android.R.id.home:
                /*intent = new Intent(UnavailedPackageDetailsActivity.this, UnAvailedPackagesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                startActivity(intent);*/
                finish();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "START, x: " + me.getX() + ", y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "END, lastGesture: " + lastPerformedGesture);
        // un-highlight values after the gesture is finished and no single-tap
        if(lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            // or highlightTouch(null) for callback to onNothingSelected(...)
            mChart.highlightValues(null);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: "
                + velocityX + ", VeloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        Log.i("Entry selected", e.toString());
        Log.i("LOWHIGH", "low: " + mChart.getLowestVisibleXIndex()
                + ", high: " + mChart.getHighestVisibleXIndex());

        Log.i("MIN MAX", "xmin: " + mChart.getXChartMin()
                + ", xmax: " + mChart.getXChartMax()
                + ", ymin: " + mChart.getYChartMin()
                + ", ymax: " + mChart.getYChartMax());
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }
}