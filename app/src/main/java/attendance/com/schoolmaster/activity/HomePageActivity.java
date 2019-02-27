package attendance.com.schoolmaster.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import attendance.com.schoolmaster.R;
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
    public  static final String DASHBOARD_TAG = "dashboard_tag";
    public  static final String CLASSES_TAG = "classes_tag";
    public static final String VIEW_EDIT_ATTENDANCE_TAG = "view_edit_attendance_tag";
    public static final String MY_SCHEDULE_TAG ="my_schedule_tag";
    public static final String STUDENT_LIST_TAG = "student_list_tag";

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
            default:
                break;
        }

        mLytDashboardDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void redirectToStudents() {
        mToolbar.setTitle(getString(R.string.menu_students));
        Fragment studentListFragment = new StudentListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.lyt_homepage, studentListFragment, VIEW_EDIT_ATTENDANCE_TAG).commit();

    }

    private void redirectToMySchedule() {
        mToolbar.setTitle(getString(R.string.menu_my_schedule));
        Fragment myScheduleFragment = new MyScheduleFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.lyt_homepage, myScheduleFragment, VIEW_EDIT_ATTENDANCE_TAG).commit();
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
}
