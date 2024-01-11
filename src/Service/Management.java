package Service;
import Data.DataBase;
import Util.MenuOption;
import Util.PrintMenuOption;
import Util.MenuOption.*;


// App
// "ID : %-5s 학생 | %-10d | 필수 과목 : %-20s | 선택 과목 : %-20s | 상태 : %-5s"
public class Management{
    public void run() {
        DataBase dataBase = new DataBase();
        PrintMenuOption printMenuOption = new PrintMenuOption();
        System.out.println(printMenuOption.getStringData(MenuOption.MAIN_MENU));
        mainMenu();
    }
    private void mainMenu() {
        // 1 -> studentMenu()
        // 2 -> scoreMenu()
    }
    // 수강생 관리 메뉴
    private void studentMenu() {
        // 1 -> registerStudentMenu()
        // 2->inquireStudentMenu()
        // 3->changeStudentMenu()
        // 4-> deleteStudentMenu()
    }

    private void registerStudentMenu() {}
    private void inquireStudentMenu() {
        // 1--> id로 조회
        // 2--> 한 상태에 대해 조회
    }
    private void changeStudentMenu() {}
    private void deleteStudentMenu() {}
    // 수강생 관련 메뉴 끝


    // 점수 관리 메뉴
    private void scoreMenu() {
        // 1->registerScoreMenu
        // 2->inquireScoreMenu
        // 3->changeScoreMenu
    }
    private void registerScoreMenu() {}
    private void inquireScoreMenu() {
        // 1-> id로 조회
        // 2-> 한 상태에 대해 조회
    }
    private void changeScoreMenu() {}

    // 점수 관리 메뉴 끝
}
