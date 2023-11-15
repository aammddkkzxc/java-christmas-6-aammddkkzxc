package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateTest {
    private Date date;

    @BeforeEach
    void setUP() {
        date = new Date(24);
    }

    @DisplayName("주어진 날짜가 크리스마스 이전일 경우 디데이 할인에 해당한다.")
    @Test
    void isDDayDiscountActive() {
        boolean result = date.isDDayDiscountActive();

        assertThat(result).isEqualTo(true);
    }

    @DisplayName("주어진 날짜가 평일로 정의한 날에 해당할 경우 평일 할인에 해당한다.")
    @Test
    void isWeekdayDiscountActive() {
        boolean result = date.isWeekdayDiscountActive();

        assertThat(result).isEqualTo(true);
    }

    @DisplayName("주어진 날짜가 주말로 정의한 날에 해당할 경우 주말 할인에 해당한다.")
    @Test
    void isWeekendDiscountActive() {
        boolean result = date.isWeekendDiscountActive();

        assertThat(result).isEqualTo(false);
    }
}