package christmas.constant;

public enum FoodType {

    APPETIZER("에피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    DRINK("음료");

    private String inKorean;

    FoodType(String inKorean) {
        this.inKorean = inKorean;
    }
}
