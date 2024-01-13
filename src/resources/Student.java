package resources;
import java.util.*;


public class Student {
    public static int NO = 1;
    private String studentId;
    private String studentName;
    private String status;
    // 이 학생이 수강하고 있는 과목 목록
    private final List<Subject> subjectList;


    public Student(String studentName, String status, List<Subject> subjectList) {
        studentId = "STU" + NO;
        this.studentName = studentName;
        this.status = status;
        this.subjectList = subjectList;
        ++NO;
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

    public List<Subject> getSubjectList() {
        return subjectList;
    }
}
