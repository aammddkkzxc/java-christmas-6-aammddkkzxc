package christmas;

import christmas.programstages.EventDecisionStage;
import christmas.programstages.ReservationStage;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        ReservationStage reservationStage = new ReservationStage();
        Date date = reservationStage.runMakeDateStage();
        List<Order> orders = reservationStage.runMakeOrdersStage();
        EventDecisionStage eventDecisionStage = new EventDecisionStage(date, orders);
        System.out.println(eventDecisionStage.calculateTotalOrderPrice());
    }
}