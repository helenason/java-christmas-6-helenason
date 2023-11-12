package christmas.model.event;

import christmas.constant.Menu;
import christmas.model.Calendar;

public class PresentEvent {

    private int startDate = 1;
    private int endDate = 31;
    private Calendar calendar;

    public int calculate(int totalAmount) {
        if (totalAmount >= 120000) {
            return Menu.getPriceOfChampagne();
        }
        return 0;
    }
}
