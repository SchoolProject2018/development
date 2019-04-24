package attendance.com.schoolmaster.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.adapter.StudentListExamAdapter;
import attendance.com.schoolmaster.interfaces.PopupListCallback;
import attendance.com.schoolmaster.model.StudentExamModel;
import attendance.com.schoolmaster.utils.Constants;
import attendance.com.schoolmaster.utils.Utils;

public class AddExamActivity extends AppCompatActivity implements View.OnClickListener,StudentListExamAdapter.OnItemClickListener {

    private View mIncludeExamDate;
    private TextView mExamDateHeader;
    private TextView mExamDateValue;
    private Calendar mCalendar;
    private int mDay;
    private int mMonth;
    private int mYear;
    private Context mContext;
    private Constants mConstants;
    private View includeExamType;
    private View includeExamSubject;
    private TextView txtExamTypeHeader;
    private TextView txtExamTypeValue;
    private TextView txtExamSubjectHeader;
    private TextView txtExamSubjectValue;
    private TextView mTxtToolbarTitle;
    private RecyclerView mRvStudents;
    private Button btnSubmit;
    private Button btnCancel;
    private List<StudentExamModel> mStudentExamList;
    private StudentListExamAdapter mStudentListExamAdapter;
    private List<String> mExamTypeList = new ArrayList<>();
    private List<String> mSubjectList = new ArrayList<>();
    private EditText edtMaxMarks;

    String[] sListArray = {"Master Clark", "Master Natalie", "Master Bruce", "Master Robin", "Master Optimus"};
    String[] rollNoArray = {"1","2","3","4","5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exam);


        initView();
        prepareData();

    }

    private void initView() {
        mConstants = new Constants();
        mContext = this;
        mStudentExamList = new ArrayList<>();
        mTxtToolbarTitle = (TextView) findViewById(R.id.txt_toolbar_title);
        mIncludeExamDate = findViewById(R.id.include_exam_date);
        mExamDateHeader = mIncludeExamDate.findViewById(R.id.txt_date_header);
        mExamDateValue = mIncludeExamDate.findViewById(R.id.txt_date_value);
        mIncludeExamDate.setOnClickListener(this);
        includeExamType = findViewById(R.id.include_exam_type);
        txtExamTypeHeader = includeExamType.findViewById(R.id.txt_spinner_header);
        txtExamTypeValue = includeExamType.findViewById(R.id.txt_spinner_value);
        includeExamType.setOnClickListener(this);
        includeExamSubject = findViewById(R.id.include_exam_subject);
        txtExamSubjectHeader = includeExamSubject.findViewById(R.id.txt_spinner_header);
        txtExamSubjectValue = includeExamSubject.findViewById(R.id.txt_spinner_value);
        includeExamSubject.setOnClickListener(this);

        mRvStudents = (RecyclerView) findViewById(R.id.rv_student_list);
        mRvStudents.setLayoutManager(new LinearLayoutManager(mContext));
        mRvStudents.setNestedScrollingEnabled(true);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);
        btnCancel = (Button)findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);

        edtMaxMarks = (EditText) findViewById(R.id.edt_max_marks);
    }

    private void prepareData()
    {
        setExamTypeList();
        setSubjectList();
        mTxtToolbarTitle.setText(getResources().getText(R.string.lbl_add_exam_marks));
        mExamDateHeader.setText(R.string.lbl_exam_date);
        mCalendar = Calendar.getInstance();
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        txtExamTypeHeader.setText(R.string.lbl_exam_type);
        setDummyData();
        mStudentListExamAdapter = new StudentListExamAdapter(mStudentExamList,mContext,this);
        mRvStudents.setAdapter(mStudentListExamAdapter);
        mStudentListExamAdapter.notifyDataSetChanged();
        txtExamSubjectHeader.setText(R.string.lbl_subject);
    }

    private void setSubjectList() {
        mSubjectList.add("English");
        mSubjectList.add("Marathi");
        mSubjectList.add("Hindi");
        mSubjectList.add("Maths");
        mSubjectList.add("Science");
        mSubjectList.add("Social Studies");
        mSubjectList.add("Drawing");
    }

    private void setDummyData() {

        for (int i = 0; i < sListArray.length; i++) {
            StudentExamModel cls = new StudentExamModel();
            cls.setRollNumber( rollNoArray[i]);
            cls.setStudentName(sListArray[i]);
            mStudentExamList.add(cls);
        }
    }

    private void setExamTypeList() {
        mExamTypeList.add("Unit Test");
        mExamTypeList.add("Final");
        mExamTypeList.add("Semester");
        mExamTypeList.add("Unit Test 2");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.include_exam_date:
                final DatePickerDialog pickerDialog = new DatePickerDialog(mContext, R.style
                        .DialogTheme,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int
                                    dayOfMonth) {
                                mCalendar.set(Calendar.YEAR, year);
                                mCalendar.set(Calendar.MONTH, monthOfYear);
                                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                mYear = mCalendar.get(Calendar.YEAR);
                                mMonth = mCalendar.get(Calendar.MONTH);
                                mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
                                updateScreen();
                            }
                        }, mYear, mMonth, mDay);

                pickerDialog.setButton(DatePickerDialog.BUTTON_POSITIVE, getString(R.string.btn_ok),
                        pickerDialog);
                pickerDialog.show();
                break;
            case R.id.include_exam_type:
                showExamTypeSpinner();
                break;
            case R.id.include_exam_subject:
                showExamSubjectSpinner();
                break;
            case R.id.btn_submit:
                submitExam();
                break;
            case R.id.btn_cancel:
                finish();
                break;
            default:
                break;
        }

    }

    private void submitExam() {
        if(idDataValid())
        {
            finish();
        }
    }

    private boolean idDataValid() {
        StringBuilder errorEmpty = new StringBuilder();
        StringBuilder errorMarks = new StringBuilder();



        if(TextUtils.isEmpty(mExamDateValue.getText().toString()))
        {
            Utils.showErrorDialog(mContext,mContext.getResources().getString(R.string.error_empty_exam_date),null);
            return false;
        }

        if(TextUtils.isEmpty(txtExamTypeValue.getText().toString()))
        {
            Utils.showErrorDialog(mContext,mContext.getResources().getString(R.string.error_empty_exam_type),null);
            return false;
        }

        if(TextUtils.isEmpty(txtExamSubjectValue.getText().toString()))
        {
            Utils.showErrorDialog(mContext,mContext.getResources().getString(R.string.error_empty_subject),null);
            return false;
        }



        if(TextUtils.isEmpty(edtMaxMarks.getText().toString()))
        {
            Utils.showErrorDialog(mContext,mContext.getResources().getString(R.string.error_empty_maximum_marks),null);
            return false;
        }
        else
        {
            for(StudentExamModel studentExamModel:mStudentExamList)
            {
                if(studentExamModel.getMarksObtained()==0)
                {
                    if(errorEmpty.toString().isEmpty())
                    {
                        errorEmpty.append(studentExamModel.getRollNumber());
                    }
                    else
                    {
                        errorEmpty.append(Constants.COMMA+studentExamModel.getRollNumber());
                    }

                }
                else if(studentExamModel.getMarksObtained() > Integer.parseInt(edtMaxMarks.getText().toString()))
                {
                    if(errorMarks.toString().isEmpty())
                    {
                        errorMarks.append(studentExamModel.getRollNumber());
                    }
                    else
                    {
                        errorMarks.append(Constants.COMMA+studentExamModel.getRollNumber());
                    }
                }
            }
        }

        if(!errorEmpty.toString().isEmpty())
        {
            Utils.showErrorDialog(mContext,mContext.getResources().getString(R.string.error_empty_marks,errorEmpty.toString()),null);
            return false;
        }

        if(!errorMarks.toString().isEmpty())
        {
            Utils.showErrorDialog(mContext,mContext.getResources().getString(R.string.error_greater_than_max_marks,errorMarks.toString()),null);
            return false;
        }


        return true;
    }

    private void showExamSubjectSpinner() {
        Utils.showSpinnerPopup(mContext, getResources().getString(R.string.lbl_subject), mSubjectList, new PopupListCallback() {
            @Override
            public void getSelectedPosition(int position) {
            }

            @Override
            public void getSelectedItemValue(String value) {
                txtExamSubjectValue.setText(value);
            }
        });
    }

    private void showExamTypeSpinner() {

        Utils.showSpinnerPopup(mContext, getResources().getString(R.string.lbl_exam_type), mExamTypeList, new PopupListCallback() {
            @Override
            public void getSelectedPosition(int position) {
            }

            @Override
            public void getSelectedItemValue(String value) {
                txtExamTypeValue.setText(value);
            }
        });
    }

    private void updateScreen() {
        mExamDateValue.setText(mConstants.getSelectDateFormat()
                .format(mCalendar.getTime()));
    }

    @Override
    public void onItemClick(StudentExamModel studentExamModel) {

    }
}
