package christmas;

import static christmas.Date.DATE_RE_INPUT_REQUEST_MESSAGE;
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
            throw new IllegalArgumentException(DATE_RE_INPUT_REQUEST_MESSAGE);
        }
    }

    public List<Order> separateNameAndAmount(String input) {
        List<String> separatedOrder = separateOrder(input);
        List<Order> orders = new ArrayList<>();

        for (String nameAndAmount : separatedOrder) {
            List<String> separatedNameAndAmount = new ArrayList<>(
                    List.of(nameAndAmount.split(NAME_AND_AMOUNT_DELIMITER)));
            orders.add(new Order(separatedNameAndAmount.get(0), separatedNameAndAmount.get(1)));
        }

        return orders;
    }

    public List<String> separateOrder(String order) {
        return new ArrayList<>(List.of(order.split(ORDER_DELIMITER)));
    }

    private static int convertAmount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ORDER_RE_READ_REQUEST_MESSAGE);
        }
    }
}
