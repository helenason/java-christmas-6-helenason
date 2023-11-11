package christmas.model;

import christmas.constant.Menu;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Order {

    private static final int MAX_OF_ORDER_MENU_SIZE = 20;

    private final Map<Menu, Integer> orderMenu;
    private TotalAmount totalAmount;

    public Order(String orderForm) {
        OrderHandler orderHandler = new OrderHandler();
        orderMenu = orderHandler.createOrder(orderForm);
        validate();
    }

    private void validate() {
        validateIfOnlyDrink();
        validateOrderCount();
    }

    private void validateIfOnlyDrink() {
        Set<Menu> menus = orderMenu.keySet();
        long drinkTypeCount = menus.stream()
                .filter(Menu::isDrinkType)
                .count();
        if (menus.size() == drinkTypeCount) {
            ErrorMessage.ONLY_DRINK.throwErrorWithMessage();
        }
    }

    private void validateOrderCount() {
        Collection<Integer> counts = orderMenu.values();
        int totalCount = counts.stream()
                .mapToInt(Integer::intValue)
                .sum(); // TODO: check convention!
        if (totalCount > MAX_OF_ORDER_MENU_SIZE) {
            ErrorMessage.MORE_THAN_MAX.throwErrorWithMessage();
        }
    }

    public Set<Menu> getAllMenus() {
        return orderMenu.keySet();
    }

    public int getCountOfMenu(Menu menu) {
        return orderMenu.get(menu);
    }

    public void createTotalAmount() {
        int amount = 0;
        for (Map.Entry<Menu, Integer> order : orderMenu.entrySet()) {
            Menu menu = order.getKey();
            int count = order.getValue();
            amount += menu.calculateAmountOf(count);
        }
        totalAmount = new TotalAmount(amount);
    }
}
