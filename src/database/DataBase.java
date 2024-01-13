package database;

import resources.Student;
import resources.SubjectScore;
import util.Valid;
import util.options.StudentStatus;

import java.util.*;


public class DataBase {
    private final Valid valid;
    public DataBase(Valid valid) {
        this.valid = valid;
        databaseInit();
    }

    // ============================= 데이터 베이스 저장관련 자료 =======================================
    private final Set<Student> studentSet = new HashSet<>();  // 등록된 모든 학생을 가지고 있는 Set
    // key : studentId            value : Student
    private final Map<String, Student> studentByIdMap = new HashMap<>();
    // key : 상태                      value : 학생리스트            상태별 학생리스트
    private final Map<String, List<Student>> studentByStatusMap = new HashMap<>();
    // key : studentId + subjectName   value : 해당id학생의 해당과목의 회차별 점수 목록
    private final Map<String, List<SubjectScore>> subjectScoreMap = new HashMap<>();

    public Set<Student> getStudentSet() {
        return studentSet;
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
        List<String> validStudentStatusList = valid.getStudentStatusList();
        for (String status : validStudentStatusList) {
            studentByStatusMap.put(status, new ArrayList<>());
        }
        //============================= 감정상태 관련 초기화 ==================================
        TestInput();
    }

    private void TestInput() {}
}
