package attendance.com.schoolmaster.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.model.ClasslstModel;

public class ClassListAdapter extends RecyclerView.Adapter<ClassListAdapter.ViewHolder> {

    private List<ClasslstModel> cList;

    //Provide a suitable constructor
    public ClassListAdapter(List<ClasslstModel> classList){
        this.cList = classList;
    }

    @Override
    public ClassListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_class_item, null);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ClassListAdapter.ViewHolder holder, int position) {

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

        TextView txt_cid, txt_rewards, txt_numStud;

        public ViewHolder(View v) {
            super(v);
            txt_cid = v.findViewById(R.id.classId);
            txt_rewards = v.findViewById(R.id.rewards);
            txt_numStud = v.findViewById(R.id.noOfStudent);
        }
    }
}