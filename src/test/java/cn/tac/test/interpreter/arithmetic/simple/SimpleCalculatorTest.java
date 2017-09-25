package cn.tac.test.interpreter.arithmetic.simple;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tac
 * @since 25/09/2017
 */
public class SimpleCalculatorTest {
    @Test
    public void calculate() throws Exception {
        SimpleCalculator calculator = new SimpleCalculator();
        Assert.assertEquals(20, calculator.calculate("1 + 2 - 3 + 4 * 5"));     //因为不支持优先级运算，所以*运算最后执行了
    }
}