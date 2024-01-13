package controller;

import database.DataBase;
import util.Vaild;
import util.options.PrintMenuOption;
import util.options.StudentInquireMenuOption;
import util.options.StudentMenuOption;
import util.options.StudentRegisterMenuOption;

import java.util.Scanner;

import static util.options.MenuOption.*;
import static util.options.MenuOption.STUDENT_DELETE_MENU;
import static util.options.StudentInquireMenuOption.STUDENT_INQUIRE_MENU_OPTION_BACK;
import static util.options.StudentInquireMenuOption.STUDENT_INQUIRE_MENU_OPTION_ID;
import static util.options.StudentMenuOption.STUDENT_MENU_OPTION_BACK;
import static util.options.StudentMenuOption.STUDENT_MENU_OPTION_REGISTER;
import static util.options.StudentRegisterMenuOption.STUDENT_REGISTER_MENU_OPTION_BACK;
import static util.options.StudentRegisterMenuOption.STUDENT_REGISTER_MENU_OPTION_ERROR;

public class StudentMenu {
    private final DataBase dataBase;
    private final Scanner sc;
    private final PrintMenuOption printMenuOption;
    private final Vaild vaild;

    public StudentMenu(DataBase dataBase, Scanner sc, PrintMenuOption printMenuOption, Vaild vaild) {
        this.dataBase = dataBase;
        this.sc = sc;
        this.printMenuOption = printMenuOption;
        this.vaild = vaild;
    }


    // 수강생 관리 메뉴
    public void studentMenu() {
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

}
