package service;
import controller.Controller;
import database.DataBase;
import resources.Student;
import resources.Subject;
import util.Vaild;
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
    private final DataBase dataBase = new DataBase();
    private final Scanner sc = new Scanner(System.in);
    private final PrintMenuOption printMenuOption = new PrintMenuOption();
    private final Vaild vaild = new Vaild(dataBase, sc, printMenuOption);
    private final Controller controller = new Controller(dataBase, sc, printMenuOption, vaild);


    public void run() {
        mainMenu();
    }
    // 메인 메뉴
    private void mainMenu() {
        while (true) {
            System.out.println(printMenuOption.getStringData(MAIN_MENU));
            MainMenuOption select = dataBase.getMainMenuOptionMap()
                    .get(vaild.returnVaildOutput(MAIN_MENU_OPTION_STU.ordinal(), MAIN_MENU_OPTION_EXIT.ordinal()));

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
                    .get(vaild.returnVaildOutput(STUDENT_MENU_OPTION_REGISTER.ordinal(), STUDENT_MENU_OPTION_BACK.ordinal()));

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
                    .get(vaild.returnVaildOutput(STUDENT_REGISTER_MENU_OPTION_ERROR.ordinal(), STUDENT_REGISTER_MENU_OPTION_BACK.ordinal()));

            switch (select) {
                case STUDENT_REGISTER_MENU_OPTION_REGISTER-> {
                    controller.registerStudentHelper();
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


    private void inquireStudent() {
        while(true) {
            System.out.println(printMenuOption.getStringData(STUDENT_INQUIRE_MENU));
            StudentInquireMenuOption select = dataBase.getStudentInquireMenuOptionMap()
                    .get(vaild.returnVaildOutput(STUDENT_INQUIRE_MENU_OPTION_ID.ordinal(), STUDENT_INQUIRE_MENU_OPTION_BACK.ordinal()));

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
                    .get(vaild.returnVaildOutput(SCORE_MENU_OPTION_REGISTER.ordinal(), SCORE_MENU_OPTION_BACK.ordinal()));

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
                    .get(vaild.returnVaildOutput(SCORE_REGISTER_MENU_OPTION_REGISTER.ordinal(), SCORE_REGISTER_MENU_OPTION_BACK.ordinal()));

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
                    .get(vaild.returnVaildOutput(STUDENT_INQUIRE_MENU_OPTION_ID.ordinal(), STUDENT_INQUIRE_MENU_OPTION_BACK.ordinal()));

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
                .get(vaild.returnVaildOutput(SCORE_CHANGE_MENU_OPTION_CHANGE.ordinal(), SCORE_CHANGE_MENU_OPTION_BACK.ordinal()));

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


    // 점수 관리 메뉴 끝
}
