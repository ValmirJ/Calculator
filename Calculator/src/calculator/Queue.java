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
import java.lang.reflect.Method;
public class Queue<X> implements Cloneable {

    protected Object[] queue;
    protected int first;
    protected int last;
    protected int elementCount;

    public Queue(int maximumSize) throws Exception {
       
        if (maximumSize <= 0) {
            throw new Exception("Invalid size set for queue");
        }
        this.queue = new Object [maximumSize];
        this.first = 0;
        this.last = -1;
        this.elementCount = 0;        
    }

    public Queue(Queue s) throws Exception {
       
         queue = new Object[s.queue.length];
         this.first = s.first;
         this.last = s.last;
         this.elementCount = s.elementCount;
         
         for (int i = 0; i < queue.length; i++)
         {
            if (s.queue[i] instanceof Cloneable)
            {
                 Class c = queue[i].getClass();
                 Class<?>[] formParams = null;
                 Object[] realParams = null;
                 Method m = c.getMethod("clone", formParams);
                 this.queue[i] = m.invoke(s.queue[i], realParams);         
            }
            else
            {
                this.queue[i] = s.queue[i];
            }
         }
    }

    public void enqueue(X elm) throws Exception {
       
        if (elementCount >= queue.length) {
            throw new Exception("Queue is full");
        }
        // last >= queue.length - 1
        if(last >= queue.length - 1)
            last = -1;
        this.last++;
        this.elementCount++;
        this.queue[last] = elm;
    }

    public X dequeue() throws Exception {
        X dequeued; 
        
        if (this.elementCount <= 0) 
            throw new Exception("Queue is empty");
        
        dequeued = (X) this.queue[this.first]; 
              
        this.first++;
        if (this.first == queue.length)
            first = 0;
        this.elementCount--;
        return dequeued;
    }

    @Override
    // \o/ Esse está ok!
    public Object clone() throws CloneNotSupportedException {
        Queue clonned = null;

        try {

            clonned = new Queue(this);
        } catch (Exception CloneNotSupportedException) {
        }

        return clonned;
    }

    @Override
    public boolean equals(Object other) {

        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (other instanceof Queue) {
            return true;
        }

        Queue que = (Queue) other;
        
        if (this.first != que.first){
            return false;
        }
        
        if (this.last != que.last) {
            return false;
        }
        
        if(this.queue.length != que.queue.length){
            return false;
        }
        if(this.elementCount != que.elementCount){
            return false;
        }
    
        for(int i = this.first; i <= this.last; i++) {
            if (!this.queue[i].equals(que.queue[i])) {
                return false;
            }
        }
        
        return true;
    }

    @Override
    public int hashCode() 
    {
        int hash = super.hashCode();
        hash += hash * 3 + this.queue.length;
        hash += hash * 3 + this.elementCount;
        hash += hash * 3 + this.first;
        hash += hash * 3 + this.last;
        
        for(int i = this.first; i <= this.last; i++)
            hash = hash * 3 + this.queue[i].hashCode();
        return hash;
    }

    @Override
    public String toString() {

        String str = "Queue = {";
         for(int i = this.first; i <= this.last; i++) {
            str += this.queue[i] + " , ";
        }
        if (this.last > - 1) {
            str += this.queue[this.last];
        }
        str += "}";
        return str;
    }
}