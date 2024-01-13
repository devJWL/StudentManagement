package database;

import resources.Student;
import resources.Subject;
import resources.SubjectScore;
import util.options.*;
import util.subject.MandatorySubject;
import util.subject.SelectSubject;

import java.util.*;


public class DataBase {
    // 모든 정보가 저장
    // 데이터베이스에 있는 값들로 조회를 한다.


    private final List<Subject> allSubjectList = new ArrayList<>();   // 모든 과목을 가지고있는 Set
    private final List<Subject> mandatorySubjectList = new ArrayList<>();   // 모든 필수과목 List
    private final List<Subject> selectSubjectList = new ArrayList<>();      // 모든 선택과목 List

    // key : studentId + subjectName    value : 과목이름
    private final Set<String> subjectSet = new HashSet<>();   // 특정학생의 과목 수강여부를 확인하기위한 Set

    private final List<Student> studentList = new ArrayList<>();  // 등록된 모든 학생을 가지고 있는 List
    // key : studentId + subjectName   value : 해당id학생의 해당과목의 회차별 점수 목록
    private final Map<String, List<SubjectScore>> subjectScoreMap = new HashMap<>();
    // key : 상태                      value : 학생리스트            상태별 학생리스트
    private final Map<String, List<Student>> studentByStatusMap = new HashMap<>();

    public DataBase() {
        databaseInit();
    }
    public List<Student> getStudentList() {
        return studentList;
    }
    public List<Subject> getAllSubjectSet() {
        return allSubjectList;
    }
    public List<Subject> getMandatorySubjectList() {
        return mandatorySubjectList;
    }
    public List<Subject> getSelectSubjectList() {
        return selectSubjectList;
    }

    public Set<String> getSubjectSet() {
        return subjectSet;
    }
    public Map<String, List<SubjectScore>> getSubjectScore() {
        return subjectScoreMap;
    }
    public Map<String, List<SubjectScore>> getSubjectScoreMap() {
        return subjectScoreMap;
    }
    public Map<String, List<Student>> getStudentByStatusMap() {
        return studentByStatusMap;
    }

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

    // 데이터 베이스에 새로운 정보 추가
    private void addData() {

    }

    private void testInput() {

    }

    private void databaseInit() {
        //============================= 과목 추가 ==================================
        allSubjectList.add(new Subject("Java", true));
        allSubjectList.add(new Subject("객체지향", true));
        allSubjectList.add(new Subject("Spring", true));
        allSubjectList.add(new Subject("JPA", true));
        allSubjectList.add(new Subject("MySQL", true));
        allSubjectList.add(new Subject("디자인_패턴", false));
        allSubjectList.add(new Subject("Spring_Security", false));
        allSubjectList.add(new Subject("Redis", false));
        allSubjectList.add(new Subject("MongoDB", false));
        for (Subject subject : allSubjectList) {
            if (subject.isMandatory()) {
                mandatorySubjectList.add(subject);
            }
            else {
                selectSubjectList.add(subject);
            }
        }
        //============================= 과목 추가 ==================================
        studentByStatusMap.put("Green", new ArrayList<Student>());
        studentByStatusMap.put("Yellow", new ArrayList<Student>());
        studentByStatusMap.put("Red", new ArrayList<Student>());
        enumClassInit();
        testInput();
    }

    private void enumClassInit()  {
        // ================================== 메뉴 관련 EnumClass HashMap =============================
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
        // ================================== 메뉴 관련 EnumClass HashMap =============================


        // ================================== 과목 EnumClass HashMap ============================
        MandatorySubject[] mandatorySubjects = MandatorySubject.values();
        for (int i = 0; i < mandatorySubjects.length; ++i) {
            mandatorySubjectMap.put(i, mandatorySubjects[i]);
        }
        SelectSubject[] selectSubjects = SelectSubject.values();
        for (int i = 0; i < selectSubjects.length; ++i) {
            selectSubjectMap.put(i, selectSubjects[i]);
        }

        for (int i = 0; i < mandatorySubjects.length; ++i) {
            mandatorySubjectStringMap.put(mandatorySubjects[i], mandatorySubjectList.get(i).getSubjectName());
        }
        for (int i = 0; i < selectSubjects.length; ++i) {
            selectSubjectStringMap.put(selectSubjects[i], selectSubjectList.get(i).getSubjectName());
        }
        // ================================== 과목 EnumClass HashMap ============================

        YesOrNoOption[] yesOrNoOptions = YesOrNoOption.values();
        for (int i = 0; i < yesOrNoOptions.length; ++i) {
            yesOrNoOptionMap.put(i, yesOrNoOptions[i]);
        }
    }
}
