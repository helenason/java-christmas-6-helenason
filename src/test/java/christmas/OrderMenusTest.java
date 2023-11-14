package christmas;

import christmas.constant.Menu;
import christmas.model.OrderMenus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class OrderMenusTest {

    @DisplayName("메뉴의 형식이 예시와 다른 경우 예외가 발생한다.")
    @ValueSource(strings = {"타파스 - 3", "타파스(1)", "타파스-1, 초코케이크-2",
            "타파스-1,,초코케이크-2"})
    @ParameterizedTest
    void createOrderByInvalidFormat(String input) {
        assertThatThrownBy(() -> new OrderMenus(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 메뉴가 메뉴판에 없는 메뉴일 경우 예외가 발생한다.")
    @ValueSource(strings = {"삼겹살-1", "물냉면-1", "된장찌개-2"})
    @ParameterizedTest
    void createOrderByNotMenu(String input) {
        assertThatThrownBy(() -> new OrderMenus(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 메뉴의 개수가 양의 숫자가 아닐 경우 예외가 발생한다.")
    @ValueSource(strings = {"티본스테이크-a", "바비큐립-**", "제로콜라-1a", "초코케이크--1"})
    @ParameterizedTest
    void createOrderByInvalidTypeCount(String input) {
        assertThatThrownBy(() -> new OrderMenus(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 메뉴의 개수가 1 이상이 아닐 경우 예외가 발생한다.")
    @Test
    void createOrderByInvalidRangeCount() {
        assertThatThrownBy(() -> new OrderMenus("티본스테이크-0"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 메뉴가 중복될 경우 예외가 발생한다.")
    @ValueSource(strings = {"티본스테이크-3,티본스테이크-2", "제로콜라-1,초코케이크-1,제로콜라-3"})
    @ParameterizedTest
    void createOrderByDuplicatedMenu(String input) {
        assertThatThrownBy(() -> new OrderMenus(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문할 경우 예외가 발생한다.")
    @ValueSource(strings = {"레드와인-3,샴페인-2", "제로콜라-1"})
    @ParameterizedTest
    void createOrderByOnlyDrink(String input) {
        assertThatThrownBy(() -> new OrderMenus(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴가 20개를 넘어가면 예외가 발생한다.")
    @ValueSource(strings = {"티본스테이크-21", "제로콜라-5,아이스크림-5,양송이수프-15"})
    @ParameterizedTest
    void createOrderMoreThanMax(String input) {
        assertThatThrownBy(() -> new OrderMenus(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 내역의 모든 메뉴와 주문 개수를 반환한다.")
    @Test
    void returnAllMenusFromOrder() {

        OrderMenus order = new OrderMenus("티본스테이크-1,아이스크림-2,양송이수프-3");
        Map<Menu, Integer> orderMenus = order.getOrderMenus();

        Map<Menu, Integer> orderMenusForCompare = new HashMap<>();
        orderMenusForCompare.put(Menu.T_BONE_STEAK, 1);
        orderMenusForCompare.put(Menu.ICE_CREAM, 2);
        orderMenusForCompare.put(Menu.MUSHROOM_SOUP, 3);

        assertThat(orderMenus).isEqualTo(orderMenusForCompare);
    }

    @DisplayName("주문 내역에 대해 할인 전 전체 금액을 계산한다.")
    @Test
    void calculateTotalAmount() {

        OrderMenus order = new OrderMenus("티본스테이크-1,아이스크림-2,양송이수프-3");

        order.calculateTotalAmount();

        int expectedAmount = Menu.T_BONE_STEAK.calculateAmountOf(1)
                + Menu.ICE_CREAM.calculateAmountOf(2)
                + Menu.MUSHROOM_SOUP.calculateAmountOf(3);
        assertThat(order.calculateTotalAmount()).isEqualTo(expectedAmount);
    }

    @DisplayName("주문 메뉴 중 메인 메뉴의 개수를 센다.")
    @Test
    void countMainMenuInOrderMenu() {
        OrderMenus orderMenus = new OrderMenus("티본스테이크-1,크리스마스파스타-2,양송이수프-3");

        int mainCount = orderMenus.countMainType();

        assertThat(mainCount).isEqualTo(3);
    }

    @DisplayName("주문 메뉴 중 메인 메뉴의 개수가 0개이다.")
    @ValueSource(strings = {"초코케이크-2", "아이스크림-1", "양송이수프-3,제로콜라-1"})
    @ParameterizedTest
    void countNoneMainMenuInOrderMenu(String input) {
        OrderMenus orderMenus = new OrderMenus(input);

        int mainCount = orderMenus.countMainType();

        assertThat(mainCount).isEqualTo(0);
    }

    @DisplayName("주문 메뉴 중 디저트 메뉴의 개수를 센다.")
    @Test
    void countDessertMenuInOrderMenu() {
        OrderMenus orderMenus = new OrderMenus("티본스테이크-1,초코케이크-2,양송이수프-3");

        int mainCount = orderMenus.countDessertType();

        assertThat(mainCount).isEqualTo(2);
    }

    @DisplayName("주문 메뉴 중 디저트 메뉴의 개수가 0개이다.")
    @ValueSource(strings = {"티본스테이크-1,샴페인-2", "크리스마스파스타-1", "양송이수프-3"})
    @ParameterizedTest
    void countNoneDessertMenuInOrderMenu(String input) {
        OrderMenus orderMenus = new OrderMenus(input);

        int mainCount = orderMenus.countDessertType();

        assertThat(mainCount).isEqualTo(0);
    }
}
