package christmas.constant;

public enum Event {

    CHRISTMAS("크리스마스 디데이 할인", 1, 25),
    WEEKDAY("평일 할인", 1, 31),
    WEEKEND("주말 할인", 1, 31),
    SPECIAL("특별 할인", 1, 31),
    PRESENT("증정 이벤트", 1, 31),
    BADGE("이벤트 배지", 1, 31);

    private final String eventName;
    private final int startDate;
    private final int endDate;

    Event(String eventName, int startDate, int endDate) {
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getEventName() {
        return eventName;
    }

    public boolean isInvalidPeriod(int date) {
        return date < startDate || date > endDate;
    }

    public boolean isAfterEndDate(int date) {
        return date > endDate;
    }

    public int gapBetweenStartDateAndDate(int date) {
        return date - startDate;
    }
}
