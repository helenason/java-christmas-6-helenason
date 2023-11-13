package christmas.view;

import christmas.constant.Menu;
import christmas.model.event.Event;

import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {

    private static final String GREETING_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PLANNER_START_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
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
    private static final String FORMAT_OF_NOTHING = "없음";
    private static final String FORMAT_OF_ZERO = "0원";

    public void printGreetingMessage() {
        System.out.println(GREETING_MESSAGE);
    }

    public void printStartMessage(int visitDate) {
        System.out.printf(PLANNER_START_MESSAGE, visitDate);
        System.out.println();
    }

    public void printErrorMessage(IllegalArgumentException exception) {
        System.out.println(exception.getMessage());
    }

    public void printOrderMenu(Map<Menu, Integer> orderMenus) {
        System.out.println(INFORM_ORDER_MENU);
        orderMenus.forEach(
                (menu, count) -> System.out.printf(FORMAT_OF_ORDER_MENU, menu.getFoodName(), count)
        );
        printNewLine();
    }

    public void printPresentMenu(String present) {
        System.out.println(INFORM_PRESENT_MENU);
        System.out.println(present);
        printNewLine();
    }

    public void printTotalAmount(int totalAmount) {
        System.out.println(INFORM_TOTAL_AMOUNT);
        System.out.printf(FORMAT_OF_TOTAL_AMOUNT, formatAmount(totalAmount));
        printNewLine();
    }

    public void printBenefits(Map<Event, Integer> benefits) {
        System.out.println(INFORM_BENEFITS_DETAILS);
        if (benefits.values().stream().allMatch(amount -> amount.equals(0))) {
            System.out.println(FORMAT_OF_NOTHING);
            printNewLine();
            return;
        }
        benefits.forEach(this::printAppliedBenefits);
        printNewLine();
    }

    private void printAppliedBenefits(Event event, Integer discountAmount) {
        if (discountAmount != 0) {
            System.out.printf(FORMAT_OF_APPLY_EVENT, event.getEventName(), formatAmount(discountAmount));
        }
    }

    public void printTotalDiscount(int discountAmount) {
        System.out.println(INFORM_TOTAL_DISCOUNT);

        if (discountAmount == 0) {
            System.out.println(FORMAT_OF_ZERO);
            printNewLine();
            return;
        }
        System.out.printf(FORMAT_OF_TOTAL_DISCOUNT, formatAmount(discountAmount));
        printNewLine();
    }

    public void printExpectedAmount(int expectedAmount) {
        System.out.println(INFORM_EXPECTED_AMOUNT);
        System.out.printf(FORMAT_OF_EXPECTED_AMOUNT, formatAmount(expectedAmount));
        printNewLine();
    }

    public void printEventBadge(String badge) {
        System.out.println(INFORM_EVENT_BADGE);
        System.out.println(badge);
    }

    private String formatAmount(int amount) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(amount);
    }

    private void printNewLine() {
        System.out.println();
    }
}
