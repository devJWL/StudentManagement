package util.options;

public enum StudentMenuOption {
    STUDENT_MENU_OPTION_ERROR,
    STUDENT_MENU_OPTION_REGISTER,
    STUDENT_MENU_OPTION_INQUIRE,
    STUDENT_MENU_OPTION_CHANGE,
    STUDENT_MENU_OPTION_DELETE,
    STUDENT_MENU_OPTION_BACK;

    private static final StudentMenuOption[] studentMenuOptions = StudentMenuOption.values();
    public static StudentMenuOption get(int index) {
        return studentMenuOptions[index];
    }
}
