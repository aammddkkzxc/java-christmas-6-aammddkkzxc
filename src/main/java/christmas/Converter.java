package christmas;

import static christmas.Date.DATE_RE_READ_REQUEST_MESSAGE;
import static christmas.Order.ORDER_RE_READ_REQUEST_MESSAGE;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    private static final String ORDER_DELIMITER = ",";
    private static final String NAME_AND_AMOUNT_DELIMITER = "-";

    public static int convertReadNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(DATE_RE_READ_REQUEST_MESSAGE);
        }
    }

    /*private static List<Order> makeOrders(String readOrder) {
        List<String> separatedOrder = separateOrder(readOrder);
        List<Order> orders = new ArrayList<>();

        for (String order : separatedOrder) {
            List<String> separatedNameAndAmount = separateNameAndAmount(order);
            String name = separatedNameAndAmount.get(0);
            int amount = convertAmount(separatedNameAndAmount.get(1));
            orders.add(new Order(name, amount));
        }

        return orders;
    }*/

    private static List<String> separateOrder(String readOrder) {
        return new ArrayList<>(List.of(readOrder.split(ORDER_DELIMITER)));
    }
    
    private static List<String> separateNameAndAmount(String order) {
        return new ArrayList<>(List.of(order.split(NAME_AND_AMOUNT_DELIMITER)));
    }

    private static int convertAmount(String amount) {
        try {
            return Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ORDER_RE_READ_REQUEST_MESSAGE);
        }
    }
}
