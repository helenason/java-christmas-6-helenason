package christmas.view;

import christmas.constant.Menu;
import christmas.constant.Event;
import christmas.constant.OutputMessage;

import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {

    public void printGreetingMessage() {
        System.out.println(OutputMessage.GREETING_MESSAGE.getMessage());
    }

    public void printStartMessage(int visitDate) {
        System.out.printf(OutputMessage.PLANNER_START_MESSAGE.getMessage(), visitDate);
        System.out.println();
    }

    public void printErrorMessage(IllegalArgumentException exception) {
        System.out.println(exception.getMessage());
    }

    public void printOrderMenu(Map<Menu, Integer> orderMenus) {
        System.out.println(OutputMessage.INFORM_ORDER_MENU.getMessage());
        orderMenus.forEach(
                (menu, count) -> {
                    System.out.printf(OutputMessage.FORMAT_OF_ORDER_MENU.getMessage(), menu.getFoodName(), count);
                }
        );
        printNewLine();
    }

    public void printPresentMenu(String present) {
        System.out.println(OutputMessage.INFORM_PRESENT_MENU.getMessage());
        System.out.println(present);
        printNewLine();
    }

    public void printTotalAmount(int totalAmount) {
        System.out.println(OutputMessage.INFORM_TOTAL_AMOUNT.getMessage());
        System.out.printf(OutputMessage.FORMAT_OF_TOTAL_AMOUNT.getMessage(), formatAmount(totalAmount));
        printNewLine();
    }

    public void printBenefits(Map<Event, Integer> benefits) {
        System.out.println(OutputMessage.INFORM_BENEFITS_DETAILS.getMessage());
        if (benefits.values().stream().allMatch(amount -> amount.equals(0))) {
            System.out.println(OutputMessage.FORMAT_OF_NOTHING.getMessage());
            printNewLine();
            return;
        }
        benefits.forEach(this::printAppliedBenefits);
        printNewLine();
    }

    private void printAppliedBenefits(Event event, Integer discountAmount) {
        if (discountAmount != 0) {
            System.out.printf(OutputMessage.FORMAT_OF_APPLY_EVENT.getMessage(),
                    event.getEventName(), formatAmount(discountAmount));
        }
    }

    public void printTotalDiscount(int discountAmount) {
        System.out.println(OutputMessage.INFORM_TOTAL_DISCOUNT.getMessage());

        if (discountAmount == 0) {
            System.out.println(OutputMessage.FORMAT_OF_ZERO.getMessage());
            printNewLine();
            return;
        }
        System.out.printf(OutputMessage.FORMAT_OF_TOTAL_DISCOUNT.getMessage(), formatAmount(discountAmount));
        printNewLine();
    }

    public void printExpectedAmount(int expectedAmount) {
        System.out.println(OutputMessage.INFORM_EXPECTED_AMOUNT.getMessage());
        System.out.printf(OutputMessage.FORMAT_OF_EXPECTED_AMOUNT.getMessage(), formatAmount(expectedAmount));
        printNewLine();
    }

    public void printEventBadge(String badge) {
        System.out.println(OutputMessage.INFORM_EVENT_BADGE.getMessage());
        System.out.println(badge);
    }

    private String formatAmount(int amount) {
        DecimalFormat formatter = new DecimalFormat(OutputMessage.FORMAT_OF_PRICE_NUMBER.getMessage());
        return formatter.format(amount);
    }

    private void printNewLine() {
        System.out.println();
    }
}
