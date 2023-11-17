package christmas.domain;

public enum OrderResultType {
    ORDER_MENU("<주문 메뉴>"),
    TOTAL_ORDER_PRICE("<할인 전 총주문 금액>"),
    GIFT_MENU("<증정 메뉴>"),
    BENEFIT_STATISTICS("<혜택 내역>"),
    TOTAL_BENEFIT_AMOUNT("<총혜택 금액>"),
    ESTIMATED_PAYMENT("<할인 후 예상 결제 금액>"),
    EVENT_BADGE("<12월 이벤트 배지>");

    private final String name;

    OrderResultType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
