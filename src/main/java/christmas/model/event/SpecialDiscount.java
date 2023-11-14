package christmas.model.event;

import christmas.constant.Event;
import christmas.model.DecemberCalendar;

public class SpecialDiscount {

    private static final int DISCOUNT_VALUE = 1000;
    private static final Event EVENT = Event.SPECIAL;

    public static int calculate(int date) {
        if (EVENT.isInvalidPeriod(date)) {
            return 0;
        }
        if (!DecemberCalendar.isSpecialDay(date)) {
            return 0;
        }
        return DISCOUNT_VALUE;
    }
}
