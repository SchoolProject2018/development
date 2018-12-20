package attendance.com.schoolmaster.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.adapter.ClassListAdapter;
import attendance.com.schoolmaster.adapter.StudListAdapter;
import attendance.com.schoolmaster.model.ClasslstModel;
import attendance.com.schoolmaster.model.StudlstModel;

public class StudListFragment extends Fragment {

    private View view;
    String[] sListArray = {"Master Clark", "Master Natalie", "Master Bruce", "Master Robin", "Master Optimus"};
    String[] rollNoArray = {"RollNo : 45","RollNo : 60","RollNo : 55",
            "RollNo : 20","RollNo : 1"};
    String[] genderArray = {"Gender: Male","Gender:Female","Gender:Male","Gender:Male","Gender:Male"};

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<StudlstModel> studDetList;
    private StudListAdapter studAdapter;

    public StudListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_stud_list, container, false);
        setRecyclerView();

        return view;
    }

    private void setRecyclerView()
    {
        mRecyclerView = view.findViewById(R.id.recycleStudView);

        studDetList = new ArrayList<>();

        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
        }

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        for (int i = 0; i < sListArray.length; i++) {
            StudlstModel cls = new StudlstModel(sListArray[i], rollNoArray[i],"Hobbies: Reading, Swimming, Cricket",
                    genderArray[i]);
            studDetList.add(cls);
        }

        studAdapter = new StudListAdapter(studDetList);
        mRecyclerView.setAdapter(studAdapter);
        studAdapter.notifyDataSetChanged();
    }
}
