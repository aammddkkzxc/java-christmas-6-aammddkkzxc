package christmas.ui;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }

    public static void printTotalPrice(int totalOrderPrice) {
        System.out.println("<할인 전 총주문 금액>" + "\n" + totalOrderPrice + "원");
        System.out.println();
    }
}
