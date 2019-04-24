package attendance.com.schoolmaster.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.fragment.AddEditPerformanceFragment;
import attendance.com.schoolmaster.fragment.AttendanceReportFragment;
import attendance.com.schoolmaster.fragment.ClassListFragment;
import attendance.com.schoolmaster.fragment.DashboardFragment;
import attendance.com.schoolmaster.fragment.MyScheduleFragment;
import attendance.com.schoolmaster.fragment.StudentListFragment;
import attendance.com.schoolmaster.fragment.ViewEditAttendanceFragment;

/**
 * Created by akmirajk on 1/18/2019.
 */

public class HomePageActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{


    //    private ListView mLstLectureList;
    private DrawerLayout mLytDashboardDrawer;
    private NavigationView mNavigationViewDashboard;
    private Toolbar mToolbar;
    private TextView  mTxtToolbarTitle;
    public static final String TAG = "HomePageActivity";
    public  static final String DASHBOARD_TAG = "dashboard_tag";
    public  static final String CLASSES_TAG = "classes_tag";
    public static final String VIEW_EDIT_ATTENDANCE_TAG = "view_edit_attendance_tag";
    public static final String MY_SCHEDULE_TAG ="my_schedule_tag";
    public static final String STUDENT_LIST_TAG = "student_list_tag";
    public static final String ADD_EDIT_PERFORMANCE_TAG = "add_edit_performance_tag";
    public static final String ATTENDANCE_REPORT_TAG = "attendance_report_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_drawer);

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
        mNavigationViewDashboard.setItemIconTintList(null);
        mNavigationViewDashboard.setNavigationItemSelectedListener(this);


    }

    private void prepareData() {
        redirectToDashboard();
        checkPermission();
    }

    private void checkPermission() {
        checkReadStoragePermissionGranted();
        checkWriteStoragePermissionGranted();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_dashboard:
                redirectToDashboard();
                break;
            case R.id.nav_view_edit_attendance:
                redirectToViewEditAttendance();
                break;
            case R.id.nav_classes:
                redirectToClasses();
                break;
            case R.id.nav_logout:
                finish();

            case R.id.nav_my_schedule:
                redirectToMySchedule();
                break;
            case R.id.nav_studnets:
                redirectToStudents();
                break;
            case R.id.nav_add_edit_performance:
                redirectToAddEditPerformance();
                break;
            case R.id.nav_attendance_report:
                redirectToAttendanceReport();
                break;
            default:
                break;
        }

        mLytDashboardDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void redirectToAttendanceReport() {
        mToolbar.setTitle(R.string.menu_attendance_report);
        Fragment attendanceReportFragment = new AttendanceReportFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.lyt_homepage, attendanceReportFragment,ATTENDANCE_REPORT_TAG ).commit();

    }

    private void redirectToAddEditPerformance() {
        mToolbar.setTitle(R.string.menu_add_edit_performance);
        Fragment addEditPerformanceFragment = new AddEditPerformanceFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.lyt_homepage, addEditPerformanceFragment, ADD_EDIT_PERFORMANCE_TAG).commit();

    }

    private void redirectToStudents() {
        mToolbar.setTitle(getString(R.string.menu_students));
        Fragment studentListFragment = new StudentListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.lyt_homepage, studentListFragment, STUDENT_LIST_TAG).commit();

    }

    private void redirectToMySchedule() {
        mToolbar.setTitle(getString(R.string.menu_my_schedule));
        Fragment myScheduleFragment = new MyScheduleFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.lyt_homepage, myScheduleFragment, MY_SCHEDULE_TAG).commit();
    }

    private void redirectToViewEditAttendance() {
        mToolbar.setTitle(getString(R.string.menu_view_edit_attendance));
        Fragment viewEditAttendanceFragment = new ViewEditAttendanceFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.lyt_homepage, viewEditAttendanceFragment,
                VIEW_EDIT_ATTENDANCE_TAG).commit();
    }


    private void redirectToClasses() {
        mToolbar.setTitle(getString(R.string.menu_classes));
        Fragment classListFragment = new ClassListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.lyt_homepage, classListFragment,CLASSES_TAG).commit();
    }

    private void redirectToDashboard() {
        mToolbar.setTitle(getString(R.string.menu_dashboard));
        Fragment dashboardFragment = new DashboardFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.lyt_homepage, dashboardFragment,DASHBOARD_TAG).commit();
    }


    public  boolean checkReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted1");
            return true;
        }
    }

    public  boolean checkWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted2");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked2");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted2");
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 2:
                Log.d(TAG, "External storage");
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);

                }
                break;

            case 3:
                Log.d(TAG, "External storage");
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
                }
                break;
        }
    }
}
