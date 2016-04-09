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
    // Uma dica, use `first` e `last`. Assim fica claro que o primeiro é o primeiro
    // que chegou, e ultimo, o ultimo que chegou. Se você imaginar começo como o ultimo
    // que chegou, ja vira uma salada.
    protected int beginning;
    protected int ending;

    // Isso não tem muita utilidade. A menos que você queira guardar a qtde
    //  de elementos que estão na fila. Portanto:
    // Chame isso de elementCount. Assim você não vai confundir com
    // queue.length
    protected int size;

    public Queue(int maximumSize) throws Exception {
        // Essa condição é inversa!
        if (maximumSize >= 0) {
            throw new Exception("Invalid size set for queue");
        }

        // Note que ao fazer (por exemplo, no main)
        // Queue<Integer> q = new Queue<>(20);
        // É esse metodo que é executado (construtor) Portanto:
        // Inicialize this.first, this.last, this.queue aqui.
        // Não no construtor de cópia.
        // Se for usar o elementCount, também comece-o aqui.
        // Você não vai precisar guardar essa informação. No java, após voce inicializar
        // um array, este ja guarda o tamanho:
        // this.queue = new Object[maximumSize]
        // portanto:
        // this.queue.length sempre será igual a maximumSize
        size = maximumSize;
    }

    public Queue(Queue s) throws Exception {
        // Quase correto. Na verdade você terá que usar s.queue.length
        queue = new Object[size];

        // Faça o clone de todos os elementos do array.
        // Mais precisamente, você poderia clonar apenas os elementos que estao entre
        // this.first e this.last. Não seria simples, pois considerando a fila circular
        // você teria que tratar o caso this.first = 5 e this.last = 1
        //
        // Note que se clonar o array todo, funciona e o código fica mais simples.
        // Esses valores tem que ser a cópia de s.
        // this.first = s.first
        // this.last = s.last
        // Se você usar o this.elementCount, copie-o também
        beginning = 0;
        ending = -1;
    }

    public void enqueue(X elm) throws Exception {
        // Se fizer uma fila cirluar, e você usar o elementCount
        // você poderá fazer:
        // if (elementCount >= queue.length)
        //     throw new Exception("Queue is full");

        // Tente fazer a fila circular.
        if (ending == queue.length - 1) {
            throw new Exception("Queue is full");
        }

        // Note que você começou ending com -1. Isso vai dar erro.
        // Incremente antes, ou começe ending (last) com 0
        queue[ending] = elm;
        ending++;

        // Também incremente o elementCount se você usá-lo
    }

    public X dequeue() throws Exception {
        X dequeued = null;

        // Isso sempre vai dar erro.
        // Você iniciou o beginning com 0, e esta atualizando somente ending
        // no enqueue. Portanto o beginning sempre será 0.
        // Note que se você usar o elementCount, você pode fazer
        // if (this.elementCount <= 0)
        if (this.beginning == 0) {
            throw new Exception("Queue is empty");
        }

        this.beginning--;

        // Faltou retornar o elemento correto. Esse mock devolverá null
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

        if (this.ending != que.ending) {
            return false;
        }

        // faltou checar o first (beginning)
        // Hmm, isso não vai rolar. Considere que numa file circular
        // podem sobrar elementos no array pois estarão fora do beginning e ending
        // Eles não devem ser considerados nessa comparação
        for (int i = 0; i <= this.ending; i++) {
            if (!this.queue[i].equals(que.queue[i])) {
                return false;
            }
        }

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
        // Você está mostrando o array da fila, não o first até o last
        for (int i = 0; i < this.ending; i++) {
            str += this.queue[i] + " , ";
        }
        if (this.ending > - 1) {
            str += this.queue[this.ending];
        }
        str += "}";

        return str;
    }
}
