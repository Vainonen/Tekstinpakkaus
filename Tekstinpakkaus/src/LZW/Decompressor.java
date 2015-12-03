package LZW;

import FileIO.FileRead;
import FileIO.FileWrite;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import tools.ByteTranslator;

/**
 * Luokka LZW-pakatun tiedoston purkamista varten.
 */
public class Decompressor {
    
    private byte [] pack;
    private FileWrite fw;
    private int bitlength;
    private ByteTranslator bt;
    private int n; // pointteri, joka viittaa käsiteltävään kohtaan tiedostossa
    
    /**
     * @param input Purettavan tiedoston nimi.
     * @param output Nimi palautettavalle tiedostolle.
     * @throws java.io.FileNotFoundException
    */
    public Decompressor (String input, String output) throws FileNotFoundException, IOException {
        FileRead fr = new FileRead(input);
        this.pack = fr.read();
        this.fw = new FileWrite(output);
        this.n = 0;
        this.bitlength = 9;
    }
      
    /**
     * Purkaa takaisin pakatun merkistön.
     * @return ArrayList, jonka alkiot byte-muodossa.
     * @throws java.io.IOException
     */
    public ArrayList decompress () throws IOException {
        
        //LZW-sanakirjan alustus:
        HashMap <Integer, ArrayList> dictionary = new HashMap();
        for (int i = 0; i < 256; i++) {
            ArrayList <Integer> bytes = new ArrayList();
            bytes.add(i);
            dictionary.put(i, bytes);
        }  
        
        ArrayList output = new ArrayList();
        
        bt = new ByteTranslator();
        int code = 256;
        int current = getBits();
        
         while (n < this.pack.length) {
            int next = -1;       
            ArrayList bytes = new ArrayList();
            ArrayList c = dictionary.get(current);
            for(int i = 0; i < c.size(); i++){
                bytes.add(c.get(i));
                output.add(c.get(i));
                int bytenumber = (int)c.get(i)-128;
                fw.write((byte)bytenumber);
                }
            
   
            if (n < this.pack.length) {
                next = getBits();
                
                if (!dictionary.containsKey(next)) {
                    ArrayList previousCode = dictionary.get(code-1);
                    bytes.add(previousCode.get(previousCode.size()-1));
                }                
                else {
                    ArrayList nextCode = dictionary.get(next);
                    bytes.add(nextCode.get(0));
                }
                dictionary.put(code, bytes);
            }    
            
            current = next;
            code++;
            
            // Tämä tarkistus bitti-tavumuunnoksia varten:
            if ((code + 1) % Math.pow(2, (double)this.bitlength) == 0) this.bitlength++;
        }

        fw.write((byte)current);
        fw.close();

        return output;
    }
    
    /**
     * Silmukanmuotoinen metodi, joka hakee ByteTranlator-oliolla määritellyn pituisen bittijonon.
     * @return bitit kokonaislukumuodossa
     */
    private int getBits () {
        int bits;
        while (true) {
     
            bits = this.bt.fromBytes((int)this.pack[this.n], this.bitlength);
            this.n++;
            
             if (this.n == this.pack.length && !bt.isEmpty()) {
                bits += (int)bt.getRemainder();
                break;
                }
             
            if (bits != -1) break;
        }    
            
        return bits;
    }
}