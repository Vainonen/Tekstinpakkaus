package LZW;

import FileIO.FileRead;
import FileIO.FileWrite;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import tools.ByteTranslator;

/**
 * Luokka merkkijonojen pakkausta varten.
 */
public class Compressor {
    
    private byte [] text;
    private FileWrite fw;
    
    /**
     * @param input Luettavan tiedoston nimi.
     * @param output Pakkaustiedoston nimi,
     * @throws java.io.FileNotFoundException
    */
    public Compressor (String input, String output) throws FileNotFoundException, IOException {
        FileRead fr = new FileRead(input);
        this.text = fr.read();
        this.fw = new FileWrite(output);
    }
  
    /**
     * Palauttaa n-pituisen bittijonon.
     * @return ArrayList, johon eripituiset bittijonot muutettu
     * tavuiksi tiedostoon kirjoittamista varten.
     * @throws java.io.IOException
     */
    public ArrayList compress () throws IOException {
           
        //LZW-sanakirjan alustus:
        HashMap <ArrayList, Integer> dictionary = new HashMap();
        for (int i = 0; i < 256; i++) {
            ArrayList <Byte> bytes = new ArrayList();
            byte b =(byte)i;
            bytes.add(b);
            dictionary.put(bytes, i);
        }  
        
        ArrayList <Byte> output = new ArrayList();
        
        int n = 0;
        int code = 256;
        int bitlength = 8; 
        ByteTranslator bt = new ByteTranslator();
        
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
            
            // Tämä tarkistus bitti-tavumuunnoksia varten:
            if (code % Math.pow(2, (double)bitlength) == 0) {
                bitlength++;         
            }
            
            ArrayList <Byte> translated = bt.toBytes((int)dictionary.get(current), bitlength);
            for (Byte b : translated) {
                output.add(b);
                fw.write(b);
            }
            code++;              
        }
        ArrayList <Byte> translated = bt.toBytes((int)this.text[n], bitlength);
        for (Byte b : translated) {
            output.add(b);
            fw.write(b);
        }
        output.add(bt.getRemainder());
        fw.write(bt.getRemainder());
        fw.close();
        return output;
    }
}