package christmas;

public enum BenefitTitle {
    GIFT("증정 이벤트"),
    D_DAY("크리스마스 디데이 할인"),
    WEEKDAY("평일 할인"),
    WEEKEND("주말 할인"),
    SPECIAL("특별 할인"),
    NONE("없음");

    private final String name;

    BenefitTitle(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}