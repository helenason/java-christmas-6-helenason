package christmas.model.event;

import christmas.constant.Menu;

public class PresentEvent {

    private static final String PRESENT = "샴페인 1개";

    private final Event event = Event.PRESENT;

    public PresentEvent() {

    }

    public int calculate(int totalAmount, int date) {
        if (event.isInvalidPeriod(date)) {
            return 0;
        }
        if (totalAmount >= 120000) {
            return Menu.getPriceOfChampagne();
        }
        return 0;
    }

    public String givePresent() {
        return PRESENT;
    }
}
