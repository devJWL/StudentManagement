package util.options;

public enum ScoreChangeMenuOption {
    SCORE_CHANGE_MENU_OPTION_ERROR,
    SCORE_CHANGE_MENU_OPTION_CHANGE,
    SCORE_CHANGE_MENU_OPTION_BACK;

    private static final ScoreChangeMenuOption[] scoreChangeMenuOptions = ScoreChangeMenuOption.values();
    public static ScoreChangeMenuOption get(int index) {
        return scoreChangeMenuOptions[index];
    }
}
