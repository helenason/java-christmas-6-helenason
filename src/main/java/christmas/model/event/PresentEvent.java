package christmas.model.event;

import christmas.constant.Event;
import christmas.constant.Menu;

public class PresentEvent {

    private static final String PRESENT = "샴페인 1개";
    private static final String NOTHING = "없음";
    private static final int MIN_AMOUNT_FOR_DISCOUNT = 120000;

    private final Event event = Event.PRESENT;

    public int calculate(int totalAmount, int date) {
        if (event.isInvalidPeriod(date)) {
            return 0;
        }
        if (totalAmount < MIN_AMOUNT_FOR_DISCOUNT) {
            return 0;
        }
        return Menu.getPriceOfChampagne();
    }

    public String givePresent(int presentEventAmount) {
        if (isPresent(presentEventAmount)) {
            return PRESENT;
        }
        return NOTHING;
    }

    private boolean isPresent(int presentEventAmount) {
        return presentEventAmount != 0;
    }
}
