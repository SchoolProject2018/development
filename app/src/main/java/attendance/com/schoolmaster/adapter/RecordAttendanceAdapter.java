package attendance.com.schoolmaster.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.util.List;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.model.StudentAttendanceModel;

/**
 * Created by akmirajk on 12/31/2018.
 */

public class RecordAttendanceAdapter  extends RecyclerView.Adapter<RecordAttendanceAdapter.ViewHolder> {

    private List<StudentAttendanceModel> mAttendanceList;
    private Context mCotext;
    private boolean mIsEditable;

    public RecordAttendanceAdapter(List<StudentAttendanceModel> attendanceList, Context context,boolean isEditable){
        mAttendanceList = attendanceList;
        mCotext = context;
        mIsEditable = isEditable;
    }

    @Override
    public RecordAttendanceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_student_list_item, parent,false);
        RecordAttendanceAdapter.ViewHolder viewHolder = new RecordAttendanceAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecordAttendanceAdapter.ViewHolder holder, int position) {

        StudentAttendanceModel attendanceModel = mAttendanceList.get(position);
        holder.txtRollNo.setText(attendanceModel.getRollNo());
        holder.txtStudentName.setText(attendanceModel.getStudentName());

        if(mIsEditable)
        {
            holder.imgLate.setEnabled(true);
            if(attendanceModel.isLate())
            {
                holder.imgLate.setImageResource(R.drawable.ic_late_yellow);
            }
            else
            {
                holder.imgLate.setImageResource(R.drawable.ic_late_black);
            }

            holder.imgLeftEarly.setEnabled(true);

            if(attendanceModel.isLeftEarly())
            {
                holder.imgLeftEarly.setImageResource(R.drawable.ic_left_early_blue);
            }
            else
            {
                holder.imgLeftEarly.setImageResource(R.drawable.ic_left_early_black);
            }
            holder.edtNote.setEnabled(true);
            holder.edtNote.setText(attendanceModel.getNote());
            holder.switchAttendance.setVisibility(View.VISIBLE);
            holder.txtAttendance.setVisibility(View.GONE);
            if(attendanceModel.isPresent())
            {
                holder.switchAttendance.setChecked(true);
            }
            else
            {
                holder.switchAttendance.setChecked(false);
            }
        }
        else
        {
            holder.imgLate.setEnabled(false);
            if(attendanceModel.isLate())
            {
                holder.imgLate.setImageResource(R.drawable.ic_late_yellow);
            }
            else
            {
                holder.imgLate.setImageResource(R.drawable.ic_late_black);
            }

            holder.imgLeftEarly.setEnabled(false);
            if(attendanceModel.isLeftEarly())
            {
                holder.imgLeftEarly.setImageResource(R.drawable.ic_left_early_blue);
            }
            else
            {
                holder.imgLeftEarly.setImageResource(R.drawable.ic_left_early_black);
            }
            holder.edtNote.setEnabled(false);
            holder.edtNote.setText(attendanceModel.getNote());
            holder.switchAttendance.setVisibility(View.GONE);
            holder.txtAttendance.setVisibility(View.VISIBLE);
            if(attendanceModel.isPresent())
            {
                holder.txtAttendance.setText(R.string.lbl_present);
                holder.txtAttendance.setTextColor(mCotext.getResources().getColor(R.color.color_switch_on_green));
            }
            else
            {
                holder.txtAttendance.setText(R.string.lbl_absent);
                holder.txtAttendance.setTextColor(mCotext.getResources().getColor(R.color.color_switch_off_red));
            }


        }
    }

    @Override
    public int getItemCount() {
        return mAttendanceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtRollNo;
        private TextView txtStudentName;
        private ImageView imgLate;
        private ImageView imgLeftEarly;
        private SwitchCompat switchAttendance;
        private EditText edtNote;
        private TextView txtAttendance;

        public ViewHolder(View v) {
            super(v);
            txtRollNo = v.findViewById(R.id.txt_roll_no);
            txtStudentName = v.findViewById(R.id.txt_student_name);
            imgLate = v.findViewById(R.id.img_late);
            imgLeftEarly = v.findViewById(R.id.img_left_early);
            switchAttendance = v.findViewById(R.id.switch_attendance);
            edtNote = v.findViewById(R.id.edt_note);
            txtAttendance = v.findViewById(R.id.txt_attendance);

            imgLate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(imgLate.getDrawable().getConstantState() == mCotext.getResources().getDrawable( R.drawable.ic_late_black).getConstantState())
                    {
                        imgLate.setImageResource(R.drawable.ic_late_yellow);
                    }
                    else
                    {
                        imgLate.setImageResource(R.drawable.ic_late_black);
                    }
                }
            });


            imgLeftEarly.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(imgLeftEarly.getDrawable().getConstantState() == mCotext.getResources().getDrawable( R.drawable.ic_left_early_black).getConstantState())
                    {
                        imgLeftEarly.setImageResource(R.drawable.ic_left_early_blue);
                    }
                    else
                    {
                        imgLeftEarly.setImageResource(R.drawable.ic_left_early_black);
                    }
                }
            });

            switchAttendance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if(isChecked)
                    {
                        switchAttendance.setText(R.string.lbl_present);
                    }
                    else
                    {
                        switchAttendance.setText(R.string.lbl_absent);
                    }
                }
            });

        }
    }
}
