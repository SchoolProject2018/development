package attendance.com.schoolmaster.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.adapter.RecordAttendanceAdapter;
import attendance.com.schoolmaster.model.StudlstModel;

/**
 * Created by akmirajk on 12/31/2018.
 */

public class RecordAttendanceActivity extends AppCompatActivity {

    private RecyclerView rvStudentList;
    private RecordAttendanceAdapter recordAttendanceAdapter;
    private List<StudlstModel> studentList;
    private TextView  mTxtToolbarTitle;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_attendance);

        initView();
        prepareData();

    }



    private void initView() {
        studentList = new ArrayList<>();
        rvStudentList = (RecyclerView) findViewById(R.id.rv_student_list);
        rvStudentList.setLayoutManager(new LinearLayoutManager(this));
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTxtToolbarTitle = (TextView) findViewById(R.id.txt_toolbar_title);

    }

    private void prepareData() {
        mTxtToolbarTitle.setText(R.string.lbl_Attendance);
        setStudentDummyData();
        recordAttendanceAdapter = new RecordAttendanceAdapter(studentList);
        rvStudentList.setAdapter(recordAttendanceAdapter);
    }

    private void setStudentDummyData() {

        StudlstModel s1 = new StudlstModel("Akshaya Mirajkar","1","test","Female");
        StudlstModel s2 = new StudlstModel("Vineet Menon","2","test","Male");

        studentList.add(s1);
        studentList.add(s2);
    }

}
