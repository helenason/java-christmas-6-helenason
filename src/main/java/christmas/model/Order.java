package christmas.model;

import christmas.constant.Menu;

import java.util.Map;

public class Order {

    private final Map<Menu, Integer> orderMenu;

    public Order(String orderForm) {

        OrderHandler orderHandler = new OrderHandler();
        orderMenu = orderHandler.createOrder(orderForm);

    }
}
