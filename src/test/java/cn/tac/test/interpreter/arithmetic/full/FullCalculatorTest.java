package cn.tac.test.interpreter.arithmetic.full;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tac
 * @since 26/09/2017
 */
public class FullCalculatorTest {
    @Test
    public void calculate() throws Exception {
        FullCalculator calculator = new FullCalculator();
        Assert.assertEquals(-18, calculator.calculate("( 1 + 2 ) * 3 - 3 * ( 4 + 5 )"));
    }
}