package database;

import resources.Student;
import resources.SubjectScore;
import util.Util;
import util.options.StudentStatus;
import java.util.*;


public class Database {
    private int count = 0;
    // ============================= 데이터 베이스 저장관련 자료 =======================================
    // key : studentId + subjectName    value : 과목이름
    private final Set<String> subjectSet = new HashSet<>();   // 특정학생의 과목 수강여부를 확인하기위한 Set
    // key : studentId            value : Student
    private final Map<String, Student> studentByIdMap = new TreeMap<>();
    // key : 상태                      value : 학생리스트            상태별 학생리스트
    private final Map<String, List<Student>> studentsByStatusMap = new HashMap<>();
    // key : studentId + subjectName   value : 해당id학생의 해당과목의 회차별 점수 목록
    private final Map<String, List<SubjectScore>> subjectScoreMap = new HashMap<>();
    public Set<String> getSubjectSet() {
        return subjectSet;
    }
    // ============================= 데이터 베이스 저장관련 자료 =======================================
    public Database() {
        databaseInit();
    }

    public int getCount() {
        return count;
    }

    private void databaseInit() {
        //============================= 감정상태 관련 초기화 ==================================
            List<String> statusStringList = StudentStatus.getStatusStringList();
            for (String status : statusStringList) {
                studentsByStatusMap.put(status, new ArrayList<>());
            }
        //============================= 감정상태 관련 초기화 ==================================
        TestInput();
    }



    // 학생 등록
    public void createStudent (String studentName, String status, List<String> subjectList) {
        Student student = new Student(studentName, status, subjectList);
        studentByIdMap.put(student.getStudentId(), student);
        studentsByStatusMap.get(status).add(student);
        ++count;
    }


    // 모든 학생 읽기
    public List<Student> readAllStudents() {
        return studentByIdMap.values().stream().toList();
    }
    // 학생 아이디로 읽기
    public Student readStudentById(String studentId) {
        return studentByIdMap.get(studentId);
    }
    // 학생 상태로 읽기
    public List<Student> readStudentsByStatus(String status) {
        return studentsByStatusMap.get(status);
    }

    // 학생 정보 변경
    public void updateStudent (String studentId, String studentName, String status) {
        Student student = studentByIdMap.get(studentId);
        if (!student.getStatus().equals(status)) {
            studentsByStatusMap.get(student.getStatus()).remove(student);
            student.setStatus(status);
            studentsByStatusMap.get(status).add(student);
        }
        if (!student.getStudentName().equals(studentName)) {
            student.setStudentName(studentName);
        }
    }

    // 학생 삭제
    public void deleteStudent (Student student) {
        String studentId = student.getStudentId();
        studentsByStatusMap.get(student.getStatus()).remove(student);
        for (String subjectName : student.getSubjectList()) {
            String key = studentId + subjectName;
            subjectSet.remove(key);
            subjectScoreMap.remove(key);
        }
        studentByIdMap.remove(studentId);
        --count;
    }

    // 점수 등록
    public void createScore(String key, List<SubjectScore> subjectScoreList) {
        subjectScoreMap.get(key).addAll(subjectScoreList);
    }

    // 점수 읽기
    public List<SubjectScore> readScoreList(String key) {
//        if (subjectScoreMap.get(key) == null) {
//            subjectScoreMap.put(key, new ArrayList<>());
//        }
        // 같은 표현
        subjectScoreMap.computeIfAbsent(key, k -> new ArrayList<>());
        return subjectScoreMap.get(key);
    }

    // 점수 변경
    public void updateScore(String key, int round, int score, boolean isMandatory) {
        if (subjectScoreMap.get(key).get(round).getScore() != score) {
            SubjectScore subjectScore = new SubjectScore(score, isMandatory);
            subjectScoreMap.get(key).remove(round);
            subjectScoreMap.get(key).add(round, subjectScore);
        }
    }

    public void deleteScore() {

    }
    private void TestInput() {
        String[] names = {"티모", "야스오", "마이", "베인", "징크스"};
        String[] statuses = {"Green", "Red", "Yellow", "Red", "Green"};
        String[] subjects = {"Java", "OOP", "Spring", "JPA","MySQL", "Design Pattern", "Spring Security", "Redis", "MongoDB"};

        int[] scores = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        boolean[] booleans = {true, true, true, true, true, false, false, false, false};
        List<String> subjectLists = List.of("dummy", "Java", "OOP", "Spring", "JPA",
                                            "MySQL", "Design Pattern", "Spring Security", "Redis", "MongoDB");

        for (int i = 0; i < names.length; ++i) {
            createStudent(names[i], statuses[i], subjectLists);
            for (int j = 1; j < subjectLists.size(); ++j) {
                String key = "STU" + (i + 1) + subjectLists.get(j);
                subjectScoreMap.put(key, new ArrayList<>());
            }
        }

        for (int i = 0; i < 3000; ++i) {
            int scoreRand = (int)(Math.random() * scores.length);
            int subjectRand = (int)(Math.random() * subjects.length);
            String key ="STU" + (i  % 5 + 1) + subjects[subjectRand];
            subjectScoreMap.get(key).add(new SubjectScore(scores[scoreRand], booleans[subjectRand]));
        }
    }
}
