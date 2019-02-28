package attendance.com.schoolmaster.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.model.StudentModel;

/**
 * Created by akmirajk on 2/26/2019.
 */

public class StudentDetailActivity extends AppCompatActivity {

    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private AppBarLayout mAppbarlayout;
    private StudentModel mStudentModel;
    private ImageView imgStudentPhoto;
    private TextView txtStudentName;
    private ImageView imgMotherPhoto;
    private ImageView imgFatherPhoto;
    private PieChart mPiechartGrades;
    private BarChart mBarchartAttendanceMonthly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        initView();
        prepareData();

    }


    private void initView() {

        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mAppbarlayout =(AppBarLayout) findViewById(R.id.app_bar_layout);
        imgStudentPhoto = (ImageView) findViewById(R.id.img_student_photo);
        txtStudentName = (TextView) findViewById(R.id.txt_student_name);
        imgMotherPhoto = (ImageView) findViewById(R.id.img_mother_photo);
        imgFatherPhoto = (ImageView) findViewById(R.id.img_father_photo);
        mPiechartGrades = (PieChart) findViewById(R.id.piechart_grades);
        mPiechartGrades.setUsePercentValues(true);
        mBarchartAttendanceMonthly = (BarChart) findViewById(R.id.barchart_attendance_monthly);
    }

    private void prepareData() {
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar_textstyle);
        setDummyData();
        mAppbarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    mCollapsingToolbarLayout.setTitle(mStudentModel.getStudentName());
                    txtStudentName.setVisibility(View.GONE);
                    isShow = true;
                } else if(isShow) {
                    txtStudentName.setVisibility(View.VISIBLE);
                    mCollapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
        txtStudentName.setText(mStudentModel.getStudentName());

        Drawable studentDrawable = getdrawableImage("student.jpg");
        if(studentDrawable!=null)
        {
            imgStudentPhoto.setImageDrawable(studentDrawable);
        }

        Drawable motherDrawable = getdrawableImage("mother.jpg");
        if(motherDrawable!=null)
        {
            imgMotherPhoto.setImageDrawable(motherDrawable);
        }

        Drawable fatherDrawable = getdrawableImage("father.jpg");
        if(fatherDrawable!=null)
        {
            imgFatherPhoto.setImageDrawable(fatherDrawable);
        }

        mBarchartAttendanceMonthly.setData(getTempData(4,60,getString(R.string.lbl_monthly_attendance)));
        getAttendanceChartData();

    }

    private Drawable getdrawableImage(String imgName) {

        Drawable d;
        try
        {
            // get input stream
            InputStream ims = getAssets().open(imgName);
            // load image as Drawable
            d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            ims .close();
        }
        catch(IOException ex)
        {
            return null;
        }
        return d;
    }


    private void setDummyData() {
        mStudentModel = new StudentModel("David Mark","22","Fourth","A");
    }

    private void setStudentImage() {
        try
        {
            // get input stream
            InputStream ims = getAssets().open("student.jpg");
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            imgStudentPhoto.setImageDrawable(d);
            ims .close();
        }
        catch(IOException ex)
        {
            return;
        }
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

        mBarchartAttendanceMonthly.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
//        data.setBarWidth(barWidth);
        return data;
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




}
