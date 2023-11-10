package christmas;

import christmas.model.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class OrderMenuTest {

    @DisplayName("메뉴의 형식이 예시와 다른 경우 예외가 발생한다.")
    @ValueSource(strings = {"타파스 - 3", "타파스(1)", "타파스-1, 초코케이크-2",
            "타파스-1,,초코케이크-2"})
    @ParameterizedTest
    void createOrderByInvalidFormat(String input) {
        assertThatThrownBy(() -> new Order(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 메뉴가 메뉴판에 없는 메뉴일 경우 예외가 발생한다.")
    @ValueSource(strings = {"삼겹살-1", "물냉면-1", "된장찌개-2"})
    @ParameterizedTest
    void createOrderByNotMenu(String input) {
        assertThatThrownBy(() -> new Order(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 메뉴의 개수가 양의 숫자가 아닐 경우 예외가 발생한다.")
    @ValueSource(strings = {"티본스테이크-a", "바비큐립-**", "제로콜라-1a", "초코케이크--1"})
    @ParameterizedTest
    void createOrderByInvalidTypeCount(String input) {
        assertThatThrownBy(() -> new Order(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 메뉴의 개수가 1 이상이 아닐 경우 예외가 발생한다.")
    @Test
    void createOrderByInvalidRangeCount() {
        assertThatThrownBy(() -> new Order("티본스테이크-0"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 메뉴가 중복될 경우 예외가 발생한다.")
    @ValueSource(strings = {"티본스테이크-3,티본스테이크-2", "제로콜라-1,초코케이크-1,제로콜라-3"})
    @ParameterizedTest
    void createOrderByDuplicatedMenu(String input) {
        assertThatThrownBy(() -> new Order(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
