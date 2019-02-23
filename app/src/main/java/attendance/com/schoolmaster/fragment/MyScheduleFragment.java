package attendance.com.schoolmaster.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.activity.RecordAttendanceActivity;
import attendance.com.schoolmaster.adapter.TaskAdapter;
import attendance.com.schoolmaster.model.TaskModel;
import attendance.com.schoolmaster.utils.Constants;

/**
 * Created by akmirajk on 2/23/2019.
 */

public class MyScheduleFragment extends Fragment implements View.OnClickListener {

    private View mIncludeScheduleDate;
    private TextView mScheduleDateHeader;
    private TextView mScheduleDateValue;
    private Calendar mCalendar;
    private int mDay;
    private int mMonth;
    private int mYear;
    private Constants mConstants;
    private Activity mActivity;
    private Button mBtnMarkAttendance;
    private RecyclerView mRvTaskList;
    private TaskAdapter mTaskAdapter;
    private List<TaskModel> mTaskList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_myschedule, container, false);
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
        mIncludeScheduleDate = rootview.findViewById(R.id.include_schedule_date);
        mScheduleDateHeader = mIncludeScheduleDate.findViewById(R.id.txt_date_header);
        mScheduleDateValue = mIncludeScheduleDate.findViewById(R.id.txt_date_value);
        mIncludeScheduleDate.setOnClickListener(this);
        mBtnMarkAttendance = (Button) rootview.findViewById(R.id.btn_mark_attendance);
        mBtnMarkAttendance.setOnClickListener(this);
        mRvTaskList = (RecyclerView) rootview.findViewById(R.id.rv_task_list);
        mRvTaskList.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvTaskList.setNestedScrollingEnabled(false);
    }

    private void prepareData()
    {
        mCalendar = Calendar.getInstance();
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        mScheduleDateHeader.setText(R.string.lbl_schedule_date);
        mTaskList = new ArrayList<>();
        updateScreen();
        setTaskDummyData();
        setTaskAdapter();
    }

    private void setTaskAdapter() {
        mTaskAdapter = new TaskAdapter(mTaskList);
        mRvTaskList.setAdapter(mTaskAdapter);
        mTaskAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            //Add Product or Update product
            case R.id.include_schedule_date:
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

                pickerDialog.setButton(DatePickerDialog.BUTTON_POSITIVE, getString(R.string.btn_ok),
                        pickerDialog);
                pickerDialog.show();
                break;
            case R.id.btn_mark_attendance:
                redirectToRecordAttendance();
                break;
            default:
                break;
        }
    }

    void redirectToRecordAttendance()
    {
        Intent i = new Intent(getActivity(),RecordAttendanceActivity.class);
        startActivity(i);
    }


        private void updateScreen() {
            /**
             * In future this method will be used to reload schedule
             */
            mScheduleDateValue.setText(mConstants.getSelectDateFormat()
                    .format(mCalendar.getTime()));
        }

    private void setTaskDummyData() {
        TaskModel t1 = new TaskModel(false,"7:30 AM To 8:00 AM","Science Lecture for Fourth A");
        TaskModel t2 = new TaskModel(false,"8:30 AM To 9:00 AM","Chemisrty Lecture for Ninth B");
        TaskModel t3 = new TaskModel(false,"11:30 AM To 12:00 AM","Science Lecture for Seventh A");
        TaskModel t4 = new TaskModel(false,"12:00 AM To 12:30 AM","Chemistry Lecture for Ninth A");

        mTaskList.add(t1);
        mTaskList.add(t2);
        mTaskList.add(t3);
        mTaskList.add(t4);

    }
}
