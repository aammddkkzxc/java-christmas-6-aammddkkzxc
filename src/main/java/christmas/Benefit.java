package christmas;

public class Benefit {
    private final BenefitTitle benefitTitle;
    private final int amount;

    public Benefit(BenefitTitle benefitTitle, int amount) {
        this.benefitTitle = benefitTitle;
        this.amount = amount;
    }

    public BenefitTitle getBenefitTitle() {
        return benefitTitle;
    }

    public int getAmount() {
        return amount;
    }
}