package Service;
import Data.DataBase;
import Util.MenuOption;
import Util.Number;
import Util.PrintMenuOption;
import Util.SelectNumber;
import java.util.Scanner;
import static Util.SelectNumber.*;


// App
// "ID : %-5s 학생 | %-10d | 필수 과목 : %-20s | 선택 과목 : %-20s | 상태 : %-5s"
public class Management{
    private final Scanner sc = new Scanner(System.in);
    private final DataBase dataBase = new DataBase();
    private final PrintMenuOption printMenuOption = new PrintMenuOption();
    private final Number number = new Number();

    public void run() {
        mainMenu();
    }


    private void mainMenu() {
        while (true) {
            System.out.println(printMenuOption.getStringData(MenuOption.MAIN_MENU));
            SelectNumber select = selectInput(ONE.ordinal(), THREE.ordinal());
            if (select == THREE) {
                System.out.println("프로그램 종료");
            }
            switch (select) {
                case ONE-> {
                    studentMenu();
                }
                case TWO-> {
                    scoreMenu();
                }
            }
        }
    }
    // 수강생 관리 메뉴
    private void studentMenu() {
        System.out.println(printMenuOption.getStringData(MenuOption.STUDENT_MAIN_MENU));
        // 1-> registerStudentMenu()
        // 2-> inquireStudentMenu()
        // 3-> changeStudentMenu()
        // 4-> deleteStudentMenu()
        SelectNumber select = selectInput(ONE.ordinal(), FIVE.ordinal());
        switch (select) {
            case ONE-> {
                registerStudent();
            }
            case TWO-> {
                inquireStudent();
            }
            case THREE-> {
                changeStudent();
            }
            case FOUR-> {
                deleteStudent();
            }
            case FIVE-> {
                return;
            }
        }
    }

    private void registerStudent() {
        System.out.println(printMenuOption.getStringData(MenuOption.STUDENT_REGISTER_MENU));
    }

    private void inquireStudent() {
        System.out.println(printMenuOption.getStringData(MenuOption.STUDENT_INQUIRE_MENU));
        SelectNumber select = selectInput(ONE.ordinal(), TWO.ordinal());
        switch (select) {
            case ONE-> {
                // 1--> id로 조회
            }
            case TWO-> {
                // 2--> 한 상태에 대해 조회
            }
        }
    }

    private void changeStudent() {
        System.out.println(printMenuOption.getStringData(MenuOption.STUDENT_CHANGE_MENU));
    }

    private void deleteStudent() {
        System.out.println(printMenuOption.getStringData(MenuOption.STUDENT_DELETE_MENU));
    }
    // 수강생 관련 메뉴 끝

    //------------------------------------------------------------------------------------------------------------------
    // 점수 관리 메뉴
    private void scoreMenu() {
        System.out.println(printMenuOption.getStringData(MenuOption.SCORE_MAIN_MENU));
        SelectNumber select = selectInput(ONE.ordinal(), THREE.ordinal());
        switch (select) {
            case ONE-> {
                registerScore();
            }
            case TWO-> {
                inquireScore();
            }
            case THREE-> {
                changeScore();
            }
        }
        // 1->registerScoreMenu
        // 2->inquireScoreMenu
        // 3->changeScoreMenu
    }
    private void registerScore() {
        System.out.println(printMenuOption.getStringData(MenuOption.SCORE_REGISTER_MENU));
    }
    private void inquireScore() {
        System.out.println(printMenuOption.getStringData(MenuOption.SCORE_INQUIRE_MENU));
        SelectNumber select = selectInput(ONE.ordinal(), THREE.ordinal());
        switch (select) {
            case ONE-> {
                registerScore();
            }
            case TWO-> {
                inquireScore();
            }
        }
    }
    private void changeScore() {
        System.out.println(printMenuOption.getStringData(MenuOption.SCORE_CHANGE_MENU));
    }

    private boolean isDigit(String number) {
        char[] chars = number.toCharArray();
        for (char ch : chars) {
            if (ch < '0' || ch > '9') return false;
        }
        return true;
    }

    // 선택사항에 대한 입력 처리 함수
    private SelectNumber selectInput(int start, int end)  {
        int input = sc.nextInt();
        return number.getSelectNumber(input);
    }
    // 점수 관리 메뉴 끝
}
