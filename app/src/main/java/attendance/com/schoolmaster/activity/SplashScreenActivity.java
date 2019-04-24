package attendance.com.schoolmaster.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import attendance.com.schoolmaster.R;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_TIME = 1500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

      init();
    }

    private void init() {
        Thread timer= new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(SPLASH_TIME);
                }
                catch (InterruptedException e)
                {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                finally
                {
                    //Goes to Activity  StartingPoint.java(STARTINGPOINT)
                    Intent homepageactivity=new Intent(SplashScreenActivity.this,HomePageActivity.class);
                    startActivity(homepageactivity);
                    finish();
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause()
    {
        // TODO Auto-generated method stub
        super.onPause();
        finish();

    }
}


