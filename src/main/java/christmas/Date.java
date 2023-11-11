package christmas;

public class Date {
    public static final String DATE_RE_INPUT_REQUEST_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final int DATE_MIN_NUMBER = 1;
    private static final int DATE_MAX_NUMBER = 31;

    private final int dayNumber;

    public Date(int dayNumber) {
        validateRange(dayNumber);
        this.dayNumber = dayNumber;
    }

    private void validateRange(int dayNumber) {
        if ((dayNumber < DATE_MIN_NUMBER) || (dayNumber > DATE_MAX_NUMBER)) {
            throw new IllegalArgumentException(DATE_RE_INPUT_REQUEST_MESSAGE);
        }
    }

    public boolean isUntilChristmas() {
        return true;
    }

    public boolean isWeekday() {
        return true;
    }

    public boolean isWeekend() {
        return true;
    }

    public boolean isStarred() {
        return true;
    }
}
