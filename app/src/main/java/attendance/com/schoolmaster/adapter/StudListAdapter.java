package attendance.com.schoolmaster.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.model.StudlstModel;

public class StudListAdapter extends RecyclerView.Adapter<StudListAdapter.ViewHolder> {

    private List<StudlstModel> studList;

    public StudListAdapter(List<StudlstModel> sList){
        this.studList = sList;
    }

    @Override
    public StudListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_stud_item, null);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(StudListAdapter.ViewHolder holder, int position) {

        StudlstModel cls = studList.get(position);
        holder.txt_cid.setText(cls.getStudentName());
        holder.txt_numStud.setText(cls.getRollNo());
        holder.txt_gender.setText(cls.getsGender());
        holder.txt_hobbies.setText(cls.getsHobbies());
    }

    @Override
    public int getItemCount() {
        return studList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_cid, txt_hobbies, txt_numStud, txt_gender;

        public ViewHolder(View v) {
            super(v);
            txt_cid = v.findViewById(R.id.classId);
            txt_hobbies = v.findViewById(R.id.hobbies);
            txt_numStud = v.findViewById(R.id.noOfStudent);
            txt_gender = v.findViewById(R.id.gender);
        }
    }
}