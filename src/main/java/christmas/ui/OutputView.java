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
    private static final String NONE = "없음";
    private static final String PRICE = "%,d원";
    private static final String QUANTITY = "개";
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";
    private static final String COLON = ":";

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
        orderResults.add(makeBadgeResult(result));

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
                String.format(PRICE, (-result.calculateTotalBenefitAmount())) + NEW_LINE);
    }

    private static OrderResult makeEstimatedPaymentResult(Order order, Result result) {
        int totalOrderPrice = order.calculateTotalOrderPrice();
        int estimatedPayment = result.calculateEstimatedPayment();

        return new OrderResult(OrderResultType.ESTIMATED_PAYMENT,
                String.format(PRICE, (totalOrderPrice - estimatedPayment)) + NEW_LINE);
    }

    private static OrderResult makeBadgeResult(Result result) {
        return new OrderResult(OrderResultType.EVENT_BADGE, result.decideEventBadge());
    }
}