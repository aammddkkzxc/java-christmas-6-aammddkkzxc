package christmas;

import christmas.ui.InputView;
import christmas.ui.OutputView;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        Date date = InputView.inputDate();
        EventManager eventManager = InputView.inputOrder(date);
        int totalOrderPrice = eventManager.calculateTotalOrderPrice();
        Map<BenefitTitle, Integer> allBenefit = eventManager.takeAllBenefit(totalOrderPrice);
        Result result = new Result(allBenefit);
        int totalBenefitPrice = result.calculateTotalBenefitPrice();
        int estimatedPayment = result.calculateEstimatedPayment();

        OutputView.printAllResult(eventManager.getOrders(), totalOrderPrice, allBenefit, totalBenefitPrice,
                estimatedPayment);
    }
}