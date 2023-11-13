package christmas.model.event;

import christmas.constant.Event;
import christmas.model.OrderMenus;

public class WeekendDiscount {

    private final Event event = Event.WEEKEND;

    public WeekendDiscount() {

    }

    public int calculate(OrderMenus orderMenus, int date) {
        if (event.isInvalidPeriod(date)) {
            return 0;
        }
        return orderMenus.countMainType() * 2023;
    }
}
