package christmas.model.event;

public class ChristmasDiscount extends Event {

    private int startDate = 1;
    private int endDate = 25;

    public int calculate(int date) {
        return 1000 + (date - startDate) * 100;
    }

}
