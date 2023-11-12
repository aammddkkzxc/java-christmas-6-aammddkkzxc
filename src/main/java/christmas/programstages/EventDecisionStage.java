package christmas.programstages;

import christmas.Date;
import christmas.Order;
import christmas.menutable.Menu;
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
        if(totalOrderPrice > 120000){
            return Menu.CHAMPAGNE;
        }
        return Menu.NONE;
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