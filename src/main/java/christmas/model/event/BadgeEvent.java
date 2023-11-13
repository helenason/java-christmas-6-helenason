package christmas.model.event;

import christmas.constant.Event;

public class BadgeEvent {

    private final Event event = Event.BADGE;

    public BadgeEvent() {

    }

    public String calculate(int benefitAmount, int date) {
        if (event.isInvalidPeriod(date)) {
            return "없음";
        }
        if (checkIfSanta(benefitAmount)) {
            return "산타";
        }
        if (checkIfTree(benefitAmount)) {
            return "트리";
        }
        if (checkIfStar(benefitAmount)) {
            return "별";
        }
        return "없음";
    }

    private boolean checkIfStar(int benefitAmount) {
        return benefitAmount >= 5000;
    }

    private boolean checkIfTree(int benefitAmount) {
        return benefitAmount >= 10000;
    }

    private boolean checkIfSanta(int benefitAmount) {
        return benefitAmount >= 20000;
    }
}
