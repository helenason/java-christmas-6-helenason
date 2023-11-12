package christmas.controller;

import christmas.constant.Menu;
import christmas.model.DecemberCalendar;
import christmas.model.Order;
import christmas.model.VisitDate;
import christmas.model.event.ChristmasDiscount;
import christmas.model.event.DayDiscount;
import christmas.model.event.PresentEvent;
import christmas.model.event.SpecialDiscount;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Set;

public class Planner {

    private static final int EVENT_START_DATE = 1;
    private static final int EVENT_END_DATE = 1;
    private static final int EVENT_CHRISTMAS_END_DATE = 1;


    private final InputView inputView;
    private final OutputView outputView;
    private VisitDate visitDate;
    private Order order;

    private ChristmasDiscount christmasDiscount;
    private DayDiscount dayDiscount;
    private PresentEvent presentEvent;
    private SpecialDiscount specialDiscount;

    public Planner() {
        inputView = new InputView();
        outputView = new OutputView();
        init();
        DecemberCalendar.of();
        christmasDiscount = new ChristmasDiscount(EVENT_START_DATE, EVENT_CHRISTMAS_END_DATE);
        dayDiscount = new DayDiscount(EVENT_START_DATE, EVENT_END_DATE);
        presentEvent = new PresentEvent(EVENT_START_DATE, EVENT_END_DATE);
        specialDiscount = new SpecialDiscount(EVENT_START_DATE, EVENT_END_DATE);
    }

    private void init() {
        visitDate = requestVisitDate();
        order = requestOrderMenu();

        informOrderMenu();

        order.createTotalAmount();
        informTotalAmount();
    }

    public void work() {
        organizeDiscountDetails();
    }

    private void organizeDiscountDetails() {
        outputView.printDiscountDetails();

        int date = visitDate.getDay();

        int christmasDiscountAmount = christmasDiscount.calculate(date);
        int dayDiscountAmount = dayDiscount.calculate(order, date);
        int specialDiscountAmount = specialDiscount.calculate(date);
        int presentEventAmount = presentEvent.calculate(order.getTotalAmount());

        outputView.printChristmasDiscount(christmasDiscountAmount);
        outputView.printDayDiscount(dayDiscountAmount);
        outputView.printSpecialDiscount(specialDiscountAmount);
        outputView.printPresentEvent(presentEventAmount);

        int totalDiscountAmount = christmasDiscountAmount
                + dayDiscountAmount
                + specialDiscountAmount
                + presentEventAmount;
        outputView.printTotalDiscount(totalDiscountAmount);

        int totalAmount = order.getTotalAmount();
        int expectedAmount = totalAmount - totalDiscountAmount;
        outputView.printExpectedAmount(expectedAmount);
    }

    private void informOrderMenu() {
        Set<Menu> menus = order.getAllMenus();
        for (Menu menu : menus) {
            String foodName = menu.getFoodName();
            int count = order.getCountOfMenu(menu);
            outputView.printOrderMenu(foodName, count);
        }
    }

    private void informTotalAmount() {
        int totalAmount = order.getTotalAmount();
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
