package christmas;

import christmas.programstages.EventDecisionStage;
import christmas.programstages.ReservationStage;
import christmas.programstages.ResultStage;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        ReservationStage reservationStage = new ReservationStage();
        Date date = reservationStage.runMakeDateStage();
        List<Order> orders = reservationStage.runMakeOrdersStage();
        EventDecisionStage eventDecisionStage = new EventDecisionStage(date, orders);
        int totalOrderPrice = eventDecisionStage.calculateTotalOrderPrice();
        List<Benefit> benefits = eventDecisionStage.takeAllBenefit(totalOrderPrice);
        ResultStage resultStage = new ResultStage(totalOrderPrice, benefits);
    }
}