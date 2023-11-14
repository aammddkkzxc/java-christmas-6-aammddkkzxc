package christmas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum BenefitTitle {
    GIFT("증정 이벤트"),
    D_DAY("크리스마스 디데이 할인"),
    WEEKDAY("평일 할인"),
    WEEKEND("주말 할인"),
    SPECIAL("특별 할인");

    private final String title;

    BenefitTitle(String title) {
        this.title = title;
    }

    public static List<BenefitTitle> findExistingBenefit(Map<BenefitTitle, Integer> allBenefit) {
        BenefitTitle[] benefitTitles = BenefitTitle.values();
        List<BenefitTitle> existingBenefit = new ArrayList<>();

        for (BenefitTitle benefitTitle : benefitTitles) {
            if (allBenefit.containsKey(benefitTitle)) {
                existingBenefit.add(benefitTitle);
            }
        }

        return existingBenefit;
    }

    public String getTitle() {
        return title;
    }
}