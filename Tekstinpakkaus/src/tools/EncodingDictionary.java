package tools;

/**
 * Luokka, jonka tarkoitus on korvata Javan HashMap-tietorakenne ja
 * olla LZW-algoritmin sanakirja pakatun tiedoston purkua varten.
 */
public class EncodingDictionary {
    
    private Nodes [] dictionary;
    
    public EncodingDictionary () {
        this.dictionary = new Nodes[772663];
    }
    
    /**
     * @param code kokonaisluku, joka on sanakirjan indeksi
     * @param n linkitetty lista, joka sisältää sanakirjaan tallennettavan merkkijonon
    */
    public void add (int code, Nodes n) {
        int hashcode = hash(n);
        KeyValuePair kvp = new KeyValuePair(code, n);
        if (dictionary[hashcode] == null) {
            Nodes <KeyValuePair> overflow = new Nodes();
            overflow.push(kvp);
            dictionary[hashcode] = overflow;
        }
        else {  
            Nodes overflow = dictionary[hashcode];
            overflow.push(kvp);
        }
    }
    
    /**
     * @param n linkitetty lista, joka etsitään sanakirjasta
     * @return palauttaa haun totuusarvon 
    */
    public boolean contains (Nodes n) {
        int hashcode = hash(n);
        if (dictionary[hashcode] == null) return false;
        Nodes overflow = dictionary[hashcode];
        Node <KeyValuePair> next = overflow.getFirst();
        KeyValuePair searched = next.getValue();
        if (searched.getNodes().equals(n)) return true;
        for (int i = 0; i < overflow.Size()-1; i++) {         
            next = overflow.getNext(next);
            searched = next.getValue();
            if (searched.getNodes().equals(n)) return true;
            }
        return false;  
    }
    
    /**
     * @param n linkitetty lista, jonka sanakirjaindeksiä haetaan
     * @return palauttaa sanakirjaindeksin
    */
    public int getCode (Nodes n) {
        int hashcode = hash(n);
        if (dictionary[hashcode] == null) return -1;
        Nodes overflow = dictionary[hashcode];
        Node <KeyValuePair> next = overflow.getFirst();
        KeyValuePair searched = next.getValue();
        
        for (int i = 0; i < overflow.Size(); i++) {
            if (searched.getNodes().equals(n)) return searched.getKey();
            next = overflow.getNext(next);
            searched = next.getValue();
            }
        return -1;
    }

    /**
     * @param n merkkijono linkitettynä listana, jonka mukaan tehdään hajautus sanakirjataulukkoon
     * @return palauttaa hajautuskoodin
    */
    private int hash (Nodes n) {
        int code = 0;
        Node node = n.getFirst();
        int c = 0;    
        code += c;
        for (int i = 0; i < n.Size(); i++) {
            if (node.getValue() instanceof Integer) c = (Integer)node.getValue();
            if (node.getValue() instanceof Byte) c = (Byte)node.getValue();
            if (c < 0) code += (Math.abs(c)+128)*(i+1);
            else code += c*(i+1);
            node = node.getNext();
            }
        code %= 772663; // jaetaan alkuluvulla
        return code;
    }
}
