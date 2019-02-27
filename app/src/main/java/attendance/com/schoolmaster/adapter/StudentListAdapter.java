package attendance.com.schoolmaster.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.model.StudentModel;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolder> {

    private List<StudentModel> mStudentList;
    private Context mContext;
    private StudentListAdapter.OnItemClickListener mOnItemClickListener;

    public StudentListAdapter(List<StudentModel> studentList,Context context, StudentListAdapter.OnItemClickListener onItemClickListener){
        mStudentList = studentList;
        mContext = context;
        mOnItemClickListener = onItemClickListener;

    }

    public interface OnItemClickListener {
        void onItemClick(StudentModel studentModel);
    }

    @Override
    public StudentListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_item, parent,false);
        StudentListAdapter.ViewHolder viewHolder = new StudentListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StudentListAdapter.ViewHolder holder, int position) {

        holder.bind(mStudentList.get(position),mOnItemClickListener);

    }

    @Override
    public int getItemCount() {
        return mStudentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgPhoto;
        private TextView txtRollNumber;
        private TextView txtStudentName;
        private TextView txtStandard;
        private TextView txtDivisison;
        private View view;

        public ViewHolder(View v) {
            super(v);
            view = v;
            imgPhoto = view.findViewById(R.id.img_student_photo);
            txtRollNumber = view.findViewById(R.id.txt_roll_number_value);
            txtStudentName = view.findViewById(R.id.txt_student_name);
            txtStandard = view.findViewById(R.id.txt_standard_value);
            txtDivisison = view.findViewById(R.id.txt_division_value);
        }

            public void bind(final StudentModel item, final StudentListAdapter.OnItemClickListener listener){

            txtRollNumber.setText(item.getRollNumber());
            txtStudentName.setText(item.getStudentName());
            txtStandard.setText(item.getStandard());
            txtDivisison.setText(item.getDivision());
            setStudentImage();

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onItemClick(item);
                    }
                });
        }

        private void setStudentImage() {
            try
            {
                // get input stream
                InputStream ims = mContext.getAssets().open("student.jpg");
                // load image as Drawable
                Drawable d = Drawable.createFromStream(ims, null);
                // set image to ImageView
                imgPhoto.setImageDrawable(d);
                ims .close();
            }
            catch(IOException ex)
            {
                return;
            }
        }



    }


}