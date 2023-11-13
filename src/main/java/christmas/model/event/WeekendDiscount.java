package christmas.model.event;

import christmas.constant.Menu;
import christmas.model.Order;

import java.util.Set;

public class WeekendDiscount {

    private final int startDate;
    private final int endDate;

    public WeekendDiscount(int startDate, int endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int calculate(Order order, int date) {
        if (isInValidPeriod(date)) {
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

    private boolean isInValidPeriod(int date) {
        return date < startDate || date > endDate;
    }
}
