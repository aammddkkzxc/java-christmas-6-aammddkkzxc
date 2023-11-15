package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

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


    @Test
    void isBeverage() {
    }

    @Test
    void calculatePrice() {
    }
}