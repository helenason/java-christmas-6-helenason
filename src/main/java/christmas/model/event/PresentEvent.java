package christmas.model.event;

import christmas.constant.Menu;

public class PresentEvent {

    private static final String PRESENT = "샴페인 1개";

    private final int startDate;
    private final int endDate;

    public PresentEvent(int startDate, int endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int calculate(int totalAmount) {
        if (totalAmount >= 120000) {
            return Menu.getPriceOfChampagne();
        }
        return 0;
    }

    public String givePresent() {
        return PRESENT;
    }
}
