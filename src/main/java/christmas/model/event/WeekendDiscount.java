package christmas.model.event;

import christmas.constant.Menu;
import christmas.model.Order;

import java.util.Set;

public class WeekendDiscount {

    private final Event event = Event.WEEKEND;

    public WeekendDiscount() {

    }

    public int calculate(Order order, int date) {
        if (event.isInvalidPeriod(date)) {
            return 0;
        }
        int discountAmount = 0;
        Set<Menu> menus = order.getAllMenus();
        for (Menu menu : menus) {
            if (menu.isMainType()) {
                int count = order.getCountOfMenu(menu);
                discountAmount += 2023 * count;
            }
        }
        return discountAmount;
    }
}
