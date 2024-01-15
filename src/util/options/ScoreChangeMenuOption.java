package util.options;

public enum ScoreChangeMenuOption {
    SCORE_CHANGE_MENU_OPTION_START,
    SCORE_CHANGE_MENU_OPTION_CHANGE,
    SCORE_CHANGE_MENU_OPTION_END;

    private static final ScoreChangeMenuOption[] scoreChangeMenuOptions = ScoreChangeMenuOption.values();
    public static ScoreChangeMenuOption get(int index) {
        return scoreChangeMenuOptions[index];
    }
}
