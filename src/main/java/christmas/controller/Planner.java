package christmas.controller;

import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Planner {

    private final InputView inputView;
    private final OutputView outputView;
    private VisitDate visitDate;
    private Order order;

    public Planner() {
        inputView = new InputView();
        outputView = new OutputView();
        init();
    }

    private void init() {
        visitDate = requestVisitDate();
        order = requestOrderMenu();
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
