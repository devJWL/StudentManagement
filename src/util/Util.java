package util;
import util.printMenu.PrintMenuOption;
import util.subject.MandatorySubject;
import util.subject.OptionSubject;
import java.util.*;
import static util.printMenu.MenuOption.INPUT_ERROR_MENU;


public class Util {
    private final Scanner sc;
    private final PrintMenuOption printMenuOption;
    private final Map<String, Boolean> OptionOrMandatoryMap = new HashMap<>();

    public Util(Scanner sc, PrintMenuOption printMenuOption) {
        this.sc = sc;
        this.printMenuOption = printMenuOption;
        initSelectOrMandatoryMap();
    }

    public Map<String, Boolean> getOptionOrMandatoryMap() {
        return OptionOrMandatoryMap;
    }

    // =================================== 선택 입력 예외 처리 =================================================
    public int returnValidOutput(int start, int end) {
        int returnNumber = -1;
        String input;
        do {
            System.out.print("> ");
            input = sc.nextLine();
<<<<<<< Updated upstream
<<<<<<< Updated upstream
            // 문자열의 길이가 0이 아니고, 숫자만으로 구성되어있는지 확인
            if (input.length() != 0 && isDigit(input)) {
=======
=======
>>>>>>> Stashed changes
            // 문자열의 길이가 0이 아니고, "0000"과 같은 문자열이 아니고, 숫자로만 구성
            if (input.length() > 0 && !input.startsWith("00") && isDigit(input)) {
>>>>>>> Stashed changes
                int number = Integer.parseInt(input);
                // 현재 메뉴옵션의 범위에 존재하는 숫자인지 확인
                if (number >= start && number <= end) {
                    returnNumber = number;
                }
            }
            if (returnNumber == -1) {
                System.out.println(printMenuOption.getStringData(INPUT_ERROR_MENU));
            }
        } while (returnNumber == -1);
        return returnNumber;
    }

    public boolean isDigit(String number) {
        char[] chars = number.toCharArray();
        for (char ch : chars) {
            if (ch < '0' || ch > '9') return false;
        }
        return true;
    }

    public char calcGrade(int score, boolean isMandatory) {
        char grade;
        if (isMandatory) {
            if (score >= 95) {
                grade = 'A';
            }
            else if (score >= 90){
                grade = 'B';
            }
            else if (score >= 80) {
                grade = 'C';
            }
            else if (score >= 70) {
                grade = 'D';
            }
            else if (score >= 60) {
                grade = 'F';
            }
            else {
                grade = 'N';
            }
        }
        else {
            if (score >= 90) {
                grade = 'A';
            }
            else if (score >= 80){
                grade = 'B';
            }
            else if (score >= 70) {
                grade = 'C';
            }
            else if (score >= 60) {
                grade = 'D';
            }
            else if (score >= 50) {
                grade = 'F';
            }
            else {
                grade = 'N';
            }
        }
        return grade;
    }

    private void initSelectOrMandatoryMap() {
        OptionSubject[] optionSubjects = OptionSubject.values();
        MandatorySubject[] mandatorySubjects = MandatorySubject.values();

        for (OptionSubject optionSubject : optionSubjects) {
            OptionOrMandatoryMap.put(optionSubject.getSubjectName(), false);
        }
        for (MandatorySubject mandatorySubject : mandatorySubjects) {
            OptionOrMandatoryMap.put(mandatorySubject.getSubjectName(), true);
        }
    }

    // =================================== 선택 입력 예외 처리 =================================================
}
