package util.options;

public enum StudentChangeMenuOption {
    STUDENT_CHANGE_MENU_OPTION_START,
    STUDENT_CHANGE_MENU_OPTION_CHANGE,
    STUDENT_CHANGE_MENU_OPTION_END;


    private static final StudentChangeMenuOption[] studentChangeMenuOptions = StudentChangeMenuOption.values();
    public static StudentChangeMenuOption get(int index) {
        return studentChangeMenuOptions[index];
    }
}
