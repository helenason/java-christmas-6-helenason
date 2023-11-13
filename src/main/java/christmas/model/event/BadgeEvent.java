package christmas.model.event;

public class BadgeEvent {

    private final int startDate;
    private final int endDate;

    public BadgeEvent(int startDate, int endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String calculate(int benefitAmount) {
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
