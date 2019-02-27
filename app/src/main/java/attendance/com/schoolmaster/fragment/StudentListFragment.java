package attendance.com.schoolmaster.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.activity.StudentDetailActivity;
import attendance.com.schoolmaster.adapter.StudentListAdapter;
import attendance.com.schoolmaster.model.StudentModel;

public class StudentListFragment extends Fragment implements StudentListAdapter.OnItemClickListener {



    private RecyclerView mRvStudents;
    private List<StudentModel> mStudentList;
    private StudentListAdapter mStudentListAdapter;
    private Activity mActivity;


    String[] sListArray = {"Master Clark", "Master Natalie", "Master Bruce", "Master Robin", "Master Optimus"};
    String[] rollNoArray = {"1","2","3","4","5"};
    String[] genderArray = {"Gender: Male","Gender:Female","Gender:Male","Gender:Male","Gender:Male"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_student_list, container, false);

        initView(rootview);
        prepareData();


        return rootview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
    }

    private void initView(View rootview) {
        mStudentList = new ArrayList<>();
        mActivity = getActivity();
        mRvStudents = rootview.findViewById(R.id.rv_students);
        mRvStudents.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    private void prepareData()
    {
        setDummyData();
        mStudentListAdapter = new StudentListAdapter(mStudentList,mActivity,this);
        mRvStudents.setAdapter(mStudentListAdapter);
        mStudentListAdapter.notifyDataSetChanged();
    }

    private void setDummyData() {

        for (int i = 0; i < sListArray.length; i++) {
            StudentModel cls = new StudentModel(sListArray[i], rollNoArray[i],"Fourth","A");
            mStudentList.add(cls);
        }
    }

    @Override
    public void onItemClick(StudentModel studentModel) {
        Intent intent = new Intent(mActivity, StudentDetailActivity.class);
        startActivity(intent);
    }
}
