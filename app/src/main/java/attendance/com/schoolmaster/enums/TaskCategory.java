package attendance.com.schoolmaster.enums;

import java.io.Serializable;

import attendance.com.schoolmaster.R;
import attendance.com.schoolmaster.application.SchoolMasterApp;

/**
 * Created by akmirajk on 2/25/2019.
 */

public enum TaskCategory  implements Serializable {
    HIGN(0x001) {
        @Override
        public String toString() {
            return SchoolMasterApp.getInstance().getString(R.string.btn_high);
        }
    }, NORMAL(0x002) {
        @Override
        public String toString() {
            return SchoolMasterApp.getInstance().getString(R.string.btn_normal);
        }
    }, LOW(0x003) {
        @Override
        public String toString() {
            return SchoolMasterApp.getInstance().getString(R.string.btn_low);
        }
    },STANDARD(0x004) {
        @Override
        public String toString() {
            return SchoolMasterApp.getInstance().getString(R.string.btn_standard);
        }
    };

    private final int taskCategoryCode;

        TaskCategory(final int taskCategoryCode) {
        this.taskCategoryCode = taskCategoryCode;
    }

    public int getTaskCategoryCode() {
        return this.taskCategoryCode;
    }
}
