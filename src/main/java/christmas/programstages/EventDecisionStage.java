package christmas.programstages;

import christmas.Date;
import christmas.Order;
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

    public void takeGift() {

    }

    public void takeDiscount() {

    }

    public void takeBenefit() {

    }

    public void calculateDiscountPrice() {

    }

    public void calculateBenefitPrice() {

    }
}