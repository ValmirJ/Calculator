package calculator;

import calculator.models.ExpressionElement;
import calculator.models.NumericElement;
import calculator.models.OperatorElement;
import calculator.models.OperatorElement.InvalidOperatorElement;
import calculator.models.ParenthesisElement;
import calculator.models.ParenthesisElement.InvalidParenthesisElement;
import calculator.structures.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression implements ExpressionContainer<ExpressionElement> {

    public class InvalidExpression extends Exception {

        public InvalidExpression(String msg) {
            super(msg);
        }
    };

    private Queue<ExpressionElement> elms;
    private String exp;
    private Pattern numberExp;

    public Expression(String exp) throws InvalidExpression {
        if (exp == null) {
            throw new InvalidExpression("Invalid Expression (null)");
        }

        // Remove white spaces
        exp = exp.replaceAll(" ", "");

        if (exp.length() <= 0) {
            throw new InvalidExpression("Invalid Expression (empty)");
        }

        this.exp = exp;
        numberExp = Pattern.compile("\\A(?<number>(-)?\\d+(\\.\\d+)?)(?<remaining>.*)\\z");
    }

    public void parseExpression() throws InvalidExpression {
        try {
            // Expression should be string where the worst case its
            // mapping 1:1 ExpressionElement
            this.elms = new Queue<>(exp.length());
        } catch (Exception ex) {
            Logger.getLogger(Expression.class.getName()).log(Level.SEVERE, null, ex);
        }

        parseExpression(this.exp, false);
    }

    private void parseExpression(String exp, boolean shouldStartWithSignal) throws InvalidExpression {
        if (exp.isEmpty()) {
            throw new InvalidExpression("Incomplete Expression");
        }

        try {
            char op = exp.charAt(0);
            OperatorElement oe = new OperatorElement(op);

            if (exp.length() > 1 && exp.charAt(1) == '-' && (op == '+' || op == '-')) {
                throw new InvalidExpression("Unexpected " + exp.charAt(1));
            }

            if (shouldStartWithSignal) {
                try {
                    elms.enqueue(oe);
                } catch (Exception ex) {
                    Logger.getLogger(Expression.class.getName()).log(Level.SEVERE, null, ex);
                }

                parseExpression(exp.substring(1), false);

                return;
            } else if (op != '-') {
                throw new InvalidExpression("Unexpected " + oe.toString());
            }
        } catch (InvalidOperatorElement ex) {
            if (shouldStartWithSignal) {
                throw new InvalidExpression("Expected a Operator. Got " + exp.charAt(0));
            }
        }

        try {
            ParenthesisElement pe = new ParenthesisElement(exp.charAt(0));

            if (pe.isClose()) {
                throw new InvalidExpression("Unexpected " + pe.toString());
            }

            int openCount = 0;
            int closePos = -1;

            for (int i = 1; i < exp.length(); i++) {
                char c = exp.charAt(i);

                if (c == '(') {
                    openCount++;
                }

                if (c == ')') {
                    openCount--;
                }

                if (openCount < 0) {
                    closePos = i;
                    break;
                }
            }

            if (closePos > 1) {
                try {
                    elms.enqueue(pe);
                } catch (Exception ex) {
                    Logger.getLogger(Expression.class.getName()).log(Level.SEVERE, null, ex);
                }

                parseExpression(exp.substring(1, closePos), false);

                try {
                    elms.enqueue(new ParenthesisElement(exp.charAt(closePos)));
                } catch (Exception ex) {
                    Logger.getLogger(Expression.class.getName()).log(Level.SEVERE, null, ex);
                }

                String continueExp = exp.substring(closePos + 1);

                if (!continueExp.isEmpty()) {
                    parseExpression(continueExp, true);
                }
            } else if (closePos == 1) {
                throw new InvalidExpression("Empty Parenthesis <()>");
            } else {
                throw new InvalidExpression("Missing <)>");
            }

            return;
        } catch (InvalidParenthesisElement ex) {

        }

        Matcher m = numberExp.matcher(exp);

        if (m.matches()) {
            String number = m.group("number");
            String remaining = m.group("remaining");

            try {
                elms.enqueue(new NumericElement(Double.parseDouble(number)));
            } catch (Exception ex) {
                Logger.getLogger(Expression.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (!remaining.isEmpty()) {
                parseExpression(remaining, true);
            }
        } else {
            throw new InvalidExpression("Expected a number or open parenthesis. Got " + exp.charAt(0));
        }
    }

    @Override
    public Queue<ExpressionElement> getExpressionQueue() {
        try {
            return (Queue<ExpressionElement>) elms.clone();
        } catch (CloneNotSupportedException ex) {
            // Cloning a queue is Supported!
            return null;
        }
    }
}
