package christmas.controller;

import christmas.model.VisitDate;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Planner {

    private final InputView inputView;
    private final OutputView outputView;
    private VisitDate visitDate;

    public Planner() {
        inputView = new InputView();
        outputView = new OutputView();
        init();
    }

    private void init() {
        visitDate = requestVisitDate();
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
}
