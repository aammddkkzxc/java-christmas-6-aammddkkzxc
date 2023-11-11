package christmas;

public class Date {
    public static final String DATE_RE_INPUT_REQUEST_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    private final int dayNumber;

    public Date(int dayNumber) {
        this.dayNumber = dayNumber;
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
