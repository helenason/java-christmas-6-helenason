package christmas.controller;

import christmas.constant.Menu;
import christmas.constant.OutputMessage;
import christmas.model.DecemberCalendar;
import christmas.model.OrderMenus;
import christmas.model.VisitDate;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.HashMap;
import java.util.Map;

public class Planner {

    private static final int MIN_TOTAL_AMOUNT_FOR_EVENT = 10000;

    private final InputView inputView;
    private final OutputView outputView;
    private VisitDate visitDate;
    private OrderMenus orderMenus;

    public Planner() {
        inputView = new InputView();
        outputView = new OutputView();
        init();
        DecemberCalendar.of();
    }

    private void init() {
        outputView.printGreetingMessage();

        visitDate = requestVisitDate();
        orderMenus = requestOrderMenu();
        inputView.finishReading();

        outputView.printStartMessage(visitDate.getDay());

        informOrderMenu();
    }

    public void work() {
        int totalAmount = orderMenus.calculateTotalAmount();
        informTotalAmount(totalAmount);

        if (isNotAppliedEvent(totalAmount)) {
            notApplyEvent(totalAmount);
            return;
        }

        EventManager eventManager = new EventManager(outputView);
        eventManager.organizeBenefits(orderMenus, visitDate.getDay());
    }

    private void informOrderMenu() {

        Map<Menu, Integer> orderMenu = orderMenus.getOrderMenus();
        outputView.printOrderMenu(orderMenu);
    }

    private void informTotalAmount(int totalAmount) {
        outputView.printTotalAmount(totalAmount);
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

    private OrderMenus requestOrderMenu() {
        while (true) {
            try {
                String inputOfOrderMenu = inputView.readOrderMenu();
                return new OrderMenus(inputOfOrderMenu);
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception);
            }
        }
    }

    private void notApplyEvent(int totalAmount) {
        outputView.printPresentMenu(OutputMessage.FORMAT_OF_NOTHING.getMessage());
        outputView.printBenefits(new HashMap<>());
        outputView.printTotalDiscount(0);
        outputView.printExpectedAmount(totalAmount);
        outputView.printEventBadge(OutputMessage.FORMAT_OF_NOTHING.getMessage());
    }

    private boolean isNotAppliedEvent(int totalAmount) {
        return totalAmount < MIN_TOTAL_AMOUNT_FOR_EVENT;
    }
}
