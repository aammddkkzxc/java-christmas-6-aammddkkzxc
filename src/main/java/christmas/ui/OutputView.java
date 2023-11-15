package christmas.ui;

import christmas.BenefitType;
import christmas.Order;
import christmas.OrderResult;
import christmas.OrderResultType;
import christmas.OrderedMenu;
import christmas.Result;
import christmas.menutable.Menu;
import java.util.ArrayList;
import java.util.List;

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

    public static void printAllResult(Order order, Result result) {
        System.out.println(RESULT_NOTIFY_MESSAGE);
        System.out.println();

        List<OrderResult> orderResults = makeOrderResults(order, result);
        for (OrderResult orderResult : orderResults) {
            System.out.println(orderResult.getOrderResultType().getName());
            System.out.println(orderResult.getResultDetails());
        }
    }

    private static List<OrderResult> makeOrderResults(Order order, Result result) {
        List<OrderResult> orderResults = new ArrayList<>();
        orderResults.add(makeOrderStatisticsResult(order));
        orderResults.add(makeTotalOrderPriceResult(order));
        orderResults.add(makeGiftResult(result));
        orderResults.add(makeBenefitStatisticsResult(result));
        orderResults.add(makeTotalBenefitAmountResult(result));
        orderResults.add(makeEstimatedPaymentResult(order, result));

        return orderResults;
    }

    private static OrderResult makeOrderStatisticsResult(Order order) {
        StringBuilder stringBuilder = new StringBuilder();
        for (OrderedMenu orderedMenu : order.getOrder()) {
            stringBuilder.append(orderedMenu.getMenuName()).append(SPACE).append(orderedMenu.getQuantity())
                    .append(QUANTITY).append(NEW_LINE);
        }
        return new OrderResult(OrderResultType.ORDER_MENU, stringBuilder.toString());
    }

    private static OrderResult makeTotalOrderPriceResult(Order order) {
        return new OrderResult(OrderResultType.TOTAL_ORDER_PRICE,
                String.format(PRICE, order.calculateTotalOrderPrice()) + NEW_LINE);
    }

    private static OrderResult makeGiftResult(Result result) {
        if (!result.isReceivedGiftBenefit()) {
            return new OrderResult(OrderResultType.GIFT_MENU, NONE + NEW_LINE);
        }
        return new OrderResult(OrderResultType.GIFT_MENU, Menu.CHAMPAGNE.getName() + SPACE + 1 + QUANTITY + NEW_LINE);
    }

    private static OrderResult makeBenefitStatisticsResult(Result result) {
        if (result.isReceivedBenefit().isEmpty()) {
            return new OrderResult(OrderResultType.BENEFIT_STATISTICS, NONE + NEW_LINE);
        }

        List<BenefitType> existingBenefit = result.isReceivedBenefit();
        StringBuilder stringBuilder = new StringBuilder();
        for (BenefitType benefitType : existingBenefit) {
            stringBuilder.append(benefitType.getName()).append(SPACE + COLON + SPACE).append(String.format(PRICE,
                    -result.getAllBenefit().get(benefitType))).append(NEW_LINE);
        }

        return new OrderResult(OrderResultType.BENEFIT_STATISTICS, stringBuilder.toString());
    }

    private static OrderResult makeTotalBenefitAmountResult(Result result) {
        return new OrderResult(OrderResultType.TOTAL_BENEFIT_AMOUNT,
                String.format(PRICE, (-result.calculateTotalBenefitPrice())) + NEW_LINE);
    }

    private static OrderResult makeEstimatedPaymentResult(Order order, Result result) {
        int totalOrderPrice = order.calculateTotalOrderPrice();
        int estimatedPayment = result.calculateEstimatedPayment();

        return new OrderResult(OrderResultType.ESTIMATED_PAYMENT,
                String.format(PRICE, (totalOrderPrice - estimatedPayment)));
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