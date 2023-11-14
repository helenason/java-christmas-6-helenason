package christmas.model;

import christmas.constant.ErrorMessage;
import christmas.constant.Menu;

import java.util.*;

public class OrderMenus {

    private static final String DELIMITER_OF_ORDER = ",";
    private static final String DELIMITER_OF_MENU_COUNT = "-";
    private static final int INDEX_OF_MENU = 0;
    private static final int INDEX_OF_COUNT = 1;
    private static final int LENGTH_OF_ORDER_FORM = 2;
    private static final int MIN_OF_COUNT = 1;
    private static final int MAX_OF_ORDER_MENU_SIZE = 20;

    private final Map<Menu, Integer> orderMenus = new HashMap<>();

    public OrderMenus(String input) {
        String[] orders = input.split(DELIMITER_OF_ORDER);
        for (String order : orders) {

            String[] orderForm = extractOrder(order);
            String foodName = extractFoodName(orderForm);
            String count = extractCount(orderForm);

            Menu menu = Menu.getMenuWithFoodName(foodName);
            orderMenus.put(menu, Integer.parseInt(count));
        }
        validate();
    }

    private void validate() {
        validateIfOnlyDrink();
        validateOrderCount();
    }

    private void validateIfOnlyDrink() {
        if (hasOnlyDrinks()) {
            ErrorMessage.ONLY_DRINK.throwErrorWithMessage();
        }
    }

    private void validateOrderCount() {
        int totalCount = countOrderMenus();
        if (totalCount > MAX_OF_ORDER_MENU_SIZE) {
            ErrorMessage.MORE_THAN_MAX.throwErrorWithMessage();
        }
    }

    private boolean hasOnlyDrinks() {
        Set<Menu> menus = orderMenus.keySet();
        return menus.stream().allMatch(Menu::isDrinkType);
    }

    private int countOrderMenus() {
        Collection<Integer> counts = orderMenus.values();
        return counts.stream()
                .mapToInt(Integer::intValue)
                .sum(); // TODO: check convention!
    }

    public Map<Menu, Integer> getOrderMenus() {
        return Collections.unmodifiableMap(orderMenus);
    }

    public int calculateTotalAmount() {
        int totalAmount = 0;
        Set<Menu> menus = orderMenus.keySet();
        for (Menu menu : menus) {
            int count = orderMenus.get(menu);
            totalAmount += menu.calculateAmountOf(count);
        }
        return totalAmount;
    }

    public int countMainType() {
        int count = 0;
        Set<Menu> menus = orderMenus.keySet();
        for (Menu menu : menus) {
            if (menu.isMainType()) {
                count += orderMenus.get(menu);
            }
        }
        return count;
    }

    public int countDessertType() {
        int count = 0;
        Set<Menu> menus = orderMenus.keySet();
        for (Menu menu : menus) {
            if (menu.isDessertType()) {
                count += orderMenus.get(menu);
            }
        }
        return count;
    }

    private String[] extractOrder(String order) {
        String[] orderForm = order.split(DELIMITER_OF_MENU_COUNT);
        validateOrderForm(orderForm);
        return orderForm;
    }

    private String extractFoodName(String[] orderForm) {
        validateMenu(orderForm[INDEX_OF_MENU]);
        return orderForm[INDEX_OF_MENU];
    }

    private String extractCount(String[] orderForm) {
        validateCount(orderForm[INDEX_OF_COUNT]);
        return orderForm[INDEX_OF_COUNT];
    }

    private void validateOrderForm(String[] order) {
        if (order.length != LENGTH_OF_ORDER_FORM) {
            ErrorMessage.INVALID_ORDER.throwErrorWithMessage();
        }
    }

    private void validateMenu(String foodName) {
        validateFoodName(foodName);
        validateDuplicatedMenu(foodName);
    }

    private void validateFoodName(String foodName) {
        Menu menu = Menu.getMenuWithFoodName(foodName);
        if (menu == null) {
            ErrorMessage.INVALID_ORDER.throwErrorWithMessage();
        }
    }

    private void validateDuplicatedMenu(String foodName) {
        Menu menu = Menu.getMenuWithFoodName(foodName);
        Set<Menu> orderedMenus = orderMenus.keySet();
        if (orderedMenus.contains(menu)) {
            ErrorMessage.INVALID_ORDER.throwErrorWithMessage();
        }
    }

    private void validateCount(String countForm) {
        validateCountType(countForm);
        validateCountRange(countForm);
    }

    private void validateCountType(String countForm) {
        char[] countPieces = countForm.toCharArray();
        for (char countPiece : countPieces) {
            if (!Character.isDigit(countPiece)) {
                ErrorMessage.INVALID_ORDER.throwErrorWithMessage();
            }
        }
    }

    private void validateCountRange(String countForm) {
        int count = Integer.parseInt(countForm);
        if (count < MIN_OF_COUNT) {
            ErrorMessage.INVALID_ORDER.throwErrorWithMessage();
        }
    }
}
