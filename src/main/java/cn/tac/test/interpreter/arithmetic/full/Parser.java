package cn.tac.test.interpreter.arithmetic.full;

import cn.tac.test.interpreter.arithmetic.Node;
import cn.tac.test.interpreter.arithmetic.symbol.DivNode;
import cn.tac.test.interpreter.arithmetic.symbol.MinusNode;
import cn.tac.test.interpreter.arithmetic.symbol.MulNode;
import cn.tac.test.interpreter.arithmetic.symbol.PlusNode;
import cn.tac.test.interpreter.arithmetic.value.SimpleValueNode;

import java.util.LinkedList;

/**
 * @author tac
 * @since 26/09/2017
 */
public class Parser {
    public static final String SPLITTER = " ";

    public Node parse(String statement) {
        LinkedList<Node> stack = new LinkedList<>();
        String[] segments = convert2Postfix(statement).split(SPLITTER);
        for (String segment : segments) {
            if (isValueNode(segment)) {
                stack.push(new SimpleValueNode(Integer.parseInt(segment)));
            } else if (isOperator(segment)) {
                Node rightNode = stack.pop();
                Node leftNode = stack.pop();
                stack.push(doOperate(leftNode, rightNode, segment));
            } else {
                throw new RuntimeException();
            }
        }
        return stack.pop();
    }

    public String convert2Postfix(String statement) {
        LinkedList<String> symbolStack = new LinkedList<>();    //临时存放符号节点的栈
        LinkedList<String> stack = new LinkedList<>();
        String[] nodes = statement.split(SPLITTER);

        for (String node : nodes) {
            if (isSymbolNode(node)) {
                if (symbolStack.size() == 0 || "(".equals(node)) {
                    symbolStack.push(node);
                } else if (isOperator(node)) {
                    String topSymbol = symbolStack.peek();
                    if (priority(node) <= priority(topSymbol)) {
                        while (symbolStack.peek() != null) {
                            if (priority(symbolStack.peek()) < priority(node)) {
                                break;
                            } else {
                                stack.push(symbolStack.pop());
                            }
                        }
                    }
                    symbolStack.push(node);
                } else if (")".equals(node)) {
                    while (!"(".equals(symbolStack.peek())) {
                        stack.push(symbolStack.pop());
                    }
                    symbolStack.pop();
                } else {
                    throw new RuntimeException("不支持的运算符");
                }
            } else if (isValueNode(node)) {
                stack.push(node);
            } else {
                throw new RuntimeException("不支持的运算符");
            }
        }

        //到数组尾部后，还有一些符号在栈内
        while (symbolStack.peek() != null) {
            stack.push(symbolStack.pop());
        }

        StringBuilder sb = new StringBuilder();
        String tmp;
        while (isNull(tmp = stack.pollLast())) {
            sb.append(tmp);
            sb.append(SPLITTER);
        }
        return sb.toString().trim();
    }

    private boolean isOperator(String node) {
        switch (node) {
            case "+":
            case "-":
            case "*":
            case "/":
                return true;
            default:
                return false;
        }
    }

    private int priority(String top) {
        switch (top) {
            case "*":
                return 2;
            case "/":
                return 2;
            case "+":
                return 1;
            case "-":
                return 1;
            case "(":
                return 0;
            case ")":
                return 0;
            default:
                throw new RuntimeException();
        }
    }

    private boolean isNull(String s) {
        return s != null;
    }

    private boolean isSymbolNode(String node) {
        switch (node) {
            case "*":
            case "/":
            case "+":
            case "-":
            case "(":
            case ")":
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

    private Node doOperate(Node leftNode, Node rightNode, String operator) {
        switch (operator) {
            case "+":
                return new PlusNode(leftNode, rightNode);
            case "-":
                return new MinusNode(leftNode, rightNode);
            case "*":
                return new MulNode(leftNode, rightNode);
            case "/":
                return new DivNode(leftNode, rightNode);
            default:
                throw new RuntimeException("不支持的运算符");
        }
    }
}
