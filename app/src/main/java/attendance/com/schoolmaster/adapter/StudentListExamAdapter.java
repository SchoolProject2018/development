package attendance.com.schoolmaster.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.model.StudentExamModel;

public class StudentListExamAdapter extends RecyclerView.Adapter<StudentListExamAdapter.ViewHolder>  {

    private List<StudentExamModel> mStudentExamList;
    private Context mContext;
    private StudentListExamAdapter.OnItemClickListener mOnItemClickListener;

    public StudentListExamAdapter(List<StudentExamModel> studentExamList, Context context, StudentListExamAdapter.OnItemClickListener onItemClickListener){
        mStudentExamList = studentExamList;
        mContext = context;
        mOnItemClickListener = onItemClickListener;

    }

    public interface OnItemClickListener {
        void onItemClick(StudentExamModel studentExamModel);
    }

    @Override
    public StudentListExamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_exam_list_item, parent,false);
        StudentListExamAdapter.ViewHolder viewHolder = new StudentListExamAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StudentListExamAdapter.ViewHolder holder, int position) {

        holder.bind(mStudentExamList.get(position),mOnItemClickListener);

    }

    @Override
    public int getItemCount() {
        return mStudentExamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        private TextView txtRollNumber;
        private TextView txtStudentName;
        private View view;
        private EditText edtMarksObtained;

        public ViewHolder(View v) {
            super(v);
            view = v;
            txtRollNumber = view.findViewById(R.id.txt_roll_no);
            txtStudentName = view.findViewById(R.id.txt_student_name);
            edtMarksObtained = view.findViewById(R.id.edt_marks_obtained);
            edtMarksObtained.addTextChangedListener(mMarksObtainedTextWatcher);
        }

        public void bind(final StudentExamModel item, final StudentListExamAdapter.OnItemClickListener listener){

            txtRollNumber.setText(item.getRollNumber());
            txtStudentName.setText(item.getStudentName());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }


        private final TextWatcher mMarksObtainedTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                final int position = getAdapterPosition();
                if(TextUtils.isEmpty(editable.toString()))
                {
                    mStudentExamList.get(position).setMarksObtained(0);
                }
                else
                {
                    mStudentExamList.get(position).setMarksObtained(Integer.parseInt(editable.toString()));
                }
            }
        };
    }




}
