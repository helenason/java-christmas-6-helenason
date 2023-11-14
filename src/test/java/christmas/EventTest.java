package christmas;

import christmas.model.DecemberCalendar;
import christmas.model.OrderMenus;
import christmas.model.event.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EventTest {

    @BeforeEach
    void createDecemberCalendar() {
        DecemberCalendar.of();
    }

    @DisplayName("크리스마스 디데이 할인 - 크리스마스에 다가갈수록 날마다 할인 금액을 100원씩 증가한다.")
    @ValueSource(ints = {1, 5, 17, 25})
    @ParameterizedTest
    void calculateChristmasDiscount(int date) {
        assertThat(ChristmasDiscount.calculate(date))
                .isEqualTo(1000 + (date - 1) * 100);
    }

    @DisplayName("크리스마스 디데이 할인 - 25일 이후의 경우 할인이 적용되지 않는다.")
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    @ParameterizedTest
    void calculateChristmasDiscountOverdue(int date) {
        assertThat(ChristmasDiscount.calculate(date))
                .isEqualTo(0);
    }

    @DisplayName("특별 할인 - 달력에 별이 있는 날짜의 경우 1000원 할인한다.")
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    @ParameterizedTest
    void calculateSpecialDiscount(int date) {
        assertThat(SpecialDiscount.calculate(date))
                .isEqualTo(1000);
    }

    @DisplayName("특별 할인 - 별이 없는 날짜의 경우 할인이 적용되지 않는다.")
    @ValueSource(ints = {1, 2, 4, 11, 18, 26})
    @ParameterizedTest
    void calculateSpecialDiscountOverdue(int date) {
        assertThat(SpecialDiscount.calculate(date))
                .isEqualTo(0);
    }

    @DisplayName("평일 할인 - 평일일 경우 디저트 메뉴를 메뉴 1개당 2023원 할인한다.")
    @ValueSource(ints = {4, 5, 6, 25, 26, 27})
    @ParameterizedTest
    void calculateWeekdayDiscount(int date) {
        OrderMenus orderMenus = new OrderMenus("티본스테이크-3,초코케이크-2,양송이수프-1");

        int discountAmount = WeekdayDiscount.calculate(orderMenus, date);

        assertThat(discountAmount).isEqualTo(2 * 2023);
    }

    @DisplayName("평일 할인 - 평일이 아닐 경우 평일 할인이 적용되지 않는다.")
    @ValueSource(ints = {1, 2, 22, 23})
    @ParameterizedTest
    void calculateWeekdayDiscountOverdue(int date) {
        OrderMenus orderMenus = new OrderMenus("티본스테이크-3,초코케이크-2,양송이수프-1");

        int discountAmount = WeekdayDiscount.calculate(orderMenus, date);

        assertThat(discountAmount).isEqualTo(0);
    }

    @DisplayName("주말 할인 - 주말일 경우 메인 메뉴를 메뉴 1개당 2023원 할인한다.")
    @ValueSource(ints = {1, 2, 22, 23})
    @ParameterizedTest
    void calculateWeekendDiscount(int date) {
        OrderMenus orderMenus = new OrderMenus("티본스테이크-3,초코케이크-2,양송이수프-1");

        int discountAmount = WeekendDiscount.calculate(orderMenus, date);

        assertThat(discountAmount).isEqualTo(3 * 2023);
    }

    @DisplayName("주말 할인 - 주말이 아닐 경우 주말 할인이 적용되지 않는다.")
    @ValueSource(ints = {4, 5, 6, 25, 26, 27})
    @ParameterizedTest
    void calculateWeekendDiscountOverdue(int date) {
        OrderMenus orderMenus = new OrderMenus("티본스테이크-3,초코케이크-2,양송이수프-1");

        int discountAmount = WeekendDiscount.calculate(orderMenus, date);

        assertThat(discountAmount).isEqualTo(0);
    }

    @DisplayName("증정 이벤트 - 할인 전 총 주문 금액이 12만 원 이상일 경우 샴페인 1개 가격을 할인한다.")
    @Test
    void calculatePresentDiscount() {
        assertThat(PresentEvent.calculate(150000, 15))
                .isEqualTo(25000);
    }

    @DisplayName("증정 이벤트 - 할인 전 총 주문 금액이 12만 원 미만일 경우 할인이 적용되지 않는다.")
    @ValueSource(ints = {4, 5, 6, 25, 26, 27})
    @Test
    void calculatePresentDiscountLess() {
        assertThat(PresentEvent.calculate(100000, 15))
                .isEqualTo(0);
    }

    @DisplayName("이벤트 배지 - 총 혜택 금액이 2만 원 이상인 경우 산타 이벤트 배지가 부여된다.")
    @ValueSource(ints = {20000, 30000, 50000})
    @ParameterizedTest
    void getSantaEventBadge(int benefitAmount) {
        assertThat(BadgeEvent.calculate(benefitAmount, 15))
                .isEqualTo("산타");
    }

    @DisplayName("이벤트 배지 - 총 혜택 금액이 1만 원 이상, 2만 원 미만인 경우 트리 이벤트 배지가 부여된다.")
    @ValueSource(ints = {10000, 15000})
    @ParameterizedTest
    void getTreeEventBadge(int benefitAmount) {
        assertThat(BadgeEvent.calculate(benefitAmount, 15))
                .isEqualTo("트리");
    }

    @DisplayName("이벤트 배지 - 총 혜택 금액이 5천 원 이상, 1만 원 미만인 경우 별 이벤트 배지가 부여된다.")
    @ValueSource(ints = {5000, 9000})
    @ParameterizedTest
    void getStarEventBadge(int benefitAmount) {
        assertThat(BadgeEvent.calculate(benefitAmount, 15))
                .isEqualTo("별");
    }
    @DisplayName("이벤트 배지 - 총 혜택 금액이 5천 원 미만인 경우 이벤트 배지가 부여되지 않는다.")
    @ValueSource(ints = {0, 1000, 4999})
    @ParameterizedTest
    void getNotEventBadge(int benefitAmount) {
        assertThat(BadgeEvent.calculate(benefitAmount, 15))
                .isEqualTo("없음");
    }
}
