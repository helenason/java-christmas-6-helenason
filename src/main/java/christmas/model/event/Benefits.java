package christmas.model.event;

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
}
