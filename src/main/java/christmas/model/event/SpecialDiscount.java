package christmas.model.event;

public class SpecialDiscount extends Event {

    public int calculate(int date) {
        if (calendar.isSpecialDay(date)) {
            return 1000;
        }
        return 0;
    }

}
