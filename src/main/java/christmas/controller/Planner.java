package christmas.controller;

import christmas.constant.Menu;
import christmas.model.DecemberCalendar;
import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Set;

public class Planner {

    private final InputView inputView;
    private final OutputView outputView;
    private final EventManager eventManager;
    private VisitDate visitDate;
    private Order order;

    public Planner() {
        inputView = new InputView();
        outputView = new OutputView();
        eventManager = new EventManager(outputView);
        init();
        DecemberCalendar.of();
    }

    private void init() {
        outputView.printGreetingMessage();

        visitDate = requestVisitDate();
        order = requestOrderMenu();
        inputView.finishReading();

        outputView.printStartMessage(visitDate.getDay());

        informOrderMenu();

        order.createTotalAmount();
        informTotalAmount();
    }

    public void work() {
        eventManager.organizeBenefits(order, visitDate.getDay());
    }

    private void informOrderMenu() {
        outputView.printOrderMenu();
        Set<Menu> menus = order.getAllMenus();
        for (Menu menu : menus) {
            String foodName = menu.getFoodName();
            int count = order.getCountOfMenu(menu);
            outputView.printOrderMenu(foodName, count);
        }
        outputView.printNewLine();
    }

    private void informTotalAmount() {
        int totalAmount = order.getTotalAmount();
        outputView.printTotalAmount(totalAmount);
        outputView.printNewLine();
    }

    private VisitDate requestVisitDate() {
        while (true) {
            try {
                String inputOfVisitDate = inputView.readVisitDate();
                return new VisitDate(inputOfVisitDate);
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception);
            }
        }
    }

    private Order requestOrderMenu() {
        while (true) {
            try {
                String inputOfOrderMenu = inputView.readOrderMenu();
                return new Order(inputOfOrderMenu);
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception);
            }
        }
    }
}
