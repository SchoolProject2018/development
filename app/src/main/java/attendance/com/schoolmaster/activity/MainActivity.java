package attendance.com.schoolmaster.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.fragment.Academics;
import attendance.com.schoolmaster.fragment.Attendance;

public class MainActivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView mBottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
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
        Fragment academicsFragment = new Academics();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main, academicsFragment);
        ft.commit();
    }

    private void navigateToAttendance() {

            Fragment attendanceFragment = new Attendance();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main, attendanceFragment);
            ft.commit();
    }
}
