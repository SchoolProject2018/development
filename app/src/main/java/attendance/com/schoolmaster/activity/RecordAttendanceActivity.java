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
import java.util.Calendar;
import java.util.List;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.adapter.RecordAttendanceAdapter;
import attendance.com.schoolmaster.model.StudentAttendanceModel;
import attendance.com.schoolmaster.utils.Constants;

/**
 * Created by akmirajk on 12/31/2018.
 */

public class RecordAttendanceActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView rvStudentList;
    private RecordAttendanceAdapter recordAttendanceAdapter;
    private List<StudentAttendanceModel> mAttendanceList;
    private TextView  mTxtToolbarTitle;
    private Toolbar mToolbar;
    private Button btnSubmit;
    private TextView txtDateValue;
    private Constants mConstants;
    private TextView txtClassInfoHeading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_attendance);

        initView();
        prepareData();

    }



    private void initView() {
        mConstants = new Constants();
        mAttendanceList = new ArrayList<>();
        rvStudentList = (RecyclerView) findViewById(R.id.rv_student_list);
        rvStudentList.setLayoutManager(new LinearLayoutManager(this));
        rvStudentList.setNestedScrollingEnabled(false);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTxtToolbarTitle = (TextView) findViewById(R.id.txt_toolbar_title);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);
        txtDateValue = (TextView) findViewById(R.id.txt_date_value);
        txtClassInfoHeading = (TextView) findViewById(R.id.txt_class_info_heading);
    }

    private void prepareData() {
        txtClassInfoHeading.requestFocus();
        mTxtToolbarTitle.setText(R.string.lbl_Attendance);
        setStudentDummyData();
        recordAttendanceAdapter = new RecordAttendanceAdapter(mAttendanceList,this, true);
        rvStudentList.setAdapter(recordAttendanceAdapter);
        txtDateValue.setText(mConstants.getAttendanceDateFormat().format(Calendar.getInstance().getTime()));
    }

    private void setStudentDummyData() {

        StudentAttendanceModel s1 = new StudentAttendanceModel("Abhishek Menon","1",true,false,false,"");
        StudentAttendanceModel s2 = new StudentAttendanceModel("Akshaya Kute","2",true,false,false,"");
        StudentAttendanceModel s3 = new StudentAttendanceModel("Mukesh Kumar","3",true,false,false,"");
        StudentAttendanceModel s4 = new StudentAttendanceModel("Shrikant Kelkar","4",true,false,false,"");
        StudentAttendanceModel s5 = new StudentAttendanceModel("Preeti Shah","5",true,false,false,"");
        StudentAttendanceModel s6 = new StudentAttendanceModel("Kamlesh Mhatre","6",true,false,false,"");
        StudentAttendanceModel s7 = new StudentAttendanceModel("Suresh Mangi","7",true,false,false,"");
        StudentAttendanceModel s8 = new StudentAttendanceModel("Devendra Rane","8",true,false,false,"");
        StudentAttendanceModel s9 = new StudentAttendanceModel("Karthik Nair","9",true,false,false,"");
        StudentAttendanceModel s10 = new StudentAttendanceModel("Narendra Vani","10",true,false,false,"");
        StudentAttendanceModel s11 = new StudentAttendanceModel("Surbhi Srivastav","11",true,false,false,"");

        mAttendanceList.add(s1);
        mAttendanceList.add(s2);
        mAttendanceList.add(s3);
        mAttendanceList.add(s4);
        mAttendanceList.add(s5);
        mAttendanceList.add(s6);
        mAttendanceList.add(s7);
        mAttendanceList.add(s8);
        mAttendanceList.add(s9);
        mAttendanceList.add(s10);
        mAttendanceList.add(s11);

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
