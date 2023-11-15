package christmas;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Date {
    public static final String DATE_RE_READ_REQUEST_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final LocalDate CHRISTMAS_DAY = LocalDate.of(2023, 12, 25);
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
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

    public LocalDate createLocalDate() {
        return LocalDate.of(YEAR, MONTH, dayNumber);
    }

    public boolean isDDayDiscountActive() {
        LocalDate localDate = createLocalDate();

        return localDate.isBefore(CHRISTMAS_DAY.plusDays(1));
    }

    public boolean isWeekdayDiscountActive() {
        LocalDate localDate = createLocalDate();
        DayOfWeek day = localDate.getDayOfWeek();

        return (day.equals(DayOfWeek.MONDAY) || day.equals(DayOfWeek.TUESDAY) || day.equals(DayOfWeek.WEDNESDAY)
                || day.equals(DayOfWeek.THURSDAY) || day.equals(DayOfWeek.SUNDAY));
    }

    public boolean isWeekendDiscountActive() {
        LocalDate localDate = createLocalDate();
        DayOfWeek day = localDate.getDayOfWeek();

        return (day.equals(DayOfWeek.FRIDAY) || day.equals(DayOfWeek.SATURDAY));
    }

    public boolean isSpecialDiscountActive() {
        LocalDate localDate = createLocalDate();
        DayOfWeek day = localDate.getDayOfWeek();

        return (day.equals(DayOfWeek.SUNDAY) || localDate.equals(CHRISTMAS_DAY));
    }

    public int getDayNumber() {
        return dayNumber;
    }
}