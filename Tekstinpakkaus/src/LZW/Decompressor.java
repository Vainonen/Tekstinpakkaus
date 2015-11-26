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
    private int pointer;
    
    /**
     * @param input Purettavan tiedoston nimi.
     * @param output Nimi palautettavalle tiedostolle.
     * @throws java.io.FileNotFoundException
    */
    public Decompressor (String input, String output) throws FileNotFoundException, IOException {
        FileRead fr = new FileRead(input);
        this.pack = fr.read();
        this.fw = new FileWrite(output);
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
            ArrayList <Byte> bytes = new ArrayList();
            byte b =(byte)i;
            bytes.add(b);
            dictionary.put(i, bytes);
        }  
        
        ArrayList output = new ArrayList();
        int n = 0;
        int code = 255;
        int bitlength = 9;
        int current = -1;
        ByteTranslator bt = new ByteTranslator();
        
         while (n < this.pack.length) {
            int next = -1;
            
            //luetaan tavumuotoisesta asyötteestä koodin mukainen määrä bittejä kokonaisluvuksi
            while (true) {
                //System.out.println("(byte)this.pack[n] "+(byte)this.pack[n]);
                next = bt.fromBytes((byte)this.pack[n], bitlength);
                n++;
                //System.out.println("next "+n+" "+next);
                if (next != -1) break;
                if (n == this.pack.length) {
                    current = (int)bt.getRemainder()+128;
                    break;
                }
            }
            ArrayList bytes = new ArrayList();          
            //int current = (int)this.pack[n]+128;
            
            if (current != -1) {
                System.out.println("current "+current);
                ArrayList c = dictionary.get(current);    
                for(int i = 0; i < c.size(); i++){
                    bytes.add(c.get(i));
                    output.add(c.get(i));
                    fw.write((byte)c.get(i));
                }
            }
            
            //int next = 0;         
            if (n < this.pack.length-1 && current != -1) {
                //next = (int)this.pack[n+1]+128;
                
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
            
            //n++;
            current = next;
            
            // Tämä tarkistus bitti-tavumuunnoksia varten:
            if (code != 256 && code % Math.pow(2, (double)bitlength) == 0) {
                bitlength++;
                //System.out.println("code "+code+" bitlength "+bitlength);
            }
            
            if (current != -1) code++;
        }
        //bt.fromBytes((byte)this.pack[this.pack.length-1], bitlength);
        //current = (int)bt.getRemainder()+128;
   
        fw.write((byte)this.pack[this.pack.length-1]);
        fw.close();
        return output;
    }
}