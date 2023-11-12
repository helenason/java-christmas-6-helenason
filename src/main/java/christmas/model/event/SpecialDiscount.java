package christmas.model.event;

import christmas.model.Calendar;

public class SpecialDiscount {

    private int startDate = 1;
    private int endDate = 31;
    private Calendar calendar;

    public int calculate(int date) {
        if (calendar.isSpecialDay(date)) {
            return 1000;
        }
        return 0;
    }
}
