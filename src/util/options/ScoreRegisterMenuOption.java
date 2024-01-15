package util.options;

public enum ScoreRegisterMenuOption {
    SCORE_REGISTER_MENU_OPTION_ERROR,
    SCORE_REGISTER_MENU_OPTION_REGISTER,
    SCORE_REGISTER_MENU_OPTION_END;

    private static final ScoreRegisterMenuOption[] scoreRegisterMenuOptions = ScoreRegisterMenuOption.values();
    public static ScoreRegisterMenuOption get(int index) {
        return scoreRegisterMenuOptions[index];
    }
}
