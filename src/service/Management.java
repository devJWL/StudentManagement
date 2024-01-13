package service;
import controller.Controller;
import controller.ScoreMenu;
import controller.StudentMenu;
import database.DataBase;
import util.Vaild;
import util.options.*;
import java.util.Scanner;
import static util.options.MenuOption.*;
import static util.options.MainMenuOption.*;




// App
// "ID : %-5s 학생 | %-10d | 필수 과목 : %-20s | 선택 과목 : %-20s | 상태 : %-5s"
public class Management{
    private final DataBase dataBase = new DataBase();
    private final Scanner sc = new Scanner(System.in);
    private final PrintMenuOption printMenuOption = new PrintMenuOption();
    private final Vaild vaild = new Vaild(dataBase, sc, printMenuOption);
    private final Controller controller = new Controller(dataBase, sc, printMenuOption, vaild);
    private final StudentMenu studentMenu = new StudentMenu(dataBase, sc, printMenuOption, vaild);
    private final ScoreMenu scoreMenu = new ScoreMenu(dataBase, sc, printMenuOption, vaild);

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
                    studentMenu.studentMenu();
                }
                case MAIN_MENU_OPTION_SCORE-> {
                    scoreMenu.scoreMenu();
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
    //------------------------------------------------------------------------------------------------------------------


}
