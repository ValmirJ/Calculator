/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 *
 * @author guilherme
 */
public class ParenthesisElement extends ExpressionElement {

    private static final char[] VALID_PARENTHESIS = {'(', ')'};

    private char op;

    public ParenthesisElement(char op) throws Exception {
        for (char o : VALID_PARENTHESIS) {
            if (o == op) {
                this.op = op;
                return;
            }
        }

        throw new Exception("Invalid Parenthesis");
    }

    @Override
    public boolean isOperator() {
        return true;
    }

    public boolean isClose() {
        return op == ')';
    }

    public boolean isOpen() {
        return op == '(';
    }
}
