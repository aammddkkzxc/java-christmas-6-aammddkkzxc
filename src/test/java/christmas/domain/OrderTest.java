package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {
    @DisplayName("주문 메뉴들이 전부 음료에 해당하면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        List<OrderedMenu> orderedMenus = new ArrayList<>();
        orderedMenus.add(new OrderedMenu("제로콜라", 1));
        orderedMenus.add(new OrderedMenu("레드와인", 1));
        orderedMenus.add(new OrderedMenu("샴페인", 2));

        assertThatThrownBy(() -> new Order(orderedMenus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void calculateTotalOrderPrice() {
    }
}