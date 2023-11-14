package christmas.ui;

import static christmas.Date.DATE_RE_READ_REQUEST_MESSAGE;
import static christmas.OrderedMenu.ORDER_RE_READ_REQUEST_MESSAGE;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    private static final String ORDER_DELIMITER = ",";
    private static final String NAME_AND_AMOUNT_DELIMITER = "-";

    public static int convertDateInput(String dateInput) {
        try {
            return Integer.parseInt(dateInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(DATE_RE_READ_REQUEST_MESSAGE);
        }
    }

    public static List<String> separateOrder(String readOrder) {
        return new ArrayList<>(List.of(readOrder.split(ORDER_DELIMITER)));
    }

    public static List<String> separateNameAndAmount(String order) {
        return new ArrayList<>(List.of(order.split(NAME_AND_AMOUNT_DELIMITER)));
    }

    public static int convertAmount(String amount) {
        try {
            return Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ORDER_RE_READ_REQUEST_MESSAGE);
        }
    }
}