package util.options;

public enum MainMenuOption {
    MAIN_MENU_OPTION_START,
    MAIN_MENU_OPTION_STU,
    MAIN_MENU_OPTION_SCORE,
    MAIN_MENU_OPTION_END;

    private static final MainMenuOption[] mainMenuOptions = MainMenuOption.values();
    public static MainMenuOption get(int index) {
        return mainMenuOptions[index];
    }
}
