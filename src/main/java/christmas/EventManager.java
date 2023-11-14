package christmas;

import christmas.menutable.Menu;
import christmas.menutable.MenuType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    private static final int MINIMUM_TOTAL_ORDER_PRICE_FOR_GIFT = 120000;
    private static final int D_DAY_DISCOUNT_PRICE_PER_DAY = 100;
    private static final int D_DAY_DISCOUNT_DEFAULT_PRICE = 1000;
    private static final int DESSERT_AND_MAIN_DISCOUNT_PRICE = 2023;
    private static final int FIRST_DAY_NUMBER = 1;

    public Map<BenefitTitle, Integer> takeAllBenefit(int totalOrderPrice, Date date) {
        Map<BenefitTitle, Integer> allBenefit = new HashMap<>();
        allBenefit.put(BenefitTitle.GIFT, takeGift(totalOrderPrice));
        allBenefit.put(BenefitTitle.D_DAY, takeDDayDiscount(date));
        allBenefit.put(BenefitTitle.WEEKDAY, takeWeekdayDiscount());
        allBenefit.put(BenefitTitle.WEEKEND, takeWeekendDiscount());
        allBenefit.put(BenefitTitle.SPECIAL, takeSpecialDiscount());

        return allBenefit;
    }

    private int takeGift(int totalOrderPrice) {
        if (totalOrderPrice >= MINIMUM_TOTAL_ORDER_PRICE_FOR_GIFT) {
            return Menu.CHAMPAGNE.getPrice();
        }
        return 0;
    }

    private int takeDDayDiscount(Date date) {
        if (date.isBeforeChristmas()) {
            return D_DAY_DISCOUNT_DEFAULT_PRICE
                    + (calculateDifferenceBetweenFirstDay(date) * D_DAY_DISCOUNT_PRICE_PER_DAY);
        }

        return 0;
    }

    private int calculateDifferenceBetweenFirstDay(Date date) {
        return date.getDayNumber() - FIRST_DAY_NUMBER;
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