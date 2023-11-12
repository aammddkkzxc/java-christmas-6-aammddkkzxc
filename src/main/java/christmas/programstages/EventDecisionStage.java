package christmas.programstages;

import christmas.Benefit;
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

    public Benefit takeGift(int totalOrderPrice) {
        if (totalOrderPrice > 120000) {
            return new Benefit("증정 이벤트", Menu.CHAMPAGNE.getPrice());
        }
        return null;
    }

    public Benefit takeDDayDiscount() {
        int discount = 0;

        if (date.isBeforeChristmas()) {
            discount = 1000 + (date.getDayNumber() - 1) * 100;
        }

        return new Benefit("크리스마스 디데이 할인", discount);
    }

    public Benefit takeWeekdayDiscount() {
        int discount = 0;

        for (Order order : orders) {
            Menu menu = Menu.decideMenu(order.getName());
            if (date.isWeekday() && MenuType.decideMenuType(menu) == MenuType.DESSERT) {
                discount += order.getAmount() * 2023;
            }
        }

        return new Benefit("평일 할인", discount);
    }

    public Benefit takeWeekendDiscount() {
        int discount = 0;

        for (Order order : orders) {
            Menu menu = Menu.decideMenu(order.getName());
            if (date.isWeekend() && MenuType.decideMenuType(menu) == MenuType.MAIN) {
                discount += order.getAmount() * 2023;
            }
        }

        return new Benefit("주말 할인", discount);
    }

    public Benefit takeSpecialDiscount() {
        int discount = 0;

        if (date.isStarred()) {
            discount += 1000;
        }

        return new Benefit("특별 할인", discount);
    }

    public void takeBenefit() {

    }

    public void calculateDiscountPrice() {

    }

    public void calculateBenefitPrice() {

    }
}