package attendance.com.schoolmaster.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.adapter.RecordAttendanceAdapter;
import attendance.com.schoolmaster.model.StudlstModel;

/**
 * Created by akmirajk on 12/31/2018.
 */

public class RecordAttendanceActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView rvStudentList;
    private RecordAttendanceAdapter recordAttendanceAdapter;
    private List<StudlstModel> studentList;
    private TextView  mTxtToolbarTitle;
    private Toolbar mToolbar;
    private Button btnSubmit;
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
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);

    }

    private void prepareData() {
        mTxtToolbarTitle.setText(R.string.lbl_Attendance);
        setStudentDummyData();
        recordAttendanceAdapter = new RecordAttendanceAdapter(studentList);
        rvStudentList.setAdapter(recordAttendanceAdapter);
    }

    private void setStudentDummyData() {

        StudlstModel s1 = new StudlstModel("Student 1","1","test","Female");
        StudlstModel s2 = new StudlstModel("Student 2","2","test","Male");
        StudlstModel s3 = new StudlstModel("Student 3","3","test","Male");
        StudlstModel s4 = new StudlstModel("Student 4","4","test","Male");
        StudlstModel s5 = new StudlstModel("Student 5","5","test","Male");
        StudlstModel s6 = new StudlstModel("Student 6","6","test","Male");
        StudlstModel s7 = new StudlstModel("Student 7","7","test","Male");
        StudlstModel s8 = new StudlstModel("Student 8","8","test","Male");
        StudlstModel s9 = new StudlstModel("Student 9","9","test","Male");
        StudlstModel s10 = new StudlstModel("Student 10","10","test","Male");
        StudlstModel s11 = new StudlstModel("Student 11","11","test","Male");

        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);
        studentList.add(s4);
        studentList.add(s5);
        studentList.add(s6);
        studentList.add(s7);
        studentList.add(s8);
        studentList.add(s9);
        studentList.add(s10);
        studentList.add(s11);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.btn_submit:
                finish();
            default:
                break;
        }
    }
}
