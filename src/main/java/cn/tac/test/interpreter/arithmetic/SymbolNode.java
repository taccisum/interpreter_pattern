package cn.tac.test.interpreter.arithmetic;

/**
 * @author tac
 * @since 24/09/2017
 */
public abstract class SymbolNode implements Node {
    protected Node left;
    protected Node right;

    public SymbolNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }
}
