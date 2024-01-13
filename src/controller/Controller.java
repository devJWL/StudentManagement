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
import static util.options.StudentInquireMenuOption.*;
import static util.options.StudentMenuOption.*;
import static util.options.StudentRegisterMenuOption.*;
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
            StudentMenuOption select = dataBase.getStudentMenuOptionMap()
                    .get(valid.returnValidOutput(STUDENT_MENU_OPTION_REGISTER.ordinal(), STUDENT_MENU_OPTION_BACK.ordinal()));
            switch (select) {
                case STUDENT_MENU_OPTION_REGISTER-> {
                    registerStudent();
                }
                case STUDENT_MENU_OPTION_INQUIRE-> {
                    inquireStudent();
                }
                case STUDENT_MENU_OPTION_CHANGE-> {
                    changeStudent();
                }
                case STUDENT_MENU_OPTION_DELETE-> {
                    deleteStudent();
                }
                case STUDENT_MENU_OPTION_BACK-> {
                    return;
                }
                // ERROR
                default ->  {
                    System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
                }
            }
        }
    }

    private void registerStudent() {
        while(true) {
            System.out.println(printMenuOption.getStringData(STUDENT_REGISTER_MENU));
            StudentRegisterMenuOption select = dataBase.getStudentRegisterMenuOptionMap()
                    .get(valid.returnValidOutput(STUDENT_REGISTER_MENU_OPTION_ERROR.ordinal(), STUDENT_REGISTER_MENU_OPTION_BACK.ordinal()));

            switch (select) {
                case STUDENT_REGISTER_MENU_OPTION_REGISTER-> {
                    registerStudentHelper();
                }
                case STUDENT_REGISTER_MENU_OPTION_BACK-> {
                    return;
                }
                // ERROR
                default ->  {
                    System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
                }
            }
        }
    }


    private void inquireStudent() {
        while(true) {
            System.out.println(printMenuOption.getStringData(STUDENT_INQUIRE_MENU));
            StudentInquireMenuOption select = dataBase.getStudentInquireMenuOptionMap()
                    .get(valid.returnValidOutput(STUDENT_INQUIRE_MENU_OPTION_ID.ordinal(), STUDENT_INQUIRE_MENU_OPTION_BACK.ordinal()));

            switch (select) {
                case STUDENT_INQUIRE_MENU_OPTION_ID-> {
                    studentInquireByIdHelper();
                }
                case STUDENT_INQUIRE_MENU_OPTION_STATUS-> {
                    studentInquireByStatusHelper();
                }
                case STUDENT_INQUIRE_MENU_OPTION_BACK -> {
                    return;
                }
                // ERROR
                default ->  {
                    System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
                }
            }
        }
    }

    private void changeStudent() {
        System.out.println(printMenuOption.getStringData(STUDENT_CHANGE_MENU));
    }

    private void deleteStudent() {
        System.out.println(printMenuOption.getStringData(STUDENT_DELETE_MENU));
    }
    // ================================== 수강생 관리 메뉴 =======================================================


    // ================================== 점수 관리 메뉴 ========================================================
    public void scoreMenu() {
        while(true) {
            System.out.println(printMenuOption.getStringData(SCORE_MAIN_MENU));
            ScoreMenuOption select = dataBase.getScoreMenuOptionMap()
                    .get(valid.returnValidOutput(SCORE_MENU_OPTION_REGISTER.ordinal(), SCORE_MENU_OPTION_BACK.ordinal()));

            switch (select) {
                case SCORE_MENU_OPTION_REGISTER -> {
                    registerScore();
                }
                case SCORE_MENU_OPTION_INQUIRE -> {
                    inquireScore();
                }
                case SCORE_MENU_OPTION_CHANGE -> {
                    changeScore();
                }
                case SCORE_MENU_OPTION_BACK -> {
                    return;
                }
                // ERROR
                default -> {
                    System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
                }
            }
        }
    }

    private void registerScore() {
        System.out.println(printMenuOption.getStringData(SCORE_REGISTER_MENU));
        while (true) {
            System.out.println(printMenuOption.getStringData(SCORE_INQUIRE_MENU));
            ScoreRegisterMenuOption select = dataBase.getScoreRegisterMenuOptionMap()
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
                default -> {
                    System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
                }
            }
        }
    }

    private void inquireScore() {
        while (true) {
            System.out.println(printMenuOption.getStringData(SCORE_INQUIRE_MENU));
            StudentInquireMenuOption select = dataBase.getStudentInquireMenuOptionMap()
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
                default ->  {
                    System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
                }
            }
        }
    }
    private void changeScore() {
        System.out.println(printMenuOption.getStringData(SCORE_CHANGE_MENU));
        ScoreChangeMenuOption select = dataBase.getScoreChangeMenuOptionMap()
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
            default ->  {
                System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
            }
        }
    }

    // ================================== 점수 관리 메뉴 ========================================================


    // ================================== 수강생 관리 헬퍼 메소드 ========================================================
    public void registerStudentHelper() {
        System.out.println("수강생의 이름을 입력해주세요");
        String studentName = sc.nextLine();
        sc.nextLine();
        System.out.println("수강생의 상태를 선택해주세요");
        printList(dataBase.getStudentStatusList());

        StudentStatus studentStatus = StudentStatus.STUDENT_STATUS_ERROR;
        do{
            studentStatus = dataBase.getStudentStatusMap()
                    .get(valid.returnValidOutput(StudentStatus.STUDENT_STATUS_GREEN.ordinal(), StudentStatus.STUDENT_STATUS_RED.ordinal()));
            if (studentStatus == StudentStatus.STUDENT_STATUS_ERROR) {
                printMenuOption.getStringData(INPUT_ERROR_MENU);
            }
        }while(studentStatus == StudentStatus.STUDENT_STATUS_ERROR);

        String status =  dataBase.getStudentStatusStringMap().get(studentStatus);
        List<String> subjectNameList = new ArrayList<>();

        addMandatorySubjectHelper(studentName, subjectNameList);
        addSelectSubjectHelper(studentName, subjectNameList);

        System.out.println("아래의 정보로 수강생을 등록하시겠습니까?");
        System.out.printf("고유번호 : %s\n", "STU" + Student.NO);
        System.out.printf("이름 : %s\n", studentName);
        System.out.printf("상태 : %s\n", status);
        System.out.println("과목 목록");
        printList(subjectNameList);

        YesOrNoOption yesOrNoOption = dataBase.getYesOrNoOptionMap()
                .get(valid.returnValidOutput(YES_OR_NO_OPTION_YES.ordinal(), YES_OR_NO_OPTION_NO.ordinal()));
        if (yesOrNoOption == YES_OR_NO_OPTION_YES) {
            saveStudentToDatabase(studentName, status, subjectNameList);
            System.out.println("수강생이 등록되었습니다.");
        }
    }

    private void addMandatorySubjectHelper(String studentName, List<String> subjectList) {
        List<String> mandatorySubjects = new ArrayList<>();
        boolean on = true;
        do {
            System.out.println("수강생이 수강 중인 필수과목을 입력해주세요");
            System.out.println("필수과목 목록");
            printList(dataBase.getMandatorySubjecNametList());
            System.out.println("과목에 해당하는 숫자를 입력해주세요");
            MandatorySubject mandatorySubject = dataBase.getMandatorySubjectMap()
                    .get(valid.returnValidOutput(JAVA.ordinal(), MYSQL.ordinal()));

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
                    mandatorySubjects.add(subjectName);
                }
            }
            System.out.println("과목을 계속 선택하시겠습니까?");
            YesOrNoOption yesOrNoOption =  dataBase.getYesOrNoOptionMap()
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

    private void addSelectSubjectHelper(String studentName, List<String> subjectList) {
        List<String> selectSubjects = new ArrayList<>();
        boolean on = true;
        do {
            System.out.println("수강생이 수강 중인 선택과목을 입력해주세요");
            System.out.println("선택과목 목록");
            printList(dataBase.getSelectSubjectNameList());
            System.out.println("과목에 해당하는 숫자를 입력해주세요");
            SelectSubject selectSubject = dataBase.getSelectSubjectMap()
                    .get(valid.returnValidOutput(DESIGN_PATTERN.ordinal(), MONGODB.ordinal()));

            String subjectName = dataBase.getSelectSubjectStringMap().get(selectSubject);
            String key = studentName + subjectName;
            // 현재 선택한 과목이 이미 수강하는(선택됐던) 과목일 경우
            if (dataBase.getSubjectSet().contains(key)) {
                System.out.printf("%s 과목은 이미 추가된 과목입니다. 다른 과목을 선택해주세요.\n", subjectName);
            }
            else {
                dataBase.getSubjectSet().add(key);
                selectSubjects.add(subjectName);
            }

            System.out.println("과목을 계속 선택하시겠습니까?");
            YesOrNoOption yesOrNoOption =  dataBase.getYesOrNoOptionMap()
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

    private void studentInquireByIdHelper() {
        System.out.println(printMenuOption.getStringData(STUDENT_INQUIRE_MENU));
    }
    private void studentInquireByStatusHelper() {}
    // ================================== 수강생 관리 헬퍼 메소드 ========================================================


    // ================================== 점수 관리 헬퍼 메소드 ========================================================









    // ================================== 점수 관리 헬퍼 메소드 ========================================================



    // ================================== 데이터 베이스 반영 메소드 ========================================================
    // 학생 등록
    private void saveStudentToDatabase(String name, String status, List<String> subjectList) {
        Student student = new Student(name, status, subjectList);
        dataBase.getStudentList().add(student);
        dataBase.getStudentByStatusMap().get(status).add(student);
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

    // ================================== 데이터 베이스 반영 메소드 ========================================================


    // ====================================== 중복코드 제거 메소드 =======================================================
    private void printList(List<String> list) {
        for (int i = 1; i < list.size(); ++i) {
            System.out.printf("%d : %s | ", i, list.get(i));
            if (i % 5 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }
    // ====================================== 중복코드 제거 메소드 =======================================================
}
