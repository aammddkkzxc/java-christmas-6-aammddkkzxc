package christmas.programstages;

import christmas.Benefit;
import java.util.List;

public class ResultStage {
    private final int totalOrderPrice;
    private final List<Benefit> Benefits;

    public ResultStage(int totalOrderPrice, List<Benefit> benefits) {
        this.totalOrderPrice = totalOrderPrice;
        this.Benefits = benefits;
    }


}
