package christmas;

import christmas.programstages.EventDecision;
import christmas.programstages.Result;
import christmas.ui.InputView;
import christmas.ui.OutputView;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        Date date = InputView.makeValidatedDate();
        List<Order> orders = InputView.makeValidatedOrders();
        EventDecision eventDecision = new EventDecision(date, orders);
        int totalOrderPrice = eventDecision.calculateTotalOrderPrice();
        Map<BenefitTitle, Integer> allBenefit = eventDecision.takeAllBenefit(totalOrderPrice);
        Result result = new Result(allBenefit);
        int totalBenefitPrice = result.calculateTotalBenefitPrice();
        int estimatedPayment = result.calculateEstimatedPayment();
        OutputView.printAllResult(totalOrderPrice, allBenefit, totalBenefitPrice, estimatedPayment);
    }
}