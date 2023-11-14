package christmas;

import christmas.programstages.EventDecisionStage;
import christmas.programstages.ReservationStage;
import christmas.programstages.ResultStage;
import christmas.ui.InputView;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Date date = InputView.makeValidatedDate();
        List<Order> orders = reservationStage.runMakeOrdersStage();
        EventDecisionStage eventDecisionStage = new EventDecisionStage(date, orders);
        int totalOrderPrice = eventDecisionStage.calculateTotalOrderPrice();
        Map<BenefitTitle, Integer> allBenefit = eventDecisionStage.takeAllBenefit(totalOrderPrice);
        ResultStage resultStage = new ResultStage(totalOrderPrice, allBenefit);
        resultStage.run();
    }
}