package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTypeTest {
    @DisplayName("주어진 메뉴를 토대로 메뉴타입을 판단할 수 있다.")
    @Test
    void decideMenu() {
        Menu inputTapas = Menu.TAPAS;
        Menu inputTBoneStake = Menu.T_BONE_STAKE;
        Menu inputZeroCoke = Menu.ZERO_COKE;
        MenuType inputAppetizerResult = MenuType.decideMenuType(inputTapas);
        MenuType inputMainResult = MenuType.decideMenuType(inputTBoneStake);
        MenuType inputDessertResult = MenuType.decideMenuType(inputZeroCoke);

        assertThat(inputAppetizerResult).isEqualTo(MenuType.APPETIZER);
        assertThat(inputMainResult).isEqualTo(MenuType.MAIN);
        assertThat(inputDessertResult).isEqualTo(MenuType.BEVERAGE);
    }
}