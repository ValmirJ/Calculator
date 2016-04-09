/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

/**
 *
 * @author guilherme
 */
public class NumericElement extends ExpressionElement implements Comparable<NumericElement>{

    protected double value;
    
    public NumericElement(double value) {
        this.value = value;
    }
    
    @Override
    public boolean isOperator() {
        return false;
    }
    
    public double getValue() {
        return value;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == this)
            return true;
        
        if (other == null)
            return false;
        
        if (!(other instanceof NumericElement)) {
            return false;
        }
                
        NumericElement otherNumeric = (NumericElement) other;
        
        return otherNumeric.value == this.value;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash += 67 * this.value;
        return hash;
    }
    
    @Override
    public String toString() {
        return "NumericElement<" + this.value + ">";
    }

    @Override
    public int compareTo(NumericElement o) {
        return Double.compare(this.value, o.value);
    }
}
