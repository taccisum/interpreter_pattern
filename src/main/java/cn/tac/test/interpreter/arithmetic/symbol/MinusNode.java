package cn.tac.test.interpreter.arithmetic.symbol;

import cn.tac.test.interpreter.arithmetic.Node;
import cn.tac.test.interpreter.arithmetic.SymbolNode;

/**
 * @author tac
 * @since 24/09/2017
 */
public class MinusNode extends SymbolNode {
    public MinusNode(Node left, Node right) {
        super(left, right);
    }

    public int interpret() {
        return left.interpret() - right.interpret();
    }
}
