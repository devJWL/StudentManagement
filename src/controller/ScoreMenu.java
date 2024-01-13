package controller;

import database.DataBase;
import util.Vaild;
import util.options.*;

import java.util.Scanner;

import static util.options.MenuOption.*;
import static util.options.ScoreChangeMenuOption.SCORE_CHANGE_MENU_OPTION_BACK;
import static util.options.ScoreChangeMenuOption.SCORE_CHANGE_MENU_OPTION_CHANGE;
import static util.options.ScoreMenuOption.SCORE_MENU_OPTION_BACK;
import static util.options.ScoreMenuOption.SCORE_MENU_OPTION_REGISTER;
import static util.options.ScoreRegisterMenuOption.SCORE_REGISTER_MENU_OPTION_BACK;
import static util.options.ScoreRegisterMenuOption.SCORE_REGISTER_MENU_OPTION_REGISTER;
import static util.options.StudentInquireMenuOption.STUDENT_INQUIRE_MENU_OPTION_BACK;
import static util.options.StudentInquireMenuOption.STUDENT_INQUIRE_MENU_OPTION_ID;

public class ScoreMenu {
    private final DataBase dataBase;
    private final Scanner sc;
    private final PrintMenuOption printMenuOption;
    private final Vaild vaild;

    public ScoreMenu(DataBase dataBase, Scanner sc, PrintMenuOption printMenuOption, Vaild vaild) {
        this.dataBase = dataBase;
        this.sc = sc;
        this.printMenuOption = printMenuOption;
        this.vaild = vaild;
    }

    // 점수 관리 메뉴
    public void scoreMenu() {
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
