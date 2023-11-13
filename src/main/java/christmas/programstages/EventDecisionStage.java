package christmas.programstages;

import christmas.BenefitTitle;
import christmas.Date;
import christmas.Order;
import christmas.menutable.Menu;
import christmas.menutable.MenuType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventDecisionStage {
    private final Date date;
    private final List<Order> orders;

    public EventDecisionStage(Date date, List<Order> orders) {
        this.date = date;
        this.orders = orders;
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
        if (totalOrderPrice >= 120000) {
            return Menu.CHAMPAGNE.getPrice();
        }
        return 0;
    }

    public int takeDDayDiscount() {
        int discount = 0;

        if (date.isBeforeChristmas()) {
            discount = 1000 + (date.getDayNumber() - 1) * 100;
        }

        return discount;
    }

    public int takeWeekdayDiscount() {
        int discount = 0;

        for (Order order : orders) {
            Menu menu = Menu.decideMenu(order.getName());
            if (date.isWeekday() && MenuType.decideMenuType(menu) == MenuType.DESSERT) {
                discount += order.getAmount() * 2023;
            }
        }

        return discount;
    }

    public int takeWeekendDiscount() {
        int discount = 0;

        for (Order order : orders) {
            Menu menu = Menu.decideMenu(order.getName());
            if (date.isWeekend() && MenuType.decideMenuType(menu) == MenuType.MAIN) {
                discount += order.getAmount() * 2023;
            }
        }

        return discount;
    }

    public int takeSpecialDiscount() {
        int discount = 0;

        if (date.isStarred()) {
            discount += 1000;
        }

        return discount;
    }
}