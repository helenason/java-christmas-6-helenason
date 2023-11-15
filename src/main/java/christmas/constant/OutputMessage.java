package christmas.constant;

public enum OutputMessage {

    GREETING_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    PLANNER_START_MESSAGE("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n"),
    INFORM_ORDER_MENU("<주문 메뉴>"),
    INFORM_TOTAL_AMOUNT("<할인 전 총주문 금액>"),
    INFORM_PRESENT_MENU("<증정 메뉴>"),
    INFORM_BENEFITS_DETAILS("<혜택 내역>"),
    INFORM_TOTAL_DISCOUNT("<총혜택 금액>"),
    INFORM_EXPECTED_AMOUNT("<할인 후 예상 결제 금액>"),
    INFORM_EVENT_BADGE("<12월 이벤트 배지>"),
    FORMAT_OF_ORDER_MENU("%s %d개\n"),
    FORMAT_OF_TOTAL_AMOUNT("%s원\n"),
    FORMAT_OF_APPLY_EVENT("%s: -%s원\n"),
    FORMAT_OF_TOTAL_DISCOUNT("-%s원\n"),
    FORMAT_OF_EXPECTED_AMOUNT("%s원\n"),
    FORMAT_OF_NOTHING("없음"),
    FORMAT_OF_ZERO("0원"),
    FORMAT_OF_PRICE_NUMBER("#,###");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
