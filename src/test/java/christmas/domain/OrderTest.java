package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {
    @DisplayName("주문 메뉴들이 전부 음료에 해당하면 예외가 발생한다.")
    @Test
    void createOrderByAllBeverage() {
        List<OrderedMenu> orderedMenus = new ArrayList<>();
        orderedMenus.add(new OrderedMenu("제로콜라", 1));
        orderedMenus.add(new OrderedMenu("레드와인", 1));
        orderedMenus.add(new OrderedMenu("샴페인", 2));

        assertThatThrownBy(() -> new Order(orderedMenus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 메뉴들에 중복된 메뉴가 존재하면 예외가 발생한다.")
    @Test
    void createOrderByDuplicatingMenu() {
        List<OrderedMenu> orderedMenus = new ArrayList<>();
        orderedMenus.add(new OrderedMenu("양송이수프", 2));
        orderedMenus.add(new OrderedMenu("레드와인", 1));
        orderedMenus.add(new OrderedMenu("양송이수프", 3));

        assertThatThrownBy(() -> new Order(orderedMenus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("총 주문 메뉴들의 개수가 20개를 초과하면 예외가 발생한다.")
    @Test
    void createOrderByOverQuantityLimit() {
        List<OrderedMenu> orderedMenus = new ArrayList<>();
        orderedMenus.add(new OrderedMenu("타파스", 3));
        orderedMenus.add(new OrderedMenu("양송이수프", 2));
        orderedMenus.add(new OrderedMenu("레드와인", 3));
        orderedMenus.add(new OrderedMenu("제로콜라", 5));
        orderedMenus.add(new OrderedMenu("바비큐립", 4));
        orderedMenus.add(new OrderedMenu("아이스크림", 4));

        assertThatThrownBy(() -> new Order(orderedMenus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("총 주문 금액을 계산할 수 있다.")
    @Test
    void calculateTotalOrderPrice() {
        List<OrderedMenu> orderedMenus = new ArrayList<>();
        orderedMenus.add(new OrderedMenu("타파스", 3));
        orderedMenus.add(new OrderedMenu("양송이수프", 2));
        orderedMenus.add(new OrderedMenu("레드와인", 3));
        orderedMenus.add(new OrderedMenu("제로콜라", 5));
        Order order = new Order(orderedMenus);
        int result = order.calculateTotalOrderPrice();

        assertThat(result).isEqualTo(223500);
    }
}