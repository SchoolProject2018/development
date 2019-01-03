package attendance.com.schoolmaster.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import attendance.com.schoolmaster.R;

public class DashboardActivity extends AppCompatActivity {

    private TextView mTxtDate;
    private ListView lstLectureList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.activity_actbar);

        mTxtDate = (TextView) findViewById(R.id.dashboard_txt_date);
        mTxtDate.setText(getCurrentDate());

        lstLectureList = (ListView) findViewById(R.id.dashboard_lst_classes);
        ArrayAdapter<String> lectureAdapter = new ArrayAdapter<String>(this, R.layout.lst_lecture_item,
                R.id.dashboard_txt_lecture_list, getLectureList());
        lstLectureList.setAdapter(lectureAdapter);

        lstLectureList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }

    public String getCurrentDate()
    {
        String datePattern = "E dd-MM-yyyy";
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat scDateFormat = new SimpleDateFormat(datePattern, Locale.ENGLISH);

        return scDateFormat.format(calendar.getTime());
    }

    public String[] getLectureList()
    {
        return new String[]{"10:10AM CLASS A Batch","11:10AM CLASS D Batch","04:10AM CLASS E Batch"};
    }
}
