package service;
import controller.Controller;
import database.DataBase;
import util.Valid;
import util.options.*;
import util.printMenu.PrintMenuOption;

import java.util.Scanner;
import static util.printMenu.MenuOption.*;
import static util.options.MainMenuOption.*;




// App
// "ID : %-5s 학생 | %-10d | 필수 과목 : %-20s | 선택 과목 : %-20s | 상태 : %-5s"
public class Management{
    private final PrintMenuOption printMenuOption = new PrintMenuOption();
    private final Scanner sc = new Scanner(System.in);
    private final Valid valid = new Valid(sc, printMenuOption);
    private final DataBase dataBase = new DataBase(valid);
    private final Controller controller = new Controller(dataBase, sc, printMenuOption, valid);


    public void run() {
        mainMenu();
    }

    // 메인 메뉴
    private void mainMenu() {
        while (true) {
            System.out.println(printMenuOption.getStringData(MAIN_MENU));
            MainMenuOption select = MainMenuOption.get(valid.returnValidOutput(MAIN_MENU_OPTION_STU.ordinal(), MAIN_MENU_OPTION_EXIT.ordinal()));
            switch (select) {
                case MAIN_MENU_OPTION_STU-> {
                    controller.studentMenu();
                }
                case MAIN_MENU_OPTION_SCORE-> {
                    controller.scoreMenu();
                }
                case MAIN_MENU_OPTION_EXIT -> {
                    return;
                }
                // ERROR
                default ->  {
                    System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
                }
            }
        }
    }
}
