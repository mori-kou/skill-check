package q006.value;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

/**
 * 除算を行うクラス
 */
public class DividedValue implements IValue {
    @Override
    public void execute(Stack<BigDecimal> values) {
        // スタックから2つの値を抜き出し、除算した結果をスタックに積む
        BigDecimal right = values.pop();
        BigDecimal left = values.pop();
        values.push(left.divide(right, RoundingMode.HALF_UP));
    }
}
