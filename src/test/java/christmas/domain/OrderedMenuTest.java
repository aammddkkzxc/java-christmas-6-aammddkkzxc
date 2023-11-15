package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderedMenuTest {
    @DisplayName("메뉴에 존재하지 않는 주문메뉴이면 예외가 발생한다.")
    @ValueSource(strings = {"피자", "치킨", "햄버거"})
    @ParameterizedTest
    void createOrderedMenuWithNonExistingMenu(String input) {
        assertThatThrownBy(() -> new OrderedMenu(input, 3))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 메뉴의 최소수량 미만의 수량이면 예외가 발생한다.")
    @ValueSource(strings = {"-3", "-1", "0"})
    @ParameterizedTest
    void createOrderedMenuWithUnderQuantityLimit(Integer input) {
        assertThatThrownBy(() -> new OrderedMenu("피자", input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 메뉴의 메뉴 타입이 음료에 해당하는지 알 수 있다.")
    @Test
    void isBeverage() {
        OrderedMenu input = new OrderedMenu("제로콜라", 3);
        boolean result = input.isBeverage();

        assertThat(result).isEqualTo(true);
    }

    @Test
    void calculatePrice() {
    }
}