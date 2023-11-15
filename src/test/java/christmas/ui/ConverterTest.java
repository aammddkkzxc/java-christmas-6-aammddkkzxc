package christmas.ui;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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

    @DisplayName("입력된 주문을 주문메뉴 별로 나눈다.")
    @Test
    void separateOrder() {
        String input = "타파스-1,제로콜라-1";
        List<String> result = Converter.separateOrder(input);

        assertThat(result).isEqualTo(List.of("타파스-1", "제로콜라-1"));
    }

    @Test
    void separateNameAndAmount() {
    }

    @Test
    void convertAmountNumeric() {
    }
}