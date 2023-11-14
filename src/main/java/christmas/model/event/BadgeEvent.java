package christmas.model.event;

import christmas.constant.Event;

public class BadgeEvent {

    private static final int MIN_BENEFIT_AMOUNT_FOR_STAR_BADGE = 5000;
    private static final int MIN_BENEFIT_AMOUNT_FOR_TREE_BADGE = 10000;
    private static final int MIN_BENEFIT_AMOUNT_FOR_SANTA_BADGE = 20000;
    private static final String NOTHING = "없음";
    private static final String SANTA = "산타";
    private static final String TREE = "트리";
    private static final String STAR = "별";

    private final Event event = Event.BADGE;

    public String calculate(int benefitAmount, int date) {
        if (event.isInvalidPeriod(date)) {
            return NOTHING;
        }
        if (checkIfSanta(benefitAmount)) {
            return SANTA;
        }
        if (checkIfTree(benefitAmount)) {
            return TREE;
        }
        if (checkIfStar(benefitAmount)) {
            return STAR;
        }
        return NOTHING;
    }

    private boolean checkIfStar(int benefitAmount) {
        return benefitAmount >= MIN_BENEFIT_AMOUNT_FOR_STAR_BADGE;
    }

    private boolean checkIfTree(int benefitAmount) {
        return benefitAmount >= MIN_BENEFIT_AMOUNT_FOR_TREE_BADGE;
    }

    private boolean checkIfSanta(int benefitAmount) {
        return benefitAmount >= MIN_BENEFIT_AMOUNT_FOR_SANTA_BADGE;
    }
}
