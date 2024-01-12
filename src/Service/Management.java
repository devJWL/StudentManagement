package Service;
import Data.DataBase;
import Util.Options.*;

import java.util.Scanner;


import static Util.Options.MenuOption.*;
import static Util.Options.MainMenuOption.*;
import static Util.Options.StudentInquireMenuOption.*;
import static Util.Options.StudentMenuOption.*;
import static Util.Options.StudentRegisterMenuOption.*;


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
                // MAIN_MENU_OPTION_ERROR
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
                // STUDENT_MENU_OPTION_ERROR
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

                }
                case STUDENT_REGISTER_MENU_OPTION_BACK-> {
                    return;
                }
                // MAIN_MENU_OPTION_ERROR
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
        System.out.println(printMenuOption.getStringData(SCORE_MAIN_MENU));

        // 1->registerScoreMenu
        // 2->inquireScoreMenu
        // 3->changeScoreMenu
    }
    private void registerScore() {
        System.out.println(printMenuOption.getStringData(SCORE_REGISTER_MENU));
    }
    private void inquireScore() {
//        System.out.println(printMenuOption.getStringData(SCORE_INQUIRE_MENU));
//        SelectNumber select = returnVaildOutput(ONE.ordinal(), THREE.ordinal());
//        switch (select) {
//            case ONE-> {
//                registerScore();
//            }
//            case TWO-> {
//                inquireScore();
//            }
//        }
    }
    private void changeScore() {
        System.out.println(printMenuOption.getStringData(SCORE_CHANGE_MENU));
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
