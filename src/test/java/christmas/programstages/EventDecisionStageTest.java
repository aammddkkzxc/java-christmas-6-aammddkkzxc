package christmas.programstages;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.Benefit;
import christmas.Date;
import christmas.Order;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventDecisionStageTest {
    EventDecisionStage eventDecisionStage;

    @BeforeEach
    void setUP() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("해산물파스타", 2));
        orders.add(new Order("레드와인", 1));
        orders.add(new Order("초코케이크", 1));

        eventDecisionStage = new EventDecisionStage(new Date(20), orders);
    }
    @Test
    void calculateTotalOrderPrice() {
        int result = eventDecisionStage.calculateTotalOrderPrice();

        assertThat(result).isEqualTo(145000);
    }

    @Test
    void takeGift() {
    }

    @Test
    void takeDDayDiscount() {
    }

    @Test
    void takeWeekdayDiscount() {
    }

    @Test
    void takeWeekendDiscount() {
    }

    @Test
    void takeSpecialDiscount() {
    }
}