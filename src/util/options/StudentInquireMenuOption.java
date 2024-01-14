package util.options;

public enum StudentInquireMenuOption {
    STUDENT_INQUIRE_MENU_OPTION_ERROR,
    STUDENT_INQUIRE_MENU_OPTION_ID,
    STUDENT_INQUIRE_MENU_OPTION_STATUS,
    STUDENT_INQUIRE_MENU_OPTION_BACK;

    private static final StudentInquireMenuOption[] studentInquireMenuOptions = StudentInquireMenuOption.values();
    public static StudentInquireMenuOption get(int index) {
        return studentInquireMenuOptions[index];
    }
}
