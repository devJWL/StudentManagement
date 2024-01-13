package util;

import database.DataBase;
import util.options.MenuOption;
import util.options.PrintMenuOption;
import util.options.YesOrNoOption;

import java.util.Scanner;

import static util.options.YesOrNoOption.*;
import static util.options.YesOrNoOption.YES_OR_NO_OPTION_ERROR;

public class Vaild {
    private final DataBase dataBase;
    private final Scanner sc;
    private final PrintMenuOption printMenuOption;

    public Vaild(DataBase dataBase, Scanner sc, PrintMenuOption printMenuOption) {
        this.dataBase = dataBase;
        this.sc = sc;
        this.printMenuOption = printMenuOption;
    }

    public YesOrNoOption yesNoInputHelper() {
        YesOrNoOption yesOrNoOption = YES_OR_NO_OPTION_ERROR;
        do {
            System.out.println("1. 네    2. 아니요");
            yesOrNoOption = dataBase.getYesOrNoOptionMap()
                    .get(returnVaildOutput(YES_OR_NO_OPTION_YES.ordinal(), YES_OR_NO_OPTION_NO.ordinal()));
            if (yesOrNoOption == YES_OR_NO_OPTION_ERROR) {
                printMenuOption.getStringData(MenuOption.INPUT_ERROR_MENU);
            }
        }while(yesOrNoOption == YES_OR_NO_OPTION_ERROR);
        return yesOrNoOption;
    }

    // 선택사항에 대한 입력 처리 함수
    public int returnVaildOutput(int start, int end)  {
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

    private boolean isDigit(String number) {
        char[] chars = number.toCharArray();
        for (char ch : chars) {
            if (ch < '0' || ch > '9') return false;
        }
        return true;
    }
}
