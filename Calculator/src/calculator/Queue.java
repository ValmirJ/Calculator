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
        // Esse elementCount deveria começar com 0, dado que isso seria um contador para saber-mos
        // qtos elementos tem na fila.
        //
        // Note que se precisar saber o valor do maximumSize, você deverá usar `this.queue.length`
        this.elementCount = maximumSize;
    }

    public Queue(Queue s) throws Exception {
         // Checar se `s != null`
         queue = new Object[s.queue.length];
         this.first = s.first;
         this.last = s.last;
         this.elementCount = s.elementCount;
         // Você tem que fazer na verdade:
         // this.queue = queue
         this.queue = s.queue;
         // e não `s.queue`
         
         // Depois clonar os elementos de `s.queue` para `this.queue`
         // this.queue[0] = s.queue[0].clone()
         // Note que aquilo acima não funciona. Vamos seguir a instrução do Maligno por hora: use o reflect.
    }

    public void enqueue(X elm) throws Exception {
       
        if (elementCount >= queue.length) {
            throw new Exception("Queue is full");
        }
        // last >= queue.length - 1
        if(last == queue.length - 1)
            last = -1;
        this.last++;
        this.elementCount++;
        this.queue[last] = elm;
    }

    public X dequeue() throws Exception {
        X dequeued; 
        // Faça a verificação de fila vazia antes de pegar o objeto do vetor
          dequeued = (X) this.queue[this.first];
          
        // Esse if tem que ir pra cima ^
        if (this.elementCount <= 0) 
            throw new Exception("Queue is empty");
            
        // Isso TEM que ficar depois do this.first++
        if (this.first == queue.length)
            first = 0;
        
        this.first++;
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
        
        // Checar se a capacidade das filas é igual!
        // this.queue.length != que.queue.length
        
        // Checar se o total de elementos é igual.
        // this.elementCount != que.elementCount
        // 
        
        // isso é apenas valido se this.first < this.last
        for (int i = this.first; i <= this.last; i++) {
            // Checar se não é nulo:
            // if (this.queue[i] == null && que.queue[i] != null)
            //     return false;
            
            if (!this.queue[i].equals(que.queue[i])) {
                return false;
            }
        }
        // se auilo não for verdade....
        //
        // Checar do primeiro ao fim array
        // for (int i = this.first; i < this.queue.length; i++) {
        //    TODO: check de nulo
        //    if (!this.queue[i].equals(que.queue[i])) {
        //        return false;
        //    }
        // }
        //
        // Chear do começo até o ultimo
        // for (int i = 0; i <= this.last; i++) {
        //    TODO: check de nulo
        //    if (!this.queue[i].equals(que.queue[i])) {
        //        return false;
        //    }
        // }
        return true;
    }

    @Override
    public int hashCode() {
        // comece isso com super.hashCode()
        int hash = 3;
        
        // Para cada atributo que você checou no equals, multiplique o hash e adicione o hash do atributo.
        // hash += hash * 3 + this.queue.length;
        // hash += hash * 3 + this.elementCount;
        // hash += hash * 3 + this.first;
        // hash += hash * 3 + this.last;
        // 
        // Lembrando que isso é apenas valido se this.first < this.last
        // for (int i = this.first; i <= this.last; i++) {
        //    hash += hash * 3 + this.queue[i].hashCode();
        // }
        //
        // se auilo não for verdade fazer o mesmo esquema do equals
        return hash;
    }

    @Override
    public String toString() {

        String str = "Queue = {";
        // Faça o mesmo algoritmo de for do equals e do hashCode
        for (int i = this.first; i < this.last; i++) {
            str += this.queue[i] + " , ";
        }
        
        // hu3r@g3m T0P
        if (this.last > - 1) {
            str += this.queue[this.last];
        }
        str += "}";
        return str;
    }
}
