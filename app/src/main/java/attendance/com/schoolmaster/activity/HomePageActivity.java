package attendance.com.schoolmaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.adapter.ClassListAdapter;
import attendance.com.schoolmaster.fragment.ClassListFragment;
import attendance.com.schoolmaster.fragment.DashboardFragment;
import attendance.com.schoolmaster.fragment.ViewEditAttendanceFragment;
import attendance.com.schoolmaster.model.ClasslstModel;

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
    public static final String VIEW_EDIT_ATTENDANCE = "view_edit_attendance";

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
            default:
                break;
        }

        mLytDashboardDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void redirectToViewEditAttendance() {
        mToolbar.setTitle(getString(R.string.menu_view_edit_attendance));
        Fragment viewEditAttendanceFragment = new ViewEditAttendanceFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.lyt_homepage, viewEditAttendanceFragment,VIEW_EDIT_ATTENDANCE).commit();
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
