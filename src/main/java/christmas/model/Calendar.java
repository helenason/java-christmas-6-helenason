package christmas.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calendar {

    private static final List<Integer> SPECIAL_DAYS = List.of(3, 10, 17, 24, 25, 31);
    private static final int FIRST_DATE_OF_DECEMBER = 1;
    private static final int LAST_DATE_OF_DECEMBER = 31;

    private final int month;
    private final Map<Integer, DayOfWeek> calendar = new HashMap<>();

    public Calendar(int month) {
        this.month = month;

        for (int date = FIRST_DATE_OF_DECEMBER; date <= LAST_DATE_OF_DECEMBER; date++) {
            DayOfWeek day = getDayOfDate(date);
            calendar.put(date, day);
        }
    }

    private DayOfWeek getDayOfDate(int date) {
        DayOfWeek[] values = DayOfWeek.values();
        return Arrays.stream(values)
                .filter(dayOfWeek -> dayOfWeek.isCorrectDayOfWeek(date))
                .findFirst().orElse(null);
    }
}
