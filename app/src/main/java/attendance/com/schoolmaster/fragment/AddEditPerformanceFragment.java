package attendance.com.schoolmaster.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import attendance.com.schoolmaster.R;

/**
 * Created by akmirajk on 2/27/2019.
 */

public class AddEditPerformanceFragment extends Fragment {

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
    }

    private void prepareData() {
    }
}
