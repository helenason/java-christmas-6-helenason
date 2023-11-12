package christmas.model.event;

import christmas.constant.Menu;
import christmas.model.DecemberCalendar;
import christmas.model.Order;

import java.util.Set;

public class DayDiscount {

    private final int startDate;
    private final int endDate;

    public DayDiscount(int startDate, int endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int calculate(Order order, int date) {
        if (DecemberCalendar.isWeekend(date)) {
            return discountMainMenu(order);
        }
        return discountDessertMenu(order);
    }

    private int discountDessertMenu(Order order) {
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

    private int discountMainMenu(Order order) {
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
