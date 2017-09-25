package cn.tac.test.interpreter.arithmetic.simple;

/**
 * 简单的计算器，不支持括弧运算，不支持优先级运算
 *
 * @author tac
 * @since 25/09/2017
 */
public class SimpleCalculator {

    public int calculate(String statement){
        Parser parser = new Parser();
        return parser.parse(statement).interpret();
    }

}
