package christmas.view;

import christmas.model.event.Event;

import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {

    private static final String GREETING_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PLANNER_START_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n\n";
    private static final String INFORM_ORDER_MENU = "<주문 메뉴>";
    private static final String INFORM_TOTAL_AMOUNT = "<할인 전 총주문 금액>";
    private static final String INFORM_PRESENT_MENU = "<증정 메뉴>";
    private static final String INFORM_BENEFITS_DETAILS = "<혜택 내역>";
    private static final String INFORM_TOTAL_DISCOUNT = "<총혜택 금액>";
    private static final String INFORM_EXPECTED_AMOUNT = "<할인 후 예상 결제 금액>";
    private static final String INFORM_EVENT_BADGE = "<12월 이벤트 배지>";
    private static final String FORMAT_OF_ORDER_MENU = "%s %d개\n";
    private static final String FORMAT_OF_TOTAL_AMOUNT = "%s원\n";
    private static final String FORMAT_OF_APPLY_EVENT = "%s: -%s원\n";
    private static final String FORMAT_OF_TOTAL_DISCOUNT = "-%s원\n";
    private static final String FORMAT_OF_EXPECTED_AMOUNT = "%s원\n";

    public void printNewLine() {
        System.out.println();
    }

    public void printGreetingMessage() {
        System.out.println(GREETING_MESSAGE);
    }

    public void printStartMessage(int visitDate) {
        System.out.printf(PLANNER_START_MESSAGE, visitDate);
    }

    public void printErrorMessage(IllegalArgumentException exception) {
        System.out.println(exception.getMessage());
    }

    public void printOrderMenu() {
        System.out.println(INFORM_ORDER_MENU);
    }

    public void printOrderMenu(String foodName, int count) {
        System.out.printf(FORMAT_OF_ORDER_MENU, foodName, count);
    }

    public void printPresentMenu(String present) {
        System.out.println(INFORM_PRESENT_MENU);
        System.out.printf(present);
    }

    public void printTotalAmount(int totalAmount) {
        System.out.println(INFORM_TOTAL_AMOUNT);

        DecimalFormat formatter = new DecimalFormat("#,###"); //
        String formatTotalAmount = formatter.format(totalAmount);
        System.out.printf(FORMAT_OF_TOTAL_AMOUNT, formatTotalAmount);
    }

    public void printBenefits(Map<Event, Integer> benefits) {
        System.out.println(INFORM_BENEFITS_DETAILS);
        benefits.forEach(this::printAppliedBenefits);
    }

    private void printAppliedBenefits(Event event, Integer discountAmount) {
        if (discountAmount != 0) {
            DecimalFormat formatter = new DecimalFormat("#,###");
            String formatDiscountAmount = formatter.format(discountAmount);
            System.out.printf(FORMAT_OF_APPLY_EVENT, event.getEventName(), formatDiscountAmount);
        }
    }

    public void printTotalDiscount(int discountAmount) {
        System.out.println(INFORM_TOTAL_DISCOUNT);

        DecimalFormat formatter = new DecimalFormat("#,###"); //
        String formatDiscountAmount = formatter.format(discountAmount);
        System.out.printf(FORMAT_OF_TOTAL_DISCOUNT, formatDiscountAmount);
    }

    public void printExpectedAmount(int expectedAmount) {
        System.out.println(INFORM_EXPECTED_AMOUNT);

        DecimalFormat formatter = new DecimalFormat("#,###"); //
        String formatExpectedAmount = formatter.format(expectedAmount);
        System.out.printf(FORMAT_OF_EXPECTED_AMOUNT, formatExpectedAmount);
    }

    public void printEventBadge(String badge) {
        System.out.println(INFORM_EVENT_BADGE);
        System.out.println(badge);
    }
}
