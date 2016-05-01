/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import calculator.models.NumericElement;
import calculator.models.OperatorElement;
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

    private NumericElement solveOperation(NumericElement n1, NumericElement n2, OperatorElement signal) {
        double result;
        switch(signal.getOperator()) {
            case '+':
                result = n1.getValue() + n2.getValue();
                break;
            case '-':
                result = n1.getValue() - n2.getValue();
                break;
            case '*':
                result = n1.getValue() * n2.getValue();
                break;
            case '/':
                result = n1.getValue() / n2.getValue();
                    break;
            default: 
                result = Math.pow(n1.getValue(), n2.getValue());
        }
        return new NumericElement(result);
    }
    
    public void generateOutput() throws Exception {
        PolishElement aux;
        NumericElement aux1, aux2;
        OperatorElement auxSignal;
        for (;;) {
            try {
                aux = this.inputQueue.dequeue();
                if(aux instanceof NumericElement)
                    this.mStack.push((NumericElement)aux);
                else {
                    auxSignal = (OperatorElement)aux;
                    aux2 = (NumericElement)this.mStack.pop();
                    aux1 = (NumericElement)this.mStack.pop();
                    this.mStack.push(solveOperation(aux1, aux2, auxSignal));
                }
            } catch (Exception ex) {
                outputQueue.enqueue(mStack.pop());
                break;
            }
        }
    }

    @Override
    public Queue<PolishElement> getExpressionQueue() {
        return outputQueue;
    }
}
