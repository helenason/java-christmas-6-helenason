package christmas.model.event;

import christmas.constant.Menu;

public class PresentEvent extends Event {

    public int calculate(int totalAmount) {
        if (totalAmount >= 120000) {
            return Menu.getPriceOfChampagne();
        }
        return 0;
    }


}
