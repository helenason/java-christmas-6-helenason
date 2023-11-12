package christmas.model.event;

import christmas.model.DecemberCalendar;

public class SpecialDiscount {

    private final int startDate;
    private final int endDate;

    public SpecialDiscount(int startDate, int endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int calculate(int date) {
        if (DecemberCalendar.isSpecialDay(date)) {
            return 1000;
        }
        return 0;
    }
}
