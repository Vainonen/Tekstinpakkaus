package LZW;

import FileIO.FileRead;
import FileIO.FileWrite;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import tools.ByteTranslator;
import tools.DecodingDictionary;
import tools.EncodingDictionary;
import tools.KeyValuePair;
import tools.Node;
import tools.Nodes;

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
     * Purkaa takaisin pakatun merkistön ja tallentaa sen levylle.
     * @throws java.io.IOException
     */
    public void decompress () throws IOException {
        
        long aikaAlussa = System.currentTimeMillis(); 
        
        //LZW-sanakirjan alustus:
        DecodingDictionary dictionary = new DecodingDictionary();
        for (int i = 0; i < 256; i++) {
            Nodes <Integer> bytes = new Nodes();
            bytes.push(i);
            dictionary.add(i, bytes);
            
        }  
        
        Nodes <Integer> output = new Nodes();
        
        bt = new ByteTranslator();
        int code = 256;
        int current = getBits();

         while (n < this.pack.length) {
            int next = -1;       
            Nodes bytes = new Nodes();
            Nodes nodes = dictionary.getValue(current);  
            Node node = nodes.getFirst();

            for(int i = 0; i < nodes.Size(); i++){            
                bytes.push(node.getValue());
                int bytenumber = (int)node.getValue()-128;
                output.push(bytenumber);
                if (node.hasNext()) node = nodes.getNext(node);
                }
             
            if (n < this.pack.length) {
                next = getBits();
                if (!dictionary.contains(next)) {
                    Nodes previousCode = dictionary.getValue(code-1);
                    bytes.push((int)previousCode.getLast().getValue());
                }                
                else {
                    Nodes nextCode = dictionary.getValue(next);
                    bytes.push((int)nextCode.getFirst().getValue());
                }
                dictionary.add(code, bytes);
            }               
            current = next;
            code++;

            // Tämä tarkistus bitti-tavumuunnoksia varten:
            if ((code + 1) % Math.pow(2, (double)this.bitlength) == 0) this.bitlength++;
        }
         
        long aikaLopussa = System.currentTimeMillis(); 
        System.out.println("Purkuaika millisekunneissa "+(aikaLopussa-aikaAlussa));
            
        Node outputnode = output.getFirst();
        
        for (int i = 0; i < output.Size(); i++) {
            Integer intByte = (int)outputnode.getValue();
            outputnode = output.getNext(outputnode);
            fw.write((byte)(int)intByte);
        }

        fw.write((byte)current);
        fw.close();
        
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