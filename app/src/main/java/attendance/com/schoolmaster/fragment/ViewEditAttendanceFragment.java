package attendance.com.schoolmaster.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.adapter.RecordAttendanceAdapter;
import attendance.com.schoolmaster.model.StudentAttendanceModel;
import attendance.com.schoolmaster.utils.Constants;

/**
 * Created by akmirajk on 2/21/2019.
 */

public class ViewEditAttendanceFragment extends Fragment implements View.OnClickListener{

    private View mIncludeAttendanceDate;
    private TextView mAttendanceDateHeader;
    private TextView mAttendanceDateValue;
    private Activity mActivity;
    private Calendar mCalendar;
    private int mDay;
    private int mMonth;
    private int mYear;
    private Constants mConstants;
    private RecyclerView rvStudentList;
    private RecordAttendanceAdapter recordAttendanceAdapter;
    private List<StudentAttendanceModel> mAttendanceList;
    private boolean mAttendanceListEditable;
    private Button mBtnSubmit;
    private Button mBtnCancel;
    private ImageView mImgedit;
    private LinearLayout mLytButton;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_view_edit_attendance, container, false);
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
        mConstants = new Constants();
        mIncludeAttendanceDate = rootview.findViewById(R.id.include_attendance_date);
        mAttendanceDateHeader = mIncludeAttendanceDate.findViewById(R.id.txt_date_header);
        mAttendanceDateValue = mIncludeAttendanceDate.findViewById(R.id.txt_date_value);
        mIncludeAttendanceDate.setOnClickListener(this);
        mAttendanceList = new ArrayList<>();
        rvStudentList = (RecyclerView) rootview.findViewById(R.id.rv_student_list);
        rvStudentList.setLayoutManager(new LinearLayoutManager(mActivity));
        rvStudentList.setNestedScrollingEnabled(false);
        mBtnSubmit = (Button) rootview.findViewById(R.id.btn_submit);
        mBtnSubmit.setOnClickListener(this);
        mImgedit = rootview.findViewById(R.id.img_edit);
        mImgedit.setOnClickListener(this);
        mBtnCancel = rootview.findViewById(R.id.btn_cancel);
        mBtnCancel.setOnClickListener(this);
        mLytButton = rootview.findViewById(R.id.lyt_button);
    }

    private void prepareData()
    {
        mAttendanceListEditable = false;
        mCalendar = Calendar.getInstance();
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        mAttendanceDateHeader.setText(R.string.lbl_attendance_date);
        setStudentDummyData();
        setButtonVisibility();
        setAttendanceAdapter();
    }

    private void setAttendanceAdapter() {
        recordAttendanceAdapter = new RecordAttendanceAdapter(mAttendanceList,mActivity,mAttendanceListEditable);
        rvStudentList.setAdapter(recordAttendanceAdapter);
        recordAttendanceAdapter.notifyDataSetChanged();
    }

    private void setButtonVisibility() {
        if (mAttendanceListEditable) {
            mLytButton.setVisibility(View.VISIBLE);
        } else {
            mLytButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //Add Product or Update product
            case R.id.include_attendance_date:
                final DatePickerDialog pickerDialog = new DatePickerDialog(mActivity, R.style
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

                pickerDialog.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
                pickerDialog.setButton(DatePickerDialog.BUTTON_POSITIVE, getString(R.string.btn_ok),
                        pickerDialog);
                pickerDialog.show();
                break;

            case R.id.img_edit:
                mAttendanceListEditable = true;
                setButtonVisibility();
                setAttendanceAdapter();
                break;
            case R.id.btn_submit:case R.id.btn_cancel:
                mAttendanceListEditable = false;
                setButtonVisibility();
                setAttendanceAdapter();
                break;
            default:
                break;
        }

    }

    private void updateScreen() {
        //In future this method will be used to reload the student list
        mAttendanceDateValue.setText(mConstants.getSelectDateFormat()
                .format(mCalendar.getTime()));
    }


    private void setStudentDummyData() {

        StudentAttendanceModel s1 = new StudentAttendanceModel("Abhishek Menon","1",true,false,false,"");
        StudentAttendanceModel s2 = new StudentAttendanceModel("Akshaya Kute","2",true,true,false,"");
        StudentAttendanceModel s3 = new StudentAttendanceModel("Mukesh Kumar","3",true,false,true,"Feeling Sick");
        StudentAttendanceModel s4 = new StudentAttendanceModel("Shrikant Kelkar","4",true,false,false,"");
        StudentAttendanceModel s5 = new StudentAttendanceModel("Preeti Shah","5",true,false,false,"");
        StudentAttendanceModel s6 = new StudentAttendanceModel("Kamlesh Mhatre","6",false,false,false,"Out of Station");
        StudentAttendanceModel s7 = new StudentAttendanceModel("Suresh Mangi","7",true,false,false,"");
        StudentAttendanceModel s8 = new StudentAttendanceModel("Devendra Rane","8",true,false,false,"");
        StudentAttendanceModel s9 = new StudentAttendanceModel("Karthik Nair","9",true,false,false,"");
        StudentAttendanceModel s10 = new StudentAttendanceModel("Narendra Vani","10",false,false,false,"Sick");
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
}
