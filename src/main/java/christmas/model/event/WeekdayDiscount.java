package christmas.model.event;

import christmas.model.OrderMenus;


public class WeekdayDiscount {

    private final Event event = Event.WEEKDAY;

    public WeekdayDiscount() {

    }

    public int calculate(OrderMenus orderMenus, int date) {
        if (event.isInvalidPeriod(date)) {
            return 0;
        }
        return orderMenus.countDessertType() * 2023;
    }
}
