package Service;
import Data.DataBase;
import Util.MenuOption;
import Util.PrintMenuOption;
import Util.SelectNumber;

import java.util.Scanner;


// App
// "ID : %-5s 학생 | %-10d | 필수 과목 : %-20s | 선택 과목 : %-20s | 상태 : %-5s"
public class Management{
    private final Scanner sc = new Scanner(System.in);
    private final DataBase dataBase = new DataBase();
    private final PrintMenuOption printMenuOption = new PrintMenuOption();

    public void run() {
        mainMenu();
    }

    // 선택사항에 대한 입력 처리 함수
    private SelectNumber selectInput()  {
        String input = sc.nextLine();
        return SelectNumber.FOUR;
    }

    private void mainMenu() {
        while (true) {
            System.out.println(printMenuOption.getStringData(MenuOption.MAIN_MENU));
            SelectNumber select = selectInput();
            if (select == SelectNumber.TREE) {
                System.out.println("프로그램 종료");
            }
            switch (select) {
                // 수강생 관리
                // 점수 관리
                // 프로그램 종료
            }
        }
    }
    // 수강생 관리 메뉴
    private void studentMenu() {
        // 1-> registerStudentMenu()
        // 2-> inquireStudentMenu()
        // 3-> changeStudentMenu()
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

    //------------------------------------------------------------------------------------------------------------------
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
    private boolean isDigit(String number){
        char[] chars = number.toCharArray();
        for (char ch : chars) {
            if (ch < '0' || ch > '9') return false;
        }
        return true;
    }

    private int inputSelect(String number){
        if(isDigit(number))
            return Integer.parseInt(number);
        else
            return 0;
    }
    // 점수 관리 메뉴 끝
}
