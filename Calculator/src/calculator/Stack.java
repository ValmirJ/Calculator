/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 *
 * @author 15096134
 */
public class Stack<X> implements Cloneable {
    
    public Stack(int maximumSize) throws Exception {
        
    }
    
    public Stack(Stack s) throws Exception {
        
    }
    
    public void push(X elm) throws Exception {
        
    }
    
    public X pop() throws Exception {
        X poped = null;
        
        return poped;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        Queue clonned = (Queue) super.clone();
        
        return clonned;
    }
    
    @Override
    public boolean equals(Object other) {
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        
        return hash;
    }
    
    @Override
    public String toString() {
        return "Queue";
    }
}
