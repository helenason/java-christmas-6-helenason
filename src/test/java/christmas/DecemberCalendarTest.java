package christmas;

import christmas.model.DecemberCalendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DecemberCalendarTest {

    @BeforeEach
    void createDecemberCalendar() {
        DecemberCalendar.of();
    }

    @DisplayName("입력한 날짜는 12월 달력에서 주말이다.")
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    @ParameterizedTest
    void isWeekendInCalendar(int date) {
        boolean isWeekend = DecemberCalendar.isWeekend(date);

        assertThat(isWeekend).isEqualTo(true);
    }

    @DisplayName("입력한 날짜는 12월 달력에서 주말이 아니다.")
    @ValueSource(ints = {3, 10, 17, 24, 31, 4, 11, 18, 25, 7, 14})
    @ParameterizedTest
    void isWeekdayInCalendar(int date) {
        boolean isWeekend = DecemberCalendar.isWeekend(date);

        assertThat(isWeekend).isEqualTo(false);
    }

    @DisplayName("입력한 날짜는 12월 달력에 별표가 있다.")
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    @ParameterizedTest
    void isSpecialDayInCalendar(int date) {
        boolean isWeekend = DecemberCalendar.isSpecialDay(date);

        assertThat(isWeekend).isEqualTo(true);
    }

    @DisplayName("입력한 날짜는 12월 달력에 별표가 없다.")
    @ValueSource(ints = {1, 2, 4, 11, 18, 26})
    @ParameterizedTest
    void isNotSpecialDayInCalendar(int date) {
        boolean isWeekend = DecemberCalendar.isSpecialDay(date);

        assertThat(isWeekend).isEqualTo(false);
    }

}
