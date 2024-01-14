package controller;
import database.Database;
import resources.Student;
import resources.SubjectScore;
import util.ScoreLimit;
import util.Util;
import util.options.*;
import util.printMenu.MenuOption;
import util.printMenu.PrintMenuOption;
import util.subject.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static util.printMenu.MenuOption.*;
import static util.options.ScoreChangeMenuOption.*;
import static util.options.ScoreMenuOption.*;
import static util.options.ScoreRegisterMenuOption.*;
import static util.options.StudentChangeMenuOption.*;
import static util.options.StudentDeleteMenuOption.*;
import static util.options.StudentInquireMenuOption.*;
import static util.options.StudentMenuOption.*;
import static util.options.StudentRegisterMenuOption.*;
import static util.options.StudentStatus.*;
import static util.options.YesOrNoOption.*;
import static util.subject.MandatorySubject.*;
import static util.subject.SelectSubject.*;



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
        //dataBase.databaseInit();
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

    private void registerStudent() {
        while(true) {
            System.out.println(printMenuOption.getStringData(STUDENT_REGISTER_MENU));
            StudentRegisterMenuOption select = StudentRegisterMenuOption
                    .get(util.returnValidOutput(STUDENT_REGISTER_MENU_OPTION_ERROR.ordinal(), STUDENT_REGISTER_MENU_OPTION_BACK.ordinal()));

            switch (select) {
                case STUDENT_REGISTER_MENU_OPTION_REGISTER -> registerStudentHelper();
                case STUDENT_REGISTER_MENU_OPTION_BACK-> {
                    return;
                }
                // ERROR
                default -> System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
            }
        }
    }

    public void registerStudentHelper() {
        String studentId = "STU" + Student.NO;
        System.out.println("수강생의 고유번호 : " + studentId);
        System.out.println("수강생의 이름을 입력해주세요");
        System.out.print("> ");
        String studentName = sc.nextLine();
        System.out.println("수강생의 상태를 선택해주세요");
        printList(StudentStatus.getStatusStringList());
        String status = StudentStatus
                .get(util.returnValidOutput(STUDENT_STATUS_GREEN.ordinal(), StudentStatus.STUDENT_STATUS_RED.ordinal()))
                .getStatus();

        List<String> subjectNameList = new ArrayList<>(List.of("dummy"));

        addMandatorySubjectHelper(studentId, subjectNameList);
        addSelectSubjectHelper(studentId, subjectNameList);

        System.out.println("아래의 정보로 수강생을 등록하시겠습니까?");
        System.out.printf("고유번호 : %s\n", studentId);
        System.out.printf("이름 : %s\n", studentName);
        System.out.printf("상태 : %s\n", status);
        System.out.println("과목 목록");
        printList(subjectNameList);
        System.out.println("1. 네    2. 아니오");
        YesOrNoOption yesOrNoOption = yesOrNoInput();

        if (yesOrNoOption == YES_OR_NO_OPTION_YES) {
            dataBase.save(studentName, status, subjectNameList);
            System.out.println("수강생이 등록되었습니다.");
        }
    }

    private void addMandatorySubjectHelper(String studentId, List<String> subjectList) {
        List<String> mandatorySubjects = new ArrayList<>();
        boolean on = true;
        do {
            System.out.println("수강생이 수강 중인 필수과목을 입력해주세요 (필수과목은 3가지 이상 선택 해야합니다.)");
            System.out.println("필수과목 목록");
            printAddingSubjectList(studentId, MandatorySubject.getMandatorySubjectStringList());
            System.out.println("과목에 해당하는 숫자를 입력해주세요");
            MandatorySubject mandatorySubject = MandatorySubject
                    .get(util.returnValidOutput(JAVA.ordinal(), MYSQL.ordinal()));

            if (mandatorySubject == MANDATORY_SUBJECT_ERROR) {
                printMenuOption.getStringData(MenuOption.INPUT_ERROR_MENU);
            }
            // 올바른 입력
            else {
                String subjectName = mandatorySubject.getSubjectName();
                String key = studentId + subjectName;
                // 현재 선택한 과목이 이미 수강하는(선택됐던) 과목일 경우
                if (dataBase.getSubjectSet().contains(key)) {
                    System.out.printf("%s 과목은 이미 추가된 과목입니다. 다른 과목을 선택해주세요.\n", subjectName);
                }
                else {
                    dataBase.getSubjectSet().add(key);
                    mandatorySubjects.add(subjectName);
                }
            }

            System.out.println("과목을 계속 선택하시겠습니까?");
            System.out.println("1. 네    2. 아니오");
            YesOrNoOption yesOrNoOption = yesOrNoInput();
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
        boolean on = true;
        do {
            System.out.println("수강생이 수강 중인 선택과목을 입력해주세요 (선택과목은 2가지 이상 선택 해야합니다.)");
            System.out.println("선택과목 목록");
            printAddingSubjectList(studentId, SelectSubject.getSelectSubjectStringList());
            System.out.println("과목에 해당하는 숫자를 입력해주세요");
            SelectSubject selectSubject = SelectSubject
                    .get(util.returnValidOutput(DESIGN_PATTERN.ordinal(), MONGODB.ordinal()));

            String subjectName = selectSubject.getSubjectName();
            String key = studentId + subjectName;
            // 현재 선택한 과목이 이미 수강하는(선택됐던) 과목일 경우
            if (dataBase.getSubjectSet().contains(key)) {
                System.out.printf("%s 과목은 이미 추가된 과목입니다. 다른 과목을 선택해주세요.\n", subjectName);
            }
            else {
                dataBase.getSubjectSet().add(key);
                selectSubjects.add(subjectName);
            }
            System.out.println("과목을 계속 선택하시겠습니까?");
            System.out.println("1. 네    2. 아니오");
            YesOrNoOption yesOrNoOption = yesOrNoInput();
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

    private void inquireStudent() {
        while(true) {
            System.out.println(printMenuOption.getStringData(STUDENT_INQUIRE_MENU));
            StudentInquireMenuOption select = StudentInquireMenuOption
                    .get(util.returnValidOutput(STUDENT_INQUIRE_MENU_OPTION_ID.ordinal(), STUDENT_INQUIRE_MENU_OPTION_BACK.ordinal()));
            switch (select) {
                case STUDENT_INQUIRE_MENU_OPTION_ID -> studentInquireByIdHelper();
                case STUDENT_INQUIRE_MENU_OPTION_STATUS -> studentInquireByStatusHelper();
                case STUDENT_INQUIRE_MENU_OPTION_BACK -> {
                    return;
                }
                // ERROR
                default -> System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
            }
        }
    }

    private void studentInquireByIdHelper() {
        String studentId;
        YesOrNoOption yesOrNoOption;
        do {
            System.out.println("조회할 수강생의 고유번호를 입력해주세요");
            studentId = sc.nextLine();
            if (dataBase.getStudentByIdMap().containsKey(studentId)) {
                Student student = dataBase.getStudentByIdMap().get(studentId);
                printStudent(student);
            } else {
                System.out.printf("%s의 고유번호는 존재하지 않습니다.\n", studentId);
            }
            System.out.println("1. 계속 조회하기     2. 뒤로가기");
            yesOrNoOption = yesOrNoInput();
        } while (yesOrNoOption != YES_OR_NO_OPTION_NO);
    }

    private void studentInquireByStatusHelper() {
        YesOrNoOption yesOrNoOption;
        do {
            System.out.println("조회할 수강생의 상태를 입력해주세요");
            printList(StudentStatus.getStatusStringList());
            String status = StudentStatus
                    .get(util.returnValidOutput(STUDENT_STATUS_GREEN.ordinal(), STUDENT_STATUS_RED.ordinal()))
                    .getStatus();
            List<Student> studentList = dataBase.getStudentByStatusMap().get(status);
            System.out.printf("%s 상태 학생목록\n", status);
            for (Student student : studentList) {
                printStudent(student);
            }
            System.out.println("1. 계속 조회하기     2. 뒤로가기");
            yesOrNoOption = yesOrNoInput();
        } while (yesOrNoOption != YES_OR_NO_OPTION_NO);
    }


    private void changeStudent() {
        while(true) {
            System.out.println(printMenuOption.getStringData(STUDENT_CHANGE_MENU));
            StudentChangeMenuOption select = StudentChangeMenuOption
                    .get(util.returnValidOutput(STUDENT_CHANGE_MENU_OPTION_CHANGE.ordinal(), STUDENT_CHANGE_MENU_OPTION_BACK.ordinal()));
            switch (select) {
                case STUDENT_CHANGE_MENU_OPTION_CHANGE -> studentChangeHelper();
                case STUDENT_CHANGE_MENU_OPTION_BACK -> {
                    return;
                }
            }
        }
    }


    private void studentChangeHelper() {
        String studentId;
        YesOrNoOption yesOrNoOption;
        do {
            System.out.println("정보를 변경할 수강생의 고유번호를 입력해주세요");
            studentId = sc.nextLine();
            if (dataBase.getStudentByIdMap().containsKey(studentId)) {
                Student student = dataBase.getStudentByIdMap().get(studentId);
                System.out.println("학생의 현재 정보");
                printStudent(student);
                System.out.println("변경할 이름을 입력해주세요");
                String studentName = sc.nextLine();
                System.out.println("변경할 상태를 입력해주세요");
                printList(StudentStatus.getStatusStringList());
                StudentStatus studentStatus = StudentStatus
                        .get(util.returnValidOutput(STUDENT_STATUS_GREEN.ordinal(), STUDENT_STATUS_YELLOW.ordinal()));

                System.out.println("1. 변경하기     2. 변경 취소하기");
                yesOrNoOption = yesOrNoInput();

                if (yesOrNoOption == YES_OR_NO_OPTION_YES) {
                    String prevName = student.getStudentName();
                    String prevStatus = student.getStatus();
                    String status = studentStatus.getStatus();

                    dataBase.change(studentId, studentName, status);
                    System.out.println("변경 전");
                    System.out.printf("이름 : %s | 상태 : %s\n", prevName, prevStatus);
                    System.out.printf("이름 : %s | 상태 : %s\n", studentName, status);
                    System.out.println("변경 되었습니다.");
                }
            } else {
                System.out.printf("%s는 존재하지 않는 고유번호입니다.\n", studentId);
            }
            System.out.println("1. 계속 변경하기     2. 뒤로가기");
            yesOrNoOption = yesOrNoInput();
        } while (yesOrNoOption != YES_OR_NO_OPTION_NO);
    }


    private void deleteStudent() {
        while(true) {
            System.out.println(printMenuOption.getStringData(STUDENT_DELETE_MENU));
            StudentDeleteMenuOption select = StudentDeleteMenuOption
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
            System.out.println("삭제할 수강생의 고유번호를 입력해주세요");
            studentId = sc.nextLine();
            if (dataBase.getStudentByIdMap().size() == 0) {
                System.out.println("현재 수강생이 아무도 등록되어있지 않습니다.");
                return;
            }

            if (dataBase.getStudentByIdMap().containsKey(studentId)) {
                Student student = dataBase.getStudentByIdMap().get(studentId);
                System.out.println("삭제할 학생의 현재 정보");
                printStudent(student);

                System.out.println("1. 삭제하기     2. 삭제 취소하기");
                yesOrNoOption = yesOrNoInput();
                if (yesOrNoOption == YES_OR_NO_OPTION_YES) {
                    dataBase.delete(student);
                }
            } else {
                System.out.printf("%s는 존재하지 않는 고유번호입니다.\n", studentId);
            }
            System.out.println("1. 계속 삭제하기     2. 뒤로가기");
            yesOrNoOption = yesOrNoInput();
        } while (yesOrNoOption != YES_OR_NO_OPTION_NO);
    }
    // ================================== 수강생 관리 메뉴 =======================================================


    // ================================== 점수 관리 메뉴 ========================================================

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


    private void registerScore() {
        while (true) {
            System.out.println(printMenuOption.getStringData(SCORE_REGISTER_MENU));
            ScoreRegisterMenuOption select = ScoreRegisterMenuOption
                    .get(util.returnValidOutput(SCORE_REGISTER_MENU_OPTION_REGISTER.ordinal(), SCORE_REGISTER_MENU_OPTION_BACK.ordinal()));
            switch (select) {
                case SCORE_REGISTER_MENU_OPTION_REGISTER-> registerScoreHelper();
                case SCORE_REGISTER_MENU_OPTION_BACK-> {
                    return;
                }
                // ERROR
                default -> System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
            }
        }
    }

    private void registerScoreHelper() {
        String studentId;
        YesOrNoOption yesOrNoOption;
        do {
            System.out.println("점수를 등록할 수강생의 고유번호를 입력해주세요");
            studentId = sc.nextLine();
            if (dataBase.getStudentByIdMap().containsKey(studentId)) {
                Student student = dataBase.getStudentByIdMap().get(studentId);
                List<String> subjectList = student.getSubjectList();
                System.out.println("현재 학생이 수강하고 있는 과목 목록입니다.\n점수를 등록할 과목을 입력해주세요");
                printList(student.getSubjectList());
                int validIndex = util.returnValidOutput(1, subjectList.size() - 1);
                String studentName = student.getStudentName();
                String subjectName = subjectList.get(validIndex);
                String key = studentId + subjectName;
                dataBase.getSubjectScoreMap().computeIfAbsent(key, k -> new ArrayList<SubjectScore>());
                List<SubjectScore> subjectScoreList =  dataBase.getSubjectScoreMap().get(key);
                int round;
                do {
                    round = subjectScoreList.size() + 1;
                    System.out.printf("%s | %s | %s %d회차 점수등록 중 입니다.\n", studentId, studentName, subjectName, round);
                    System.out.println("점수를 입력해주세요");
                    int score = util.returnValidOutput(ScoreLimit.SCORE_LIMIT_MIN.getScore(), ScoreLimit.SCORE_LIMIT_MAX.getScore());
                    SubjectScore subjectScore = new SubjectScore(score, util.selectOrMandatoryMap.get(subjectName));
                    subjectScoreList.add(subjectScore);
                    System.out.printf("%d회차에 %d점 | %c등급으로 점수가 등록되었습니다.\n", round, subjectScore.getScore(), subjectScore.getGrade());
                    System.out.println("점수를 계속 등록 하시겠습니까?");
                    System.out.println("1. 네    2. 아니요");
                    yesOrNoOption = yesOrNoInput();
                }while(yesOrNoOption == YES_OR_NO_OPTION_YES);
            } else {
                System.out.printf("%s의 고유번호는 존재하지 않습니다.\n", studentId);
            }
            System.out.println("1. 계속 점수 등록하기     2. 뒤로가기");
            yesOrNoOption = yesOrNoInput();
        } while (yesOrNoOption != YES_OR_NO_OPTION_NO);
    }

    private void inquireScore() {
        while (true) {
            System.out.println(printMenuOption.getStringData(SCORE_INQUIRE_MENU));
            StudentInquireMenuOption select = StudentInquireMenuOption
                    .get(util.returnValidOutput(STUDENT_INQUIRE_MENU_OPTION_ID.ordinal(), STUDENT_INQUIRE_MENU_OPTION_BACK.ordinal()));

            switch (select) {
                case STUDENT_INQUIRE_MENU_OPTION_ID-> {
                    // 1. id로 검색
                }
                case STUDENT_INQUIRE_MENU_OPTION_STATUS-> {
                    // 2. 상태로 검색
                }
                case STUDENT_INQUIRE_MENU_OPTION_BACK-> {
                    // 3. 뒤로가기
                    return;
                }
                // ERROR
                default -> System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
            }
        }
    }
    private void changeScore() {
        System.out.println(printMenuOption.getStringData(SCORE_CHANGE_MENU));
        ScoreChangeMenuOption select = ScoreChangeMenuOption
                .get(util.returnValidOutput(SCORE_CHANGE_MENU_OPTION_CHANGE.ordinal(), SCORE_CHANGE_MENU_OPTION_BACK.ordinal()));

        switch (select) {
            case SCORE_CHANGE_MENU_OPTION_CHANGE-> {
                // 1. 변경하기
            }
            case SCORE_CHANGE_MENU_OPTION_BACK-> {
                // 2. 뒤로가기
                return;
            }
            // ERROR
            default -> System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
        }
    }

    // ================================== 점수 관리 메뉴 ========================================================


    // ====================================== 중복코드 제거 메소드 =======================================================

    private void printList(List<String> list) {
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

    private void printAddingSubjectList(String studentId, List<String> list) {
        if (list.size() <= 1) {
            return;
        }
        for (int i = 1; i < list.size(); ++i) {
            String subjectName = list.get(i);
            String key = studentId + subjectName;
            if (dataBase.getSubjectSet().contains(key)) {
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
