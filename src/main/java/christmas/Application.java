package christmas;

import christmas.ui.InputView;
import christmas.ui.OutputView;

public class Application {
    public static void main(String[] args) {
        Date date = InputView.inputDate();
        Order order = InputView.inputOrder();
        EventManager eventManager = new EventManager();
        Result result = eventManager.takeAllBenefit(date, order);

        OutputView.printAllResult(order, result);
    }
}