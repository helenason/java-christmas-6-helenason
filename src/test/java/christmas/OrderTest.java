package christmas;

import christmas.constant.Menu;
import christmas.model.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class OrderTest {

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

    @DisplayName("음료만 주문할 경우 예외가 발생한다.")
    @ValueSource(strings = {"레드와인-3,샴페인-2", "제로콜라-1"})
    @ParameterizedTest
    void createOrderByOnlyDrink(String input) {
        assertThatThrownBy(() -> new Order(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴가 20개를 넘어가면 예외가 발생한다.")
    @ValueSource(strings = {"티본스테이크-21", "제로콜라-5,아이스크림-5,양송이수프-15"})
    @ParameterizedTest
    void createOrderMoreThanMax(String input) {
        assertThatThrownBy(() -> new Order(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 내역의 모든 메뉴를 반환한다.")
    @Test
    void returnAllMenusFromOrder() {

        Order order = new Order("티본스테이크-1,아이스크림-2,양송이수프-3");

        Set<Menu> allMenus = order.getAllMenus();

        assertThat(allMenus)
                .isEqualTo(new HashSet<>(List.of(Menu.T_BONE_STEAK, Menu.ICE_CREAM, Menu.MUSHROOM_SOUP)));
    }

    @DisplayName("주문 내역에서 해당 메뉴의 주문 개수를 반환한다.")
    @Test
    void returnCountOfMenu() {

        Order order = new Order("티본스테이크-1,아이스크림-2,양송이수프-3");

        int steakCount = order.getCountOfMenu(Menu.T_BONE_STEAK);
        int iceCreamCount = order.getCountOfMenu(Menu.ICE_CREAM);
        int soupCount = order.getCountOfMenu(Menu.MUSHROOM_SOUP);

        assertThat(steakCount).isEqualTo(1);
        assertThat(iceCreamCount).isEqualTo(2);
        assertThat(soupCount).isEqualTo(3);
    }
}
