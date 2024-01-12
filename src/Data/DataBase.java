package Data;

import Resources.Student;
import Resources.Subject;
import Resources.SubjectScore;
import Util.Options.*;
import Util.Subject.MandatorySubject;
import Util.Subject.SelectSubject;

import java.util.*;


public class DataBase {
    // 모든 정보가 저장
    // 데이터베이스에 있는 값들로 조회를 한다.

    // 등록된 학생 목록
    private final List<Student> studentList = new ArrayList<>();

    // 모든 과목 목록
    private final Set<Subject> subjectList = new HashSet<>();

    // key : studentId + subjectName    value : 과목이름
    private final Set<String> subjectSet = new HashSet<>();
    
    // key : studentId + subjectName   value : 해당id학생의 해당과목의 회차별 점수 목록
    private final Map<String, List<SubjectScore>> subjectScoreMap = new HashMap<>();

    // key : 상태                      value : 학생리스트            상태별 학생리스트
    private final Map<String, List<Student>> studentByStatusMap = new HashMap<>();


    // ================================== 메뉴 관련 EnumClass HashMap =============================
    private final Map<Integer, MainMenuOption>  mainMenuOptionMap = new HashMap<>();
    private final Map<Integer, StudentMenuOption> studentMenuOptionMap = new HashMap<>();
    private final Map<Integer, StudentRegisterMenuOption> studentRegisterMenuOptionMap = new HashMap<>();
    private final Map<Integer, StudentInquireMenuOption> studentInquireMenuOptionMap = new HashMap<>();
    private final Map<Integer, ScoreMenuOption> scoreMenuOptionMap = new HashMap<>();
    private final Map<Integer, ScoreRegisterMenuOption> scoreRegisterMenuOptionMap = new HashMap<>();
    private final Map<Integer, ScoreInquireMenuOption> scoreInquireMenuOptionMap = new HashMap<>();





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
    // ================================== 메뉴 관련 EnumClass HashMap =============================


    // ================================== 과목 EnumClass HashMap ============================
    private final Map<Integer, MandatorySubject> mandatorySubjectMap = new HashMap<>();
    private final Map<Integer, SelectSubject> selectSubjectMap = new HashMap<>();

    public Map<Integer, MandatorySubject> getMandatorySubjectMap() {
        return mandatorySubjectMap;
    }

    public Map<Integer, SelectSubject> getSelectSubjectMap() {
        return selectSubjectMap;
    }

    // ================================== 과목 EnumClass HashMap ============================

    public DataBase() {
        databaseInit();
    }
    public List<Student> getStudentList() {
        return studentList;
    }
    public Set<Subject> getSubjectList() {
        return subjectList;
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


    // 학생 삭제
    public void deleteStudent (Student student) {
        studentList.remove(student);
        for (String subject : student.getSubjectList()) {
            String key = student.getStudentId() + subject;
            subjectSet.remove(key);
            subjectScoreMap.remove(key);
        }
    }


    public void createStudent(String name, String status, List<String> subjectNameList) {
        Student student = new Student(name, status);
        studentByStatusMap.get(status).add(student);

        for (String subjectName : subjectNameList) {
            student.getSubjectList().add(subjectName);
            String key = student.getStudentId() + subjectName;
            subjectSet.add(key);
            subjectScoreMap.put(key, new ArrayList<SubjectScore>());

        }
    }


    public void inputSubjectScore(String key, List<SubjectScore> subjectScoreList) {
        for (SubjectScore subjectScore : subjectScoreList) {
            subjectScoreMap.get(key).add(subjectScore);
        }
    }


    private void databaseInit() {
        enumClassInit();

        subjectList.add(new Subject("Java", true));
        subjectList.add(new Subject("객체지향", true));
        subjectList.add(new Subject("Spring", true));
        subjectList.add(new Subject("JPA", true));
        subjectList.add(new Subject("MySQL", true));
        subjectList.add(new Subject("디자인_패턴", false));
        subjectList.add(new Subject("Spring_Security", false));
        subjectList.add(new Subject("Redis", false));
        subjectList.add(new Subject("MongoDB", false));

        studentByStatusMap.put("Green", new ArrayList<Student>());
        studentByStatusMap.put("Yellow", new ArrayList<Student>());
        studentByStatusMap.put("Red", new ArrayList<Student>());

        testInput();
    }

    private void testInput() {
        createStudent("김상엽", "Green", List.of(
            "Java", "객체지향", "Spring", "디자인_패턴", "Spring_Security"
        ));
        createStudent("이준우", "Green", List.of(
                "JPA", "객체지향", "Spring", "Redis", "Spring_Security"
        ));
        createStudent("최유라", "Yellow", List.of(
                "JPA", "MySQL", "Spring", "MongoDB", "Spring_Security"
        ));
        createStudent("김준혁", "Red", List.of(
                "Java", "MySQL", "Spring", "MongoDB", "Redis"
        ));
        // inputSubjectScore();
    }



    private void enumClassInit()  {

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

        MandatorySubject[] mandatorySubjects = MandatorySubject.values();
        for (int i = 0; i < mandatorySubjects.length; ++i) {
            mandatorySubjectMap.put(i, mandatorySubjects[i]);
        }

        SelectSubject[] selectSubjects = SelectSubject.values();
        for (int i = 0; i < selectSubjects.length; ++i) {
            selectSubjectMap.put(i, selectSubjects[i]);
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
    }
}
