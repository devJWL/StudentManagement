package util;
import util.printMenu.PrintMenuOption;
import java.util.*;
import static util.printMenu.MenuOption.*;


public class Valid {
    private final Scanner sc;
    private final PrintMenuOption printMenuOption;

    public Valid(Scanner sc, PrintMenuOption printMenuOption) {
        this.sc = sc;
        this.printMenuOption = printMenuOption;
    }



    // =================================== 선택 입력 예외 처리 =================================================
    public int returnValidOutput(int start, int end) {
        int returnNumber = 0;
        String input;
        do {
            input = sc.nextLine();
            // 문자열의 길이가 0이 아니고, 숫자만으로 구성되어있는지 확인
            if (input.length() != 0 && isDigit(input)) {
                int number = Integer.parseInt(input);
                // 현재 메뉴옵션의 범위에 존재하는 숫자인지 확인
                if (number >= start && number <= end) {
                    returnNumber = number;
                }
            }
            if (returnNumber == 0) {
                System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
            }
        } while (returnNumber == 0);
        return returnNumber;
    }

    private boolean isDigit(String number) {
        char[] chars = number.toCharArray();
        for (char ch : chars) {
            if (ch < '0' || ch > '9') return false;
        }
        return true;
    }
    // =================================== 선택 입력 예외 처리 =================================================
}
