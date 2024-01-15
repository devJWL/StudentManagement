package util.options;

public enum StudentRegisterMenuOption {
    STUDENT_REGISTER_MENU_OPTION_START,
    STUDENT_REGISTER_MENU_OPTION_REGISTER,
    STUDENT_REGISTER_MENU_OPTION_END;

    private static final StudentRegisterMenuOption[] studentRegisterMenuOptions = StudentRegisterMenuOption.values();
    public static StudentRegisterMenuOption get(int index) {
        return studentRegisterMenuOptions[index];
    }
}
