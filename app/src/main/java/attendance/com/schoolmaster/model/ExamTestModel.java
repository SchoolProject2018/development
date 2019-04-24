package attendance.com.schoolmaster.model;

/**
 * Created by akmirajk on 3/1/2019.
 */

public class ExamTestModel {

    private String id;
    private String subject;
    private int maxMarks;
    private String testDecsription;
    private String testType;
    private String testDate;

    public ExamTestModel(String subject, int maxMarks, String testDecsription, String testType,
                         String testDate) {
        this.subject = subject;
        this.maxMarks = maxMarks;
        this.testDecsription = testDecsription;
        this.testType = testType;
        this.testDate = testDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(int maxMarks) {
        this.maxMarks = maxMarks;
    }

    public String getTestDecsription() {
        return testDecsription;
    }

    public void setTestDecsription(String testDecsription) {
        this.testDecsription = testDecsription;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }
}
