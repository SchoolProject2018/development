package attendance.com.schoolmaster.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.model.ClasslstModel;

public class ClassListAdapter extends RecyclerView.Adapter<ClassListAdapter.ViewHolder> {

    private List<ClasslstModel> cList;
    private final OnItemClickListener onItemClickListener;

    //Provide a suitable constructor
    public ClassListAdapter(List<ClasslstModel> classList, OnItemClickListener onItemClickListener){
        this.cList = classList;
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(ClasslstModel classlstModel);

        void onBtnAttendanceClick(ClasslstModel classlstModel);
    }

    @Override
    public ClassListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_class_item, null);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ClassListAdapter.ViewHolder holder, int position) {
        holder.bind(cList.get(position), onItemClickListener);
        ClasslstModel cls = cList.get(position);
        holder.txt_cid.setText(cls.getName());
        holder.txt_numStud.setText(cls.getStudNo());
        holder.txt_rewards.setText(cls.getRewards());

    }

    @Override
    public int getItemCount() {
        return cList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final View mView;
        private TextView txt_cid, txt_rewards, txt_numStud;
        private Button mBtnAttendance;

        public ViewHolder(View v) {
            super(v);
            mView = v;
            txt_cid = v.findViewById(R.id.classId);
            txt_rewards = v.findViewById(R.id.rewards);
            txt_numStud = v.findViewById(R.id.noOfStudent);
            mBtnAttendance = v.findViewById(R.id.btn_attendance);

//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                    Intent i = new Intent(view.getContext(), ProfileActivity.class);
////                    view.getContext().startActivity(i);
//                }
//            });
        }

        public void bind(final ClasslstModel item, final OnItemClickListener listener) {

            txt_cid.setText(item.getName());
            txt_numStud.setText(item.getStudNo());
            txt_rewards.setText(item.getRewards());


            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

            mBtnAttendance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onBtnAttendanceClick(item);
                }
            });
        }
    }

}