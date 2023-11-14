package christmas;

import christmas.programstages.EventDecision;
import christmas.programstages.Result;
import christmas.ui.InputView;
import christmas.ui.OutputView;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        Date date = InputView.makeValidatedDate();
        EventDecision eventDecision = InputView.makeEventDecisionWithValidatedOrders(date);
        int totalOrderPrice = eventDecision.calculateTotalOrderPrice();
        Map<BenefitTitle, Integer> allBenefit = eventDecision.takeAllBenefit(totalOrderPrice);
        Result result = new Result(allBenefit);
        int totalBenefitPrice = result.calculateTotalBenefitPrice();
        int estimatedPayment = result.calculateEstimatedPayment();
        OutputView.printAllResult(eventDecision.getOrders(), totalOrderPrice, allBenefit, totalBenefitPrice, estimatedPayment);
    }
}