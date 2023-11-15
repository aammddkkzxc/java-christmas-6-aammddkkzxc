package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventResultTest {
    EventResult eventResult;

    @BeforeEach
    void setUP() {
        EventProcess eventProcess = new EventProcess();
        Date date = new Date(3);
        Order order = new Order(setUpOrder());
        eventResult = eventProcess.takeAllBenefit(date, order);
    }

    private List<OrderedMenu> setUpOrder() {
        List<OrderedMenu> orderedMenus = new ArrayList<>();
        orderedMenus.add(new OrderedMenu("티본스테이크", 1));
        orderedMenus.add(new OrderedMenu("바비큐립", 1));
        orderedMenus.add(new OrderedMenu("초코케이크", 2));
        orderedMenus.add(new OrderedMenu("제로콜라", 1));

        return orderedMenus;
    }

    @DisplayName("주어진 날짜와 주문으로 전체 혜택 금액을 계산한다.")
    @Test
    void calculateTotalBenefitAmount() {
        int result = eventResult.calculateTotalBenefitAmount();

        assertThat(result).isEqualTo(31246);
    }

    @Test
    void calculateEstimatedPayment() {
    }

    @Test
    void isReceivedGiftBenefit() {
    }

    @Test
    void isReceivedBenefit() {
    }

    @Test
    void decideEventBadge() {
    }
}