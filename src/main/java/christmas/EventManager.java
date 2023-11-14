package christmas;

import static christmas.OrderedMenu.ORDER_RE_READ_REQUEST_MESSAGE;

import christmas.menutable.Menu;
import christmas.menutable.MenuType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EventManager {
    private static final int MAXIMUM_MENU_QUANTITY = 20;
    private static final int MINIMUM_TOTAL_ORDER_PRICE_FOR_GIFT = 120000;
    private static final int D_DAY_DISCOUNT_PER_DAY = 100;
    private static final int ONE_THOUSAND_WON_DISCOUNT = 1000;
    private static final int DESSERT_AND_MAIN_DISCOUNT_PRICE = 2023;

    private final Date date;
    private final List<OrderedMenu> orderedMenus;

    public EventManager(Date date, List<OrderedMenu> orderedMenus) {
        validateOrdersMenuNotAllBeverage(orderedMenus);
        validateOrdersMenuNotDuplicate(orderedMenus);
        validateTotalMenuQuantity(orderedMenus);

        this.date = date;
        this.orderedMenus = orderedMenus;
    }

    private void validateOrdersMenuNotAllBeverage(List<OrderedMenu> orderedMenus) {
        List<Boolean> beverageChecker = new ArrayList<>();

        for (OrderedMenu orderedMenu : orderedMenus) {
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

    private void validateTotalMenuQuantity(List<OrderedMenu> orderedMenus) {
        int totalMenuQuantity = 0;

        for (OrderedMenu orderedMenu : orderedMenus) {
            totalMenuQuantity += orderedMenu.getQuantity();
        }
        if (totalMenuQuantity > MAXIMUM_MENU_QUANTITY) {
            throw new IllegalArgumentException(ORDER_RE_READ_REQUEST_MESSAGE);
        }
    }

    public int calculateTotalOrderPrice() {
        int totalOrderPrice = 0;

        for (OrderedMenu orderedMenu : orderedMenus) {
            totalOrderPrice += orderedMenu.calculatePrice();
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

        for (OrderedMenu orderedMenu : orderedMenus) {
            Menu menu = Menu.decideMenu(orderedMenu.getMenuName());
            if (date.isWeekday() && MenuType.decideMenuType(menu) == MenuType.DESSERT) {
                discount += orderedMenu.getQuantity() * DESSERT_AND_MAIN_DISCOUNT_PRICE;
            }
        }

        return discount;
    }

    public int takeWeekendDiscount() {
        int discount = 0;

        for (OrderedMenu orderedMenu : orderedMenus) {
            Menu menu = Menu.decideMenu(orderedMenu.getMenuName());
            if (date.isWeekend() && MenuType.decideMenuType(menu) == MenuType.MAIN) {
                discount += orderedMenu.getQuantity() * DESSERT_AND_MAIN_DISCOUNT_PRICE;
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

    public List<OrderedMenu> getOrders() {
        return orderedMenus;
    }
}