package controller;

import database.DataBase;
import java.util.Scanner;

public class Controller {
    private final DataBase dataBase;
    private final Scanner sc;
    public Controller(DataBase dataBase, Scanner sc) {
        this.dataBase = dataBase;
        this.sc = sc;
    }
}
