package attendance.com.schoolmaster.model;

public class AttendanceReportModel {
    private String rollNumber;
    private String studentName;
    private int noOfPresent;
    private int noOfAbsents;

    public AttendanceReportModel(String rollNumber, String studentName, int noOfPresent,
                                 int noOfAbsents) {
        this.rollNumber = rollNumber;
        this.studentName = studentName;
        this.noOfPresent = noOfPresent;
        this.noOfAbsents = noOfAbsents;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getNoOfPresent() {
        return noOfPresent;
    }

    public void setNoOfPresent(int noOfPresent) {
        this.noOfPresent = noOfPresent;
    }

    public int getNoOfAbsents() {
        return noOfAbsents;
    }

    public void setNoOfAbsents(int noOfAbsents) {
        this.noOfAbsents = noOfAbsents;
    }
}
