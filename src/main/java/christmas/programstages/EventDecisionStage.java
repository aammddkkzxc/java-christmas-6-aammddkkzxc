package christmas.programstages;

import christmas.Date;
import christmas.Order;
import christmas.menutable.Menu;
import christmas.menutable.MenuType;
import java.util.List;

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

    public Menu takeGift(int totalOrderPrice) {
        if (totalOrderPrice > 120000) {
            return Menu.CHAMPAGNE;
        }
        return Menu.NONE;
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

    public void takeBenefit() {

    }

    public void calculateDiscountPrice() {

    }

    public void calculateBenefitPrice() {

    }
}