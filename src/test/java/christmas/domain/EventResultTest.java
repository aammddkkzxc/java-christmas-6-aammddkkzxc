package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
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

    @DisplayName("주어진 날짜와 주문으로 전체 할인 금액을 계산한다.")
    @Test
    void calculateTotalDiscountAmount() {
        int result = eventResult.calculateTotalDiscountAmount();

        assertThat(result).isEqualTo(6246);
    }

    @DisplayName("증정 메뉴 혜택을 받는지 알 수 있다.")
    @Test
    void isReceivedGiftBenefit() {
        boolean result = eventResult.isReceivedGiftBenefit();

        assertThat(result).isEqualTo(true);
    }

    @DisplayName("제공 받는 혜택에 어떠한 혜택들이 존재 하는지 알 수 있다.")
    @Test
    void checkWhichBenefitExist() {
        List<BenefitType> result = eventResult.checkWhichBenefitExist();

        assertThat(result).isEqualTo(
                List.of(BenefitType.GIFT, BenefitType.D_DAY, BenefitType.WEEKDAY, BenefitType.SPECIAL));
    }

    @DisplayName("어떠한 이벤트 배지 혜택을 받는지 알 수 있다.")
    @Test
    void decideEventBadge() {
        String result = eventResult.decideEventBadge();

        assertThat(result).isEqualTo("산타");
    }
}