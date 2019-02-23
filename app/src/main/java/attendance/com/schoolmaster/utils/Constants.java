package attendance.com.schoolmaster.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by akmirajk on 2/20/2019.
 */

public class Constants {

    public final SimpleDateFormat attendanceDateFormat = new SimpleDateFormat("E MMMM dd,yyyy hh:mm a");
    public final SimpleDateFormat selectDateFormat = new SimpleDateFormat("E MMMM dd,yyyy", Locale.ENGLISH);


    public SimpleDateFormat getAttendanceDateFormat() {
        return attendanceDateFormat;
    }

    public SimpleDateFormat getSelectDateFormat() {
        return selectDateFormat;
    }
}
