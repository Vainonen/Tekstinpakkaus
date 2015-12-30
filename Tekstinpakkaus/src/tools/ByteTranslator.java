package tools;

/**
 * Luokka bitti- ja tavumuunnoksia varten.
 * Javan tiedoston luvussa ja kirjoituksessa käytetään tavukokoa,
 * joten tämä luokka pitää kirjaa LZW-algoritmin ylijäämänä jääneistä
 * biteistä, sillä LZW-pakkausessa käsitellään erimittaisia bittijonoja
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
    public Nodes toBytes (int number, int bitlength) {

        Nodes <Byte> bytes = new Nodes();
        String bs = Integer.toBinaryString(number);
        for (int i=0; i < bitlength - bs.length(); i++) {
            this.residue.append("0");
        }
        this.residue.append(bs);
        while (this.residue.length() >= 8) {
            String eightbit = this.residue.substring(0, 8);
            bytes.push((byte)Integer.parseInt(eightbit, 2));        
            this.residue.delete(0, 8);
        }       
        return bytes;       
    }
    
    /**
     * @param number tavu, josta muodostetaan kokonaisluku edellisten muunnosten perusteella tallennusta varten
     * @param bitlength LZW-sanakirjan koodista laskettu lukema, jonka kokoisena syöte palautetaan
     * @return palauttaa syötteen kokonaislukuna purkualgoritmia varten
    */
    public int fromBytes (int number, int bitlength) {
        if (number < 0) number = 256 + number; // numero muunnetaan byte-muodosta kokonaislukualueelle
        String bs = Integer.toBinaryString(number);
        for (int i=0; i < 8 - bs.length(); i++) {
            this.residue.append("0");
        }
        this.residue.append(bs);
        if (this.residue.length() < bitlength) return -1;
        int bits = Integer.parseInt(this.residue.substring(0, bitlength), 2);
        this.residue.delete(0, bitlength);    
        return bits;
    }
    
    /**
     * Metodi, joka palauttaa pakattaessa ja purettaessa viimeiset jäljelläolevat bitit
     * @return palauttaa tavumuunnoksista ylijääneen bittilukeman
    */
    public byte getRemainder() {
        String eightbit = this.residue.toString();
        return (byte)Integer.parseInt(eightbit, 2);
    } 
    
    /**
     * @return palauttaa tavumuunnoksista ylijääneen bittilukeman
    */
    public boolean isEmpty() {
        return this.residue.toString().isEmpty();
    }
}
