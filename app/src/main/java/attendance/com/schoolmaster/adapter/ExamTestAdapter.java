package attendance.com.schoolmaster.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.model.ExamTestModel;

/**
 * Created by akmirajk on 3/1/2019.
 */

public class ExamTestAdapter extends RecyclerView.Adapter<ExamTestAdapter.ViewHolder>  {

    private List<ExamTestModel> mExamTestList;
    private Context mContext;
    private ExamTestAdapter.OnItemClickListener mOnItemClickListener;

    public ExamTestAdapter(List<ExamTestModel> examTestList, Context context, ExamTestAdapter.OnItemClickListener onItemClickListener){
        mExamTestList = examTestList;
        mContext = context;
        mOnItemClickListener = onItemClickListener;

    }

    public interface OnItemClickListener {
        void onItemClick(ExamTestModel examTestModel);
    }

    @Override
    public ExamTestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_test_list_item, parent,false);
        ExamTestAdapter.ViewHolder viewHolder = new ExamTestAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ExamTestAdapter.ViewHolder holder, int position) {

        holder.bind(mExamTestList.get(position),mOnItemClickListener);

    }

    @Override
    public int getItemCount() {
        return mExamTestList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtTestDesc;
        private TextView txtTestTypeValue;
        private TextView txtMaxMarksValue;
        private TextView txtSubjectValue;
        private View view;

        public ViewHolder(View v) {
            super(v);
            view = v;
            txtTestDesc = view.findViewById(R.id.txt_test_desc);
            txtTestTypeValue = view.findViewById(R.id.txt_test_type_value);
            txtMaxMarksValue = view.findViewById(R.id.txt_max_marks_value);
            txtSubjectValue = view.findViewById(R.id.txt_subject_values);
        }

        public void bind(final ExamTestModel item, final ExamTestAdapter.OnItemClickListener listener){

            txtTestDesc.setText(item.getTestDecsription());
            txtTestTypeValue.setText(item.getTestType());
            txtMaxMarksValue.setText(String.valueOf(item.getMaxMarks()));
            txtSubjectValue.setText(item.getSubject());


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }
    }

}
