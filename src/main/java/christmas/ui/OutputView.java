package christmas.ui;

import christmas.domain.BenefitType;
import christmas.domain.Date;
import christmas.domain.EventResult;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.OrderResult;
import christmas.domain.OrderResultType;
import christmas.domain.OrderedMenu;
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
    private static final int GIFT_QUANTITY = 1;

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }

    public static void printTotalOrderResults(Date date, Order order, EventResult eventResult) {
        System.out.println(String.format(RESULT_NOTIFY_MESSAGE, date.getDayNumber()));
        System.out.println();

        List<OrderResult> orderResults = makeTotalOrderResults(order, eventResult);
        for (OrderResult orderResult : orderResults) {
            System.out.println(orderResult.getOrderResultType().getName());
            System.out.println(orderResult.getResultDetails());
        }
    }

    private static List<OrderResult> makeTotalOrderResults(Order order, EventResult eventResult) {
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
        StringBuilder orderDetails = new StringBuilder();
        for (OrderedMenu orderedMenu : order.getOrder()) {
            orderDetails.append(orderedMenu.getMenuName()).append(SPACE).append(orderedMenu.getQuantity())
                    .append(QUANTITY).append(NEW_LINE);
        }
        return new OrderResult(OrderResultType.ORDER_MENU, orderDetails.toString());
    }

    private static OrderResult makeTotalOrderPriceResult(Order order) {
        StringBuilder totalOrderPriceDetails = new StringBuilder();
        totalOrderPriceDetails.append(String.format(PRICE, order.calculateTotalOrderPrice())).append(NEW_LINE);

        return new OrderResult(OrderResultType.TOTAL_ORDER_PRICE, totalOrderPriceDetails.toString());
    }

    private static OrderResult makeGiftResult(EventResult eventResult) {
        StringBuilder giftDetails = new StringBuilder();

        if (!eventResult.isReceivedGiftBenefit()) {
            giftDetails.append(NONE).append(NEW_LINE);
            return new OrderResult(OrderResultType.GIFT_MENU, giftDetails.toString());
        }
        giftDetails.append(Menu.CHAMPAGNE.getName()).append(SPACE).append(GIFT_QUANTITY).append(QUANTITY)
                .append(NEW_LINE);
        return new OrderResult(OrderResultType.GIFT_MENU, giftDetails.toString());
    }

    private static OrderResult makeBenefitStatisticsResult(EventResult eventResult) {
        StringBuilder benefitDetails = new StringBuilder();
        List<BenefitType> existingBenefit = eventResult.checkWhichBenefitExist();

        if (eventResult.checkWhichBenefitExist().isEmpty()) {
            benefitDetails.append(NONE).append(NEW_LINE);
            return new OrderResult(OrderResultType.BENEFIT_STATISTICS, benefitDetails.toString());
        }

        for (BenefitType benefitType : existingBenefit) {
            benefitDetails.append(benefitType.getName()).append(SPACE + COLON + SPACE).append(String.format(PRICE,
                    -eventResult.getAllBenefit().get(benefitType))).append(NEW_LINE);
        }
        return new OrderResult(OrderResultType.BENEFIT_STATISTICS, benefitDetails.toString());
    }

    private static OrderResult makeTotalBenefitAmountResult(EventResult eventResult) {
        StringBuilder totalBenefitAmountDetails = new StringBuilder();
        totalBenefitAmountDetails.append(String.format(PRICE, (-eventResult.calculateTotalBenefitAmount())))
                .append(NEW_LINE);

        return new OrderResult(OrderResultType.TOTAL_BENEFIT_AMOUNT, totalBenefitAmountDetails.toString());
    }

    private static OrderResult makeEstimatedPaymentResult(Order order, EventResult eventResult) {
        int totalOrderPrice = order.calculateTotalOrderPrice();
        int totalDiscountAmount = eventResult.calculateTotalDiscountAmount();

        return new OrderResult(OrderResultType.ESTIMATED_PAYMENT,
                String.format(PRICE, (totalOrderPrice - totalDiscountAmount)) + NEW_LINE);
    }

    private static OrderResult makeBadgeResult(EventResult eventResult) {
        return new OrderResult(OrderResultType.EVENT_BADGE, eventResult.decideEventBadge());
    }
}