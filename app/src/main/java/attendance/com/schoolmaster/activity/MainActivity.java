package attendance.com.schoolmaster.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.adapter.TabFragmentAdapter;
import attendance.com.schoolmaster.fragment.ClassListFragment;
import attendance.com.schoolmaster.fragment.RecentListFragment;
import attendance.com.schoolmaster.fragment.StudListFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView mBottomNavigationView;

    private TabLayout tablayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tablayout = (TabLayout) findViewById(R.id.tab_layout);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        TabFragmentAdapter fragAdapter = new TabFragmentAdapter(getSupportFragmentManager());
        fragAdapter.addFragment(new ClassListFragment(), "Class");
        fragAdapter.addFragment(new StudListFragment(), "Students");
        fragAdapter.addFragment(new RecentListFragment(), "Recents");

        viewPager.setAdapter(fragAdapter);
        tablayout.setupWithViewPager(viewPager);

        getSupportActionBar().hide();

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == 0) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            R.color.color_tab1));
                    tablayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            R.color.color_tab1));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                                R.color.color_tab1));
                    }
                } else if (tab.getPosition() == 1) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            R.color.color_tab2));
                    tablayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            R.color.color_tab2));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                                R.color.color_tab2));
                    }
                } else if (tab.getPosition() == 2) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            R.color.color_tab3));
                    tablayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                            R.color.color_tab3));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                                R.color.color_tab3));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        /*ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.listview_row, listArray);
        ListView mlistView = (ListView)findViewById(R.id.list);
        mlistView.setAdapter(adapter);

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);*/
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_attendance:
                navigateToAttendance();
                break;
            case R.id.navigation_academics:
                navigateToAcademics();
                break;
            default:
                break;

        }
        return true;
    }

    private void navigateToAcademics() {
        /*Fragment academicsFragment = new Academics();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main, academicsFragment);
        ft.commit();*/
    }

    private void navigateToAttendance() {

            /*Fragment attendanceFragment = new Attendance();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main, attendanceFragment);
            ft.commit();*/
    }
}
