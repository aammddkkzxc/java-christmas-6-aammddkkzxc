package christmas.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventResult {
    private final Map<BenefitType, Integer> allBenefit = new HashMap<>();

    public EventResult() {
        initialize();
    }

    private void initialize() {
        allBenefit.put(BenefitType.GIFT, 0);
        allBenefit.put(BenefitType.D_DAY, 0);
        allBenefit.put(BenefitType.WEEKDAY, 0);
        allBenefit.put(BenefitType.WEEKEND, 0);
        allBenefit.put(BenefitType.SPECIAL, 0);
    }

    public void takeAllBenefit(Date date, Order order, EventProcess eventProcess) {
        if (!eventProcess.isInsufficientTotalOrderPriceForEvent(order)) {
            allBenefit.replace(BenefitType.GIFT, eventProcess.takeGift(order));
            allBenefit.replace(BenefitType.D_DAY, eventProcess.takeDDayDiscount(date));
            allBenefit.replace(BenefitType.WEEKDAY, eventProcess.takeWeekdayDiscount(date, order));
            allBenefit.replace(BenefitType.WEEKEND, eventProcess.takeWeekendDiscount(date, order));
            allBenefit.replace(BenefitType.SPECIAL, eventProcess.takeSpecialDiscount(date));
        }

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


    public Map<BenefitType, Integer> getAllBenefit() {
        return Collections.unmodifiableMap(allBenefit);
    }
}