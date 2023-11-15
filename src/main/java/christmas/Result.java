package christmas;

import christmas.menutable.Menu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Result {
    private static final String NON_BADGE = "없음";
    private static final String STAR_BADGE = "별";
    private static final String TREE_BADGE = "트리";
    private static final String SANTA_BADGE = "산타";

    private final Map<BenefitType, Integer> allBenefit;

    public Result(Map<BenefitType, Integer> allBenefit) {
        this.allBenefit = allBenefit;
    }

    public int calculateTotalBenefitAmount() {
        int totalBenefitAmount = 0;
        List<BenefitType> benefitTypes = List.of(BenefitType.values());

        for (BenefitType benefitType : benefitTypes) {
            totalBenefitAmount += allBenefit.get(benefitType);
        }

        return totalBenefitAmount;
    }

    public int calculateEstimatedPayment() {
        int estimatedPayment = 0;
        List<BenefitType> benefitTypes = List.of(BenefitType.values());

        for (BenefitType benefitType : benefitTypes) {
            estimatedPayment += allBenefit.get(benefitType);
        }
        if (allBenefit.get(BenefitType.GIFT) == Menu.CHAMPAGNE.getPrice()) {
            estimatedPayment -= Menu.CHAMPAGNE.getPrice();
        }

        return estimatedPayment;
    }

    public boolean isReceivedGiftBenefit() {
        return allBenefit.get(BenefitType.GIFT) == Menu.CHAMPAGNE.getPrice();
    }

    public List<BenefitType> isReceivedBenefit() {
        List<BenefitType> existingBenefit = new ArrayList<>();
        BenefitType[] benefitTypes = BenefitType.values();

        for (BenefitType benefitType : benefitTypes) {
            if (allBenefit.get(benefitType) != 0) {
                existingBenefit.add(benefitType);
            }
        }

        return existingBenefit;
    }

    public String decideEventBadge() {
        int totalBenefitPrice = calculateTotalBenefitAmount();

        if (totalBenefitPrice >= 5000 && totalBenefitPrice < 10000) {
            return STAR_BADGE;
        }
        if (totalBenefitPrice >= 10000 && totalBenefitPrice < 20000) {
            return TREE_BADGE;
        }
        if (totalBenefitPrice >= 20000) {
            return SANTA_BADGE;
        }
        return NON_BADGE;
    }

    public Map<BenefitType, Integer> getAllBenefit() {
        return Collections.unmodifiableMap(allBenefit);
    }
}