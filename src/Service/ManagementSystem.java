package Service;


import Data.DataBase;

public class ManagementSystem {
    // 데이터베이스를 접근하는 함수를 가지고 있는거에요
    private final DataBase dataBase;
    public ManagementSystem(DataBase dataBase) {
        this.dataBase = dataBase;
    }
    static public void mainMenu() {
        // 1 -> studentMenu()
        // 2 -> scoreMenu()
    }
    // 수강생 관리 메뉴
    public void studentMenu() {
        // 1 -> registerStudentMenu()
        // 2->inquireStudentMenu()
        // 3->changeStudentMenu()
        // 4-> deleteStudentMenu()
    }

    public void registerStudentMenu() {}
    public void inquireStudentMenu() {
        // 1--> id로 조회
        // 2--> 한 상태에 대해 조회
    }
    public void changeStudentMenu() {}
    public void deleteStudentMenu() {}
    // 수강생 관련 메뉴 끝


    // 점수 관리 메뉴
    public void scoreMenu() {
        // 1->registerScoreMenu
        // 2->inquireScoreMenu
        // 3->changeScoreMenu
    }
    public void registerScoreMenu() {}
    public void inquireScoreMenu() {
        // 1-> id로 조회
        // 2-> 한 상태에 대해 조회
    }
    public void changeScoreMenu() {}

    // 점수 관리 메뉴 끝
}
