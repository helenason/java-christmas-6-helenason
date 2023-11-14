package christmas.model.event;

import christmas.constant.Event;
import christmas.constant.Menu;

public class PresentEvent {

    private static final String PRESENT = "샴페인 1개";
    private static final String NOTHING = "없음";
    private static final int MIN_AMOUNT_FOR_DISCOUNT = 120000;
    private static final Event EVENT = Event.PRESENT;

    public static int calculate(int totalAmount, int date) {
        if (EVENT.isInvalidPeriod(date)) {
            return 0;
        }
        if (totalAmount < MIN_AMOUNT_FOR_DISCOUNT) {
            return 0;
        }
        return Menu.getPriceOfChampagne();
    }

    public static String givePresent(int presentEventAmount) {
        if (isPresent(presentEventAmount)) {
            return PRESENT;
        }
        return NOTHING;
    }

    private static boolean isPresent(int presentEventAmount) {
        return presentEventAmount != 0;
    }
}
