package attendance.com.schoolmaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.adapter.ClassListAdapter;
import attendance.com.schoolmaster.model.ClasslstModel;

import static java.security.AccessController.getContext;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,ClassListAdapter.OnItemClickListener{

    private TextView mTxtDate;
//    private ListView mLstLectureList;
    private DrawerLayout mLytDashboardDrawer;
    private NavigationView mNavigationViewDashboard;
    private Toolbar mToolbar;
    private TextView  mTxtToolbarTitle;
    private RecyclerView mRvClass;
    private ClassListAdapter mClassAdapter;
    private List<ClasslstModel> mClassesList;

    String[] cListArray = {"Class A", "Class B", "Class C", "Class D", "Class E","Class F","Class G"};
    String[] numArray = {"Number of Students : 45","Number of Students : 60","Number of Students : 55",
            "Number of Students : 75","Number of Students : 50","Number of Students : 75","Number of Students : 50","Number of Students : 75","Number of Students : 50"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_drawer);

        initView();
        prepareData();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTxtToolbarTitle = (TextView) findViewById(R.id.txt_toolbar_title);
        mLytDashboardDrawer = (DrawerLayout) findViewById(R.id.lyt_dashboard_drawer);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mLytDashboardDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mLytDashboardDrawer.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationViewDashboard = (NavigationView) findViewById(R.id.navigation_view_dashboard);
        mNavigationViewDashboard.setNavigationItemSelectedListener(this);
        mTxtDate = (TextView) findViewById(R.id.dashboard_txt_date);
//        mLstLectureList = (ListView) findViewById(R.id.dashboard_lst_classes);

        mRvClass = (RecyclerView) findViewById(R.id.rv_class);
        mRvClass.setLayoutManager( new LinearLayoutManager(this));
        mRvClass.setNestedScrollingEnabled(false);
        mClassesList = new ArrayList<>();

    }

    private void prepareData() {
        mTxtToolbarTitle.setText(R.string.lbl_dashboard);
        mTxtDate.setText(getCurrentDate());

//        ArrayAdapter<String> lectureAdapter = new ArrayAdapter<String>(this, R.layout.lst_lecture_item,
//                R.id.dashboard_txt_lecture_list, getLectureList());
//        mLstLectureList.setAdapter(lectureAdapter);
//
//        mLstLectureList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
//
//                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(mainIntent);
//            }
//        });


        for (int i = 0; i < cListArray.length; i++) {
            ClasslstModel cls = new ClasslstModel(cListArray[i], "Rewards: none", numArray[i]);
            mClassesList.add(cls);
        }

        mClassAdapter = new ClassListAdapter(mClassesList,this);
        mRvClass.setAdapter(mClassAdapter);
        mClassAdapter.notifyDataSetChanged();
    }

    public String getCurrentDate()
    {
        String datePattern = "E dd-MM-yyyy";
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat scDateFormat = new SimpleDateFormat(datePattern, Locale.ENGLISH);
        return scDateFormat.format(calendar.getTime());
    }

    public String[] getLectureList()
    {
        return new String[]{"10:10AM CLASS A Batch","11:10AM CLASS D Batch","04:10AM CLASS E Batch"};
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_dashboard:
                redirectToDashboard();
                break;
            case R.id.nav_view_edit_attendance:
                break;
            case R.id.nav_classes:
                redirectToClasses();
                break;
            case R.id.nav_logout:
                finish();
            default:
                break;
        }

        mLytDashboardDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void redirectToClasses() {
        Intent i = new Intent(this,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    private void redirectToDashboard() {
        Intent i = new Intent(this,DashboardActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Override
    public void onItemClick(ClasslstModel classlstModel) {

        Intent i = new Intent(DashboardActivity.this,ProfileActivity.class);
        i.putExtra("classlstModel",(Serializable)classlstModel);
        startActivity(i);

    }

    @Override
    public void onBtnAttendanceClick(ClasslstModel classlstModel) {
        Intent i = new Intent(DashboardActivity.this,RecordAttendanceActivity.class);
        i.putExtra("classlstModel",(Serializable)classlstModel);
        startActivity(i);
    }
}
