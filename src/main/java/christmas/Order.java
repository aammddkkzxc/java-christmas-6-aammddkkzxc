package christmas;

import static christmas.OrderedMenu.ORDER_RE_READ_REQUEST_MESSAGE;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<OrderedMenu> Order;

    public Order(List<OrderedMenu> order) {
        validateOrderedMenuNamesNotAllBeverage(order);
        Order = order;
    }

    private void validateOrderedMenuNamesNotAllBeverage(List<OrderedMenu> order) {
        List<Boolean> beverageChecker = new ArrayList<>();

        for (OrderedMenu orderedMenu : order) {
            if (orderedMenu.isBeverage()) {
                beverageChecker.add(true);
            }
            if (!orderedMenu.isBeverage()) {
                beverageChecker.add(false);
            }
        }

        if (!beverageChecker.contains(false)) {
            throw new IllegalArgumentException(ORDER_RE_READ_REQUEST_MESSAGE);
        }
    }
}
