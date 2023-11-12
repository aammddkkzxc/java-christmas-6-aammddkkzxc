package christmas;

import christmas.ui.InputView;
import christmas.ui.OutputView;
import java.util.ArrayList;
import java.util.List;

public class ReservationStage {
    public Date runMakeDateStage() {
        try {
            return makeDate();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return runMakeDateStage();
        }
    }

    private Date makeDate() {
        String dateInput = InputView.readDate();
        int dayNumber = Converter.convertDateInput(dateInput);

        return new Date(dayNumber);
    }

    public List<Order> runMakeOrdersStage() {
        try {
            return makeOrders();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return runMakeOrdersStage();
        }
    }

    private List<Order> makeOrders() {
        String orderInput = InputView.readOrder();
        List<String> separatedOrder = Converter.separateOrder(orderInput);
        List<Order> orders = new ArrayList<>();

        for (String order : separatedOrder) {
            List<String> separatedNameAndAmount = Converter.separateNameAndAmount(order);
            String name = separatedNameAndAmount.get(0);
            int amount = Converter.convertAmount(separatedNameAndAmount.get(1));
            orders.add(new Order(name, amount));
        }

        return orders;
    }
}
