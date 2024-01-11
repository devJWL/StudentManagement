package Data;

import Resources.Student;
import Resources.Subject;
import Resources.SubjectScore;

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


    // key : studentId + subjectName   value : 해당id의 학생의 해당 과목의 회차별 점수 목록
    private final Map<String, List<SubjectScore>> subjectScoreMap = new HashMap<>();

    // key : 상태                      value : 학생리스트
    private final Map<String, List<Student>> studentByStatusMap = new HashMap<>();

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
}
