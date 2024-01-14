package util.options;

public enum StudentRegisterMenuOption {
    STUDENT_REGISTER_MENU_OPTION_ERROR,
    STUDENT_REGISTER_MENU_OPTION_REGISTER,
    STUDENT_REGISTER_MENU_OPTION_BACK;

    private static final StudentRegisterMenuOption[] studentRegisterMenuOptions = StudentRegisterMenuOption.values();
    public static StudentRegisterMenuOption get(int index) {
        return studentRegisterMenuOptions[index];
    }
}
