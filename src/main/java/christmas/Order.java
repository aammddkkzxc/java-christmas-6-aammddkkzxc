package christmas;

import christmas.menutable.Menu;
import christmas.menutable.MenuType;
import java.util.Objects;

public class Order {
    public static final String ORDER_RE_READ_REQUEST_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final int MINIMUM_QUANTITY = 1;

    private final String name;
    private final int amount;


    public Order(String name, int amount) {
        validateOrderNameExistInMenu(name);
        validateOrderAmountBiggerThanMinimumQuantity(amount);

        this.name = name;
        this.amount = amount;
    }

    private void validateOrderNameExistInMenu(String name) {
        if (Menu.decideMenu(name) == Menu.NONE) {
            throw new IllegalArgumentException(ORDER_RE_READ_REQUEST_MESSAGE);
        }
    }

    private void validateOrderAmountBiggerThanMinimumQuantity(int amount) {
        if (amount < MINIMUM_QUANTITY) {
            throw new IllegalArgumentException(ORDER_RE_READ_REQUEST_MESSAGE);
        }
    }

    public boolean isBeverage() {
        return MenuType.decideMenuType(Menu.decideMenu(name)) == MenuType.BEVERAGE;
    }

    public int calculatePrice() {
        return Menu.decideMenu(name).getPrice() * amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}