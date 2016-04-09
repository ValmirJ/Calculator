package calculator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Calculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
            String exp = null;

            System.out.print("Calculadora:\n\n");
            System.out.print("Digite a expressão que será resolvida:\n");

            exp = b.readLine();

            Expression e = new Expression(exp);
            e.parseExpression();

            // TODO: Transform `e` in a PosfixedExpression
            // TODO: Evaluate the PosfixedExpression
            //
            // To debug pruporses:
            Queue<ExpressionElement> q = e.getExpressionQueue();
            ExpressionElement el;

            while (true) {
                el = q.dequeue();
                System.out.println("Desenfileirou: " + el.toString());
            }

        } catch (Exception ex) {
            Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
