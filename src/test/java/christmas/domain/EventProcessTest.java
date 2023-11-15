package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

class EventProcessTest {

    @DisplayName("이벤트 받는 금액에 충분한 전체 주문 금액으로 전체 혜택 계산")
    @Test
    void takeAllBenefitWithSufficientTotalOrderPrice() {
        Date dateInput = new Date(3);
        Order orderInput = new Order(setUpSufficientTotalOrderPriceOrder());
        EventProcess eventProcess = new EventProcess();
        EventResult eventResult = eventProcess.takeAllBenefit(dateInput, orderInput);
        Map<BenefitType, Integer> result = eventResult.getAllBenefit();

        assertThat(result.get(BenefitType.GIFT)).isEqualTo(25000);
        assertThat(result.get(BenefitType.D_DAY)).isEqualTo(1200);
        assertThat(result.get(BenefitType.WEEKDAY)).isEqualTo(2023);
        assertThat(result.get(BenefitType.WEEKEND)).isEqualTo(0);
        assertThat(result.get(BenefitType.SPECIAL)).isEqualTo(1000);
    }

    private List<OrderedMenu> setUpSufficientTotalOrderPriceOrder() {
        List<OrderedMenu> orderedMenus = new ArrayList<>();
        orderedMenus.add(new OrderedMenu("해산물파스타", 2));
        orderedMenus.add(new OrderedMenu("레드와인", 1));
        orderedMenus.add(new OrderedMenu("초코케이크", 1));

        return orderedMenus;
    }
}