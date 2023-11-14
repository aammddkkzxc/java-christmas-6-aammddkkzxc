package christmas;

import christmas.menutable.Menu;
import christmas.menutable.MenuType;
import java.util.HashMap;
import java.util.Map;

public class EventManager {
    private static final int MINIMUM_TOTAL_ORDER_PRICE_FOR_GIFT = 120000;
    private static final int D_DAY_DISCOUNT_PRICE_PER_DAY = 100;
    private static final int D_DAY_DISCOUNT_DEFAULT_PRICE = 1000;
    private static final int WEEKDAY_DESSERT_DISCOUNT_PRICE = 2023;
    private static final int WEEKEND_MAIN_DISCOUNT_PRICE = 2023;
    private static final int SPECIAL_DISCOUNT_PRICE = 1000;
    private static final int FIRST_DAY_NUMBER = 1;

    public Result takeAllBenefit(Date date, Order order) {
        Map<BenefitTitle, Integer> allBenefit = new HashMap<>();
        allBenefit.put(BenefitTitle.GIFT, takeGift(order));
        allBenefit.put(BenefitTitle.D_DAY, takeDDayDiscount(date));
        allBenefit.put(BenefitTitle.WEEKDAY, takeWeekdayDiscount(date, order));
        allBenefit.put(BenefitTitle.WEEKEND, takeWeekendDiscount(date, order));
        allBenefit.put(BenefitTitle.SPECIAL, takeSpecialDiscount(date));

        return new Result(allBenefit);
    }

    private int takeGift(Order order) {
        if (order.calculateTotalOrderPrice() >= MINIMUM_TOTAL_ORDER_PRICE_FOR_GIFT) {
            return Menu.CHAMPAGNE.getPrice();
        }

        return 0;
    }

    private int takeDDayDiscount(Date date) {
        if (date.isDDayDiscountActive()) {
            return D_DAY_DISCOUNT_DEFAULT_PRICE
                    + (calculateDifferenceBetweenFirstDay(date) * D_DAY_DISCOUNT_PRICE_PER_DAY);
        }

        return 0;
    }

    private int calculateDifferenceBetweenFirstDay(Date date) {
        return date.getDayNumber() - FIRST_DAY_NUMBER;
    }

    private int takeWeekdayDiscount(Date date, Order order) {
        int discount = 0;

        for (OrderedMenu orderedMenu : order.getOrder()) {
            Menu menu = Menu.decideMenu(orderedMenu.getMenuName());
            if (date.isWeekdayDiscountActive() && MenuType.decideMenuType(menu) == MenuType.DESSERT) {
                discount += orderedMenu.getQuantity() * WEEKDAY_DESSERT_DISCOUNT_PRICE;
            }
        }

        return discount;
    }

    private int takeWeekendDiscount(Date date, Order order) {
        int discount = 0;

        for (OrderedMenu orderedMenu : order.getOrder()) {
            Menu menu = Menu.decideMenu(orderedMenu.getMenuName());
            if (date.isWeekendDiscountActive() && MenuType.decideMenuType(menu) == MenuType.MAIN) {
                discount += orderedMenu.getQuantity() * WEEKEND_MAIN_DISCOUNT_PRICE;
            }
        }

        return discount;
    }

    private int takeSpecialDiscount(Date date) {
        if (date.isSpecialDiscountActive()) {
            return SPECIAL_DISCOUNT_PRICE;
        }

        return 0;
    }
}