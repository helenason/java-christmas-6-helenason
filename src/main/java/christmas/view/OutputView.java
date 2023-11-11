package christmas.view;

public class OutputView {

    private static final String FORMAT_OF_ORDER_MENU = "%s %d개";

    public void printErrorMessage(IllegalArgumentException exception) {
        System.out.println(exception.getMessage());
    }

    public void printOrderMenu(String foodName, int count) {
        System.out.printf(FORMAT_OF_ORDER_MENU, foodName, count);
    }
}
