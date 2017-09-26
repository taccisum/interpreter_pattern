package cn.tac.test.interpreter.arithmetic.full;

/**
 * @author tac
 * @since 26/09/2017
 */
public class FullCalculator {
    public int calculate(String statement){
        Parser parser = new Parser();
        return parser.parse(statement).interpret();
    }
}
