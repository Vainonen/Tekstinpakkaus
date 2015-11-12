package LZW;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Luokka merkkijonojen pakkausta varten.
 */
public class Compressor {
    
    private byte [] text;
    
    /**
    * @param feed Tavutaulukko, joka pakataan LZW-algoritmilla
    */
    public Compressor (byte [] feed) {
        this.text = feed;
    }
  
    /**
     * Palauttaa n-pituisen bittijonon.
     * @return ArrayList, johon eripituiset bittijonot muutettu
     * tavuiksi tiedostoon kirjoittamista varten.
     */
    public ArrayList compress () {
        
        //LZW-sanakirjan alustus:
        HashMap <ArrayList, Integer> dictionary = new HashMap();
        for (int i = 0; i < 256; i++) {
            ArrayList <Byte> bytes = new ArrayList();
            byte b =(byte)i;
            bytes.add(b);
            dictionary.put(bytes, i);
        }  
        
        ArrayList <Integer> output = new ArrayList();
        int n = 0;
        int code = 256;
        /* Nämä muuttujat tulevia bittimuunnoksia varten:
        int bitlength = 9; 
        double check = Math.pow(2, (double)bitlength);
        */
        while (n < this.text.length-1) {
            ArrayList <Byte> current = new ArrayList();
            ArrayList <Byte> next = new ArrayList();
            current.add(this.text[n]); 
            next.add(this.text[n]); 
            while (dictionary.containsKey(next) && n < this.text.length-1) {
                current.add(this.text[n+1]);
                next.add(this.text[n+1]);
                n++;
            }
            dictionary.put(next, code);
            current.remove(current.size()-1);
            output.add((int)dictionary.get(current));
            /* Tämä tarkistus tulevia bittimuunnoksia varten:
            if (code % check == 0) {
                bitlength++;
                check = Math.pow(2, (double)bitlength);
                System.out.println("code "+code+" bitlength "+bitlength);
            }
            */
            code++;    
        }
        
        output.add((int)this.text[n]);
        return output;
    }
}