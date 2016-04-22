package calculator;

import calculator.models.ExpressionElement;
import calculator.models.NumericElement;
import calculator.models.OperatorElement;
import calculator.models.ParenthesisElement;
import calculator.models.PolishElement;
import calculator.structures.Queue;
import calculator.structures.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PolishExpression implements ExpressionContainer<PolishElement> {

    Queue<ExpressionElement> inputQueue;
    Queue<PolishElement> outputQueue;
    Stack<ExpressionElement> mStack;

    public PolishExpression(ExpressionContainer<ExpressionElement> mcont) {
        try {
            this.inputQueue = (Queue<ExpressionElement>) mcont.getExpressionQueue().clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(PolishExpression.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            this.outputQueue = new Queue<>(this.inputQueue.getElementCount());
        } catch (Exception ex) {
            Logger.getLogger(PolishExpression.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            this.mStack = new Stack<>(this.inputQueue.getElementCount());
        } catch (Exception ex) {
            Logger.getLogger(PolishExpression.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void generateOutput() throws Exception {
        ExpressionElement aux;
        try {
            while (true) {
                try {
                    aux = this.inputQueue.dequeue();
                } catch (Exception e) {
                    break;
                }
                if (aux instanceof NumericElement) {
                    try {
                        this.outputQueue.enqueue((NumericElement) aux);
                    } catch (Exception e) {
                        Logger.getLogger(PolishExpression.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
                if (aux instanceof OperatorElement) {
                    try {
                        this.mStack.push((OperatorElement) aux);
                    } catch (Exception e) {
                        Logger.getLogger(PolishExpression.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
                if (aux instanceof ParenthesisElement) {
                    try {
                        if (((ParenthesisElement) aux).isOpen()) {
                            this.mStack.push(aux);
                        } else {
                            for (;;) {
                                ExpressionElement aux2 = this.mStack.pop();
                                if (aux2 instanceof ParenthesisElement && ((ParenthesisElement) aux2).isOpen()) {
                                    break;
                                }

                                this.outputQueue.enqueue((PolishElement) aux2);
                            }
                        }
                    } catch (Exception e) {
                        Logger.getLogger(PolishExpression.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }
            for (;;) {
                try {
                    this.outputQueue.enqueue((PolishElement) this.mStack.pop());
                } catch (Exception e) {
                    break;
                }
            }

        } catch (Exception ex) { //Fila de entrada vázia
            Logger.getLogger(PolishExpression.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Queue<PolishElement> getExpressionQueue() {
        return this.outputQueue;
    }

}
