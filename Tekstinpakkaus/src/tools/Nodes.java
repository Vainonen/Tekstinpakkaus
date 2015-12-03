package tools;

/**
 * Luokka, jonka tarkoitus on korvata Javan ArrayList-tietorakenne
 * solmullisella listalla / pinolla tira-kurssin luentomateriaalia mukaillen
 * sillä erotuksella, että pinon pohjimmainen solmu on näkyvissä
 */
public class Nodes<E> {
    
    private int length;
    private int index;
    private Node<E> next;
    private Node<E> first;
    private Node<E> top;
    
    public Nodes () {
        this.length = 0;
        this.index = 0;
        this.first = null;
        this.next = null;
    }
    
    /**
     * @param number kokonaisluku, joka pilkotaan tavuiksi
     * @param value pinoon laitettava arvo
    */
    public void push(E value) {
        Node node = new Node(value);
        if (this.top == null) this.first = node;
        else {
            node.setNext(this.next);
            this.next = this.top;
        }
        this.top = node;
    }
    
    /**
     * @param number kokonaisluku, joka pilkotaan tavuiksi
     * @return palauttaa arvon pinon päältä
    */
    public boolean pop() {
        if (this.top != null) {
            
            
            if (this.next.getNext() != null) {
                this.top = this.next;
                this.next = this.next.getNext();
            }
            else {
                this.top = null;
                this.first = null;
            }
            //if (this.next.getNext() != null) this.next = this.next.getNext();
            return true;
        }
        else return false;
    }
    
    /**
     * @param number kokonaisluku, joka pilkotaan tavuiksi
     * @return palauttaa pinoon ensimmäiseksi laitetun arvon
    */
    public E getFirst() {
        if (this.first != null) return this.first.getValue();
        else return null;
    }
    
}
