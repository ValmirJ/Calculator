/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import calculator.models.PolishElement;
import calculator.structures.Queue;
import calculator.structures.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guilherme
 */
public class PolishExpressionEvaluator implements ExpressionContainer<PolishElement> {

    Queue<PolishElement> inputQueue;
    Queue<PolishElement> outputQueue;
    Stack<PolishElement> mStack;

    public PolishExpressionEvaluator(ExpressionContainer<PolishElement> mcont) {
        try {
            this.inputQueue = (Queue<PolishElement>) mcont.getExpressionQueue().clone();
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
        // TODO: Replace this with the correct algorythm

        for (;;) {
            try {
                outputQueue.enqueue(inputQueue.dequeue());
            } catch (Exception ex) {
                break;
            }
        }
    }

    @Override
    public Queue<PolishElement> getExpressionQueue() {
        return outputQueue;
    }
}
