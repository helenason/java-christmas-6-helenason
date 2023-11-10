package christmas.model;

import christmas.constant.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderHandler {

    private static final String DELIMITER_OF_ORDER = ",";
    private static final String DELIMITER_OF_MENU_COUNT = "-";
    private static final int INDEX_OF_MENU = 0;
    private static final int INDEX_OF_COUNT = 1;
    private static final int LENGTH_OF_ORDER_FORM = 2;
    private static final int MIN_OF_COUNT = 1;

    private final List<String> foodNames = new ArrayList<>();

    public Map<Menu, Integer> createOrder(String input) {
        Map<Menu, Integer> orderMenu = new HashMap<>();

        String[] orders = input.split(DELIMITER_OF_ORDER);
        for (String order : orders) {

            String[] orderForm = extractOrder(order);
            String foodName = extractFoodName(orderForm);
            String count = extractCount(orderForm);

            Menu menu = Menu.getMenuWithFoodName(foodName);
            orderMenu.put(menu, Integer.parseInt(count));
        }
        return orderMenu;
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
        if (foodNames.contains(foodName)) {
            ErrorMessage.INVALID_ORDER.throwErrorWithMessage();
        }
        foodNames.add(foodName);
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
