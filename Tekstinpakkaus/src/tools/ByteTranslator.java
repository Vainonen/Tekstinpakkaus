package tools;

import java.util.ArrayList;

/**
 * Luokka bitti- ja tavumuunnoksia varten.
 */
public class ByteTranslator {
    
    private StringBuilder residue;
    
    public ByteTranslator() {
        this.residue = new StringBuilder();
    }
    
    /**
     * @param number kokonaisluku, joka pilkotaan tavuiksi
     * @param bitlength LZW-sanakirjan koodista laskettu lukema, jonka suuruisiin osiin syöte pilkotaan
     * @return palauttaa listana tavun kokoisiin osiin pilkotun syötteen tallennusta varten
    */
    public ArrayList toBytes (int number, int bitlength) {
            
        ArrayList <Byte> bytes = new ArrayList();
        String bs = Integer.toBinaryString(number);
        for (int i=0; i < bitlength - bs.length(); i++) {
            this.residue.append("0");
        }
        this.residue.append(bs);
   
        for (int i = 0; i < bitlength - 8; i += 8) {
            String eightbit = this.residue.substring(i, i+8);
            bytes.add((byte)Integer.parseInt(eightbit, 2));
        }
        int r = (this.residue.length() % 8);
        this.residue = new StringBuilder(this.residue.substring(this.residue.length() -r, this.residue.length()));

        return bytes;
        
    }
    
    /**
     * @param number tavu, josta muodostetaan kokonaisluku edellisten muunnosten perusteella tallennusta varten
     * @param bitlength LZW-sanakirjan koodista laskettu lukema, jonka kokoisena syöte palautetaan
     * @return palauttaa syötteen kokonaislukuna purkualgoritmia varten
    */
    public int fromBytes (byte number, int bitlength) {
        String bs = Integer.toBinaryString(number+128);
        for (int i=0; i < 8 - bs.length(); i++) {
            this.residue.append("0");
        }
        this.residue.append(bs);

        if (this.residue.length() < bitlength) return -1;
        int bits = Integer.parseInt(this.residue.substring(0, bitlength-1), 2);
        this.residue.delete(0, bitlength);
        return bits;
    }
    
    /**
     * @return palauttaa tavumuunnoksista ylijääneen bittilukeman
    */
    public byte getRemainder() {
        String eightbit = this.residue.toString();
        return (byte)Integer.parseInt(eightbit, 2);
    } 
}
