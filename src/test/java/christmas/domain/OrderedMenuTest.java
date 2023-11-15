package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderedMenuTest {
    @DisplayName("메뉴에 존재하지 않는 주문메뉴이면 예외가 발생한다.")
    @Test
    void createOrderedMenuWithNonExistingMenu() {
        assertThatThrownBy(() -> new OrderedMenu("피자", 3))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void isBeverage() {
    }

    @Test
    void calculatePrice() {
    }
}