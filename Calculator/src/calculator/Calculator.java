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

            PolishExpressionEvaluator pee = new PolishExpressionEvaluator(pe);
            pee.generateOutput();

            Queue<PolishElement> q = pee.getExpressionQueue();
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
