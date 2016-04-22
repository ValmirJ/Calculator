package calculator.models;

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
    
    @Override
    public boolean equals(Object other) {
        if (other == this)
            return true;
        
        if (other == null)
            return false;
        
        if (!(other instanceof ParenthesisElement))
            return false;
        
        ParenthesisElement otherPar = (ParenthesisElement) other;
        
        return otherPar.op == this.op;
    }

    @Override
    public String toString() {
        return "ParenthesisElement<" + this.op + ">";
    }

    @Override
    public int hashCode() {
        int h = super.hashCode();
        h = h * 29 + Character.hashCode(this.op);
        return h;
    }
}
