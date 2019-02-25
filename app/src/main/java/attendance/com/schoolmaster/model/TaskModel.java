package attendance.com.schoolmaster.model;

import attendance.com.schoolmaster.enums.TaskCategory;

/**
 * Created by akmirajk on 2/23/2019.
 */

public class TaskModel {

    private boolean isCompleted;
    private String taskTime;
    private String taskDesc;
    private TaskCategory taskCategory;


    public TaskModel(boolean isCompleted, String taskTime, String taskDesc,TaskCategory taskCategory) {
        this.isCompleted = isCompleted;
        this.taskTime = taskTime;
        this.taskDesc = taskDesc;
        this.taskCategory = taskCategory;
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

    public TaskCategory getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(TaskCategory taskCategory) {
        this.taskCategory = taskCategory;
    }
}
