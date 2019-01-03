package attendance.com.schoolmaster.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.model.ClasslstModel;
import attendance.com.schoolmaster.model.StudlstModel;

/**
 * Created by akmirajk on 12/31/2018.
 */

public class RecordAttendanceAdapter  extends RecyclerView.Adapter<RecordAttendanceAdapter.ViewHolder> {

    private List<StudlstModel> studentList;

    public RecordAttendanceAdapter(List<StudlstModel> studentList){
        this.studentList = studentList;
    }

    @Override
    public RecordAttendanceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_student_list_item, null);

        RecordAttendanceAdapter.ViewHolder vh = new RecordAttendanceAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecordAttendanceAdapter.ViewHolder holder, int position) {

        StudlstModel student = studentList.get(position);
        holder.txtRollNo.setText(student.getRollNo());
        holder.txtStudentName.setText(student.getStudentName());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtRollNo;
        private TextView txtStudentName;

        public ViewHolder(View v) {
            super(v);
            txtRollNo = v.findViewById(R.id.txt_roll_no);
            txtStudentName = v.findViewById(R.id.txt_student_name);
        }
    }
}
