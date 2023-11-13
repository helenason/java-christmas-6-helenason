package christmas.model;

import christmas.constant.DayOfWeek;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecemberCalendar {

    private static final List<Integer> SPECIAL_DAYS = List.of(3, 10, 17, 24, 25, 31);
    private static final int FIRST_DATE_OF_DECEMBER = 1;
    private static final int LAST_DATE_OF_DECEMBER = 31;

    private static final Map<Integer, DayOfWeek> calendar = new HashMap<>();

    public static void of() {
        for (int date = FIRST_DATE_OF_DECEMBER; date <= LAST_DATE_OF_DECEMBER; date++) {
            DayOfWeek day = getDayOfDate(date);
            calendar.put(date, day);
        }
    }

    private static DayOfWeek getDayOfDate(int date) {
        DayOfWeek[] values = DayOfWeek.values();
        return Arrays.stream(values)
                .filter(dayOfWeek -> dayOfWeek.isCorrectDayOfWeek(date))
                .findFirst().orElse(null);
    }

    public static boolean isWeekend(int date) {
        DayOfWeek dayOfWeek = calendar.get(date);
        return dayOfWeek.isWeekend();
    }

    public static boolean isSpecialDay(int date) {
        return SPECIAL_DAYS.contains(date);
    }
}
