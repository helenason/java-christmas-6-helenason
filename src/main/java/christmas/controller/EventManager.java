package christmas.controller;

import christmas.model.DecemberCalendar;
import christmas.model.OrderMenus;
import christmas.model.event.*;
import christmas.view.OutputView;

public class EventManager {

    private static final int MIN_TOTAL_AMOUNT_FOR_EVENT = 10000;

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
        christmasDiscount = new ChristmasDiscount();
        weekdayDiscount = new WeekdayDiscount();
        weekendDiscount = new WeekendDiscount();
        presentEvent = new PresentEvent();
        specialDiscount = new SpecialDiscount();
        badgeEvent = new BadgeEvent();
        benefits = new Benefits();
    }

    public void organizeBenefits(OrderMenus orderMenus, int date) {

        int totalAmount = orderMenus.calculateTotalAmount();

        if (totalAmount < MIN_TOTAL_AMOUNT_FOR_EVENT) {
            outputView.printPresentMenu("없음");
            outputView.printBenefits(benefits.getBenefits());
            outputView.printTotalDiscount(0);
            outputView.printExpectedAmount(totalAmount);
            outputView.printEventBadge("없음");
            return;
        }

        int presentEventAmount = organizePresentBenefits(orderMenus, date);
        int christmasDiscountAmount = organizeChristmasBenefits(date);
        int dayDiscountAmount = organizeDayBenefits(orderMenus, date);
        int specialDiscountAmount = organizeSpecialBenefits(date);

        organizePresentation(presentEventAmount);

        outputView.printBenefits(benefits.getBenefits());

        int totalDiscountAmount = christmasDiscountAmount
                + dayDiscountAmount
                + specialDiscountAmount
                + presentEventAmount;
        outputView.printTotalDiscount(totalDiscountAmount);

        int expectedAmount = totalAmount - totalDiscountAmount + presentEventAmount;
        outputView.printExpectedAmount(expectedAmount);

        String badge = badgeEvent.calculate(totalDiscountAmount, date);
        outputView.printEventBadge(badge);
    }

    private void organizePresentation(int presentEventAmount) {
        String present = presentEvent.givePresent(presentEventAmount);
        outputView.printPresentMenu(present);
    }

    private int organizeChristmasBenefits(int date) {
        int discountAmount = christmasDiscount.calculate(date);
        benefits.createBenefit(Event.CHRISTMAS, discountAmount);
        return discountAmount;
    }

    private int organizeDayBenefits(OrderMenus orderMenus, int date) {
        if (DecemberCalendar.isWeekend(date)) {
            int discountAmount = weekendDiscount.calculate(orderMenus, date);
            benefits.createBenefit(Event.WEEKEND, discountAmount);
            return discountAmount;
        }
        int discountAmount = weekdayDiscount.calculate(orderMenus, date);
        benefits.createBenefit(Event.WEEKDAY, discountAmount);
        return discountAmount;
    }

    private int organizeSpecialBenefits(int date) {
        int discountAmount = specialDiscount.calculate(date);
        benefits.createBenefit(Event.SPECIAL, discountAmount);
        return discountAmount;
    }

    private int organizePresentBenefits(OrderMenus orderMenus, int date) {
        int discountAmount = presentEvent.calculate(orderMenus.calculateTotalAmount(), date);
        benefits.createBenefit(Event.PRESENT, discountAmount);
        return discountAmount;
    }
}
