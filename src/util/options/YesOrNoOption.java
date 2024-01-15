package util.options;

public enum YesOrNoOption{
    YES_OR_NO_OPTION_START,
    YES_OR_NO_OPTION_YES,
    YES_OR_NO_OPTION_NO,
    YES_OR_NO_OPTION_END;

    private static final YesOrNoOption[] yesOrNoOptions = YesOrNoOption.values();
    public static YesOrNoOption get(int index) {
        return yesOrNoOptions[index];
    }
}
