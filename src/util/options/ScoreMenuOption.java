package util.options;

public enum ScoreMenuOption {
    SCORE_MENU_OPTION_START,
    SCORE_MENU_OPTION_REGISTER,
    SCORE_MENU_OPTION_INQUIRE,
    SCORE_MENU_OPTION_CHANGE,
    SCORE_MENU_OPTION_END;

    private static final ScoreMenuOption[] scoreMenuOptions = ScoreMenuOption.values();
    public static ScoreMenuOption get(int index) {
        return scoreMenuOptions[index];
    }
}
