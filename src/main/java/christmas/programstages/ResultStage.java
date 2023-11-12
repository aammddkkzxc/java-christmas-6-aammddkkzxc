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

    public void run() {

    }

    public void makeGiftResult() {
        if (Benefits.contains(null)) {
            System.out.println("<증정 메뉴>" + "\n" + "없음");
        }
        if (!Benefits.contains(null)) {
            System.out.println("<증정 메뉴>" + "\n" + "샴페인 1개");
        }
    }
}
