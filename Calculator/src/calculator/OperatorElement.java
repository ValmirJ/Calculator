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
public class OperatorElement extends ExpressionElement {

    private static final char[] VALID_OPERATORS = {'+', '-', '*', '/', '^'};

    private char op;

    public OperatorElement(char op) throws Exception {
        for (char o : VALID_OPERATORS) {
            if (o == op) {
                this.op = op;
                return;
            }
        }

        throw new Exception("Invalid Operator");
    }

    @Override
    public boolean isOperator() {
        return true;
    }

    public char getOperator() {
        return op;
    }

    public NumericElement applyTo(NumericElement leftElm, NumericElement rightElm) {
        double left = leftElm.getValue();
        double right = rightElm.getValue();
        double result = 0.0;

        switch (op) {
            case '+':
                result = left + right;
                break;

            case '-':
                result = left - right;
                break;

            case '*':
                result = left * right;
                break;

            case '/':
                result = left / right;
                break;

            case '^':
                result = Math.pow(left, right);
                break;
        }

        return new NumericElement(result);
    }
}
