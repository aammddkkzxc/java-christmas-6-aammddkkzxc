package christmas.programstages;

import christmas.BenefitTitle;
import christmas.menutable.Menu;
import java.util.List;
import java.util.Map;

public class Result {
    private final int totalOrderPrice;
    private final Map<BenefitTitle, Integer> allBenefit;

    public Result(int totalOrderPrice, Map<BenefitTitle, Integer> allBenefit) {
        this.totalOrderPrice = totalOrderPrice;
        this.allBenefit = allBenefit;
    }

    public int calculateTotalBenefitPrice() {
        int totalBenefitPirce = 0;
        List<BenefitTitle> benefitTitles = BenefitTitle.findExistingBenefit(allBenefit);

        for (BenefitTitle benefitTitle : benefitTitles) {
            totalBenefitPirce += allBenefit.get(benefitTitle);
        }

        return totalBenefitPirce;
    }

    public int calculateEstimatedPayment() {
        int estimatedPayment = 0;
        List<BenefitTitle> benefitTitles = BenefitTitle.findExistingBenefit(allBenefit);

        for (BenefitTitle benefitTitle : benefitTitles) {
            estimatedPayment += allBenefit.get(benefitTitle);
        }
        if (allBenefit.get(BenefitTitle.GIFT) == Menu.CHAMPAGNE.getPrice()) {
            estimatedPayment -= Menu.CHAMPAGNE.getPrice();
        }

        return estimatedPayment;
    }
}