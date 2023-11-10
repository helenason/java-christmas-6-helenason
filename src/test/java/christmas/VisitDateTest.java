package christmas;

import christmas.model.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class VisitDateTest {

    @DisplayName("방문 날짜가 양의 숫자 형식이 아닌 경우 예외가 발생한다.")
    @ValueSource(strings = {"a", "**", "1a", "-15"})
    @ParameterizedTest
    void createDateByInvalidType(String input) {
        assertThatThrownBy(() -> new VisitDate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("방문 날짜의 범위가 1 이상 31 이하가 아닌 경우 예외가 발생한다.")
    @ValueSource(strings = {"0", "32", "1000"})
    @ParameterizedTest
    void createDateByInvalidRange(String input) {
        assertThatThrownBy(() -> new VisitDate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
