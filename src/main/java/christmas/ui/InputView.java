package christmas.ui;

import camp.nextstep.edu.missionutils.Console;
import christmas.Date;
import christmas.Order;
import java.util.ArrayList;
import java.util.List;

public class InputView {
    private static final String OPENING_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String DATE_REQUEST_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ORDER_REQUEST_MESSAGE
            = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public static Date makeValidatedDate() {
        try {
            return makeDate();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return makeValidatedDate();
        }
    }

    private static Date makeDate() {
        System.out.println(OPENING_MESSAGE);
        System.out.println(DATE_REQUEST_MESSAGE);

        String dateInput = Console.readLine();
        int dayNumber = Converter.convertDateInput(dateInput);

        return new Date(dayNumber);
    }

    public static List<Order> makeValidatedOrders() {
        try {
            return makeOrders();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return makeValidatedOrders();
        }
    }

    private static List<Order> makeOrders() {
        System.out.println(ORDER_REQUEST_MESSAGE);

        String orderInput = Console.readLine();
        List<String> separatedOrder = Converter.separateOrder(orderInput);
        List<Order> orders = new ArrayList<>();

        for (String order : separatedOrder) {
            List<String> separatedNameAndAmount = Converter.separateNameAndAmount(order);
            String name = separatedNameAndAmount.get(0);
            int amount = Converter.convertAmount(separatedNameAndAmount.get(1));
            orders.add(new Order(name, amount));
        }

        return orders;
    }
}