package resources;
import java.util.*;


public class Student {
    public static int NO = 1;
    private String studentId;
    private String studentName;
    private String status;
    // 이 학생이 수강하고 있는 과목 목록
    private final List<String> subjectList;


    public Student(String studentName, String status, List<String> subjectList) {
        studentId = "STU" + NO;
        this.studentName = studentName;
        this.status = status;
        this.subjectList = new ArrayList<>(subjectList);
        ++NO;
    }

    public Student(Student other) {
        this.studentId = other.studentId;
        this.studentName = other.studentName;
        this.status = other.status;
        this.subjectList = new ArrayList<>(other.subjectList);
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getSubjectList() {
        return subjectList;
    }
}
