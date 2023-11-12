package christmas.model.event;

import christmas.constant.Menu;

public class PresentEvent {

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
}
