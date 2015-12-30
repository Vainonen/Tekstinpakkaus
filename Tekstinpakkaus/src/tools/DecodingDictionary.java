package tools;

/**
 * Luokka, jonka tarkoitus on korvata Javan HashMap-tietorakenne ja
 * olla LZW-algoritmin sanakirja pakatun tiedoston pakkausta varten.
 */
public class DecodingDictionary {
        
    private Nodes [] dictionary;
    
    public DecodingDictionary () {
        this.dictionary = new Nodes[772663];
    }
    
    /**
     * @param code kokonaisluku, joka on sanakirjan indeksi
     * @param n linkitetty lista, joka sisältää sanakirjaan tallennettavan merkkijonon
    */
    public void add (int code, Nodes n) {
        int hashcode = hash(code);
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
     * @param code sanakirjan koodi, jonka mukaan etsitään merkkijonoa
     * @return palauttaa haun totuusarvon 
    */
    public boolean contains (int code) {
        if (getValue(code) == null) return false;
        return true;
        /*
        int hashcode = hash(code);
        if (dictionary[hashcode] == null) return false;
        Nodes overflow = dictionary[hashcode];
        Node <KeyValuePair> next = overflow.getFirst();
        KeyValuePair searched = next.getValue();
        if (searched.getKey() == code) return true;
        for (int i = 0; i < overflow.Size()-1; i++) {        
            next = overflow.getNext(next);
            searched = next.getValue();
            if (searched.getKey() == code) return true;
            }
        return false;
                */
    }
    
    /**
     * @param code sanakirjan koodi, jonka mukaan etsitään merkkijonoa
     * @return palauttaa merkkijonon linkitettynä listana
    */
    public Nodes getValue (int code) {
        int hashcode = hash(code);
        if (dictionary[hashcode] == null) return null;
        Nodes overflow = dictionary[hashcode];
        Node <KeyValuePair> next = overflow.getFirst();
        KeyValuePair searched = next.getValue();
        
        for (int i = 0; i < overflow.Size(); i++) {
            if (searched.getKey() == code) return searched.getNodes();
            next = overflow.getNext(next);
            searched = next.getValue();
            }
        return null;
    }
    
    /**
     * @param code sanakirjan koodi, jonka mukaan tehdään hajautus sanakirjataulukkoon
     * @return palauttaa hajautuskoodin
    */
    private int hash (int code) {
        return code %= 772663;
    }
}


