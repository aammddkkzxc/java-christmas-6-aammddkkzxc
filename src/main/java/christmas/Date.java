package christmas;

public class Date {
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
