package attendance.com.schoolmaster.application;

import android.app.Application;

/**
 * Created by akmirajk on 2/25/2019.
 */

public class SchoolMasterApp extends Application {

    private static volatile SchoolMasterApp mSchoolMasterApp;
    @Override
    public void onCreate() {
        super.onCreate();
        mSchoolMasterApp = this;
    }

    public static SchoolMasterApp getInstance() {
        return mSchoolMasterApp;
    }
}

