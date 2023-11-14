package christmas.ui;

import christmas.BenefitTitle;
import christmas.OrderedMenu;
import christmas.menutable.Menu;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String RESULT_NOTIFY_MESSAGE = "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_HEADER = "<주문 메뉴>";
    private static final String TOTAL_ORDER_PRICE_HEADER = "<할인 전 총주문 금액>";
    private static final String GIFT_MENU_HEADER = "<증정 메뉴>";
    private static final String BENEFIT_STATISTICS_HEADER = "<혜택 내역>";
    private static final String TOTAL_BENEFIT_PRICE_HEADER = "<총혜택 금액>";
    private static final String ESTIMATED_PAYMENT_HEADER = "<할인 후 예상 결제 금액>";
    private static final String BADGE_HEADER = "<12월 이벤트 배지>";
    private static final String STAR_BADGE = "별";
    private static final String TREE_BADGE = "트리";
    private static final String SANTA_BADGE = "산타";
    private static final String NONE = "없음";
    private static final String PRICE = "%,d원";
    private static final String QUANTITY = "개";
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";
    private static final String COLON = ":";
    private static final int EVENT_MINIMUM_PRICE = 10000;

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }

    public static void printAllResult(List<OrderedMenu> orderedMenus, int totalOrderPrice, Map<BenefitTitle, Integer> allBenefit,
                                      int totalBenefitPrice, int estimatedPayment) {
        System.out.println(RESULT_NOTIFY_MESSAGE);
        System.out.println();

        printOrders(orderedMenus);
        printTotalOrderPrice(totalOrderPrice);
        printGift(totalOrderPrice, allBenefit);
        printBenefitStatistics(totalOrderPrice, allBenefit);
        printTotalBenefitPrice(totalOrderPrice, totalBenefitPrice);
        printEstimatedPayment(totalOrderPrice, estimatedPayment);
        printBadge(totalOrderPrice, totalBenefitPrice);
    }

    private static void printOrders(List<OrderedMenu> orderedMenus) {
        System.out.println(ORDER_MENU_HEADER);
        for (OrderedMenu orderedMenu : orderedMenus) {
            System.out.println(orderedMenu.getMenuName() + SPACE + orderedMenu.getQuantity() + QUANTITY);
        }
        System.out.println();
    }

    private static void printTotalOrderPrice(int totalOrderPrice) {
        System.out.println(TOTAL_ORDER_PRICE_HEADER + NEW_LINE + String.format(PRICE, totalOrderPrice));
        System.out.println();
    }

    private static void printGift(int totalOrderPrice, Map<BenefitTitle, Integer> allBenefit) {
        if (totalOrderPrice < EVENT_MINIMUM_PRICE || allBenefit.get(BenefitTitle.GIFT) == 0) {
            System.out.println(GIFT_MENU_HEADER + NEW_LINE + NONE);
        }
        if (allBenefit.get(BenefitTitle.GIFT) == Menu.CHAMPAGNE.getPrice()) {
            System.out.println(GIFT_MENU_HEADER + NEW_LINE + Menu.CHAMPAGNE.getName() + 1 + QUANTITY);
        }
        System.out.println();
    }

    private static void printBenefitStatistics(int totalOrderPrice, Map<BenefitTitle, Integer> allBenefit) {
        System.out.println(BENEFIT_STATISTICS_HEADER);

        if (totalOrderPrice < EVENT_MINIMUM_PRICE || allBenefit.isEmpty()) {
            System.out.println(NONE);
        }

        List<BenefitTitle> benefitTitles = BenefitTitle.findExistingBenefit(allBenefit);
        for (BenefitTitle benefitTitle : benefitTitles) {
            if (totalOrderPrice >= EVENT_MINIMUM_PRICE && allBenefit.get(benefitTitle) != 0) {
                System.out.println(benefitTitle.getTitle() + SPACE + COLON + SPACE + String.format(PRICE,
                        -allBenefit.get(benefitTitle)));
            }
        }

        System.out.println();
    }

    private static void printTotalBenefitPrice(int totalOrderPrice, int totalBenefitPrice) {
        if (totalOrderPrice < EVENT_MINIMUM_PRICE) {
            System.out.println(TOTAL_BENEFIT_PRICE_HEADER + NEW_LINE + String.format(PRICE, 0));
        }
        if (totalOrderPrice >= EVENT_MINIMUM_PRICE) {
            System.out.println(TOTAL_BENEFIT_PRICE_HEADER + NEW_LINE + String.format(PRICE, (-totalBenefitPrice)));
        }
        System.out.println();
    }

    private static void printEstimatedPayment(int totalOrderPrice, int estimatedPayment) {
        if (totalOrderPrice < EVENT_MINIMUM_PRICE) {
            System.out.println(ESTIMATED_PAYMENT_HEADER + NEW_LINE + String.format(PRICE, totalOrderPrice));
        }
        if (totalOrderPrice >= EVENT_MINIMUM_PRICE) {
            System.out.println(
                    ESTIMATED_PAYMENT_HEADER + NEW_LINE + String.format(PRICE, (totalOrderPrice - estimatedPayment)));
        }
        System.out.println();
    }

    private static void printBadge(int totalOrderPrice, int totalBenefitPrice) {
        if (totalOrderPrice < EVENT_MINIMUM_PRICE || totalBenefitPrice < 5000) {
            System.out.println(BADGE_HEADER + NEW_LINE + NONE);
        }
        if (totalBenefitPrice >= 5000 && totalBenefitPrice < 10000) {
            System.out.println(BADGE_HEADER + NEW_LINE + STAR_BADGE);
        }
        if (totalBenefitPrice >= 10000 && totalBenefitPrice < 20000) {
            System.out.println(BADGE_HEADER + NEW_LINE + TREE_BADGE);
        }
        if (totalBenefitPrice >= 20000) {
            System.out.println(BADGE_HEADER + NEW_LINE + SANTA_BADGE);
        }
    }
}