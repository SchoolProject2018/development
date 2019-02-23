package attendance.com.schoolmaster.model;

/**
 * Created by akmirajk on 2/22/2019.
 */

public class StudentAttendanceModel {

    private String studentName;
    private String rollNo;
    private boolean isPresent;
    private boolean isLate;
    private boolean isLeftEarly;
    private String Note;

    public StudentAttendanceModel(String studentName,String rollNo,  boolean isPresent, boolean
            isLate, boolean isLeftEarly, String note) {
        this.studentName = studentName;
        this.rollNo = rollNo;
        this.isPresent = isPresent;
        this.isLate = isLate;
        this.isLeftEarly = isLeftEarly;
        Note = note;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public boolean isLate() {
        return isLate;
    }

    public void setLate(boolean late) {
        isLate = late;
    }

    public boolean isLeftEarly() {
        return isLeftEarly;
    }

    public void setLeftEarly(boolean leftEarly) {
        isLeftEarly = leftEarly;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }
}
