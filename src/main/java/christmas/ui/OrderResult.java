package christmas.ui;

public class OrderResult {
    private final OrderResultType orderResultType;
    private final String resultDetails;

    public OrderResult(OrderResultType orderResultType, String resultDetails) {
        this.orderResultType = orderResultType;
        this.resultDetails = resultDetails;
    }

    public OrderResultType getOrderResultType() {
        return orderResultType;
    }

    public String getResultDetails() {
        return resultDetails;
    }
}