package util;
import util.options.*;
import util.subject.MandatorySubject;
import util.subject.SelectSubject;

import java.util.*;

import static util.options.MenuOption.INPUT_ERROR_MENU;


public class Valid {
    private final Scanner sc;
    private final PrintMenuOption printMenuOption;

    public Valid(Scanner sc, PrintMenuOption printMenuOption) {
        this.sc = sc;
        this.printMenuOption = printMenuOption;
        validInit();
    }

    // ================================= Enum 초기화 ===============================================
    private final List<String> studentStatusList = new ArrayList<>();     // 모든 감정상태를 가지고 있는 List

    public List<String> getStudentStatusList() {
        return studentStatusList;
    }

    // ================================== 수강생 상태 EnumClass HashMap ============================
    private final Map<Integer, StudentStatus> studentStatusMap = new HashMap<>();
    private final Map<StudentStatus, String> studentStatusStringMap = new HashMap<>();

    public Map<Integer, StudentStatus> getStudentStatusMap() {
        return studentStatusMap;
    }

    public Map<StudentStatus, String> getStudentStatusStringMap() {
        return studentStatusStringMap;
    }

    // ================================== 수강생 상태 EnumClass HashMap ============================

    // ================================= Enum 초기화 ===============================================

    // ============================= 데이터 베이스 초기화 및 출력을 위한 자료 =======================================
    private final List<String> mandatorySubjectNameList = new ArrayList<>();   // 모든 필수과목 List
    private final List<String> selectSubjectNameList = new ArrayList<>();      // 모든 선택과목 List

    public List<String> getMandatorySubjecNametList() {
        return mandatorySubjectNameList;
    }
    public List<String> getSelectSubjectNameList() {
        return selectSubjectNameList;
    }

    // ============================= 데이터 베이스 초기화 및 출력을 위한 자료 =======================================

    // ======================================= valid =======================================================
    // key : studentId + subjectName    value : 과목이름
    private final Set<String> subjectSet = new HashSet<>();   // 특정학생의 과목 수강여부를 확인하기위한 Set
    public Set<String> getSubjectSet() {
        return subjectSet;
    }

    // ================================== 메뉴 관련 EnumClass HashMap =============================
    private final Map<Integer, MainMenuOption> mainMenuOptionMap = new HashMap<>();
    private final Map<Integer, StudentMenuOption> studentMenuOptionMap = new HashMap<>();
    private final Map<Integer, StudentRegisterMenuOption> studentRegisterMenuOptionMap = new HashMap<>();
    private final Map<Integer, StudentInquireMenuOption> studentInquireMenuOptionMap = new HashMap<>();
    private final Map<Integer, ScoreMenuOption> scoreMenuOptionMap = new HashMap<>();
    private final Map<Integer, ScoreRegisterMenuOption> scoreRegisterMenuOptionMap = new HashMap<>();
    private final Map<Integer, ScoreInquireMenuOption> scoreInquireMenuOptionMap = new HashMap<>();
    private final Map<Integer, ScoreChangeMenuOption> scoreChangeMenuOptionMap = new HashMap<>();
    private final Map<Integer, YesOrNoOption> yesOrNoOptionMap = new HashMap<>();


    public Map<Integer, MainMenuOption> getMainMenuOptionMap() {
        return mainMenuOptionMap;
    }
    public Map<Integer, StudentMenuOption> getStudentMenuOptionMap() {
        return studentMenuOptionMap;
    }
    public Map<Integer, StudentRegisterMenuOption> getStudentRegisterMenuOptionMap() {
        return studentRegisterMenuOptionMap;
    }
    public Map<Integer, StudentInquireMenuOption> getStudentInquireMenuOptionMap() {
        return studentInquireMenuOptionMap;
    }
    public Map<Integer, ScoreMenuOption> getScoreMenuOptionMap() {
        return scoreMenuOptionMap;
    }

    public Map<Integer, ScoreRegisterMenuOption> getScoreRegisterMenuOptionMap() {
        return scoreRegisterMenuOptionMap;
    }

    public Map<Integer, ScoreInquireMenuOption> getScoreInquireMenuOptionMap() {
        return scoreInquireMenuOptionMap;
    }

    public Map<Integer, ScoreChangeMenuOption> getScoreChangeMenuOptionMap() {
        return scoreChangeMenuOptionMap;
    }

    public Map<Integer, YesOrNoOption> getYesOrNoOptionMap() {
        return yesOrNoOptionMap;
    }
    // ================================== 메뉴 관련 EnumClass HashMap =============================


    // ================================== 과목 EnumClass HashMap ============================
    private final Map<Integer, MandatorySubject> mandatorySubjectMap = new HashMap<>();
    private final Map<Integer, SelectSubject> selectSubjectMap = new HashMap<>();
    private final Map<MandatorySubject, String> mandatorySubjectStringMap = new HashMap<>();
    private final Map<SelectSubject, String> selectSubjectStringMap = new HashMap<>();


    public Map<Integer, MandatorySubject> getMandatorySubjectMap() {
        return mandatorySubjectMap;
    }
    public Map<Integer, SelectSubject> getSelectSubjectMap() {
        return selectSubjectMap;
    }
    public Map<MandatorySubject, String> getMandatorySubjectStringMap() {
        return mandatorySubjectStringMap;
    }
    public Map<SelectSubject, String> getSelectSubjectStringMap() {
        return selectSubjectStringMap;
    }
    // ================================== 과목 EnumClass HashMap ============================


    private void validInit() {
        //============================= 과목 관련 초기화==================================
        mandatorySubjectNameList.addAll(List.of("Error", "Java", "OOP", "Spring", "JPA", "MySQL"));
        selectSubjectNameList.addAll(List.of ("Error", "Design Pattern", "Spring Security", "Redis", "MongoDB"));

        //============================= 과목 관련 초기화==================================
        enumClassInit();
    }

    private void enumClassInit()  {
        // ================================== 메뉴 관련 EnumClass HashMap 초기화=============================
        MainMenuOption[] mainMenuOptions = MainMenuOption.values();
        for (int i = 0; i < mainMenuOptions.length; ++i) {
            mainMenuOptionMap.put(i, mainMenuOptions[i]);
        }

        StudentMenuOption[] studentMenuOptions = StudentMenuOption.values();
        for (int i = 0; i < studentMenuOptions.length; ++i) {
            studentMenuOptionMap.put(i, studentMenuOptions[i]);
        }

        StudentRegisterMenuOption[] studentRegisterMenuOptions = StudentRegisterMenuOption.values();
        for (int i = 0; i < studentRegisterMenuOptions.length; ++i) {
            studentRegisterMenuOptionMap.put(i, studentRegisterMenuOptions[i]);
        }
        StudentInquireMenuOption[] studentInquireMenuOptions = StudentInquireMenuOption.values();
        for (int i = 0; i < studentInquireMenuOptions.length; ++i) {
            studentInquireMenuOptionMap.put(i, studentInquireMenuOptions[i]);
        }
        ScoreMenuOption[] scoreMenuOptions = ScoreMenuOption.values();
        for (int i = 0; i < scoreMenuOptions.length; ++i) {
            scoreMenuOptionMap.put(i, scoreMenuOptions[i]);
        }

        ScoreRegisterMenuOption[] scoreRegisterMenuOptions = ScoreRegisterMenuOption.values();
        for (int i = 0; i < scoreRegisterMenuOptions.length; ++i) {
            scoreRegisterMenuOptionMap.put(i, scoreRegisterMenuOptions[i]);
        }

        ScoreInquireMenuOption[] scoreInquireMenuOptions = ScoreInquireMenuOption.values();
        for (int i = 0; i < scoreInquireMenuOptions.length; ++i) {
            scoreInquireMenuOptionMap.put(i, scoreInquireMenuOptions[i]);
        }
        ScoreChangeMenuOption[] scoreChangeMenuOptions = ScoreChangeMenuOption.values();
        for (int i = 0; i < scoreChangeMenuOptions.length; ++i) {
            scoreChangeMenuOptionMap.put(i, scoreChangeMenuOptions[i]);
        }
        // ================================== 메뉴 관련 EnumClass HashMap 초기화=============================


        // ================================== 과목 EnumClass HashMap 초기화============================
        MandatorySubject[] mandatorySubjects = MandatorySubject.values();
        for (int i = 0; i < mandatorySubjects.length; ++i) {
            mandatorySubjectMap.put(i, mandatorySubjects[i]);
            mandatorySubjectStringMap.put(mandatorySubjects[i], mandatorySubjectNameList.get(i));
        }
        SelectSubject[] selectSubjects = SelectSubject.values();
        for (int i = 0; i < selectSubjects.length; ++i) {
            selectSubjectMap.put(i, selectSubjects[i]);
            selectSubjectStringMap.put(selectSubjects[i], selectSubjectNameList.get(i));
        }
        // ================================== 과목 EnumClass HashMap 초기화============================


        YesOrNoOption[] yesOrNoOptions = YesOrNoOption.values();
        for (int i = 0; i < yesOrNoOptions.length; ++i) {
            yesOrNoOptionMap.put(i, yesOrNoOptions[i]);
        }
    }
    // ======================================= valid =======================================================
    // 선택사항에 대한 입력 처리 함수
    public int returnValidOutput(int start, int end) {
        int returnNumber = 0;
        String input;
        do {
            input = sc.nextLine();
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
