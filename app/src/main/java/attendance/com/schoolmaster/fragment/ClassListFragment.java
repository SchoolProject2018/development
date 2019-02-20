package attendance.com.schoolmaster.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.activity.DashboardActivity;
import attendance.com.schoolmaster.activity.ProfileActivity;
import attendance.com.schoolmaster.activity.RecordAttendanceActivity;
import attendance.com.schoolmaster.adapter.ClassListAdapter;
import attendance.com.schoolmaster.model.ClasslstModel;

public class ClassListFragment extends Fragment implements ClassListAdapter.OnItemClickListener {

    private View view;
    String[] cListArray = {"Class A", "Class B", "Class C", "Class D", "Class E","Class F","Class G"};
    String[] numArray = {"Number of Students : 45","Number of Students : 60","Number of Students : 55",
            "Number of Students : 75","Number of Students : 50","Number of Students : 75","Number of Students : 50","Number of Students : 75","Number of Students : 50"};

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ClasslstModel> classDetList;
    private ClassListAdapter classAdapter;
    private Activity mActivity;

    public ClassListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_class_list, container, false);
        mActivity = getActivity();
        setRecyclerView();

        return view;
    }

    private void setRecyclerView()
    {
        mRecyclerView = view.findViewById(R.id.recycleClassView);

        classDetList = new ArrayList<>();

//        if (mRecyclerView != null) {
//            mRecyclerView.setHasFixedSize(true);
//        }

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        for (int i = 0; i < cListArray.length; i++) {
            ClasslstModel cls = new ClasslstModel(cListArray[i], "Rewards: none", numArray[i]);
            classDetList.add(cls);
        }

        classAdapter = new ClassListAdapter(classDetList,this);
        mRecyclerView.setAdapter(classAdapter);
        classAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(ClasslstModel classlstModel) {
        Intent i = new Intent(mActivity,ProfileActivity.class);
        i.putExtra("classlstModel",(Serializable)classlstModel);
        startActivity(i);
    }

    @Override
    public void onBtnAttendanceClick(ClasslstModel classlstModel) {
        Intent i = new Intent(getActivity(),RecordAttendanceActivity.class);
        i.putExtra("classlstModel",(Serializable)classlstModel);
        startActivity(i);
    }
}
