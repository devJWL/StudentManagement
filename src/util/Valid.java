package util;
import util.options.PrintMenuOption;
import java.util.Scanner;
import static util.options.MenuOption.INPUT_ERROR_MENU;


public class Valid {
    private final Scanner sc;
    private final PrintMenuOption printMenuOption;

    public Valid(Scanner sc, PrintMenuOption printMenuOption) {
        this.sc = sc;
        this.printMenuOption = printMenuOption;
    }

    // 선택사항에 대한 입력 처리 함수
    public int returnValidOutput(int start, int end) {
        int returnNumber = 0;
        String input;
        do {
            input = sc.next();
            // 숫자만으로 구성되어있는지 확인
            if (isDigit(input)) {
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
    }

    private boolean isDigit(String number) {
        char[] chars = number.toCharArray();
        for (char ch : chars) {
            if (ch < '0' || ch > '9') return false;
        }
        return true;
    }
}
