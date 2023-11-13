package christmas.model.event;

public class ChristmasDiscount {

    private final int startDate;
    private final int endDate;

    public ChristmasDiscount(int startDate, int endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int calculate(int date) {
        if (date <= endDate) {
            return 1000 + (date - startDate) * 100;
        }
        return 0;
    }
}
