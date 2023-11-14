package christmas.programstages;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.Date;
import christmas.EventManager;
import christmas.OrderedMenu;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventManagerTest {
    EventManager eventManager;

    @BeforeEach
    void setUP() {
        List<OrderedMenu> orderedMenus = new ArrayList<>();
        orderedMenus.add(new OrderedMenu("해산물파스타", 2));
        orderedMenus.add(new OrderedMenu("레드와인", 1));
        orderedMenus.add(new OrderedMenu("초코케이크", 1));

        eventManager = new EventManager(new Date(26), orderedMenus);
    }
    @Test
    void calculateTotalOrderPrice() {
        int result = eventManager.calculateTotalOrderPrice();

        assertThat(result).isEqualTo(145000);
    }

    /*@Test
    void takeGift() {
        Benefit result = eventDecisionStage.takeGift(eventDecisionStage.calculateTotalOrderPrice());

        assertThat(result.getName()).isEqualTo("증정 이벤트");
    }*/

    /*@Test
    void takeDDayDiscount() {
        Benefit result = eventDecisionStage.takeDDayDiscount();

        assertThat(result.getAmount()).isEqualTo(3400);
    }*/

    /*@Test
    void takeWeekdayDiscount() {
        Benefit result = eventDecisionStage.takeWeekdayDiscount();

        assertThat(result.getAmount()).isEqualTo(2023);
    }

    @Test
    void takeWeekendDiscount() {
        Benefit result = eventDecisionStage.takeWeekendDiscount();

        assertThat(result.getAmount()).isEqualTo(0);
    }

    @Test
    void takeSpecialDiscount() {
        Benefit result = eventDecisionStage.takeSpecialDiscount();

        assertThat(result.getAmount()).isEqualTo(0);
    }*/
}