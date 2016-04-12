package calculator;

import java.lang.reflect.Method;

public class Stack<X> implements Cloneable {
    
    private int top;
    private Object[] p;
    
    public Stack(int maximumSize) throws Exception {
        if(maximumSize <= 0)
            throw new Exception("Invalid Size");
        this.p = new Object[maximumSize];
        this.top= 0;
    }
    
    public Stack(Stack<X> another) throws Exception {
        if(another == null)
            throw new Exception("Null Object, copy is impossible");
        
        this.top = another.top;
        for(int i = 0; i < this.top; i++)
        // Abre esse treco sempre ao lado ou sempre na linha de baixo.
        // AQUI vai se ao lado.
        {
            if(another.p[i] instanceof Cloneable)
            // Aqui tb
            {
                Class c = p[i].getClass();
                Class<?>[] paramsForm = null;
                Object[] paramsReais = null;
                Method m = c.getMethod("clone", paramsForm);
                this.p[i] = m.invoke(another.p[i], paramsReais);
            }
            else 
                this.p[i] = another.p[i];
        }
    }
    
    public void push(X elm) throws Exception {
        if(elm == null)
            throw new Exception("Null Object, impossible to push");
        if(this.top >= this.p.length)
            throw new Exception("Stack FULL, impossible to push");
        
        if(elm instanceof Cloneable) {
            Class c = elm.getClass();
            Class <?>[] paramsFormais = null;
            Object[] paramsReais = null;
            Method m = c.getMethod("clone", paramsFormais);
            this.p[this.top] = (X) m.invoke(elm, paramsReais);
        }
        else 
            this.p[this.top] = elm;
        
        this.top++;
    }
    
    public X pop() throws Exception {
        if(this.top <= 0)
            throw new Exception("Empty Stack");
        
        this.top--;
        return (X)this.p[this.top];
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        Stack<X> clonned = (Stack<X>) super.clone(); // ou Stack<X> clonned = null (Segundo o maligno)
        try {
            clonned = new Stack(this);
        }
        catch(Exception e) {} //Never happen, just ignore
        return clonned;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(!(obj instanceof Stack))
            return false;
        
        Stack<X> outra = (Stack<X>) obj;
        
        if(this.top != outra.top)
            return false;
        
        for(int i = 0; i < this.top; i++)
            if(!(this.p[this.top].equals(outra.p[outra.top])))
                return false;
       
        return true;
    }

    @Override
    public int hashCode() {
        int res = super.hashCode();
        res  = res * 7 + new Integer(this.top).hashCode();
        for(int i = 0; i < this.top; i++)
            res = res * 7 + this.p[i].hashCode();
        
        return res;
    }
    
    @Override
    public String toString() {
        String str = "Topo: " + this.top + "\nPilha: ";
        
        for(int i = 0; i < this.top; i++) {
            // O cast não é necessario.
            // chamar (X)this.p[i].toString(); ou this.p[i].toString(); sem cast é a mesma coisa.
            // o java sempre chama o método da classe na qual o objeto foi instanciado.
            // Ex.
            // Integer a = new Integer()
            // a.toString => Integer#toString()
            //
            // Supondo que a classe abaixa é válida:
            // class NumeroInteiro extends Integer { .... }
            //
            // NumeroInteiro i = new NumeroInteiro(20)
            // Integer b = (Integer) i;
            // b.toString() => NumeroInteiro#toString()
            str += (X)this.p[i].toString();  // ***Creio*** que fazendo o cast ele vai pegar o metodo toString da classe e não da Classe Objeto
        }
        return str;
    }
}
