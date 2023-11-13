package christmas.programstages;

import christmas.Benefit;
import christmas.BenefitTitle;
import christmas.menutable.Menu;
import christmas.ui.OutputView;
import java.util.Map;

public class ResultStage {
    private final int totalOrderPrice;
    private final Map<BenefitTitle, Integer> allBenefit;

    public ResultStage(int totalOrderPrice, Map<BenefitTitle, Integer> allBenefit) {
        this.totalOrderPrice = totalOrderPrice;
        this.allBenefit = allBenefit;
    }

    public void run() {
        OutputView.printTotalOrderPrice(totalOrderPrice);
        OutputView.printGift(totalOrderPrice, allBenefit);
        OutputView.printBenefitStatistics(totalOrderPrice, allBenefit);
        printBenefitPrice();
        int estimatedPayment = calculateEstimatedPayment(allBenefit);
        OutputView.printEstimatedPayment(totalOrderPrice, estimatedPayment);
        printBadge();
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

    public int calculateBenefitPrice(Map<BenefitTitle, Integer> allBenefit) {
        int price = 0;

        for (Benefit benefit : Benefits) {
            price += benefit.getAmount();
        }

        return price;
    }

    public int calculateEstimatedPayment(Map<BenefitTitle, Integer> allBenefit) {
        int estimatedPayment = 0;
        BenefitTitle[] benefitTitles = BenefitTitle.values();

        for (BenefitTitle benefitTitle : benefitTitles) {
            estimatedPayment += allBenefit.get(benefitTitle);
        }
        if (allBenefit.get(BenefitTitle.GIFT) == Menu.CHAMPAGNE.getPrice()) {
            estimatedPayment -= Menu.CHAMPAGNE.getPrice();
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