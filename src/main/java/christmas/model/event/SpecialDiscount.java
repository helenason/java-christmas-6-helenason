package christmas.model.event;

import christmas.model.DecemberCalendar;

public class SpecialDiscount {

    private final Event event = Event.SPECIAL;

    public SpecialDiscount() {

    }

    public int calculate(int date) {
        if (event.isInvalidPeriod(date)) {
            return 0;
        }
        if (DecemberCalendar.isSpecialDay(date)) {
            return 1000;
        }
        return 0;
    }
}
