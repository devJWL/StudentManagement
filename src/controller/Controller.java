package controller;

import database.DataBase;
import resources.Student;
import resources.Subject;
import resources.SubjectScore;

import java.util.List;
import java.util.Scanner;

public class Controller {
    private final DataBase dataBase;
    private final Scanner sc;

    public Controller(DataBase dataBase, Scanner sc) {
        this.dataBase = dataBase;
        this.sc = sc;
    }
    // 학생 등록
    private void createStudent(String name, String status, List<Subject> subjectList) {
        Student student = new Student(name, status, subjectList);
        studentByStatusMap.get(status).add(student);
    }

    // 학생 삭제
    public void deleteStudent (Student student) {
        studentList.remove(student);
        for (Subject subject : student.getSubjectList()) {
            String key = student.getStudentId() + subject.getSubjectName();
            subjectSet.remove(key);
            subjectScoreMap.remove(key);
        }
    }

    // 점수 등록
    public void inputSubjectScore(String key, List<SubjectScore> subjectScoreList) {
        for (SubjectScore subjectScore : subjectScoreList) {
            subjectScoreMap.get(key).add(subjectScore);
        }
    }

}
