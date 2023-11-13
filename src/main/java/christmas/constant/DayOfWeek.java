package christmas.constant;

public enum DayOfWeek {

    MONDAY(4),
    TUESDAY(5),
    WEDNESDAY(6),
    THURSDAY(0),
    FRIDAY(1),
    SATURDAY(2),
    SUNDAY(3);

    private final int turn;

    DayOfWeek(int turn) {
        this.turn = turn;
    }

    public boolean isCorrectDayOfWeek(int date) {
        return turn == date % 7;
    }

    public boolean isWeekend() {
        return this == FRIDAY || this == SATURDAY;
    }
}
