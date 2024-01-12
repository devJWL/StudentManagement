package Util;

import java.util.HashMap;
import java.util.Map;

public class Number {
    private final Map<Integer, SelectNumber> selectNumberMap = new HashMap<>();

    public Number() {
        initSelectNumberMap();
    }

    public  SelectNumber getSelectNumber(Integer number) {
        return selectNumberMap.get(number);
    }

    private void initSelectNumberMap() {
        selectNumberMap.put(1, SelectNumber.ONE);
        selectNumberMap.put(2, SelectNumber.TWO);
        selectNumberMap.put(3, SelectNumber.THREE);
        selectNumberMap.put(4, SelectNumber.FOUR);
        selectNumberMap.put(5, SelectNumber.FIVE);
        selectNumberMap.put(6, SelectNumber.ERROR);
    }
}
