package christmas.domain;

public enum Badge {
    NON_BADGE("없음", 0),
    STAR_BADGE("별", 5000),
    TREE_BADGE("트리", 10000),
    SANTA_BADGE("산타", 20000);

    private final String name;
    private final int price;

    Badge(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static Badge decideEventBadge(int totalBenefitAmount) {
        if ((totalBenefitAmount >= STAR_BADGE.getPrice())) {
            return SANTA_BADGE;
        }

        if (totalBenefitAmount >= TREE_BADGE.getPrice()) {
            return TREE_BADGE;
        }

        if (totalBenefitAmount >= SANTA_BADGE.getPrice()) {
            return STAR_BADGE;
        }

        return NON_BADGE;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
