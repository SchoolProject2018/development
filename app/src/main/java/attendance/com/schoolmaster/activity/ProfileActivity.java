package attendance.com.schoolmaster.activity;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import attendance.com.schoolmaster.R;

public class ProfileActivity extends AppCompatActivity {

    BarChart mChart, mChart1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mChart = (BarChart) findViewById(R.id.barchart_attend);
        mChart1 = (BarChart) findViewById(R.id.barchart_perform);

        getSupportActionBar().hide();

        setTempData(6, 50);
    }

    private void setTempData(int noOfLines, int range)
    {
        ArrayList<BarEntry> barValues = new ArrayList<>();
        float barWidth = 2f;
        float spaceForBar = 4f;

        for (int i = 0; i < noOfLines; i++)
        {
            float val = (float)Math.random()* range;
            barValues.add(new BarEntry(i+spaceForBar, val));
        }

        BarDataSet set1 = new BarDataSet(barValues, "Paathshala");
        BarData data = new BarData(set1);
        data.setBarWidth(barWidth);

        mChart.setData(data);

        mChart1.setData(data);
    }
}
