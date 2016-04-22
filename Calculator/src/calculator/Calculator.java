package calculator;

import calculator.models.PolishElement;
import calculator.structures.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Calculator {

    public String calculate(String exp) {
        StringBuilder builder = new StringBuilder();

        try {
            Expression e = new Expression(exp);
            e.parseExpression();
            PolishExpression pe = new PolishExpression(e);
            pe.generateOutput();

            // TODO: Transform `e` in a PosfixedExpression
            // TODO: Evaluate the PosfixedExpression
            //
            // To debug pruporses:
            Queue<PolishElement> q = pe.getExpressionQueue();
            PolishElement el;

            while (true) {
                try {
                    el = q.dequeue();
                    builder.append(el);
                } catch (Exception ex) {
                    break;
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
            builder.append(ex.getMessage());
        }

        return builder.toString();
    }

}
