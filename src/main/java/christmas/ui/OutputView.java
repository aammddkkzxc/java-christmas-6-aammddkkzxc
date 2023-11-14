package christmas.ui;

import christmas.BenefitTitle;
import christmas.Order;
import christmas.menutable.Menu;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String RESULT_NOTIFY_MESSAGE = "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }

    public static void printAllResult(List<Order> orders, int totalOrderPrice, Map<BenefitTitle, Integer> allBenefit,
                                      int totalBenefitPrice, int estimatedPayment) {
        System.out.println(RESULT_NOTIFY_MESSAGE);
        System.out.println();

        printOrders(orders);
        printTotalOrderPrice(totalOrderPrice);
        printGift(totalOrderPrice, allBenefit);
        printBenefitStatistics(totalOrderPrice, allBenefit);
        printTotalBenefitPrice(totalOrderPrice, totalBenefitPrice);
        printEstimatedPayment(totalOrderPrice, estimatedPayment);
        printBadge(totalOrderPrice, totalBenefitPrice);
    }

    private static void printOrders(List<Order> orders) {
        System.out.println("<주문 메뉴>");
        for (Order order : orders) {
            System.out.println(order.getName() + " " + order.getAmount() + "개");
        }
        System.out.println();
    }

    private static void printTotalOrderPrice(int totalOrderPrice) {
        System.out.println("<할인 전 총주문 금액>" + "\n" + totalOrderPrice + "원");
        System.out.println();
    }

    private static void printGift(int totalOrderPrice, Map<BenefitTitle, Integer> allBenefit) {
        if (totalOrderPrice < 10000 || allBenefit.get(BenefitTitle.GIFT) == 0) {
            System.out.println("<증정 메뉴>" + "\n" + "없음");
        }
        if (allBenefit.get(BenefitTitle.GIFT) == Menu.CHAMPAGNE.getPrice()) {
            System.out.println("<증정 메뉴>" + "\n" + "샴페인 1개");
        }
        System.out.println();
    }

    private static void printBenefitStatistics(int totalOrderPrice, Map<BenefitTitle, Integer> allBenefit) {
        System.out.println("<혜택 내역>");
        if (totalOrderPrice < 10000 || allBenefit.isEmpty()) {
            System.out.println("없음");
        }
        List<BenefitTitle> benefitTitles = BenefitTitle.findExistingBenefit(allBenefit);

        for (BenefitTitle benefitTitle : benefitTitles) {
            if (totalOrderPrice >= 10000 && allBenefit.get(benefitTitle) != 0) {
                System.out.println(benefitTitle.getName() + " : " + (-allBenefit.get(benefitTitle)) + "원");
            }
        }

        System.out.println();
    }

    private static void printTotalBenefitPrice(int totalOrderPrice, int totalBenefitPrice) {
        if (totalOrderPrice < 10000) {
            System.out.println("<총혜택 금액>" + "\n" + 0 + "원");
        }
        if (totalOrderPrice >= 10000) {
            System.out.println("<총혜택 금액>" + "\n" + (-totalBenefitPrice) + "원");
        }
        System.out.println();
    }

    private static void printEstimatedPayment(int totalOrderPrice, int estimatedPayment) {
        if (totalOrderPrice < 10000) {
            System.out.println("<할인 후 예상 결제 금액>" + "\n" + totalOrderPrice + "원");
        }
        if (totalOrderPrice >= 10000) {
            System.out.println("<할인 후 예상 결제 금액>" + "\n" + (totalOrderPrice - estimatedPayment) + "원");
        }
        System.out.println();
    }

    private static void printBadge(int totalOrderPrice, int totalBenefitPrice) {
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