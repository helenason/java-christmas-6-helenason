package christmas.controller;

import christmas.model.Order;
import christmas.model.event.*;
import christmas.view.OutputView;

public class EventManager {

    private static final int EVENT_START_DATE = 1;
    private static final int EVENT_END_DATE = 31;
    private static final int EVENT_CHRISTMAS_END_DATE = 25;

    private final OutputView outputView;
    private final ChristmasDiscount christmasDiscount;
    private final DayDiscount dayDiscount;
    private final SpecialDiscount specialDiscount;
    private final PresentEvent presentEvent;
    private final BadgeEvent badgeEvent;

    public EventManager(OutputView outputView) {
        this.outputView = outputView;
        christmasDiscount = new ChristmasDiscount(EVENT_START_DATE, EVENT_CHRISTMAS_END_DATE);
        dayDiscount = new DayDiscount(EVENT_START_DATE, EVENT_END_DATE);
        presentEvent = new PresentEvent(EVENT_START_DATE, EVENT_END_DATE);
        specialDiscount = new SpecialDiscount(EVENT_START_DATE, EVENT_END_DATE);
        badgeEvent = new BadgeEvent(EVENT_START_DATE, EVENT_END_DATE);
    }

    public void organizeBenefits(Order order, int date) {
        outputView.printDiscountDetails();

        int christmasDiscountAmount = organizeChristmasBenefits(date);
        int dayDiscountAmount = organizeDayBenefits(order, date);
        int specialDiscountAmount = organizeSpecialBenefits(date);
        int presentEventAmount = organizePresentBenefits(order);
        outputView.printNewLine();

        int totalDiscountAmount = christmasDiscountAmount
                + dayDiscountAmount
                + specialDiscountAmount
                + presentEventAmount;
        outputView.printTotalDiscount(totalDiscountAmount);
        outputView.printNewLine();

        int totalAmount = order.getTotalAmount();
        int expectedAmount = totalAmount - totalDiscountAmount + presentEventAmount;
        outputView.printExpectedAmount(expectedAmount);
        outputView.printNewLine();

        String badge = badgeEvent.calculate(totalDiscountAmount);
        outputView.printEventBadge(badge);
    }

    private int organizeChristmasBenefits(int date) {
        int christmasDiscountAmount = christmasDiscount.calculate(date);
        outputView.printChristmasDiscount(christmasDiscountAmount);
        return christmasDiscountAmount;
    }

    private int organizeDayBenefits(Order order, int date) {
        int dayDiscountAmount = dayDiscount.calculate(order, date);
        outputView.printDayDiscount(dayDiscountAmount);
        return dayDiscountAmount;
    }

    private int organizeSpecialBenefits(int date) {
        int specialDiscountAmount = specialDiscount.calculate(date);
        outputView.printSpecialDiscount(specialDiscountAmount);
        return specialDiscountAmount;
    }

    private int organizePresentBenefits(Order order) {
        int presentEventAmount = presentEvent.calculate(order.getTotalAmount());
        outputView.printPresentEvent(presentEventAmount);
        return presentEventAmount;
    }
}
