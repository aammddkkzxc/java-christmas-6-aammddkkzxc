package christmas.ui;

import christmas.BenefitType;
import christmas.Date;
import christmas.Order;
import christmas.OrderResult;
import christmas.OrderResultType;
import christmas.OrderedMenu;
import christmas.EventResult;
import christmas.menutable.Menu;
import java.util.ArrayList;
import java.util.List;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String RESULT_NOTIFY_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String NONE = "없음";
    private static final String PRICE = "%,d원";
    private static final String QUANTITY = "개";
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";
    private static final String COLON = ":";

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }

    public static void printAllResult(Date date, Order order, EventResult eventResult) {
        System.out.println(String.format(RESULT_NOTIFY_MESSAGE, date.getDayNumber()));
        System.out.println();

        List<OrderResult> orderResults = makeOrderResults(order, eventResult);
        for (OrderResult orderResult : orderResults) {
            System.out.println(orderResult.getOrderResultType().getName());
            System.out.println(orderResult.getResultDetails());
        }
    }

    private static List<OrderResult> makeOrderResults(Order order, EventResult eventResult) {
        List<OrderResult> orderResults = new ArrayList<>();
        orderResults.add(makeOrderStatisticsResult(order));
        orderResults.add(makeTotalOrderPriceResult(order));
        orderResults.add(makeGiftResult(eventResult));
        orderResults.add(makeBenefitStatisticsResult(eventResult));
        orderResults.add(makeTotalBenefitAmountResult(eventResult));
        orderResults.add(makeEstimatedPaymentResult(order, eventResult));
        orderResults.add(makeBadgeResult(eventResult));

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

    private static OrderResult makeGiftResult(EventResult eventResult) {
        if (!eventResult.isReceivedGiftBenefit()) {
            return new OrderResult(OrderResultType.GIFT_MENU, NONE + NEW_LINE);
        }
        return new OrderResult(OrderResultType.GIFT_MENU, Menu.CHAMPAGNE.getName() + SPACE + 1 + QUANTITY + NEW_LINE);
    }

    private static OrderResult makeBenefitStatisticsResult(EventResult eventResult) {
        if (eventResult.isReceivedBenefit().isEmpty()) {
            return new OrderResult(OrderResultType.BENEFIT_STATISTICS, NONE + NEW_LINE);
        }

        List<BenefitType> existingBenefit = eventResult.isReceivedBenefit();
        StringBuilder stringBuilder = new StringBuilder();
        for (BenefitType benefitType : existingBenefit) {
            stringBuilder.append(benefitType.getName()).append(SPACE + COLON + SPACE).append(String.format(PRICE,
                    -eventResult.getAllBenefit().get(benefitType))).append(NEW_LINE);
        }

        return new OrderResult(OrderResultType.BENEFIT_STATISTICS, stringBuilder.toString());
    }

    private static OrderResult makeTotalBenefitAmountResult(EventResult eventResult) {
        return new OrderResult(OrderResultType.TOTAL_BENEFIT_AMOUNT,
                String.format(PRICE, (-eventResult.calculateTotalBenefitAmount())) + NEW_LINE);
    }

    private static OrderResult makeEstimatedPaymentResult(Order order, EventResult eventResult) {
        int totalOrderPrice = order.calculateTotalOrderPrice();
        int estimatedPayment = eventResult.calculateEstimatedPayment();

        return new OrderResult(OrderResultType.ESTIMATED_PAYMENT,
                String.format(PRICE, (totalOrderPrice - estimatedPayment)) + NEW_LINE);
    }

    private static OrderResult makeBadgeResult(EventResult eventResult) {
        return new OrderResult(OrderResultType.EVENT_BADGE, eventResult.decideEventBadge());
    }
}