package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {
    @DisplayName("주어진 이름을 토대로 메뉴를 판단할 수 있다.")
    @Test
    void decideMenu() {
        String inputTapas = "타파스";
        String inputSeafoodPasta = "해산물파스타";
        String inputRedWine = "레드와인";
        Menu inputTapasResult = Menu.decideMenu(inputTapas);
        Menu inputSeafoodPastaResult = Menu.decideMenu(inputSeafoodPasta);
        Menu inputRedWineResult = Menu.decideMenu(inputRedWine);

        assertThat(inputTapasResult).isEqualTo(Menu.TAPAS);
        assertThat(inputSeafoodPastaResult).isEqualTo(Menu.SEAFOOD_PASTA);
        assertThat(inputRedWineResult).isEqualTo(Menu.RED_WINE);
    }
}