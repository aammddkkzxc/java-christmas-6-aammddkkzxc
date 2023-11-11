package christmas;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    private static final String ORDER_DELIMITER = ",";

    public List<String> separateOrder(String input) {
        return new ArrayList<>(List.of(input.split(ORDER_DELIMITER)));
    }
}
