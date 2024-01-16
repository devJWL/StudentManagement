package controller;
import database.Database;
import resources.Student;
import resources.SubjectScore;
import util.Util;
import util.options.*;
import util.printMenu.PrintMenuOption;
import util.subject.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;
import static util.ScoreLimit.*;
import static util.options.ScoreInquireMenuOption.*;
import static util.options.ScoreMenuOption.*;
import static util.options.StudentMenuOption.*;
import static util.printMenu.MenuOption.*;
import static util.options.ScoreChangeMenuOption.*;
import static util.options.ScoreRegisterMenuOption.*;
import static util.options.StudentChangeMenuOption.*;
import static util.options.StudentDeleteMenuOption.*;
import static util.options.StudentInquireMenuOption.*;
import static util.options.StudentRegisterMenuOption.*;
import static util.options.StudentStatus.*;
import static util.options.YesOrNoOption.*;
import static util.subject.MandatorySubject.*;
import static util.subject.OptionSubject.*;



public class Controller {
    private final Database dataBase;
    private final Scanner sc;
    private final PrintMenuOption printMenuOption;
    private final Util util;

    public Controller(Database dataBase, Scanner sc, PrintMenuOption printMenuOption, Util util) {
        this.dataBase = dataBase;
        this.sc = sc;
        this.printMenuOption = printMenuOption;
        this.util = util;
    }

    // ================================== 수강생 관리 메뉴 =======================================================
    public void studentMenu() {
        while(true) {
            System.out.println(printMenuOption.getStringData(STUDENT_MAIN_MENU));
            StudentMenuOption select = StudentMenuOption
                    .get(util.returnValidOutput(STUDENT_MENU_OPTION_REGISTER.ordinal(), STUDENT_MENU_OPTION_BACK.ordinal()));
            switch (select) {
                case STUDENT_MENU_OPTION_REGISTER -> registerStudent();
                case STUDENT_MENU_OPTION_INQUIRE -> inquireStudent();
                case STUDENT_MENU_OPTION_CHANGE -> changeStudent();
                case STUDENT_MENU_OPTION_DELETE -> deleteStudent();
                case STUDENT_MENU_OPTION_BACK -> {
                    return;
                }
                // ERROR
                default -> System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
            }
        }
    }

    public void scoreMenu() {
        while(true) {
            System.out.println(printMenuOption.getStringData(SCORE_MAIN_MENU));
            ScoreMenuOption select = ScoreMenuOption
                    .get(util.returnValidOutput(SCORE_MENU_OPTION_REGISTER.ordinal(), SCORE_MENU_OPTION_BACK.ordinal()));

            switch (select) {
                case SCORE_MENU_OPTION_REGISTER -> registerScore();
                case SCORE_MENU_OPTION_INQUIRE -> inquireScore();
                case SCORE_MENU_OPTION_CHANGE -> changeScore();
                case SCORE_MENU_OPTION_BACK -> {
                    return;
                }
                // ERROR
                default -> System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
            }
        }
    }
    public void registerStudent() {
        boolean on = true;
        while(on) {
            System.out.println(printMenuOption.getStringData(STUDENT_REGISTER_MENU));
            StudentRegisterMenuOption select = StudentRegisterMenuOption
                    .get(util.returnValidOutput(STUDENT_REGISTER_MENU_OPTION_ERROR.ordinal(), STUDENT_REGISTER_MENU_OPTION_BACK.ordinal()));

            switch (select) {
                case STUDENT_REGISTER_MENU_OPTION_REGISTER -> registerStudentHelper();
                case STUDENT_REGISTER_MENU_OPTION_BACK-> on = false;
                // ERROR
                default -> System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
            }
        }
    }

    private void registerStudentHelper() {
        YesOrNoOption yesOrNoOption;
        String studentId;
        String studentName;
        String status;
        List<String> subjectNameList;
        do {
            System.out.println(printMenuOption.getStringData(STUDENT_REGISTER_HELPER_MENU));
            studentId = "STU" + Student.NO;
            System.out.println("수강생의 고유번호 : " + studentId);
            System.out.println("수강생의 이름을 입력해주세요");
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
            System.out.print("> ");
            String studentName = sc.nextLine();
            System.out.println("수강생의 상태를 선택해주세요");
            printMenuList(StudentStatus.getStatusStringList());
            String status = StudentStatus
                    .get(util.returnValidOutput(STUDENT_STATUS_GREEN.ordinal(), StudentStatus.STUDENT_STATUS_RED.ordinal()))
                    .getStatus();

            List<String> subjectNameList = new ArrayList<>(List.of("dummy"));
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
            studentName = inputStudentName();
            System.out.println("수강생의 상태에 해당하는 숫자를 입력해주세요.");
            status = inputStudentStatus();
            subjectNameList = new ArrayList<>(List.of("dummy"));
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes

            addMandatorySubjectHelper(studentId, subjectNameList);
            addSelectSubjectHelper(studentId, subjectNameList);
=======

            addMandatorySubjectHelper(studentId, subjectNameList);
            addOptionSubjectHelper(studentId, subjectNameList);
>>>>>>> Stashed changes
=======

            addMandatorySubjectHelper(studentId, subjectNameList);
            addOptionSubjectHelper(studentId, subjectNameList);
>>>>>>> Stashed changes

            System.out.printf("고유번호 : %s\n", studentId);
            System.out.printf("이름 : %s\n", studentName);
            System.out.printf("상태 : %s\n", status);
            System.out.println("과목 목록");
            printList(subjectNameList);
            System.out.println("위의 정보로 수강생을 등록하시겠습니까?");
            System.out.println("1. 네\t2. 아니오");
            yesOrNoOption = yesOrNoInput();
            if (yesOrNoOption == YES_OR_NO_OPTION_YES) {
                dataBase.createStudent(studentName, status, subjectNameList);
                System.out.println("수강생이 등록되었습니다.");
            }
            System.out.print("1. 계속 등록하기\t2. 뒤로가기");
            yesOrNoOption = yesOrNoInput();
        } while(yesOrNoOption == YES_OR_NO_OPTION_YES);
    }

    private void addMandatorySubjectHelper(String studentId, List<String> subjectList) {
        List<String> mandatorySubjects = new ArrayList<>();
        Set<String> subjectSet = new HashSet<>();
        YesOrNoOption yesOrNoOption;
        List<String> mandatorySubjectStringList = MandatorySubject.getMandatorySubjectStringList();
        MandatorySubject mandatorySubject;
        String subjectName, key;
        boolean on = true;

        do {
            System.out.println("수강생이 수강 중인 필수과목을 입력해주세요 (필수과목은 3가지 이상 선택 해야합니다.)");
            System.out.println("필수과목 목록");
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
            printAddingSubjectList(studentId, MandatorySubject.getMandatorySubjectStringList(), subjectSet);
            System.out.println("과목에 해당하는 숫자를 입력해주세요");
            MandatorySubject mandatorySubject = MandatorySubject
                    .get(util.returnValidOutput(JAVA.ordinal(), MYSQL.ordinal()));
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
            printAddingSubjectList(studentId, mandatorySubjectStringList, subjectSet);
            System.out.println("선택할 과목에 해당하는 숫자를 입력해주세요");
            mandatorySubject = MandatorySubject
                    .get(util.returnValidOutput(MANDATORY_SUBJECT_ERROR.ordinal(), MANDATORY_SUBJECT_END.ordinal()));
>>>>>>> Stashed changes

            subjectName = mandatorySubject.getSubjectName();
            key = studentId + subjectName;

            System.out.printf("%s 과목을 선택 하시겠습니까?\n", subjectName);
            System.out.println("1. 네     2. 아니요");
            yesOrNoOption = yesOrNoInput();

            if (yesOrNoOption == YES_OR_NO_OPTION_YES) {
                // 현재 선택한 과목이 이미 수강하는(선택됐던) 과목일 경우
                if (subjectSet.contains(key)) {
                    System.out.printf("%s 과목은 이미 선택된 필수과목입니다. 다른 과목을 선택해주세요.\n", subjectName);
                }
                else {
                    System.out.printf("%s 과목을 선택하였습니다.\n", subjectName);
                    subjectSet.add(key);
                    mandatorySubjects.add(subjectName);
                }
            }
            else {
                System.out.printf("%s 과목 선택이 취소되었습니다.\n", subjectName);
            }

            System.out.println("과목을 계속 선택하시겠습니까?");
            System.out.println("1. 네\t2. 아니오");
            yesOrNoOption = yesOrNoInput();
            if (yesOrNoOption == YES_OR_NO_OPTION_YES) {
                continue;
            }

            if (mandatorySubjects.size() >= 3){
                on = false;
                subjectList.addAll(mandatorySubjects);
            }
            else {
                System.out.println("필수과목은 3가지 이상 선택해야합니다.");
                System.out.println("현재 신청한 필수과목 목록");
                System.out.println(mandatorySubjects);
            }
        }while(on);
    }

    private void addSelectSubjectHelper(String studentId, List<String> subjectList) {
        List<String> selectSubjects = new ArrayList<>();
        Set<String> subjectSet = new HashSet<>();
        YesOrNoOption yesOrNoOption;
        String subjectName, key;
        List<String> optionSubjectStringList  = OptionSubject.getOptionSubjectStringList();
        boolean on = true;

        do {
            System.out.println("수강생이 수강 중인 선택과목을 입력해주세요 (선택과목은 2가지 이상 선택 해야합니다.)");
            System.out.println("선택과목 목록");
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
            printAddingSubjectList(studentId, OptionSubject.getOptionSubjectStringList(), subjectSet);
            System.out.println("과목에 해당하는 숫자를 입력해주세요");
            OptionSubject selectSubject = OptionSubject
                    .get(util.returnValidOutput(DESIGN_PATTERN.ordinal(), MONGODB.ordinal()));
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
            printAddingSubjectList(studentId, optionSubjectStringList, subjectSet);
            System.out.println("선택할 과목에 해당하는 숫자를 입력해주세요");
            OptionSubject optionSubject = OptionSubject
                    .get(util.returnValidOutput(OPTION_SUBJECT_ERROR.ordinal(), OPTION_SUBJECT_END.ordinal()));
>>>>>>> Stashed changes

            subjectName = optionSubject.getSubjectName();
            key = studentId + subjectName;

            System.out.printf("%s 과목을 선택 하시겠습니까?\n", subjectName);
            System.out.println("1. 네     2. 아니요");
            yesOrNoOption = yesOrNoInput();
            if (yesOrNoOption == YES_OR_NO_OPTION_YES) {
                // 현재 선택한 과목이 이미 수강하는(선택됐던) 과목일 경우
                if (subjectSet.contains(key)) {
                    System.out.printf("%s 과목은 이미 선택된 과목입니다. 다른 과목을 선택해주세요.\n", subjectName);
                }
                else {
                    System.out.printf("%s 과목을 선택하였습니다.\n", subjectName);
                    subjectSet.add(key);
                    selectSubjects.add(subjectName);
                }
            }
            else {
                System.out.printf("%s 과목 선택이 취소되었습니다.\n", subjectName);
            }

            System.out.println("과목을 계속 선택 하시겠습니까?");
            System.out.println("1. 네\t2. 아니오");
            yesOrNoOption = yesOrNoInput();
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
                System.out.println(selectSubjects);
            }
        }while(on);
    }

    public void inquireStudent() {
        boolean on = true;
        while(on) {
            System.out.println(printMenuOption.getStringData(STUDENT_INQUIRE_MENU));
            StudentInquireMenuOption select = StudentInquireMenuOption
                    .get(util.returnValidOutput(STUDENT_INQUIRE_MENU_OPTION_ALL.ordinal(), STUDENT_INQUIRE_MENU_OPTION_BACK.ordinal()));
            switch (select) {
                case STUDENT_INQUIRE_MENU_OPTION_ALL -> studentInquireALLHelper();
                case STUDENT_INQUIRE_MENU_OPTION_ID -> studentInquireByIdHelper();
                case STUDENT_INQUIRE_MENU_OPTION_STATUS -> studentInquireByStatusHelper();
                case STUDENT_INQUIRE_MENU_OPTION_BACK -> on = false;
                // ERROR
                default -> System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
            }
        }
    }

    private void studentInquireALLHelper() {
        List<Student> allStudents = dataBase.readAllStudents();
        for (Student student : allStudents) {
            printStudent(student);
        }
        System.out.println("이전 메뉴로 가시려면 엔터키를 눌러주세요");
        sc.nextLine();
    }

    private void studentInquireByIdHelper() {
        String studentId;
        YesOrNoOption yesOrNoOption;
        Student student;
        do {
            System.out.println(printMenuOption.getStringData(STUDENT_INQUIRE_HELPER_ID_MENU));
            System.out.println("조회할 수강생의 고유번호를 입력해주세요");
            studentId = inputStudentId();
            student = dataBase.readStudentById(studentId);
            if (student != null) {
                printStudent(student);
            }
            else {
                System.out.printf("%s의 고유번호는 존재하지 않습니다.\n", studentId);
            }
            System.out.println("1. 계속 조회하기\t2. 뒤로가기");
            yesOrNoOption = yesOrNoInput();
        } while (yesOrNoOption != YES_OR_NO_OPTION_NO);
    }


    private void studentInquireByStatusHelper() {
        YesOrNoOption yesOrNoOption;
        String status;
        List<Student> studentList;
        do {
            System.out.println(printMenuOption.getStringData(STUDENT_INQUIRE_HELPER_STATUS_MENU));
            printMenuList(StudentStatus.getStatusStringList());
            System.out.println("조회할 수강생의 상태를 입력해주세요");
            status = inputStudentStatus();
            studentList = dataBase.readStudentsByStatus(status);

            if (studentList.size() == 0) {
                System.out.printf("%s 상태의 학생이 없습니다.\n", status);
            }
            else {
                System.out.printf("%s 상태 학생목록\n", status);
                for (Student student : studentList) {
                    printStudent(student);
                }
            }
            System.out.println("1. 계속 조회하기\t2. 뒤로가기");
            yesOrNoOption = yesOrNoInput();
        } while (yesOrNoOption != YES_OR_NO_OPTION_NO);
    }


    public void updateStudent() {
        boolean on = true;
        StudentChangeMenuOption select;
        while(on) {
            System.out.println(printMenuOption.getStringData(STUDENT_CHANGE_MENU));
            select = StudentChangeMenuOption
                    .get(util.returnValidOutput(STUDENT_CHANGE_MENU_OPTION_CHANGE.ordinal(), STUDENT_CHANGE_MENU_OPTION_BACK.ordinal()));
            switch (select) {
                case STUDENT_CHANGE_MENU_OPTION_CHANGE -> studentUpdateHelper();
                case STUDENT_CHANGE_MENU_OPTION_BACK -> on = false;
            }
        }
    }


    private void studentUpdateHelper() {
        String studentId;
        YesOrNoOption yesOrNoOption;
        Student student;
        String newStudentName, newStatus;
        StudentStatus studentStatus;
        do {
            System.out.println(printMenuOption.getStringData(STUDENT_CHANGE_HELPER_MENU));
            System.out.println("정보를 변경할 수강생의 고유번호를 입력해주세요");
            studentId = inputStudentId();
            student = dataBase.readStudentById(studentId);
            if (student != null) {
                System.out.println("학생의 현재 정보");
                printStudent(student);
                System.out.println("새로운 이름을 입력해주세요");
                newStudentName = inputStudentName();
                System.out.println("새로운 상태를 선택해주세요");
                newStatus = inputStudentStatus();
                System.out.println("1. 변경하기\t2. 변경 취소하기");
                yesOrNoOption = yesOrNoInput();
                if (yesOrNoOption == YES_OR_NO_OPTION_YES) {
                    String prevStudentName = student.getStudentName();
                    String prevStatus = student.getStatus();
                    dataBase.updateStudent(studentId, prevStudentName, newStudentName, prevStatus, newStatus);
                    System.out.println("변경 전");
                    System.out.printf("이름 : %s | 상태 : %s\n", prevStudentName, prevStatus);
                    System.out.println("변경 후");
                    System.out.printf("이름 : %s | 상태 : %s\n", newStudentName, newStatus);
                    System.out.println("변경 되었습니다.");
                }
            }
            else {
                System.out.printf("%s는 존재하지 않는 고유번호입니다.\n", studentId);
            }
            System.out.println("1. 계속 변경하기\t2. 뒤로가기");
            yesOrNoOption = yesOrNoInput();
        } while (yesOrNoOption != YES_OR_NO_OPTION_NO);
    }


    public void deleteStudent() {
        StudentDeleteMenuOption select;
        while(true) {
            System.out.println(printMenuOption.getStringData(STUDENT_DELETE_MENU));
            select = StudentDeleteMenuOption
                    .get(util.returnValidOutput(STUDENT_DELETE_MENU_OPTION_DELETE.ordinal(), STUDENT_DELETE_MENU_OPTION_BACK.ordinal()));
            switch (select) {
                case STUDENT_DELETE_MENU_OPTION_DELETE -> studentDeleteHelper();
                case STUDENT_DELETE_MENU_OPTION_BACK -> {
                    return;
                }
            }
        }
    }

    private void studentDeleteHelper() {
        String studentId;
        YesOrNoOption yesOrNoOption;
        do {
            System.out.println(printMenuOption.getStringData(STUDENT_DELETE_HELPER_MENU));
            System.out.println("삭제할 수강생의 고유번호를 입력해주세요");
            studentId = sc.nextLine();
            if (dataBase.getCount() == 0) {
                System.out.println("현재 등록된 수강생이 0명 입니다.");
            }
            else {
                Student student = dataBase.readStudentById(studentId);
                if (student != null) {
                    System.out.println("삭제할 학생의 현재 정보");
                    printStudent(student);

                    System.out.println("1. 삭제하기\t2. 삭제 취소하기");
                    yesOrNoOption = yesOrNoInput();
                    if (yesOrNoOption == YES_OR_NO_OPTION_YES) {
                        dataBase.deleteStudent(student);
                    }
                }
                else {
                    System.out.printf("%s는 존재하지 않는 고유번호입니다.\n", studentId);
                }
            }
            System.out.println("1. 계속 삭제하기\t2. 뒤로가기");
            yesOrNoOption = yesOrNoInput();
        } while (yesOrNoOption != YES_OR_NO_OPTION_NO);
    }
    // ================================== 수강생 관리 메뉴 =======================================================


    // ================================== 점수 관리 메뉴 ========================================================

    public void registerScore() {
        boolean on = true;
        while (on) {
            System.out.println(printMenuOption.getStringData(SCORE_REGISTER_MENU));
            ScoreRegisterMenuOption select = ScoreRegisterMenuOption
                    .get(util.returnValidOutput(SCORE_REGISTER_MENU_OPTION_REGISTER.ordinal(), SCORE_REGISTER_MENU_OPTION_BACK.ordinal()));
            switch (select) {
                case SCORE_REGISTER_MENU_OPTION_REGISTER-> registerScoreHelper();
                case SCORE_REGISTER_MENU_OPTION_BACK-> on = false;
                // ERROR
                default -> System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
            }
        }
    }

    private void registerScoreHelper() {
        String studentId;
        YesOrNoOption yesOrNoOption;
        do {
            System.out.println(printMenuOption.getStringData(SCORE_REGISTER_HELPER_MENU));
            System.out.println("점수를 등록할 수강생의 고유번호를 입력해주세요");
            studentId = sc.nextLine();
            Student student = dataBase.readStudentById(studentId);
            if (student != null) {
                List<String> subjectList = student.getSubjectList();
                System.out.println("현재 학생이 수강하고 있는 과목 목록입니다.\n점수를 등록할 과목을 입력해주세요");
                printMenuList(student.getSubjectList());
                int validIndex = util.returnValidOutput(1, subjectList.size() - 1);
                String studentName = student.getStudentName();
                String subjectName = subjectList.get(validIndex);
                String key = studentId + subjectName;
                List<SubjectScore> subjectScoreList =  dataBase.readScoreList(key);
                int round = subjectScoreList.size();
                List<SubjectScore> newSubjectScores = new ArrayList<>();
                do {
                    ++round;
                    System.out.printf("%s | %s | %s 과목 %d회차 점수등록 중 입니다.\n", studentId, studentName, subjectName, round);
                    System.out.println("점수를 입력해주세요");
                    int score = util.returnValidOutput(SCORE_LIMIT_MIN.getScore(), SCORE_LIMIT_MAX.getScore());
                    SubjectScore subjectScore = new SubjectScore(score, util.getOptionOrMandatoryMap().get(subjectName));
                    newSubjectScores.add(subjectScore);
                    System.out.println("점수를 등록 하시겠습니까?");
                    System.out.println("1. 네    2. 아니요");
                    yesOrNoOption = yesOrNoInput();
                    if (yesOrNoOption == YES_OR_NO_OPTION_NO) {
                        System.out.printf("%s과목 | %d회차에 %d점 | %c등급으로 점수가 등록되었습니다.\n", subjectName, round, subjectScore.getScore(), subjectScore.getGrade());
                        dataBase.createScore(key, newSubjectScores);
                    }
                }while(yesOrNoOption == YES_OR_NO_OPTION_YES);
            } else {
                System.out.printf("%s의 고유번호는 존재하지 않습니다.\n", studentId);
            }
            System.out.println("1. 계속 등록하기\t2. 뒤로가기");
            yesOrNoOption = yesOrNoInput();
        } while (yesOrNoOption != YES_OR_NO_OPTION_NO);
    }

    public void inquireScore() {
        boolean on = true;
        while (on) {
            System.out.println(printMenuOption.getStringData(SCORE_INQUIRE_MENU));
            ScoreInquireMenuOption select = ScoreInquireMenuOption
                    .get(util.returnValidOutput(SCORE_INQUIRE_MENU_OPTION_ID.ordinal(), SCORE_INQUIRE_MENU_OPTION_BACK.ordinal()));

            // 고유번호로 조회하기 상태로 조회하기 만들기
            switch (select) {
                case SCORE_INQUIRE_MENU_OPTION_ID -> inquireScoreByIdHelper();
                case SCORE_INQUIRE_MENU_OPTION_STATUS-> inquireScoreByStatusHelper();
                case SCORE_INQUIRE_MENU_OPTION_BACK-> on = false;
                    // 3. 뒤로가기
                // ERROR
                default -> System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
            }
        }
    }

    private void inquireScoreByIdHelper() {
        String studentId;
        YesOrNoOption yesOrNoOption;
        do {
            System.out.println(printMenuOption.getStringData(SCORE_INQUIRE_HELPER_ID_MENU));
            System.out.println("조회할 수강생의 고유번호를 입력해주세요");
            studentId = sc.nextLine();
            Student student = dataBase.readStudentById(studentId);
            if (student != null){
                inquireScoreByStudentHelper(student);
            }
            else {
                System.out.printf("%s의 고유번호는 존재하지 않습니다.\n", studentId);
            }
            System.out.println("1. 계속 조회하기\t2. 뒤로가기");
            yesOrNoOption = yesOrNoInput();
        } while (yesOrNoOption != YES_OR_NO_OPTION_NO);
    }

    private void inquireScoreByStatusHelper() {
        YesOrNoOption yesOrNoOption;
        do {
            System.out.println(printMenuOption.getStringData(SCORE_INQUIRE_HELPER_STATUS_MENU));
            System.out.println("조회하실 수강생의 상태를 선택해주세요");
            printMenuList(StudentStatus.getStatusStringList());
            String status = StudentStatus
                    .get(util.returnValidOutput(STUDENT_STATUS_GREEN.ordinal(), STUDENT_STATUS_RED.ordinal()))
                    .getStatus();
            List<Student> studentList = dataBase.readStudentsByStatus(status);
            if (studentList.size() == 0) {
                System.out.printf("%s 상태의 학생이 없습니다.\n", status);
            }
            else {
                System.out.printf("%s 상태 학생목록\n", status);
                for (int i = 0; i < studentList.size(); ++i) {
                    Student student = studentList.get(i);
                    System.out.printf("%d. %s %s\n", (i + 1), student.getStudentId(), student.getStudentName());
                }
                System.out.println("조회를 원하시는 학생을 입력해주세요");
                int validIndex = util.returnValidOutput(1, studentList.size());
                Student student = studentList.get(validIndex - 1);
                inquireScoreByStudentHelper(student);
            }
            System.out.println("1. 계속 조회하기\t2. 뒤로가기");
            yesOrNoOption = yesOrNoInput();
        } while (yesOrNoOption != YES_OR_NO_OPTION_NO);
    }

    private void inquireScoreByStudentHelper(Student student) {
        List<String> subjectList = student.getSubjectList();
        System.out.println("현재 학생이 수강하고 있는 과목 목록입니다.\n점수를 조회 과목을 선택해주세요");
        printMenuList(student.getSubjectList());
        int validIndex = util.returnValidOutput(1, subjectList.size() - 1);
        String studentId = student.getStudentId();
        String studentName = student.getStudentName();
        String subjectName = subjectList.get(validIndex);
        String key = studentId + subjectName;
        List<SubjectScore> subjectScoreList = dataBase.readScoreList(key);
        if (subjectScoreList == null || subjectScoreList.size() == 0) {
            System.out.printf("현재 %s | %s 학생의 %s 과목은 점수가 등록 되어있지 않습니다.\n", studentId, studentName, subjectName);
        }
        else {
            float avg = 0;
            System.out.printf("%s | %s 학생의 %s 과목점수 조회\n", studentId, studentName, subjectName);
            for (int i = 0; i < subjectScoreList.size(); ++i) {
                SubjectScore subjectScore = subjectScoreList.get(i);
                System.out.printf("%-3d회차 | %-3d점 | %c등급\n", i + 1, subjectScore.getScore(), subjectScore.getGrade());
                avg += subjectScore.getScore();
            }
            avg /= subjectScoreList.size();
            System.out.printf("평균점수 : %.1f점 | 평균등급 : %c등급\n", avg, util.calcGrade((int)avg, util.getOptionOrMandatoryMap().get(subjectName)));
        }
    }

    public void updateScore() {
        boolean on = true;
        while(on) {
            System.out.println(printMenuOption.getStringData(SCORE_CHANGE_MENU));
            ScoreChangeMenuOption select = ScoreChangeMenuOption
                    .get(util.returnValidOutput(SCORE_CHANGE_MENU_OPTION_CHANGE.ordinal(), SCORE_CHANGE_MENU_OPTION_BACK.ordinal()));

            switch (select) {
                case SCORE_CHANGE_MENU_OPTION_CHANGE-> updateScoreHelper();
                case SCORE_CHANGE_MENU_OPTION_BACK-> on = false;
                // ERROR
                default -> System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
            }
        }
    }

    private void updateScoreHelper() {
        YesOrNoOption yesOrNoOption;
        do{
            System.out.println(printMenuOption.getStringData(SCORE_CHANGE_HELPER_MENU));
            System.out.println("점수를 수정할 학생의 고유번호를 입력해주세요");
            String studentId = sc.nextLine();
            Student student = dataBase.readStudentById(studentId);
            if (student != null){
                List<String> subjectList = student.getSubjectList();
                System.out.println("현재 학생이 수강하고 있는 과목 목록입니다.\n점수를 변경할 과목을 선택해주세요");
                printMenuList(student.getSubjectList());
                int validIndex = util.returnValidOutput(1, subjectList.size() - 1);
                String studentName = student.getStudentName();
                String subjectName = subjectList.get(validIndex);
                String key = studentId + subjectName;
                List<SubjectScore> subjectScoreList = dataBase.readScoreList(key);
                if (subjectScoreList == null || subjectScoreList.size() == 0) {
                    System.out.printf("현재 %s과목은 점수가 등록되지 않았습니다.\n", subjectName);
                }
                else {
                    System.out.printf("현재 %s과목은 %d회차까지 점수가 등록되어 있습니다.\n", studentName, subjectScoreList.size());
                    System.out.println("점수를 수정할 회차를 입력해주세요 (점수수정은 점수가 등록된 회차만 가능합니다)");
                    int round = util.returnValidOutput(1, subjectList.size()) - 1;
                    System.out.println("점수를 입력해주세요");
                    int score = util.returnValidOutput(SCORE_LIMIT_MIN.getScore(), SCORE_LIMIT_MAX.getScore());
                    System.out.println("점수를 수정 하시겠습니까?");
                    System.out.println("1. 네\t2. 아니요");
                    yesOrNoOption = yesOrNoInput();
                    if (yesOrNoOption == YES_OR_NO_OPTION_YES) {
                        dataBase.updateScore(key, round, score, util.getOptionOrMandatoryMap().get(subjectName));
                        System.out.println("점수를 수정 하였습니다.");
                    }
                }
            }
            else {
                System.out.printf("%s의 고유번호는 존재하지 않습니다.\n", studentId);
            }
            System.out.println("1. 계속 변경하기\t2. 뒤로가기");
            yesOrNoOption = yesOrNoInput();
        }while(yesOrNoOption == YES_OR_NO_OPTION_YES);
    }


    // ================================== 점수 관리 메뉴 ========================================================


    // ====================================== 중복코드 제거 메소드 =======================================================

    private String inputStudentId() {
        System.out.print("> ");
        return sc.nextLine();
    }

    private String inputStudentName() {
        System.out.print("> ");
        return sc.nextLine();
    }

    private String inputStudentStatus() {
        printMenuList(StudentStatus.getStatusStringList());
        return StudentStatus
                .get(util.returnValidOutput(STUDENT_STATUS_ERROR.ordinal(), STUDENT_STATUS_END.ordinal()))
                .getStatus();
    }


    private void printList(List<String> list) {
        if (list.size() <= 1) {
            return;
        }
        for (int i = 1; i < list.size(); ++i) {
            System.out.printf("%s | ", list.get(i));
            if (i % 5 == 0 && (i + 1) != list.size()) {
                System.out.println();
            }
        }
        System.out.println();
    }

    private void printMenuList(List<String> list) {
        if (list.size() <= 1) {
            return;
        }
        for (int i = 1; i < list.size(); ++i) {
            System.out.printf("%d : %s | ", i, list.get(i));
            if (i % 5 == 0 && (i + 1) != list.size()) {
                System.out.println();
            }
        }
        System.out.println();
    }

    private void printAddingSubjectList(String studentId, List<String> list, Set<String> subjectSet) {
        if (list.size() <= 1) {
            return;
        }
        for (int i = 1; i < list.size(); ++i) {
            String subjectName = list.get(i);
            String key = studentId + subjectName;
            if (subjectSet.contains(key)) {
                System.out.print("*");
            }

            System.out.printf("%d : %s | ", i, subjectName);
            if (i % 5 == 0 && i != list.size() - 1) {
                System.out.println();
            }
        }
        System.out.println();
    }

    private void printStudent(Student student) {
        System.out.println("수강생 정보");
        System.out.printf("고유번호 : %s\n", student.getStudentId());
        System.out.printf("이름 : %s\n", student.getStudentName());
        System.out.printf("상태 : %s\n", student.getStatus());
        System.out.println("과목 목록");
        printList(student.getSubjectList());
    }

    private YesOrNoOption yesOrNoInput() {
        return YesOrNoOption.get(util.returnValidOutput(YES_OR_NO_OPTION_YES.ordinal(), YES_OR_NO_OPTION_NO.ordinal()));
    }

    // ====================================== 중복코드 제거 메소드 =======================================================
}
