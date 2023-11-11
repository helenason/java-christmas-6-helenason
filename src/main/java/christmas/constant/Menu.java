package christmas.constant;

import java.util.List;

import static christmas.constant.FoodType.*;

public enum Menu {

    MUSHROOM_SOUP("양송이수프", 6000, APPETIZER),
    TAPAS("타파스", 5500, APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, APPETIZER),

    T_BONE_STEAK("티본스테이크", 55000, MAIN),
    BARBECUE_RIBS("바비큐립", 54000, MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MAIN),

    CHOCOLATE_CAKE("초코케이크", 15000, DESSERT),
    ICE_CREAM("아이스크림", 5000, DESSERT),

    ZERO_COKE("제로콜라", 3000, DRINK),
    RED_WINE("레드와인", 60000, DRINK),
    CHAMPAGNE("샴페인", 25000, DRINK);

    private String foodName;
    private int price;
    private Enum foodType;

    Menu(String foodName, int price, Enum foodType) {
        this.foodName = foodName;
        this.price = price;
        this.foodType = foodType;
    }

    public static Menu getMenuWithFoodName(String name) {
        List<Menu> allMenu = getAllMenu();
        return allMenu.stream()
                .filter(menu -> menu.foodName.equals(name))
                .findAny().orElse(null);
    }

    private static List<Menu> getAllMenu() {
        return List.of(values());
    }

    public boolean isDrinkType() {
        return foodType.equals(DRINK);
    }

    public String getFoodName() {
        return foodName;
    }
}
