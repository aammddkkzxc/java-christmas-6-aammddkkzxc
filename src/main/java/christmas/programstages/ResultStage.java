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
}
