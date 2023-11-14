package christmas;

import static christmas.OrderedMenu.ORDER_RE_READ_REQUEST_MESSAGE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Order {
    private final List<OrderedMenu> Order;

    public Order(List<OrderedMenu> order) {
        validateOrderedMenuNamesNotAllBeverage(order);
        validateOrdersMenuNotDuplicate(order);
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

    private void validateOrdersMenuNotDuplicate(List<OrderedMenu> orderedMenus) {
        Set<String> duplicateMenuNameChecker = new HashSet<>();

        for (OrderedMenu orderedMenu : orderedMenus) {
            duplicateMenuNameChecker.add(orderedMenu.getMenuName());
        }
        if (duplicateMenuNameChecker.size() != orderedMenus.size()) {
            throw new IllegalArgumentException(ORDER_RE_READ_REQUEST_MESSAGE);
        }
    }
}
