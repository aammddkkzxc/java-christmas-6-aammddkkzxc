package christmas.programstages;

import christmas.BenefitTitle;
import christmas.menutable.Menu;
import christmas.ui.OutputView;
import java.util.List;
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
        OutputView.printTotalBenefitPrice(totalOrderPrice, calculateTotalBenefitPrice());
        OutputView.printEstimatedPayment(totalOrderPrice, calculateEstimatedPayment());
        printBadge();
    }

    public int calculateTotalBenefitPrice() {
        int totalBenefitPirce = 0;
        List<Integer> benefitPrices = (List<Integer>) allBenefit.values();

        for (Integer benefitPrice : benefitPrices) {
            totalBenefitPirce += benefitPrice;
        }

        return totalBenefitPirce;
    }

    public int calculateEstimatedPayment() {
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
        int price = calculateTotalBenefitPrice();
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