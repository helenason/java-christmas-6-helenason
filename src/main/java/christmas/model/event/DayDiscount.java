package christmas.model.event;

import christmas.constant.Menu;
import christmas.model.Calendar;

import java.util.Map;
import java.util.Set;

public class DayDiscount {

    private int startDate = 1;
    private int endDate = 31;
    private Calendar calendar;

    public int calculate(Map<Menu, Integer> orderMenu, int date) {
        if (calendar.isWeekend(date)) {
            // 메인 메뉴 1개당 2023 할인 (종류 한 개 ??)
            return discountMainMenu(orderMenu);
        }
        // 디저트 메뉴 1개당 2023 할인
        return discountDessertMenu(orderMenu);
    }

    private int discountDessertMenu(Map<Menu, Integer> orderMenu) {
        int discountAmount = 0;
        Set<Menu> menus = orderMenu.keySet();
        for (Menu menu : menus) {
            if (menu.isDessertType()) {
                int count = orderMenu.get(menu);
                discountAmount += 2023 * count;
            }
        }
        return discountAmount;
    }

    private int discountMainMenu(Map<Menu, Integer> orderMenu) {
        int discountAmount = 0;
        Set<Menu> menus = orderMenu.keySet();
        for (Menu menu : menus) {
            if (menu.isMainType()) {
                int count = orderMenu.get(menu);
                discountAmount += 2023 * count;
            }
        }
        return discountAmount;
    }

}
