package controller;

import database.DataBase;
import resources.Student;
import resources.Subject;
import resources.SubjectScore;
import util.Vaild;
import util.options.MenuOption;
import util.options.PrintMenuOption;
import util.options.YesOrNoOption;
import util.subject.MandatorySubject;
import util.subject.SelectSubject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static util.options.YesOrNoOption.*;
import static util.subject.MandatorySubject.*;
import static util.subject.SelectSubject.*;

public class Controller {
    private final DataBase dataBase;
    private final Scanner sc;
    private final PrintMenuOption printMenuOption;
    private final Vaild vaild;

    public Controller(DataBase dataBase, Scanner sc, PrintMenuOption printMenuOption, Vaild vaild) {
        this.dataBase = dataBase;
        this.sc = sc;
        this.printMenuOption = printMenuOption;
        this.vaild = vaild;
    }


    public void registerStudentHelper() {
        System.out.println("수강생의 이름을 입력해주세요");
        String studentName = sc.nextLine();
        System.out.println("수강생의 상태를 입력해주세요");
        String status = sc.nextLine();
        List<Subject> subjectList = new ArrayList<>();
        addMandatorySubjectHelper(studentName, subjectList);
        addSelectSubjectHelper(studentName, subjectList);
        System.out.println("아래의 정보로 수강생을 등록하시겠습니까?");
        System.out.printf("고유번호 : %-10s\n", "STU" + Student.NO);
        System.out.printf("이름 : %-10s\n", studentName);
        System.out.printf("상태 : %-10s\n", status);
        System.out.println("과목 목록");
        for (Subject subject : subjectList) {
            System.out.printf("%s | ", subject.getSubjectName());
        }
        System.out.println();
        System.out.println("1. 네        2. 아니오");
        YesOrNoOption yesOrNoOption = vaild.yesNoInputHelper();
        if (yesOrNoOption == YES_OR_NO_OPTION_YES) {
            createStudent(studentName, status, subjectList);
            System.out.println("수강생이 등록되었습니다.");
        }
    }

    private void addMandatorySubjectHelper(String studentName, List<Subject> subjectList) {
        System.out.println("수강생이 수강 중인 필수과목을 입력해주세요");
        System.out.println("필수과목 목록");
        int subjectNumber = 1;
        for (Subject mandatorySubject : dataBase.getMandatorySubjectList()) {
            System.out.printf("%-3d. %-20s\n", subjectNumber, mandatorySubject.getSubjectName());
            ++subjectNumber;
        }
        System.out.println();

        List<Subject> mandatorySubjects = new ArrayList<>();
        boolean on = true;
        do {
            System.out.println("과목에 해당하는 숫자를 입력해주세요");
            MandatorySubject mandatorySubject = dataBase.getMandatorySubjectMap()
                    .get(vaild.returnVaildOutput(JAVA.ordinal(), MYSQL.ordinal()));

            if (mandatorySubject == MANDATORY_SUBJECT_ERROR) {
                printMenuOption.getStringData(MenuOption.INPUT_ERROR_MENU);
            }
            // 올바른 입력
            else {
                String subjectName = dataBase.getMandatorySubjectStringMap().get(mandatorySubject);
                String key = studentName + subjectName;
                // 현재 선택한 과목이 이미 수강하는(선택됐던) 과목일 경우
                if (dataBase.getSubjectSet().contains(key)) {
                    System.out.printf("%s 과목은 이미 추가된 과목입니다. 다른 과목을 선택해주세요.\n", subjectName);
                }
                else {
                    dataBase.getSubjectSet().add(key);
                    mandatorySubjects.add(new Subject(subjectName, true));
                }
            }

            System.out.println("과목을 계속 선택하시겠습니까?");
            YesOrNoOption yesOrNoOption = vaild.yesNoInputHelper();
            if (yesOrNoOption == YES_OR_NO_OPTION_YES) {
                continue;
            }

            if (mandatorySubjects.size() >= 3){
                on = false;
                subjectList.addAll(mandatorySubjects);
            }
            else {
                System.out.println("필수과목은 3개 이상 선택해야합니다.");
                System.out.println("현재 신청한 필수과목 목록");
                for (Subject subject : mandatorySubjects) {
                    System.out.printf("%-20s\n", subject.getSubjectName());
                }
                System.out.println();
            }
        }while(on);
    }

    private void addSelectSubjectHelper(String studentName, List<Subject> subjectList) {
        System.out.println("수강생이 수강 중인 선택과목을 입력해주세요");
        System.out.println("선택과목 목록");
        int subjectNumber = 1;
        for (Subject selectSubject : dataBase.getSelectSubjectList()) {
            System.out.printf("%-3d. %-20s\n", subjectNumber, selectSubject.getSubjectName());
            ++subjectNumber;
        }
        System.out.println();

        List<Subject> selectSubjects = new ArrayList<>();
        boolean on = true;
        do {
            System.out.println("과목에 해당하는 숫자를 입력해주세요");
            SelectSubject selectSubject = dataBase.getSelectSubjectMap()
                    .get(vaild.returnVaildOutput(DESIGN_PATTERN.ordinal(), MONGODB.ordinal()));

            if (selectSubject == SELECT_SUBJECT_ERROR) {
                printMenuOption.getStringData(MenuOption.INPUT_ERROR_MENU);
            }
            // 올바른 입력
            else {
                String subjectName = dataBase.getSelectSubjectStringMap().get(selectSubject);
                String key = studentName + subjectName;
                // 현재 선택한 과목이 이미 수강하는(선택됐던) 과목일 경우
                if (dataBase.getSubjectSet().contains(key)) {
                    System.out.printf("%s 과목은 이미 추가된 과목입니다. 다른 과목을 선택해주세요.\n", subjectName);
                }
                else {
                    dataBase.getSubjectSet().add(key);
                    selectSubjects.add(new Subject(subjectName, true));
                }
            }
            System.out.println("과목을 계속 선택하시겠습니까?");
            YesOrNoOption yesOrNoOption = vaild.yesNoInputHelper();
            if (yesOrNoOption == YES_OR_NO_OPTION_YES) {
                continue;
            }

            if (selectSubjects.size() >= 2){
                on = false;
                subjectList.addAll(selectSubjects);
            }
            else {
                System.out.println("선택과목은 2개이상 선택해야합니다.");
                System.out.println("현재 신청한 선택과목 목록");
                for (Subject subject : selectSubjects) {
                    System.out.printf("%-20s\n", subject.getSubjectName());
                }
                System.out.println();
            }
        }while(on);
    }



    // 학생 등록
    private void createStudent(String name, String status, List<Subject> subjectList) {
        Student student = new Student(name, status, subjectList);
        studentByStatusMap.get(status).add(student);
    }

//    // 학생 삭제
//    private void deleteStudent (Student student) {
//        studentList.remove(student);
//        for (Subject subject : student.getSubjectList()) {
//            String key = student.getStudentId() + subject.getSubjectName();
//            subjectSet.remove(key);
//            subjectScoreMap.remove(key);
//        }
//    }
//
//    // 점수 등록
//    private void inputSubjectScore(String key, List<SubjectScore> subjectScoreList) {
//        for (SubjectScore subjectScore : subjectScoreList) {
//            subjectScoreMap.get(key).add(subjectScore);
//        }
//    }


}
