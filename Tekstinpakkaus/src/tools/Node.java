package tools;

/**
 * Luokka pinoon laitettaville solmuille
 */
public class Node<E> {
    
    private E value;
    private Node next;
    
    /**
     * @param value Solmuun asetettava arvo
    */
    public Node (E value) {
        this.value = value;
        this.next = null;
    }
    
    public void setValue(E value) {
        this.value = value;
    }
    
    public void setNext(Node next) {
        this.next = next;
    }
    
     public E getValue() {
        return this.value;
    }
     
    public Node getNext() {
        return this.next;
    }
    
    public boolean hasNext() {
        if (this.next == null) return false;
        return true;
    }
}
