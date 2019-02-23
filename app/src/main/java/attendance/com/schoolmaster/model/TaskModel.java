package attendance.com.schoolmaster.model;

/**
 * Created by akmirajk on 2/23/2019.
 */

public class TaskModel {

    private boolean isCompleted;
    private String taskTime;
    private String taskDesc;

    public TaskModel(boolean isCompleted, String taskTime, String taskDesc) {
        this.isCompleted = isCompleted;
        this.taskTime = taskTime;
        this.taskDesc = taskDesc;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }
}
