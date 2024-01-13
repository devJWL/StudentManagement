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
    private final List<Student> studentList = new ArrayList<>();  // 등록된 모든 학생을 가지고 있는 List
    // key : studentId + subjectName   value : 해당id학생의 해당과목의 회차별 점수 목록
    private final Map<String, List<SubjectScore>> subjectScoreMap = new HashMap<>();
    // key : 상태                      value : 학생리스트            상태별 학생리스트
    private final Map<String, List<Student>> studentByStatusMap = new HashMap<>();

    public List<Student> getStudentList() {
        return studentList;
    }

    public Map<String, List<SubjectScore>> getSubjectScoreMap() {
        return subjectScoreMap;
    }

    public Map<String, List<Student>> getStudentByStatusMap() {
        return studentByStatusMap;
    }
    // ============================= 데이터 베이스 저장관련 자료 =======================================


    private void databaseInit() {
        //============================= 감정상태 관련 초기화 ==================================
        List<String> studentStatusList =valid.getStudentStatusList();
        studentStatusList.addAll(List.of("Error", "Green", "Yellow", "Red"));
        for (int i = 1; i < studentStatusList.size(); ++i) {
            studentByStatusMap.put(studentStatusList.get(i), new ArrayList<>());
        }
        //============================= 감정상태 관련 초기화 ==================================
        // ================================== 수강생 상태 EnumClass HashMap 초기화============================
        Map<Integer, StudentStatus> studentStatusMap = valid.getStudentStatusMap();
        Map<StudentStatus, String> studentStatusStringMap = valid.getStudentStatusStringMap();

        StudentStatus[] studentStatuses = StudentStatus.values();
        for (int i = 0; i < studentStatuses.length; ++i) {
            studentStatusMap.put(i, studentStatuses[i]);
            studentStatusStringMap.put(studentStatuses[i], studentStatusList.get(i));
        }
        // ================================== 수강생 상태 EnumClass HashMap 초기화============================
        TestInput();
    }

    private void TestInput() {}
}
