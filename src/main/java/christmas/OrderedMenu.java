package christmas;

import christmas.menutable.Menu;
import christmas.menutable.MenuType;

public class OrderedMenu {
    public static final String ORDER_RE_READ_REQUEST_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final int AMOUNT_MINIMUM_QUANTITY = 1;

    private final String menuName;
    private final int quantity;


    public OrderedMenu(String menuName, int quantity) {
        validateOrderNameExistInMenu(menuName);
        validateOrderAmountQuantity(quantity);

        this.menuName = menuName;
        this.quantity = quantity;
    }

    private void validateOrderNameExistInMenu(String name) {
        if (Menu.decideMenu(name) == Menu.NONE) {
            throw new IllegalArgumentException(ORDER_RE_READ_REQUEST_MESSAGE);
        }
    }

    private void validateOrderAmountQuantity(int amount) {
        if (amount < AMOUNT_MINIMUM_QUANTITY) {
            throw new IllegalArgumentException(ORDER_RE_READ_REQUEST_MESSAGE);
        }
    }

    public boolean isBeverage() {
        return MenuType.decideMenuType(Menu.decideMenu(menuName)) == MenuType.BEVERAGE;
    }

    public int calculatePrice() {
        return Menu.decideMenu(menuName).getPrice() * quantity;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getQuantity() {
        return quantity;
    }
}