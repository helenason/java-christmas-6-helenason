package christmas.model.event;

import christmas.constant.Event;

public class ChristmasDiscount {

    private static final int MIN_DISCOUNT_VALUE = 1000;
    private static final int DISCOUNT_VALUE_PER_DATE = 100;

    private final Event event = Event.CHRISTMAS;

    public int calculate(int date) {
        if (event.isInvalidPeriod(date)) {
            return 0;
        }
        if (event.isAfterEndDate(date)) {
            return 0;
        }
        return MIN_DISCOUNT_VALUE + event.gapBetweenStartDateAndDate(date) * DISCOUNT_VALUE_PER_DATE;
    }
}
