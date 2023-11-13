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
        if (totalOrderPrice < 10000 || allBenefit.get(BenefitTitle.GIFT) == 0) {
            System.out.println("<증정 메뉴>" + "\n" + "없음");
        }
        if (allBenefit.get(BenefitTitle.GIFT) == Menu.CHAMPAGNE.getPrice()) {
            System.out.println("<증정 메뉴>" + "\n" + "샴페인 1개");
        }
        System.out.println();
    }

    public static void printBenefitStatistics(int totalOrderPrice, Map<BenefitTitle, Integer> allBenefit) {
        System.out.println("<혜택 내역>");
        if (totalOrderPrice < 10000 || allBenefit.isEmpty()) {
            System.out.println("없음");
        }
        BenefitTitle[] benefitTitles = BenefitTitle.values();

        for (BenefitTitle benefitTitle : benefitTitles) {
            if (totalOrderPrice >= 10000 && allBenefit.get(benefitTitle) != 0) {
                System.out.println(benefitTitle + " : " + (-allBenefit.get(benefitTitle)) + "원");
            }
        }

        System.out.println();
    }

    public static void printTotalBenefitPrice(int totalOrderPrice, int totalBenefitPrice) {
        if (totalOrderPrice < 10000) {
            System.out.println("<총혜택 금액>" + "\n" + 0 + "원");
        }
        if (totalOrderPrice >= 10000) {
            System.out.println("<총혜택 금액>" + "\n" + (-totalBenefitPrice) + "원");
        }
        System.out.println();
    }

    public static void printEstimatedPayment(int totalOrderPrice, int estimatedPayment) {
        if (totalOrderPrice < 10000) {
            System.out.println("<할인 후 예상 결제 금액>" + "\n" + totalOrderPrice + "원");
        }
        if (totalOrderPrice >= 10000) {
            System.out.println("<할인 후 예상 결제 금액>" + "\n" + (totalOrderPrice - estimatedPayment) + "원");
        }
        System.out.println();
    }

    public static void printBadge(int totalOrderPrice, int totalBenefitPrice) {
        if (totalOrderPrice < 10000 || totalBenefitPrice < 5000) {
            System.out.println("<12월 이벤트 배지>" + "\n" + "없음");
        }
        if (totalBenefitPrice >= 5000 && totalBenefitPrice < 10000) {
            System.out.println("<12월 이벤트 배지>" + "\n" + "별");
        }
        if (totalBenefitPrice >= 10000 && totalBenefitPrice < 20000) {
            System.out.println("<12월 이벤트 배지>" + "\n" + "트리");
        }
        if (totalBenefitPrice >= 20000) {
            System.out.println("<12월 이벤트 배지>" + "\n" + "산타");
        }
    }
}