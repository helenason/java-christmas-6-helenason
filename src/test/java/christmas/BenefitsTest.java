package christmas;

import christmas.constant.Event;
import christmas.model.Benefits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BenefitsTest {

    @DisplayName("혜택 내역을 저장하고 확인할 수 있다.")
    @Test
    void saveBenefits() {
        Benefits benefits = new Benefits();

        benefits.createBenefit(Event.CHRISTMAS, 1200);
        benefits.createBenefit(Event.WEEKDAY, 4046);
        benefits.createBenefit(Event.SPECIAL, 1000);
        benefits.createBenefit(Event.PRESENT, 25000);

        Map<Event, Integer> result = new HashMap<>();
        result.put(Event.CHRISTMAS, 1200);
        result.put(Event.WEEKDAY, 4046);
        result.put(Event.SPECIAL, 1000);
        result.put(Event.PRESENT, 25000);

        assertThat(benefits.getBenefits()).isEqualTo(result);
    }

}
