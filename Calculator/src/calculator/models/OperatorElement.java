package calculator.models;

public class OperatorElement extends PolishElement {

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

        switch (this.op) {
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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (!(other instanceof OperatorElement)) {
            return false;
        }

        OperatorElement otherOper = (OperatorElement) other;

        return otherOper.op == this.op;
    }

    @Override
    public String toString() {
        return "<" + this.op + ">";
    }

    @Override
    public int hashCode() {
        int h = super.hashCode();
        h += h * 29 + (new Character(this.op)).hashCode();
        return h;
    }
}
