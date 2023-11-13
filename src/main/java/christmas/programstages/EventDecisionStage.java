package christmas.programstages;

import christmas.Benefit;
import christmas.BenefitTitle;
import christmas.Date;
import christmas.Order;
import christmas.menutable.Menu;
import christmas.menutable.MenuType;
import java.util.ArrayList;
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
        /*List<Benefit> benefits = new ArrayList<>();
        benefits.add(takeGift(totalOrderPrice));
        benefits.add(takeDDayDiscount());
        benefits.add(takeWeekdayDiscount());
        benefits.add(takeWeekendDiscount());
        benefits.add(takeSpecialDiscount());*/
        Map<BenefitTitle, Integer> allBenefit = new HashMap<>();
        allBenefit.put(BenefitTitle.GIFT, takeGift(totalOrderPrice));
        return allBenefit;
    }

    public int takeGift(int totalOrderPrice) {
        if (totalOrderPrice >= 120000) {
            return Menu.CHAMPAGNE.getPrice();
        }
        return 0;
    }

    public Benefit takeDDayDiscount() {
        int discount = 0;

        if (date.isBeforeChristmas()) {
            discount = 1000 + (date.getDayNumber() - 1) * 100;
        }

        return new Benefit(BenefitTitle.D_DAY, discount);
    }

    public Benefit takeWeekdayDiscount() {
        int discount = 0;

        for (Order order : orders) {
            Menu menu = Menu.decideMenu(order.getName());
            if (date.isWeekday() && MenuType.decideMenuType(menu) == MenuType.DESSERT) {
                discount += order.getAmount() * 2023;
            }
        }

        return new Benefit(BenefitTitle.WEEKDAY, discount);
    }

    public Benefit takeWeekendDiscount() {
        int discount = 0;

        for (Order order : orders) {
            Menu menu = Menu.decideMenu(order.getName());
            if (date.isWeekend() && MenuType.decideMenuType(menu) == MenuType.MAIN) {
                discount += order.getAmount() * 2023;
            }
        }

        return new Benefit(BenefitTitle.WEEKEND, discount);
    }

    public Benefit takeSpecialDiscount() {
        int discount = 0;

        if (date.isStarred()) {
            discount += 1000;
        }

        return new Benefit(BenefitTitle.SPECIAL, discount);
    }
}