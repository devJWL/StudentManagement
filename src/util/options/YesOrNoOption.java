package util.options;

public enum YesOrNoOption{
    YES_OR_NO_OPTION_ERROR,
    YES_OR_NO_OPTION_YES,
    YES_OR_NO_OPTION_NO,
    YES_OR_NO_OPTION_BACK;

    private static final YesOrNoOption[] yesOrNoOptions = YesOrNoOption.values();
    public static YesOrNoOption get(int index) {
        return yesOrNoOptions[index];
    }
}
