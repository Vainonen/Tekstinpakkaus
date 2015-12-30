package LZW;

import FileIO.FileRead;
import FileIO.FileWrite;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import tools.ByteTranslator;
import tools.EncodingDictionary;
import tools.Node;
import tools.Nodes;

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
     * Kirjoittaa n-pituisen bittijonon levylle.
     * @throws java.io.IOException
     */
    public void compress () throws IOException {
        
        long aikaAlussa = System.currentTimeMillis(); 
        
        Nodes <Integer> test = new Nodes();
        test.push((int)this.text[0]+128); 

        EncodingDictionary dictionary = new EncodingDictionary();
        for (int i = 0; i < 256; i++) {
            Nodes <Integer> bytes = new Nodes();
            bytes.push(i);
            dictionary.add(i, bytes);
        }  
  
        Nodes <Byte> output = new Nodes();
        
        int n = 0;
        int code = 256;
        int bitlength = 8; 
        ByteTranslator bt = new ByteTranslator();
        
        while (n < this.text.length-1) {
            Nodes <Integer> current = new Nodes();
            Nodes <Integer> next = new Nodes();
            current.push((int)this.text[n]+128); 
            next.push((int)this.text[n]+128); 

            while (dictionary.contains(next) && n < this.text.length-1) {
                current.push((int)this.text[n+1]+128); 
                next.push((int)this.text[n+1]+128); 
                n++;
            }
            dictionary.add(code, next);
            
            current.pop();

            // Tämä tarkistus bitti-tavumuunnoksia varten:
            if (code % Math.pow(2, (double)bitlength) == 0) bitlength++;         

            Nodes <Byte> translated = bt.toBytes((int)dictionary.getCode(current), bitlength);
            
            Node node = translated.getFirst();
        
            for (int i = 0; i < translated.Size(); i++) {
                Byte b = (byte)node.getValue();
                output.push(b);
                node = translated.getNext(node);
            }
            code++;
        
        }
        
        long aikaLopussa = System.currentTimeMillis(); 
        System.out.println("Pakkausaika millisekunneissa "+(aikaLopussa-aikaAlussa));
        
        Nodes <Byte> translated = bt.toBytes((int)this.text[n], bitlength);
        Node node = translated.getFirst();
        
            for (int i = 0; i < translated.Size(); i++) {
                Byte b = (byte)node.getValue();
                output.push(b);
                node = translated.getNext(node);
            }
            
        Node outputnode = output.getFirst();
        
            for (int i = 0; i < output.Size(); i++) {
                Byte b = (byte)outputnode.getValue();
                fw.write(b);
                if (outputnode.hasNext()) outputnode = output.getNext(outputnode);
            }
        if (!bt.isEmpty()) {
            fw.write(bt.getRemainder());
        }
        fw.close();
    }
}