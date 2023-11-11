package christmas;

public class Order {
    public static final String ORDER_RE_READ_REQUEST_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    private final String name;
    private final int amount;


    public Order(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }
}