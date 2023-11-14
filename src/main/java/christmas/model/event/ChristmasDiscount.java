package christmas.model.event;

import christmas.constant.Event;

public class ChristmasDiscount {

    private static final int MIN_DISCOUNT_VALUE = 1000;
    private static final int DISCOUNT_VALUE_PER_DATE = 100;
    private static final Event EVENT = Event.CHRISTMAS;

    public static int calculate(int date) {
        if (EVENT.isInvalidPeriod(date)) {
            return 0;
        }
        if (EVENT.isAfterEndDate(date)) {
            return 0;
        }
        return MIN_DISCOUNT_VALUE + EVENT.gapBetweenStartDateAndDate(date) * DISCOUNT_VALUE_PER_DATE;
    }
}
