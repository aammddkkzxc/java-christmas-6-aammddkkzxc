package christmas;

import christmas.ui.InputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        ReservationStage reservationStage = new ReservationStage();
        reservationStage.runMakeDateStage();
        reservationStage.runMakeOrdersStage();
    }
}
