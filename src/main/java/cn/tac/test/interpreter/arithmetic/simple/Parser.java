package cn.tac.test.interpreter.arithmetic.simple;

import cn.tac.test.interpreter.arithmetic.Node;
import cn.tac.test.interpreter.arithmetic.SymbolNode;
import cn.tac.test.interpreter.arithmetic.symbol.DivNode;
import cn.tac.test.interpreter.arithmetic.symbol.MinusNode;
import cn.tac.test.interpreter.arithmetic.symbol.MulNode;
import cn.tac.test.interpreter.arithmetic.symbol.PlusNode;
import cn.tac.test.interpreter.arithmetic.value.SimpleValueNode;

import java.util.LinkedList;

/**
 * @author tac
 * @since 25/09/2017
 */
public class Parser {
    public static final String SPLITTER = " ";  //为了方便解析，用空格将表达式分隔开

    public Node parse(String statement) {
        String[] arr = statement.split(SPLITTER);

        LinkedList<Node> nodeStack = new LinkedList<>();

        boolean symbolFlag = false;
        String symbol = "";
        for (String item : arr) {
            if (isSymbolNode(item)) {
                symbol = item;
                symbolFlag = true;
            } else if (isValueNode(item)) {
                nodeStack.push(new SimpleValueNode(Integer.parseInt(item)));
                if (symbolFlag) {
                    Node right = nodeStack.pop();    //后入先出
                    Node left = nodeStack.pop();
                    nodeStack.push(mapSymbolNode(left, right, symbol));
                    symbolFlag = false;
                }
            } else {
                throw new RuntimeException("表达式格式有误");
            }
        }

        return nodeStack.pop();
    }

    private SymbolNode mapSymbolNode(Node left, Node right, String symbol) {
        switch (symbol) {
            case "+":
                return new PlusNode(left, right);
            case "-":
                return new MinusNode(left, right);
            case "*":
                return new MulNode(left, right);
            case "/":
                return new DivNode(left, right);
            default:
                throw new RuntimeException("不支持的运算符");
        }
    }

    private boolean isSymbolNode(String item) {
        switch (item) {
            case "+":
            case "-":
            case "*":
            case "/":
                return true;
            default:
                return false;
        }
    }

    private boolean isValueNode(String item) {
        try {
            Integer.parseInt(item);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
