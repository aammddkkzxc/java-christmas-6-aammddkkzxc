package christmas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum BenefitType {
    GIFT("증정 이벤트"),
    D_DAY("크리스마스 디데이 할인"),
    WEEKDAY("평일 할인"),
    WEEKEND("주말 할인"),
    SPECIAL("특별 할인");

    private final String name;

    BenefitType(String name) {
        this.name = name;
    }

    public static List<BenefitType> findExistingBenefitType(Map<BenefitType, Integer> allBenefit) {
        BenefitType[] benefitTypes = BenefitType.values();
        List<BenefitType> existingBenefitType = new ArrayList<>();

        for (BenefitType benefitType : benefitTypes) {
            if (allBenefit.containsKey(benefitType)) {
                existingBenefitType.add(benefitType);
            }
        }

        return existingBenefitType;
    }

    public String getName() {
        return name;
    }
}