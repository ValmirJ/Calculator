package calculator.models;

public class NumericElement extends PolishElement implements Comparable<NumericElement> {

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
        if (other == this) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (!(other instanceof NumericElement)) {
            return false;
        }

        NumericElement otherNumeric = (NumericElement) other;

        return otherNumeric.value == this.value;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = hash * 67 + new Double(this.value).hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return Double.toString(this.value);
    }

    @Override
    public int compareTo(NumericElement o) {
        return Double.compare(this.value, o.value);
    }
}
