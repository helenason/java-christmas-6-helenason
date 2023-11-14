package christmas.model.event;

import christmas.constant.Event;
import christmas.model.DecemberCalendar;

public class SpecialDiscount {

    private static final int DISCOUNT_VALUE = 1000;

    private final Event event = Event.SPECIAL;

    public int calculate(int date) {
        if (event.isInvalidPeriod(date)) {
            return 0;
        }
        if (!DecemberCalendar.isSpecialDay(date)) {
            return 0;
        }
        return DISCOUNT_VALUE;
    }
}
