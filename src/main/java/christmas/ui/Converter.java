package christmas.ui;

import static christmas.domain.Date.DATE_RE_READ_REQUEST_MESSAGE;
import static christmas.domain.OrderedMenu.ORDER_RE_READ_REQUEST_MESSAGE;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    private static final String ORDER_DELIMITER = ",";
    private static final String NAME_AND_AMOUNT_DELIMITER = "-";

    public static int convertDateNumeric(String Input) {
        try {
            return Integer.parseInt(Input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(DATE_RE_READ_REQUEST_MESSAGE);
        }
    }

    public static List<String> separateOrder(String order) {
        return new ArrayList<>(List.of(order.split(ORDER_DELIMITER)));
    }

    public static List<String> separateNameAndAmount(String orderedMenu) {
        validateAppropriateDelimiter(orderedMenu);
        List<String> separatedNameAndAmount = new ArrayList<>(List.of(orderedMenu.split(NAME_AND_AMOUNT_DELIMITER)));
        validateAppropriateForm(separatedNameAndAmount);

        return separatedNameAndAmount;
    }

    private static void validateAppropriateDelimiter(String orderedMenu) {
        if (!orderedMenu.contains(NAME_AND_AMOUNT_DELIMITER)) {
            throw new IllegalArgumentException(ORDER_RE_READ_REQUEST_MESSAGE);
        }
    }

    private static void validateAppropriateForm(List<String> separatedNameAndAmount) {
        if (separatedNameAndAmount.size() != 2) {
            throw new IllegalArgumentException(ORDER_RE_READ_REQUEST_MESSAGE);
        }
    }

    public static int convertAmountNumeric(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ORDER_RE_READ_REQUEST_MESSAGE);
        }
    }
}