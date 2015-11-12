package LZW;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Luokka LZW-pakatun tiedoston purkamista varten.
 */
public class Decompressor {
    
    private ArrayList pack;
    
    /**
    * @param feed Tavutaulukko, joka puretaan LZW-algoritmilla
    */
    public Decompressor (ArrayList feed) {
        this.pack = feed;
    }
      
    /**
     * Purkaa takaisin pakatun merkistön.
     * @return ArrayList, jonka alkiot byte-muodossa.
     */
    public ArrayList decompress () {
        
        //LZW-sanakirjan alustus:
        HashMap <Integer, ArrayList> dictionary = new HashMap();
        for (int i = 0; i < 256; i++) {
            ArrayList <Byte> bytes = new ArrayList();
            byte b =(byte)i;
            bytes.add(b);
            dictionary.put(i, bytes);
        }  
        
        ArrayList output = new ArrayList();
        int n = 0;
        int code = 256;
         while (n < this.pack.size()) {
            ArrayList bytes = new ArrayList();          
            int current = (int)this.pack.get(n);
            ArrayList c = dictionary.get(current);                 
                for(int i = 0; i < c.size(); i++){
                    bytes.add(c.get(i));
                }
            int next = 0;         
            if (n < this.pack.size()-1) {
                next = (int)this.pack.get(n+1);
                //Tässä ehtolauseessa on vielä bugi, jonka takia purkaminen ei onnistu 100-prosenttisesti:
                if (!dictionary.containsKey(next)) {
                    ArrayList d = dictionary.get(current);                 
                    bytes.add(d.get(0));
                    dictionary.put(code, bytes);
                }                
                else if (current > 255) {
                    ArrayList a = dictionary.get(next);
                    bytes.add(a.get(0));
                    dictionary.put(code, bytes);
                }
                else {
                    ArrayList b = dictionary.get(next);
                    for(int i = 0; i < b.size(); i++){
                    bytes.add(b.get(i));
                    dictionary.put(code, bytes);
                    }    
                }
            }    
            ArrayList ci = dictionary.get(current);
            for (int i = 0; i < ci.size(); i++) {
                output.add(ci.get(i));
            }
            n++;
            code++;
        }
        return output;
    }

}