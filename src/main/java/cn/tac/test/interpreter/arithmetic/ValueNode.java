package cn.tac.test.interpreter.arithmetic;

/**
 * @author tac
 * @since 24/09/2017
 */
public abstract class ValueNode implements Node{
    protected int value;

    public ValueNode(int value) {
        this.value = value;
    }
}
