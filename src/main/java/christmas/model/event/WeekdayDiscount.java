package christmas.model.event;

import christmas.constant.Event;
import christmas.model.DecemberCalendar;
import christmas.model.OrderMenus;


public class WeekdayDiscount {

    private static final int DISCOUNT_VALUE_PER_MENU = 2023;
    private static final Event EVENT = Event.WEEKDAY;

    public static int calculate(OrderMenus orderMenus, int date) {
        if (EVENT.isInvalidPeriod(date)) {
            return 0;
        }
        if (DecemberCalendar.isWeekend(date)) {
            return 0;
        }
        return orderMenus.countDessertType() * DISCOUNT_VALUE_PER_MENU;
    }
}
