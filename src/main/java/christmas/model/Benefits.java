package christmas.model;

import christmas.constant.Event;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Benefits {

    private final Map<Event, Integer> benefits;

    public Benefits() {
        benefits = new HashMap<>();
    }

    public void createBenefit(Event event, int benefitsAmount) {
        benefits.put(event, benefitsAmount);
    }

    public Map<Event, Integer> getBenefits() {
        return Collections.unmodifiableMap(benefits);
    }

    public int calculateTotalDiscountAmount() {
        return benefits.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getPresentEventDiscountAmount() {
        return benefits.get(Event.PRESENT);
    }
}
