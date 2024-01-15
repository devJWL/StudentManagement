package util.options;

public enum ScoreInquireMenuOption {
    SCORE_INQUIRE_MENU_OPTION_ERROR,
    SCORE_INQUIRE_MENU_OPTION_ID,
    SCORE_INQUIRE_MENU_OPTION_STATUS,
    SCORE_INQUIRE_MENU_OPTION_BACK;

    private static final ScoreInquireMenuOption[] scoreChangeMenuOptions = ScoreInquireMenuOption.values();
    public static ScoreInquireMenuOption get(int index) {
        return scoreChangeMenuOptions[index];
    }
}
