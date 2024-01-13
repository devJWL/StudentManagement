package controller;
import database.DataBase;
import resources.Student;
import util.Valid;
import util.options.*;
import util.subject.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static util.options.MenuOption.*;
import static util.options.ScoreChangeMenuOption.*;
import static util.options.ScoreMenuOption.*;
import static util.options.ScoreRegisterMenuOption.*;
import static util.options.StudentChangeMenuOption.STUDENT_CHANGE_MENU_OPTION_BACK;
import static util.options.StudentChangeMenuOption.STUDENT_CHANGE_MENU_OPTION_CHANGE;
import static util.options.StudentDeleteMenuOption.STUDENT_DELETE_MENU_OPTION_BACK;
import static util.options.StudentDeleteMenuOption.STUDENT_DELETE_MENU_OPTION_DELETE;
import static util.options.StudentInquireMenuOption.*;
import static util.options.StudentMenuOption.*;
import static util.options.StudentRegisterMenuOption.*;
import static util.options.StudentStatus.*;
import static util.options.YesOrNoOption.*;
import static util.subject.MandatorySubject.*;
import static util.subject.SelectSubject.*;



public class Controller {
    private final DataBase dataBase;
    private final Scanner sc;
    private final PrintMenuOption printMenuOption;
    private final Valid valid;

    public Controller(DataBase dataBase, Scanner sc, PrintMenuOption printMenuOption, Valid valid) {
        this.dataBase = dataBase;
        this.sc = sc;
        this.printMenuOption = printMenuOption;
        this.valid = valid;
        //dataBase.databaseInit();
    }

    // ================================== 수강생 관리 메뉴 =======================================================
    public void studentMenu() {
        while(true) {
            System.out.println(printMenuOption.getStringData(STUDENT_MAIN_MENU));
            StudentMenuOption select = valid.getStudentMenuOptionMap()
                    .get(valid.returnValidOutput(STUDENT_MENU_OPTION_REGISTER.ordinal(), STUDENT_MENU_OPTION_BACK.ordinal()));
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
            ScoreMenuOption select = valid.getScoreMenuOptionMap()
                    .get(valid.returnValidOutput(SCORE_MENU_OPTION_REGISTER.ordinal(), SCORE_MENU_OPTION_BACK.ordinal()));

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

    private void registerStudent() {
        while(true) {
            System.out.println(printMenuOption.getStringData(STUDENT_REGISTER_MENU));
            StudentRegisterMenuOption select = valid.getStudentRegisterMenuOptionMap()
                    .get(valid.returnValidOutput(STUDENT_REGISTER_MENU_OPTION_ERROR.ordinal(), STUDENT_REGISTER_MENU_OPTION_BACK.ordinal()));

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


    private StudentStatus inputStudentStatus() {
        printEnumList(valid.getStudentStatusList());
        StudentStatus studentStatus;
        do{
            studentStatus = valid.getStudentStatusMap()
                    .get(valid.returnValidOutput(STUDENT_STATUS_GREEN.ordinal(), StudentStatus.STUDENT_STATUS_RED.ordinal()));
            if (studentStatus == StudentStatus.STUDENT_STATUS_ERROR) {
                printMenuOption.getStringData(INPUT_ERROR_MENU);
            }
        }while(studentStatus == StudentStatus.STUDENT_STATUS_ERROR);
        return studentStatus;
    }


    public void registerStudentHelper() {
        String studentId = "STU" + Student.NO;
        System.out.println("수강생의 고유번호 : " + studentId);
        System.out.println("수강생의 이름을 입력해주세요");
        String studentName = sc.nextLine();
        System.out.println("수강생의 상태를 선택해주세요");
        StudentStatus studentStatus = inputStudentStatus();
        String status =  valid.getStudentStatusStringMap().get(studentStatus);
        List<String> subjectNameList = new ArrayList<>();

        addMandatorySubjectHelper(studentId, subjectNameList);
        addSelectSubjectHelper(studentId, subjectNameList);

        System.out.println("아래의 정보로 수강생을 등록하시겠습니까?");
        System.out.printf("고유번호 : %s\n", studentId);
        System.out.printf("이름 : %s\n", studentName);
        System.out.printf("상태 : %s\n", status);
        System.out.println("과목 목록");
        printList(subjectNameList);
        System.out.println("1. 네    2. 아니오");
        YesOrNoOption yesOrNoOption = valid.getYesOrNoOptionMap()
                .get(valid.returnValidOutput(YES_OR_NO_OPTION_YES.ordinal(), YES_OR_NO_OPTION_NO.ordinal()));
        if (yesOrNoOption == YES_OR_NO_OPTION_YES) {
            saveStudentToDatabase(studentId, studentName, status, subjectNameList);
            System.out.println("수강생이 등록되었습니다.");
        }
    }

    private void addMandatorySubjectHelper(String studentId, List<String> subjectList) {
        List<String> mandatorySubjects = new ArrayList<>();
        boolean on = true;
        do {
            System.out.println("수강생이 수강 중인 필수과목을 입력해주세요");
            System.out.println("필수과목 목록");
            printEnumSubjectList(studentId, valid.getMandatorySubjecNametList());
            System.out.println("과목에 해당하는 숫자를 입력해주세요");
            MandatorySubject mandatorySubject = valid.getMandatorySubjectMap()
                    .get(valid.returnValidOutput(JAVA.ordinal(), MYSQL.ordinal()));

            if (mandatorySubject == MANDATORY_SUBJECT_ERROR) {
                printMenuOption.getStringData(MenuOption.INPUT_ERROR_MENU);
            }
            // 올바른 입력
            else {
                String subjectName = valid.getMandatorySubjectStringMap().get(mandatorySubject);
                String key = studentId + subjectName;
                // 현재 선택한 과목이 이미 수강하는(선택됐던) 과목일 경우
                if (valid.getSubjectSet().contains(key)) {
                    System.out.printf("%s 과목은 이미 추가된 과목입니다. 다른 과목을 선택해주세요.\n", subjectName);
                }
                else {
                    valid.getSubjectSet().add(key);
                    mandatorySubjects.add(subjectName);
                }
            }

            System.out.println("과목을 계속 선택하시겠습니까?");
            System.out.println("1. 네    2. 아니오");
            YesOrNoOption yesOrNoOption =  valid.getYesOrNoOptionMap()
                    .get(valid.returnValidOutput(YES_OR_NO_OPTION_YES.ordinal(), YES_OR_NO_OPTION_NO.ordinal()));
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
                printList(mandatorySubjects);
            }
        }while(on);
    }

    private void addSelectSubjectHelper(String studentId, List<String> subjectList) {
        List<String> selectSubjects = new ArrayList<>();
        boolean on = true;
        do {
            System.out.println("수강생이 수강 중인 선택과목을 입력해주세요");
            System.out.println("선택과목 목록");
            printEnumSubjectList(studentId, valid.getSelectSubjectNameList());
            System.out.println("과목에 해당하는 숫자를 입력해주세요");
            SelectSubject selectSubject = valid.getSelectSubjectMap()
                    .get(valid.returnValidOutput(DESIGN_PATTERN.ordinal(), MONGODB.ordinal()));

            String subjectName = valid.getSelectSubjectStringMap().get(selectSubject);
            String key = studentId + subjectName;
            // 현재 선택한 과목이 이미 수강하는(선택됐던) 과목일 경우
            if (valid.getSubjectSet().contains(key)) {
                System.out.printf("%s 과목은 이미 추가된 과목입니다. 다른 과목을 선택해주세요.\n", subjectName);
            }
            else {
                valid.getSubjectSet().add(key);
                selectSubjects.add(subjectName);
            }
            System.out.println("과목을 계속 선택하시겠습니까?");
            System.out.println("1. 네    2. 아니오");
            YesOrNoOption yesOrNoOption =  valid.getYesOrNoOptionMap()
                    .get(valid.returnValidOutput(YES_OR_NO_OPTION_YES.ordinal(), YES_OR_NO_OPTION_NO.ordinal()));
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
                printList(selectSubjects);
            }
        }while(on);
    }

    private void inquireStudent() {
        while(true) {
            System.out.println(printMenuOption.getStringData(STUDENT_INQUIRE_MENU));
            StudentInquireMenuOption select = valid.getStudentInquireMenuOptionMap()
                    .get(valid.returnValidOutput(STUDENT_INQUIRE_MENU_OPTION_ID.ordinal(), STUDENT_INQUIRE_MENU_OPTION_BACK.ordinal()));
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
            yesOrNoOption = valid.getYesOrNoOptionMap()
                    .get(valid.returnValidOutput(YES_OR_NO_OPTION_YES.ordinal(), YES_OR_NO_OPTION_NO.ordinal()));
        } while (yesOrNoOption != YES_OR_NO_OPTION_NO);
    }

    private void studentInquireByStatusHelper() {
        StudentStatus studentStatus;
        YesOrNoOption yesOrNoOption;
        do {
            System.out.println("조회할 수강생의 상태를 입력해주세요");
            printEnumList(valid.getStudentStatusList());
            studentStatus = valid.getStudentStatusMap()
                    .get(valid.returnValidOutput(STUDENT_STATUS_GREEN.ordinal(), STUDENT_STATUS_RED.ordinal()));

            String status = valid.getStudentStatusStringMap().get(studentStatus);
            List<Student> studentList = dataBase.getStudentByStatusMap().get(status);
            System.out.printf("%s 상태 학생목록\n", status);
            for (Student student : studentList) {
                printStudent(student);
            }
            System.out.println("1. 계속 조회하기     2. 뒤로가기");
            yesOrNoOption = valid.getYesOrNoOptionMap()
                    .get(valid.returnValidOutput(YES_OR_NO_OPTION_YES.ordinal(), YES_OR_NO_OPTION_NO.ordinal()));
        } while (yesOrNoOption != YES_OR_NO_OPTION_NO);
    }


    private void changeStudent() {
        while(true) {
            System.out.println(printMenuOption.getStringData(STUDENT_CHANGE_MENU));
            StudentChangeMenuOption select = valid.getStudentChangeMenuOptionMap()
                    .get(valid.returnValidOutput(STUDENT_CHANGE_MENU_OPTION_CHANGE.ordinal(), STUDENT_CHANGE_MENU_OPTION_BACK.ordinal()));
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
                StudentStatus studentStatus = inputStudentStatus();
                System.out.println("1. 변경하기     2. 변경 취소하기");
                yesOrNoOption = valid.getYesOrNoOptionMap()
                        .get(valid.returnValidOutput(YES_OR_NO_OPTION_YES.ordinal(), YES_OR_NO_OPTION_NO.ordinal()));

                if (yesOrNoOption == YES_OR_NO_OPTION_YES) {
                    String prevName = student.getStudentName();
                    String prevStatus = student.getStatus();
                    String status = valid.getStudentStatusStringMap().get(studentStatus);
                    changeStudentInfo(studentId, studentName, status);
                    System.out.println("변경 전");
                    System.out.printf("이름 : %s | 상태 : %s\n", prevName, prevStatus);
                    System.out.printf("이름 : %s | 상태 : %s\n", studentName, status);
                    System.out.println("변경 되었습니다.");
                }
            } else {
                System.out.printf("%s는 존재하지 않는 고유번호입니다.\n", studentId);
            }
            System.out.println("1. 계속 변경하기     2. 뒤로가기");
            yesOrNoOption = valid.getYesOrNoOptionMap()
                    .get(valid.returnValidOutput(YES_OR_NO_OPTION_YES.ordinal(), YES_OR_NO_OPTION_NO.ordinal()));
        } while (yesOrNoOption != YES_OR_NO_OPTION_NO);
    }


    private void deleteStudent() {
        while(true) {
            System.out.println(printMenuOption.getStringData(STUDENT_DELETE_MENU));
            StudentDeleteMenuOption select = valid.getStudentDeleteMenuOptionMap()
                    .get(valid.returnValidOutput(STUDENT_DELETE_MENU_OPTION_DELETE.ordinal(), STUDENT_DELETE_MENU_OPTION_BACK.ordinal()));
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
            if (dataBase.getStudentByIdMap().containsKey(studentId)) {
                Student student = dataBase.getStudentByIdMap().get(studentId);
                System.out.println("학생의 현재 정보");
                printStudent(student);
                System.out.println("변경할 이름을 입력해주세요");
                String studentName = sc.nextLine();
                System.out.println("변경할 상태를 입력해주세요");
                StudentStatus studentStatus = inputStudentStatus();
                System.out.println("1. 변경하기     2. 변경 취소하기");
                yesOrNoOption = valid.getYesOrNoOptionMap()
                        .get(valid.returnValidOutput(YES_OR_NO_OPTION_YES.ordinal(), YES_OR_NO_OPTION_NO.ordinal()));

                if (yesOrNoOption == YES_OR_NO_OPTION_YES) {
                    String prevName = student.getStudentName();
                    String prevStatus = student.getStatus();
                    String status = valid.getStudentStatusStringMap().get(studentStatus);
                    changeStudentInfo(studentId, studentName, status);
                    System.out.println("변경 전");
                    System.out.printf("이름 : %s | 상태 : %s\n", prevName, prevStatus);
                    System.out.printf("이름 : %s | 상태 : %s\n", studentName, status);
                    System.out.println("변경 되었습니다.");
                }
            } else {
                System.out.printf("%s는 존재하지 않는 고유번호입니다.\n", studentId);
            }
            System.out.println("1. 계속 변경하기     2. 뒤로가기");
            yesOrNoOption = valid.getYesOrNoOptionMap()
                    .get(valid.returnValidOutput(YES_OR_NO_OPTION_YES.ordinal(), YES_OR_NO_OPTION_NO.ordinal()));
        } while (yesOrNoOption != YES_OR_NO_OPTION_NO);
    }
    // ================================== 수강생 관리 메뉴 =======================================================


    // ================================== 점수 관리 메뉴 ========================================================


    private void registerScore() {
        System.out.println(printMenuOption.getStringData(SCORE_REGISTER_MENU));
        while (true) {
            System.out.println(printMenuOption.getStringData(SCORE_INQUIRE_MENU));
            ScoreRegisterMenuOption select = valid.getScoreRegisterMenuOptionMap()
                    .get(valid.returnValidOutput(SCORE_REGISTER_MENU_OPTION_REGISTER.ordinal(), SCORE_REGISTER_MENU_OPTION_BACK.ordinal()));

            switch (select) {
                case SCORE_REGISTER_MENU_OPTION_REGISTER-> {
                    // 점수 등록하기
                }
                case SCORE_REGISTER_MENU_OPTION_BACK-> {
                    // 뒤로가기
                    return;
                }
                // ERROR
                default -> System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
            }
        }
    }

    private void inquireScore() {
        while (true) {
            System.out.println(printMenuOption.getStringData(SCORE_INQUIRE_MENU));
            StudentInquireMenuOption select = valid.getStudentInquireMenuOptionMap()
                    .get(valid.returnValidOutput(STUDENT_INQUIRE_MENU_OPTION_ID.ordinal(), STUDENT_INQUIRE_MENU_OPTION_BACK.ordinal()));

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
        ScoreChangeMenuOption select = valid.getScoreChangeMenuOptionMap()
                .get(valid.returnValidOutput(SCORE_CHANGE_MENU_OPTION_CHANGE.ordinal(), SCORE_CHANGE_MENU_OPTION_BACK.ordinal()));

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


    // ================================== 수강생 관리 헬퍼 메소드 ========================================================




    // ================================== 수강생 관리 헬퍼 메소드 ========================================================


    // ================================== 점수 관리 헬퍼 메소드 ========================================================









    // ================================== 점수 관리 헬퍼 메소드 ========================================================



    // ================================== 데이터 베이스 반영 메소드 ========================================================

    // 학생 등록
    private void saveStudentToDatabase(String studentId, String studentName, String status, List<String> subjectList) {
        Student student = new Student(studentName, status, subjectList);
        dataBase.getStudentByIdMap().put(studentId, student);
        dataBase.getStudentByStatusMap().get(status).add(student);
    }

    // 학생 정보 변경
    private void changeStudentInfo(String studentId, String studentName, String status) {
        Student student = dataBase.getStudentByIdMap().get(studentId);
        if (!student.getStatus().equals(status)) {
            dataBase.getStudentByStatusMap().get(student.getStatus()).remove(student);
            student.setStatus(status);
            dataBase.getStudentByStatusMap().get(status).add(student);
        }
        if (!student.getStudentName().equals(studentName)) {
            student.setStudentName(studentName);
        }
    }

    // 학생 삭제
    private void deleteStudent (Student student) {
        String studentId = student.getStudentId();

        dataBase.getStudentByStatusMap().get(student.getStatus()).remove(student);
        for (String subjectName : student.getSubjectList()) {
            String key = studentId + subjectName;
            dataBase.getSubjectScoreMap().remove(key);
        }
    }
//
//    // 점수 등록
//    private void inputSubjectScore(String key, List<SubjectScore> subjectScoreList) {
//        for (SubjectScore subjectScore : subjectScoreList) {
//            subjectScoreMap.get(key).add(subjectScore);
//        }
//    }

    // ================================== 데이터 베이스 반영 메소드 ========================================================


    // ====================================== 중복코드 제거 메소드 =======================================================


    private void printList(List<String> list) {
        if (list.size() == 0) {
            return;
        }
        for (int i = 0; i < list.size(); ++i) {
            System.out.printf("%d : %s | ", i + 1, list.get(i));
            if ((i + 1) % 5 == 0 && (i + 1) != list.size()) {
                System.out.println();
            }
        }
        System.out.println();
    }
    private void printEnumList(List<String> list) {
        if (list.size() == 0) {
            return;
        }
        for (int i = 1; i < list.size(); ++i) {
            System.out.printf("%d : %s | ", i, list.get(i));
            if (i % 5 == 0 && i != list.size() - 1) {
                System.out.println();
            }
        }
        System.out.println();
    }

    private void printEnumSubjectList(String studentId, List<String> list) {
        if (list.size() == 0) {
            return;
        }
        for (int i = 1; i < list.size(); ++i) {
            String subjectName = list.get(i);
            String key = studentId + subjectName;
            if (valid.getSubjectSet().contains(key)) {
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
        System.out.println(student.getStudentId());
        System.out.println(student.getStudentName());
        System.out.println(student.getStatus());
        printList(student.getSubjectList());
    }
    // ====================================== 중복코드 제거 메소드 =======================================================
}
