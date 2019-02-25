package attendance.com.schoolmaster.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.enums.TaskCategory;
import attendance.com.schoolmaster.model.TaskModel;

/**
 * Created by akmirajk on 2/23/2019.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<TaskModel> mTaskList;
    private Context mContext;
//    private TaskAdapter.OnItemClickListener mOnItemClickListener;

    public TaskAdapter(List<TaskModel> tasklist,Context context){
        this.mTaskList = tasklist;
        this.mContext = context;
//        this.mOnItemClickListener = onItemClickListener;
    }

//    public interface OnItemClickListener {
//        void onItemClick(TaskModel travelSummary);
//    }

    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int
            viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_item, parent,false);
        TaskAdapter.ViewHolder viewHolder = new TaskAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        holder.bind(mTaskList.get(position));
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CheckBox chkTask;
        private TextView txtTaskTime;
        private TextView txtTaskDesc;
        private ImageView imgTaskCategory;
        private View view;


        public ViewHolder(View v) {
            super(v);
            view = v;
            chkTask = view.findViewById(R.id.chk_task);
            txtTaskTime = view.findViewById(R.id.txt_task_time);
            txtTaskDesc = view.findViewById(R.id.txt_task_desc);
            imgTaskCategory = view.findViewById(R.id.img_task_category);

        }

        public void bind(final TaskModel item) {
            txtTaskTime.setText(item.getTaskTime());
            txtTaskDesc.setText(item.getTaskDesc());

            if (item.getTaskCategory().equals(TaskCategory.HIGN)) {
                imgTaskCategory.setBackground(ContextCompat.getDrawable(mContext, R.drawable
                        .bg_left_rounded_high));
            } else if (item.getTaskCategory().equals(TaskCategory.NORMAL)) {
                imgTaskCategory.setBackground(ContextCompat.getDrawable(mContext, R.drawable
                        .bg_left_rounded_normal));
            } else if (item.getTaskCategory().equals(TaskCategory.LOW)) {
                imgTaskCategory.setBackground(ContextCompat.getDrawable(mContext, R.drawable
                        .bg_left_rounded_low));
            } else {
                imgTaskCategory.setBackground(ContextCompat.getDrawable(mContext, R.drawable
                        .bg_left_rounded_standard));
            }
        }
    }
}
