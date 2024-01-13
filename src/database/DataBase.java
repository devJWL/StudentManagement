package database;

import resources.Student;
import resources.SubjectScore;
import util.options.*;
import util.subject.MandatorySubject;
import util.subject.SelectSubject;

import java.util.*;


public class DataBase {
    // 모든 정보가 저장
    // 데이터베이스에 있는 값들로 조회를 한다.
    public DataBase() {
        databaseInit();
    }

    // ============================= 데이터 베이스 초기화 및 출력을 위한 자료 =======================================
    private final List<String> mandatorySubjectNameList = new ArrayList<>();   // 모든 필수과목 List
    private final List<String> selectSubjectNameList = new ArrayList<>();      // 모든 선택과목 List
    private final List<String> studentStatusList = new ArrayList<>();     // 모든 감정상태를 가지고 있는 List


    public List<String> getMandatorySubjecNametList() {
        return mandatorySubjectNameList;
    }
    public List<String> getSelectSubjectNameList() {
        return selectSubjectNameList;
    }
    public List<String> getStudentStatusList() {
        return studentStatusList;
    }


    // ============================= 데이터 베이스 저장관련 자료 =======================================
    // key : studentId + subjectName    value : 과목이름
    private final Set<String> subjectSet = new HashSet<>();   // 특정학생의 과목 수강여부를 확인하기위한 Set
    private final List<Student> studentList = new ArrayList<>();  // 등록된 모든 학생을 가지고 있는 List
    // key : studentId + subjectName   value : 해당id학생의 해당과목의 회차별 점수 목록
    private final Map<String, List<SubjectScore>> subjectScoreMap = new HashMap<>();
    // key : 상태                      value : 학생리스트            상태별 학생리스트
    private final Map<String, List<Student>> studentByStatusMap = new HashMap<>();


    public Set<String> getSubjectSet() {
        return subjectSet;
    }
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


    // ================================== 메뉴 관련 EnumClass HashMap =============================
    private final Map<Integer, MainMenuOption>  mainMenuOptionMap = new HashMap<>();
    private final Map<Integer, StudentMenuOption> studentMenuOptionMap = new HashMap<>();
    private final Map<Integer, StudentRegisterMenuOption> studentRegisterMenuOptionMap = new HashMap<>();
    private final Map<Integer, StudentInquireMenuOption> studentInquireMenuOptionMap = new HashMap<>();
    private final Map<Integer, ScoreMenuOption> scoreMenuOptionMap = new HashMap<>();
    private final Map<Integer, ScoreRegisterMenuOption> scoreRegisterMenuOptionMap = new HashMap<>();
    private final Map<Integer, ScoreInquireMenuOption> scoreInquireMenuOptionMap = new HashMap<>();
    private final Map<Integer, ScoreChangeMenuOption> scoreChangeMenuOptionMap = new HashMap<>();
    private final Map<Integer, YesOrNoOption> yesOrNoOptionMap = new HashMap<>();


    public Map<Integer, MainMenuOption> getMainMenuOptionMap() {
        return mainMenuOptionMap;
    }
    public Map<Integer, StudentMenuOption> getStudentMenuOptionMap() {
        return studentMenuOptionMap;
    }
    public Map<Integer, StudentRegisterMenuOption> getStudentRegisterMenuOptionMap() {
        return studentRegisterMenuOptionMap;
    }
    public Map<Integer, StudentInquireMenuOption> getStudentInquireMenuOptionMap() {
        return studentInquireMenuOptionMap;
    }
    public Map<Integer, ScoreMenuOption> getScoreMenuOptionMap() {
        return scoreMenuOptionMap;
    }

    public Map<Integer, ScoreRegisterMenuOption> getScoreRegisterMenuOptionMap() {
        return scoreRegisterMenuOptionMap;
    }

    public Map<Integer, ScoreInquireMenuOption> getScoreInquireMenuOptionMap() {
        return scoreInquireMenuOptionMap;
    }

    public Map<Integer, ScoreChangeMenuOption> getScoreChangeMenuOptionMap() {
        return scoreChangeMenuOptionMap;
    }

    public Map<Integer, YesOrNoOption> getYesOrNoOptionMap() {
        return yesOrNoOptionMap;
    }
    // ================================== 메뉴 관련 EnumClass HashMap =============================


    // ================================== 과목 EnumClass HashMap ============================
    private final Map<Integer, MandatorySubject> mandatorySubjectMap = new HashMap<>();
    private final Map<Integer, SelectSubject> selectSubjectMap = new HashMap<>();
    private final Map<MandatorySubject, String> mandatorySubjectStringMap = new HashMap<>();
    private final Map<SelectSubject, String> selectSubjectStringMap = new HashMap<>();


    public Map<Integer, MandatorySubject> getMandatorySubjectMap() {
        return mandatorySubjectMap;
    }
    public Map<Integer, SelectSubject> getSelectSubjectMap() {
        return selectSubjectMap;
    }
    public Map<MandatorySubject, String> getMandatorySubjectStringMap() {
        return mandatorySubjectStringMap;
    }
    public Map<SelectSubject, String> getSelectSubjectStringMap() {
        return selectSubjectStringMap;
    }
    // ================================== 과목 EnumClass HashMap ============================

    // ================================== 수강생 상태 EnumClass HashMap ============================
    private final Map<Integer, StudentStatus> studentStatusMap = new HashMap<>();
    private final Map<StudentStatus, String> studentStatusStringMap = new HashMap<>();

    public Map<Integer, StudentStatus> getStudentStatusMap() {
        return studentStatusMap;
    }

    public Map<StudentStatus, String> getStudentStatusStringMap() {
        return studentStatusStringMap;
    }

    // ================================== 수강생 상태 EnumClass HashMap ============================
    // 데이터 베이스에 새로운 정보 추가
    private void addData() {

    }

    private void testInput() {

    }

    private void databaseInit() {
        //============================= 과목 관련 초기화==================================
        mandatorySubjectNameList.addAll(List.of("Error", "Java", "OOP", "Spring", "JPA", "MySQL"));
        selectSubjectNameList.addAll(List.of ("Error", "Design Pattern", "Spring Security", "Redis", "MongoDB"));

        //============================= 과목 관련 초기화==================================


        //============================= 감정상태 관련 초기화 ==================================
        studentStatusList.addAll(List.of("Error", "Green", "Yellow", "Red"));
        for (int i = 1; i < studentStatusList.size(); ++i) {
            studentByStatusMap.put(studentStatusList.get(i), new ArrayList<>());
        }
        //============================= 감정상태 관련 초기화 ==================================

        enumClassInit();
        testInput();
    }

    private void enumClassInit()  {
        // ================================== 메뉴 관련 EnumClass HashMap 초기화=============================
        MainMenuOption[] mainMenuOptions = MainMenuOption.values();
        for (int i = 0; i < mainMenuOptions.length; ++i) {
            mainMenuOptionMap.put(i, mainMenuOptions[i]);
        }

        StudentMenuOption[] studentMenuOptions = StudentMenuOption.values();
        for (int i = 0; i < studentMenuOptions.length; ++i) {
            studentMenuOptionMap.put(i, studentMenuOptions[i]);
        }

        StudentRegisterMenuOption[] studentRegisterMenuOptions = StudentRegisterMenuOption.values();
        for (int i = 0; i < studentRegisterMenuOptions.length; ++i) {
            studentRegisterMenuOptionMap.put(i, studentRegisterMenuOptions[i]);
        }
        StudentInquireMenuOption[] studentInquireMenuOptions = StudentInquireMenuOption.values();
        for (int i = 0; i < studentInquireMenuOptions.length; ++i) {
            studentInquireMenuOptionMap.put(i, studentInquireMenuOptions[i]);
        }
        ScoreMenuOption[] scoreMenuOptions = ScoreMenuOption.values();
        for (int i = 0; i < scoreMenuOptions.length; ++i) {
            scoreMenuOptionMap.put(i, scoreMenuOptions[i]);
        }

        ScoreRegisterMenuOption[] scoreRegisterMenuOptions = ScoreRegisterMenuOption.values();
        for (int i = 0; i < scoreRegisterMenuOptions.length; ++i) {
            scoreRegisterMenuOptionMap.put(i, scoreRegisterMenuOptions[i]);
        }

        ScoreInquireMenuOption[] scoreInquireMenuOptions = ScoreInquireMenuOption.values();
        for (int i = 0; i < scoreInquireMenuOptions.length; ++i) {
            scoreInquireMenuOptionMap.put(i, scoreInquireMenuOptions[i]);
        }
        ScoreChangeMenuOption[] scoreChangeMenuOptions = ScoreChangeMenuOption.values();
        for (int i = 0; i < scoreChangeMenuOptions.length; ++i) {
            scoreChangeMenuOptionMap.put(i, scoreChangeMenuOptions[i]);
        }
        // ================================== 메뉴 관련 EnumClass HashMap 초기화=============================


        // ================================== 과목 EnumClass HashMap 초기화============================
        MandatorySubject[] mandatorySubjects = MandatorySubject.values();
        for (int i = 0; i < mandatorySubjects.length; ++i) {
            mandatorySubjectMap.put(i, mandatorySubjects[i]);
            mandatorySubjectStringMap.put(mandatorySubjects[i], mandatorySubjectNameList.get(i));
        }
        SelectSubject[] selectSubjects = SelectSubject.values();
        for (int i = 0; i < selectSubjects.length; ++i) {
            selectSubjectMap.put(i, selectSubjects[i]);
            selectSubjectStringMap.put(selectSubjects[i], selectSubjectNameList.get(i));
        }
        // ================================== 과목 EnumClass HashMap 초기화============================


        // ================================== 수강생 상태 EnumClass HashMap 초기화============================

        StudentStatus[] studentStatuses = StudentStatus.values();
        for (int i = 0; i < studentStatuses.length; ++i) {
            studentStatusMap.put(i, studentStatuses[i]);
            studentStatusStringMap.put(studentStatuses[i], studentStatusList.get(i));
        }
        // ================================== 수강생 상태 EnumClass HashMap 초기화============================
        YesOrNoOption[] yesOrNoOptions = YesOrNoOption.values();
        for (int i = 0; i < yesOrNoOptions.length; ++i) {
            yesOrNoOptionMap.put(i, yesOrNoOptions[i]);
        }
    }
}
