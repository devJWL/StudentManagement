package service;
import controller.Controller;
import database.Database;
import util.Util;
import util.options.*;
import util.printMenu.PrintMenuOption;
import java.util.Scanner;

import static util.options.MainMenuOption.*;
import static util.options.ScoreMenuOption.*;
import static util.options.StudentMenuOption.*;
import static util.printMenu.MenuOption.*;



public class Management{
    private final PrintMenuOption printMenuOption = new PrintMenuOption();
    private final Scanner sc = new Scanner(System.in);
    private final Util util = new Util(sc, printMenuOption);
    private final Database dataBase = new Database();
    private final Controller controller = new Controller(dataBase, sc, printMenuOption, util);


    public void run() {
        mainMenu();
    }

    // 메인 메뉴
    private void mainMenu() {
        boolean on = true;
        while (on) {
            System.out.println(printMenuOption.getStringData(MAIN_MENU));
            MainMenuOption select = MainMenuOption
                    .get(util.returnValidOutput(MAIN_MENU_OPTION_ERROR.ordinal(), MAIN_MENU_OPTION_BACK.ordinal()));
            switch (select) {
                case MAIN_MENU_OPTION_STU-> studentMenu();
                case MAIN_MENU_OPTION_SCORE-> scoreMenu();
                case MAIN_MENU_OPTION_BACK -> on = false;
                // ERROR
                default -> System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
            }
        }
    }

    // 학생관리 메뉴
    private void studentMenu() {
        boolean on = true;
        while(on) {
            System.out.println(printMenuOption.getStringData(STUDENT_MAIN_MENU));
            StudentMenuOption select = StudentMenuOption
                    .get(util.returnValidOutput(STUDENT_MENU_OPTION_ERROR.ordinal(), STUDENT_MENU_OPTION_BACK.ordinal()));
            switch (select) {
                case STUDENT_MENU_OPTION_REGISTER -> controller.registerStudent();
                case STUDENT_MENU_OPTION_INQUIRE -> controller.inquireStudent();
                case STUDENT_MENU_OPTION_CHANGE -> controller.changeStudent();
                case STUDENT_MENU_OPTION_DELETE -> controller.deleteStudent();
                case STUDENT_MENU_OPTION_BACK -> on = false;
                // ERROR
                default -> System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
            }
        }
    }

    // 점수관리 메뉴
    private void scoreMenu() {
        boolean on = true;
        while(on) {
            System.out.println(printMenuOption.getStringData(SCORE_MAIN_MENU));
            ScoreMenuOption select = ScoreMenuOption
                    .get(util.returnValidOutput(SCORE_MENU_OPTION_ERROR.ordinal(), SCORE_MENU_OPTION_BACK.ordinal()));
            switch (select) {
                case SCORE_MENU_OPTION_REGISTER -> controller.registerScore();
                case SCORE_MENU_OPTION_INQUIRE -> controller.inquireScore();
                case SCORE_MENU_OPTION_CHANGE -> controller.changeScore();
                // 점수 삭제 구현 하기
                case SCORE_MENU_OPTION_BACK -> on = false;
                // ERROR
                default -> System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
            }
        }
    }
}
