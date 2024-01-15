package util.options;

public enum StudentDeleteMenuOption {
    STUDENT_DELETE_MENU_OPTION_ERROR,
    STUDENT_DELETE_MENU_OPTION_DELETE,
    STUDENT_DELETE_MENU_OPTION_END;

    private static final StudentDeleteMenuOption[] studentDeleteMenuOptions = StudentDeleteMenuOption.values();
    public static StudentDeleteMenuOption get(int index) {
        return studentDeleteMenuOptions[index];
    }
}
