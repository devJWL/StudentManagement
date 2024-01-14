package database;

import resources.Student;
import resources.SubjectScore;
import util.options.StudentStatus;
import java.util.*;


public class Database {
    public Database() {
        databaseInit();
    }

    // ============================= 데이터 베이스 저장관련 자료 =======================================
    // key : studentId + subjectName    value : 과목이름
    private final Set<String> subjectSet = new HashSet<>();   // 특정학생의 과목 수강여부를 확인하기위한 Set
    // key : studentId            value : Student
    private final Map<String, Student> studentByIdMap = new TreeMap<>();
    // key : 상태                      value : 학생리스트            상태별 학생리스트
    private final Map<String, List<Student>> studentByStatusMap = new HashMap<>();
    // key : studentId + subjectName   value : 해당id학생의 해당과목의 회차별 점수 목록
    private final Map<String, List<SubjectScore>> subjectScoreMap = new HashMap<>();

    public Set<String> getSubjectSet() {
        return subjectSet;
    }
    public Map<String, Student> getStudentByIdMap() {
        return studentByIdMap;
    }
    public Map<String, List<Student>> getStudentByStatusMap() {
        return studentByStatusMap;
    }
    public Map<String, List<SubjectScore>> getSubjectScoreMap() {
        return subjectScoreMap;
    }
    // ============================= 데이터 베이스 저장관련 자료 =======================================


    private void databaseInit() {
        //============================= 감정상태 관련 초기화 ==================================
            List<String> statusStringList = StudentStatus.getStatusStringList();
            for (String status : statusStringList) {
                studentByStatusMap.put(status, new ArrayList<>());
            }
        //============================= 감정상태 관련 초기화 ==================================
        TestInput();
    }



    // 학생 등록
    public void save (String studentId, String studentName, String status, List<String> subjectList) {
        Student student = new Student(studentName, status, subjectList);
        studentByIdMap.put(studentId, student);
        studentByStatusMap.get(status).add(student);
    }

    // 학생 정보 변경
    public void change (String studentId, String studentName, String status) {
        Student student = studentByIdMap.get(studentId);
        if (!student.getStatus().equals(status)) {
            studentByStatusMap.get(student.getStatus()).remove(student);
            student.setStatus(status);
            studentByStatusMap.get(status).add(student);
        }
        if (!student.getStudentName().equals(studentName)) {
            student.setStudentName(studentName);
        }
    }

    // 학생 삭제
    public void delete (Student student) {
        String studentId = student.getStudentId();
        studentByStatusMap.get(student.getStatus()).remove(student);
        for (String subjectName : student.getSubjectList()) {
            String key = studentId + subjectName;
            subjectSet.remove(key);
            subjectScoreMap.remove(key);
        }
        studentByIdMap.remove(studentId);
    }
    //
//    // 점수 등록
//    private void inputSubjectScore(String key, List<SubjectScore> subjectScoreList) {
//        for (SubjectScore subjectScore : subjectScoreList) {
//            subjectScoreMap.get(key).add(subjectScore);
//        }
//    }
    private void TestInput() {}
}
