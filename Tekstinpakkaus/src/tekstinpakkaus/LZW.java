package tekstinpakkaus;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * LZW-luokka merkkijonojen pakkausta ja purkamista varten.
 */
public class LZW {
    
    private byte [] text;
    private ArrayList pack;
    private HashMap <Integer, ArrayList> dictionary;
    
    public LZW (byte [] feed) {
        this.text = feed;
    }
    
    public LZW (ArrayList feed) {
        this.pack = feed;
    }
    
    /**
     * Alustaa LZW-sanakirjan HashMap-rakenteena pakkausta ja purkua varten.
     */
    private void initDictionary() {
        this.dictionary = new HashMap();
        for (int i = 0; i < 256; i++) {
            ArrayList bytes = new ArrayList();
            byte b =(byte)i;
            bytes.add(b);
            this.dictionary.put(i, bytes);
        }
    }  
    
    /**
     * Palauttaa HashMapsanakirjan avainluvun.
     * @param value ArrayList-muotoinen merkkijonotaulukko.
     * @return Kokonaislukumuodossa HashMap-avainarvo.
     */
    private Integer getKey(ArrayList value){
        for(int key : this.dictionary.keySet()){
            if(this.dictionary.get(key).equals(value)){
                return key;
                }
        }
        return null;
    }
    
    /**
     * Palauttaa n-pituisen bittijonon.
     * @return ArrayList, jonka tavut toistaiseksi vielä kokonaislukumuodossa.
     */
    public ArrayList compress () {
        ArrayList <Integer> output = new ArrayList();
        initDictionary();
        int n = 0;
        int code = 256;
        while (n < text.length-1) {
            ArrayList current = new ArrayList();
            current.add(text[n]);       
            while (this.dictionary.containsValue(current)) {
                current.add(text[n+1]);
                n++;
            }
            this.dictionary.put(code, current);
            
            current.remove(current.size()-1);
            output.add(getKey(current));
            code++;
        }
        output.add((int)text[n]);
        return output;
    }
    
    /**
     * Purkaa takaisin pakatun merkistön.
     * @return ArrayList, jonka alkiot byte-muodossa.
     */
    public ArrayList decompress () {
        ArrayList output = new ArrayList();
        initDictionary();
        int n = 0;
        int code = 256;
         while (n < this.pack.size()) {
            ArrayList bytes = new ArrayList();
            byte current = (byte)(int)this.pack.get(n); 
            bytes.add(current);
            byte next;
            if (n < this.pack.size()-1) {
                next = (byte)(int)this.pack.get(n+1);
                bytes.add(next);
            }
            this.dictionary.put(code, bytes);
            output.add(current);
            n++;
            code++;
        }
        return output;
    }

}

