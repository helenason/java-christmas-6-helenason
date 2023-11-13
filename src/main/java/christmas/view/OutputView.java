package christmas.view;

import java.text.DecimalFormat;

public class OutputView {

    private static final String GREETING_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PLANNER_START_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n\n";
    private static final String INFORM_ORDER_MENU = "<주문 메뉴>";
    private static final String INFORM_TOTAL_AMOUNT = "<할인 전 총주문 금액>";
    private static final String INFORM_DISCOUNT_DETAILS = "<혜택 내역>";
    private static final String INFORM_TOTAL_DISCOUNT = "<총혜택 금액>";
    private static final String INFORM_EXPECTED_AMOUNT = "<할인 후 예상 결제 금액>";
    private static final String INFORM_EVENT_BADGE = "<12월 이벤트 배지>";
    private static final String FORMAT_OF_ORDER_MENU = "%s %d개\n";
    private static final String FORMAT_OF_TOTAL_AMOUNT = "%s원\n";
    private static final String FORMAT_OF_CHRISTMAS_DISCOUNT = "크리스마스 디데이 할인: -%s원\n";
    private static final String FORMAT_OF_DAY_DISCOUNT = "평일 할인: -%s원\n";
    private static final String FORMAT_OF_SPECIAL_DISCOUNT = "특별 할인: -%s원\n";
    private static final String FORMAT_OF_PRESENT_EVENT = "증정 이벤트: -%s원\n";
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

    public void printTotalAmount(int totalAmount) {
        System.out.println(INFORM_TOTAL_AMOUNT);

        DecimalFormat formatter = new DecimalFormat("#,###"); //
        String formatTotalAmount = formatter.format(totalAmount);
        System.out.printf(FORMAT_OF_TOTAL_AMOUNT, formatTotalAmount);
    }

    public void printDiscountDetails() {
        System.out.println(INFORM_DISCOUNT_DETAILS);
    }

    public void printChristmasDiscount(int discountAmount) {
        DecimalFormat formatter = new DecimalFormat("#,###"); //
        String formatDiscountAmount = formatter.format(discountAmount);
        System.out.printf(FORMAT_OF_CHRISTMAS_DISCOUNT, formatDiscountAmount);
    }

    public void printDayDiscount(int discountAmount) {
        DecimalFormat formatter = new DecimalFormat("#,###"); //
        String formatDiscountAmount = formatter.format(discountAmount);
        System.out.printf(FORMAT_OF_DAY_DISCOUNT, formatDiscountAmount);
    }

    public void printSpecialDiscount(int discountAmount) {
        DecimalFormat formatter = new DecimalFormat("#,###"); //
        String formatDiscountAmount = formatter.format(discountAmount);
        System.out.printf(FORMAT_OF_SPECIAL_DISCOUNT, formatDiscountAmount);
    }

    public void printPresentEvent(int discountAmount) {
        DecimalFormat formatter = new DecimalFormat("#,###"); //
        String formatDiscountAmount = formatter.format(discountAmount);
        System.out.printf(FORMAT_OF_PRESENT_EVENT, formatDiscountAmount);
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
