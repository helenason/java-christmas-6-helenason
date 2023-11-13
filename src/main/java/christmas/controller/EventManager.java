package christmas.controller;

import christmas.model.DecemberCalendar;
import christmas.model.Order;
import christmas.model.event.*;
import christmas.view.OutputView;

public class EventManager {

    private static final int EVENT_START_DATE = 1;
    private static final int EVENT_END_DATE = 31;
    private static final int EVENT_CHRISTMAS_END_DATE = 25;

    private final OutputView outputView;
    private final ChristmasDiscount christmasDiscount;
    private final WeekdayDiscount weekdayDiscount;
    private final WeekendDiscount weekendDiscount;
    private final SpecialDiscount specialDiscount;
    private final PresentEvent presentEvent;
    private final BadgeEvent badgeEvent;
    private final Benefits benefits;

    public EventManager(OutputView outputView) {
        this.outputView = outputView;
        christmasDiscount = new ChristmasDiscount(EVENT_START_DATE, EVENT_CHRISTMAS_END_DATE);
        weekdayDiscount = new WeekdayDiscount(EVENT_START_DATE, EVENT_END_DATE);
        weekendDiscount = new WeekendDiscount(EVENT_START_DATE, EVENT_END_DATE);
        presentEvent = new PresentEvent(EVENT_START_DATE, EVENT_END_DATE);
        specialDiscount = new SpecialDiscount(EVENT_START_DATE, EVENT_END_DATE);
        badgeEvent = new BadgeEvent(EVENT_START_DATE, EVENT_END_DATE);
        benefits = new Benefits();
    }

    public void organizeBenefits(Order order, int date) {

        int presentEventAmount = organizePresentBenefits(order);
        int christmasDiscountAmount = organizeChristmasBenefits(date);
        int dayDiscountAmount = organizeDayBenefits(order, date);
        int specialDiscountAmount = organizeSpecialBenefits(date);

        if (isPresent(presentEventAmount)) { // 증정 메뉴
            String present = presentEvent.givePresent();
            outputView.printPresentMenu(present);
            outputView.printNewLine();
        }

        outputView.printBenefits(benefits.getBenefits());
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
        int discountAmount = christmasDiscount.calculate(date);
        benefits.createBenefit(Event.CHRISTMAS, discountAmount);
        return discountAmount;
    }

    private int organizeDayBenefits(Order order, int date) {
        if (DecemberCalendar.isWeekend(date)) {
            int discountAmount = weekendDiscount.calculate(order, date);
            benefits.createBenefit(Event.WEEKEND, discountAmount);
            return discountAmount;
        }
        int discountAmount = weekdayDiscount.calculate(order, date);
        benefits.createBenefit(Event.WEEKDAY, discountAmount);
        return discountAmount;
    }

    private int organizeSpecialBenefits(int date) {
        int discountAmount = specialDiscount.calculate(date);
        benefits.createBenefit(Event.SPECIAL, discountAmount);
        return discountAmount;
    }

    private int organizePresentBenefits(Order order) {
        int discountAmount = presentEvent.calculate(order.getTotalAmount());
        benefits.createBenefit(Event.PRESENT, discountAmount);
        return discountAmount;
    }

    private boolean isPresent(int presentEventAmount) {
        return presentEventAmount != 0;
    }
}
