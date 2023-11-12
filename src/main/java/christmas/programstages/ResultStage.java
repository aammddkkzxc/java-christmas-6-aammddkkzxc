package christmas.programstages;

import christmas.Benefit;
import java.util.List;

public class ResultStage {
    private final int totalOrderPrice;
    private final List<Benefit> Benefits;

    public ResultStage(int totalOrderPrice, List<Benefit> benefits) {
        this.totalOrderPrice = totalOrderPrice;
        this.Benefits = benefits;
    }

    public void run() {

    }

    public void makeGiftResult() {
        if (Benefits.contains(null)) {
            System.out.println("<증정 메뉴>" + "\n" + "없음");
        }
        if (!Benefits.contains(null)) {
            System.out.println("<증정 메뉴>" + "\n" + "샴페인 1개");
        }
        System.out.println();
    }

    public void makeBenefitStatistics() {
        System.out.println("<혜택 내역>");
        if (Benefits.isEmpty()) {
            System.out.println("없음");
        }
        if (!Benefits.isEmpty()) {
            for (Benefit benefit : Benefits) {
                System.out.println(benefit.getName() + " : -" + benefit.getAmount() + "원");
            }
        }
        System.out.println();
    }

    public void printBenefitPrice() {
        System.out.println("<총혜택 금액>" + "\n" + "-" + calculateBenefitPrice() + "원");
    }

    public int calculateBenefitPrice() {
        int price = 0;

        for (Benefit benefit : Benefits) {
            price += benefit.getAmount();
        }

        return price;
    }

    public void printEstimatedPayment() {
        System.out.println("<할인 후 예상 결제 금액>" + "\n" + "-" + (totalOrderPrice - calculateEstimatedPayment()) + "원");
    }

    public int calculateEstimatedPayment() {
        int estimatedPayment = 0;
        if (Benefits.contains(null)) {
            Benefits.remove(null);
            for (Benefit benefit : Benefits) {
                estimatedPayment += benefit.getAmount();
            }
        }
        if (!Benefits.contains(null)) {
            for (Benefit benefit : Benefits) {
                estimatedPayment += benefit.getAmount();
            }
            estimatedPayment -= 25000;
        }
        return estimatedPayment;
    }

    public void printBadge() {
        int price = calculateBenefitPrice();
        if(price < 5000) {
            System.out.println("<12월 이벤트 배지>" + "\n" + "없음");
        }
        if(price >= 5000 && price < 10000) {
            System.out.println("<12월 이벤트 배지>" + "\n" + "별");
        }
        if(price >= 10000 && price < 20000) {
            System.out.println("<12월 이벤트 배지>" + "\n" + "트리");
        }
        if(price >= 20000) {
            System.out.println("<12월 이벤트 배지>" + "\n" + "산타");
        }
    }
}
