package christmas.ui;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConverterTest {

    @DisplayName("입력된 날짜를 숫자로 변환한다")
    @Test
    void convertDateNumeric() {
        String input = "15";
        int result = Converter.convertDateNumeric(input);

        assertThat(result).isEqualTo(15);
    }

    @Test
    void separateOrder() {
    }

    @Test
    void separateNameAndAmount() {
    }

    @Test
    void convertAmountNumeric() {
    }
}