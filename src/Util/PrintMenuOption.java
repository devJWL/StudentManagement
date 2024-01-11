package Util;

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

    /*
    MAIN_MENU,
    STUDENT_MAIN_MENU,
    STUDENT_REGISTER_MENU,
    STUDENT_INQUIRE_MENU,
    STUDENT_CHANGE_MENU,
    STUDENT_DELETE_MENU,
    SCORE_MAIN_MENU,
    SCORE_REGISTER_MENU,
    SCORE_INQUIRE_MENU,
    SCORE_CHANGE_MENU,
     */
    private void initMenuOptionStringMap() {
        menuOptionStringMap.put(MenuOption.MAIN_MENU,
                """
                        ==================================
                        내일배움캠프 스프링 수강생 관리 프로그램
                        ==================================
                        관리자 메뉴에 오신 것을 환영합니다.
                        아래 메뉴 중 하나를 선택해주세요.
                        
                        1. 수강생 관리
                        2. 점수 관리
                        3. 프로그램 종료
                        =================================="""
                );
        menuOptionStringMap.put(MenuOption.STUDENT_MAIN_MENU,
                """
                        ==================================
                        내일배움캠프 스프링 수강생 관리 프로그램
                        ==================================
                        수강생 관리 페이지입니다.
                        
                        1. 수강생 등록
                        2. 수강생 조회
                        3. 수강생 수정
                        4. 수강생 삭제
                        5. 뒤로가기
                        """);
        menuOptionStringMap.put(MenuOption.STUDENT_REGISTER_MENU,
                """
                        ==================================
                        내일배움캠프 스프링 수강생 관리 프로그램
                        ==================================
                        수강생 등록 페이지입니다.
                       
                        """);
        menuOptionStringMap.put(MenuOption.STUDENT_INQUIRE_MENU,
                """
                        ==================================
                        내일배움캠프 스프링 수강생 관리 프로그램
                        ==================================
                        수강생 조회 페이지입니다.
                        """);
        menuOptionStringMap.put(MenuOption.STUDENT_CHANGE_MENU,
                """
                        ==================================
                        내일배움캠프 스프링 수강생 관리 프로그램
                        ==================================
                        수강생 수정 페이지입니다.
                        
                        """);
        menuOptionStringMap.put(MenuOption.STUDENT_DELETE_MENU,
                """
                        ==================================
                        내일배움캠프 스프링 수강생 관리 프로그램
                        ==================================
                        수강생 삭제 페이지입니다.
                        
                        """);

        menuOptionStringMap.put(MenuOption.SCORE_MAIN_MENU,
                """
                        ==================================
                        내일배움캠프 스프링 수강생 관리 프로그램
                        ==================================
                        점수 관리 페이지입니다
                        
                        1. 점수 입력
                        2. 점수 조회
                        3. 점수 수정
                        4. 뒤로가기
                        ==================================""");
        menuOptionStringMap.put(MenuOption.SCORE_REGISTER_MENU,
                """
                        ==================================
                        내일배움캠프 스프링 수강생 관리 프로그램
                        ==================================
                        점수 입력 페이지입니다
                        
                        """);
        menuOptionStringMap.put(MenuOption.SCORE_INQUIRE_MENU,
                """
                        ==================================
                        내일배움캠프 스프링 수강생 관리 프로그램
                        ==================================
                        점수 조회 페이지입니다
                        
                        """);
        menuOptionStringMap.put(MenuOption.SCORE_CHANGE_MENU,
                """
                        ==================================
                        내일배움캠프 스프링 수강생 관리 프로그램
                        ==================================
                        점수 수정 페이지입니다
                        
                        """);
    }
}
