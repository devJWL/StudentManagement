package util.options;

import java.util.Arrays;

public enum MainMenuOption {
    MAIN_MENU_OPTION_ERROR(0),
    MAIN_MENU_OPTION_STU(1),
    MAIN_MENU_OPTION_SCORE(2),
    MAIN_MENU_OPTION_EXIT(3);


    private int num;
    MainMenuOption(int num) {
        this.num = num;
    }

    private static final MainMenuOption[] mainMenuOptions = MainMenuOption.values();
    public static MainMenuOption get(int index) {
        return Arrays.stream(mainMenuOptions).filter(idx -> idx.num == index).findFirst().orElse(MAIN_MENU_OPTION_ERROR);
    }

}
