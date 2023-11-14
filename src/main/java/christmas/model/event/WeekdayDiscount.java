package christmas.model.event;

import christmas.constant.Event;
import christmas.model.DecemberCalendar;
import christmas.model.OrderMenus;


public class WeekdayDiscount {

    private static final int DISCOUNT_VALUE_PER_MENU = 2023;

    private final Event event = Event.WEEKDAY;

    public int calculate(OrderMenus orderMenus, int date) {
        if (event.isInvalidPeriod(date)) {
            return 0;
        }
        if (DecemberCalendar.isWeekend(date)) {
            return 0;
        }
        return orderMenus.countDessertType() * DISCOUNT_VALUE_PER_MENU;
    }
}
