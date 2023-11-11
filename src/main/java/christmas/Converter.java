package christmas;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    private static final String ORDER_DELIMITER = ",";
    private static final String NAME_AND_AMOUNT_DELIMITER = "-";

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
}
