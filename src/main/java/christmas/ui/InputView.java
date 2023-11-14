package christmas.ui;

import camp.nextstep.edu.missionutils.Console;
import christmas.Date;
import christmas.Order;
import christmas.OrderedMenu;
import java.util.ArrayList;
import java.util.List;

public class InputView {
    private static final String OPENING_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String DATE_REQUEST_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ORDER_REQUEST_MESSAGE
            = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public static Date inputDate() {
        try {
            return readDate();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return inputDate();
        }
    }

    private static Date readDate() {
        System.out.println(OPENING_MESSAGE);
        System.out.println(DATE_REQUEST_MESSAGE);

        String dateInput = Console.readLine();
        int dayNumber = Converter.convertDateInput(dateInput);

        return new Date(dayNumber);
    }

    public static Order inputOrder() {
        try {
            return new Order(readOrder());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return inputOrder();
        }
    }

    private static List<OrderedMenu> readOrder() {
        System.out.println(ORDER_REQUEST_MESSAGE);

        String orderInput = Console.readLine();
        List<String> separatedOrder = Converter.separateOrder(orderInput);
        List<OrderedMenu> order = new ArrayList<>();

        for (String orderedMenu : separatedOrder) {
            List<String> separatedNameAndAmount = Converter.separateNameAndAmount(orderedMenu);
            String name = separatedNameAndAmount.get(0);
            int amount = Converter.convertAmount(separatedNameAndAmount.get(1));
            order.add(new OrderedMenu(name, amount));
        }

        return order;
    }
}