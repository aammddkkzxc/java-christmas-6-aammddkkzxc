package christmas;

import static christmas.OrderedMenu.ORDER_RE_READ_REQUEST_MESSAGE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Order {
    private static final int MAXIMUM_MENU_QUANTITY = 20;

    private final List<OrderedMenu> Order;

    public Order(List<OrderedMenu> order) {
        validateOrderedMenuNamesNotAllBeverage(order);
        validateOrderedMenuNamesNotDuplicate(order);
        validateTotalOrderedMenuQuantity(order);

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

    private void validateOrderedMenuNamesNotDuplicate(List<OrderedMenu> order) {
        Set<String> duplicateChecker = new HashSet<>();

        for (OrderedMenu orderedMenu : order) {
            duplicateChecker.add(orderedMenu.getMenuName());
        }
        if (duplicateChecker.size() != order.size()) {
            throw new IllegalArgumentException(ORDER_RE_READ_REQUEST_MESSAGE);
        }
    }

    private void validateTotalOrderedMenuQuantity(List<OrderedMenu> order) {
        int totalQuantity = 0;

        for (OrderedMenu orderedMenu : order) {
            totalQuantity += orderedMenu.getQuantity();
        }
        if (totalQuantity > MAXIMUM_MENU_QUANTITY) {
            throw new IllegalArgumentException(ORDER_RE_READ_REQUEST_MESSAGE);
        }
    }
}
