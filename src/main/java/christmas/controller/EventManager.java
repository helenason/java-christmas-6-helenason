package christmas.controller;

import christmas.constant.Event;
import christmas.model.Benefits;
import christmas.model.OrderMenus;
import christmas.model.event.*;
import christmas.view.OutputView;

public class EventManager {

    private final OutputView outputView;
    private final Benefits benefits;

    public EventManager(OutputView outputView) {
        this.outputView = outputView;
        benefits = new Benefits();
    }

    public void organizeBenefits(OrderMenus orderMenus, int date) {
        organizeEveryBenefits(orderMenus, date);
        int presentEventAmount = organizePresentation();
        outputView.printBenefits(benefits.getBenefits());

        int totalAmount = orderMenus.calculateTotalAmount();
        int totalDiscountAmount = organizeTotalDiscountAmount();
        organizeExpectedAmount(totalAmount, presentEventAmount, totalDiscountAmount);
        organizeBadgeEvent(totalDiscountAmount, date);
    }

    private int organizeTotalDiscountAmount() {
        int totalDiscountAmount = benefits.calculateTotalDiscountAmount();
        outputView.printTotalDiscount(totalDiscountAmount);
        return totalDiscountAmount;
    }

    private void organizeExpectedAmount(int totalAmount, int presentEventAmount, int totalDiscountAmount) {
        int expectedAmount = totalAmount - totalDiscountAmount + presentEventAmount;
        outputView.printExpectedAmount(expectedAmount);
    }

    private void organizeBadgeEvent(int totalDiscountAmount, int date) {
        String badge = BadgeEvent.calculate(totalDiscountAmount, date);
        outputView.printEventBadge(badge);
    }

    private void organizeEveryBenefits(OrderMenus orderMenus, int date) {
        organizePresentBenefits(orderMenus, date);
        organizeChristmasBenefits(date);
        organizeWeekdayBenefits(orderMenus, date);
        organizeWeekendBenefits(orderMenus, date);
        organizeSpecialBenefits(date);
    }

    private int organizePresentation() {
        int presentEventAmount = benefits.getPresentEventDiscountAmount();
        String present = PresentEvent.givePresent(presentEventAmount);
        outputView.printPresentMenu(present);
        return presentEventAmount;
    }

    private void organizeChristmasBenefits(int date) {
        int discountAmount = ChristmasDiscount.calculate(date);
        benefits.createBenefit(Event.CHRISTMAS, discountAmount);
    }

    private void organizeWeekdayBenefits(OrderMenus orderMenus, int date) {
        int discountAmount = WeekdayDiscount.calculate(orderMenus, date);
        benefits.createBenefit(Event.WEEKDAY, discountAmount);
    }

    private void organizeWeekendBenefits(OrderMenus orderMenus, int date) {
        int discountAmount = WeekendDiscount.calculate(orderMenus, date);
        benefits.createBenefit(Event.WEEKEND, discountAmount);
    }

    private void organizeSpecialBenefits(int date) {
        int discountAmount = SpecialDiscount.calculate(date);
        benefits.createBenefit(Event.SPECIAL, discountAmount);
    }

    private void organizePresentBenefits(OrderMenus orderMenus, int date) {
        int discountAmount = PresentEvent.calculate(orderMenus.calculateTotalAmount(), date);
        benefits.createBenefit(Event.PRESENT, discountAmount);
    }
}
