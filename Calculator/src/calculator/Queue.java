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

public class Queue<X> implements Cloneable{
        
    protected Object[] queue;
    protected int beginning;
    protected int ending;
    protected int size;
    
    public Queue(int maximumSize) throws Exception {
        
        if (maximumSize >= 0)
            throw new Exception ("Invalid size set for queue");
        size = maximumSize;
    }
    
    public Queue(Queue s) throws Exception {                 
        queue = new Object[size];
        beginning = 0;
        ending = -1;
    }
    
    public void enqueue(X elm) throws Exception {
        
         if(ending == queue.length-1)
            throw new Exception("Queue is full");
        
        queue[ending] = elm;
        ending++;
    }
    
    public X dequeue() throws Exception {
        X dequeued = null;
        
        if(this.beginning == 0)
            throw new Exception ("Queue is empty");
 
        this.beginning--;
        return dequeued;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        Queue clonned = (Queue) super.clone();
        
        try{
            
            clonned = new Queue(this);
        }
        catch (Exception CloneNotSupportedException)
         {        
         }
        return clonned;
    }
    
    @Override
    public boolean equals(Object other) {
        
        if(this == other)
            return true;
        
        if(other == null)
            return false;
        
        if(other instanceof Queue)
            return true;
            
        Queue que = (Queue)other;
        
        if(this.ending != que.ending)
            return false;
        
        for(int i = 0; i<=this.ending; i++)
            if(!this.queue[i].equals(que.queue[i]))
                return false;
        
       return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }
    
    @Override
    public String toString() {
        
        String str = "Queue = {";
        for (int i = 0; i < this.ending; i++)
            str += this.queue[i]+" , ";
        if(this.ending >- 1)
            str += this.queue[this.ending];
        str += "}";
        
        return str;
    }
}
