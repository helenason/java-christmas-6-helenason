package christmas.model.event;

import christmas.constant.Event;
import christmas.model.DecemberCalendar;
import christmas.model.OrderMenus;

public class WeekendDiscount {

    private static final int DISCOUNT_VALUE_PER_MENU = 2023;

    private final Event event = Event.WEEKEND;

    public int calculate(OrderMenus orderMenus, int date) {
        if (event.isInvalidPeriod(date)) {
            return 0;
        }
        if (!DecemberCalendar.isWeekend(date)) {
            return 0;
        }
        return orderMenus.countMainType() * DISCOUNT_VALUE_PER_MENU;
    }
}
