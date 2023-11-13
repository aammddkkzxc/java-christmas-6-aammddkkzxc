package christmas.programstages;

import christmas.Benefit;
import christmas.BenefitTitle;
import java.util.List;

public class ResultStage {
    private final int totalOrderPrice;
    private final List<Benefit> Benefits;

    public ResultStage(int totalOrderPrice, List<Benefit> benefits) {
        this.totalOrderPrice = totalOrderPrice;
        this.Benefits = benefits;
    }

    public void run() {
        printTotalPrice();
        makeGiftResult();
        makeBenefitStatistics();
        printBenefitPrice();
        printEstimatedPayment();
        printBadge();
    }

    public void printTotalPrice() {
        System.out.println("<할인 전 총주문 금액>" + "\n" + totalOrderPrice + "원");
        System.out.println();
    }

    public void makeGiftResult() {
        if (totalOrderPrice < 10000 || Benefits.get(0).getBenefitTitle() == BenefitTitle.NONE) {
            System.out.println("<증정 메뉴>" + "\n" + "없음");
        }
        if (Benefits.get(0).getBenefitTitle() == BenefitTitle.GIFT) {
            System.out.println("<증정 메뉴>" + "\n" + "샴페인 1개");
        }
        System.out.println();
    }

    public void makeBenefitStatistics() {
        System.out.println("<혜택 내역>");
        if (totalOrderPrice < 10000 || Benefits.isEmpty()) {
            System.out.println("없음");
        }
        for (Benefit benefit : Benefits) {
            if (totalOrderPrice >= 10000 && benefit.getAmount() != 0) {
                System.out.println(benefit.getBenefitTitle().getName() + " : " + (-benefit.getAmount()) + "원");
            }
        }
        System.out.println();
    }

    public void printBenefitPrice() {
        if (totalOrderPrice < 10000) {
            System.out.println("<총혜택 금액>" + "\n" + 0 + "원");
        }
        if (totalOrderPrice >= 10000) {
            System.out.println("<총혜택 금액>" + "\n" + (-calculateBenefitPrice()) + "원");
        }
        System.out.println();
    }

    public int calculateBenefitPrice() {
        int price = 0;

        for (Benefit benefit : Benefits) {
            price += benefit.getAmount();
        }

        return price;
    }

    public void printEstimatedPayment() {
        if (totalOrderPrice < 10000) {
            System.out.println("<할인 후 예상 결제 금액>" + "\n" + totalOrderPrice + "원");
        }
        if (totalOrderPrice >= 10000) {
            System.out.println("<할인 후 예상 결제 금액>" + "\n" + (totalOrderPrice - calculateEstimatedPayment()) + "원");
        }
        System.out.println();
    }

    public int calculateEstimatedPayment() {
        int estimatedPayment = 0;
        for (Benefit benefit : Benefits) {
            estimatedPayment += benefit.getAmount();
            if (benefit.getBenefitTitle() == BenefitTitle.GIFT) {
                estimatedPayment -= 25000;
            }
        }
        return estimatedPayment;
    }

    public void printBadge() {
        int price = calculateBenefitPrice();
        if (totalOrderPrice < 10000 || price < 5000) {
            System.out.println("<12월 이벤트 배지>" + "\n" + "없음");
        }
        if (price >= 5000 && price < 10000) {
            System.out.println("<12월 이벤트 배지>" + "\n" + "별");
        }
        if (price >= 10000 && price < 20000) {
            System.out.println("<12월 이벤트 배지>" + "\n" + "트리");
        }
        if (price >= 20000) {
            System.out.println("<12월 이벤트 배지>" + "\n" + "산타");
        }
    }
}