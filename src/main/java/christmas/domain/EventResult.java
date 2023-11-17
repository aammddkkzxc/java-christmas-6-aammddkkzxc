package christmas.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EventResult {
    private static final String NON_BADGE = "없음";
    private static final String STAR_BADGE = "별";
    private static final String TREE_BADGE = "트리";
    private static final String SANTA_BADGE = "산타";
    private static final int STAR_BADGE_MINIMUM_AMOUNT = 5000;
    private static final int STAR_BADGE_MAXIMUM_AMOUNT = 10000;
    private static final int TREE_BADGE_MINIMUM_AMOUNT = 10000;
    private static final int TREE_BADGE_MAXIMUM_AMOUNT = 20000;
    private static final int SANTA_BADGE_MINIMUM_AMOUNT = 20000;

    private final Map<BenefitType, Integer> allBenefit;

    public EventResult(Map<BenefitType, Integer> allBenefit) {
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

    public int calculateTotalDiscountAmount() {
        int totalDiscountAmount = 0;
        List<BenefitType> benefitTypes = List.of(BenefitType.values());

        for (BenefitType benefitType : benefitTypes) {
            totalDiscountAmount += allBenefit.get(benefitType);
        }
        if (allBenefit.get(BenefitType.GIFT) == Menu.CHAMPAGNE.getPrice()) {
            totalDiscountAmount -= Menu.CHAMPAGNE.getPrice();
        }

        return totalDiscountAmount;
    }

    public boolean isReceivedGiftBenefit() {
        return allBenefit.get(BenefitType.GIFT) == Menu.CHAMPAGNE.getPrice();
    }

    public List<BenefitType> checkWhichBenefitExist() {
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
        int totalBenefitAmount = calculateTotalBenefitAmount();

        if ((totalBenefitAmount >= STAR_BADGE_MINIMUM_AMOUNT) && (totalBenefitAmount < STAR_BADGE_MAXIMUM_AMOUNT)) {
            return STAR_BADGE;
        }
        if ((totalBenefitAmount >= TREE_BADGE_MINIMUM_AMOUNT) && (totalBenefitAmount < TREE_BADGE_MAXIMUM_AMOUNT)) {
            return TREE_BADGE;
        }
        if ((totalBenefitAmount >= SANTA_BADGE_MINIMUM_AMOUNT)) {
            return SANTA_BADGE;
        }
        return NON_BADGE;
    }

    public Map<BenefitType, Integer> getAllBenefit() {
        return Collections.unmodifiableMap(allBenefit);
    }
}