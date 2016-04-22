package calculator;

import calculator.models.NumericElement;
import calculator.models.PolishElement;
import calculator.structures.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Calculator {

    public NumericElement calculate(String exp) {
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
                el = q.dequeue();
                if (el != null) {
                    Logger.getLogger(Calculator.class.getName()).log(Level.INFO, "Desenfileirou: " + el.toString());
                } else {
                    Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, "Desenfileirou um elemento nulo");
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new NumericElement(0.0);
    }

}
