package christmas;

import christmas.domain.Date;
import christmas.domain.EventProcess;
import christmas.domain.EventResult;
import christmas.domain.Order;
import christmas.ui.InputView;
import christmas.ui.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView.notifyProgramStarted();
        Date date = InputView.inputDate();
        Order order = InputView.inputOrder();

        EventProcess eventProcess = new EventProcess();
        EventResult eventResult = new EventResult();
        eventResult.takeAllBenefit(date, order, eventProcess);

        OutputView.printTotalOrderResults(date, order, eventResult);
    }
}