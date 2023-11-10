package christmas.model;

public enum ErrorMessage {

    INVALID_DATE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public void throwErrorWithMessage() {
        throw new IllegalArgumentException(message);
    }
}
