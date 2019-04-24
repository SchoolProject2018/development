package attendance.com.schoolmaster.fragment;

import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.model.AttendanceReportModel;
import attendance.com.schoolmaster.utils.Constants;
import attendance.com.schoolmaster.utils.CreatePdf;

public class AttendanceReportFragment extends Fragment implements View.OnClickListener {

    private Button btnGenerateReport;
    private View mIncludeFromDate;
    private TextView mFromDateHeader;
    private TextView mFromDateValue;
    private TextView mToDateHeader;
    private TextView mToDateValue;
    private View mIncludeToDate;
    private List<AttendanceReportModel> mStudentList = new ArrayList<>();
    private Context mContext;
    private Calendar mFromCalendar;
    private Calendar mToCalendar;
    private Constants mConstants;
    final String path = Environment.getExternalStorageDirectory() +"/"+"PdfFiles";
    final String fileName = "attendanceReport.pdf";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_attendance_report, container, false);

        initView(rootview);
        prepareData();

        return rootview;
    }



    private void initView(View rootview) {
        mFromCalendar = Calendar.getInstance();
        mToCalendar = Calendar.getInstance();
        mConstants = new Constants();
        mContext = getActivity();
        btnGenerateReport = rootview.findViewById(R.id.btn_generate_report);
        btnGenerateReport.setOnClickListener(this);
        mIncludeFromDate = rootview.findViewById(R.id.include_from_date);
        mFromDateHeader = mIncludeFromDate.findViewById(R.id.txt_date_header);
        mFromDateValue = mIncludeFromDate.findViewById(R.id.txt_date_value);
        mIncludeFromDate.setOnClickListener(this);
        mIncludeToDate = rootview.findViewById(R.id.include_to_date);
        mToDateHeader = mIncludeToDate.findViewById(R.id.txt_date_header);
        mToDateValue = mIncludeToDate.findViewById(R.id.txt_date_value);
        mIncludeToDate.setOnClickListener(this);
    }

    private void prepareData() {

        mFromDateHeader.setText(R.string.lbl_from_date);
        mToDateHeader.setText(R.string.lbl_to_date);
        createPdfPath();
        setDummyData();

    }

    private void createPdfPath() {

        File dir = new File(path);
        if (!dir.exists()) {
            boolean isCreated = dir.mkdirs();
            Log.e("AM","Created"+isCreated);
        }
    }

    private void openPdf(boolean isSuccessful,String path, String fileName) {

        if(isSuccessful)
        {
            File pdfFile = new File(path +"/" +fileName);  // -> filename = maven.pdf
//            Uri uripath = Uri.fromFile(pdfFile);
            Uri photoURI = FileProvider.getUriForFile(mContext, mContext.getPackageName() + ".provider",pdfFile);
            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
            pdfIntent.setDataAndType(photoURI, "application/pdf");
            pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pdfIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

            try{
                startActivity(pdfIntent);
            }catch(ActivityNotFoundException e){
                Toast.makeText(mContext, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void setDummyData() {

        String[] sListArray = {"Deidra Knowles","Doria Quintanar","Annette Heinrich","Blaine Gheen","Jolynn Wetherington","January Galle","Zita Sweeting","Carin Legros","Ivonne Dragon","Eustolia Cambareri",
                "Margareta Fukushima","Doloris Melson","Sacha Hughley","Penelope Fulop","Ayana Daughdrill","Pamela Alcon","Kirk Mclean","Twanna Spindler","Tanisha Mcdonagh","Delicia Meredith"};
        String[] rollNoArray = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","'18","19","20"};

        Random rand = new Random();
        int max = 23;
        int min=1;
        for (int i = 0; i < sListArray.length; i++) {

            int present = rand.nextInt((max - min) + 1) + min;
            AttendanceReportModel cls = new AttendanceReportModel( rollNoArray[i],sListArray[i],present,max-present);
            mStudentList.add(cls);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_generate_report:
                CreatePdf pdf = new CreatePdf();
                boolean isSuccessful = pdf.createAttendancePdf(path,fileName,mStudentList);
                openPdf(isSuccessful,path,fileName);
                break;

            case R.id.include_from_date:
                final DatePickerDialog pickerDialog = new DatePickerDialog(mContext, R.style
                        .DialogTheme,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int
                                    dayOfMonth) {
                                mFromCalendar.set(Calendar.YEAR, year);
                                mFromCalendar.set(Calendar.MONTH, monthOfYear);
                                mFromCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                setFromDate();
                            }
                        }, mFromCalendar.get(Calendar.YEAR), mFromCalendar.get(Calendar.MONTH), mFromCalendar.get(Calendar.DAY_OF_MONTH));

                pickerDialog.setButton(DatePickerDialog.BUTTON_POSITIVE, getString(R.string.btn_ok),
                        pickerDialog);
                pickerDialog.show();
                break;
            case R.id.include_to_date:
                final DatePickerDialog pickerToDialog = new DatePickerDialog(mContext, R.style
                        .DialogTheme,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int
                                    dayOfMonth) {
                                mToCalendar.set(Calendar.YEAR, year);
                                mToCalendar.set(Calendar.MONTH, monthOfYear);
                                mToCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                setToDate();
                            }
                        }, mToCalendar.get(Calendar.YEAR), mToCalendar.get(Calendar.MONTH), mToCalendar.get(Calendar.DAY_OF_MONTH));

                pickerToDialog.setButton(DatePickerDialog.BUTTON_POSITIVE, getString(R.string.btn_ok),
                        pickerToDialog);
                pickerToDialog.show();
                break;
             default:
                 break;
        }
    }

    private void setToDate() {
        mToDateValue.setText(mConstants.getSelectDateFormat()
                .format(mToCalendar.getTime()));
    }

    private void setFromDate() {
        mFromDateValue.setText(mConstants.getSelectDateFormat()
                .format(mFromCalendar.getTime()));
    }

}
