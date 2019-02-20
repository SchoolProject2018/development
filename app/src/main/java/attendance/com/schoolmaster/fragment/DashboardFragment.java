package attendance.com.schoolmaster.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.activity.ProfileActivity;
import attendance.com.schoolmaster.activity.RecordAttendanceActivity;
import attendance.com.schoolmaster.adapter.ClassListAdapter;
import attendance.com.schoolmaster.model.ClasslstModel;

/**
 * Created by akmirajk on 1/18/2019.
 */

public class DashboardFragment extends Fragment implements ClassListAdapter.OnItemClickListener {

//    private TextView mTxtDate;
    private Activity mActivity;
    private RecyclerView mRvClass;
    private ClassListAdapter mClassAdapter;
    private List<ClasslstModel> mClassesList;
    private PieChart mPiechartGrades;
    private HorizontalBarChart mBarChartaAttendanceToday;
    private BarChart mBarchartAttendanceMonthly;

    String[] cListArray = {"Class A", "Class B", "Class C", "Class D", "Class E","Class F","Class G"};
    String[] numArray = {"Number of Students : 45","Number of Students : 60","Number of Students : 55",
            "Number of Students : 75","Number of Students : 50","Number of Students : 75","Number of Students : 50","Number of Students : 75","Number of Students : 50"};



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_dashboard, container, false);
        mActivity = getActivity();
        initView(rootview);
        prepareData();
        return rootview;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles


    }

    private void initView(View rootview) {

//        mTxtDate = (TextView) rootview.findViewById(R.id.dashboard_txt_date);
//        mLstLectureList = (ListView) findViewById(R.id.dashboard_lst_classes);

        mRvClass = (RecyclerView) rootview.findViewById(R.id.rv_class);
        mRvClass.setLayoutManager( new LinearLayoutManager(mActivity));
        mRvClass.setNestedScrollingEnabled(false);
        mClassesList = new ArrayList<>();
        mPiechartGrades = (PieChart) rootview.findViewById(R.id.piechart_grades);
        mPiechartGrades.setUsePercentValues(true);
        mBarChartaAttendanceToday = (HorizontalBarChart) rootview.findViewById(R.id.barchart_attendance_today);
        mBarchartAttendanceMonthly = (BarChart) rootview.findViewById(R.id.barchart_attendance_monthly);
    }

    private void prepareData()
    {
//        mTxtDate.setText(getCurrentDate());
        for (int i = 0; i < cListArray.length; i++) {
            ClasslstModel cls = new ClasslstModel(cListArray[i], "Rewards: none", numArray[i]);
            mClassesList.add(cls);
        }

        mClassAdapter = new ClassListAdapter(mClassesList,this);
        mRvClass.setAdapter(mClassAdapter);
        mClassAdapter.notifyDataSetChanged();

        mBarChartaAttendanceToday.setData(getTempData(4,60,getString(R.string.lbl_todays_attendance)));
        mBarchartAttendanceMonthly.setData(getTempData(4,60,getString(R.string.lbl_todays_attendance)));
        getAttendanceChartData();
    }

    public String getCurrentDate()
    {
        String datePattern = "E dd-MM-yyyy";
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat scDateFormat = new SimpleDateFormat(datePattern, Locale.ENGLISH);
        return scDateFormat.format(calendar.getTime());
    }

    @Override
    public void onItemClick(ClasslstModel classlstModel) {
        Intent i = new Intent(mActivity,ProfileActivity.class);
        i.putExtra("classlstModel",(Serializable)classlstModel);
        startActivity(i);
    }

    @Override
    public void onBtnAttendanceClick(ClasslstModel classlstModel) {
        Intent i = new Intent(mActivity,RecordAttendanceActivity.class);
        i.putExtra("classlstModel",(Serializable)classlstModel);
        startActivity(i);
    }

    private void getAttendanceChartData()
    {


        ArrayList<PieEntry> yvalues = new ArrayList<PieEntry>();
        yvalues.add(new PieEntry(45f, "A+"));
        yvalues.add(new PieEntry(15f, "A"));
        yvalues.add(new PieEntry(12f, "B"));
        yvalues.add(new PieEntry(20f, "C"));
        yvalues.add(new PieEntry(8f, "F"));


        PieDataSet dataSet = new PieDataSet(yvalues, "Grade Results");
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        Description description = new Description();
        description.setText("Grades");

        mPiechartGrades.setDescription(description);
        mPiechartGrades.setEntryLabelColor(Color.BLACK);
        mPiechartGrades.setEntryLabelTextSize(8f);
        mPiechartGrades.setDrawHoleEnabled(false);
//        pieChart.setTransparentCircleRadius(58f);
//
//        pieChart.setHoleRadius(58f);
//        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
//

        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.DKGRAY);
        data.setValueFormatter(new PercentFormatter());
        mPiechartGrades.setData(data);
    }


    private BarData getTempData(int noOfLines, int range, String lable)
    {
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Present");
        labels.add("Absent");
        labels.add("Late");
        labels.add("Left Early");

        ArrayList<BarEntry> barValues = new ArrayList<>();
        float barWidth = 2f;
        float spaceForBar = 4f;

        for (int i = 0; i < noOfLines; i++)
        {
            float val = (float)Math.random()* range;
            barValues.add(new BarEntry(i+spaceForBar, val));
        }

        BarDataSet set1 = new BarDataSet(barValues, lable);
        set1.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data = new BarData(set1);

        mBarChartaAttendanceToday.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
//        data.setBarWidth(barWidth);
        return data;
    }


}
