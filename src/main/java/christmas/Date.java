package christmas;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Date {
    public static final String DATE_RE_READ_REQUEST_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final List<Integer> WEEKDAY_CONDITION = new ArrayList<>(Arrays.asList(0, 3, 4, 5, 6));
    private static final List<Integer> WEEKEND_CONDITION = new ArrayList<>(Arrays.asList(1, 2));
    private static final List<Integer> STARRED_CONDITION = new ArrayList<>(Arrays.asList(3, 25));
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final LocalDate CHRISTMAS_DAY = LocalDate.of(YEAR, MONTH, 25);
    private static final int DAY_NUMBER_JUDGEMENT_FACTOR = 7;
    private static final int DATE_MIN_NUMBER = 1;
    private static final int DATE_MAX_NUMBER = 31;

    private final int dayNumber;

    public Date(int dayNumber) {
        validateRange(dayNumber);
        this.dayNumber = dayNumber;
    }

    private void validateRange(int dayNumber) {
        if ((dayNumber < DATE_MIN_NUMBER) || (dayNumber > DATE_MAX_NUMBER)) {
            throw new IllegalArgumentException(DATE_RE_READ_REQUEST_MESSAGE);
        }
    }

    public LocalDate createdLocalDate() {
        return LocalDate.of(YEAR, MONTH, dayNumber);
    }

    public boolean isDDayDiscountActive() {
        return dayNumber <= CHRISTMAS_DAY.getDayOfWeek().getValue();
    }

    public boolean isWeekdayDiscountActive() {
        return WEEKDAY_CONDITION.contains(dayNumber % DAY_NUMBER_JUDGEMENT_FACTOR);
    }

    public boolean isWeekendDiscountActive() {
        return WEEKEND_CONDITION.contains(dayNumber % DAY_NUMBER_JUDGEMENT_FACTOR);
    }

    public boolean isSpecialDiscountActive() {
        return STARRED_CONDITION.contains(dayNumber % DAY_NUMBER_JUDGEMENT_FACTOR) || STARRED_CONDITION.contains(
                dayNumber);
    }

    public int getDayNumber() {
        return dayNumber;
    }
}