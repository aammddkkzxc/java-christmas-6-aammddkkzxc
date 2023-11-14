package christmas;

import christmas.ui.InputView;
import christmas.ui.OutputView;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        Date date = InputView.inputDate();
        Order order = InputView.inputOrder();
        int totalOrderPrice = order.calculateTotalOrderPrice();
        EventManager eventManager = new EventManager();
        Result result = eventManager.takeAllBenefit(totalOrderPrice, date, order);
        int totalBenefitPrice = result.calculateTotalBenefitPrice();
        int estimatedPayment = result.calculateEstimatedPayment();

        OutputView.printAllResult(eventManager.getOrders(), totalOrderPrice, allBenefit, totalBenefitPrice,
                estimatedPayment);
    }
}