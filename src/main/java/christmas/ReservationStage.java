package christmas;

import christmas.ui.OutputView;
import java.util.ArrayList;
import java.util.List;

public class ReservationStage {
    public Date runMakeDateStage(String dateInput) {
        try {
            return makeDate(dateInput);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return runMakeDateStage(dateInput);
        }
    }

    public Date makeDate(String dateInput) {
        int dayNumber = Converter.convertDateInput(dateInput);
        return new Date(dayNumber);
    }

    public List<Order> makeOrders(String orderInput) {
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
