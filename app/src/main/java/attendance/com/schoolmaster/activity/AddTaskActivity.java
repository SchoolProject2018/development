package attendance.com.schoolmaster.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.utils.Constants;

/**
 * Created by akmirajk on 2/24/2019.
 */

public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView mTxtToolbarTitle;
    private Toolbar mToolbar;
    private View mIncludeTaskDate;
    private TextView mTaskDateHeader;
    private TextView mTaskDateValue;
    private View mIncludeTaskFromTime;
    private TextView mTaskFromTimeHeader;
    private TextView mTaskFromTimeValue;
    private ImageView mImgFromTime;
    private Calendar mFromCalendar;
    private View mIncludeTaskToTime;
    private TextView mTaskToTimeHeader;
    private TextView mTaskToTimeValue;
    private ImageView mImgToTime;
    private Calendar mToCalendar;
    private Calendar mCalendar;
    private int mDay;
    private int mMonth;
    private int mYear;
    private Constants mConstants;
    private Context mContext;
    private Button mBtnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        initView();
        prepareData();

    }

    private void initView() {
        mContext= this;
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTxtToolbarTitle = (TextView) findViewById(R.id.txt_toolbar_title);
        mConstants = new Constants();
        mIncludeTaskDate = findViewById(R.id.include_task_date);
        mTaskDateHeader = mIncludeTaskDate.findViewById(R.id.txt_date_header);
        mTaskDateValue = mIncludeTaskDate.findViewById(R.id.txt_date_value);
        mIncludeTaskDate.setOnClickListener(this);
        mIncludeTaskFromTime = findViewById(R.id.include_from_time);
        mTaskFromTimeHeader = mIncludeTaskFromTime.findViewById(R.id.txt_date_header);
        mTaskFromTimeValue = mIncludeTaskFromTime.findViewById(R.id.txt_date_value);
        mImgFromTime = mIncludeTaskFromTime.findViewById(R.id.img_date);
        mImgFromTime.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_time));
        mIncludeTaskFromTime.setOnClickListener(this);

        mIncludeTaskToTime = findViewById(R.id.include_to_time);
        mTaskToTimeHeader = mIncludeTaskToTime.findViewById(R.id.txt_date_header);
        mTaskToTimeValue = mIncludeTaskToTime.findViewById(R.id.txt_date_value);
        mImgToTime = mIncludeTaskToTime.findViewById(R.id.img_date);
        mImgToTime.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_time));
        mIncludeTaskToTime.setOnClickListener(this);

        mBtnSubmit = (Button) findViewById(R.id.btn_submit);
        mBtnSubmit.setOnClickListener(this);
    }

    private void prepareData() {

        mTxtToolbarTitle.setText(R.string.lbl_add_task);
        mCalendar = Calendar.getInstance();
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        mTaskDateHeader.setText(R.string.lbl_task_date);
        mTaskFromTimeHeader.setText(R.string.lbl_from_time);
        mTaskToTimeHeader.setText(R.string.lbl_to_time);
        mFromCalendar = Calendar.getInstance();
        mToCalendar = Calendar.getInstance();
        updateScreen();
        setFromTime();
        setToTime();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.include_task_date:
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
            case R.id.include_from_time:

                TimePickerDialog mFromTimePicker = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mFromCalendar.set(Calendar.HOUR_OF_DAY,selectedHour);
                        mFromCalendar.set(Calendar.MINUTE,selectedMinute);
                        setFromTime();
                    }
                }, mFromCalendar.get(Calendar.HOUR_OF_DAY), mFromCalendar.get(Calendar.MINUTE),false);
                mFromTimePicker.show();
                break;

            case R.id.include_to_time:
                TimePickerDialog mToTimePicker = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mToCalendar.set(Calendar.HOUR_OF_DAY,selectedHour);
                        mToCalendar.set(Calendar.MINUTE,selectedMinute);
                        setToTime();
                    }
                }, mToCalendar.get(Calendar.HOUR_OF_DAY), mToCalendar.get(Calendar.MINUTE),false);
                mToTimePicker.show();
                break;

            case R.id.btn_submit:
                finish();
                break;
            default:
                break;
        }
    }

    private void setFromTime() {
        mTaskFromTimeValue.setText(mConstants.getTimeFormat()
                .format(mFromCalendar.getTime()));
    }

    private void setToTime() {
        mTaskToTimeValue.setText(mConstants.getTimeFormat()
                .format(mToCalendar.getTime()));
    }


    private void updateScreen() {
            /**
             * In future this method will be used to reload schedule
             */
            mTaskDateValue.setText(mConstants.getSelectDateFormat()
                    .format(mCalendar.getTime()));
        }

}
