package util.printMenu;

import java.util.HashMap;
import java.util.Map;


public class PrintMenuOption {

    private final Map<MenuOption, String> menuOptionStringMap = new HashMap<>();

    public PrintMenuOption() {
        initMenuOptionStringMap();
    }

    public String getStringData(MenuOption menuOption) {
        return menuOptionStringMap.get(menuOption);
    }

    private void initMenuOptionStringMap() {
        menuOptionStringMap.put(MenuOption.MAIN_MENU,
                """
                        ==================================
                        수강생 관리 프로그램
                        ==================================
                        관리자 메뉴에 오신 것을 환영합니다.
                        아래 메뉴 중 하나를 선택해주세요.
                        (프로그램 종료 -1 입력)
                        1. 수강생 관리하기
                        2. 수강생 점수 관리하기
                        """
                );
        menuOptionStringMap.put(MenuOption.STUDENT_MAIN_MENU,
                """
                        ==================================
                        수강생 관리 프로그램
                        ==================================
                        수강생 관리 페이지입니다.
                        아래 메뉴 중 하나를 선택해주세요.
                        1. 수강생 등록하기
                        2. 수강생 조회하기
                        3. 수강생 수정하기
                        4. 수강생 삭제하기
                        5. 뒤로가기""");
        menuOptionStringMap.put(MenuOption.STUDENT_REGISTER_MENU,
                """
                        ==================================
                        수강생 관리 프로그램
                        ==================================
                        수강생 등록 페이지입니다.
                        아래 메뉴 중 하나를 선택해주세요.
                        1. 등록하기
                        2. 뒤로가기""");
        menuOptionStringMap.put(MenuOption.STUDENT_REGISTER_HELPER_MENU,
                """
                        ==================================
                        수강생 관리 프로그램
                        ==================================
                        수강생 등록 입력 페이지입니다.""");
        menuOptionStringMap.put(MenuOption.STUDENT_INQUIRE_MENU,
                """
                        ==================================
                        수강생 관리 프로그램
                        ==================================
                        수강생 조회 페이지입니다.
                        아래 메뉴 중 하나를 선택해주세요.
                        1. 모든 수강생 조회하기
                        2. 고유번호로 조회하기
                        3. 상태별로 조회하기
                        4. 뒤로가기""");

        menuOptionStringMap.put(MenuOption.STUDENT_INQUIRE_HELPER_ALL_MENU,
                """
                        ==================================
                        수강생 관리 프로그램
                        ==================================
                        모든 수강생을 조회하는 페이지입니다.""");
        menuOptionStringMap.put(MenuOption.STUDENT_INQUIRE_HELPER_ID_MENU,
                """
                        ==================================
                        수강생 관리 프로그램
                        ==================================
                        수강생을 고유번호로 조회하는 페이지입니다.""");

        menuOptionStringMap.put(MenuOption.STUDENT_INQUIRE_HELPER_STATUS_MENU,
                """
                        ==================================
                        수강생 관리 프로그램
                        ==================================
                        수강생을 상태로 조회하는 페이지입니다.""");
        menuOptionStringMap.put(MenuOption.STUDENT_CHANGE_MENU,
                """
                        ==================================
                        수강생 관리 프로그램
                        ==================================
                        수강생 수정 페이지입니다.
                        아래 메뉴 중 하나를 선택해주세요.
                        1. 수정하기
                        2. 뒤로가기""");
        menuOptionStringMap.put(MenuOption.STUDENT_CHANGE_HELPER_MENU,
                """
                        ==================================
                        수강생 관리 프로그램
                        ==================================
                        수강생 정보 변경 입력 페이지입니다.""");
        menuOptionStringMap.put(MenuOption.STUDENT_DELETE_MENU,
                """
                        ==================================
                        수강생 관리 프로그램
                        ==================================
                        수강생 삭제 페이지입니다.
                        아래 메뉴 중 하나를 선택해주세요.
                        1. 삭제하기
                        2. 뒤로가기""");
        menuOptionStringMap.put(MenuOption.STUDENT_DELETE_HELPER_MENU,
                """
                        ==================================
                        수강생 관리 프로그램
                        ==================================
                        수강생 삭제 입력 페이지입니다.""");
        menuOptionStringMap.put(MenuOption.SCORE_MAIN_MENU,
                """
                        ==================================
                        수강생 관리 프로그램
                        ==================================
                        점수 관리 페이지입니다
                        아래 메뉴 중 하나를 선택해주세요.
                        1. 점수 입력
                        2. 점수 조회
                        3. 점수 수정
                        4. 뒤로가기""");
        menuOptionStringMap.put(MenuOption.SCORE_REGISTER_MENU,
                """
                        ==================================
                        수강생 관리 프로그램
                        ==================================
                        점수 입력 페이지입니다
                        아래 메뉴 중 하나를 선택해주세요.
                        1. 점수 입력하기
                        2. 뒤로가기""");
        menuOptionStringMap.put(MenuOption.SCORE_REGISTER_HELPER_MENU,
                """
                        ==================================
                        수강생 관리 프로그램
                        ==================================
                        수강생 점수 등록 입력 페이지입니다.""");
        menuOptionStringMap.put(MenuOption.SCORE_INQUIRE_MENU,
                """
                        ==================================
                        수강생 관리 프로그램
                        ==================================
                        점수 조회 페이지입니다
                        아래 메뉴 중 하나를 선택해주세요.
                        1. 고유번호로 점수 조회하기
                        2. 상태로 점수 조회하기
                        3. 뒤로가기""");
        menuOptionStringMap.put(MenuOption.SCORE_INQUIRE_HELPER_ID_MENU,
                """
                        ==================================
                        수강생 관리 프로그램
                        ==================================
                        수강생 점수 ID로 조회 페이지입니다.""");
        menuOptionStringMap.put(MenuOption.SCORE_INQUIRE_HELPER_STATUS_MENU,
                """
                        ==================================
                        수강생 관리 프로그램
                        ==================================
                        수강생 점수 상태로 조회 페이지입니다.""");
        menuOptionStringMap.put(MenuOption.SCORE_CHANGE_MENU,
                """
                        ==================================
                        수강생 관리 프로그램
                        ==================================
                        점수 수정 페이지입니다
                        아래 메뉴 중 하나를 선택해주세요.
                        1. 점수 수정하기
                        2. 뒤로가기""");
        menuOptionStringMap.put(MenuOption.SCORE_CHANGE_HELPER_MENU,
                """
                        ==================================
                        수강생 관리 프로그램
                        ==================================
                        수강생 점수 변경 입력 페이지입니다.""");
        menuOptionStringMap.put(MenuOption.INPUT_ERROR_MENU,
                """
                        올바르지 않은 입력이거나 범위를 벗어난 숫자입니다.
                        다시 입력해주세요.""");
    }
}
