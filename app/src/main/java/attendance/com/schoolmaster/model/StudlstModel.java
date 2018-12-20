package attendance.com.schoolmaster.model;

public class StudlstModel {

    private String studentName;
    private String rollNo;
    private String sHobbies;
    private String sGender;

    public StudlstModel(String sName, String sRollNo, String hobbies, String gender)
    {
        this.studentName = sName;
        this.rollNo = sRollNo;
        this.sHobbies = hobbies;
        this.sGender = gender;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String getsHobbies() {
        return sHobbies;
    }

    public String getsGender() {
        return sGender;
    }
}
