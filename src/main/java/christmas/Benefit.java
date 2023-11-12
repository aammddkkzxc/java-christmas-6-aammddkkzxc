package christmas;

public class Benefit {
    private final String name;
    private final int amount;

    public Benefit(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}