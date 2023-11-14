package christmas;

import christmas.menutable.Menu;
import java.util.List;
import java.util.Map;

public class Result {
    private final Map<BenefitType, Integer> allBenefit;

    public Result(Map<BenefitType, Integer> allBenefit) {
        this.allBenefit = allBenefit;
    }

    public int calculateTotalBenefitPrice() {
        int totalBenefitPirce = 0;
        List<BenefitType> benefitTypes = BenefitType.findExistingBenefitType(allBenefit);

        for (BenefitType benefitType : benefitTypes) {
            totalBenefitPirce += allBenefit.get(benefitType);
        }

        return totalBenefitPirce;
    }

    public int calculateEstimatedPayment() {
        int estimatedPayment = 0;
        List<BenefitType> benefitTypes = BenefitType.findExistingBenefitType(allBenefit);

        for (BenefitType benefitType : benefitTypes) {
            estimatedPayment += allBenefit.get(benefitType);
        }
        if (allBenefit.get(BenefitType.GIFT) == Menu.CHAMPAGNE.getPrice()) {
            estimatedPayment -= Menu.CHAMPAGNE.getPrice();
        }

        return estimatedPayment;
    }
}