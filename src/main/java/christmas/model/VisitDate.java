package christmas.model;

import christmas.constant.ErrorMessage;

public class VisitDate {

    private static final int FIRST_DAY = 1;
    private static final int LAST_DAY = 31;

    private final int day;

    public VisitDate(String dayForm) {
        validate(dayForm);
        this.day = stringToInt(dayForm);
    }

    private void validate(String dayForm) {
        validateType(dayForm);
        validateRange(dayForm);
    }

    private void validateType(String dayForm) {
        char[] dayPieces = dayForm.toCharArray();
        for (char dayPiece : dayPieces) {
            if (!Character.isDigit(dayPiece)) {
                ErrorMessage.INVALID_DATE.throwErrorWithMessage();
            }
        }
    }

    private void validateRange(String dayForm) {
        int day = stringToInt(dayForm);
        if (day < FIRST_DAY || day > LAST_DAY) {
            ErrorMessage.INVALID_DATE.throwErrorWithMessage();
        }
    }

    private int stringToInt(String before) {
        return Integer.parseInt(before);
    }

    public int getDay() {
        return day;
    }
}
