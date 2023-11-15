package christmas.domain;

import java.util.HashMap;
import java.util.Map;

public class EventProcess {
    private static final int MINIMUM_TOTAL_ORDER_PRICE_FOR_GIFT = 120000;
    private static final int D_DAY_DISCOUNT_AMOUNT_PER_DAY = 100;
    private static final int D_DAY_DISCOUNT_DEFAULT_AMOUNT = 1000;
    private static final int FIRST_DAY_NUMBER = 1;
    private static final int WEEKDAY_DESSERT_DISCOUNT_AMOUNT = 2023;
    private static final int WEEKEND_MAIN_DISCOUNT_AMOUNT = 2023;
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1000;

    public EventResult takeAllBenefit(Date date, Order order) {
        Map<BenefitType, Integer> allBenefit = new HashMap<>();

        allBenefit.put(BenefitType.GIFT, takeGift(order));
        allBenefit.put(BenefitType.D_DAY, takeDDayDiscount(date));
        allBenefit.put(BenefitType.WEEKDAY, takeWeekdayDiscount(date, order));
        allBenefit.put(BenefitType.WEEKEND, takeWeekendDiscount(date, order));
        allBenefit.put(BenefitType.SPECIAL, takeSpecialDiscount(date));

        cancelAllBenefitIfMeetCondition(order, allBenefit);

        return new EventResult(allBenefit);
    }

    private int takeGift(Order order) {
        if (order.calculateTotalOrderPrice() >= MINIMUM_TOTAL_ORDER_PRICE_FOR_GIFT) {
            return Menu.CHAMPAGNE.getPrice();
        }

        return 0;
    }

    private int takeDDayDiscount(Date date) {
        if (date.isDDayDiscountActive()) {
            return D_DAY_DISCOUNT_DEFAULT_AMOUNT
                    + (calculateDifferenceBetweenFirstDay(date) * D_DAY_DISCOUNT_AMOUNT_PER_DAY);
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
                discount += orderedMenu.getQuantity() * WEEKDAY_DESSERT_DISCOUNT_AMOUNT;
            }
        }

        return discount;
    }

    private int takeWeekendDiscount(Date date, Order order) {
        int discount = 0;

        for (OrderedMenu orderedMenu : order.getOrder()) {
            Menu menu = Menu.decideMenu(orderedMenu.getMenuName());
            if (date.isWeekendDiscountActive() && MenuType.decideMenuType(menu) == MenuType.MAIN) {
                discount += orderedMenu.getQuantity() * WEEKEND_MAIN_DISCOUNT_AMOUNT;
            }
        }

        return discount;
    }

    private int takeSpecialDiscount(Date date) {
        if (date.isSpecialDiscountActive()) {
            return SPECIAL_DISCOUNT_AMOUNT;
        }

        return 0;
    }

    private Map<BenefitType, Integer> cancelAllBenefitIfMeetCondition(Order order,
                                                                      Map<BenefitType, Integer> allBenefit) {
        if (isInsufficientTotalOrderPriceForEvent(order)) {
            allBenefit.replace(BenefitType.GIFT, 0);
            allBenefit.replace(BenefitType.D_DAY, 0);
            allBenefit.replace(BenefitType.WEEKDAY, 0);
            allBenefit.replace(BenefitType.WEEKEND, 0);
            allBenefit.replace(BenefitType.SPECIAL, 0);
        }

        return allBenefit;
    }

    private boolean isInsufficientTotalOrderPriceForEvent(Order order) {
        return order.calculateTotalOrderPrice() < 10000;
    }
}