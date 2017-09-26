package cn.tac.test.interpreter.arithmetic.full;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author tac
 * @since 26/09/2017
 */
public class ParserTest {
    @Test
    public void parseSimply() throws Exception {
        Parser parser = new Parser();
        Assert.assertEquals("10 5 +", parser.convert2Postfix("10 + 5"));
        Assert.assertEquals("10 5 -", parser.convert2Postfix("10 - 5"));
        Assert.assertEquals("10 5 + 7 +", parser.convert2Postfix("10 + 5 + 7"));
        Assert.assertEquals("10 5 - 7 +", parser.convert2Postfix("10 - 5 + 7"));
        Assert.assertEquals("10 5 7 + +", parser.convert2Postfix("10 + ( 5 + 7 )"));
        Assert.assertEquals("10 5 7 - +", parser.convert2Postfix("10 + ( 5 - 7 )"));
        Assert.assertEquals("10 5 7 + + 19 23 + +", parser.convert2Postfix("10 + ( 5 + 7 ) + ( 19 + 23 )"));
        Assert.assertEquals("10 5 7 - + 19 23 - +", parser.convert2Postfix("10 + ( 5 - 7 ) + ( 19 - 23 )"));
        Assert.assertEquals("10 5 7 19 + + +", parser.convert2Postfix("10 + ( 5 + ( 7 + 19 ) )"));
        Assert.assertEquals("10 5 7 19 - + +", parser.convert2Postfix("10 + ( 5 + ( 7 - 19 ) )"));
    }

    @Test
    public void parsePriority() {
        Parser converter = new Parser();
        Assert.assertEquals("10 5 + 7 +", converter.convert2Postfix("10 + 5 + 7"));
        Assert.assertEquals("10 5 7 * +", converter.convert2Postfix("10 + 5 * 7"));
        Assert.assertEquals("10 5 7 * + 19 23 * -", converter.convert2Postfix("10 + 5 * 7 - 19 * 23"));
        Assert.assertEquals("10 5 7 / + 19 23 * +", converter.convert2Postfix("10 + 5 / 7 + 19 * 23"));
        Assert.assertEquals("10 5 7 + *", converter.convert2Postfix("10 * ( 5 + 7 )"));
        Assert.assertEquals("10 5 7 19 + / 23 * +", converter.convert2Postfix("10 + 5 / ( 7 + 19 ) * 23"));
        Assert.assertEquals("10 5 7 19 + 23 * / +", converter.convert2Postfix("10 + 5 / ( ( 7 + 19 ) * 23 )"));
        Assert.assertEquals("10 5 - 7 19 * 3 / 66 27 33 * + 107 - * +", converter.convert2Postfix("10 - 5 + 7 * 19 / 3 * ( 66 + 27 * 33 - 107 )"));
        Assert.assertEquals("10 5 - 7 * 19 3 / 66 27 33 * + 107 - * -", converter.convert2Postfix("( 10 - 5 ) * 7 - 19 / 3 * ( 66 + 27 * 33 - 107 )"));
    }
}