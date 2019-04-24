package attendance.com.schoolmaster.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.activity.AddExamActivity;
import attendance.com.schoolmaster.activity.AddTaskActivity;
import attendance.com.schoolmaster.adapter.ExamTestAdapter;
import attendance.com.schoolmaster.model.ExamTestModel;

/**
 * Created by akmirajk on 2/27/2019.
 */

public class AddEditPerformanceFragment extends Fragment implements ExamTestAdapter.OnItemClickListener,View.OnClickListener{


    private RecyclerView mRvExamTests;
    private List<ExamTestModel> mExamTestList;
    private ExamTestAdapter mExamTestAdapter;
    private Activity mActivity;
    private FloatingActionButton btnFloatingAdd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_add_edit_performance, container, false);

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

        mExamTestList = new ArrayList<>();
        mActivity = getActivity();
        mRvExamTests = rootview.findViewById(R.id.rv_exam_test);
        mRvExamTests.setLayoutManager(new LinearLayoutManager(mActivity));
        btnFloatingAdd = rootview.findViewById(R.id.btn_floating_add);
        btnFloatingAdd.setOnClickListener(this);
    }

    private void prepareData() {

        setDummyData();
        mExamTestAdapter = new ExamTestAdapter(mExamTestList,mActivity,this);
        mRvExamTests.setAdapter(mExamTestAdapter);
        mExamTestAdapter.notifyDataSetChanged();
    }

    private void setDummyData() {

        ExamTestModel e1 = new ExamTestModel("English",100,"English Unit Test",mActivity.getResources().getString(R.string.unit_test),"08/20/2018");
        ExamTestModel e2 = new ExamTestModel("Hindi",100,"Hindi Unit Test", mActivity.getResources().getString(R.string.unit_test),"08/21/2018");
        ExamTestModel e3 = new ExamTestModel("Science 1",50,"Science 1 Semester", mActivity.getResources().getString(R.string.semester),"10/01/2018");
        ExamTestModel e4 = new ExamTestModel("Maths 2",50,"Maths 2", mActivity.getResources().getString(R.string.semester),"10/02/2018");
        ExamTestModel e5 = new ExamTestModel("Social Studies",100,"Social Studies Unit Test", mActivity.getResources().getString(R.string.unit_test_two),"02/04/2019");
        ExamTestModel e6 = new ExamTestModel("Maths",100,"Maths Unit Test",  mActivity.getResources().getString(R.string.unit_test_two),"02/05/2019");
        ExamTestModel e7 = new ExamTestModel("English",100,"English Unit Test",  mActivity.getResources().getString(R.string.unit_test_two),"02/06/2019");

        mExamTestList.add(e1);
        mExamTestList.add(e2);
        mExamTestList.add(e3);
        mExamTestList.add(e4);
        mExamTestList.add(e5);
        mExamTestList.add(e6);
        mExamTestList.add(e7);

    }

    @Override
    public void onItemClick(ExamTestModel examTestModel) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_floating_add:
                redirectToAddExam();
                break;
            default:
                break;
        }
    }

    private void redirectToAddExam() {
        Intent i = new Intent(getActivity(), AddExamActivity.class);
        startActivity(i);
    }
}
