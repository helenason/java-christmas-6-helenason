package christmas.model;

public enum ErrorMessage {

    INVALID_DATE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    MORE_THAN_MAX("[ERROR] 메뉴는 한 번에 최대 20개 주문할 수 있습니다. 다시 입력해 주세요."),
    ONLY_DRINK("[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요.");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public void throwErrorWithMessage() {
        throw new IllegalArgumentException(message);
    }
}
