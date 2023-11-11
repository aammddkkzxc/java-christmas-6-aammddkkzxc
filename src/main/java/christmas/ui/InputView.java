package christmas.ui;

import static christmas.Date.DATE_RE_INPUT_REQUEST_MESSAGE;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String DATE_REQUEST_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    public int readDate() {
        System.out.println(DATE_REQUEST_MESSAGE);
        String input = Console.readLine();

        return validateReadDateNumber(input);
    }

    private int validateReadDateNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(DATE_RE_INPUT_REQUEST_MESSAGE);
        }
    }

    public void readOrder() {

    }
}
