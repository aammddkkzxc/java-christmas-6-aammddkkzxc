package christmas.ui;

import christmas.BenefitTitle;
import christmas.menutable.Menu;
import java.util.Map;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }

    public static void printTotalOrderPrice(int totalOrderPrice) {
        System.out.println("<할인 전 총주문 금액>" + "\n" + totalOrderPrice + "원");
        System.out.println();
    }

    public static void printGift(int totalOrderPrice, Map<BenefitTitle, Integer> allBenefit) {
        if (totalOrderPrice < 10000 || allBenefit.containsValue(0)) {
            System.out.println("<증정 메뉴>" + "\n" + "없음");
        }
        if (allBenefit.containsValue(Menu.CHAMPAGNE.getPrice())) {
            System.out.println("<증정 메뉴>" + "\n" + "샴페인 1개");
        }
        System.out.println();
    }
}
