package christmas.model.event;

import christmas.constant.Event;

public class ChristmasDiscount {

    private final Event event = Event.CHRISTMAS;

    public ChristmasDiscount() {

    }

    public int calculate(int date) {
        if (event.isInvalidPeriod(date)) {
            return 0;
        }
        if (event.isBeforeOrDayOfEndDate(date)) {
            return 1000 + event.gapBetweenStartDateAndDate(date) * 100;
        }
        return 0;
    }
}
