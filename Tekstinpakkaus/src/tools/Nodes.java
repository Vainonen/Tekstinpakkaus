package tools;

import java.util.Iterator;

/**
 * Luokka, jonka tarkoitus on korvata Javan ArrayList-tietorakenne
 * solmullisella listalla / pinolla tira-kurssin luentomateriaalia mukaillen
 * sillä erotuksella, että pinon pohjimmainen solmu on näkyvissä
 * @param <E>
 */
public class Nodes<E> {
    
    private int length;
    private int size;
    private Node<E> previous;
    private Node<E> first;
    private Node<E> last;
    
    public Nodes () {
        this.length = 0;
        this.size = 0;
        this.first = null;
        this.previous = null;
    }
    
    /**
     * Metodi, joka laittaa listaa
    */
    public void push(E value) {
        Node node = new Node(value);
        if (this.last == null) this.first = node;
        else {
            this.last.setNext(node);
            this.previous = this.last;
        }
        this.last = node;
        this.size++;
    }
    
    /**
     * @param number kokonaisluku, joka pilkotaan tavuiksi
     * @return palauttaa arvon pinon päältä
    */
    public boolean pop() {
        if (this.last != null) {
            
            if (this.previous != null) {
            //if (this.next.getNext() != null) {
                this.last = this.previous;
                this.previous.setNext(null);
            }
            else {
                this.last = null;
                this.first = null;
            }
            size--;
            return true;
        }
        else return false;
    }
    
    /**
     * @param number kokonaisluku, joka pilkotaan tavuiksi
     * @return palauttaa pinoon ensimmäiseksi laitetun arvon
    */
    public Node getFirst() {
        if (this.first != null) return this.first;
        else return null;
    }
    
    public Node getLast() {
        if (this.last != null) return this.last;
        else return null;
    }
    
    public Node getNext(Node n) {
        if (n.getNext() != null) return n.getNext();
        else return null;
    }
    
    public int Size() {
        return this.size;
    }
    
    /**
     * @param n linkitetty lista
     * @return palauttaa totuusarvon, onko linkitetty lista sama kuin parametrissä
    */
    public boolean equals (Nodes n) {
        if (this == null) return false;
        if (this.getClass() != n.getClass()) return false;
        if (this.Size() != n.Size()) return false;
        int a = 0;
        int b = 0;
        Node node = this.getFirst();
        Node test = n.getFirst();
        if (node.getValue() instanceof Integer) a = (Integer)node.getValue();
        if (node.getValue() instanceof Byte) a = (Byte)node.getValue();
        if (test.getValue() instanceof Integer) b = (Integer)test.getValue();
        if (test.getValue() instanceof Byte) b = (Byte)test.getValue();
        if (a != b) return false;

        for (int i = 0; i < n.Size(); i++) {
            
            if (node.getValue() instanceof Integer) a = (Integer)node.getValue();
            if (node.getValue() instanceof Byte) a = (Byte)node.getValue();
            if (test.getValue() instanceof Integer) b = (Integer)test.getValue();
            if (test.getValue() instanceof Byte) b = (Byte)test.getValue();

            node = this.getNext(node);
            test = n.getNext(test);
            if (a != b) return false;

            }
        return true;
   }
}
