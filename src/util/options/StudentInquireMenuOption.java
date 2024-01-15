package util.options;

public enum StudentInquireMenuOption {
    STUDENT_INQUIRE_MENU_OPTION_START,
    STUDENT_INQUIRE_MENU_OPTION_ALL,
    STUDENT_INQUIRE_MENU_OPTION_ID,
    STUDENT_INQUIRE_MENU_OPTION_STATUS,
    STUDENT_INQUIRE_MENU_OPTION_END;

    private static final StudentInquireMenuOption[] studentInquireMenuOptions = StudentInquireMenuOption.values();
    public static StudentInquireMenuOption get(int index) {
        return studentInquireMenuOptions[index];
    }
}
