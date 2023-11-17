package christmas.ui;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @DisplayName("주문메뉴를 이름과 수량으로 나눈다.")
    @Test
    void separateNameAndAmount() {
        String input = "타파스-1";
        List<String> result = Converter.separateNameAndAmount(input);

        assertThat(result).isEqualTo(List.of("타파스", "1"));
    }

    @DisplayName("이름과 수량 구분자가 존재하지 않을 경우 예외가 발생한다")
    @Test
    void trySeparateNameAndAmountWithoutDelimiter() {
        String input = "타파스1";

        assertThatThrownBy(() -> Converter.separateNameAndAmount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 메뉴를 구분자로 구분한 값에 형식에 맞지 않는 값이 있을 경우 예외가 발생한다.")
    @Test
    void checkSeparatedNameAndAmountHasRightValue() {
        String input = "타파스-1-맥주";

        assertThatThrownBy(() -> Converter.separateNameAndAmount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수량을 숫자로 변환한다.")
    @Test
    void convertAmountNumeric() {
        String input = "1";
        int result = Converter.convertAmountNumeric(input);

        assertThat(result).isEqualTo(1);
    }
}