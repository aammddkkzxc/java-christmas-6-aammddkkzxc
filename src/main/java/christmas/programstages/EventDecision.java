package christmas.programstages;

import static christmas.Order.ORDER_RE_READ_REQUEST_MESSAGE;

import christmas.BenefitTitle;
import christmas.Date;
import christmas.Order;
import christmas.menutable.Menu;
import christmas.menutable.MenuType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EventDecision {
    private static final int MAXIMUM_MENU_QUANTITY = 20;
    private static final int MINIMUM_TOTAL_ORDER_PRICE_FOR_GIFT = 120000;
    private static final int D_DAY_DISCOUNT_PER_DAY = 100;
    private static final int ONE_THOUSAND_WON_DISCOUNT = 1000;
    private static final int DESSERT_AND_MAIN_DISCOUNT_PRICE = 2023;

    private final Date date;
    private final List<Order> orders;

    public EventDecision(Date date, List<Order> orders) {
        validateOrdersMenuNotAllBeverage(orders);
        validateOrdersMenuNotDuplicate(orders);
        validateTotalMenuQuantity(orders);

        this.date = date;
        this.orders = orders;
    }

    private void validateOrdersMenuNotAllBeverage(List<Order> orders) {
        List<Boolean> beverageChecker = new ArrayList<>();

        for (Order order : orders) {
            if (order.isBeverage()) {
                beverageChecker.add(true);
            }
            if (!order.isBeverage()) {
                beverageChecker.add(false);
            }
        }

        if (!beverageChecker.contains(false)) {
            throw new IllegalArgumentException(ORDER_RE_READ_REQUEST_MESSAGE);
        }
    }

    private void validateOrdersMenuNotDuplicate(List<Order> orders) {
        Set<String> duplicateMenuNameChecker = new HashSet<>();

        for (Order order : orders) {
            duplicateMenuNameChecker.add(order.getName());
        }
        if (duplicateMenuNameChecker.size() != orders.size()) {
            throw new IllegalArgumentException(ORDER_RE_READ_REQUEST_MESSAGE);
        }
    }

    private void validateTotalMenuQuantity(List<Order> orders) {
        int totalMenuQuantity = 0;

        for (Order order : orders) {
            totalMenuQuantity += order.getAmount();
        }
        if (totalMenuQuantity > MAXIMUM_MENU_QUANTITY) {
            throw new IllegalArgumentException(ORDER_RE_READ_REQUEST_MESSAGE);
        }
    }

    public int calculateTotalOrderPrice() {
        int totalOrderPrice = 0;

        for (Order order : orders) {
            totalOrderPrice += order.calculatePrice();
        }

        return totalOrderPrice;
    }

    public Map<BenefitTitle, Integer> takeAllBenefit(int totalOrderPrice) {
        Map<BenefitTitle, Integer> allBenefit = new HashMap<>();
        allBenefit.put(BenefitTitle.GIFT, takeGift(totalOrderPrice));
        allBenefit.put(BenefitTitle.D_DAY, takeDDayDiscount());
        allBenefit.put(BenefitTitle.WEEKDAY, takeWeekdayDiscount());
        allBenefit.put(BenefitTitle.WEEKEND, takeWeekendDiscount());
        allBenefit.put(BenefitTitle.SPECIAL, takeSpecialDiscount());

        return allBenefit;
    }

    public int takeGift(int totalOrderPrice) {
        if (totalOrderPrice >= MINIMUM_TOTAL_ORDER_PRICE_FOR_GIFT) {
            return Menu.CHAMPAGNE.getPrice();
        }
        return 0;
    }

    public int takeDDayDiscount() {
        int discount = 0;

        if (date.isBeforeChristmas()) {
            discount = ONE_THOUSAND_WON_DISCOUNT + (date.getDayNumber() - 1) * D_DAY_DISCOUNT_PER_DAY;
        }

        return discount;
    }

    public int takeWeekdayDiscount() {
        int discount = 0;

        for (Order order : orders) {
            Menu menu = Menu.decideMenu(order.getName());
            if (date.isWeekday() && MenuType.decideMenuType(menu) == MenuType.DESSERT) {
                discount += order.getAmount() * DESSERT_AND_MAIN_DISCOUNT_PRICE;
            }
        }

        return discount;
    }

    public int takeWeekendDiscount() {
        int discount = 0;

        for (Order order : orders) {
            Menu menu = Menu.decideMenu(order.getName());
            if (date.isWeekend() && MenuType.decideMenuType(menu) == MenuType.MAIN) {
                discount += order.getAmount() * DESSERT_AND_MAIN_DISCOUNT_PRICE;
            }
        }

        return discount;
    }

    public int takeSpecialDiscount() {
        int discount = 0;

        if (date.isStarred()) {
            discount += ONE_THOUSAND_WON_DISCOUNT;
        }

        return discount;
    }

    public List<Order> getOrders() {
        return orders;
    }
}