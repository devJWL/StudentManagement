package service;
import database.DataBase;
import resources.Student;
import resources.Subject;
import util.options.*;
import util.subject.MandatorySubject;
import util.subject.SelectSubject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static util.options.MenuOption.*;
import static util.options.MainMenuOption.*;
import static util.options.ScoreChangeMenuOption.*;
import static util.options.ScoreMenuOption.*;
import static util.options.ScoreRegisterMenuOption.*;
import static util.options.StudentInquireMenuOption.*;
import static util.options.StudentMenuOption.*;
import static util.options.StudentRegisterMenuOption.*;
import static util.options.YesOrNoOption.*;
import static util.subject.MandatorySubject.*;
import static util.subject.SelectSubject.*;



// App
// "ID : %-5s 학생 | %-10d | 필수 과목 : %-20s | 선택 과목 : %-20s | 상태 : %-5s"
public class Management{
    private final Scanner sc = new Scanner(System.in);
    private final DataBase dataBase = new DataBase();
    private final PrintMenuOption printMenuOption = new PrintMenuOption();

    public void run() {
        mainMenu();
    }


    // 메인 메뉴
    private void mainMenu() {
        while (true) {
            System.out.println(printMenuOption.getStringData(MAIN_MENU));
            MainMenuOption select = dataBase.getMainMenuOptionMap()
                    .get(returnVaildOutput(MAIN_MENU_OPTION_STU.ordinal(), MAIN_MENU_OPTION_EXIT.ordinal()));

            switch (select) {
                case MAIN_MENU_OPTION_STU-> {
                    studentMenu();
                }
                case MAIN_MENU_OPTION_SCORE-> {
                    scoreMenu();
                }
                case MAIN_MENU_OPTION_EXIT -> {
                    return;
                }
                // ERROR
                default ->  {
                    printMenuOption.getStringData(INPUT_ERROR_MENU);
                }
            }
        }
    }
    // 수강생 관리 메뉴
    private void studentMenu() {
        while(true) {
            System.out.println(printMenuOption.getStringData(STUDENT_MAIN_MENU));
            StudentMenuOption select = dataBase.getStudentMenuOptionMap()
                    .get(returnVaildOutput(STUDENT_MENU_OPTION_REGISTER.ordinal(), STUDENT_MENU_OPTION_BACK.ordinal()));

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
                    .get(returnVaildOutput(STUDENT_REGISTER_MENU_OPTION_ERROR.ordinal(), STUDENT_REGISTER_MENU_OPTION_BACK.ordinal()));

            switch (select) {
                case STUDENT_REGISTER_MENU_OPTION_REGISTER-> {
                    registerStudentHelper();
                }
                case STUDENT_REGISTER_MENU_OPTION_BACK-> {
                    return;
                }
                // ERROR
                default ->  {
                    printMenuOption.getStringData(INPUT_ERROR_MENU);
                }
            }
        }
    }

    private void registerStudentHelper() {
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
        YesOrNoOption yesOrNoOption = yesNoInputHelper();
        if (yesOrNoOption == YES_OR_NO_OPTION_YES) {
            dataBase.createStudent(studentName, status, subjectList);
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
                    .get(returnVaildOutput(JAVA.ordinal(), MYSQL.ordinal()));

            if (mandatorySubject == MANDATORY_SUBJECT_ERROR) {
                System.out.println("잘못된 입력 혹은 범위 밖의 숫자를 입력하셨습니다.");
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
            YesOrNoOption yesOrNoOption = yesNoInputHelper();
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
                    .get(returnVaildOutput(DESIGN_PATTERN.ordinal(), MONGODB.ordinal()));

            if (selectSubject == SELECT_SUBJECT_ERROR) {
                System.out.println("잘못된 입력 혹은 범위 밖의 숫자를 입력하셨습니다.");
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
            YesOrNoOption yesOrNoOption = yesNoInputHelper();
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


    private YesOrNoOption yesNoInputHelper() {
        YesOrNoOption yesOrNoOption = YES_OR_NO_OPTION_ERROR;
        do {
            System.out.println("1. 네    2. 아니요");
            yesOrNoOption = dataBase.getYesOrNoOptionMap()
                    .get(returnVaildOutput(YES_OR_NO_OPTION_YES.ordinal(), YES_OR_NO_OPTION_NO.ordinal()));
            if (yesOrNoOption == YES_OR_NO_OPTION_ERROR) {
                System.out.println("잘못된 입력이거나 범위 밖에 값을 입력하셨습니다.");
            }
        }while(yesOrNoOption == YES_OR_NO_OPTION_ERROR);
        return yesOrNoOption;
    }

    private void inquireStudent() {
        while(true) {
            System.out.println(printMenuOption.getStringData(STUDENT_INQUIRE_MENU));
            StudentInquireMenuOption select = dataBase.getStudentInquireMenuOptionMap()
                    .get(returnVaildOutput(STUDENT_INQUIRE_MENU_OPTION_ID.ordinal(), STUDENT_INQUIRE_MENU_OPTION_BACK.ordinal()));

            switch (select) {
                case STUDENT_INQUIRE_MENU_OPTION_ID-> {
                    // 1--> id로 조회
                }
                case STUDENT_INQUIRE_MENU_OPTION_STATUS-> {
                    // 2--> 한 상태에 대해 조회
                }
                case STUDENT_INQUIRE_MENU_OPTION_BACK -> {
                    return;
                }
                // ERROR
                default ->  {
                    printMenuOption.getStringData(INPUT_ERROR_MENU);
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
    // 수강생 관련 메뉴 끝

    //------------------------------------------------------------------------------------------------------------------

    // 점수 관리 메뉴
    private void scoreMenu() {
        while(true) {
            System.out.println(printMenuOption.getStringData(SCORE_MAIN_MENU));
            ScoreMenuOption select = dataBase.getScoreMenuOptionMap()
                    .get(returnVaildOutput(SCORE_MENU_OPTION_REGISTER.ordinal(), SCORE_MENU_OPTION_BACK.ordinal()));

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
                    printMenuOption.getStringData(INPUT_ERROR_MENU);
                }
            }
        }
    }

    private void registerScore() {
        System.out.println(printMenuOption.getStringData(SCORE_REGISTER_MENU));
        while (true) {
            System.out.println(printMenuOption.getStringData(SCORE_INQUIRE_MENU));
            ScoreRegisterMenuOption select = dataBase.getScoreRegisterMenuOptionMap()
                    .get(returnVaildOutput(SCORE_REGISTER_MENU_OPTION_REGISTER.ordinal(), SCORE_REGISTER_MENU_OPTION_BACK.ordinal()));

            switch (select) {
                case SCORE_REGISTER_MENU_OPTION_REGISTER-> {
                    // 점수 등록하기
                }
                case SCORE_REGISTER_MENU_OPTION_BACK-> {
                    // 뒤로가기
                }
                // ERROR
                default -> {
                    return;
                }
            }
        }
    }

    private void inquireScore() {
        while (true) {
            System.out.println(printMenuOption.getStringData(SCORE_INQUIRE_MENU));
            StudentInquireMenuOption select = dataBase.getStudentInquireMenuOptionMap()
                    .get(returnVaildOutput(STUDENT_INQUIRE_MENU_OPTION_ID.ordinal(), STUDENT_INQUIRE_MENU_OPTION_BACK.ordinal()));

            switch (select) {
                case STUDENT_INQUIRE_MENU_OPTION_ID-> {
                    // 1. id로 검색
                }
                case STUDENT_INQUIRE_MENU_OPTION_STATUS-> {
                    // 2. 상태로 검색
                }
                case STUDENT_INQUIRE_MENU_OPTION_BACK-> {
                    // 3. 뒤로가기
                }
                // ERROR
                default ->  {
                    return;
                }
            }
        }
    }
    private void changeScore() {
        System.out.println(printMenuOption.getStringData(SCORE_CHANGE_MENU));
        ScoreChangeMenuOption select = dataBase.getScoreChangeMenuOptionMap()
                .get(returnVaildOutput(SCORE_CHANGE_MENU_OPTION_CHANGE.ordinal(), SCORE_CHANGE_MENU_OPTION_BACK.ordinal()));

        switch (select) {
            case SCORE_CHANGE_MENU_OPTION_CHANGE-> {
                // 1. 변경하기
            }
            case SCORE_CHANGE_MENU_OPTION_BACK-> {
                // 2. 뒤로가기
            }
            // ERROR
            default ->  {
                return;
            }
        }
    }

    private boolean isDigit(String number) {
        char[] chars = number.toCharArray();
        for (char ch : chars) {
            if (ch < '0' || ch > '9') return false;
        }
        return true;
    }

    // 선택사항에 대한 입력 처리 함수
    private int returnVaildOutput(int start, int end)  {
        int returnNumber = 0;
        String input = sc.next();
        // 숫자만으로 구성되어있는지 확인
        if (isDigit(input)) {
            int number = Integer.parseInt(input);
            // 현재 메뉴옵션의 범위에 존재하는 숫자인지 확인
            if (number >= start && number <= end){
                returnNumber = number;
            }
        }
        return returnNumber;
    }
    // 점수 관리 메뉴 끝
}