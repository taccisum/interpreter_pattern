package cn.tac.test.interpreter.arithmetic.value;

import cn.tac.test.interpreter.arithmetic.ValueNode;

/**
 * @author tac
 * @since 24/09/2017
 */
public class SimpleValueNode extends ValueNode {
    public SimpleValueNode(int value) {
        super(value);
    }

    public int interpret() {
        return value;
    }
}
