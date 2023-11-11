package christmas.view;

import java.text.DecimalFormat;

public class OutputView {

    private static final String INFORM_TOTAL_AMOUNT = "<할인 전 총주문 금액>";
    private static final String FORMAT_OF_ORDER_MENU = "%s %d개";
    private static final String FORMAT_OF_TOTAL_AMOUNT = "%s원";

    public void printErrorMessage(IllegalArgumentException exception) {
        System.out.println(exception.getMessage());
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
}
